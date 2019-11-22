(ns clojure-server.routes.other
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.pprint :refer :all]
            [http.async.client :as http]))

(defn other-handler
  "
  handles some other page
  "
  [request]
  (pprint request)
  {:status 201 :body {:hello "world" } })


(defn post-handler
"
I handle stuff for post
"
[request]
(let [name (or (get-in request [:params :name])
                   (get-in request [:body :name])
                   "John Doe")]
  { :body (get-in request [:body])}))

(defroutes other-routes
  (context "/other" []
    (GET "/some" request (other-handler request))
    (POST "/some" request (post-handler request))))