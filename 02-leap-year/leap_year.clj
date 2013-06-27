(ns leap_year.core)

(defn foo [y]
  (cond
   (zero? (mod y 400)) true
   (zero? (mod y 100)) false
   (zero? (mod y 4)) true
   :else false))

(foo 1988)
