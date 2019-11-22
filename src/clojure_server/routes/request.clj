(ns clojure-server.routes.request
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.pprint :refer :all]
            [http.async.client :as http]))

(defn make-http-request
  "
  Makes an HTTP request given a URL
  "
  [url]
  (with-open [client (http/create-client)]
    (let [response (http/GET client (str "https://" url))]
      (-> response
          http/await
          http/string))))

(defroutes request-routes
  (context "/request" []
    (GET "/:url" [url] (make-http-request url))))