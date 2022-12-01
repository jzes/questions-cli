(ns questions-cli.core
  (:require [questions-cli.router :as router])
  (:gen-class))




(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (router/route args))