[:section#glossary
 [:h2 "Glossary"]

 [:dl
  [:dt#panel "panel"]
  [:dd [:p "A single frame of a screencast, written in hiccup-style " [:span.small-caps "html"] ", which may contain Clojure code examples. Screedcast uses the term " [:em "panel"] " to distinguish between a traditional " [:a {:href "#slideshow"} "slideshow"] " " [:em "slide"] "."]]]
 
 [:dl
  [:dt#screencast "screencast"]
  [:dd [:p "An presentation whose visual component is a series of screens and whose audio component is voice narration."]]]

 [:dl
  [:dt#section "section"]
  [:dd [:p "One " [:span.small-caps "html"] " file that contains the " [:a {:href "#panel"} "panels"] " for one episode of a screencast. The Screedcast generator is capable of compiling multiple episodes with a single invocation."]]]

 [:dl
  [:dt#slideshow "slideshow"]
  [:dd [:p "A typical presentation authored in "
        "Apple " [:em "Keynote"] ", "
        "Google " [:em "Slides"] ", "
        "LibreOffice " [:em "Impress"] ", or "
        "Microsoft " [:em "PowerPoint"] ", etc. No particular provisions for displaying evaluated Clojure code examples."]]]


]