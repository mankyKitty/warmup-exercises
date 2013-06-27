(ns scrabble.core)

(def letter-scores [{:score 1 :letters "aeioulnrst"}
                    {:score 2 :letters "dg"}
                    {:score 3 :letters "bcmp"}
                    {:score 4 :letters "fhvwy"}
                    {:score 5 :letters "k"}
                    {:score 8 :letters "jx"}
                    {:score 10 :letters "qz"}])

(def modifier-example-one {:double "ac"
                           :triple "i"})

(def modifier-example-two {:double-word})

(defn letter-in? [letter coll]
  ;; Determine this collection has our letter.
  (some #(= letter %) (:letters coll)))

(defn get-letter-score [letter]
  ;; Return the score of the first collection that contains our letter.
  (:score (first (filter #(letter-in? letter %) letter-scores))))

(defn get-word-score [word]
  ;; Lower case the given word and give a score for it.
  (if-not (nil? word)
    (apply + (map get-letter-score (clojure.string/lower-case word)))
    ;; If we're not given anything, avoid null pointer and give 0.
    0))

(defn apply-modifiers [w mods]
  (apply + (filter #(not (nil? %)) (conj ()
                                         ;; Double word score just adds the letters.
                                         (get-word-score (:double mods))
                                         ;; Triple word score adds double the letters.
                                         (* 2 (get-word-score (:triple mods)))
                                         ;; Double word score adds the value of the word.
                                         (when (:double-word mods)
                                           (get-word-score w))
                                         ;; Triple word score adds double value of the word.
                                         (when (:triple-word mods)
                                           (* 2 (get-word-score w)))))))

(defn scrabble-word
  ;; Just get a basic word score
  ([w] (get-word-score w))
  ;; If we have any modifiers apply them to the base score
  ([w mods] (+ (get-word-score w) (apply-modifiers w mods))))

(get-word-score "Clojure")
(get-word-score "Fish")
