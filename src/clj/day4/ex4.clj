(ns day4.ex4
  (:require [clojure.java.io :as io]))

(defn cast-line [line]
  (let [frst (list (first line))
        ints (rest (take 3 line))
        lst (drop 3 line)]
    (concat frst
            (map (fn [i] (Integer/parseInt i)) ints)
            lst)))

(defn parse-line [line]
  (->>
    (re-matches #"^\[(\d{4}-\d\d-\d\d) (\d\d):(\d\d)\].*?([\d]+)? (begins|falls|wakes).*$" line)
    (rest)
    (cast-line)))

(defn get-lines []
  (with-open [rdr (io/reader "./input.txt")]
    (->>
      (line-seq rdr)
      (sort-by (fn [l] l))
      (map parse-line)
      (doall))))


(defmulti find-sleep
  (fn [res line] (last line)))

(defmethod find-sleep "begins" [res line]
  (conj res (list (nth line 3))))

(defmethod find-sleep "falls" [res line]
  (conj (rest res)
        (conj (first res) (nth line 2))))

(defmethod find-sleep "wakes" [res line]
  (if (< 1 (count (first res)))
    (let [f (first res)
          sleep (range (- (nth line 2) 1) (first f) -1)]
      (conj (rest res)
            (concat sleep f)))
    res))

(defn sleep-count [res guard-sleep]
  (let [guard (last guard-sleep)
        sleep (- (count guard-sleep) 1)]
    (assoc res guard (+ (get res guard 0) sleep))))

(defn guard-sleepiest-minute [guard-sleeps guard]
  (->> (filter (fn [s] (= guard (last s))) guard-sleeps)
       (map (fn [s] (drop-last s)))
       (flatten)
       (group-by (fn [x] x))
       (into {} (map (fn [[k v]] [k (count v)])))
       (sort-by val >)
       (first)))


(let [lines (get-lines)]
  (let [guard-sleeps (reduce find-sleep () (get-lines))
        sleeps (reduce sleep-count {} guard-sleeps)
        guards (keys sleeps)
        sleepiest-guard (first (first (sort-by val > sleeps)))]
    (println "Sleepiest guard: " sleepiest-guard)
    (println "Most asleep during minute: " (first (guard-sleepiest-minute guard-sleeps sleepiest-guard)))
    (println
      (map vector
           guards
           (map (fn [x] (guard-sleepiest-minute guard-sleeps x)) guards)))))

