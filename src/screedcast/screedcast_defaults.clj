(def ^{:no-doc true} screedcast-defaults {:project-license-section [:div.license
                                                                    [:p "This program and the accompanying materials are made available under the terms of the "
                                                                     [:a {:href "https://opensource.org/license/MIT"}
                                                                      "MIT License"] "."]]

                                          :screencast-html-directory "doc/screencast_slides/"
                                          :sections-directory "resources/screencast_sections/"

                                          :separator " => "
                                          :wrap-at 80})