(ns questions-cli.adapter.title
  (:require [clojure.data.json :as json]
            [pretty.cli.colors :as c]
[questions-cli.adapter.tags :as tags]))

(defn config->title
  [{server :server
    chosen-room :chosen-room
    user-id :user-id}
   title]
  {:url (str server "/v1/game")
   :headers {"X-ROOM-ID" chosen-room
             "X-PLAYER-ID" user-id}
   :body (json/write-str {:text title})})

(defn http-response->cmd-output
  [{raw-body :body}]
  (let [body (-> raw-body
                 (json/read-str :key-fn keyword))]

    (if (:success body)
      (str (c/cyan "╭─send title.: ") (tags/success) (c/cyan "\n╰─❯ sended title.: ") (c/blue (->> body :data :Game)) )
      (str (c/cyan "send title.: ") (tags/fail)))))
