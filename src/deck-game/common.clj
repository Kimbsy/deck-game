(ns deck-game.common)

(def black [60 58 42])
(def white [242 243 226])
(def green [94 252 141])
(def purple [242 213 248])
(def yellow [247 179 43])
(def blue [98 187 193])
(def pink [236 40 142])

(defn random-color
  []
  (rand-nth [white
             green
             purple
             yellow
             blue
             pink]))
