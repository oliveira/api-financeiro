(ns financeiro.transacoes-test
  (:require [midje.sweet :refer :all]
            [financeiro.transacoes :refer :all]))

(fact "Uma transação sem valor não é válida" :unit
  (valida? {:tipo "receita"}) => false)

(fact "Uma transação com valor negativo não é válida" :unit
  (valida? {:valor -10 :tipo "receita"}) => false)

(fact "Uma transação com valor não numérico não é válida" :unit
  (valida? {:valor "mil" :tipo "receita"}) => false)

(fact "Uma transação sem tipo não é válida" :unit
  (valida? {:valor 90}) => false)

(fact "Uma transação com tipo desconhecido não é válida" :unit
  (valida? {:valor 8 :tipo "investimento"}) => false)

(fact "Uma transação com valor numérico positivo e com tipo conhecido é válida" :unit
  (valida? {:valor 230 :tipo "receita"}) => true)
