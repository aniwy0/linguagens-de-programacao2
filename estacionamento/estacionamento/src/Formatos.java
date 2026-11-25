import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** Utilitários de formatação para exibir dados no console. */
public final class Formatos {

    private static final DateTimeFormatter DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final NumberFormat MOEDA = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

    private Formatos() {
    }

    public static String dataHora(LocalDateTime dataHora) {
        return dataHora == null ? "-" : DATA_HORA.format(dataHora);
    }

    public static String moeda(double valor) {
        return MOEDA.format(valor).replace('\u00A0', ' ');
    }

    public static String duracao(Duration duracao) {
        long horas = duracao.toHours();
        long minutos = duracao.toMinutesPart();
        return horas + "h " + minutos + "min";
    }
}
