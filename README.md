### Comex

#### Diagrama de integração entre os serviços

Planeje e faça um diagrama da integração entre os serviços:

* loja (o comex que você vem trabalhando)
* pagamentos
* notas fiscais
Quando um pedido for feito na loja, deve ser criado um pagamento e gerada a nota fiscal.

Pense em integrações síncronas e assíncronas.

Para as integrações síncronas, pense em chamadas HTTP entre os serviços.
Para as integrações assícronas, pense em tópicos, partições, chaves e consumer groups, usando JSON como formato de mensagens no Kafka.