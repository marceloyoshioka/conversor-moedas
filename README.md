# 💱 Conversor de Moedas Dinâmico

Uma API REST desenvolvida com **Spring Boot** que realiza a conversão de moedas em tempo real integrando-se com a **AwesomeAPI**. O projeto implementa as melhores práticas de mercado para aritmética financeira e um ecossistema robusto de tratamento de exceções.

---

## 🚀 Principais Funcionalidades Implementadas

Ao longo do ciclo de desenvolvimento, o projeto evoluiu através de branches estruturadas para garantir a qualidade do código e a confiabilidade dos cálculos:

- **Conversão Dinâmica:** Os endpoints aceitam qualquer par de moedas suportado pela API externa (ex.: `USD`, `BRL`, `EUR`).
- **Precisão Financeira de Alta Fidelidade:** Substituição completa do tipo `Double` por `BigDecimal` com **4 casas decimais**, utilizando o padrão **`RoundingMode.HALF_EVEN`** (Arredondamento Bancário) exigido pelo Banco Central do Brasil.
- **Tratamento Global de Exceções:** Uso de `@RestControllerAdvice` para interceptar entradas malformadas (como textos em campos numéricos ou códigos de moedas inválidos), devolvendo respostas JSON limpas e profissionais com os códigos de status HTTP corretos (`400`, `503`).
- **Injeção de Dependências e Clean Code:** Arquitetura refatorada para eliminar o acoplamento rígido, utilizando corretamente o container IoC do Spring.

---

## 🛠️ Tecnologias & Ferramentas

- Java 17
- Spring Boot 3.x
- Spring Web (API REST)
- Jackson (Processamento polimórfico de JSON via `JsonNode`)
- Maven (Gerenciamento de Dependências)
- JUnit 5 (Testes Unitários)
- STS (Spring Tool Suite) / Eclipse

---

## ⚙️ Como Executar o Projeto Localmente

### Pré-requisitos

Certifique-se de possuir instalado:

- Java 17 ou superior
- Maven 3.x ou superior
- Git

### 1. Clonar o repositório

```bash
git clone https://github.com/SEU_USUARIO/conversor-moedas.git
cd conversor-moedas
```

### 2. Compilar o projeto

```bash
mvn clean install
```

### 3. Executar a aplicação

```bash
mvn spring-boot:run
```

O servidor será iniciado, por padrão, na porta **8080**.

---

# 🔌 Documentação da API

## Converter Moedas

Realiza a conversão entre duas moedas utilizando a cotação mais recente disponibilizada pela AwesomeAPI.

### Endpoint

```http
GET /converter
```

### Parâmetros

| Nome | Tipo | Obrigatório | Descrição |
|------|------|-------------|-----------|
| `daMoeda` | String | Sim | Código da moeda de origem (ex.: USD, EUR, BRL) |
| `paraMoeda` | String | Sim | Código da moeda de destino |
| `valor` | BigDecimal | Sim | Valor a ser convertido |

### Exemplo de requisição

```http
GET http://localhost:8080/converter?daMoeda=EUR&paraMoeda=USD&valor=100
```

### Resposta de sucesso (200 OK)

```json
{
  "valorOriginal": 100.0000,
  "moedaOrigem": "EUR",
  "moedaDestino": "USD",
  "taxaCambio": 1.1431,
  "valorConvertido": 114.3080
}
```

---

## Exemplo de erro tratado

### Requisição

```http
GET http://localhost:8080/converter?daMoeda=BRL&paraMoeda=USD&valor=xxx
```

### Resposta (400 Bad Request)

```json
{
  "message": "O parâmetro 'valor' deve ser um número válido. O valor enviado 'xxx' é inválido.",
  "timestamp": "2026-07-10T16:05:00",
  "statusCode": 400
}
```

---

## 🧪 Executando a Suíte de Testes

O projeto conta com testes unitários adaptados para validação das operações com `BigDecimal` e margens de segurança.

Para executá-los:

```bash
mvn test
```

---

## 📂 Estrutura do Projeto

```text
src
├── controller
├── service
├── dto
├── exception
├── config
└── ConversorMoedasApplication
```

---

## 👨‍💻 Autor

**Peterson Marcelo Santos Yoshioka**

Analista de TI | Engenheiro da Computação

Desenvolvedor Backend Java | Spring Boot | APIs REST
