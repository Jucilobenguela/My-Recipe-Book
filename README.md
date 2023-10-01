[My Recipe Book](https://github.com/Jucilobenguela/My-Recipe-Book)

## Descrição
Este projecto utiliza duas APIs *my_recipe_book* e *sendGrid*

[my_recipe_book](https://github.com/Jucilobenguela/My-Recipe-Book/tree/main/my_recipe_book)
O "My Recipe Book" é um projeto que permite aos usuários registrar, atualizar e excluir, buscar receitas. Além disso, oferece funcionalidade de registar, autenticação para os usuários, atualização (desde o usuário até a senha) e exclusão.


[sendGrid](https://github.com/Jucilobenguela/My-Recipe-Book/tree/main/sendgrid)
SendGrid para enviar e-mails automatico, quando é feito o regista, enviando uma mensagem de boas vindas

#### Configuração

As configurações para o SendGrid são especificadas no arquivo de propriedades da aplicação (application.properties).

## Configuração do Docker Compose

Docker Compose para gerenciar os serviços necessários. O arquivo docker-compose.yml contém as configurações para os serviços de banco de dados MySQL e um ambiente simulado AWS LocalStack para testes.

### Serviços
- *my_recipe-book*: Container para a aplicação principal do My Recipe Book, usando MySQL.
- *localstack*: Container com LocalStack para simulação de serviços AWS SQS.

## Estrutura do Projeto

[academy.mindswap.my_recipe_book.controllers*: Controladores da aplicação.](https://github.com/Jucilobenguela/My-Recipe-Book/tree/main/my_recipe_book/src/main/java/academy/mindswap/my_recipe_book/controllers)
[academy.mindswap.my_recipe_book.dtos*: DTOs para comunicação.](https://github.com/Jucilobenguela/My-Recipe-Book/tree/main/my_recipe_book/src/main/java/academy/mindswap/my_recipe_book/dtos)
[*academy.mindswap.my_recipe_book.model*: Entidades do modelo.](https://github.com/Jucilobenguela/My-Recipe-Book/tree/main/my_recipe_book/src/main/java/academy/mindswap/my_recipe_book/model/entity)
[academy.mindswap.my_recipe_book.repository: Repositório para operações relacionadas a Banco de dados.](https://github.com/Jucilobenguela/My-Recipe-Book/tree/main/my_recipe_book/src/main/java/academy/mindswap/my_recipe_book/repository)
[academy.mindswap.my_recipe_book.service*: Lógica de negócio da aplicação.](https://github.com/Jucilobenguela/My-Recipe-Book/tree/main/my_recipe_book/src/main/java/academy/mindswap/my_recipe_book/service)
[academy.mindswap.my_recipe_book.infra*:  Infraestrutura.](https://github.com/Jucilobenguela/My-Recipe-Book/tree/main/my_recipe_book/src/main/java/academy/mindswap/my_recipe_book/infra)
[academy.mindswap.my_recipe_book.configuration*:  Configuracão.](https://github.com/Jucilobenguela/My-Recipe-Book/tree/main/my_recipe_book/src/main/java/academy/mindswap/my_recipe_book/configuration)
[academy.mindswap.my_recipe_book.enums*: enums](https://github.com/Jucilobenguela/My-Recipe-Book/tree/main/my_recipe_book/src/main/java/academy/mindswap/my_recipe_book/enums)
[academy.mindswap.my_recipe_book.converts*:  convertedor.](https://github.com/Jucilobenguela/My-Recipe-Book/tree/main/my_recipe_book/src/main/java/academy/mindswap/my_recipe_book/converts)
    

## Endpoints da API

### POST /my_recipe_book/recipe/register

Registra uma nova receita.

### Post /my_recipe_book/recipe/{id}

Atualiza uma receita existente com base no ID.

### DELETE /my_recipe_book/recipe/{id}

Exclui uma receita com base no ID.

### GET /my_recipe_book

Recupera uma lista de usuários.

### GET /my_recipe_book/user/login

Autentica um usuário com base nas credenciais fornecidas.

### POST /my_recipe_book/user/register

Regista um novo usuário.

### Delete /my_recipe_book/user/{id}
Apaga um usuario fornecendo o id

### POST /my_recipe_book/user/update/{id}
Modifica os dados do usuario, nome e email

### POST /my_recipe_book/user/updatePassword/{id}
Modifica a senha do usuario



## Como Executar

1. Clone este repositório.
   
3. Certifique-se de ter o Docker e o Docker Compose e aws instalados.

4. Execute docker-compose up --build na raiz do projeto para iniciar os serviços.

5. Acesse a aplicação em http://localhost:PORTA/endpoint

---------------------------------------------------
