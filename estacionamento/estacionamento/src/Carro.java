import java.time.Duration;

public class Carro extends Veiculo {

    private static final double VALOR_HORA = 6.00;

    public Carro(String placa, String marca, String modelo) {
        super(placa, marca, modelo);
    }

    @Override
    public TipoVeiculo getTipo() {
        return TipoVeiculo.CARRO;
    }

    @Override
    public double calcularValor(Duration permanencia) {
        return horasCobradas(permanencia) * VALOR_HORA;
    }
}
