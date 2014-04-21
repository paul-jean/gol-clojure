(ns gol-clojure.run
  (:require [gol-clojure.core :as gol]))

(defn -main []
  (println "Hello World!"))

(println (gol/sum-excluding-middle [[0 0 1] [1 1 0]]))
