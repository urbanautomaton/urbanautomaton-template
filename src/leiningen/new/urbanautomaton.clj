(ns leiningen.new.urbanautomaton
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "urbanautomaton"))

(import [java.util Calendar])
(defn year []
  (.get (Calendar/getInstance) Calendar/YEAR))

(defn urbanautomaton
  "Simon Coffey's personal 'lein new' template"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)
              :author "Simon Coffey"
              :year (year)}]
    (main/info "Generating new project with MIT license and test.check.")
    (->files data
             ["src/{{sanitized}}/core.clj"       (render "core.clj" data)]
             ["project.clj"                      (render "project.clj" data)]
             ["README.markdown"                  (render "README.markdown" data)]
             ["LICENSE"                          (render "LICENSE" data)]
             [".gitignore"                       (render "gitignore" data)]
             ["test/{{sanitized}}/core_test.clj" (render "test.clj" data)])))
