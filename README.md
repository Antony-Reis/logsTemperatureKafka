# Logs Temperature Kafka

Projeto de estudo desenvolvido com **Java 21 e Spring Boot** para simular a coleta de temperaturas, transmitir as leituras por meio do **Apache Kafka** e armazenar os registros no **MongoDB** e no **PostgreSQL**, conforme uma regra simples de criticidade.

## Funcionalidades

- Simula leituras de temperatura com valores aleatórios entre 0 e 100.
- Gera uma leitura a cada 2 segundos e publica o evento no tópico Kafka `temperature`.
- Consome os eventos do Kafka e salva todas as leituras no MongoDB.
- Considera crítica uma leitura com temperatura **maior ou igual a 85**.
- Salva as leituras críticas tanto no MongoDB quanto no PostgreSQL.
- Executa diariamente, à meia-noite no fuso `America/Sao_Paulo`, uma rotina que remove do MongoDB os registros cuja data/hora seja igual ou anterior ao limite de sete dias.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring for Apache Kafka
- Apache Kafka
- Spring Data MongoDB
- Spring Data JPA
- MongoDB
- PostgreSQL
- Docker Compose
- Maven

## Como funciona

1. A classe `Sensor` simula a leitura de temperatura e cria um objeto `RTemperatureDto` com a temperatura e a data/hora da leitura.
2. `TemperatureProducer` envia o evento para o tópico `temperature` do Kafka a cada 2 segundos.
3. `TemperatureService` recebe o evento usando `@KafkaListener` e salva a leitura no MongoDB.
4. Se a temperatura for maior ou igual a 85, a leitura também é salva no PostgreSQL.
5. Uma tarefa agendada remove do MongoDB os registros antigos, diariamente à meia-noite.

O desenho da arquitetura está em [`docs/TemperatureKafka.png`](docs/TemperatureKafka.png).

## Pré-requisitos

- JDK 21 instalado.
- Maven instalado e disponível no terminal (`mvn`).
- Docker e Docker Compose.

## Configuração

Na pasta raiz do projeto, crie um arquivo chamado `.env` usando `.env.example` como modelo. No Windows PowerShell, você pode executar:

```powershell
Copy-Item .env.example .env
```

Confira o conteúdo do `.env` e defina suas próprias credenciais. O modelo contém:

```dotenv
POSTGRES_DB=logsTemperature
POSTGRES_USER=postgres
POSTGRES_PASSWORD=altere_esta_senha
MONGO_DB=logsTemperature
MONGODB_USER=root
MONGODB_PASSWORD=altere_esta_senha
```

## Como executar

1. Abra o terminal na pasta raiz do projeto, onde está o arquivo `compose.yaml`.
2. Inicie Kafka, MongoDB e PostgreSQL:

   ```bash
   docker compose up -d
   ```

3. Inicie a aplicação Spring Boot:

   ```bash
   mvn spring-boot:run
   ```

Para encerrar os contêineres:

```bash
docker compose down
```

As portas usadas pelo Compose são `9092` para Kafka, `27017` para MongoDB e `5432` para PostgreSQL. A configuração da aplicação pressupõe que ela será executada no computador host, e não dentro de um contêiner.

## Onde os dados são armazenados

| Banco de dados | O que é salvo |
| --- | --- |
| MongoDB | Todas as leituras, incluindo a indicação de criticidade. |
| PostgreSQL | Somente as leituras com temperatura maior ou igual a 85. |

## Observação sobre o escopo

Este projeto tem finalidade didática e não disponibiliza uma API REST para consulta das temperaturas. O fluxo principal é executado automaticamente pelo sensor simulado, pelo produtor Kafka e pelo consumidor.

## Uso de inteligência artificial

Este projeto foi desenvolvido para fins de estudo, sem uso de inteligência artificial, com o objetivo de aprender e praticar as tecnologias utilizadas.
