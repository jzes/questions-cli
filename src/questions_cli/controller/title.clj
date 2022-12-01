(ns questions-cli.controller.title
  (:require [questions-cli.adapter.title :as adpt-title]
            [questions-cli.diplomat.cmd :as dipl-cmd]
            [questions-cli.diplomat.http-out.title :as dipl-title]))

(defn send-title
  [{config :config}]
  (let [title (dipl-cmd/ask-title)
        http-input (adpt-title/config->title config title)]
    (->> http-input
         dipl-title/send-title
         adpt-title/http-response->cmd-output
         println)))