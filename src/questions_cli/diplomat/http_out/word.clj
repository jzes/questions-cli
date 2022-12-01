(ns questions-cli.diplomat.http-out.word  
  (:require [clj-http.client :as client]))

(defn get-current-word
  [{url :url
    room-id :x-room-id}]
  (client/get url {:headers {"X-ROOM-ID" (str room-id)}}))
