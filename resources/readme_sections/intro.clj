[:section#intro
 [:h2 "Introduction"]

 [:p "Software documentation ought to have " [:a {:href "https://github.com/blosavio/readmoi"} "lots of code examples"] ". Because some people learn better by watching audio-visual screencasts, those screencasts ought to have lots of code examples, too. Or, perhaps someone is considering a library, but would prefer to casually watch and listen to a screencast with a cup of tea instead of wading through pages and pages of dry text."

  [:p "A standard slideshow presentation does not display code examples in an audience-friendly manner. While they may offer animations for transitioning from one slide to the next, they don't have any particular awareness of text that represents code. Displaying a mass of code can be visually overwhelming, and it's difficult for people in the audience to focus on what the speaker is dicussing. One tactic is to progressively reveal examples to keep people's attention focused on the moment's topic. Typical slideshow software does not facilitate this."]

  [:p "And standard slideshow presentation software does not make it straightforward include code examples. We must write the examples in the code editor, evaluate it, then copy-paste the entire glob into the slideshow. If we want to change a code example, we must delete it, and start the dance all over again."]

  [:p "Screedcast is a library for generating a screencast with evaluated Clojure code examples. Except for the source file directories and the output file directories, there are almost no settings to be adjusted. Take it as it is."]

  [:p "Screencasts are authored in " [:a {:href "https://github.com/weavejester/hiccup"} "hiccup"] "-dialect " [:span.small-caps "html"] ". A utility function inserts a minuscule amount of javascript/" [:span.small-caps "css"] " around Clojure code examples. When clicked/tapped, the screencast gently reveals the expression's value. Code examples are easily adjusted directly inside the document, the functions are always up-to-date with the library, and the screencast reveals the code examples in a manner that focuses the audience's attention."]]]