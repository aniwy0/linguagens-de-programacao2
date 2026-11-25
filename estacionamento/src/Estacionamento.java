import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Classe central: concentra as regras de negócio do estacionamento. */
public class Estacionamento {

    private final List<Vaga> vagas = new ArrayList<>();
    private final Map<String, Veiculo> veiculos = new LinkedHashMap<>();                      // todos os cadastrados
    private final Map<String, RegistroEstacionamento> ativos = new LinkedHashMap<>();         // estacionados agora
    private final List<RegistroEstacionamento> historico = new ArrayList<>();                 // todas as passagens

    public Estacionamento(int totalVagas) {
        if (totalVagas <= 0) {
            throw new IllegalArgumentException("O estacionamento precisa ter ao menos 1 vaga.");
        }
        for (int i = 1; i <= totalVagas; i++) {
            vagas.add(new Vaga(i));
        }
    }

    // RF01 - Cadastrar veículo
    public void cadastrarVeiculo(Veiculo veiculo) {
        if (veiculos.containsKey(veiculo.getPlaca())) {
            throw new RegraNegocioException("Já existe um veículo cadastrado com a placa " + veiculo.getPlaca() + ".");
        }
        veiculos.put(veiculo.getPlaca(), veiculo);
    }

    // RF02 - Entrada de veículo
    public RegistroEstacionamento registrarEntrada(String placa) {
        return registrarEntrada(placa, LocalDateTime.now());
    }

    public RegistroEstacionamento registrarEntrada(String placa, LocalDateTime entrada) {
        Veiculo veiculo = exigirVeiculo(placa);

        if (ativos.containsKey(veiculo.getPlaca())) {
            throw new RegraNegocioException("O veículo " + veiculo.getPlaca() + " já está estacionado.");
        }

        Vaga vaga = vagas.stream()
                .filter(Vaga::isLivre)
                .findFirst()
                .orElseThrow(() -> new RegraNegocioException("Estacionamento lotado: não há vagas disponíveis."));

        vaga.ocupar(veiculo);
        RegistroEstacionamento registro = new RegistroEstacionamento(veiculo, vaga, entrada);
        ativos.put(veiculo.getPlaca(), registro);
        historico.add(registro);
        return registro;
    }

    // RF03 e RF04 - Saída de veículo (calcula permanência e valor, libera a vaga)
    public RegistroEstacionamento registrarSaida(String placa) {
        return registrarSaida(placa, LocalDateTime.now());
    }

    public RegistroEstacionamento registrarSaida(String placa, LocalDateTime saida) {
        Veiculo veiculo = exigirVeiculo(placa);
        RegistroEstacionamento registro = ativos.get(veiculo.getPlaca());

        if (registro == null) {
            throw new RegraNegocioException("O veículo " + veiculo.getPlaca() + " não está estacionado.");
        }
        if (saida.isBefore(registro.getEntrada())) {
            throw new RegraNegocioException("A saída não pode ser anterior à entrada.");
        }

        Duration permanencia = Duration.between(registro.getEntrada(), saida);
        double valor = veiculo.calcularValor(permanencia); // polimorfismo: cada tipo cobra de um jeito

        registro.registrarSaida(saida, valor);
        registro.getVaga().liberar();
        ativos.remove(veiculo.getPlaca());
        return registro;
    }

    // RF05 - Consultar vagas
    public List<Vaga> getVagas() {
        return Collections.unmodifiableList(vagas);
    }

    public long getQuantidadeVagasLivres() {
        return vagas.stream().filter(Vaga::isLivre).count();
    }

    public int getTotalVagas() {
        return vagas.size();
    }

    // RF06 - Listar veículos estacionados
    public List<RegistroEstacionamento> getVeiculosEstacionados() {
        return new ArrayList<>(ativos.values());
    }

    // RF07 - Histórico de entradas e saídas
    public List<RegistroEstacionamento> getHistorico() {
        return Collections.unmodifiableList(historico);
    }

    // RF08 - Consultar veículo pela placa
    public Veiculo buscarVeiculo(String placa) {
        return veiculos.get(Veiculo.normalizarPlaca(placa));
    }

    public RegistroEstacionamento buscarRegistroAtivo(String placa) {
        return ativos.get(Veiculo.normalizarPlaca(placa));
    }

    private Veiculo exigirVeiculo(String placa) {
        Veiculo veiculo = buscarVeiculo(placa);
        if (veiculo == null) {
            throw new RegraNegocioException("Veículo não cadastrado: " + Veiculo.normalizarPlaca(placa) + ".");
        }
        return veiculo;
    }
}
