#language: pt
Funcionalidade: API - Clientes

  Cenario: Registrar um novo cliente
    Quando submeter um novo cliente
    Entao o cliente e registrado com sucesso

  Cenario: Excluir um cliente existente
    Dado que um cliente ja foi cadastrado
    Quando requisitar a exclusao do cliente
    Entao o clente e removido com sucesso

  Cenario: Alterar um cliente existente
    Dado que um cliente ja foi cadastrado
    Quando requisitar a alteracao do cliente
    Entao o cliente e atualizado com sucesso

  Cenario: Buscar um cliente existente
    Dado que um cliente ja foi cadastrado
    Quando requisitar a busca do cliente
    Entao o cliente e exibido com sucesso