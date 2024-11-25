(ns movie-api.api
  (:require [clj-http.client :as client]))


(def base-url "https://api.themoviedb.org/3")
(def API_KEY "")

;; (print (System/getenv "API_KEY"))

(defn query-params
  [page]
  (merge
   {:include_adult "false"
    :include_video "false"
    :language      "en-US"
    :sort_by        "popularity.desc"
    :year           "2024"}
   (when page 
     {:page           page})))

(defn build-url
  ([search-type]
   (case search-type
     "discover"         (str base-url "/discover/movie")
     "find"             (str base-url "/find/")
     "search"           (str base-url "/search/movie")
     "upcoming-movies"  (str base-url "/search/upcoming")
     "top-rated-movies" (str base-url "/search/top-rated")
     "popular-movies"   (str base-url "/search/popular")
     "series"           (str base-url "/tv")
     "popular-series"   (str base-url "/tv/popular")
     "top-rated-series" (str base-url "/tv/top_rated")))
  ([search-type id]
   (case search-type
     "movie"            (str base-url "/movie/" id))))

(defn fetch-content
  [{:keys [search-type page id]}]
  (print search-type)
  (let [response (client/get (if id
                               (build-url search-type id)
                               (build-url search-type))
                             {:accept       :json
                              :headers      {:Authorization (str "Bearer " API_KEY)}
                              :query-params (query-params page)})]
    (get response :body)))
