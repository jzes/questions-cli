(ns questions-cli.diplomat.cmd
  (:require [pretty.cli.prompt :as p]
            [clojure.string :as str] 
            [questions-cli.adapter.tags :as tags]
            [pretty.cli.colors :as c]))

(defn chosen-one
  [options]
  (p/list-select "chose the room" (into [] options)))

(defn ask-title
  []
  (p/input (str "Send a title with # to replace current word.") (fn 
                                                                    [title] 
                                                                    (if (empty? title)
                                                                      "you must send a title"
                                                                      (when (not (str/includes? title "#"))
                                                                        "must have \"#\" in title")))))

(defn show-result
  [result] 
  (if (nil? result)
    (println (str (c/cyan "get result.: ") (tags/fail)))
    (p/list-checkbox "chose the bests" result)))
