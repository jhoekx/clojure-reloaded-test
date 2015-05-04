(ns reload-test.routable)

(defprotocol Routable
  (get-routes [component]))
