import java.io.IOException;

import data.model.Node;
public class PersonGuessGame extends GuessGame {

    public PersonGuessGame(String filename) throws IOException {
        super(filename);
    }

    public void correctAnswer() {
        System.out.println("I told you, I'm good at it eiei.");
    }

    public void wrongAnswer(Node current) throws IOException {
        System.out.println("What? Can you tell me who it is? T_T");
        String correctPerson = sc.nextLine();
        System.out.println("Please ask a question that distinguishes between " + current.questionorname + " and " + correctPerson);
        String newQuestion = sc.nextLine();

        Node oldGuess = new Node(current.questionorname);
        current.questionorname = newQuestion;

        System.out.println("If the answer is 'yes', is it " + correctPerson + "? (yes/no)");
        String isYes = sc.nextLine().trim().toLowerCase();
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
