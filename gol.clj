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

(def matrix [[0 1 2 3] [4 5 6 7] [8 9 10 11]])

(map #(subvec % 0 3) (subvec matrix 0 3))

(neighborhood matrix 1 1)