(ns questions-cli.diplomat.http-out.result
  (:require [clj-http.client :as client])) 

(defn get-result
  [{:keys [url headers]}]
  (client/get url {:headers headers}))