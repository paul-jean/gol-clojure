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
  (vec (map #(subvec % (- col 1) (+ col 2)) (subvec matrix (- row 1) (+ row 2)))))

(defn new-val [nbhd]
  (let [s (sum-excluding-middle nbhd) center ((nbhd 1) 1)]
    (if (= center 0)
      (if (= s 3) 1 0)
      (if (< s 2) 0
        (if (or (= s 2) (= s 3)) 1 0)))))

(def matrix [[0 1 2 3] [4 5 6 7] [8 9 10 11]])

(map #(subvec % 0 3) (subvec matrix 0 3))

matrix

(neighborhood matrix 1 1)
(neighborhood matrix 1 2)

;; [0,0] -> 0
(new-val [[0 0 0] [0 0 0] [0 0 0]])
;; [0,1] -> 0
(new-val [[0 1 0] [0 0 0] [0 0 0]])
;; [0,2] -> 0
(new-val [[0 1 0] [1 0 0] [0 0 0]])
;; [0, 3] -> 1
(new-val [[0 0 1] [1 0 0] [0 1 0]])
;; [0, 4] -> 0
(new-val [[0 0 1] [1 0 0] [1 1 0]])
;; [0, 5] -> 0
(new-val [[0 1 1] [1 0 0] [1 1 0]])
;; [0, 8] -> 0
(new-val [[1 1 1] [1 0 1] [1 1 1]])

(defn pad-matrix [matrix]
  (let [cols (+ (count (matrix 0)) 2)]
    (into (into [(vec (replicate cols 0))] (map #(into (into [0] %) [0]) matrix)) [(vec (replicate cols 0))])
  ))

(pad-matrix [[0]])
(pad-matrix [[0 0] [0 0]])

(range 1 (- 3 1))
(vec (range 1 4))

(map #(new-val (neighborhood matrix %1 %2)) (range 1 (- 3 1)) (range 1 (- 4 1)))

((fn [] (new-val (neighborhood matrix 1 1))))

((fn [arg1 arg2] (new-val (neighborhood matrix arg1 arg2))) 1 1)

(map (fn [arg1 arg2] (new-val (neighborhood matrix arg1 arg2))) (vec (range 1 (- 3 1))) (vec (range 1 (- 4 1))))

(defn update [matrix]
  (let [rows (count matrix) cols (count (matrix 0))]
    (vec (map (fn [arg1 arg2] (new-val (neighborhood matrix arg1 arg2))) (vec (range 1 (- rows 1))) (vec (range 1 (- cols 1)))))))

(defn update [matrix]
  (let [rows (count matrix) cols (count (matrix 0))]
   (vec (map
     (fn [c] (vec (map
                   (fn [r] (new-val (neighborhood matrix r c)))
                   (vec (range 1 (- rows 1)))
                   )))
     (vec (range 1 (- cols 1)))
     ))))

(map (fn [x] x) [1 2 3])

(def zeros (pad-matrix (vec (replicate 3 (vec (replicate 3 0))))))

zeros

(count zeros)
(vec (range 1 (- rows 1)))

(update zeros)

(test-update zeros)

(rand-int 2)
(vec (repeatedly 5 (fn [] (vec (repeatedly 5 (fn [] (rand-int 2)))))))
(defn rand-matrix [n]
   (vec (repeatedly n (fn [] (vec (repeatedly n (fn [] (rand-int 2)))))))
  )

(rand-matrix 4)

(def m (pad-matrix (rand-matrix 4)))

m

(update m)
(update (pad-matrix (update m)))

;; (reduce #(pad-matrix (update %)) )
