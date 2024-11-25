(ns movie-api.core
  (:require
   [dotenv :refer [env]]
   [compojure.route :as route]
   [compojure.core :refer [defroutes GET POST]]
   [org.httpkit.server :as server]
   [ring.middleware.json :as js]
   [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
   [ring.middleware.reload :refer [wrap-reload]]
   [movie-api.routes :as routes])
  (:gen-class))

(defroutes app-routes
  (GET "/" [] routes/echo-route)
  (GET "/discover" [] (routes/fetch-lists "discover" 1))
  (GET "/movie/:id" [id] (routes/fetch-details "movie" id))
  (route/not-found "Page not found"))

(defn -main
  "Production"
  [& args]
  (let [port  (Integer/parseInt (or (env :PORT) "3000"))
        host (or (env :HOST) "0.0.0.0")]
    (server/run-server
     (js/wrap-json-params
      (js/wrap-json-response
       (wrap-defaults app-routes api-defaults)))
     {:port port
      :host host})
    (println (str "Running webserver at http://0.0.0.0:" port "/"))))


(defn -dev-main
  "Dev/Test (auto reload watch enabled)"
  [& args]
  (let [port (Integer/parseInt (env :PORT))
        host (or (env :HOST) "0.0.0.0")]
    (server/run-server
     (wrap-reload
      (js/wrap-json-params
       (js/wrap-json-response
        (wrap-defaults #'app-routes api-defaults))))
     {:port port
      :host host})
    (println (str "Running webserver at http://0.0.0.0:" port "/"))))
