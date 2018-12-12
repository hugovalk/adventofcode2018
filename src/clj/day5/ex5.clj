(ns day5.ex5
  (:require [clojure.java.io :as io]))

(defn get-lines []
  (with-open [rdr (io/reader "./input.txt")]
    (first (line-seq rdr))))

(defn differs-case? [a b]
  (= 32 (Math/abs (- (int a) (int b)))))

(defn react [res next]
  (cond
    (nil? (first res)) (conj res next)
    (differs-case? next (first res)) (rest res)
    :else (conj res next)))

(defn counts-optimized [sequence]
  (let [types (distinct (.toLowerCase sequence))]
    (->> (map (fn [t]
                (filter (fn [c] (not= (Character/toLowerCase c) t)) sequence)) types)
         (map (fn [s] (reduce react () s)))
         (map (fn [s] (count s)))
         (map vector types))))

(let [sequence (get-lines)]
  (println sequence)
  (println "number of units: " (count sequence))
  (println "number of units left after reaction: " (count (reduce react () sequence)))
  (println "number of units left after reaction for optimized polymers: "
           (sort-by second < (counts-optimized sequence))))