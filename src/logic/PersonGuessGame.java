package logic;
import java.io.IOException;

import data.Node;
// inheritance class จาก GuessGame
public class PersonGuessGame extends GuessGame {

    public PersonGuessGame(String filename) throws IOException {
        super(filename);
    }
    
    // เติมเต็ม abstract method
    public void correctAnswer() {
        System.out.println("I told you, I'm good at it eiei.");
    }

    // เติมเต็ม abstract method
    public void wrongAnswer(Node current) throws IOException { // เรียนรู้
        System.out.println("What? Can you tell me who it is? T_T");
        String correctPerson = sc.nextLine(); // เก็บคนที่ถูก
        System.out.println("Please ask a question that distinguishes between " + current.questionorname + " and " + correctPerson);
        String newQuestion = sc.nextLine(); // หาว่าคนนี้กับอีกคนต่างกันยังไง

        Node oldGuess = new Node(current.questionorname); // เก็บคำถามเก่าสร้างเป็น node ใหม่
        current.questionorname = newQuestion; // set คำตอบเก่าเป็นคำถามใหม่

        System.out.println("If the answer is 'yes', is it " + correctPerson + "? (yes/no)"); // check ว่าควรต่อซ้ายขวา
        String isYes = sc.nextLine().trim().toLowerCase();
        // ต่อซ้ายขวา
        if (isYes.equals("yes")) { 
            current.right = new Node(correctPerson);
            current.left = oldGuess;
        } else {
            current.left = new Node(correctPerson);
            current.right = oldGuess;
        }
        System.out.println("Thank you! I learned its!");
    }
}
