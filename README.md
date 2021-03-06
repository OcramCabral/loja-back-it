# CONFIGURAÇÃO

Cenários Levantados para realização dos testes

--------------------------------------------------- CREATE ------------------------------------------------------------

Cenário 1: Usuário cadastra um produto com dados corretos
Dado que o usuário deseja cadastrar um produto
E informou o "nome"
E informou a "descrição", entre 5 a 50 caracters
E informou a "quantidade", entre 1 e Integer_Max_Value
E informou o "preco_unitario", entre 1 e Long_Max_Value
Quando entrar com essas informaçoes 
Então um novo produto é cadastrado

Cenário 2: Usuário cadastra um produto sem nome
Dado que o usuário deseja cadastrar um produto
E informou a "descrição"
E informou a "quantidade"
E inforou o "preco_unitario"
Quando entrar com essas informaçoes 
Então o produto não é cadastrado 
E deve ser notificado com o erro 400

Cenário 3.0: Usuário cadastra um produto com descrição menor 5 caracters
Dado que o usuário deseja cadastrar um produto
E informou a "nome"
E informou a "descrição" com menos de 5 caracteres
E informou a "quantidade"
E inforou o "preco_unitario"
Quando entrar com essas informaçoes
Então o produto não é cadastrado

Cenário 3.1: Usuário cadastra um produto com descrição igual 5 caracters
Dado que o usuário deseja cadastrar um produto
E informou a "nome"
E informou a "descrição" com menos de 5 caracteres
E informou a "quantidade"
E inforou o "preco_unitario"
Quando entrar com essas informaçoes 
Então o produto é cadastrado

Cenário 3.2: Usuário cadastra um produto com descrição igual 50 caracters
Dado que o usuário deseja cadastrar um produto
E informou a "nome"
E informou a "descrição", igual 50 caracteres
E informou a "quantidade"
E informou o "preco_unitario"
Quando entrar com essas informaçoes 
Então é cadastrado o produto

Cenário 3.3: Usuário cadastra um produto com descrição maior que 50 caracters
Dado que o usuário deseja cadastrar um produto
E informou a "nome"
E informou a "descrição", maior que 50 caracters
E informou a "quantidade"
E informou o "preco_unitario"
Quando entrar com essas informaçoes
Então não é cadastrado o produto

Cenário 4.0: Usuário cadastra um produto com quantidade menor a 1
Dado que o usuário deseja cadastrar um produto
E informou a "nome"
E informou a "quantidade"
E inforou o "preco_unitario"
Quando entrar com essas informaçoes
Então o produto não é cadastrado

Cenário 4.1: Usuário cadastra um produto com quantidade igual a 1
Dado que o usuário deseja cadastrar um produto
E informou a "nome"
E informou a "quantidade"
E inforou o "preco_unitario"
Quando entrar com essas informaçoes 
Então o produto é cadastrado

Cenário 4.2: Usuário cadastra um produto com quantidade igual Integer_Max_Value
Dado que o usuário deseja cadastrar um produto
E informou a "nome"
E informou a "quantidade",
E inforou o "preco_unitario"
Quando entrar com essas informaçoes
Então o produto é cadastrado

Cenário 4.3: Usuário cadastra um produto com quantidade maior Integer_Max_Value
Dado que o usuário deseja cadastrar um produto
E informou a "nome"
E informou a "quantidade",
E informou o "preco_unitario"
Quando entrar com essas informaçoes
Então o produto não é cadastrado

Cenário 5.0: Usuário cadastra um produto com preço menor que 1
Dado que o usuário deseja cadastrar um produto
E informou a "nome"
E informou a "quantidade"
E informou o "preco_unitario"
Quando entrar com essas informaçoes
Então o produto nao é cadastrado

Cenário 5.1: Usuário cadastra um produto com preço igual 1
Dado que o usuário deseja cadastrar um produto
E informou a "nome"
E informou a "quantidade"
E inforou o "preco_unitario", menor que 1
Quando entrar com essas informaçoes 
Então o produto é cadastrado

Cenário 5.2: Usuário cadastra um produto com preço igual Long_Max_Value
Dado que o usuário deseja cadastrar um produto
E informou a "nome"
E informou a "quantidade"
E inforou o "preco_unitario"
Quando entrar com essas informaçoes
Então o produto é cadastrado

Cenário 5.3: Usuário cadastra um produto com preço maior Long_Max_Value
Dado que o usuário deseja cadastrar um produto
E informou a "nome"
E informou a "quantidade"
E inforou o "preco_unitario"
Quando entrar com essas informaçoes
Então o produto não é cadastrado

--------------------------------------------------- READ ------------------------------------------------------------

Cenário 6.1: Usuário busca um produto com identificador
Dado que o usuário deseja pesquisar um produto
E informou o "id" do produto 
Quando essas informaçoes forem encontradas 
Então deve retornar para o usuário as respectivas informaçoes do produto

Cenário 6.2: Usuário busca um produto com identificador
Dado que o usuário deseja pesquisar um produto
E informou o "id" do produto 
Quando essas informaçoes não forem encontradas 
Então deve restonar para o usuário uma mensagem de produto não encontrado
E deve ser notificado o erro 404

Cenário 7.1: Usuário deseja buscar a lista de todos produtos cadastrados
Dado que o usuário deseja pesquisar todos os produtos do sistema
E existe pelo menos um produto cadastrado
Quando buscar essas informaçoes
Então deve retornar para o usuário os itens listados

Cenário 7.2: Usuário deseja buscar a lista de produtos com nenhum produto cadastrado
Dado que o usuário deseja pesquisar todos os produtos do sistema
E existe nenhum produto cadastrado
Quando buscar essas informaçoes
Então deve retornar para o usuário uma lista vazia


--------------------------------------------------- UPDATE ------------------------------------------------------------

Cenário 8.1: Usuário busca alterar um produto com identificador
Dado que o usuário deseja pesquisar um produto
E informou o "id" do produto 
Quando essas informaçoes forem encontradas 
Então deve retornar para o usuário as respectivas informaçoes do produto
E excluir o produto do sistema

Cenário 8.2: Usuário busca alterar um produto com identificador
Dado que o usuário deseja pesquisar um produto
E informou o "id" do produto 
Quando essas informaçoes não forem encontradas 
Então deve retornar para o usuário produto nao encontrado
E deve ser notificado o erro 404

--------------------------------------------------- DELETE ------------------------------------------------------------

Cenário 9.1: Usuário busca excluir um produto com identificador
Dado que o usuário deseja pesquisar um produto
E informou o "id" do produto 
Quando essas informaçoes forem encontradas 
Então deve retornar para o usuário as respectivas informaçoes do produto
E excluir o produto do sistema

Cenário 9.2: Usuário busca excluir um produto com identificador
Dado que o usuário deseja pesquisar um produto
E informou o "id" do produto 
Quando essas informaçoes não forem encontradas 
Então deve retornar para o usuário produto nao encontrado
E deve ser notificado o erro 404




