import java.io.*;
import java.util.*;

import data.model.Node;
import data.model.TreeBuilder;

public abstract class GuessGame {
    protected Node root;
    protected Scanner sc = new Scanner(System.in);

    public GuessGame(String filename) throws IOException {
        root = TreeBuilder.buildTreeFromFile(filename);
    }

    public abstract void correctAnswer();
    public abstract void wrongAnswer(Node current) throws IOException;

    public void playgame() throws IOException {
        Stack<Node> pathNode = new Stack<>();
        Node current = root;
        while (!current.isleaf()) {
            System.out.println(current.questionorname + " (yes/no/back)");
            String ans = sc.nextLine().trim().toLowerCase();
            if (ans.equals("yes")) {
                pathNode.push(current);
                current = current.right;
            } else if (ans.equals("no")) {
                pathNode.push(current);
                current = current.left;
            } else if (ans.equals("back")) {
                if (!pathNode.empty()) {
                    current = pathNode.pop();
                } else {
                    System.out.println("You are at the first Question!");
                }
            }
        }

        System.out.println("Is this person " + current.questionorname + "? (yes/no)");
        String finalans = sc.nextLine().trim().toLowerCase();
        if (finalans.equals("yes")) {
            correctAnswer();
        } else {
            wrongAnswer(current);
            saveTreeToFile(root, "assets/data/questions.txt");
        }
    }

    void saveTreeToFile(Node node, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writeNode(node, writer, 0);
        writer.close();
    }

    private void writeNode(Node node, BufferedWriter writer, int indent) throws IOException {
        if (node == null) return;
        String prefix = node.isleaf() ? "A:" : "Q:";
        String indentSpaces = " ".repeat(indent);
        writer.write(indentSpaces + prefix + node.questionorname);
        writer.newLine();

        if (!node.isleaf()) {
            writeNode(node.left, writer, indent + 2);
            writeNode(node.right, writer, indent + 2);
        }
    }
}
