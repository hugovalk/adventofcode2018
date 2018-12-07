(ns day3.ex3
  (:require [clojure.java.io :as io]))

(defn parse-line [line]
  (->>
    (re-matches #"^#([0-9]+) @ ([0-9]+),([0-9]+): ([0-9]+)x([0-9]+).*$" line)
    (rest)
    (map (fn [s] (Integer/parseInt s)))))

(defn get-lines []
  (with-open [rdr (io/reader "./input.txt")]
    (->>
      (line-seq rdr)
      (map parse-line)
      (doall))))

(defn locations [l]
  (let [x0 (+ 1 (nth l 1))
        y0 (+ 1 (nth l 2))
        xs (range x0 (+ x0 (nth l 3)))
        ys (range y0 (+ y0 (nth l 4)))]
    (for [x xs y ys] (str x "x" y))))

(defn claims [lines]
  (->> (map locations lines)
       (flatten)
       (group-by (fn [id] id))
       (into {} (map (fn [[k v]] [k (count v)])))))

(defn multiple-claimed-inches [claims]
  (->> (vals claims)
       (filter (fn [e] (<= 2 e)))
       (count)))

(let [lines (get-lines)]
  (println (take 10 lines))
  (let [claims (claims lines)
        mci (multiple-claimed-inches claims)]
    (println (take 10 claims))
    (println "The # of square inches having multiple claims: " mci)
    (println "The lines that contain no double claims: "
             (filter (fn [l]
                       (let [locs (locations l)]
                         (every? (fn [e] (= (claims e) 1)) locs)))
                     lines))))
