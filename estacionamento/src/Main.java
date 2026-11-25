import java.util.List;
import java.util.Scanner;

/** Interface de console do sistema. */
public class Main {

    private static final int TOTAL_VAGAS = 10;
    private static final Scanner scanner = Terminal.criarScanner();
    private static final Estacionamento estacionamento = new Estacionamento(TOTAL_VAGAS);

    public static void main(String[] args) {
        Terminal.configurar();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            try {
                switch (opcao) {
                    case 1 -> cadastrarVeiculo();
                    case 2 -> entradaVeiculo();
                    case 3 -> saidaVeiculo();
                    case 4 -> consultarVagas();
                    case 5 -> listarEstacionados();
                    case 6 -> consultarVeiculo();
                    case 7 -> exibirHistorico();
                    case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (RegraNegocioException e) {
                System.out.println("\n[ERRO] " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n===== ESTACIONAMENTO =====");
        System.out.println("1 - Cadastrar veículo");
        System.out.println("2 - Registrar entrada");
        System.out.println("3 - Registrar saída");
        System.out.println("4 - Consultar vagas");
        System.out.println("5 - Listar veículos estacionados");
        System.out.println("6 - Consultar veículo por placa");
        System.out.println("7 - Histórico de entradas e saídas");
        System.out.println("0 - Sair");
    }

    private static void cadastrarVeiculo() {
        System.out.println("\n--- Cadastrar veículo ---");
        String placa = lerTexto("Placa: ");
        String marca = lerTexto("Marca: ");
        String modelo = lerTexto("Modelo: ");
        System.out.println("Tipo: 1 - Carro | 2 - Moto | 3 - Caminhonete");
        int tipo = lerInteiro("Tipo: ");

        Veiculo veiculo = switch (tipo) {
            case 1 -> new Carro(placa, marca, modelo);
            case 2 -> new Moto(placa, marca, modelo);
            case 3 -> new Caminhonete(placa, marca, modelo);
            default -> throw new RegraNegocioException("Tipo de veículo inválido.");
        };

        estacionamento.cadastrarVeiculo(veiculo);
        System.out.println("Veículo cadastrado: " + veiculo);

        String resposta = lerTexto("Registrar a entrada agora? (s/n): ");
        if (resposta.equalsIgnoreCase("s")) {
            RegistroEstacionamento registro = estacionamento.registrarEntrada(veiculo.getPlaca());
            System.out.println("Entrada registrada: " + registro);
        }
    }

    private static void entradaVeiculo() {
        System.out.println("\n--- Entrada de veículo ---");
        String placa = lerTexto("Placa: ");
        RegistroEstacionamento registro = estacionamento.registrarEntrada(placa);
        System.out.println("Entrada registrada na vaga " + registro.getVaga().getNumero()
                + " às " + Formatos.dataHora(registro.getEntrada()) + ".");
    }

    private static void saidaVeiculo() {
        System.out.println("\n--- Saída de veículo ---");
        String placa = lerTexto("Placa: ");
        RegistroEstacionamento registro = estacionamento.registrarSaida(placa);

        System.out.println("\n----- COMPROVANTE -----");
        System.out.println("Veículo:     " + registro.getVeiculo());
        System.out.println("Vaga:        " + registro.getVaga().getNumero() + " (liberada)");
        System.out.println("Entrada:     " + Formatos.dataHora(registro.getEntrada()));
        System.out.println("Saída:       " + Formatos.dataHora(registro.getSaida()));
        System.out.println("Permanência: " + Formatos.duracao(registro.getPermanencia()));
        System.out.println("Valor:       " + Formatos.moeda(registro.getValorPago()));
    }

    private static void consultarVagas() {
        System.out.println("\n--- Vagas ---");
        estacionamento.getVagas().forEach(System.out::println);
        System.out.println("\nLivres: " + estacionamento.getQuantidadeVagasLivres()
                + " de " + estacionamento.getTotalVagas());
    }

    private static void listarEstacionados() {
        System.out.println("\n--- Veículos estacionados ---");
        List<RegistroEstacionamento> ativos = estacionamento.getVeiculosEstacionados();
        if (ativos.isEmpty()) {
            System.out.println("Nenhum veículo estacionado no momento.");
            return;
        }
        ativos.forEach(System.out::println);
    }

    private static void consultarVeiculo() {
        System.out.println("\n--- Consultar veículo ---");
        String placa = lerTexto("Placa: ");
        Veiculo veiculo = estacionamento.buscarVeiculo(placa);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }
        System.out.println("Veículo: " + veiculo);
        RegistroEstacionamento ativo = estacionamento.buscarRegistroAtivo(placa);
        if (ativo != null) {
            System.out.println("Situação: estacionado na vaga " + ativo.getVaga().getNumero()
                    + " desde " + Formatos.dataHora(ativo.getEntrada()));
        } else {
            System.out.println("Situação: fora do estacionamento.");
        }
    }

    private static void exibirHistorico() {
        System.out.println("\n--- Histórico ---");
        List<RegistroEstacionamento> historico = estacionamento.getHistorico();
        if (historico.isEmpty()) {
            System.out.println("Nenhum registro ainda.");
            return;
        }
        historico.forEach(System.out::println);
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }
}
