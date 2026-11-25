import java.time.Duration;

public class Caminhonete extends Veiculo {

    private static final double VALOR_HORA = 8.00;
    private static final double TAXA_FIXA = 5.00; // ocupa mais espaço na vaga

    public Caminhonete(String placa, String marca, String modelo) {
        super(placa, marca, modelo);
    }

    @Override
    public TipoVeiculo getTipo() {
        return TipoVeiculo.CAMINHONETE;
    }

    @Override
    public double calcularValor(Duration permanencia) {
        return TAXA_FIXA + horasCobradas(permanencia) * VALOR_HORA;
    }
}
