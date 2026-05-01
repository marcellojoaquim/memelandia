# MEMELÂNDIA
### Projeto final EBAC

<P>Este projeto tem como objetivo desmembrar uma aplicação monolítica em microsserviços mantendo as mesmas funcionalidades e acrescentando a funcionalidade Meme do dia. </P>

## Objetivos
- Identificar os domínios presentes na aplicação.
- Criar os serviços necessários as operações de cada domínio, seguindo quando possível, os 12 fatores.
- Melhorar a observabilidade nos novos serviços, Logs, Métricas, etc.

## Requisitos Não-funcionais
- Os endpoints deverão ter logs úteis.
- Todos os serviços deverão gerar pelo menos métricas de acesso aos endpoints.

## Requisitos Funcionais
- O cadastro de usuários deverá conter, nome, email e data de cadastro.
- O cadastro de categorias deverá conter, nome, descricao e data de cadastro.
- A publicação do meme deverá conter, nome, url, categoria, usuario e data de cadastro.

## Tecnologias

- Linguagem: Java 17
- Framework: Spring Boot, Spring Security, Spring Authentication Server, Spring Gateway, Spring Cloud.
- Banco de Dados: PostgreSQL, MongoDB, Redis.
- Mensageria: RabbitMQ
- Ferramentas/ORMs: JPA/Hibernate.
- Infraestrutura: Docker, Docker Compose, Zipkin.
- Documentação: Swagger/OpenAPI, Postman.

## Aquitetura do projeto

<p> Para este projeto esto utlizando o padrão arquitetural CQRS que é um padrão que separa as responsabilidade de leitura e escrita em modelos distintos. Com isto há um ganho de performance e otimização da escalabilidade horizontal. </p>
<p> No caso deste projeto este padrão foi adaptado para que as postagens sejam executadas pelo microsserviço Post (post-server) enquanto que a leitura é executada e está sob a responsabilidade do microsserviço de Feed (feed-server).</p>
