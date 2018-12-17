(ns day6.ex6
  (:require [clojure.java.io :as io])
  (:import (java.util Date)))

(defn abs [a b]
  (Math/abs (- a b)))

(defn indices [coll]
  (range (count coll)))

(defn zip-with-index [coll]
  (map vector coll (indices coll)))

(defn get-lines []
  (with-open [rdr (io/reader "./input.txt")]
    (->> (doall (line-seq rdr))
         (map (fn [s]
                (->> (.split s ",")
                     (vec)
                     (map (fn [i] (Integer/parseInt (.trim i))))))))))

(defn max-xy [lines]
  (reduce (fn [res l]
            [(max (first res) (first l))
             (max (second res) (second l))])
          [0 0] lines))

(defn init-world [max]
  (vec (map (fn [_] (vec (repeat (+ 1 (first max)) -1)))
            (repeat (+ 1 (second max)) []))))

(defn print-world [world]
  (doseq [w world]
    (println w)))

(defn manhattan-distances [loc world]
  (let [rows-i (zip-with-index world)]
    (reduce (fn [row-res row-i]
              (conj row-res
                    (map (fn [c] (+ c (abs (second loc) (second row-i))))
                         (reduce (fn [col-res col]
                                   (conj col-res (abs (first loc) col)))
                                 [] (indices (first row-i))))))
            [] rows-i)))

(def start (.getTime (Date.)))
(let [lines (get-lines)
      locs (zip-with-index lines)
      max (max-xy lines)
      world (init-world max)
      locs-dista (map (fn [loc] manhattan-distances loc world) locs)]
  (println (count locs-dista))
  (println "Done in" (- (.getTime (Date.)) start) "ms"))