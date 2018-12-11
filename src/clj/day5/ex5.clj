(ns day5.ex5
  (:require [clojure.java.io :as io]))

(defn get-lines []
  (with-open [rdr (io/reader "./input.txt")]
    (first (line-seq rdr))))

(defn react [res next]
  (if (= 32 (Math/abs
              (- (int next)
                 (int (first res)))))
    (rest res)
    (conj res next)))

(let [sequence (get-lines)]
  (println sequence)
  (println (reduce react () sequence)))