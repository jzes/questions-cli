(ns questions-cli.controller.not-found
  (:require [pretty.cli.colors :as c] 
            [questions-cli.adapter.tags :as tags]))

(defn command-notfound
  [[args]]
  (let [msg (str (c/cyan (str "╭─" args ".: ")) (tags/fail) (c/cyan "\n╰─❯ message.: ") (c/txt-bold (c/red "command not found")))]
    (println msg)))