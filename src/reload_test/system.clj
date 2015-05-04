(ns reload-test.system
  (:require [com.stuartsierra.component :as component]
            [compojure.core :refer :all]
            [ring.component.jetty :refer [jetty-server]]
            [reload-test.routable :refer :all]
            [reload-test.messenger :as messenger]
            [reload-test.incrementor :as incrementor]))

(defn handle [messenger incrementor]
  (routes
    (context "/message" []
      (get-routes messenger))
    (context "/increment" []
      (get-routes incrementor))))

(defrecord AppHandler [messenger incrementor]
  component/Lifecycle
  (start [component]
    (assoc component :handler (handle messenger incrementor)))
  (stop [component]
    (dissoc component :handler)))

(defn new-handler []
  (map->AppHandler {}))

(defn web-system []
  (component/system-map
    :messenger (messenger/new-messenger "hello world")
    :incrementor (incrementor/new-incrementor)
    :app (component/using
           (new-handler)
           [:messenger :incrementor])))

(defn dev-system []
  (-> (web-system)
      (assoc :server (component/using
                       (jetty-server {:port 3000})
                       [:app]))))
