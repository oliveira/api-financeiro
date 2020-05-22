(ns financeiro.saldo-aceitacao-test
  (:require [midje.sweet :refer :all]
            [cheshire.core :as json]
            [financeiro.auxiliares :refer :all]))

(against-background [(before :facts (iniciar-servidor porta-padrao))
                      (after :facts (parar-servidor))]
  (fact "O saldo inicial é 0" :aceitacao
    (json/parse-string (conteudo "/saldo") true) => {:saldo 0}))
