

//*โค้ดรวมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมมม*//


// import java.io.*;
// import java.util.*;
// class Node{
//     String questionorname;
//     Node left; // ไป No
//     Node right;// ไป Yes

//     public Node(String questionorname){
//         this.questionorname = questionorname;
//         this.left = null;
//         this.right= null;
//     }

//     public boolean isleaf(){
//         return left==null && right==null;
//     }
// }
// class GuessGame {
    
//     Node root;
//     Scanner sc = new Scanner(System.in);

//     //ข้อมูลจากไฟล์ TXT
//     public class TreeBuilder { 
//         private static final String QUESTION_PREFIX = "Q:";
//         private static final String ANSWER_PREFIX = "A:";
    
//         private static class Line {
//             int indent;
//             String content;
    
//             Line(String line) {
//                 this.indent = countLeadingSpaces(line);
//                 this.content = line.trim();
//             }
    
//             private int countLeadingSpaces(String line) {
//                 int count = 0;
//                 while (count < line.length() && line.charAt(count) == ' ') count++;
//                 return count;
//             }
//         }
    
//         private static int index = 0;
    
//         public static Node buildTreeFromFile(String filename) throws IOException {
//             List<Line> lines = new ArrayList<>();
//             BufferedReader reader = new BufferedReader(new FileReader(filename));
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 if (!line.trim().isEmpty()) {
//                     lines.add(new Line(line));
//                 }
//             }
//             reader.close();
//             index = 0;
//             return buildNode(lines, 0);
//         }
    
//         private static Node buildNode(List<Line> lines, int currentIndent) {
//             if (index >= lines.size()) return null;
    
//             Line line = lines.get(index);
//             if (line.indent != currentIndent) return null;
    
//             index++;
//             Node node = new Node(line.content.substring(2)); // ลบ Q:/A:
    
//             if (line.content.startsWith(QUESTION_PREFIX)) {
//                 node.left = buildNode(lines, currentIndent + 2);  // No
//                 node.right = buildNode(lines, currentIndent + 2); // Yes
//             }
    
//             return node;
//         }
//     }

//     public void saveTreeToFile(Node node, String filename) throws IOException {
//         BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
//         writeNode(node, writer, 0);
//         writer.close();
//     }

//     private void writeNode(Node node, BufferedWriter writer, int indent) throws IOException {
//         if (node == null) return;
//         String prefix = node.isleaf() ? "A:" : "Q:";
//         String indentSpaces = " ".repeat(indent);
//         writer.write(indentSpaces + prefix + node.questionorname);
//         writer.newLine();

//         if (!node.isleaf()) {
//             writeNode(node.left, writer, indent + 2);
//             writeNode(node.right, writer, indent + 2);
//         }
//     }
    

//     public GuessGame(String filename) throws IOException {
//         root = TreeBuilder.buildTreeFromFile(filename); //ดึงคำถามจากFile txt
//     }
//     public void playgame()throws IOException{
//         Stack<Node> pathNode = new Stack<>(); //  Stack เก็บ path ของNode
//         Node current = root;
//         while(!current.isleaf()){
//             System.out.println(current.questionorname + "(yes/no/back)");
//             String ans = sc.nextLine().trim().toLowerCase();
//             if(ans.equals("yes")){
//                 pathNode.push(current);
//                 current = current.right;
//             }
//             else if (ans.equals("no")){
//                 pathNode.push(current);
//                 current = current.left;
//             }
//             else if(ans.equals("back")){
//                 if(!pathNode.empty()){
//                     current = pathNode.pop();
//                 }
//                 else{
//                     System.out.println("You are at the first Question!");
//                 }
//             }
//         }
//         System.out.println("is this person " + current.questionorname + "?" + "(yes/no)");
//         String finalans =  sc.nextLine().trim().toLowerCase();
//         if(finalans.equals("yes")){
//             System.out.println("I told you, I'm good at it eiei.");
//         }
//         else{
//             System.out.println("What ? Can You tell me Who? T_T");
//             String correctperson = sc.nextLine();
//             System.out.println("Please ask a question that distinguishes between " + current.questionorname + " and " + correctperson);
//             String newQuestion = sc.nextLine();

//             // เพิ่มข้อมูล ใน Tree
//             Node oldGuess = new Node(current.questionorname);
//             current.questionorname = newQuestion;

//             System.out.println("if answer is  'yes' it is " + correctperson + " ? (yes/no)");
//             String isYesForNewPerson = sc.nextLine().trim().toLowerCase();
//             if (isYesForNewPerson.equals("yes")) {
//                 current.right = new Node(correctperson);
//                 current.left = oldGuess;
//             } else {
//                 current.left = new Node(correctperson);
//                 current.right = oldGuess;
//             }
//             saveTreeToFile(root, "questions.txt");
//             System.out.println("Thank You i learned its! ");
//         }
//     }
// }
