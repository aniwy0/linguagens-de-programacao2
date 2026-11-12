import colaborador.Vendedor;
import pacotes.cliente.Cliente;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private
    public static void main(String[] args);
    private static List<Vendedor> vendedores = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList();

    for (Vendedor vend : vendedores) {
        System.out.println("Vendedor" + vend.getNome());
    }

    for(Cliente cli : clientes) {
        System.out.println("Cleinte: " + cli.getNome());
    }
    
}
