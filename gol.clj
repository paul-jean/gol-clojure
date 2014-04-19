;; Anything you type in here will be executed
;; immediately with the results shown on the
;; right.
(defn f [v1 v2] (map + v1 v2))

(f [0 1 2] [3 4 5])

(reduce + [1 2 3])


(defn middle-row-sum [row] (+ (row 0) (row 2)))

(defn sum-excluding-middle [matrix]
  (+ (reduce + (matrix 0)) (middle-row-sum (matrix 1)) (reduce + (matrix 2))))

(sum-excluding-middle [[0 1 2] [3 4 5] [6 7 8]])

(sum-excluding-middle [[0 0 0] [1 1 1] [2 3 4]])

(subvec [0 1 2] 0 2)

(defn neighborhood [matrix row col]
  (map #(subvec % (- col 1) (+ col 2)) (subvec matrix (- row 1) (+ row 2))))

(defn new-val [nbhd]
  (let [s (sum-excluding-middle nbhd) center ((nbhd 1) 1)]
    (if (= center 0)
      (if (= s 3) 1 0)
      (if (< s 2) 0
        (if (or (= s 2) (= s 3)) 1 0)))))

(def matrix [[0 1 2 3] [4 5 6 7] [8 9 10 11]])

(map #(subvec % 0 3) (subvec matrix 0 3))

(neighborhood matrix 1 1)

(new-val [[0 0 0] [0 0 0] [0 0 0]])

(new-val [[0 0 1] [1 0 0] [0 1 0]])

(count [1 2 3])

(count [[1 2 3] [4 5 6]])

(defn pad-matrix [matrix]
  (let [cols (+ (count (matrix 0)) 2)]
    (into (into [(vec (replicate cols 0))] (map #(into (into [0] %) [0]) matrix)) [(vec (replicate cols 0))])
  ))

(pad-matrix [[0]])

(defn update [matrix]
  (let [rows (count matrix) cols (count (matrix 0))]
    (map #(new-val (neighborhood matrix %1 %2)) (range 1 (- rows 1)) (range 1 (- cols 1)))))

(def zeros (vec (replicate 4 (vec (replicate 4 0)))))

zeros

(update zeros)
