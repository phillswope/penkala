(ns com.verybigthings.penkala.util.core
  (:require [clojure.string :as str]))

(def joins
  {:left "LEFT OUTER JOIN"
   :left-lateral "LEFT JOIN LATERAL"
   :right "RIGHT OUTER JOIN"
   :right-lateral "RIGHT JOIN LATERAL"
   :inner "INNER JOIN"
   :full "FULL OUTER JOIN"
   :cross "CROSS JOIN"})

(def join-separator "__")
(def join-separator-re #"__")

(defn path-prefix-join [path]
  (str/join join-separator path))

(defn path-prefix-split [str]
  (str/split str join-separator-re))

(defn str-quote [s]
  (str "\"" (if (keyword? s) (name s) s) "\""))

(def q str-quote)

(defn select-keys-with-default [m ks default]
  (reduce
    (fn [acc k]
      (assoc acc k (get m k default)))
    {}
    ks))

(defn as-vec [val]
  (if (sequential? val) (vec val) [val]))

(defn expand-join-path [path]
  (mapcat (fn [v] [:joins (keyword v) :relation]) path))

(def vec-conj (fnil conj []))