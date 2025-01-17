(ns koans.14-recursion
  (:require [koan-engine.core :refer :all]))

(defn is-even? [n]
  (if (= n 0)
    true
    (not (is-even? (dec n)))))

(defn is-even-bigint? [n]
  (loop [n   n
         acc true]
    (if (> n 0)
      (recur (dec n) (not acc))
      acc)))

(defn recursive-reverse [coll]
  (loop [coll1 (seq coll)
         rev ()]
    (if (not= [] coll1)
      (recur (rest coll1) (cons (first coll1) rev))
      rev)))

(defn factorial [n]
  (if (= n 1) 1
      (* n (factorial (- n 1)))))

(defn factorial1 [n]
  (loop [n1 n
         res 1]
    (if (> n1 1)
      (recur (dec n1) (* res n1))
      res)))

;; (println "result:")
;; (println (recursive-reverse [1]))

(meditations
 "Recursion ends with a base case"
 (= true (is-even? 0))

 "And starts by moving toward that base case"
 (= false (is-even? 1))

 "Having too many stack frames requires explicit tail calls with recur"
 (= false (is-even-bigint? 100003N))

 "Reversing directions is easy when you have not gone far"
 (= '(1) (recursive-reverse [1]))

 "Yet it becomes more difficult the more steps you take"
 (= '(6 5 4 3 2) (recursive-reverse [2 3 4 5 6]))

 "Simple things may appear simple"
 (= 1 (factorial 1))

 "They may require other simple steps"
 (= 2 (factorial 2))

 "Sometimes a slightly bigger step is necessary"
 (= 6 (factorial 3))

 "And eventually you must think harder"
 (= 24 (factorial 4))

 "Simple things may appear simple"
 (= 1 (factorial1 1))

 "They may require other simple steps"
 (= 2 (factorial1 2))

 "Sometimes a slightly bigger step is necessary"
 (= 6 (factorial1 3))

 "And eventually you must think harder"
 (= 24 (factorial1 4))
 "You can even deal with very large numbers"
 (< 1000000000000000000000000N (factorial 1000N))

 "But what happens when the machine limits you?"
 (< 1000000000000000000000000N (factorial1 100003N)))
