import java.time.Duration;

/**
 * Contrato para tudo que pode ser cobrado pelo tempo de permanência.
 * Cada tipo de veículo implementa sua própria regra de cobrança.
 */
public interface Cobravel {
    double calcularValor(Duration permanencia);
}
