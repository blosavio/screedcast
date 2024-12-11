(defproject com.sagevisuals/screedcast "2-SNAPSHOT1"
  :description "A highly un-configurable library for generating Clojure screencasts."
  :url "https://github.com/blosavio/screedcast"
  :license {:name "MIT License"
            :url "https://opensource.org/license/mit"
            :distribution :repo}
  :dependencies [[org.clojure/clojure "1.12.0"]]
  :repl-options {:init-ns screedcast.core}
  :plugins []
  :profiles {:dev {:dependencies [[org.clojure/test.check "1.1.1"]
                                  [hiccup "2.0.0-RC3"]
                                  [zprint "1.2.9"]
                                  [com.sagevisuals/chlog "1"]
                                  [com.sagevisuals/readmoi "3"]]
                   :plugins [[dev.weavejester/lein-cljfmt "0.12.0"]
                             [lein-codox "0.10.8"]]}
             :repl {}}
  :codox {:metadata {:doc/format :markdown}
          :namespaces [#"^screedcast\.(?!scratch)"]
          :target-path "doc"
          :output-path "doc"
          :doc-files []
          :source-uri "https://github.com/blosavio/screedcast/blob/main/{filepath}#L{line}"
          :html {:transforms [[:div.sidebar.primary] [:append [:ul.index-link [:li.depth-1 [:a {:href "https://github.com/blosavio/screedcast"} "Project home"]]]]]}
          :project {:name "Screedcast" :version "version 2"}}
  :scm {:name "git" :url "https://github.com/blosavio/screedcast"})
