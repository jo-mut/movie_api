(ns movie-api.api
  (:require [clj-http.client :as client]))


(def base-url "https://api.themoviedb.org/3/")

;; (print (System/getenv "API_KEY"))

(defn query-params
  [page]
  {:include_adult "false"
   :include_video "false"
   :language      "en-US"
   :page           page
   :sort_by        "popularity.desc"
   :year           "2024"})

(defn build-url
  [search-type]
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

(defn fetch-content
  [search-type]
  (let [response (client/get (build-url search-type)
                             {:accept       :json
                              :headers      {:Authorization (str "Bearer " "")}
                              :query-params (query-params 1)})]
    (get response :body)))
