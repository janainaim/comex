### Comex

#### Refatorando o projeto para **Clean Architecture**

- Inspirado no curso de Clean Architecture, refatora o código do Comex para que siga uma estrutura parecida com a vista no curso. Não deixe de representar conceitos como Use Cases, Services e Repository onde forem adequados.

**Reflita: o código melhorou? Se sim, em qual aspecto? Se não, qual a raiz do problema?**
###### <span style="color: light-gray">De forma geral ao bater o olho no código é possível em poucos cliques descobrir do que se trata a aplicação pelo dominio de sua estrtura, no entanto eu considero este tipo de arquitetura ainda muito confusa. Basicamente o form, o dto e o repository ficam junto com os models, o que acaba tornando mais complexo visualizar uma classe específica ou se a estrutura de uma funcionalidade segue padrões parecidos, por exemplo. Além disso, existem classes que representam entidades, serviços, repositórios</span>


#### DDD

**- Quais padrões táticos do DDD cada classe implementa?**
###### <span style="color: light-gray">Acredito que as classes seguem uma linguagem ubíqua, tendo nomemclaturas claras e próximas ao contexto do mundo real. <span>

  
**- Quais os agregados da aplicação, qual sua raiz e que classes os compõem?**
###### <span style="color: light-gray">A classe `Cliente` é um Aggregate root, e a classe endereço é dependente, ou seja, no contexto atual ela depende do cliente para "existir" pois representa o endereço de um cliente. Além disso, a classe `Perfil` também depende do Aggregate root usuário para existir, pois ela representa um tipo de perfil de usuário.<span>

  
**- Quais contextos delimitados existem?**
###### <span style="color: light-gray"> Eu diria que o contexto de produto/pedido, onde são implementados tudo sobre os pedidos e de cliente, responsável por inserir, alterar, ou quaisquer outras movimentações do gênero. No entanto esse contexto ainda é um pouco abstrato para mim, eu até pensei em separar dessa forma quando refatorei o código, mas a ideia do que seria compartilhado (as classes de `Security` por exemplo) e as relações entre as tabelas como o cliente possuir um usuário, que hoje está relacionado só ao contexto de cliente mas posteriormente pode ser um funcionário, são coisas que ainda são bem estranhas de se pensar/delimitar.<span>  
