(ns movie-api.routes
  (:require
   [movie-api.api :as api]))

(defn echo-route
  "Echo back the request"
  [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (-> (str "GET '/' " req))})

(defn fetch-lists 
  [search-type page]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (-> (api/fetch-content {:search-type search-type 
                                 :page        page}))})

(defn fetch-details
  [search-type id]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (-> (api/fetch-content {:search-type search-type
                                 :id          id}))})
