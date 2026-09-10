public class Main {
    public static void main(String[] args) {
        int a = 7, b = 3, c = 1;
        if(a > b && a > c){
            System.out.println( "O maior eh: " + a );
        } else if(b > a && b > c){
            System.out.println( " O maior eh: " + b);
        } else {
            System.out.println( " O maior eh: " + c);
        }
    }
    
}
