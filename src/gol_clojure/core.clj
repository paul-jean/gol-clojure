(ns gol-clojure.core)

(defn -main []
  (println "Hello World!"))

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
    (let [rows (count matrix) cols (count (matrix 0))]
      (pad-matrix (mapv #(vec (new-row matrix %)) (range 1 (- rows 1))))))
