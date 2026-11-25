public class Vaga {

    private final int numero;
    private Veiculo veiculo; // null = vaga livre

    public Vaga(int numero) {
        this.numero = numero;
    }

    public void ocupar(Veiculo veiculo) {
        if (!isLivre()) {
            throw new RegraNegocioException("A vaga " + numero + " já está ocupada.");
        }
        this.veiculo = veiculo;
    }

    public void liberar() {
        this.veiculo = null;
    }

    public boolean isLivre() {
        return veiculo == null;
    }

    public int getNumero() {
        return numero;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    @Override
    public String toString() {
        String situacao = isLivre() ? "LIVRE" : "OCUPADA por " + veiculo;
        return String.format("Vaga %02d - %s", numero, situacao);
    }
}
