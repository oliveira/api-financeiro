(ns financeiro.handler_test
  (:require [midje.sweet :refer :all]
            [ring.mock.request :as mock]
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
  (let [response (app (mock/request :get "/saldo"))]
    (fact "o status é 200"
      (:status response) => 200)
    (fact "o texto do corpo é '0'"
      (:body response) => "0")))
