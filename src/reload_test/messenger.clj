(ns reload-test.messenger
  (:require [com.stuartsierra.component :as component]
            [compojure.core :refer :all]
            [reload-test.routable :refer :all]))

(defn- get-message [printer]
  (:message printer))

(defrecord Messenger [msg]
  component/Lifecycle
  (start [component]
    (println ";; Starting messenger")
    (assoc component :message msg))
  (stop [component]
    (println ";; Stopping messenger")
    (dissoc component :message))
  Routable
  (get-routes [messenger]
    (routes
      (GET "/" [] (get-message messenger)))))

(defn new-messenger [msg]
  (map->Messenger {:msg msg}))
