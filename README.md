# Sistema de Gestão de Processos e Réus

Este projeto é um sistema de gestão de processos judiciais, onde é possível cadastrar processos e réus, realizar associações entre eles e executar operações de CRUD. A aplicação foi desenvolvida em Java 23 com o framework Spring Boot, e inclui testes unitários com JUnit 4.

## Estrutura do Projeto

O projeto possui os seguintes pacotes principais:
- **controller**: Define os controladores que lidam com as requisições HTTP.
- **dto**: Contém os Data Transfer Objects (DTOs) para transferir dados entre a aplicação e o banco de dados.
- **model**: Define as entidades de Processos e Réus.
- **repository**: Interfaces de repositórios que gerenciam a persistência de dados no banco de dados.
- **service**: Classes de serviço que contêm a lógica de negócios.

## Endpoints

### Endpoints de Processos

| Método   | Endpoint                      | Descrição                                     | Corpo da Requisição (JSON)                               |
|----------|--------------------------------|-----------------------------------------------|----------------------------------------------------------|
| POST     | `/api/processos`               | Cria um novo processo                         | `{ "numero": "string", "reusIds": [long, long] }`        |
| GET      | `/api/processos`               | Lista todos os processos                      | N/A                                                      |
| DELETE   | `/api/processos/{id}`          | Exclui um processo pelo ID                    | N/A                                                      |
| PUT      | `/api/processos/{processoId}/reus/{reuId}` | Adiciona um réu a um processo específico      | N/A                                                      |

### Endpoints de Réus

| Método   | Endpoint                      | Descrição                                     | Corpo da Requisição (JSON)                               |
|----------|--------------------------------|-----------------------------------------------|----------------------------------------------------------|
| POST     | `/api/reus`                    | Cria um novo réu                              | `{ "nome": "string", "cpf": "string", "telefone": "string" }` |
| GET      | `/api/reus`                    | Lista todos os réus                           | N/A                                                      |
| GET      | `/api/reus/{id}`               | Busca um réu por ID                           | N/A                                                      |
| PUT      | `/api/reus/{id}`               | Atualiza as informações de um réu             | `{ "nome": "string", "cpf": "string", "telefone": "string" }` |
| DELETE   | `/api/reus/{id}`               | Exclui um réu pelo ID                         | N/A                                                      |

## Testes Unitários

Os testes unitários foram criados para garantir a integridade do sistema e cobrir os principais casos de uso para as operações de CRUD de Processos e Réus. Os testes foram desenvolvidos utilizando JUnit 4 e MockMvc para simular as requisições HTTP e validar as respostas da API.

### Estrutura de Testes

1. **Testes dos Endpoints do Processo**:
   - `testSalvarProcesso`: Valida a criação de um novo processo e verifica o retorno do ID e número do processo salvo.
   - `testListarProcessos`: Testa a listagem de processos, verificando se os dados retornados estão corretos.
   - `testExcluirProcesso`: Garante que o processo é excluído corretamente com o status esperado.
   - `testAdicionarReu`: Valida a adição de um réu a um processo, garantindo que a associação é realizada.

2. **Testes dos Endpoints do Réu**:
   - `testSalvarReu`: Verifica a criação de um novo réu, garantindo que os dados do réu salvo estão corretos.
   - `testListarReus`: Valida a listagem de réus, conferindo que a resposta inclui os dados corretos.
   - `testAtualizarReu`: Valida a atualização das informações de um réu, garantindo que o nome, CPF e telefone foram atualizados.
   - `testDeletarReu`: Verifica a exclusão de um réu e garante o retorno do status adequado.

3. **Testes de Serviço**:
   - `testSalvarProcesso`: Confirma a persistência de um novo processo.
   - `testSalvarProcessoComNumeroExistente`: Verifica que um erro é lançado ao tentar salvar um processo com um número duplicado.
   - `testListarProcessos`: Testa a listagem dos processos no banco de dados.
   - `testExcluirProcesso`: Verifica a exclusão do processo com o ID especificado.

Os testes são executados automaticamente durante a pipeline de CI, garantindo a robustez e a funcionalidade do sistema a cada nova atualização.

## Estrutura da Base de Dados

Para inicializar o banco de dados, execute o script SQL abaixo. Ele cria o banco de dados `processos` e as tabelas necessárias para os Processos e Réus.

```sql
CREATE DATABASE processos;
