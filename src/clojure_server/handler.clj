(ns clojure-server.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [clojure-server.routes.request :refer :all]
            [clojure-server.static-assets.handler :refer :all]

            [ring.middleware.json :as middleware]))

(def app-handler
  (routes request-routes
          static-files 
          (route/not-found "Not Found")))

(def app
  (-> (handler/site app-handler)
      (middleware/wrap-json-body {:keywords? true})
      middleware/wrap-json-response))

