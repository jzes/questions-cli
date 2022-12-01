(ns questions-cli.controller.config
  (:require [questions-cli.diplomat.config :as dipl-config]))

(defn set-url
  [{ [_, url] :args}]
   (if (nil? url)
     {:success false
      :message "URL cant't be nil"}
     (dipl-config/set-url url)))

