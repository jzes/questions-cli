(ns questions-cli.controller.room
  (:require [questions-cli.diplomat.http-out.room :as dipl-room]
            [questions-cli.adapter.room :as adpt-room]
            [questions-cli.diplomat.cmd :as dipl-cmd]
            [questions-cli.diplomat.config :as dipl-config]))


(defn make-room
  [{config :config}]
  (-> config
      adpt-room/input->make
      :url
      dipl-room/make
      adpt-room/make->output
      println))

(defn ^:private make-new-user-id
  [config]
  (let [new-user-id (quot (System/currentTimeMillis) 1000)
        new-config (assoc config :user-id new-user-id)]
    (dipl-config/write-config new-config)
    new-user-id))

(defn enter-room
  [{config :config}]
  (let [input (adpt-room/input->enter-room config)
        new-user-id (if (nil? (:user-id config))
                      (make-new-user-id config)
                      (:user-id config))
        chosen-room (-> input
                        dipl-room/list-rooms
                        adpt-room/list-room->chosen-room
                        dipl-cmd/chosen-one)]
    (-> config
        (adpt-room/chose-room->http-out new-user-id chosen-room)
        dipl-room/enter-room
        adpt-room/http-response->success?
        println)
    (dipl-config/write-config (assoc config :chosen-room chosen-room))))

