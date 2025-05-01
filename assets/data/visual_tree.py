from graphviz import Digraph

# Step 1: Parse text into a tree structure
class TreeNode:
    def __init__(self, text):
        self.text = text
        self.children = []

def parse_tree(lines):
    root = TreeNode("Root")
    stack = [(root, -1)]  # Stack to track nodes and their levels
    for line in lines:
        stripped = line.lstrip()
        if not stripped: 
            continue  # Skip empty lines
        level = (len(line) - len(stripped)) // 2  # Determine level based on indentation
        node = TreeNode(stripped)
        while stack and stack[-1][1] >= level:
            stack.pop()  # Pop nodes until we find the correct parent
        stack[-1][0].children.append(node)  # Add the new node as a child
        stack.append((node, level))  # Push the new node onto the stack
    return root

# Step 2: Render with Graphviz
def add_nodes_edges(dot, node, parent_id=None, counter=[0]):
    node_id = str(counter[0])
    counter[0] += 1
    dot.node(node_id, node.text)  # Add the current node
    if parent_id is not None:
        dot.edge(parent_id, node_id)  # Add an edge from the parent to the current node
    for child in node.children:
        add_nodes_edges(dot, child, node_id, counter)

def visualize_tree(tree_root):
    dot = Digraph()
    dot.attr(rankdir='TB')  # Top-to-bottom layout
    dot.attr(nodesep='0.5')  # Adjust horizontal spacing
    dot.attr(ranksep='0.75')  # Adjust vertical spacing
    add_nodes_edges(dot, tree_root)
    return dot

# Read tree structure from file
file_path = 'assets/data/questions.txt'  # Relative path to the file
with open(file_path, 'r') as file:
    lines = file.readlines()

# Parse and render
tree_root = parse_tree(lines)
dot = visualize_tree(tree_root)

# Save and/or display
output_path = 'assets/data/visual/visual'  # Updated output path
dot.render(output_path, format='png', cleanup=True)
dot.view()