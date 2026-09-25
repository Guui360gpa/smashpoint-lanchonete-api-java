# 🍔 SmashPoint - Sistema de Gerenciamento para Lanchonete

## 📖 Sobre o Projeto

O SmashPoint é uma aplicação desenvolvida em Java com Spring Boot para gerenciamento de uma lanchonete. O sistema permite o cadastro e gerenciamento de clientes, produtos e pedidos, utilizando persistência de dados com PostgreSQL e JPA/Hibernate.

O objetivo do projeto é simular o funcionamento básico de uma lanchonete, permitindo registrar clientes, cadastrar produtos disponíveis e criar pedidos relacionando clientes e produtos.

---

## 🚀 Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Git e GitHub

---

## 📂 Estrutura do Projeto

```text
Cliente
   │
   └───< Pedido >─── Produto
            │
            └── ItemPedido
```

### Entidades

#### Cliente

Responsável por armazenar os dados dos clientes cadastrados.

Campos:

* id
* nome
* cpf
* dataCadastro

#### Produto

Responsável por armazenar os produtos vendidos pela lanchonete.

Campos:

* id
* nome
* descricao
* categoria
* preco

#### Pedido

Representa um pedido realizado por um cliente.

Campos:

* id
* cliente
* total
* dataPedido

#### ItemPedido

Representa cada item que compõe um pedido.

Campos:

* id
* pedido
* produto
* quantidade
* precoUnitario

---

## 🔗 Relacionamentos

### Cliente → Pedido

Um cliente pode realizar vários pedidos.

```java
@OneToMany
```

### Pedido → Cliente

Cada pedido pertence a um único cliente.

```java
@ManyToOne
```

### Pedido → ItemPedido

Um pedido pode possuir vários itens.

```java
@OneToMany
```

### ItemPedido → Pedido

Cada item pertence a um único pedido.

```java
@ManyToOne
```

### Produto → ItemPedido

Um produto pode aparecer em diversos pedidos.

```java
@OneToMany
```

### ItemPedido → Produto

Cada item referencia um único produto.

```java
@ManyToOne
```

---

## ⚙️ Funcionalidades

### Clientes

* Cadastrar cliente
* Listar clientes
* Buscar clientes por CPF

### Produtos

* Cadastrar produto
* Listar produtos
* Buscar produtos por nome

### Pedidos

* Realizar Novo Pedido
* Listar pedidos ativos (em andamento)
* Listar pedidos inativos (já pagos)
* Desativar pedido (após pagamento)
* Ativar pedido (reverter)

### Item Pedido

* Realizar Novo Item Pedido
* Listar Itens Pedidos em um Pedido Ativo
* Remover(Sem soft delete) Itens Pedidos do Pedido Ativo

---

## 🔍 Sistema de Busca

O sistema utiliza buscas parciais através do Spring Data JPA.

Exemplo:

Entrada:

```text
Guilherme
```

Resultado:

```text
1 - Guilherme Paiva
2 - Guilherme Almeida
3 - Guilherme Souza
```

Da mesma forma para produtos:

```text
Smash
```

Resultado:

```text
1 - Smash Burger
2 - Smash Bacon
3 - Smash Duplo
```

---

## 🛠️ Configuração do Banco de Dados

Criar um banco PostgreSQL:

```sql
CREATE DATABASE lanchonete;
```

Configurar o arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lanchonete
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
```

---

## ▶️ Executando o Projeto

Clone o repositório:

```bash
git clone <url-do-repositorio>
```

Entre na pasta:

```bash
cd smashpoint
```

Execute:

```bash
mvn spring-boot:run
```

---

## 👨‍💻 Autor

Desenvolvido por Guilherme Paiva como projeto de estudo para aprofundamento em Java, Spring Boot, JPA/Hibernate e PostgreSQL.
