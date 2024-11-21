(defproject movie-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 ; Compojure - A basic routing library
                 [compojure "1.6.1"]
                 ; Our Http library for client/server
                 [http-kit "2.3.0"]
                 ; Ring defaults - for query params etc
                 [ring/ring-defaults "0.3.4"]
                 [ring/ring-devel "1.6.3"]
                 [ring/ring-json "0.5.0"]
                 ; Clojure JDBC
                 [org.clojure/java.jdbc "0.7.10"]
                 ; Postgresql
                 [org.postgresql/postgresql "42.3.1"]
                 [cheshire "5.11.0"]
                 [http-kit "2.3.0"]
                 [clj-http "2.3.0"]
                 [ns-tracker "0.4.0"]
                 [lynxeyes/dotenv "1.0.2"]
                 [re-frame "1.4.3"]]
  :plugins [[lein-environ "1.2.0"]]
  :main ^:skip-aot movie-api.core/-main
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                       :dev {:main movie-api.core/-dev-main}}})
