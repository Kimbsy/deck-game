(ns deck-game.scenes.game
  (:require [quil.core :as q]
            [quip.sprite :as qpsprite]
            [quip.utils :as qpu]
            [deck-game.common :as common]))

;; A is \newline (key-code 10)
;; B is ESC (key-code 27)

;; L1 is control (key-code 17)
;; R1 is alt (key-code 18)

;; R2 is left click
;; L2 is right click

;; R3 is left click

;; R4 is key-code 33
;; R5 is key-code 34

;; L4 is key-code 16
;; L5 is key-code 524 (super)

;; right pad click is left click
;; left pad click is middle click (:center)

;; left on left stick or D-pad is key-code 37
;; up is 38
;; right is 39
;; down is 40
;; ^ these are the arrow keys

;; Right stick is mouse move

;; right menu button is ESC (key-code 27)
;; left menu button is tab (key-code 9)

(def keymap {"a" 10
             "b" 27
             "l1" 17
             "r1" 18
             "r4" 33
             "r5" 34
             "l4" 16
             "l5" 524
             "left" 37
             "up" 38
             "right" 39
             "down" 40
             "view" 9})

(def circle-max-size 800)

(defn circle
  [pos color]
  {:sprite-group :circle
   :pos pos
   :color color
   :size 10
   :update-fn (fn [{s :size :as c}]
                (when (< s circle-max-size)
                  (update c :size * 1.09)))
   :draw-fn (fn [{[x y] :pos c :color s :size}]
              (q/no-fill)
              (qpu/stroke c)
              (q/stroke-weight 5)
              (q/ellipse x y s s))})

(defn key-pressed
  [state e]
  (prn e)
  state)

(defn mouse-pressed
  [{:keys [current-scene] :as state} {:keys [x y] :as e}]
  (-> state
      (update-in [:scenes current-scene :sprites]
                 conj (circle [x y] (common/random-color)))))

(defn update-game
  [{:keys [current-scene] :as state}]
  (-> state
      (qpsprite/update-scene-sprites)
      (update-in [:scenes current-scene :sprites]
                 #(filter some? %))))

(defn draw-game
  [{:keys [current-scene] :as state}]
  (qpu/background common/black)
  (qpsprite/draw-scene-sprites state))

(defn init
  []
  {:sprites []
   :update-fn update-game
   :draw-fn draw-game
   :key-pressed-fns [key-pressed]
   :key-released-fns []
   :mouse-pressed-fns [mouse-pressed]
   :mouse-released-fns []})
