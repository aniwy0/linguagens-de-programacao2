public class Main {
        public static void main(String[] args) {
            Carro locomocao = new Carro();
            locomocao.marca = "Wolks";
            locomocao.modelo = "Virtus";
            locomocao.combustivel = "flex";
            locomocao.cor = "prata";
    
            System.out.println(locomocao.marca);
            System.out.println(locomocao.modelo);
            System.out.println(locomocao.combustivel);
            System.out.println(locomocao.cor);
            locomocao.ligarmotor();
            locomocao.desligarmotor();

            Moto = new Moto();
            Moto.marca = "Honda";
            Moto.modelo = "Cb650r";
            Moto.combustivel = "Flex";
            Moto.cilindrada = "600";

            System.out.println(Moto.marca);
            System.out.println(Moto.modelo);
            System.out.println(Moto.combustivel);
            System.out.println(Moto.cilindrada);
            locomocao.ligarmotor();
            locomocao.desligarmotor();
        }
    }
