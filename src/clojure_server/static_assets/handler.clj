(ns clojure-server.static-assets.handler
  (:require [clojure.java.io :as io]
            [stasis.core :as stasis]
            [hiccup.page :refer [html5]]))

(defn layout-page
  "
  Adds generic markup to the given page data
  "
  [page]
  (html5
    [:head
    [:meta {:charset "utf-8"}]
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1.0"}]
    [:title "Tech blog"]
    [:link {:rel "stylesheet" :href "/styles/styles.css"}]]
    [:body
    [:div.logo "cjohansen.no"]
    [:div.body page]]))

(defn partial-pages [pages]
  (zipmap (keys pages)
          (map layout-page (vals pages))))

(defn get-pages []
  (stasis/merge-page-sources
    {:public (partial-pages (stasis/slurp-directory "resources/public" #".*\.(html|css|js)$"))
                  
    :partials (partial-pages (stasis/slurp-directory "resources/partials" #".*\.html$"))}))

(def static-files (stasis/serve-pages get-pages))