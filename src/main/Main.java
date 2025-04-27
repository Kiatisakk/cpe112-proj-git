import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {  
        Scanner sc = new Scanner(System.in);
        PersonGuessGame game = new PersonGuessGame("questions.txt");
        while (true) {
            System.out.println("\n---------- Welcome To JINNYJA GAME ----------\n");
            game.playgame();
            System.out.println("Try Again? (yes/no)");
            String again = sc.nextLine().trim().toLowerCase();
            if (!again.equals("yes")) {
                break;
            }
        }
        System.out.println("Thank You For Playing!!!!!!");
        sc.close(); 
    }
}
