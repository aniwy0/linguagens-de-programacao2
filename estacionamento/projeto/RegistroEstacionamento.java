import java.time.Duration;
import java.time.LocalDateTime;

/** Representa uma passagem de um veículo pelo estacionamento (entrada e, depois, saída). */
public class RegistroEstacionamento {

    private final Veiculo veiculo;
    private final Vaga vaga;
    private final LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;

    public RegistroEstacionamento(Veiculo veiculo, Vaga vaga, LocalDateTime entrada) {
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.entrada = entrada;
    }

    public void registrarSaida(LocalDateTime saida, double valorPago) {
        this.saida = saida;
        this.valorPago = valorPago;
    }

    public boolean isAberto() {
        return saida == null;
    }

    public Duration getPermanencia() {
        LocalDateTime fim = isAberto() ? LocalDateTime.now() : saida;
        return Duration.between(entrada, fim);
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public double getValorPago() {
        return valorPago;
    }

    @Override
    public String toString() {
        String base = String.format("%s | Vaga %02d | Entrada: %s",
                veiculo, vaga.getNumero(), Formatos.dataHora(entrada));
        if (isAberto()) {
            return base + " | Ainda estacionado";
        }
        return base + " | Saída: " + Formatos.dataHora(saida)
                + " | Permanência: " + Formatos.duracao(getPermanencia())
                + " | Valor: " + Formatos.moeda(valorPago);
    }
}
