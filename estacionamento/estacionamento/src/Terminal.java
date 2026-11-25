import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Ajusta a entrada e a saída do console para o mesmo charset do terminal,
 * para que acentos (ç, ã, é...) apareçam corretamente no Windows.
 */
public final class Terminal {

    private static final Charset CHARSET = descobrirCharset();

    private Terminal() {
    }

    /** Troca System.out e System.err por versões que usam o charset do terminal. */
    public static void configurar() {
        System.setOut(new PrintStream(System.out, true, CHARSET));
        System.setErr(new PrintStream(System.err, true, CHARSET));
    }

    /** Cria um Scanner de System.in usando o mesmo charset do terminal. */
    public static Scanner criarScanner() {
        return new Scanner(System.in, CHARSET);
    }

    private static Charset descobrirCharset() {
        // Com um terminal interativo, usa a página de código dele (ex.: CP850, CP1252 ou UTF-8).
        if (System.console() != null) {
            return System.console().charset();
        }
        // Sem terminal (saída redirecionada, algumas IDEs): UTF-8.
        return StandardCharsets.UTF_8;
    }
}
