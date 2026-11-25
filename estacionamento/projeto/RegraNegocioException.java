/**
 * Exceção lançada quando uma regra de negócio do estacionamento é violada
 * (placa duplicada, estacionamento lotado, veículo já estacionado, etc.).
 */
public class RegraNegocioException extends RuntimeException {
    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
