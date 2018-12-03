(ns day1.ex1
  (:require [clojure.java.io :as io]))

(defn get-lines []
  (with-open [rdr (io/reader "./src/clj/day1/input.txt")]
    (let [l (doall (line-seq rdr))]
      (map (fn [i] (Integer/parseInt i)) l))))

(defn sum [lines]
  (reduce + lines))

(defn aggregating-sum
  ([x] [x])
  ([coll x] (conj coll (+ (last coll) x))))

(defn first-repeating
  ([lines] (first-repeating lines 0 (set nil)))
  ([lines sum found]
   (let [sums (rest (reduce aggregating-sum [sum] lines))]
     (if (every? #(not (contains? found %1)) sums)
       (recur lines (last sums) (set (concat sums found)))
       (first (filter #(contains? found %1) sums))))))

(defn -main []
  (let [lines (get-lines)]
    (println lines)
    (println (str "The sum is: " (sum lines)))
    (println (str "The first repeating number is: " (first-repeating lines)))))