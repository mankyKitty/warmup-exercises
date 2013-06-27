(ns seanc.core)
;; 99 Bottles of Beer Solution !
;; Loop 100 -> 1 and sing the song to zero?

(def starting-value 99)

(defn beer-w
  ([n]
   (str n " bottles of beer"))
  ([n include-wall]
   (str (beer-w n) " on the wall")))

(defn pass-verse [n]
  (str "Take one down pass it around, " (beer-w n true)))

;; Take first value
;; Print wall-verse
;; decrement value
;; Print pass-verse
;; When we're done, print the last verse and exit

(defn drink-beer [n]
  (doseq [bottle (reverse (range 0 n))]
    (if (zero? bottle)
      (println (str (beer-w "No more" true)
                    ", "
                    (beer-w "no more")
                    ".\n"
                    "Go to the store and buy some more, "
                    (beer-w n true)))
      (println (str (beer-w bottle true)
                    ", "
                    (beer-w bottle)
                    ".\n"
                    (if (= (- bottle 1) 0)
                      (pass-verse "no more")
                      (pass-verse (- bottle 1))))))))
