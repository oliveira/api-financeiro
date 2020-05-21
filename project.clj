(defproject api-financeiro "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler api-financeiro.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]
                        [midje "1.9.6"]
                        [ring/ring-core "1.7.1"]
                        [ring/ring-jetty-adapter "1.7.1"]]
         :plugins[[lein-midje "3.2.1"]
                  [lein-cloverage "1.0.13"]]}})