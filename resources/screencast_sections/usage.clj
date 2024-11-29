(require
 '[screedcast.core :refer [panel
                           prettyfy-form-prettyfy-eval
                           screencast-title
                           whats-next-panel]])


(def usage-index 1)


[:body
 (panel
  (screencast-title usage-index "Usage")

  [:p "Use a screencast to augment written documents."]

  [:p "Some people learn/understand audio-visual better than written examples."]

  [:div.note
   [:p "Speaker notes can be toggled."]])


 (whats-next-panel
  usage-index
  [:div.note
   [:p "Speaker notes..."]])
 ]