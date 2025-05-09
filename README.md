# CPE112 Project

## How to Run the Project

### On Windows
1. Open the `run.bat` file located in the root directory.
2. Double-click the file to compile and run the project automatically.

### On macOS/Linux
1. Open a terminal and navigate to the project directory.
2. Run the following command to execute the `run.sh` script:
   ```bash
   ./run.sh
   ```

### Requirements
- Java Development Kit (JDK) installed.
- Graphviz installed (for visualizing the tree).

---

## About `visual_tree.py`

The `visual_tree.py` script is located in `assets\data\visual_tree.py`. It is used to:
1. Parse the decision tree structure from `assets\data\questions.txt`.
2. Visualize the tree using Graphviz.
3. Save the visualization as a PNG file in `assets\data\visual\visual.png`.

### How to Use
1. Ensure Graphviz is installed on your system.
2. Run the script using Python:
   ```bash
   python assets/data/visual_tree.py
   ```
3. The tree visualization will be saved as `visual.png` in the `assets\data\visual` directory and opened automatically.

### Purpose
The visualization helps to:
- Understand the structure of the decision tree.
- Debug or verify the relationships between questions and answers.

Better. 