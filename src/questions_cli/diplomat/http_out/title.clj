(ns questions-cli.diplomat.http-out.title
  (:require [clj-http.client :as client]))

(defn send-title
  [{:keys [url headers body]}]
  (client/post url {:headers headers :body body}))