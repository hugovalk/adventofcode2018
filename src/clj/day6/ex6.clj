(ns day6.ex6
  (:require [clojure.java.io :as io]))

(defn get-lines []
  (with-open [rdr (io/reader "./input.txt")]
    (->> (doall (line-seq rdr))
         (map (fn [s]
                (->> (.split s ",")
                     (vec)
                     (map (fn [i] (Integer/parseInt (.trim i))))))))))


(let [lines (get-lines)]
  (println lines))