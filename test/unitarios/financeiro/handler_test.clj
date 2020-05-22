(ns financeiro.handler_test
  (:require [midje.sweet :refer :all]
            [ring.mock.request :as mock]
            [cheshire.core :as json]
            [api-financeiro.handler :refer :all]))

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

(facts "Saldo inicial é 0" :aceitacao
  (against-background (json/generate-string {:saldo 0}) => "{\"saldo\":0}")

  (let [response (app (mock/request :get "/saldo"))]
    (fact "o formato é 'application/json'"
      (get-in response [:headers "Content-Type"]) => "application/json; charset=utf-8")

    (fact "o status é 200"
      (:status response) => 200)

    (fact "o texto do corpo um json com saldo igual a 0"
      (:body response) => "{\"saldo\":0}")))
