package logic;
import java.io.*;
import java.util.*;

import data.Node;
import data.TreeBuilder;
// สร้าง abstract class สำหรับนำไปเรียกใช้ใน PersonGuessGame
public abstract class GuessGame {
    protected Node root; // root
    public Node getRoot() { // ใช้เรียก root ในกรณีที่เรียกจาไฟล์อื่น ๆ
        return root;
    }
    
    protected Scanner sc = new Scanner(System.in); // เปิด scanner
    public GuessGame(String filename) throws IOException { // get root เรียกใช้จาก Tree Buildder
        root = TreeBuilder.buildTreeFromFile(filename);
    }

    public abstract void correctAnswer(); // method abstract
    public abstract void wrongAnswer(Node current) throws IOException; // method abstract

    public void playgame() throws IOException { // method playgame but terminal
        Stack<Node> pathNode = new Stack<>(); // เก็บ path
        Node current = root; // curNode ปัจจุบันที่อยู่
        while (!current.isleaf()) { // วิ่งจนกว่าจะไม่ใช่ leaf
            System.out.println(current.questionorname + " (yes/no/back)");
            String ans = sc.nextLine().trim().toLowerCase(); // รับคำตอบ yes no back
            if (ans.equals("yes")) { // ถ้าใช่
                pathNode.push(current); // เก็บเข้า stack pathNode
                current = current.right; // วิ่งไปเส้นขวา 
            } else if (ans.equals("no")) { // ถ้าไม่
                pathNode.push(current); // เก็บเข้า stack pathNode
                current = current.left; // วิ่งไปเส้นซ้าย
            } else if (ans.equals("back")) { // ถ้าจะย้อนกลับ
                if (!pathNode.empty()) { // เช็คว่าว่างไหม
                    current = pathNode.pop(); // ถ้าไม่ให้ pop ออก
                } else {
                    System.out.println("You are at the first Question!"); // ถ้ามันหมดแล้วให้ปริ้น
                }
            }
        }

        System.out.println("Is this person " + current.questionorname + "? (yes/no)"); 
        String finalans = sc.nextLine().trim().toLowerCase(); // check คำตอบสุดท้ายว่าตรงมั้ย
        if (finalans.equals("yes")) { // ถ้าใช่ก็เรียก method correctAnswer
            correctAnswer();
        } else {
            wrongAnswer(current); // ถ้าไม่ก็เรียก method wrongAnswer
            saveTreeToFile(root, "assets/data/questions.txt"); // ถ้าไม่แล้วเรียนรู้ใหม่แล้ว savefile 
        }
    }

    public void saveTreeToFile(Node node, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename)); // เรียกใช้ writer
        writeNode(node, writer, 0); // เรียก recursive
        writer.close();
    }

    private void writeNode(Node node, BufferedWriter writer, int indent) throws IOException { // สำหรับการเขียน Node ใหม่
        if (node == null) return; // base case
        String prefix = node.isleaf() ? "A:" : "Q:"; // เช็คว่าเป็น leaf ไหมจาก แล้วแทน prefix ลงไป
        String indentSpaces = " ".repeat(indent); 
        writer.write(indentSpaces + prefix + node.questionorname); // เรียกใช้
        writer.newLine();

        if (!node.isleaf()) { // วิ่งไปจนสุดท้าย
            writeNode(node.left, writer, indent + 2); // ลงเส้นซ้าย
            writeNode(node.right, writer, indent + 2); // ลงเส้นขวา
        }
    }
}
