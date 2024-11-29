(ns screedcast-generator
  "CIDER eval buffer C-c C-k generates a screencast page and a
  screencast+comments page."
  {:no-doc true}
  (:require
   [hiccup2.core :as h2]
   [screedcast.core :refer :all]))


(def screedcast-options (load-file "resources/screedcast_options.edn"))


(generate-all-screencasts screedcast-options)