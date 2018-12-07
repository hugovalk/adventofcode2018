(ns day2.ex2
  (:require [clojure.java.io :as io]))

(defn get-lines []
  (with-open [rdr (io/reader "./input.txt")]
    (doall (line-seq rdr))))

(defn find-dupes-triples [lines]
  (map
    (fn [s]
      (->>
        (group-by (fn [c] c) s)
        (into {} (map (fn [[k v]] [k (count v)])))
        (vals)
        (filter (fn [v] (< 1 v 4)))
        (set)))
    lines))

(defn count-dupes [dtr]
  (count (filter #(contains? %1 2) dtr)))

(defn count-triples [dtr]
  (count (filter #(contains? %1 3) dtr)))

(defn num-diff [one two]
  (let [m (map vector one two)]
    (count
      (filter (fn [[k v]] (not (= k v))) m))))

(defn find-div-1 [lines]
  (let [f (first lines)
        r (rest lines)
        match (filter (fn [e] (= 1 (num-diff e f))) r)]
    (if (empty? match)
        (recur r)
        (list f (first match)))))

(let [lines (get-lines)
      dt (find-dupes-triples lines)
      dupes (count-dupes dt)
      triples (count-triples dt)]
  (println (take 10 lines))
  (println "#containing 2: " dupes)
  (println "#containing 3: " triples)
  (println "Checksum: " (* dupes triples))
  (println "Similar boxes: " (find-div-1 lines)))

