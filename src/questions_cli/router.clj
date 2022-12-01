(ns questions-cli.router
  (:require [questions-cli.controller.not-found :as ctrl-not-found]
            [questions-cli.controller.room :as ctrl-room]
            [questions-cli.controller.config :as ctrl-config]
            [questions-cli.diplomat.config :as dipl-config]
            [questions-cli.controller.word :as ctrl-word]
            [questions-cli.controller.title :as ctrl-title]
            [questions-cli.controller.result :as ctrl-result]
            [pretty.cli.colors :as c]))

(def config (dipl-config/read-config))

(def commands
  {:set-server ctrl-config/set-url
   :make-room ctrl-room/make-room 
   :enter-room  ctrl-room/enter-room
   :get-word ctrl-word/get-current-word
   :new-word ctrl-word/new-word
   :send-title ctrl-title/send-title
   :check-result ctrl-result/check-result})

(defn route
  [args]
  (let [cmd-function (->> args
                          first
                          keyword
                          (get commands))]
    (if (= nil cmd-function)
      (ctrl-not-found/command-notfound args)
      (let [input {:args args
                   :config config}
            
            result (cmd-function input)] 
        
        (when (not (nil? result))
          (-> result
              :message
              c/red-bg
              println))))))

