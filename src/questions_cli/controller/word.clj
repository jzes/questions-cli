(ns questions-cli.controller.word
  (:require [questions-cli.adapter.word :as adpt-word]
            [questions-cli.diplomat.http-out.word :as dipl-word]))

(defn get-current-word
  [{config :config}]
  (-> config
      adpt-word/config->http-out
      dipl-word/get-current-word
      adpt-word/http-response->word
      println)) ;; criar um adapter pra tratar o resultado de quando não houver palavra
                ;; criar um comando para sortiar uma palavra na sala

(defn new-word
  [{config :config}]
  (-> config
      adpt-word/config->http-out-new-word
      dipl-word/get-current-word
      adpt-word/http-response->new-word
      println))