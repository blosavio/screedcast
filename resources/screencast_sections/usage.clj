(require
 '[screedcast.core :refer [panel
                           prettyfy-form-prettyfy-eval
                           screencast-title
                           whats-next-panel]])


(def usage-index 1)


[:body
 (panel
  (screencast-title usage-index "Usage")

  [:ul

   [:li [:p "Use a screencast to augment written documents."]]

   [:li [:p "Some people learn/understand audio-visual better than written examples."]]

   [:li [:p "The visual component of a screencast is an " [:span.small-caps "html"] " file rendered by a web-browser."]]

   [:li [:p "I suggest putting the browser into " [:em "Full Screen"] " mode and adjusting the zoom so that the text fills the middle 50% of the screen. This screencast is currently adjusted for my screen; you may have to tweak it for your own screen."]]]

  [:div.vspace]
  [:div.vspace]
  [:div.vspace]

  [:p "Click/tap the footer to precisely advance to next panel."])

 (panel
  [:h3 "Speaker notes"]

  [:ul
   [:li [:p "Notes for the speaker may be included in a hidden " [:span.small-caps "html"] " div."]]
   [:li [:p "The intention is that the notes will be visible for rehearsal, but hidden for recording. Perhaps the speaker could have the speaker notes displayed off to the side on a laptop running the same screencast section."]]
   [:li [:p "Speaker notes appear as orange text to distinguish from main content, and as a warning while recording that they should be hidden."]]]
  
  [:div.note
   [:p "Speaker notes can be toggled by clicking/tapping the copyright notice at the end of the section."]])


 (panel
  [:h3 "Pseudo-evaluated code examples"]

  [:p "During a screencast, it's often desirable to discuss an expression without being distracted by what the result with be. Screedcast offers a facility for simulating an evaluation."]

  [:p "Click/tap Clojure code examples to reveal the expression's evaluation."]

  (prettyfy-form-prettyfy-eval "(map inc [1 2 3])")

  [:p "Giving the impression of being at the " [:span.small-caps "repl"] " also makes the screencast a little more familiar and engaging."]

  [:div.vspace]

  [:p "If a panel contains many code examples, you may want to re-hide the evaluations as you work your way through the discussion. Click/tap the expression again (not the evaluation) to re-hide."]

  [:div.note
   [:p "Speaker note: " [:strong "Way"] " too much text on this panel. Not a good example of a screencast panel."]])


 (panel
  [:h3 "Precise navigation"]

  [:p "Don't bother to drag the scroll-bar or use the mouse scroll wheel. Clicking the headers/footers will always advance exactly one screenful."]

  [:p "If you bump it and the screen gets a little bit displaced, simply click the nearest header/footer to precisely re-align the panel with the screen."])


 (whats-next-panel
  usage-index
  [:div.note
   [:p "A good way to wrap up a screencast section is to put it into context. The 'What's Next' panel tells us what we just discussed and highlights the next topic."]
   
   [:p "This particular screencast series contains three sections. Since we just discussed " [:em "Using"] " Screedcast (section #2), we'll follow that up with a discussion on how to " [:em "Create"] " a screencast (section #3)."]

   [:div.vspace]
   [:div.vspace]
   [:div.vspace]
   [:div.vspace]
   [:div.vspace]
   [:div.vspace]

   [:p "And since we're here, scroll just a tiny bit further to see the copyright and license banners. Clicking the copyright section will toggle the speaker notes."]])]