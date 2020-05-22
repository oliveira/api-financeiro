(ns financeiro.saldo-aceitacao-test
  (:require [midje.sweet :refer :all]
            [financeiro.auxiliares :refer :all]))

(against-background [(before :facts (iniciar-servidor porta-padrao))
                      (after :facts (parar-servidor))]
  (fact "O saldo inicial é 0" :aceitacao
    (conteudo "/saldo") => "0"))
