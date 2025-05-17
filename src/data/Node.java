package data;
// class Node สำหรับเก็บว่า question หรือ name
public class Node {
    public String questionorname; // เก็บชื่อ question or name
    public Node left; // No branch
    public Node right; // Yes branch
    
    // constructor เก็บ name right left
    public Node(String questionorname) {
        this.questionorname = questionorname;
        this.left = null; // no branch
        this.right = null; // yes branch
    }
    
    // เชฺ็คว่าเป็น leaf มั้ยเพื่อเช็คว่่าเป็น question หรือ name
    public boolean isleaf() {
        return left == null && right == null; 
    }
}