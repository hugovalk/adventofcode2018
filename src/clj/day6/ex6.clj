(ns day6.ex6
  (:require [clojure.java.io :as io]))

(defn get-lines []
  (with-open [rdr (io/reader "./input.txt")]
    (->> (doall (line-seq rdr))
         (map (fn [s]
                (->> (.split s ",")
                     (vec)
                     (map (fn [i] (Integer/parseInt (.trim i))))))))))

(defn man-dis [a b]
  (+
    (Math/abs (- (first a) (first b)))
    (Math/abs (- (second a) (second b)))))

(defn max-xy [lines]
  (reduce (fn [res l]
            [(max (first res) (first l))
             (max (second res) (second l))])
          [0 0] lines))

(defn world [max]
  (vec (map (fn [_] (vec (repeat (first max) -1)))
            (repeat (second max) []))))

(defn print-world [world]
  (doseq [w world]
    (println w)))

(let [lines (get-lines)
      locs (map vector (range (count lines)) lines)
      max (max-xy lines)]
  (print-world (world max)))