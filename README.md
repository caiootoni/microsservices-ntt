# 🚀 Sistema de Microserviços - NTT Challenge

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.0-brightgreen.svg)](https://spring.io/projects/spring-cloud)

Sistema completo de microserviços desenvolvido com Spring Boot e Spring Cloud, implementando Service Discovery, API Gateway e comunicação entre serviços.

## 🏗️ Arquitetura

Browser → API Gateway → Service Discovery
├── Product Catalog Service
└── Order Simulator Service



📋 Requisitos Implementados


✅ Funcionalidades Obrigatórias

Dois microsserviços independentes (Product Catalog + Order Simulator)

Spring Boot em todos os serviços

Spring Cloud Eureka como Service Discovery

Spring Cloud Gateway como API Gateway

APIs RESTful com boas práticas

Persistência via H2 Database

Endpoints acessíveis exclusivamente via Gateway


🧩 Funcionalidades Extras

Autenticação simplificada com token fixo

Filtro de autenticação no Gateway

Comunicação entre serviços com Feign Client

Health checks e monitoramento via Eureka Dashboard


## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Cloud 2023.0.0**
- **Spring Cloud Gateway**
- **Netflix Eureka**
- **Spring Data JPA**
- **H2 Database**
- **Feign Client**
- **Maven**

## 🚀 Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+
- Git

### Execução Local

1. **Clone o repositório:**
   
   git clone https://github.com/caiootoni/microservices-ntt.git
   cd microservices-ntt

2. Execute os serviços na ordem:

Terminal 1 - Service Discovery:


cd service-discovery


mvn spring-boot:run


Terminal 2 - API Gateway:


cd api-gateway


mvn spring-boot:run


Terminal 3 - Product Catalog:


cd product-catalog


mvn spring-boot:run


Terminal 4 - Order Simulator:


cd order-simulator


mvn spring-boot:run


🌐 Portas dos Serviços

| Serviço           | Porta    | Descrição                  |
| ----------------- | -------- | -------------------------- |
| Service Discovery | **8761** | Registro de microsserviços |
| API Gateway       | **8700** | Ponto de entrada central   |
| Product Catalog   | **8100** | Catálogo de produtos       |
| Order Simulator   | **8200** | Simulação de pedidos       |


📡 Endpoints Principais

| Método | Endpoint         | Descrição               |
| ------ | ---------------- | ----------------------- |
| GET    | `/produtos`      | Lista todos os produtos |
| GET    | `/produtos/{id}` | Busca produto por ID    |
| POST   | `/produtos`      | Cria novo produto       |
| PUT    | `/produtos/{id}` | Atualiza produto        |
| DELETE | `/produtos/{id}` | Remove produto          |


📦 Order Simulator Service

| Método | Endpoint                        | Descrição                            |
| ------ | ------------------------------- | ------------------------------------ |
| GET    | `/pedidos/produtos-disponiveis` | Lista produtos disponíveis           |
| POST   | `/pedidos/simular`              | Simula criação de pedido             |
| GET    | `/pedidos/{id}/simular`         | Simula pedido com produto específico |

🔐 Autenticação

Todos os endpoints requerem autenticação via token no header:

Authorization: Bearer supersecrettoken123

💡 Exemplo (via cURL ou Postman)

curl -X GET http://localhost:8700/produtos \

-H "Authorization: Bearer supersecrettoken123"

🗂️ Estrutura do Projeto

microservices-ntt/
├── service-discovery/      # Eureka Server - Porta 8761
├── api-gateway/            # Spring Cloud Gateway - Porta 8700
├── product-catalog/        # Catálogo de Produtos - Porta 8100
├── order-simulator/        # Simulação de Pedidos - Porta 8200
└── README.md

🧪 Testando a Aplicação

✅Verifique o Service Discovery:
http://localhost:8761

🧩 Criar um novo produto:

curl -X POST http://localhost:8700/produtos \

-H "Authorization: Bearer supersecrettoken123" \

-H "Content-Type: application/json" \

-d '{

"nome": "Notebook Dell",

"descricao": "Notebook Dell i7 16GB RAM",

"preco": 4500.00

}'

🧾 Simular pedido:

curl -X POST http://localhost:8700/pedidos/simular \

-H "Authorization: Bearer supersecrettoken123" \

-H "Content-Type: application/json" \

-d '[{
"produtoId": 1,
"quantidade": 2
}]'

📊 Monitoramento e Acesso

| Serviço                          | URL                                                                  | Observação                                                             |
| -------------------------------- | -------------------------------------------------------------------- | ---------------------------------------------------------------------- |
| **Eureka Dashboard**             | [http://localhost:8761](http://localhost:8761)                       | Visualiza status dos microsserviços                                    |
| **H2 Console (Product Catalog)** | [http://localhost:8100/h2-console](http://localhost:8100/h2-console) | JDBC URL: `jdbc:h2:mem:produtodb`<br>Usuário: `sa`<br>Senha: *(vazio)* |


👨‍💻 Desenvolvido por
[Caio Otoni] - [https://www.linkedin.com/in/caio-otoni/]
