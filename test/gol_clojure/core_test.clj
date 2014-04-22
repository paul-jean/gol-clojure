(ns gol-clojure.core-test
  (:require [clojure.test :refer :all]
            [gol-clojure.core :refer :all]))

(deftest sum-excluding-middle-test
  (testing "sum-excluding-middle:"
    (let [matrix [[1 2 3] [4 5 6] [7 8 9]]]
      (is (= 40 (sum-excluding-middle matrix))))))

(deftest neighborhood-test
  (testing "neighborhood:"
    (let [matrix [[1 2 3 4] [5 6 7 8] [9 10 11 12]]]
      (is (= [[1 2 3] [5 6 7] [9 10 11]] (neighborhood matrix 1 1))))))

(deftest pad-matrix-test
  (testing "pad-matrix:"
    (let [matrix [[0 0] [0 0]]]
      (is (= [[0 0 0 0] [0 0 0 0] [0 0 0 0] [0 0 0 0]] (pad-matrix matrix))))))

; The following tests should really be stubbing out calls to other functions
; to be true unit tests. But I can't figure out how to do that yet.
; I tried to use clojure.contrib/mock unsuccessfully.

(deftest new-val-test
  (testing "new-val"
    (testing "when the center cell is dead"
      (testing "with exactly 3 alive neighbors"
        (let [matrix [[1 1 1] [0 0 0] [0 0 0]]]
          (is (= 1 (new-val matrix)))))
      (testing "with fewer than 3 alive neighbors"
        (let [matrix [[1 1 0] [0 0 0] [0 0 0]]]
          (is (= 0 (new-val matrix))))))
    (testing "when the center cell is alive"
      (testing "with fewer than 2 alive neighbors"
        (let [matrix [[1 0 0] [0 1 0] [0 0 0]]]
          (is (= 0 (new-val matrix)))))
      (testing "with 2 alive neighbors"
        (let [matrix [[1 1 0] [0 1 0] [0 0 0]]]
          (is (= 1 (new-val matrix)))))
      (testing "with 3 alive neighbors"
        (let [matrix [[1 1 1] [0 1 0] [0 0 0]]]
          (is (= 1 (new-val matrix))))))))

(deftest new-row-test
  (testing "new-row"
    (let [matrix [[0 0 0 0 0] [0 1 0 1 0] [0 0 1 0 0] [0 0 0 1 0] [0 0 0 0 0]]]
      (is (= [0 1 0] (new-row matrix 1)))
      (is (= [0 1 1] (new-row matrix 2)))
      (is (= [0 0 0] (new-row matrix 3))))))

(deftest update-test
  (testing "update"
    (let [matrix [[0 0 0 0 0] [0 1 0 1 0] [0 0 1 0 0] [0 0 0 1 0] [0 0 0 0 0]]]
      (is (= [[0 0 0 0 0] [0 0 1 0 0] [0 0 1 1 0] [0 0 0 0 0] [0 0 0 0 0]] (update matrix))))))
