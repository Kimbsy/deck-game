(ns deck-game.core
  (:require [quil.core :as q]
            [quip.core :as qp]
            [deck-game.scenes.game :as g]))

(defn setup
  []
  {})

(defn init-scenes
  []
  {:game (g/init)})

(def game
  (qp/game
   {:title "untitled steam deck game"
    :size :fullscreen
    :setup setup
    :init-scenes-fn init-scenes
    :current-scene :game}))

(defn -main
  [& args]
  (qp/run game))
