(ns questions-cli.adapter.word
  (:require [clojure.data.json :as json]
            [pretty.cli.colors :as c]
            [questions-cli.adapter.tags :as tags]))

(defn config->http-out
  [{server :server
    chosen-room :chosen-room}]
  {:url (str server "v1/word")
   :x-room-id chosen-room})

(defn config->http-out-new-word
  [{server :server
    chosen-room :chosen-room}]
  {:url (str server "v1/word/new")
   :x-room-id chosen-room})

(defn http-response->word
  [{raw-body :body}]
  (let [body (-> raw-body
                 (json/read-str :key-fn keyword))]
    (if (:success body)
      (let [word (->> body
                      :data
                      :Word
                      :word)]
        (if (empty? word)
          (str (c/cyan "╭─get current word.: ") (tags/warning) (c/cyan "\n╰─❯ message.: ") (c/blue "no word for this room"))
          (str (c/cyan "╭─get current word.: ") (tags/success) (c/cyan "\n╰─❯ word.: ") (c/txt-underscore (c/txt-bold (c/green (str "\" " word " \"")))))))

      (str (c/cyan "get current word.: ") (tags/fail)))))


(defn http-response->new-word
  [{raw-body :body}]
  (let [body (-> raw-body
                 (json/read-str :key-fn keyword))]
    (if (:success body)
      (let [word (->> body
                      :data
                      :Word
                      :word)]
        (if (empty? word)
          (str (c/cyan "╭─new word.: ") (tags/warning) (c/cyan "\n╰─❯ message.: ") (c/blue "no word to sort"))
          (str (c/cyan "╭─new word.: ") (tags/success) (c/cyan "\n╰─❯ new word for your room.: ") (c/txt-underscore (c/txt-bold (c/green word))))))

      (str (c/cyan "new word.: ") (tags/fail)))))