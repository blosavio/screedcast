(ns screedcast.core
  "Generate screencast panels."
  (:require
   [clojure.pprint :as pp]
   [clojure.string :as str]
   [clojure.test.check.generators :as gen]
   [zprint.core :as zp]
   [hiccup2.core :as h2]
   [hiccup.page :as page]
   [hiccup.element :as element]
   [hiccup.form :as form]
   [hiccup.util :as util]
   [readmoi.core :refer :all]))


(defn page-ize
  "Given hiccup/html form `body`, insert a page number into the panel-footer of
  each panel."
  {:UUIDv4 #uuid "c30b6a0b-3092-4e84-a7a5-7fd07ab248b1"}
  [body opt]
  (let [total (dec (count body))]
    (concat
     [(first body)]
     (vec (map-indexed #(vec (conj (vec (butlast %2))
                                   (conj (last %2)
                                         [:span.panel-number (str (inc %1) "/" total)]
                                         [:span.footer-link (opt :project-webpage-link)])))
                       (vec (rest body)))))))


(defn screencast-template
  "Generate a screencast with header title `t`, hiccup/html dialect `body`,
  UUIDv4 `uuid`, and options map `opt`."
  {:UUIDv4 #uuid "9eac9c34-c44c-4921-97f3-4418b37e15c9"}
  [title uuid body opt]
  (page/html5
   {:lang "en"}
   [:head
    (page/include-css "screedcast.css")
    (page/include-js "jquery-3.7.1.min.js")
    (page/include-js "screedcast.js")
    [:title title]
    [:meta {"charset"  "utf-8"
            "name" "viewport"
            "content" "width=device-width, initial-scale=1"
            "compile-date" (long-date)}]
    (conj
     (vec (page-ize body opt))
     [:p#page-footer
      (copyright (opt :copyright-holder))
      [:br]
      (str "Compiled " (short-date) ".")
      (opt :project-license-section)
      [:span#uuid [:br] uuid]])]))


(defn prettyfy-form-prettyfy-eval
  "Returns a hiccup [:pre [:code]] block wrapping a Clojure stringified form
  str-form, then a [:pre [:code]] block wrapping a separator sep
  (default ' => '), and evaluated value.

  `def`, `defn`, `s/def/`, `defmacro`, `defpred`, and `require` expressions are
  only evaled; their output is not captured.

  Note: Evaluated output can not contain an anonymous function of either
  (fn [x] ...) nor #(...) because zprint requires an internal reference
  to attempt a backtrack. Since the rendering of an anonymous function
  changes from one invocation to the next, there is no stable reference."
  {:UUIDv4 #uuid "0d6c7ba9-a9a5-4980-b449-ea1b27230d47"}
  ([str-form] (prettyfy-form-prettyfy-eval str-form " => " 80 40))
  ([str-form separator] (prettyfy-form-prettyfy-eval str-form separator 80 40))
  ([str-form width-fn width-output] (prettyfy-form-prettyfy-eval str-form " => " width-fn width-output))
  ([str-form separator width-fn width-output]
   (let [def? (re-find #"^\((s\/)?defn?(macro)?(pred)? " str-form)
         require? (re-find #"^\(require " str-form)
         form (read-string str-form)
         evaled-form (eval form)
         evaled-str (revert-fn-obj-rendering (pr-str evaled-form))]
     (if (or def? require?)
       [:pre [:code (prettyfy str-form)]]
       [:pre
        [:code.form (prettyfy str-form width-fn)]
        [:br]
        [:code.eval (comment-newlines (prettyfy evaled-str width-output)
                                      separator
                                      ";;")]]))))


(defn panel
  "Generate a screencast panel, with zero or more hiccup forms."
  {:UUIDv4 #uuid "1ba78b65-4568-4517-9d98-5b21fc39e0f8"}
  [& hiccups]
  (conj
   (into [:div.panel
          [:div.panel-header]]
         hiccups)
   [:div.panel-footer]))


(def ^{:no-doc true} project-name (atom "default project-name"))


(defn screencast-title
  "Construct a screencast title element from index `idx` and topic
  `screencast-title`."
  {:UUIDv4 #uuid "ba991108-a524-496b-88c6-851587363b20"}
  [idx screencast-title]
  [:h1 (str @project-name " Screencast " (inc idx) " — " screencast-title)])


(def ^{:no-doc true} screencast-topics (atom ["foo" "bar" "baz"]))


(defn whats-next-panel
  "Generate a `what's next` panel, with the next `idx` screencast highlighted,
  optional presentation `notes` to be appended."
  {:UUIDv4 #uuid "6ab9ae17-8942-4bc4-ae4e-7c520f243929"}
  [idx & notes]
  (panel
   [:h3 "What's next."]
   (reduce-kv (fn [v i val] (conj v [(if (= i (inc idx)) :li.highlight :li) val])) [:ol.de-highlight.whats-next] @screencast-topics)
   notes))


(defn generate-screencast
  "Given file-name base entry `fnbe` and screedcast options map `opt`, generate
  an html screencast page."
  {:UUIDv4 #uuid "b07a9fbd-0ad1-4ae3-96a9-01937ab053e6"}
  [fnbe opt]
  (let [title (str (opt :project-name-formatted) " — " (opt :project-description))
        screencast-body (screencast-template
                         title
                         (:screencast-uuid fnbe)
                         (load-file (str (opt :sections-directory) (:screencast-filename fnbe) ".clj"))
                         opt)
        screencast-body-fn-reverted (revert-fn-obj-rendering screencast-body)
        title-replaced (clojure.string/replace screencast-body-fn-reverted #":::title replacement target:::" (opt :project-name-formatted))]
    (spit (str (opt :screencast-html-directory) (:screencast-filename fnbe) ".html") screencast-body-fn-reverted)))


(load "screedcast_defaults")


(defn generate-all-screencasts
  "Given an options map `opt`, generate a screencast html doc for all filenames."
  {:UUIDv4 #uuid "d856e482-bf0e-4ad6-8056-095f49c84f42"}
  [opt]
  (let [options-n-defaults (merge screedcast-defaults opt)
        _ (reset! screencast-topics (mapv #(:screencast-title %) (opt :screencast-filename-bases)))
        _ (reset! project-name (opt :project-name-formatted))]
    (binding [readmoi.core/*wrap-at* (options-n-defaults :wrap-at)
              readmoi.core/*separator* (options-n-defaults :separator)]
      (map #(generate-screencast % options-n-defaults) (opt :screencast-filename-bases)))))