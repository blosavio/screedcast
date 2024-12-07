(def ^{:no-doc true} screedcast-defaults-docstring
  "A hash-map residing in `screedcast_defaults.clj` that supplies the default
 values for the following options keys:

  * `:project-license-section`
  * `:screencast-html-directory`
  * `:sections-directory`
  * `:separator`
  * `:wrap-at`
  * `:tidy-html?`")


(def ^{:doc screedcast-defaults-docstring}
  screedcast-defaults {:project-license-section [:div.license
                                                 [:p "This program and the accompanying materials are made available under the terms of the "
                                                  [:a {:href "https://opensource.org/license/MIT"}
                                                   "MIT License"] "."]]

                       :screencast-html-directory "doc/screencast_slides/"
                       :sections-directory "resources/screencast_sections/"

                       :separator " => "
                       :wrap-at 80

                       :tidy-html? false})