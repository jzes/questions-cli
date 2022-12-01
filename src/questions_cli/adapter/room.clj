(ns questions-cli.adapter.room
  (:require [clojure.data.json :as json] 
            [pretty.cli.colors :as c]
            [questions-cli.adapter.tags :as tags]))



(defn input->make
  [{server :server}]
  {:url (str server "v1/MakeRoom")})

(defn make->output
  [{raw-body :body}]
  (let [body (-> raw-body
                 (json/read-str :key-fn keyword))]

    (if (:success body)
      (str (c/cyan "╭─make room.: ") (tags/success) (c/cyan "\n╰─❯ room id.: ") (c/blue (-> body :data :Room)))
      (str (c/cyan "make room.: ") (tags/fail)))))

(defn input->enter-room
  [{server :server}] 
  {:list-url (str server "v1/rooms")})

(defn list-room->chosen-room
  [{raw-body :body}]
  (let [body (-> raw-body
                 (json/read-str :key-fn keyword))]
    (if (:success body)
      (->> body
          :data
          :Room
          (map (fn [r] (str (:room r)))))
      (str (c/cyan "messge.: ") (c/red-bg "error on list rooms created")))))

(defn chose-room->http-out
  "adapt data to enter room"
  [{server :server} user-id chosen-room]
  {:enter-url (str server "v1/rooms/" chosen-room)
   :header-user-id user-id})

(defn http-response->success?
  [{raw-body :body}]
  (let [body (-> raw-body
                 (json/read-str :key-fn keyword))]
    (if (:success body)
      (str (c/cyan "enter room.: ") (tags/success))
      (str (c/cyan "enter room.: ") (tags/fail)))))