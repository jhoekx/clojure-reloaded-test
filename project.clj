(defproject reload_test "0.1.0-SNAPSHOT"
  :description "Test of the reloaded workflow with components"
  :url "https://github.com/jhoekx/clojure-reloaded-test"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.stuartsierra/component "0.2.1"]
                 [ring-jetty-component "0.2.2"]
                 [compojure "1.3.3"]]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.4"]]
                   :source-paths ["dev"]}})
