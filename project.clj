(defproject advent-of-code "2018.0-SNAPSHOT"
  :description "Advent of code 2018 solutions."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :source-paths ["src/clj"]
  :profiles {:dev {:plugins [[lein-midje "3.2.1"]]
                   :dependencies [[midje "1.9.3"]]}
            })
