(require '[screedcast.core :refer [panel
                                   prettyfy-form-prettyfy-eval
                                   screencast-title
                                   whats-next-panel]])


(def intro-index 0)


[:body
 (panel
  (screencast-title intro-index "Introduction")
  [:h4.subtitle [:em "A highly un-configurable screencast maker thingy"]]

  [:p "The Screedcast lib generates the visual component of a screencast, like this one."]

  [:p "Note: Text size is adjusted for my screen. Adjust your browser's 'zoom' to your taste."]

  [:div.vspace]
  [:div.vspace]
  [:div.vspace]
  [:div.vspace]
  [:div.vspace]
  [:div.vspace]
  [:div.vspace]

  [:p "Click/tap the footer (i.e., the region just below with the \"1/" [:em "N"] "\" and the web link) to go forward one panel."])


 (panel
  [:h3 "Authored in hiccup-dialect " [:span.small-caps "html"]]

  [:p [:em "Hiccup"] " is actually Clojure that gets compiled into html."]

  [:div.vspace]

  [:p "That heading above is composed like this."]

  [:pre [:code "[:h3 \"Authored in hiccup-dialect\" [:span.small-caps \"html\"]]"]]

  [:div.vspace]
  [:div.vspace]

  [:p "And this sentence is composed like this."]

  [:pre [:code "[:p \"And this sentence is composed like this.\"]"]]

  [:div.vspace]
  [:div.vspace]

  [:p "Click/tap this footer to advance."])


 (panel
  [:h3 "Pseudo-evaluated code examples"]

  (prettyfy-form-prettyfy-eval "(inc 99)")

  [:div.vspace]

  [:p "Click/tap the s-expression to reveal the evaluation."])


 (panel
  [:h3 "Footer metadata"]

  [:p "The footer displays the current progress to give the speaker and audience a gentle reminder of where they are in the discussion."]

  [:p "The footer also displays a weblink so a person in the audience can, at any point, jet off to the website for more info."])


 (panel
  [:h3 "Speaker notes"]

  [:ul
   [:li "foo"]
   [:li "bar"]
   [:li "baz"]]

  [:div.note]

  [:div.note
   [:p "Speaker notes look like this. Their display state can be toggled, so they won't be seen during the actual screencast recording."]
   [:ul
    [:li "'foo' is great,"]
    [:li "'bar' is really good, and "]
    [:li "'baz' works well for a lot of situations..."]]])


 (panel
  [:h3 "Definition: " [:em "panel"]]

  [:div.vspace]

  [:p "A " [:em "panel"] " is a single screen-ful of content, including the header, the middle stuff, and the footer."]

  [:p "Middle stuff..."]

  [:ol
   [:li "chocolate"]
   [:li "vanilla"]
   [:li "strawberry"]]

  [:div.vspace]

  [:p "The " [:em "header"] " is the region up there above the " [:strong "Definition: " [:em "panel"]] " heading."]
  [:p "The " [:em "footer"] " is the region down here at the bottom that contains the panel numbers and the web-link."])


 (panel
  [:h3 "Definition: " [:em "section"]]

  [:p "A " [:em "section"] " is a series of panels in one " [:span.small-caps "html"] " file devoted to one topic."]

  [:div.vspace]

  [:p "This screencast section is the " [:em "Introduction"] ". There are two other sections: the " [:em "Usage"] " section, and the " [:em "Creating"] " section."])


 (panel
  [:h3 "Side-by-side sub-panels"]

  [:p "It can be helpful to display pairs of concepts for comparison."]

  [:div.side-by-side-container
   [:div.side-by-side
    [:p [:strong "Addition"]]
    (prettyfy-form-prettyfy-eval "(+ 5 4)")]
   [:div.side-by-side
    [:p [:strong "Subtraction"]]
    (prettyfy-form-prettyfy-eval "(- 5 4)")]]

  [:div.vspace]

  [:p "Click/tap each expression to reveal its evaluation."])


 (whats-next-panel
  intro-index
  [:div.note
   [:p "A 'What's Next' panel is a special panel at the end of a screencast section that lets the audience feel our current location. It shows what we just finished discussing and what will be discussed in the next section."]

   [:p "In this case, we just finished the " [:em "Introduction"] " discussion, and will next discuss the " [:em "Usage"] ". And there's a third section after that will discuss " [:em "Creating"] "."]

   [:p "Click/tap the header to go back one panel."]

   [:p "Scroll just a tiny bit further down and click/tap the copyright statement to toggle the speaker notes. If you do that, this orange text will disappear. Clicking it again will make this orange text re-appear."]])]