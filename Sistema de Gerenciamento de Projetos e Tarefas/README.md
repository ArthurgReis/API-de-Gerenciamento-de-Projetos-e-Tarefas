# Sistema de Gerenciamento de Projetos e Tarefas

API REST desenvolvida com Spring Boot para gerenciar projetos e suas respectivas tarefas, permitindo organização e controle de atividades.

## 🚀 Tecnologias Utilizadas

- **Java 17** (ou sua versão)
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database** (ou PostgreSQL/MySQL)
- **Lombok**
- **Maven**

## 📋 Pré-requisitos

Antes de começar, você precisa ter instalado em sua máquina:

- [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.6+](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

## 🔧 Instalação e Configuração

### 1. Clone o repositório

git clone https://github.com/seu-usuario/arthuratividade2.git
cd arthuratividade2

text

### 2. Configure o banco de dados

Edite o arquivo `src/main/resources/application.properties`:

**Opção A: H2 Database (banco em memória - mais simples)**
spring.application.name=demo

H2 Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

H2 Console (acesse em http://localhost:8080/h2-console)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

text

**Opção B: MySQL**
spring.application.name=demo

MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/projeto_db
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

text

### 3. Compile o projeto

mvn clean install

text

### 4. Execute a aplicação

mvn spring-boot:run

text

Ou execute a classe principal `DemoApplication.java` pela sua IDE.

A aplicação estará disponível em: `http://localhost:8080`

## 📚 Documentação da API

### **Projetos**

#### Criar Projeto
POST /trabalho/projeto/cadastrar
Content-Type: application/json

{
"nome": "Projeto Spring Boot",
"descricao": "API REST para gerenciamento de tarefas"
}

text

#### Listar Todos os Projetos
GET /trabalho/projeto

text

#### Buscar Projeto por ID
GET /trabalho/projeto/{id}

text

#### Atualizar Projeto
PUT /trabalho/projeto/atualizar/{id}
Content-Type: application/json

{
"nome": "Projeto Atualizado",
"descricao": "Nova descrição"
}

text

#### Deletar Projeto
DELETE /trabalho/projeto/deletar/{id}

text

---

### **Tarefas**

#### Criar Tarefa
POST /trabalho/projeto/{idProjeto}/tarefa
Content-Type: application/json

{
"descricao": "Implementar endpoint de login",
"status": "PENDENTE",
"dataLimite": "2025-11-30T23:59:59"
}

text

#### Listar Tarefas de um Projeto
GET /trabalho/projeto/{idProjeto}/tarefa

text

#### Buscar Tarefa Específica
GET /trabalho/projeto/{idProjeto}/tarefa/{idTarefa}

text

#### Atualizar Tarefa
PUT /trabalho/projeto/{idProjeto}/tarefa/atualizar/{idTarefa}
Content-Type: application/json

{
"descricao": "Implementar endpoint de login com JWT",
"status": "EM_ANDAMENTO",
"dataLimite": "2025-12-01T23:59:59"
}

text

#### Deletar Tarefa
DELETE /trabalho/projeto/{idProjeto}/tarefa/deletar/{idTarefa}

text

---

### **Status das Tarefas**

As tarefas podem ter os seguintes status:
- `PENDENTE` - Tarefa ainda não iniciada
- `EM_ANDAMENTO` - Tarefa em execução
- `CONCLUIDA` - Tarefa finalizada

## 🧪 Testando a API

### Usando cURL

**Criar um projeto:**
curl -X POST http://localhost:8080/trabalho/projeto/cadastrar
-H "Content-Type: application/json"
-d '{
"nome": "Meu Projeto",
"descricao": "Descrição do projeto"
}'

text

**Criar uma tarefa:**
curl -X POST http://localhost:8080/trabalho/projeto/1/tarefa
-H "Content-Type: application/json"
-d '{
"descricao": "Minha primeira tarefa",
"status": "PENDENTE",
"dataLimite": "2025-12-31T23:59:59"
}'

text

### Usando Postman ou Insomnia

1. Importe a coleção de requisições (se disponível)
2. Configure a base URL: `http://localhost:8080`
3. Execute as requisições seguindo a documentação acima

## 📁 Estrutura do Projeto

src/main/java/br/com/arthuratividade2/demo/
├── controller/ # Controladores REST
│ ├── ProjetoController.java
│ └── TarefaController.java
├── model/ # Entidades JPA
│ ├── Projeto.java
│ ├── Tarefa.java
│ └── StatusTarefa.java
├── repository/ # Repositórios JPA
│ ├── ProjetoRepository.java
│ └── TarefaRepository.java
├── service/ # Lógica de negócio
│ ├── ProjetoService.java
│ └── TarefaService.java
└── DemoApplication.java # Classe principal

text

## 🐛 Resolução de Problemas

### Erro de conexão com o banco de dados
- Verifique se as credenciais estão corretas no `application.properties`
- Certifique-se de que o banco de dados está rodando (se não for H2)

### Porta 8080 já em uso
Adicione ao `application.properties`:
server.port=8081

text

### Erro ao compilar
Execute:
mvn clean install -U

text

## 👨‍💻 Autor

**Seu Nome**
- GitHub: [@seu-usuario](https://github.com/seu-usuario)
- LinkedIn: [Seu Perfil](https://linkedin.com/in/seu-perfil)

## 📝 Licença

Este projeto foi desenvolvido como atividade acadêmica.

## 🎯 Próximas Melhorias

- [ ] Implementar autenticação e autorização (Spring Security)
- [ ] Adicionar validações customizadas
- [ ] Implementar paginação nas listagens
- [ ] Criar testes unitários e de integração
- [ ] Adicionar documentação Swagger/OpenAPI
- [ ] Implementar logs estruturados
Arquivo .gitignore
Crie um arquivo .gitignore na raiz do projeto:

text
# Compiled class file
*.class

# Log file
*.log

# BlueJ files
*.ctxt

# Mobile Tools for Java (J2ME)
.mtj.tmp/

# Package Files
*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar

# Virtual machine crash logs
hs_err_pid*
replay_pid*

# Maven
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties
dependency-reduced-pom.xml
buildNumber.properties
.mvn/timing.properties
.mvn/wrapper/maven-wrapper.jar

# Eclipse
.classpath
.project
.settings/
bin/

# IntelliJ IDEA
.idea/
*.iml
*.iws
*.ipr
out/

# VS Code
.vscode/

# OS
.DS_Store
Thumbs.db

# Spring Boot
application-dev.properties
application-prod.properties