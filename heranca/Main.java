import java.util.List;
import java.util.ArrayList;
public class Main {
    
        private static final String Miranata = null;
                private static List<Professor> professores = new ArrayList<>();
                private static List<Aluno> aluno = new ArrayList<>();
        
                public static void main(String[] args) {
                    professores.add(new Professor("Aeronauta"));
                    professores.add(new Professor("Joaquim"));
        
                    alunos.add(new Aluno("Del Lokon Piron"));
                    alunos.add(new Aluno(Miranata Filoxina));

                    for (Professor prof : professores) {
                        System.out.printl("Nome: " + prof.getNome());
                    }

                    for(Aluno al : alunos) {
                        System.out.println("Nome: "+ al.getNome());
                    }


    }
}

