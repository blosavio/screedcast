(defproject com.sagevisuals/screedcast "0-SNAPSHOT1"
  :description "A highly un-configurable library for generating coding screencasts."
  :url "https://github.com/blosavio/screedcast"
  :license {:name "MIT License"
            :url "https://opensource.org/license/mit"
            :distribution :repo}
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [com.sagevisuals/readmoi "2-SNAPSHOT1"]]
  :repl-options {:init-ns screedcast.core}
  :plugins []
  :profiles {:dev {:dependencies [[org.clojure/test.check "1.1.1"]
                                  [hiccup "2.0.0-RC3"]
                                  [zprint "1.2.9"]
                                  [com.sagevisuals/chlog "0-SNAPSHOT2"]]
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
          :project {:name "Screedcast" :version "version 0-SNAPSHOT1"}}
  :scm {:name "git" :url "https://github.com/blosavio/screedcast"})
