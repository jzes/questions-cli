(ns questions-cli.diplomat.http-out.room
  (:require [clj-http.client :as client]))


(defn make
  [url]
  (client/post url))

(defn list-rooms
  [{url :list-url}]
  (client/get url))

(defn enter-room
  [{url :enter-url user-id :header-user-id}]
  (client/post url {:headers {"X-PLAYER-ID" (str user-id)}}))