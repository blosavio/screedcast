(require '[screedcast.core :refer [panel
                                   prettyfy-form-prettyfy-eval
                                   screencast-title
                                   whats-next-panel]])


(def intro-index 0)


[:body
 (panel
  (screencast-title intro-index "Introduction")
  [:h4.subtitle [:em "A highly un-configurable screencast maker thingy"]]

  [:p "The Screedcast lib generates a series of panels like this one."]

  [:p "Click/tap the footer to go forward one panel."]

  [:div.note
   [:p "Speaker notes down here. They won't be seen during the actual screencast."]])

 (panel
  [:h3 "Code examples"]

  (prettyfy-form-prettyfy-eval "(inc 99)")

  [:p "Click/tap the s-expression to reveal the evaluation."])

 (whats-next-panel
  intro-index
  [:div.note
   [:p "Click/tap the header to go back one panel. Click/tap the copyright statement to toggle the speaker notes."]])
 ]