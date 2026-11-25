public enum TipoVeiculo {
    CARRO("Carro"),
    MOTO("Moto"),
    CAMINHONETE("Caminhonete");

    private final String descricao;

    TipoVeiculo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
