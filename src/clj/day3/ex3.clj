(ns day3.ex3
  (:require [clojure.java.io :as io]))

(defn parse-line [line]
  (->>
    (re-matches #"^.* @ ([0-9]+),([0-9]+).*([0-9]+)x([0-9]+).*$" line)
    (rest)
    (map (fn [s] (Integer/parseInt s)))))

(defn get-lines []
  (with-open [rdr (io/reader "./input.txt")]
    (->>
      (line-seq rdr)
      (map parse-line)
      (doall))))

(defn multiple-claimed-inches [])

(let [lines (get-lines)]
  (println lines)
  (println "The # of square inches having multiple claims: " (multiple-claimed-inches)))