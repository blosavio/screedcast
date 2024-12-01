(require '[screedcast.core :refer [panel
                                   prettyfy-form-prettyfy-eval
                                   screencast-title
                                   whats-next-panel]])

(def create-index 2)


[:body
 (panel
  (screencast-title create-index "Creating")
  [:h3 "How to author a Screedcast"]

  [:div.vspace]
  
  [:p "Pull in the requirements."]
  
  [:pre [:code "(require '[screedcast.core :refer [panel prettyfy-form-prettyfy-eval screencast-title whats-next-panel]])"]]

  [:div.vspace]
  [:div.vspace]
  [:div.vspace]
  [:div.vspace]

  [:p "Click/tap footer to advance precisely one panel."])

 (panel
  [:h3 "It's just hiccup/" [:span.small-caps "html"] "..."]
  
  [:p "...which is just Clojure."]

  [:pre [:code "[:p \"This is a paragraph element\"]"]]

  [:div.vspace]
  
  [:p "And this is an ordered list."]

  [:pre [:code "[:ol
   [:li \"1st item\"]
   [:li \"2nd item\"]
   [:li \"3rd item\"]]"]]

  [:div.vspace]

  [:p "And this is a pre-formatted code element."]
  
  [:pre [:code "[:pre [:code \"(inc 99)\"]]"]]

  [:div.note
   [:p "But we'll see soon that Screedcast has a utility that displays code examples better."]])

 
 (panel
  [:h3 "Create a panel"]

  [:pre [:code "(panel\n  [:h3 \"Panel title\"]\n  [:p \"Panel contents\"]\n  [:div.note [:p \"Speaker notes\"]])"]]

  [:div.vspace]
  
  [:p [:code "panel"] " is a convenience utility that automatically creates the hiccup/" [:span.small-caps "html"] " for a screencast panel with"]
  [:ul
   [:li "clickable headers and footers,"]
   [:li "panel numbering/progress indicator"]
   [:li "webpage url"]])


 (panel
  [:h3 "Demonstrate code"]

  [:pre [:code "(prettyfy-form-prettyfy-eval \"(inc 99)\")"]]

  [:div.vspace]
  
  [:p "Gets rendered as this clickable element:"]

  (prettyfy-form-prettyfy-eval "(inc 99)")

  [:div.vspace]

  [:p [:code "prettyfy-form-prettyfy-eval"] " is a utility that creates the hiccup/" [:span.small-caps "html"] " for simulating evaluating a Clojure expression."]

  [:div.note
   [:p "The expression is passed to " [:code "prettyfy-form-prettyfy-eval"] " as a string. It then evaluates the expression, converts that value to a pretty-printed string, and appends it to the original expression with an interleaved separator, which defaults to " [:code " => "] "."]
   
   [:p "Those strings are then wrapped in hiccup/" [:span.small-caps "html"] " div elements that are manipulated by javascript/jQuery during the screencast presentation to give the illusion of " [:span.small-caps "repl"] " evaluation."]

   [:p "See the API documentation for details on how to use " [:code "prettyfy-form-prettyfy-eval"] "."]])


 (panel
  [:h3 [:span.small-caps "css"] " helper #1: vertical spacing"]

  [:p "Element foo"]

  [:pre.de-highlight [:code "[:div.vspace]"]]
  
  [:p "Element bar"]

  [:pre.de-highlight [:code "[:div.vspace]"]]

  [:p "Element baz"]

  [:div.note
   [:p "Screedcast doesn't replicate Microsoft PowerPoint, Apple Keynote, et. al., so it doesn't have all the facilities for infinite visual adjustment. But it does have a basic method for spacing elements vertically by inserting a " [:code "[:div.vspace]"] " element."]]
  )

 (panel
  [:h3 [:span.small-caps "css"] " helper #2: side-by-side"]

  [:div.side-by-side-container
   [:div.side-by-side
    [:p "Stuff in the " [:strong "left column"] "."]
    [:p "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."]]
   [:div.side-by-side
    [:p "Stuff in the " [:strong "right column"] "."]
    [:p "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."]]]

  [:div.vspace]
  
  [:pre [:code "[:div.side-by-side-container
   [:div.side-by-side
    [:p \"Stuff in the left column.\"]
    [:p \"Lorem ipsum dolor...\"]]
   [:div.side-by-side
    [:p \"Stuff in the right column.\"]
    [:p \"Duis aute irure...\"]]]"]]

  [:div.note
   [:p "The " [:span.small-caps "css"] " file contains layout directives to display content side-by-side for easier comparison."]])

 (panel
  [:h3 [:span.small-caps "css"] " helper #3: speaker notes"]

  [:ul
   [:li "Don't junk up the panel with what to say, then bore the audience by reading to them."]
   [:li "Write speaker notes in a " [:code "[:div.note [:p \"...\"]]"] " block."]
   [:li "Toggle the speaker notes visibility by clicking the copyright notice at the end of the screencast section."]]

  [:div.note
   [:p "The speaker notes appear in orange so that they're easily distinguishable from the main content."]])


 (panel
  [:h3 "Setup"]

  [:p "See the Screedcast project ReadMe for details on how to setup the files, directories, and options, and how to generate the " [:span.small-caps "html"] "."]

  [:p [:code "https://github.com/blosavio/screedcast"]])


 (whats-next-panel
  create-index
  [:div.note
   [:p "This is the final screencast section, so there ain't no more."]])]