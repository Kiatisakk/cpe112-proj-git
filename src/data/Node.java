package data;
public class Node {
    public String questionorname;
    public Node left; // No branch
    public Node right; // Yes branch

    public Node(String questionorname) {
        this.questionorname = questionorname;
        this.left = null;
        this.right = null;
    }

    public boolean isleaf() {
        return left == null && right == null;
    }
}