(ns gol-clojure.core
  (:require [clojure.string]))

(defn sum-excluding-middle [matrix]
  (- (reduce + (flatten matrix)) (get-in matrix [1 1])))

(defn neighborhood [matrix row col]
  (vec (map #(subvec % (- col 1) (+ col 2)) (subvec matrix (- row 1) (+ row 2)))))

(defn new-val [nbhd]
  (let [s (sum-excluding-middle nbhd) center ((nbhd 1) 1)]
    (if (= center 0)
      (if (= s 3) 1 0)
      (if (< s 2) 0
        (if (or (= s 2) (= s 3)) 1 0)))))

(defn pad-matrix [matrix]
  (let [cols (+ (count (matrix 0)) 2)]
    (into (into [(vec (replicate cols 0))] (map #(into (into [0] %) [0]) matrix)) [(vec (replicate cols 0))])
  ))

(defn new-row [matrix row]
  (let [cols (count (matrix 0))]
    (map #(new-val (neighborhood matrix row %))
         (range 1 (- cols 1)))))

(defn update [matrix]
    (let [padded (pad-matrix matrix) rows (count padded)]
      (vec (map #(vec (new-row padded %)) (range 1 (- rows 1))))))

(defn rand-matrix [n]
   (vec (repeatedly n (fn [] (vec (repeatedly n (fn [] (rand-int 2))))))))

(defn rand-matrix [n m]
   (vec (repeatedly n (fn [] (vec (repeatedly m (fn [] (rand-int 2))))))))

(defn print-row [row]
  (clojure.string/join (vec (map #(format "%s" (if (= % 1) "o" "-")) row))))

(defn matrix-string [matrix]
  (clojure.string/join "\n" (vec (map #(print-row %) matrix))))
