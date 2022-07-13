### Comex

#### Lidando com pagamentos

_Chegou a hora de fazer com o que o Comex lide com pagamentos._

* A diretoria financeira entrou em contato com nosso time e você precisa modelar como será a solução técnica para essa parte do sistema. Como Dev<T>, vista seu chapéu de pessoa que cuida da Arquitetura do Software! 👷️👷‍♂️️👷‍♀️️

* Seu time será responsável por **implementar a parte de pagamentos**, lidando com **boletos**, **cartões de créditos** e **Pix**. Temos uma **equipe de especialistas** nesse tipo de sistema.

* Atualmente, já há uma **equipe desenvolvendo independentemente um sistema de geração de notas fiscais**. Há uma subdiretoria da empresa responsável por notas fiscais e auditorias, com seus próprios especialistas de domínio.


###### <span style="color: light-gray">Escreva no `README` do seu projeto o que você pretende fazer, respondendo as seguintes perguntas:</span>

**1. Você criará um serviço separado ou fará no seu projeto atual?**
###### <span style="color: light-gray"> O serviço será separado. Quando o comex foi separado em domínios houve a necessidade de sepração entre:</span>

###### <span style="color: light-gray"> - Loja: responsável pelo gerenciamento de pedidos, retirada de produtos no estoque, controle de clientes por pedidos, etc;</span>

###### <span style="color: light-gray"> - Estoque: gerenciamento dos produtos existentes, quantidade de cada produto em estoque, responsabilidade de repor o estoque seja com fornecedores ou algo do gênero;</span>

###### <span style="color: light-gray">Portanto, o processo de pagamento tende a ser um serviço único. Por exemplo, se a parte de pagamentos estiver fora do ar ainda assim o cliente conseguirá fazer um pedido e o pagamento ficaria "pendente" até ser normalizado.</span>

**2. o Banco de Dados será separado ou será o mesmo do seu projeto atual?**
###### <span style="color: light-gray">Lidar com pagamentos terá um fluxo diferente de entrada e saída comparado ao gerencimaneto de pedidos, produtos, clientes, etc. Vai ser mais interessante separar cada banco para cada serviço (um pra um). Com isso, cada serviço se tornaria realmente independente em todos os aspectos, o que faria com que cada microsserviço tivesse de fato uma autonomia. <span>


**3.  Você precisará de um API Gateway? Se sim, quais as responsabilidades dessa peça da sua arquitetura?**
###### <span style="color: light-gray">Sim, ele será responsável pelo controle de fluxo de acesso entre os serviços (como uma "porteira" mesmo) garantindo o redirecionamento correto e de forma balancedada conforme demanda. Além disso, o Gateway auxilia na proteção contra ataques maliciosos, pois não permite sobrecarga nos serviços além de autenticar todo tráfego antes de liberá-lo para o serviço requisitado. <span>

  
**4. O sistema de notas fiscais será um projeto separado do de pagamentos ou os times serão unidos?**
###### <span style="color: light-gray"> Separado, as notas fiscais podem ser de compra (caso seja descrito na documentação essa funcionalidade ou até mesmo implementado posteriormente) ou venda. Com isso, o processo de geração de nota fiscal fica independente do pagamento podendo ser atualizado de forma assíncrona, além de possibilitar a atualização sem comprometer o sistema de pagamentos, da loja ou controle de estoque.<span>  

#### Diagrama de implantação na AWS

_E aí, Dev<T>? Bora mergulhar na parte de Infra?_

* Faça um diagrama de implantação, que mostra onde o código da sua Arquitetura definida na tarefa anterior será executada!

* Para fazer o diagrama, utilize ferramentas como:
  * draw.io, buscando por AWS em Search shapes
  * excalidraw.com, clicando no ícone de livrinho > em Procurar bibliotecas e escolhendo uma biblioteca de AWS

* Considere os seguintes serviços da AWS:
  - EC2
  - RDS
  - ELB
  - Elasticache

_Bônus: qual o serviço da AWS que cuida de Monitoramento? Inclua no seu diagrama!_

![Untitled Diagram drawio (1)](https://user-images.githubusercontent.com/105435774/178832495-7573d573-0015-4edd-b8d1-29a13042da37.png)
