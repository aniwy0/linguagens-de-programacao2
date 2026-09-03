public class Main {
    public static void main(String[] args) {
      //  String[] alunos = {"Miranata", "Savalo", "Aeronauta"};
        
      //  alunos[0] = "Mariazinha";
      //  System.out.println("Quantidade de alunos: " +  alunos.length);

      //  for(String estudante : alunos){
          //  System.out.println(estudante);

      //  }
    //  String[] produtos = {"monitor", "mouse", "teclado", "headset", "gabinete"};
    //  for(String perifericos : produtos){
    //    System.out.println(perifericos);
    //  }

    // String[] produtos = {"monitor", "mouse", "teclado", "headset", "gabinete"};
    // produtos[0] = "pecas";
    // System.out.println("Quantidade de produtos: " +  produtos.length);
    // for(int i = 0; i < produtos.length; i++) {
        //System.out.println(produtos[i]);

        //int[] num = {0, 2, -3, 4, 5};
        //for(int mostrar : num) {
            //if (mostrar <0)
            //System.out.println("Negativo");  //APENAS FOREACH
        //if (mostrar == 0)
            //System.out.println("Zero");
        //if (mostrar > 0)
            //System.out.println("Positivo");    
    //}
    int[] num = {0, 2, -3, 4, 5};
        for(int mostrar : num) {
            if (mostrar <0)
            System.out.println("O num : " + mostrar + " eh Negativo ");
        if (mostrar == 0)
            System.out.println("O num : " + mostrar + " eh Zero ");
        if (mostrar > 0)
            System.out.println("O num : " + mostrar + " eh Positivo ");    
    }     

    }
}    

