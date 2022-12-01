(ns questions-cli.controller.result
  (:require [questions-cli.adapter.result :as adpt-result]
            [questions-cli.diplomat.http-out.result :as dipl-result]
            [questions-cli.diplomat.cmd :as dipl-cmd]))

(defn check-result
  [{config :config}]
  (->> config
       adpt-result/config->check-result-input
       dipl-result/get-result
       adpt-result/result->cmd-output
       dipl-cmd/show-result))