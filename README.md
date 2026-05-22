# 🚀 Accenture

# 📌 Sobre o Projeto

A aplicação permite o gerenciamento de:

* Empresas
* Fornecedores
* Associação de fornecedores às empresas
* Remoção de fornecedores de empresas

O projeto foi construído utilizando conceitos de:

* Clean Architecture
* DDD (Domain-Driven Design)
* SOLID
* REST API
* Versionamento de banco com Flyway
* Git 
---

# 🏗️ Arquitetura do Projeto

O backend foi estruturado seguindo os princípios da **Clean Architecture**, promovendo:

✅ Separação de responsabilidades
✅ Baixo acoplamento
✅ Alta coesão
✅ Facilidade de manutenção
✅ Escalabilidade
✅ Testabilidade

---

# 🛠️ Tecnologias Utilizadas

## 🔙 Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Flyway
* Maven
* MySQL

## 🎨 Frontend

* Angular 21
* Angular Material
* Bootstrap
* RxJS
* TypeScript

---

# 📂 Estrutura do Projeto

## Backend

```bash
src
 ├── domain
 ├── application
 ├── infra
 └── interfaces
```

## Frontend

```bash
src
 ├── app
     ├── components
     ├── services    
     └── paginas
```

---

# ⚙️ Pré-requisitos

Antes de executar o projeto, é necessário possuir instalado:

* Java 17+
* Maven
* Node.js
* Angular CLI
* MySQL

---

# 🗄️ Configuração do Banco de Dados

Criar um banco MySQL e configurar as credenciais da aplicação.

## Credenciais utilizadas

```properties
Usuário: juninho
Senha: juninho11
```

---

# 🔥 Executando o Backend

## 1️⃣ Clone o repositório

```bash
git clone url-api
```

## 2️⃣ Acesse a pasta do projeto

```bash
cd dt-acc-api
```

## 3️⃣ Compile o projeto

```bash
mvn clean install
```

## 4️⃣ Execute a aplicação

```bash
java -jar target/accenture-0.0.1-SNAPSHOT.jar
```

---

# 🎨 Executando o Frontend

## 1️⃣ Clone o repositório

```bash
git clone url-ui
```

## 2️⃣ Acesse a pasta do projeto

```bash
cd dt-acc-ui
```

## 3️⃣ Instale as dependências

```bash
npm install
```

## 4️⃣ Execute a aplicação

```bash
ng serve
```

---

# 🌐 URLs da Aplicação

## Frontend

```bash
http://localhost:4200
```

## Backend

```bash
http://localhost:8080/api/
```

---

# 🔄 Migração de Banco

O projeto utiliza o **Flyway** para versionamento e controle de migrations do banco de dados.

## Localização das migrations

```bash
src/main/resources/db/migration
```

---

# 📋 Funcionalidades

## Empresas

* ✅ Listar empresas
* ✅ Cadastrar empresa
* ✅ Adicionar fornecedor à empresa
* ✅ Remover fornecedor da empresa

## Fornecedores

* ✅ Listar fornecedores
* ✅ Cadastrar fornecedor

---

# 📚 Boas Práticas Aplicadas

* Clean Architecture
* DDD
* SOLID
* DTO Pattern
* Repository Pattern
* Dependency Injection
* Tratamento global de exceções
* Separação de camadas
* RESTful APIs
* Versionamento de banco com Flyway

---

# 👨‍💻 Autor

Desenvolvido por **Ademir Gava Jr**

---

