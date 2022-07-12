### Comex

#### Lidando com pagamentos

_Chegou a hora de fazer com o que o Comex lide com pagamentos._

* A diretoria financeira entrou em contato com nosso time e você precisa modelar como será a solução técnica para essa parte do sistema. Como Dev<T>, vista seu chapéu de pessoa que cuida da Arquitetura do Software! 👷️👷‍♂️️👷‍♀️️

* Seu time será responsável por **implementar a parte de pagamentos**, lidando com **boletos**, **cartões de créditos** e **Pix**. Temos uma **equipe de especialistas** nesse tipo de sistema.

* Atualmente, já há uma **equipe desenvolvendo independentemente um sistema de geração de notas fiscais**. Há uma subdiretoria da empresa responsável por notas fiscais e auditorias, com seus próprios especialistas de domínio.


###### <span style="color: light-gray">Escreva no `README` do seu projeto o que você pretende fazer, respondendo as seguintes perguntas:</span>

**1. Você criará um serviço separado ou fará no seu projeto atual?**
###### <span style="color: light-gray"> O serviço será separado, explicar um pouco do conceito de microsserviços e exemplificar os microsserviços separados do comex</span>

**2. o Banco de Dados será separado ou será o mesmo do seu projeto atual?**
###### <span style="color: light-gray">Lidar com pagamentos terá um fluxo diferente de produtos, clientes. Vai ser mais interessante separar cada banco para cada serviço (explicar o conceito por trás disso); <span>

  
**3.  Você precisará de um API Gateway? Se sim, quais as responsabilidades dessa peça da sua arquitetura?**
###### <span style="color: light-gray">Sim, será responsável pelo controle de fluxo, fraudes (verificar certinho o conceito), o gateway vai acessar cada serviço algo assim<span>

  
**4. O sistema de notas fiscais será um projeto separado do de pagamentos ou os times serão unidos?**
###### <span style="color: light-gray"> Separado, as notas fiscais podem ser de compra ou venda (eu acho), e se houver redimensionamento, etc, mais fácil serem microsserviços amigos do que tudo fazer parte do mesmo serviço
conceito de assincrono sobre demora no pagamento, geração de nota.<span>  

#### MS-rascunho
- Loja (pedidos, clientes) - liga a estpque/pagamentos/juridico
- Estoque (produtos, quantidades, fornecedores) - liga a loja(retira quant)/juridico???
- Pagamentos (tipo de pagamento, status) - liga a loja e estoque?
- Jurídico - notas fiscais (de vendas na loja e de reposição de estoque) - liga a loja/pagamentos/estoque?
