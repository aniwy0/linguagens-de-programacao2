import java.time.Duration;

public class Moto extends Veiculo {

    private static final double VALOR_HORA = 3.00;

    public Moto(String placa, String marca, String modelo) {
        super(placa, marca, modelo);
    }

    @Override
    public TipoVeiculo getTipo() {
        return TipoVeiculo.MOTO;
    }

    @Override
    public double calcularValor(Duration permanencia) {
        return horasCobradas(permanencia) * VALOR_HORA;
    }
}
