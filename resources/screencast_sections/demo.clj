(require '[screedcast.core :refer [panel
                                   prettyfy-form-prettyfy-eval
                                   screencast-title
                                   whats-next-panel]])

(def demo-index 2)


[:body
 (panel
  (screencast-title demo-index "Demonstrations")
  [:h3 "How to use Screedcast."]

  [:pre [:code "(require '[screedcast.core :refer [panel prettfy-form-prettyfy-eval screencast-title whats-next-panel]])"]])

 (panel
  [:h3 "Make a panel."]

  [:pre [:code "(panel\n [:h3 \"Insert panel title\"] [:p \"Insert panel contents\"])"]])


 (panel
  [:h3 "Make a code demo."]

  [:pre [:code "(prettyfy-form-prettyfy-eval \"(inc 99)\")"]]

  [:p "gets rendered as this:"]

  (prettyfy-form-prettyfy-eval "(inc 99)"))

 (whats-next-panel
  demo-index
  [:div.note
   [:p "Put speaker notes in a " [:code "[:div.note ...]"] " at the end."]])
 ]