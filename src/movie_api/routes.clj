(ns movie-api.routes)

(defn echo-route
  "Echo back the request"
  [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (-> (str "GET '/' " req))})

