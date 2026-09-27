# 🍔 SmashPoint - API REST para Gerenciamento de Lanchonete

## 📖 Sobre o Projeto

O SmashPoint é uma API REST desenvolvida em Java com Spring Boot para gerenciamento de uma lanchonete. O sistema permite o cadastro e gerenciamento de clientes, produtos e pedidos, utilizando persistência de dados com PostgreSQL e JPA/Hibernate.

> **Nota de migração:** este projeto era originalmente uma aplicação de terminal (`CommandLineRunner` com menu via `Scanner`). Foi refatorado para expor uma API REST completa, testável via Postman, mantendo a mesma regra de negócio original e adicionando novas regras de domínio (soft delete, janela de reativação, limites de quantidade, etc). As seções abaixo já refletem o estado atual do projeto.

---

## 🚀 Tecnologias Utilizadas

* Java 17
* Spring Boot 3.5
* Spring Web (REST)
* Spring Data JPA
* Spring Validation (Bean Validation)
* Lombok
* Hibernate
* PostgreSQL
* Maven
* Git e GitHub

---

## 🏗️ Arquitetura

O projeto segue uma separação em camadas, com os **services organizados por caso de uso** (uma classe por ação, em vez de uma única classe de serviço por entidade):

```text
controller/     → Endpoints REST (@RestController), um por entidade principal
service/
  ├── cliente/     → CadastrarCliente, BuscarClientePorCpf, ListarClientes
  ├── produto/     → CadastrarProduto, BuscarProdutoPorNome, AtivarProduto,
  │                  DesativarProduto, ListarProdutosAtivos, ListarProdutosInativos
  ├── pedido/      → NovoPedido, AtivarPedido, DesativarPedido,
  │                  ListarPedidosAtivos, ListarPedidosInativos
  └── itempedido/  → NovoItemPedido, RemoverItemPedido, ListarItensPedidos
repository/      → Interfaces Spring Data JPA
model/           → Entidades JPA
dto/
  ├── requests/    → DTOs de entrada (o que a API recebe)
  └── responses/   → DTOs de saída (o que a API devolve), com fromEntity(...)
exception/       → Exceções customizadas + GlobalExceptionHandler (@RestControllerAdvice)
```

Cada classe de service representa um único caso de uso (ex: `CadastrarCliente`, `AtivarPedido`), o que mantém cada uma pequena, fácil de testar isoladamente e com mapeamento quase 1:1 para um endpoint do controller.

---

## 📂 Entidades

### Cliente

| Campo | Tipo | Observações |
|---|---|---|
| id | Long | gerado automaticamente |
| nome | String | **opcional** |
| cpf | String | obrigatório, único |
| dataCadastro | LocalDate | preenchido automaticamente na criação |

### Produto

| Campo | Tipo | Observações |
|---|---|---|
| id | Long | gerado automaticamente |
| nome | String | obrigatório, único |
| descricao | String | obrigatório |
| categoria | Categoria (enum) | LANCHES, SALGADOS, BEBIDAS, SOBREMESAS |
| preco | BigDecimal | obrigatório |
| status | Status (enum) | ATIVADO / DESATIVADO — nasce como `ATIVADO` |

### Pedido

| Campo | Tipo | Observações |
|---|---|---|
| id | Long | gerado automaticamente |
| numeroMesa | int | obrigatório |
| cliente | Cliente | referência ao cliente do pedido |
| status | Status (enum) | ATIVADO / DESATIVADO — nasce como `ATIVADO` |
| dataPedido | LocalDateTime | preenchido automaticamente na criação |
| dataDesativacao | LocalDateTime | preenchido **apenas na primeira desativação** (ver regra de reativação abaixo) |
| itens | List\<ItemPedido> | itens do pedido |
| total | BigDecimal | **calculado**, não persistido (`@Transient`) — soma do total de cada item |

### ItemPedido

| Campo | Tipo | Observações |
|---|---|---|
| id | Long | gerado automaticamente |
| pedido | Pedido | pedido ao qual pertence |
| produto | Produto | produto referenciado |
| quantidade | Integer | entre 1 e 99 |
| precoUnitario | BigDecimal | **snapshot** do preço do produto no momento da criação do item (não muda se o preço do produto mudar depois) |
| total | BigDecimal | calculado: `precoUnitario * quantidade` |

---

## 🔗 Relacionamentos

```text
Cliente 1 ──── N Pedido
Pedido  1 ──── N ItemPedido
Produto 1 ──── N ItemPedido
```

---

## 📏 Regras de Negócio

