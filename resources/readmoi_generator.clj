(ns readmoi-generator
  "Script to load options and perform actions.

  CIDER eval buffer C-c C-k generates an html page and a markdown chunk."
  {:no-doc true}
  (:require
   [hiccup2.core :refer [raw]]
   [readmoi.core :refer [*project-group*
                         *project-name*
                         *project-version*
                         generate-all
                         prettyfy
                         print-form-then-eval]]))


(def project-metadata (read-string (slurp "project.clj")))
(def readmoi-options (load-file "resources/readmoi_options.edn"))


(generate-all project-metadata readmoi-options)


(defn -main
  [& args]
  {:UUIDv4 #uuid "27ae0185-41a1-49d1-a7af-930a8d718c90"}
  (println "generated Screedcast ReadMe docs"))