package data;
import java.io.*;
import java.util.*;

public class TreeBuilder {
    private static final String QUESTION_PREFIX = "Q:";
    private static int index = 0;

    private static class Line {
        int indent;
        String content;

        Line(String line) {
            this.indent = countLeadingSpaces(line);
            this.content = line.trim();
        }

        private int countLeadingSpaces(String line) {
            int count = 0;
            while (count < line.length() && line.charAt(count) == ' ') count++;
            return count;
        }
    }

    public static Node buildTreeFromFile(String filename) throws IOException {
        List<Line> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                lines.add(new Line(line));
            }
        }
        reader.close();
        index = 0;
        return buildNode(lines, 0);
    }

    private static Node buildNode(List<Line> lines, int currentIndent) {
        if (index >= lines.size()) return null;
        Line line = lines.get(index);
        if (line.indent != currentIndent) return null;

        index++;
        Node node = new Node(line.content.substring(2));

        if (line.content.startsWith(QUESTION_PREFIX)) {
            node.left = buildNode(lines, currentIndent + 2);  // No
            node.right = buildNode(lines, currentIndent + 2); // Yes
        }

        return node;
    }
}
