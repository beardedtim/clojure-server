(ns clojure-server.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [clojure-server.routes.home :refer :all]
            [clojure-server.routes.other :refer :all]
            [clojure-server.routes.request :refer :all]
            [ring.middleware.json :as middleware]))

(defroutes app-routes
  (GET "/" request (home-page request)))

(def app-handler
  (routes app-routes
          other-routes
          request-routes
          (route/not-found "Not Found")))

(def app
  (-> (handler/site app-handler)
      (middleware/wrap-json-body {:keywords? true})
      middleware/wrap-json-response))
