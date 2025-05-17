package data;
import java.io.*;
import java.util.*;

// เป็นฟังชั่นสำหรับการอ่านไฟล์ question.txt ใน assets\data\question.txt เพื่อนำมาสร้างเป็น Tree

public class TreeBuilder {
    private static final String QUESTION_PREFIX = "Q:"; // เก็บ suffix
    private static int index = 0; // locate position

    private static class Line { // structor ใหม่ชื่อ line สำหรับการคำนวณ indent และเก็บค่า
        int indent;
        String content;

        Line(String line) { // constructor
            this.indent = countLeadingSpaces(line);
            this.content = line.trim();
        }

        private int countLeadingSpaces(String line) { // ใข้สำหรับคำนวณ indent เพื่อให้รู้ว่า node อยู่ level ไหน
            int count = 0;
            while (count < line.length() && line.charAt(count) == ' ') count++;
            return count;
        }
    }

    public static Node buildTreeFromFile(String filename) throws IOException { // สำหรับอ่านไฟล์
        List<Line> lines = new ArrayList<>(); // สร้าง list โดยใช้ structor line มาเก็บ
        BufferedReader reader = new BufferedReader(new FileReader(filename)); // สำหรับ read file
        String line; 
        while ((line = reader.readLine()) != null) { // อ่านแล้วเก็บเข้า list 
            if (!line.trim().isEmpty()) {
                lines.add(new Line(line));
            }
        }
        reader.close(); // ปิด reader
        index = 0; // set index to 0
        return buildNode(lines, 0); // recursive สำหรับสร้าง node ใหม่
    }

    private static Node buildNode(List<Line> lines, int currentIndent) {
        if (index >= lines.size()) return null; // เช็คว่าหมดมั้ย
        Line line = lines.get(index);
        if (line.indent != currentIndent) return null; // check indent ว่าตรงมัั้ย

        index++;
        Node node = new Node(line.content.substring(2)); // สร้าง node ใหม่

        if (line.content.startsWith(QUESTION_PREFIX)) { // ถ้าเป็น question ให้สร้างลูกด้วย
            node.left = buildNode(lines, currentIndent + 2);  // No
            node.right = buildNode(lines, currentIndent + 2); // Yes
        }

        return node; // return node ที่สร้างเรียบร้อย
    }
}
