# My Recipe Book

## Descrição
Este projecto utiliza duas APIs *my_recipe_book* e *sendGrid*

### my_recipe-book
O "My Recipe Book" é um projeto que permite aos usuários registrar, atualizar e excluir receitas. Além disso, oferece funcionalidade de autenticação para os usuários.

### sendGrid
SendGrid para enviar e-mails automatico.

#### Configuração

As configurações para o SendGrid são especificadas no arquivo de propriedades da aplicação (application.properties).



spring.sendgrid.api-key=Criar Chave de API na pagina endGrid.

spring.sendgrid.from-email=seu email confugurado na página SendGrid

spring.sendgrid.text-email=Seu texto de e-mail padrão.

spring.sendgrid.subject-email=Assunto padrão do e-mail.

spring.sendgrid.title-email=Título padrão do e-mail.


## Configuração do Docker Compose

Docker Compose para gerenciar os serviços necessários. O arquivo docker-compose.yml contém as configurações para os serviços de banco de dados MySQL e um ambiente simulado AWS LocalStack para testes.

### Serviços

- *my_recipe-book*: Container para a aplicação principal do My Recipe Book, usando MySQL.
- *localstack*: Container com LocalStack para simulação de serviços AWS SQS.

## Estrutura do Projeto

- *academy.mindswap.my_recipe_book.controllers*: Controladores da aplicação.
- *academy.mindswap.my_recipe_book.dtos*: DTOs para comunicação.
- *academy.mindswap.my_recipe_book.model*: Entidades do modelo.
- *academy.mindswap.my_recipe_book.repository*: Repositório para operações relacionadas a Banco de dados.
- *academy.mindswap.my_recipe_book.service*: Lógica de negócio da aplicação.
- *academy.mindswap.my_recipe_book.infra*:  Infraestrutura e configurações de segurança.

## Endpoints da API

### POST /my_recipe_book/recipe/register

Registra uma nova receita.

### PATCH /my_recipe_book/recipe/{id}

Atualiza uma receita existente com base no ID.

### DELETE /my_recipe_book/recipe/{id}

Exclui uma receita com base no ID.

### GET /my_recipe_book

Recupera uma lista de usuários.

### GET /my_recipe_book/login

Autentica um usuário com base nas credenciais fornecidas.

### POST /my_recipe_book/register

Registra um novo usuário.

## Como Executar

1. Clone este repositório.

2. Certifique-se de ter o Docker e o Docker Compose instalados.

3. Execute docker-compose up --build na raiz do projeto para iniciar os serviços.

4. Acesse a aplicação em http://localhost:PORTA/endpoint

---------------------------------------------------
