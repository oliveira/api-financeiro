(ns financeiro.handler_test
  (:require [midje.sweet :refer :all]
            [ring.mock.request :as mock]
            [cheshire.core :as json]
            [financeiro.handler :refer :all]
            [financeiro.db :as db]))

(facts "Dá um 'Hello World' na rota raiz"
  (fact "o status da resposta é 200"
    (let [response (app (mock/request :get "/"))]
      (:status response) => 200))

  (fact "o texto do corpo é 'Hello World'"
    (let [response (app (mock/request :get "/"))]
      (:body response) => "Olá, mundo!")))

(facts "Rota não existe"
  (let [response (app (mock/request :get "/1nv4l1d4"))]
    (fact "o código de erro é 404"
      (:status response) => 404)

    (fact "o texto do corpo é 'Recurso não encontrado'"
      (:body response) => "Recurso não encontrado")))

(facts "Registra uma receita de 10$" :unit
  (against-background (db/registrar {:valor 10
                                     :tipo "receita"})
    => {:id 1 :valor 10 :tipo "receita"})
  (let [response
    (app (-> (mock/request :post "/transacoes")
              (mock/json-body {:valor 10
                               :tipo "receita"})))]
    (fact "o status da resposta = 201"
      (:status response) => 201)
    (fact "o body é um json com id, valor e tipo"
      (:body response) => "{\"id\":1,\"valor\":10,\"tipo\":\"receita\"}")))
