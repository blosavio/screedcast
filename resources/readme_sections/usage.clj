[:section#usage
 [:h2 "Usage"]

 [:h3 "Overview"]

 [:p "We write a screencast, which is, at the bottom, Clojure code in " [:code ".clj"] " files. The code examples are straight Clojure, while the surrounding text, formatting, and document organization are hiccup forms. Then, we create an "[:em "options"] " file, that tells Screedcast where to find the files and where to send the output " [:span.small-caps "html"] " files. Finally, we tell Screedcast to compile the hiccup forms into " [:span.small-caps "html"] ". Those " [:span.small-caps "html"] " files are viewable in any modern web browser. When rendered by a web browser, clicking/tapping on a Clojure expression triggers a " [:span.small-caps "dom"] " event that toggles the visibility of expression's evaluation."]

 [:h3 "Detailed usage"]
 
 [:ol
  [:li [:p "Complete the " [:a {:href "#setup"} "setup"] "."]]
  
  [:li [:p "Write the presentation in hiccup/" [:span.small-caps "html"] ". Each episode section is formatted like this."]

   [:pre [:code "[:body\n  (panel " [:em "❬hiccup content❭"] ")\n  ...]"]]

   [:p "The " [:code "panel"] " function is convenience utility automatically creates the hiccup required for a header, footer, page-numbering, etc."]

   [:p "When we wish to create a code example, we use the following pattern."]

   [:pre [:code "(prettyfy-form-prettyfy-eval \"(inc 99)\")"]]

   [:p "Notice that we do not insert a " [:code "100"] ". " [:code "prettyfy-form-prettyfy-eval"] " does that for us during compilation. If, while writing the screencast, we'd like to know what the expression evaluates to, put the cursor right before the closing quotation marks and " [:code "eval-last-sexp"] "."]

   [:p "See also a " [:a {:href "https://github.com/blosavio/readmoi?tab=readme-ov-file#detailed-usage"} " sibling project's usage"] " for a few more tips."]

   [:p "Screedcast also includes a trio of " [:span.small-caps "css"] " helpers."]

   [:ul
    [:li [:p [:code "[:div.vspace]"] " provides some vertical breathing room between elements."]]
    [:li
     [:p "This pattern provides side-by-side column content blocks."]
     [:pre [:code
"[:div.side-by-side-container
  [:div.side-by-side
    [:p \"Stuff in the left column.\"]
    [:p \"Lorem ipsum dolor...\"]]
  [:div.side-by-side
    [:p \"Stuff in the right column.\"]
    [:p \"Duis aute irure...\"]]]"]]]
    [:li "Speaker notes may be included within a " [:code "[:div.note ...]"] ". Their visibility is toggled by clicking the copyright notice."]]]
  
  [:li
   [:p "Insert the required and optional information in the " [:a {:href "https://github.com/blosavio/screedcast/blob/main/resources/screedcast_options.edn"} [:code "screedcast_options.edn"]] " file. Feel free to copy-paste " [:a {:href "https://github.com/blosavio/screedcast/blob/main/resources/screedcast_options.edn"} "this example"] ", and edit as necessary."]

   [:p "The following keys are " [:strong "required"] " (i.e., they do not have a default value):"]

   [:ul
    [:li
     [:p [:code ":screencast-filename-bases"] " A vector of hash-maps, one hash-map for each screencast. Each map contains the following keys:"]
     [:ul
      [:li [:p [:code ":screencast-filename"] " A string representing the filename without the " [:code ".clj"] " extension."]]
      [:li [:p [:code ":screencast-title"] " A string that will appear as the first text of the screencast title in the first panel."]]
      [:li [:p [:code ":screencast-uuid"] " A version 4 Universally Unique Identifier."]]
      [:li [:p [:code ":video-slug"] " A string that completes the url (i.e., the 'slug'), along with " [:code ":toc-video-url-base"] " (below), that locates the screencast's video."]]]]
    [:li [:p [:code ":project-name-formatted"] " A string containing the name of the project, appearing in the title panel."]]
    [:li [:p [:code ":project-description"] " A string describing the project, appearing in the web browser's OS window frame."]]
    [:li [:p [:code ":project-webpage-link"] " A string appearing in the footer of the screencast."]]
    [:li [:p [:code ":copyright-holder"] " A string used to create the copyright notice."]]]

   [:p "The following keys are " [:strong "optional"] ", and will revert to defaults if not supplied by the options."]

   [:ul
    [:li [:p [:code ":project-license-section"] " A hiccup/" [:span.small-caps "html"] " form that announces the screencast's license. Defaults to the MIT license."]]
    [:li [:p [:code ":sections-directory"] " A string representing the directory to find the hiccup/" [:span.small-caps "html"] " files. Include the trailing '/'. Defaults to 'resources/screencast_sections/'."]]
    [:li [:p [:code ":screencast-html-directory"] " A string representing the directory to place the output files. Include the trailing '/'. Defaults to 'doc/screencast_slides/'."]]
    [:li [:p [:code ":separator"] " A string representing a sequence of characters to insert between the Clojure code form and resulting evaluation. Defaults to ' => '."]]
    [:li [:p [:code ":wrap-at"] " An integer that governs the wrapping column for the " [:a {:href "https://github.com/kkinnear/zprint"} "zprint"] " pretty printer. Defaults to " [:code "80"] "."]]
    [:li [:p [:code ":tidy-html?"] " Indent and wrap " [:span.small-caps "html"] ". Defaults to " [:code "false"] "."]]
    [:li [:p [:code ":toc?"] " A boolean that controls generating a " [:em "Table of Contents"] " page with hyperlinks to each screencast section. Defaults to " [:code "false"] "."]]
    [:li [:p [:code ":toc-uuid"] " A UUID for the " [:em "Table of Contents"] " webpage."]]
    [:li [:p [:code ":toc-video-url-base"] " A string that locates the screencast videos server, representing the leading portion of the url up to the slug. Include the trailing '/'. Defaults to " [:code "nil"] ". " [:em "Note:"] " This value does not point to the local file system, but the video-hosting server."]]
    [:li [:p [:code ":toc-panel-url-base"] " A string that locates the screencast panels server, representing the leading portion of the url up to the slug. Include the trailing '/'. Defaults to " [:code "nil"] ". " [:em "Note:"] " This value does not point to the local file system, but intended to be somewhere like the project's GitHub resources sub-directory."]]
    [:li [:p [:code ":toc-preamble"] " A hiccup/" [:span.small-caps "html"] " form that will be displayed above the screencast listings."]]]]
  
  [:li [:p "Generate the " [:span.small-caps "html"] " files. Screedcast is not fancy. Just evaluate this." ]

   [:pre [:code "(generate-all-screencasts (load-file \"resources/screedcast_options.edn\"))"]]

   [:p  "Or use a " [:a {:href "https://github.com/blosavio/screedcast/blob/main/resources/screedcast_generator.clj"} " generator script"] ". Find the " [:span.small-caps "html"] " files in the output directory, which defaults to " [:code "doc/screencast_slices/"] "."]]]]