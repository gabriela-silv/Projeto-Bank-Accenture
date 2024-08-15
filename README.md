**README**
================

**Projeto Bank Accenture**
---------------------------

Este projeto é uma aplicação Java com Spring Boot e MySQL, projetada para gerenciar clientes, contas correntes, agências e transações bancárias. Ele utiliza várias tecnologias e bibliotecas, incluindo Lombok para geração de métodos e construtores, Swagger para documentação da API, ApplicationEventPublisher para eventos de transações e JUnit para testes unitários.

**Descrição**
-------------

O projeto Bank Accenture é uma aplicação que permite:

* Cadastro de clientes
* Cadastro de contas bancárias
* Realização de transações (depósitos, saques e transferências)
* Consulta de extratos
* Consulta de contas por CPF


## Estrutura do Projeto

O projeto segue a estrutura padrão do Spring Boot:

```markdown
src/
│
├── main/
│ ├── java/com/bankaccenture/Projeto_Bank_Accenture/
│ │ ├── commons/
│ │ ├── config/
│ │ ├── controller/
│ │ ├── enums/
│ │ ├── exception/
│ │ ├── model/
│ │ │ ├── Agencia.java
│ │ │ ├── Cliente.java
│ │ │ ├── ContaCorrente.java
│ │ │ └── Extrato.java
│ │ ├── repository/
│ │ └── service/
│ │ ├── AgenciaService.java
│ │ ├── ClienteService.java
│ │ ├── ContaCorrenteService.java
│ │ └── ExtratoService.java
│ └── resources/
│ └── application.properties
│
└── test/
├── java/com/bankaccenture/Projeto_Bank_Accenture/
│ ├── AgenciaTest.java
│ ├── ClienteTest.java
│ ├── ContaCorrenteTest.java
│ └── ExtratoTest.java
└── resources/
```


## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação utilizada.
- **Spring Boot**: Framework para criar aplicações Java.
- **MySQL**: Banco de dados relacional utilizado.
- **Lombok**: Biblioteca para reduzir a estrutura de código.
- **Spring Data JPA**: Framework para persistência de dados.
- **Swagger**: Ferramenta para documentação da API.
- **JUnit**: Framework para testes unitários.



**Funcionalidades**
-------------------

* **Cadastro de Clientes**: permite cadastrar novos clientes com informações como nome, CPF, telefone e endereço.
* **Cadastro de Contas Bancárias**: permite cadastrar novas contas bancárias para os clientes, com informações como número da conta, agência e saldo.
* **Realização de Transações**: permite realizar transações como depósitos, saques e transferências entre contas.
* **Consulta de Extratos**: permite consultar o extrato de uma conta bancária, mostrando todas as transações realizadas.
* **Consulta de Contas por CPF**: permite consultar as contas bancárias de um cliente por meio do seu CPF.


## Estrutura de Classes
-------------------

### Cliente
- **Cliente.java:** Classe que representa o modelo de cliente.
- **ClienteService.java:** Classe de serviço que gerencia as operações de cliente (listar, cadastrar, atualizar, deletar).

### Conta Corrente
- **ContaCorrente.java:** Classe que representa o modelo de conta corrente.
- **ContaCorrenteService.java:** Classe de serviço que gerencia as operações de conta corrente (listar, cadastrar, atualizar, deletar, depositar, sacar, transferir).

### Agência
- **Agencia.java:** Classe que representa o modelo de agência.
- **AgenciaService.java:** Classe de serviço que gerencia as operações de agência (listar, cadastrar, atualizar, deletar).

### Extrato
- **Extrato.java:** Classe que representa o modelo de extrato.
- **ExtratoService.java:** Classe de serviço que gerencia as operações de extrato (listar, cadastrar, atualizar, deletar).

## Eventos e Transações

Utiliza ApplicationEventPublisher para criar eventos de transações e EventListener para ouvir esses eventos e gerar registros automáticos no extrato.

## Testes Unitários

Os testes unitários são realizados com JUnit, e estão localizados no pacote src/test/java/com/bankaccenture/Projeto_Bank_Accenture/.

**Como Executar o Projeto**
---------------------------

1. Clone o repositório: `git clone https://github.com/gabriela-silv/Projeto-Bank-Accenture.git`
2. Instale as dependências: `mvn install`
3. Configure o banco de dados MySQL:
	* Crie um banco de dados com o nome `bank_accenture`
	* Crie um usuário com permissão de leitura e escrita no banco de dados
4. Configure as propriedades do projeto:
	* Edite o arquivo `application.properties` e informe as credenciais do banco de dados
5. Execute o projeto: `mvn spring-boot:run`

**API**
------

A API do projeto é documentada utilizando o Swagger. Para acessar a documentação, acesse `http://localhost:8080/swagger-ui.html`

**Endpoints**
------------

* **Clientes**:
	+ `GET /clientes`: lista todos os clientes
	+ `POST /clientes`: cria um novo cliente
	+ `GET /clientes/{id}`: busca um cliente por ID
	+ `PUT /clientes/{id}`: atualiza um cliente
	+ `DELETE /clientes/{id}`: exclui um cliente
* **Contas Bancárias**:
	+ `GET /contas`: lista todas as contas bancárias
	+ `POST /contas`: cria uma nova conta bancária
	+ `GET /contas/{id}`: busca uma conta bancária por ID
	+ `PUT /contas/{id}`: atualiza uma conta bancária
	+ `DELETE /contas/{id}`: exclui uma conta bancária
* **Agências**:
	+ `GET /agencias`: lista todas as agências
	+ `POST /agencias`: cria uma nova agência
	+ `GET /agencias/{id}`: busca uma agência por ID
	+ `PUT /agencias/{id}`: atualiza uma agência
	+ `DELETE /agencias/{id}`: exclui uma agência
* **Extratos**:
	+ `GET /extratos`: lista todos os extratos
	+ `POST /extratos`: cria um novo extrato
	+ `GET /extratos/{id}`: busca um extrato por ID
	+ `PUT /extratos/{id}`: atualiza um extrato
	+ `DELETE /extratos/{id}`: exclui um extrato


**Autores**
---------

### Autor 1: Maria Gabriela

* GitHub: [https://github.com/gabriela-silv](https://github.com/gabriela-silv)
* Contribuições: Desenvolvimento da API e desenvolvimebnto e implementação do front-end

### Autor 2: Messias Ramon

* GitHub: [https://github.com/MessiasRamom](https://github.com/MessiasRamom)
* Contribuições: Desenvolvimento da lógica de negócio, documentação e implementação do sistema

### Autor 3: Michele Santos

* GitHub: [https://github.com/michelesm](https://github.com/michelesm)
* Contribuições: Desenvolvimento do back-end e testes unitários


**Agradecimentos**
------------

A Accenture por realizar a Academia Java
