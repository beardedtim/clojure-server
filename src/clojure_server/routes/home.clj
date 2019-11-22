(ns clojure-server.routes.home
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.pprint :refer :all]))

(defn home-page
  "
  handles the home page
  "
  [request]
  (pprint request)
  (clojure.string/upper-case "the world is round"))
  