(ns bob.core
  (:require [clojure.string :as slib]
            [clojure.zip :as zlib]
            [clojure.java.io :as io])
  (:gen-class :main true))

(defn consonant-in? [s]
  (not (empty? (re-seq #"[^aeiouAEIOU\s]" (str s)))))

(defn leet-vowel [v]
  (let [c (slib/lower-case v)]
    (cond
     (= "a" c) 4
     (= "e" c) 3
     (= "i" c) 1
     (= "o" c) 0
     :else v)))

(defn is-lower-case? [c]
  (= (slib/lower-case c) c))

(defn switch-letter-case [c]
  (if (is-lower-case? c)
    (slib/upper-case c)
    (slib/lower-case c)))

(defn first-letter-rule [c]
  (if (consonant-in? c)
    (slib/lower-case c)
    c))

(defn flip-direction [l]
  (if (is-lower-case? l)
    2
    1))

(defn rest-of-word [dir w head n l]
  (if (even? (+ dir n))
    (slib/upper-case l)
    (slib/lower-case l)))

(defn flip-case [w]
  (let [head (first-letter-rule (first w))
        rest (rest w)]
    (apply str
           " "  ;; Space between the word
           head ;; First Letter of this word
           (map #(rest-of-word (flip-direction head) rest head %1 %2)
                (range 0 (count rest))
                rest))))

(defn leet-words [s]
  (apply str (map flip-case (lazy-seq (slib/split (apply str (map leet-letter s)) #" ")))))

(defn leet-letter [l]
  (if-not (consonant-in? l)
    (leet-vowel l)
    l))

(defn -main [& args]
  (if args
    (println (leet-words args))
    (println "Usage: <string to leetify>")))