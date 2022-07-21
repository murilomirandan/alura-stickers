public class TesteClass {
    public static void main(String[] args) {
        System.out.println("op > 0\top <= 5\tAND");
        for (int i = 0; i < 10; i++){
            System.out.println((i > 0) + "\t" + (i <= 5) + "\t" + ((i > 0 && i <= 5)));
        }
    }
}
