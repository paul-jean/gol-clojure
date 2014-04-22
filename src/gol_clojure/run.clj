(ns gol-clojure.run
  (:require [gol-clojure.core :as gol]))

(defn -main []
  (doseq
    [state (iterate
      (fn [matrix] (gol/update matrix))
      (gol/rand-matrix 25 50))]

    (println (gol/matrix-string state))
    (read-line)))
