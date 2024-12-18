(ns screedcast.core-tests
  (:require [clojure.test :refer [deftest is are run-tests testing]]
            [readmoi.core :refer [*separator*]]
            [screedcast.core :refer :all]))


(deftest page-ize-tests
  (are [x y] (= x y)
    (page-ize [:body] {:project-webpage-link "http://example.com"})
    [:body]
    
    (page-ize [:body
               (panel [:p "foo"])
               (panel [:p "bar"])
               (panel [:p "baz"])]
              {:project-webpage-link "https://example.com"})
    [:body
     [:div.panel
      [:div.panel-header]
      [:p "foo"]
      [:div.panel-footer [:span.panel-number "1/3"]
       [:span.footer-link "https://example.com"]]]
     [:div.panel
      [:div.panel-header]
      [:p "bar"]
      [:div.panel-footer [:span.panel-number "2/3"]
       [:span.footer-link "https://example.com"]]]
     [:div.panel
      [:div.panel-header]
      [:p "baz"]
      [:div.panel-footer [:span.panel-number "3/3"]
       [:span.footer-link "https://example.com"]]]]))


(def screencast-regex #"<\!DOCTYPE html>\n<html lang=\"en\"><head><link href=\"screedcast.css\" rel=\"stylesheet\" type=\"text/css\"><script src=\"jquery-3.7.1.min.js\" type=\"text/javascript\"></script><script src=\"screedcast.js\" type=\"text/javascript\"></script><title>Test title</title><meta charset=\"utf-8\" compile-date=\"\d{4}-\d{1,2}-\d{1,2} \d{1,2}:\d{1,2}:\d{1,2}\" content=\"width=device-width, initial-scale=1\" name=\"viewport\"><body><div class=\"panel\"><div class=\"panel-header\"></div><p>foo</p><div class=\"panel-footer\"><span class=\"panel-number\">1/3</span><span class=\"footer-link\"></span></div></div><div class=\"panel\"><div class=\"panel-header\"></div><p>bar</p><div class=\"panel-footer\"><span class=\"panel-number\">2/3</span><span class=\"footer-link\"></span></div></div><div class=\"panel\"><div class=\"panel-header\"></div><p>baz</p><div class=\"panel-footer\"><span class=\"panel-number\">3/3</span><span class=\"footer-link\"></span></div></div><p id=\"page-footer\">Copyright © \d{4} test copyright holder.<br>Compiled by <a href=\"https://github.com/blosavio/screedcast\">Screedcast</a> on \d{4} \w{3,9} \d{1,2}.<span id=\"uuid\"><br>Test UUID</span></p></body></head></html>")


(deftest screencast-template-tests
  (are [x] ((complement nil?) x)
    (re-find screencast-regex (screencast-template "Test title"
                                                   "Test UUID"
                                                   [:body
                                                    (panel [:p "foo"])
                                                    (panel [:p "bar"])
                                                    (panel [:p "baz"])]
                                                   {:copyright-holder "test copyright holder"}))))


(deftest prettyfy-form-prettyfy-eval-tests
  (are [x y] (= x y)
    (prettyfy-form-prettyfy-eval "()")
    [:pre [:code.form "()"] [:br] [:code.eval ";; => ()"]]
    
    (prettyfy-form-prettyfy-eval "(inc 99)")
    [:pre [:code.form "(inc 99)"] [:br] [:code.eval ";; => 100"]]

    (prettyfy-form-prettyfy-eval "(def test-1 99)")
    [:pre [:code "(def test-1 99)"]]

    (binding [*separator* " -->> "]
      (prettyfy-form-prettyfy-eval "(inc 99)"))
    [:pre [:code.form "(inc 99)"] [:br] [:code.eval ";; -->> 100"]]

    (prettyfy-form-prettyfy-eval "{:a 1 :b 2 :c 3}" 12 12)
    [:pre [:code.form "{:a 1,\n :b 2,\n :c 3}"] [:br] [:code.eval ";; => {:a 1,\n;;     :b 2,\n;;     :c 3}"]]))


(deftest panel-tests
  (are [x y] (= x y)
    (panel)
    [:div.panel [:div.panel-header] [:div.panel-footer]]

    (panel [:p "foo"])
    [:div.panel [:div.panel-header] [:p "foo"] [:div.panel-footer]]

    (panel [:p "foo"] [:span "bar"])
    [:div.panel [:div.panel-header] [:p "foo"] [:span "bar"] [:div.panel-footer]]))


(reset! project-name "Test project name")


(deftest screencast-title-tests
  (are [x y] (= x y)
    (screencast-title 1 "Test screencast title")
    [:h1 "Test project name Screencast 2 — Test screencast title"]))


(reset! screencast-topics ["test topic 1"
                           "test topic 2"
                           "test topic 3"])


(deftest whats-next-panel-tests
  (are [x y] (= x y)
    (whats-next-panel 1 [:p "test notes"])
    [:div.panel
     [:div.panel-header]
     [:h3 "What's next."]
     [:ol.de-highlight.whats-next
      [:li "test topic 1"]
      [:li "test topic 2"]
      [:li.highlight "test topic 3"]]
     [[:p "test notes"]]
     [:div.panel-footer]]))

(run-tests)