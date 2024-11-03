 #Sistema de Nota Fiscal

Este é um sistema básico de Nota Fiscal desenvolvido em Java. Ele permite o cadastro e gerenciamento de produtos, clientes, notas fiscais e itens de nota fiscal. O projeto é dividido em diversas camadas para melhor organização e manutenção.

## Funcionalidades

- **Cadastro de Produtos**: Adicionar, atualizar e remover produtos do sistema.
- **Cadastro de Clientes**: Adicionar, atualizar e remover clientes.
- **Cadastro de Notas Fiscais**: Criar novas notas fiscais, associar clientes e adicionar itens.
- **Gerenciamento de Itens da Nota**: Adicionar produtos às notas fiscais com quantidade e valores.
- **Testes de Integração**: Inclui testes de integração para garantir a correta funcionalidade do sistema e a comunicação com o banco de dados.

## Estrutura do Projeto

O projeto é dividido nas seguintes camadas:

- **DAO (Data Access Object)**: Contém as classes responsáveis pela manipulação dos dados no banco (CRUD de cada entidade).
- **Bean**: Define as classes de modelo com os atributos principais de cada entidade.
- **Models**: Classes que representam os modelos de tabelas e `ComboBox` utilizados na interface gráfica.
- **Tests**: Inclui testes de integração que verificam a interação com o banco de dados.

## Requisitos

- **Java**: Versão 8 ou superior.
- **Maven**: Para gerenciamento de dependências.
- **Banco de Dados**: Este projeto utiliza um banco de dados para persistência de dados. Certifique-se de configurá-lo corretamente antes de executar o sistema.
