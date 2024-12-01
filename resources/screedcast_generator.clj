(ns screedcast-generator
  "CIDER eval buffer C-c C-k generates a screencast page and a
  screencast+comments page."
  {:no-doc true}
  (:require
   [hiccup2.core :as h2]
   [screedcast.core :refer :all]))


(def screedcast-options (load-file "resources/screedcast_options.edn"))


(generate-all-screencasts screedcast-options)


#_(defn -main
    [& args]
    {:UUIDv4 #uuid "e3df00e2-0efd-459f-8407-53cd03d40856"}
    (println "generated Screedcast screedcasts"))