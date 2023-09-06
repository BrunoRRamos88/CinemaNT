import Enum.Role;
import Enum.Genero;
import Enum.Tecnologia;
import Models.*;

import java.util.*;
import java.util.Base64;

public class Main {
    private static List<Filmes> filmes = new ArrayList<>();
    private static List<Usuarios> usuarios = new ArrayList<>();
    private static List<Poltronas> poltronas = new ArrayList<>();
    private static List<Ingressos> ingressosComprados = new ArrayList<>();
    private static Usuarios usuariosLogado;

    public static void main(String[] args) {

        for (int i = 1; i <= 100; i++) {
            poltronas.add(new Poltronas(i));
        }

        telaInicial();
    }

    private static void telaInicial() {
        System.out.println("MENU");
        System.out.println("------------------------");
        System.out.println("1. Entrar");
        System.out.println("2. Cadastrar");
        System.out.println("3. Sair");
        System.out.println("Escolha uma opção: ");

        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                telaLogin();
                break;
            case 2:
                telaCadastro();
                break;
            case 3:
                System.out.println("Até mais! Saindo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida, tente novamente\n");
                telaInicial();
        }
    }

    public static void telaLogin() {
        System.out.println("LOGIN");
        System.out.println("------------------------");
        System.out.println("Usuário: ");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine().toUpperCase();

        System.out.println("Senha: ");
        String password = scanner.nextLine();
        String senhaCriptografada = Base64.getEncoder().encodeToString(password.getBytes());

        Usuarios usuarios = autenticarUsuario(user, senhaCriptografada);
        if (usuarios != null) {
            usuariosLogado = usuarios;
            if (usuarios instanceof Funcionarios) {
                telaFuncionario();
            } else if (usuarios instanceof Clientes) {
                telaCliente();
            }
        } else {
            System.out.println("Usuário ou senha inválidos, tente novamente.\n");
            telaInicial();
        }
    }

    public static Usuarios autenticarUsuario(String user, String password) {
        for (Usuarios usuarios : Main.usuarios) {
            if (usuarios.getUser().equalsIgnoreCase(user) && usuarios.getPassword().equals(password)) {
                return usuarios;
            }
        }
        return null;
    }

    public static void telaCadastro() {
        System.out.println("CADASTRO");
        System.out.println("------------------------");
        Scanner scanner = new Scanner(System.in);

        String nome = null;
        Role role = null;
        int idade = 0;
        String user = null;
        String password = null;

        System.out.println("Nome: ");
        nome = scanner.nextLine().toUpperCase();

        System.out.println("Escolha a categoria de usuário:");
        System.out.println("1. CLIENTE");
        System.out.println("2. FUNCIONARIO");
        int categoriaEscolhida = scanner.nextInt();
        scanner.nextLine();

        switch (categoriaEscolhida) {
            case 1:
                role = Role.CLIENTE;
                break;
            case 2:
                role = Role.FUNCIONARIO;
                break;
            default:
                System.out.println("Opção inválida. Escolha 1 para CLIENTE ou 2 para FUNCIONARIO.");
                telaCadastro();
                return;
        }

        boolean idadeValida = false;
        do {
            System.out.println("Idade: ");
            if (scanner.hasNextInt()) {
                idade = scanner.nextInt();
                scanner.nextLine();
                idadeValida = true;
            } else {
                System.out.println("Idade inválida. Digite novamente.");
                scanner.nextLine();
            }
        } while (!idadeValida);

        do {
            System.out.println("Nome do usuário: ");
            user = scanner.nextLine();
            if (user.isEmpty()) {
                System.out.println("Nome de usuário inválido, digite novamente.");
            } else if (usuarioJaExiste(user)) {
                System.out.println("Usuário já existente, digite outro nome de usuário.");
                user = null;
            }
        } while (user == null || user.isEmpty());

        do {
            System.out.println("Senha: ");
            password = scanner.nextLine();
            if (password.isEmpty()) {
                System.out.println("Senha inválida, digite novamente.");
            }
        } while (password.isEmpty());

        String senhaCriptografada = Base64.getEncoder().encodeToString(password.getBytes());

        if (role == Role.CLIENTE) {
            Clientes novoClientes = new Clientes(nome, role, idade, user, senhaCriptografada);
            usuarios.add(novoClientes);
        } else if (role == Role.FUNCIONARIO) {
            Funcionarios novoFuncionarios = new Funcionarios(nome, role, idade, user, senhaCriptografada);
            usuarios.add(novoFuncionarios);
        } else {
            System.out.println("Categoria de usuário inválida, tente novamente.\n");
            telaCadastro();
        }

        System.out.println("Cadastro realizado com sucesso!\n");
        telaInicial();
    }

    private static boolean usuarioJaExiste(String user) {
        for (Usuarios usuarios : Main.usuarios) {
            if (usuarios.getUser().equalsIgnoreCase(user)) {
                return true;
            }
        }
        return false;
    }

    private static void telaFuncionario() {
        System.out.println("\nÁREA DO FUNCIONÁRIO");
        System.out.println("------------------------");
        System.out.println("1. Cadastrar novo filme");
        System.out.println("2. Excluir filme do cartaz");
        System.out.println("3. Listar filmes em cartaz");
        System.out.println("4. Sair");
        System.out.println("Escolha uma opção: ");

        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                cadastrarFilme();
                telaFuncionario();
                break;
            case 2:
                if (filmes.isEmpty()) {
                    System.out.println("EXCLUIR FILME DO CARTAZ");
                    System.out.println("------------------------");
                    System.out.println("Não há filmes cadastrados para serem excluídos!");
                } else {
                    excluirFilme();
                }
                telaFuncionario();
                break;
            case 3:
                listarFilmes();
                telaFuncionario();
            case 4:
                usuariosLogado = null;
                telaInicial();
            default:
                System.out.println("Opção inválida. Tente novamente.");
                telaFuncionario();
        }
    }

    private static void cadastrarFilme() {
        System.out.println("CADASTRAR NOVO FILME");
        System.out.println("------------------------");
        System.out.println("Título: ");
        Scanner scanner = new Scanner(System.in);
        String titulo = scanner.nextLine().toUpperCase();

        System.out.println("Gênero:");
        for (Genero genero : Genero.values()) {
            System.out.println(genero.getNumero() + ". " + genero.name());
        }

        int generoEscolhido = scanner.nextInt();
        scanner.nextLine();

        Genero genero = null;
        for (Genero g : Genero.values()) {
            if (g.getNumero() == generoEscolhido) {
                genero = g;
                break;
            }
        }

        if (genero == null) {
            System.out.println("Opção de gênero inválida. Tente novamente.");
            cadastrarFilme();
            return;
        }

        System.out.println("Classificação indicativa: ");
        int classificacao = scanner.nextInt();

        System.out.println("Tempo: ");
        int tempo = scanner.nextInt();
        scanner.nextLine();
        double valor;
        while (true) {
            System.out.println("Valor: ");
            String valorStr = scanner.nextLine();
            try {
                valor = Double.parseDouble(valorStr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Certifique-se de usar o formato correto (ex: 15.5).");
            }
        }
        System.out.println("Tecnologia:");
        for (Tecnologia tecnologia : Tecnologia.values()) {
            System.out.println(tecnologia.getNumero() + ". " + tecnologia.name());
        }

        int tecnologiaEscolhida = scanner.nextInt();
        scanner.nextLine();

        Tecnologia tecnologia = null;
        for (Tecnologia t : Tecnologia.values()) {
            if (t.getNumero() == tecnologiaEscolhida) {
                tecnologia = t;
                break;
            }
        }

        if (tecnologia == null) {
            System.out.println("Opção de tecnologia inválida. Tente novamente.");
            cadastrarFilme();
            return;
        }

        System.out.println("Poltronas disponíveis: ");
        int poltronasDisponíveis = scanner.nextInt();
        scanner.nextLine();
        List<Poltronas> poltronas = new ArrayList<>();
        for (int i = 1; i <= poltronasDisponíveis; i++) {
            poltronas.add(new Poltronas(i));
        }

        Filmes novoFilmes = new Filmes(titulo, genero, classificacao, tempo, valor, tecnologia, poltronas);
        filmes.add(novoFilmes);
        System.out.println("Filme cadastrado com sucesso!");
    }

    private static void excluirFilme() {
        System.out.println("EXCLUIR FILME DO CARTAZ");
        System.out.println("------------------------");
        for (Filmes filmes : Main.filmes) {
            System.out.println("ID: " + filmes.getId());
            System.out.println("Título: " + filmes.getTitulo());
            System.out.println("------------------------");
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID do filme a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Filmes filmesEncontrado = null;
        for (Filmes filmes : Main.filmes) {
            if (filmes.getId() == id) {
                filmesEncontrado = filmes;
                break;
            }
        }

        if (filmesEncontrado != null) {
            boolean temPoltronasVendidas = false;
            for (Ingressos ingressos : ingressosComprados) {
                if (ingressos.getFilme() == filmesEncontrado) {
                    temPoltronasVendidas = true;
                    break;
                }
            }

            if (temPoltronasVendidas) {
                System.out.println("O filme possui poltronas vendidas e não pode ser excluído.");
            } else {
                filmes.removeIf(filme -> filme.getId() == id);
                System.out.println("Filme excluído com sucesso!");
            }
        } else {
            System.out.println("Filme não encontrado");
        }
    }

    private static void listarFilmes() {
        System.out.println("FILMES EM CARTAZ");
        System.out.println("------------------------");

        if (filmes.isEmpty()) {
            System.out.println("Não há filmes em cartaz.");
        } else {
            for (Filmes filmes : Main.filmes) {
                System.out.println("ID: " + filmes.getId());
                System.out.println("Título: " + filmes.getTitulo());
                System.out.println("Gênero: " + filmes.getGenero());
                System.out.println("Idade mínima: " + filmes.getClassificacao());
                System.out.println("Tempo: " + filmes.getTempo());
                System.out.println("Valor: " + filmes.getValor());
                System.out.println("Tecnologia: " + filmes.getTecnologia());
                System.out.println("Models.Poltronas disponíveis: " + filmes.getPoltronasDisponiveis().size());
                System.out.println("------------------------");
            }
        }
    }

    private static void telaCliente() {
        System.out.println("\nÁREA DO CLIENTE");
        System.out.println("------------------------");
        System.out.println("1. Comprar ingressos");
        System.out.println("2. Listar filmes em cartaz");
        System.out.println("3. Visualizar ingressos comprados");
        System.out.println("4. Sair");
        System.out.println("Escolha uma opção: ");

        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                comprarIngressos();
                telaCliente();
                break;
            case 2:
                listarFilmesCliente();
                telaCliente();
                break;
            case 3:
                visualizarIngressos();
                telaCliente();
                break;
            case 4:
                usuariosLogado = null;
                telaInicial();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                telaCliente();
        }
    }

    private static void comprarIngressos() {
        System.out.println("COMPRA DE INGRESSOS");
        System.out.println("------------------------");
        listarFilmesCliente();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID do filme desejado ou 0 para sair.");
        int idFilme = scanner.nextInt();
        scanner.nextLine();

        if (idFilme == 0) {
            return;
        }

        Filmes filmesSelecionado = null;
        for (Filmes filmes : Main.filmes) {
            if (filmes.getId() == idFilme) {
                filmesSelecionado = filmes;
                break;
            }
        }

        if (filmesSelecionado != null) {
            if (usuariosLogado instanceof Clientes) {
                int idadeUsuario = ((Clientes) usuariosLogado).getIdade();
                if (idadeUsuario < filmesSelecionado.getClassificacao()) {
                    System.out.println("Compra bloqueada devido à classificação indicativa do filme selecionado.");
                    return;
                }
            }

            System.out.println("Filme selecionado: " + filmesSelecionado.getTitulo());
            poltronasDisponiveis(filmesSelecionado);
            System.out.println("Digite o número da poltrona desejada ou 0 para sair.");
            int numeroPoltrona = scanner.nextInt();
            scanner.nextLine();

            if (numeroPoltrona == 0) {
                return;
            }

            Poltronas poltronasSelecionada = null;
            for (Poltronas poltronas : filmesSelecionado.getPoltronasDisponiveis()) {
                if (poltronas.getNumero() == numeroPoltrona && poltronas.isDisponivel()) {
                    poltronasSelecionada = poltronas;
                    break;
                }
            }

            if (poltronasSelecionada != null) {
                poltronasSelecionada.setDisponivel(false);
                filmesSelecionado.getPoltronasDisponiveis().remove(poltronasSelecionada);
                ingressosComprados.add(new Ingressos(filmesSelecionado, poltronasSelecionada, (Clientes) usuariosLogado));
                System.out.println("Ingresso comprado com sucesso!");
            } else {
                System.out.println("Poltrona indisponível ou inválida, tente novamente.");
                comprarIngressos();
            }
        } else {
            System.out.println("Filme não encontrado, tente novamente.");
            comprarIngressos();
        }
    }

    private static void listarFilmesCliente() {
        System.out.println("FILMES DISPONÍVEIS");
        System.out.println("------------------------");
        for (Filmes filmes : Main.filmes) {
            if (usuariosLogado instanceof Clientes && filmes.getClassificacao() <= ((Clientes) usuariosLogado).getIdade()) {
                System.out.println("ID: " + filmes.getId());
                System.out.println("Título: " + filmes.getTitulo());
                System.out.println("Gênero: " + filmes.getGenero());
                System.out.println("Idade mínima: " + filmes.getClassificacao());
                System.out.println("Tempo: " + filmes.getTempo());
                System.out.println("Valor: " + filmes.getValor());
                System.out.println("Tecnologia: " + filmes.getTecnologia());
                System.out.println("Models.Poltronas disponíveis: " + filmes.getPoltronasDisponiveis().size());
                System.out.println("---------------------------------------");
            }
        }
    }

    private static void poltronasDisponiveis(Filmes filmes) {
        System.out.println("POLTRONAS DISPONÍVEIS");
        System.out.println("------------------------");

        int poltronasPorLinha = 10;
        int contador = 0;

        for (Poltronas poltronas : filmes.getPoltronasDisponiveis()) {
            if (poltronas.isDisponivel()) {
                System.out.print(poltronas.getNumero() + " ");
                contador++;

                if (contador == poltronasPorLinha) {
                    System.out.println();
                    contador = 0;
                }
            }
        }

        if (contador > 0) {
            System.out.println();
        }
    }

    private static void visualizarIngressos() {
        System.out.println("INGRESSOS COMPRADOS");
        System.out.println("------------------------");

        if (ingressosComprados.isEmpty()) {
            System.out.println("Nenhum ingresso comprado.");
            return;
        }

        for (Ingressos ingressos : ingressosComprados) {
            if (ingressos.getCliente().equals(usuariosLogado)) {
                System.out.println("Filme: " + ingressos.getFilme().getTitulo());
                System.out.println("Poltrona: " + ingressos.getPoltrona().getNumero());
                System.out.println("------------------------");
            }
        }
    }
}