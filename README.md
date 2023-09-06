✅ Aqui está um sistema de venda de ingressos 🎫 simples para um desafio
proposto:

Devemos construir um sistema para realização de venda de ingressos:
O sistema tera as seguintes funcionalidades:

	• Cadastrar filmes
		- id
		- titulo
		- genero (ACAO,AVENTURA,COMEDIA,DRAMA,FICCAO CIENTIFICA,ROMANCE,SUSPENSE,TERROR)
		- idade mínima
		- tempo
		- valor
		- tecnologia (2D, 3D, 4D, IMAX)
		- poltronas disponiveis

	• Cadastro de usuarios
		- nome
		- role (CLIENTE, FUNCIONARIO)
		- idade
		- user
		- password


A sala de cinema deve possuir 100 poltronas enumeradas onde existe um ingressos para cada poltronas
Usuários do tipo (funcionário) poderão:

- Mostrar as poltronas reservadas
- cadastrar novos filmes no sistema
- excluir filmes do cartaz
- um filmes só pode ser excluído caso nenhum ingressos tenha sido vendido
- listar os filmes em cartaz
- sair para a tela de login


Usuários do tipo (clientes) poderão:
- comprar ingressos no sistema
- listar os filmes em cartaz
- a listagem deve retornar somente os filmes de acordo com a classificação indicativa especificada
- visualizar os ingressos que comprou
- sair para a tela de login

Deve existir um sistema de login
- Deve identificar se o usuário é clientes ou funcionario e direciona-lo para o menu correto
Deve ter um cadastro de usuários


MAIN

    MENU INICIAL
		ENTRAR -> (TELA DE LOGIN)
		CADASTRAR -> (TELA DE CADASTRO)

     
     CADASTRO
	informar os campos:
		- nome
		- role (clientes, funcionario)
		- idade
		- user
		- password
	CADASTRAR -> (TELA DE CLIENTE OU TELA DE FUNCIONARIO)

     LOGIN
		Pede user
		Pede password
			O password deve ver armazenado em (Base64 OU md5) criptografia maveproject
		ENTRAR -> (TELA DE CLIENTE OU TELA DE FUNCIONARIO)

      CLIENTE
		- comprar ingressos no sistema
		- listas os filmes em cartaz
			• a listagem deve retornar somente os filmes de acordo com a classificação indicativa especificada
		- visualizar os ingressos que comprou
		- sair -> (TELA DE LOGIN)

      FUNCIONARIO
		- cadastrar novos filmes no sistema
		- excluir filmes do cartaz
			• um filmes só pode ser excluído caso nenhum ingressos tenha sido vendido
		- listas os filmes em cartaz
		- sair -> (TELA DE LOGIN)
