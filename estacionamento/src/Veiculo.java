import java.time.Duration;

/**
 * Classe abstrata base para todos os veículos.
 * Encapsula os dados comuns e deixa a cobrança para as subclasses (polimorfismo).
 */
public abstract class Veiculo implements Cobravel {

    private final String placa;
    private final String marca;
    private final String modelo;

    protected Veiculo(String placa, String marca, String modelo) {
        String placaNormalizada = normalizarPlaca(placa);
        // Aceita placa antiga (ABC1234) e Mercosul (ABC1D23)
        if (!placaNormalizada.matches("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")) {
            throw new RegraNegocioException("Placa inválida: use o formato ABC1234 ou ABC1D23.");
        }
        if (marca == null || marca.isBlank()) {
            throw new RegraNegocioException("A marca é obrigatória.");
        }
        if (modelo == null || modelo.isBlank()) {
            throw new RegraNegocioException("O modelo é obrigatório.");
        }
        this.placa = placaNormalizada;
        this.marca = marca.trim();
        this.modelo = modelo.trim();
    }

    /** Remove espaços e hífen e converte para maiúsculas. */
    public static String normalizarPlaca(String placa) {
        if (placa == null) {
            return "";
        }
        return placa.trim().toUpperCase().replace("-", "").replace(" ", "");
    }

    /** Quantidade de horas a cobrar: toda hora iniciada conta como hora cheia (mínimo 1). */
    protected long horasCobradas(Duration permanencia) {
        long minutos = permanencia.toMinutes();
        return Math.max(1, (long) Math.ceil(minutos / 60.0));
    }

    public abstract TipoVeiculo getTipo();

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    @Override
    public String toString() {
        return getTipo().getDescricao() + " | " + placa + " | " + marca + " " + modelo;
    }
}
