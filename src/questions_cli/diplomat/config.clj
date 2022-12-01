(ns questions-cli.diplomat.config)

(def config-file-path "/.quest.edn")

(defn set-url
  [url]
  (if (nil? url)
    {:success false
     :message "URL cant't be nil"}
    (spit (str (System/getenv "HOME") config-file-path) (pr-str {:server url}))))

(defn read-config
  []
  (let [config-file (slurp (str (System/getenv "HOME") config-file-path))]
    (read-string config-file)))

(defn write-config
  [config] 
  (spit (str (System/getenv "HOME") config-file-path) (pr-str config)))