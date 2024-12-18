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
  {:UUIDv4 #uuid "c30b6a0b-3092-4e84-a7a5-7fd07ab248b1"
   :no-doc true}
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
  "Creates a screencast with header title `t`, hiccup/html dialect `body`,
  UUIDv4 `uuid`, and options map `opt`."
  {:UUIDv4 #uuid "9eac9c34-c44c-4921-97f3-4418b37e15c9"
   :no-doc true}
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
      "Compiled by " [:a {:href "https://github.com/blosavio/screedcast"} "Screedcast"] " on " (short-date) "."
      (opt :project-license-section)
      [:span#uuid [:br] uuid]])]))


(defn prettyfy-form-prettyfy-eval
  "Returns a hiccup `[:pre [:code]]` block wrapping a Clojure stringified form
  `str-form`, then a `[:pre [:code]]` block wrapping a separator `sep`
  (default `' => '`), and evaluated value.

  `def`, `defn`, `s/def/`, `defmacro`, `defpred`, and `require` expressions are
  only evaled; their output is not captured.

  Example:
  ```clojure
  (prettyfy-form-prettyfy-eval \"(inc 99)\")
  ```
  
  produces this hiccup/html

  ```clojure
  [:pre [:code.form \"(inc 99)\"] [:br] [:code.eval \";; => 100\"]]
  ```

  The compiled html elements are manipulated by javascript.

  Re-bind [`readmoi.core/*wrap-at*`](https://blosavio.github.io/readmoi/readmoi.core.html#var-*wrap-at*)
  to change base-case column-wrap width. The two optional width args, `width-fn`
  and `width-output`, supersede this value.

  Re-bind [`readmoi.core/*separator*`](https://blosavio.github.io/readmoi/readmoi.core.html#var-*separator*)
  to change the evaluation arrow.

  Note: Evaluated output can not contain an anonymous function of either
  `(fn [x] ...)` nor `#(...)` because zprint requires an internal reference
  to attempt a backtrack. Since the rendering of an anonymous function
  changes from one invocation to the next, there is no stable reference."
  {:UUIDv4 #uuid "0d6c7ba9-a9a5-4980-b449-ea1b27230d47"}
  ([str-form] (prettyfy-form-prettyfy-eval str-form *wrap-at* (/ *wrap-at* 2)))
  ([str-form width-fn width-output]
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
                                      *separator*
                                      ";;")]]))))


(defn panel
  "Returns a screencast panel, with zero or more hiccup forms, including a
  header and a footer."
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
  "Returns a 'What's next' panel, with the next `idx` screencast highlighted,
  optional presentation `notes` to be appended."
  {:UUIDv4 #uuid "6ab9ae17-8942-4bc4-ae4e-7c520f243929"}
  [idx & notes]
  (panel
   [:h3 "What's next."]
   (reduce-kv (fn [v i val] (conj v [(if (= i (inc idx)) :li.highlight :li) val])) [:ol.de-highlight.whats-next] @screencast-topics)
   notes))


(defn generate-toc
  "Given an _options_ map `opt`, generate a _Table of Contents_ listing of
  screencasts hyperlinks. Hyperlink slugs are composed from the screencast
  filenames.

  Within `opt`,

  * `:toc-preamble` is hiccup/html displayed above the listing
  * `:toc-uuid` is a UUID for the TOC webpage
  * `:toc-panel-url-base` is the leading portion of the url up to the slug
  (include a trailing slash '/'); address to the panels
  * `:toc-video-url-base` is the leading portion of the url up to the slug
  (include a trailing slash '/'); address to the video"
  {:UUIDv4 #uuid "74f4e9c1-334b-4d8d-ba96-142f1f97f3b0"}
  [opt]
  (let [toc-body (page-template
                  (str (opt :project-name-formatted) " — " (opt :project-description))
                  #uuid "b41578e4-d1cb-4ea6-9cd3-1ca68f8af62d"
                  [:body
                   [:h1 (opt :project-name-formatted) " Screencast Table of Contents"]
                   (opt :toc-preamble)
                   [:ol (map #(vector :li
                                      [:a {:href (str (opt :toc-video-url-base) (% :video-slug))} (% :screencast-title)]
                                      " ("
                                      [:a {:href (str (opt :toc-panel-url-base) (% :screencast-filename) ".html")} "slides"]
                                      ")")
                             (opt :screencast-filename-bases))]]
                  (opt :copyright-holder)
                  [:a {:href "https://github.com/blosavio/screedcast"} "Screedcast"])
        toc-filename (str (opt :screencast-html-directory) "table_of_contents.html")]
    (do
      (spit toc-filename toc-body)
      (if (opt :tidy-html?) (tidy-html-document toc-filename)))))


(defn generate-screencast
  "Given file-name base entry `fnbe` and screedcast options map `opt`, generate
  an html screencast page. `fnbe` is a single element of an _options_ hash-map
  containing `:screencast-filename`, `:screencast-title`, and
  `:screencast-uuid`."
  {:UUIDv4 #uuid "b07a9fbd-0ad1-4ae3-96a9-01937ab053e6"}
  [fnbe opt]
  (let [title (str (opt :project-name-formatted) " — " (opt :project-description))
        screencast-body (screencast-template
                         title
                         (:screencast-uuid fnbe)
                         (load-file (str (opt :sections-directory) (:screencast-filename fnbe) ".clj"))
                         opt)
        screencast-body-fn-reverted (revert-fn-obj-rendering screencast-body)
        title-replaced (clojure.string/replace screencast-body-fn-reverted #":::title replacement target:::" (opt :project-name-formatted))
        filepath-name (str (opt :screencast-html-directory) (:screencast-filename fnbe) ".html")]
    (do
      (spit filepath-name screencast-body-fn-reverted)
      (if (opt :tidy-html?) (tidy-html-document filepath-name))
      (if (opt :toc?) (generate-toc opt)))))


(load "screedcast_defaults")


(defn generate-all-screencasts
  "Given an _options_ map `opt`, generate a screencast html doc for all maps
  contained by the vector associated to `:screencast-filename-bases`.

  See [project documentation](https://github.com/blosavio/screedcast) for details
  of the options map."
  {:UUIDv4 #uuid "d856e482-bf0e-4ad6-8056-095f49c84f42"}
  [opt]
  (let [options-n-defaults (merge screedcast-defaults opt)]
    (binding [*wrap-at* (options-n-defaults :wrap-at)
              *separator* (options-n-defaults :separator)]
      (reset! screencast-topics (mapv #(:screencast-title %) (opt :screencast-filename-bases)))
      (reset! project-name (opt :project-name-formatted))
      (map #(generate-screencast % options-n-defaults) (opt :screencast-filename-bases)))))