(ns questions-cli.adapter.result
  (:require [clojure.data.json :as json]))

(defn config->check-result-input
  [{:keys [server user-id chosen-room]}]
  {:url (str server "/v1/game/messages")
   :headers {"X-ROOM-ID" chosen-room
             "X-PLAYER-ID" user-id}})

(defn result->cmd-output
  [{raw-body :body}]
  (let [body (-> raw-body
                 (json/read-str :key-fn keyword))] 
    (->> body
         :data
         :Game
         (map (fn [res] (:text res))))
    ))