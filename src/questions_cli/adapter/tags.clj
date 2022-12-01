(ns questions-cli.adapter.tags
  (:require [pretty.cli.colors :as c]))

(defn warning
  ([]
   (str (c/yellow "") (c/yellow-bg "warning") (c/yellow "")))
  ([msg]
   (str (c/yellow "") (c/yellow-bg msg) (c/yellow ""))))

(defn fail
  ([]
   (str (c/red "") (c/red-bg "fail") (c/red "")))
  ([msg]
   (str (c/red "") (c/red-bg msg) (c/red ""))))

(defn success
  ([]
   (str (c/green "") (c/green-bg "success") (c/green "")))
  ([msg]
   (str (c/green "") (c/green-bg msg) (c/green ""))))