* **Cliente:** CPF é único no sistema; nome é opcional (permite cadastro rápido só com CPF).
* **Produto:** nome é único; produto nasce ativo.
* **Pedido:**
    * Um cliente não pode ter mais de um pedido **ativo** simultaneamente.
    * Pedido nasce sem itens e sem valor calculado (`total = 0`, derivado da lista de itens vazia).
    * **Soft delete** via `status` (ATIVADO/DESATIVADO) — pedidos não são apagados fisicamente.
    * **Janela de reativação de 24 horas:** um pedido só pode ser reativado até 24h após a **primeira** desativação. O campo `dataDesativacao` é carimbado uma única vez e nunca é limpo ou sobrescrito em desativações subsequentes — ou seja, o prazo de reativação é fixo a partir do primeiro momento em que o pedido saiu do estado ativo.
* **ItemPedido:**
    * Só pode ser adicionado a um pedido **ativo** e com um produto **ativo**.
    * Quantidade deve estar entre 1 e 99.
    * O preço do item é fixado no momento da criação (não reflete alterações futuras no preço do produto).

---

## 🌐 Endpoints da API

### Clientes — `/cliente`

| Verbo | Rota | Descrição |
|---|---|---|
| POST | `/cliente` | Cadastra um novo cliente |
| GET | `/cliente` | Lista todos os clientes |
| GET | `/cliente/{cpf}` | Busca cliente pelo CPF exato |

### Produtos — `/produtos`

| Verbo | Rota | Descrição |
|---|---|---|
| POST | `/produtos` | Cadastra um novo produto |
| GET | `/produtos/{nome}` | Busca produto pelo nome exato |
| GET | `/produtos/ativos` | Lista produtos ativos |
| GET | `/produtos/inativos` | Lista produtos inativos |
| PATCH | `/produtos/{nome}/ativar` | Ativa um produto |
| PATCH | `/produtos/{nome}/desativar` | Desativa um produto |

### Pedidos — `/pedidos`

| Verbo | Rota | Descrição |
|---|---|---|
| POST | `/pedidos` | Cria um novo pedido para um cliente |
| GET | `/pedidos/ativos` | Lista pedidos ativos |
| GET | `/pedidos/inativos` | Lista pedidos desativados **no dia atual** |
| PATCH | `/pedidos/{id}/ativar` | Reativa um pedido (dentro da janela de 24h) |
| PATCH | `/pedidos/{id}/desativar` | Desativa um pedido |

### Itens de Pedido — `/itens`

| Verbo | Rota | Descrição |
|---|---|---|
| POST | `/itens` | Adiciona um item a um pedido |
| GET | `/itens/pedido/{idPedido}` | Lista os itens de um pedido |
| DELETE | `/itens/{id}` | Remove um item do pedido |

---

## ⚠️ Tratamento de Erros

Toda exceção de negócio é capturada por um `@RestControllerAdvice` central (`GlobalExceptionHandler`), que devolve uma resposta JSON padronizada em vez de uma stack trace:

```json
{
  "status": 404,
  "error": "Não encontrado",
  "message": "Cliente não encontrado",
  "timestamp": "2026-09-26T12:00:00"
}
```

| Status HTTP | Quando ocorre |
|---|---|
| 404 Not Found | Cliente/Produto/Pedido/Item não encontrado, ou listagem vazia |
| 409 Conflict | CPF/nome já existente, entidade já está no status solicitado (já ativo/já inativo), cliente já com pedido ativo, pedido/produto inativo bloqueando uma ação, prazo de reativação expirado |
| 400 Bad Request | Quantidade fora do limite (1–99), ou falha de validação de campo (`@Valid` nos DTOs — ex: CPF inválido, campo obrigatório ausente) |
| 500 Internal Server Error | Qualquer erro não mapeado (rede de segurança) |

---

## 🛠️ Configuração do Banco de Dados

Criar um banco PostgreSQL:

```sql
CREATE DATABASE lanchonete;
```

Configurar o arquivo `application.properties`:

```properties
spring.application.name=smashpoint
spring.datasource.url=jdbc:postgresql://localhost:5432/lanchonete
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
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

A aplicação sobe por padrão em `http://localhost:8080`. Importe as rotas listadas acima numa collection do Postman para testar cada fluxo (cadastrar cliente → cadastrar produto → criar pedido → adicionar item → listar → desativar/ativar).

---

## 👨‍💻 Autor

Desenvolvido por Guilherme Paiva como projeto de estudo para aprofundamento em Java, Spring Boot, JPA/Hibernate, PostgreSQL e design de APIs REST.