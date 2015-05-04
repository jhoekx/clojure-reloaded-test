(ns reload-test.incrementor
  (:require [com.stuartsierra.component :as component]
            [compojure.core :refer :all]
            [reload-test.routable :refer :all]))

(defn- get-value [incrementor]
  (deref (:value incrementor)))

(defn- increment [incrementor]
  (swap! (:value incrementor) #(+ % 1)))

(defrecord Incrementor []
  component/Lifecycle
  (start [component]
    (println ";; Starting incrementor")
    (assoc component :value (atom 0)))
  (stop [component]
    (println ";; Stopping incrementor")
    (dissoc component :value))
  Routable
  (get-routes [incrementor]
    (routes
      (GET "/" []  (str (get-value incrementor)))
      (POST "/" [] (str (increment incrementor))))))

(defn new-incrementor []
  (->Incrementor))
