package ui;
import javax.swing.*;

import data.Node;
import logic.PersonGuessGame;

import java.awt.*;
import java.io.IOException;
import java.util.Stack;

public class JinnyGUI {
    private JFrame frame;
    private JLayeredPane layeredPane;
    private JLabel bgLabel, logoLabel, bodyLabel, body2Label, boxLabel, speechBubble;
    private JButton playButton, letsGoButton, yesButton, noButton, backButton;
    private MusicPlayer musicPlayer;
    private PersonGuessGame game;
    private Node currentNode;
    private Stack<Node> pathStack = new Stack<>();

    public static void main(String[] args) {
        new JinnyGUI();
    }

    public JinnyGUI() { 
        setupFrame();
        setupBackground();
        setupLogo();
        setupBody();
        setupPlayButton();
        setupMusic();
        setupLayeredPane();
        setupMuteButton();
        frame.setVisible(true);
    }
    
    private void setupFrame() { // setupFrame สำหรับใส่ component
        frame = new JFrame("Jinny Ja UI");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private void setupBackground() {  // setup Background
        ImageIcon bgIcon = new ImageIcon("assets/images/aaa.png");
        Image bgImage = bgIcon.getImage();
        bgLabel = new JLabel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    }

    private void setupLogo() { // setup logo game
        ImageIcon logoIcon = new ImageIcon("assets/images/jinnyja.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(400, -30, 600, 400);
    }

    private void setupBody() { // setup จินนี่จ้าสุดเท่
        ImageIcon bodyIcon = new ImageIcon("assets/images/bbb.png");
        Image bodyImage = bodyIcon.getImage().getScaledInstance(600, 700, Image.SCALE_SMOOTH);
        bodyLabel = new JLabel(new ImageIcon(bodyImage));
        bodyLabel.setBounds(-10, -40, 600, 700);
        ImageIcon body2Icon = new ImageIcon("assets/images/bbb2.png");
        Image body2Image = body2Icon.getImage().getScaledInstance(600, 700, Image.SCALE_SMOOTH);
        body2Label = new JLabel(new ImageIcon(body2Image));
        body2Label.setBounds(0, 100, 400, 600);
    }

    private void setupPlayButton() { // ปุ่มเล่น
        playButton = createRoundButton("Play", 40);
        playButton.setBounds(600, 400, 220, 90);
        playButton.addActionListener(e -> showLetsGoPage());
    }

    private void setupMusic() { // setup เพลง
        musicPlayer = new MusicPlayer();
        musicPlayer.playMusic("assets/audio/megh-raag.wav");
    }

    private void setupLayeredPane() { // ใช้สำหรับวางโครงซ้อน layer หลาย ๆ ชั้น
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 600));
        bgLabel.setSize(1000, 600);
        layeredPane.add(bgLabel, Integer.valueOf(0));
        layeredPane.add(logoLabel, Integer.valueOf(1));
        layeredPane.add(bodyLabel, Integer.valueOf(2));
        layeredPane.add(playButton, Integer.valueOf(3));
        frame.setContentPane(layeredPane);
    }

    private void setupMuteButton() { // ใช้สำหรับเปิดปิดเสียง
        ImageIcon muteIcon = new ImageIcon("assets/images/mute.png");
        Image muteImg = muteIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon unmuteIcon = new ImageIcon("assets/images/unmute.png");
        Image unmuteImg = unmuteIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton muteButton = new JButton(new ImageIcon(unmuteImg));
        muteButton.setBounds(20, 20, 40, 40);
        muteButton.setFocusPainted(false);
        muteButton.setContentAreaFilled(false);
        muteButton.setBorderPainted(false);
        muteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        muteButton.addActionListener(e -> {
            if (musicPlayer.isPlaying()) {
                musicPlayer.stopMusic();
                muteButton.setIcon(new ImageIcon(muteImg));
            } else {
                musicPlayer.playMusic("assets/audio/megh-raag.wav");
                muteButton.setIcon(new ImageIcon(unmuteImg));
            }
        });
        layeredPane.add(muteButton, Integer.valueOf(4));
    }

    private void showLetsGoPage() {  // สำหรับเริ่มเกม
        logoLabel.setVisible(false);
        bodyLabel.setVisible(false);
        playButton.setVisible(false);
        layeredPane.add(body2Label, Integer.valueOf(2));
        setupBox();
        speechBubble = new JLabel("<html><div style='text-align: center;'>Ay! Ay!<br>Who do you think of?<br>I will guess it!</div></html>");
        speechBubble.setFont(new Font("Georgia", Font.BOLD, 35));
        speechBubble.setForeground(Color.WHITE);
        speechBubble.setBounds(420, 50, 400, 200);
        speechBubble.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(speechBubble, Integer.valueOf(4));
        letsGoButton = createRoundButton("Let's Go!", 30);
        letsGoButton.setBounds(500, 340, 220, 90);
        layeredPane.add(letsGoButton, Integer.valueOf(4));
        letsGoButton.addActionListener(e -> startGame());
    }

    private void setupBox() { // สร้างกรอบข้อความสำหรับจินนี่พูด
        ImageIcon boxIcon = new ImageIcon("assets/images/box.png");
        Image boxImage = boxIcon.getImage().getScaledInstance(900, 550, Image.SCALE_SMOOTH);
        boxLabel = new JLabel(new ImageIcon(boxImage));
        boxLabel.setBounds(150, -120, 900, 550);
        layeredPane.add(boxLabel, Integer.valueOf(3));
    }

    private void setupSpeechBubble() { // สร้างฟ้อนกรอบข้อความที่จินนี่พูดออกมา
        speechBubble = new JLabel();
        speechBubble.setFont(new Font("Georgia", Font.BOLD, 35));
        speechBubble.setForeground(Color.WHITE);
        speechBubble.setBounds(420, 50, 400, 200);
        speechBubble.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(speechBubble, Integer.valueOf(4));
    }

    private void setupGameButtons() { // สร้างปุ่มสำหรับ ProcessGame Yes No Back
        yesButton = createRoundButton("Yes", 30);
        yesButton.setBounds(500, 350, 120, 80);
        yesButton.addActionListener(e -> processYes());
        layeredPane.add(yesButton, Integer.valueOf(4));
        noButton = createRoundButton("No", 30);
        noButton.setBounds(650, 350, 120, 80);
        noButton.addActionListener(e -> processNo());
        layeredPane.add(noButton, Integer.valueOf(4));
        backButton = createRoundButton("Back", 20);
        backButton.setBounds(850, 450, 100, 50);
        backButton.addActionListener(e -> processBack());
        layeredPane.add(backButton, Integer.valueOf(4));
    }

    private void startGame() { // สำหรับเริ่มเกม
        letsGoButton.setVisible(false); // ปิด letgobutton 
        speechBubble.setVisible(false); // ปิด speechBubble
        setupSpeechBubble(); // setup
        setupGameButtons(); // setup
        try {
            game = new PersonGuessGame("assets/data/questions.txt"); // เรียก PersonGueeGame
            currentNode = game.getRoot(); // ปรับ curNode เป็น root
            pathStack.clear(); // clear Stack 
            showCurrentQuestion(); // เริ่มแสดงความถาม
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void processYes() { // ถ้าตอบ yes
        if (currentNode.isleaf()) { // ถ้าเป็น leaf แล้ว
            Object[] options = {"OK!", "Exit"}; // มีให้เลือก OK Exit ถ้า OK Restart Game
            int choice = JOptionPane.showOptionDialog(
                frame, 
                "Yay! I guessed it right!\nDo you to try again?", 
                "JinnyJa",         
                JOptionPane.YES_NO_OPTION,   
                JOptionPane.QUESTION_MESSAGE,
                null,                       
                options,                    
                options[0] 
            );
            if (choice == JOptionPane.YES_OPTION) {
                restartGame(); // ok restart game
            } else if (choice == JOptionPane.NO_OPTION) {
                System.exit(0); // Exit ปิดหน้าต่าง
            }
        } else { // ถ้ายังไม่ถึง leaf
            pathStack.push(currentNode); // push curNode เข้าไปถ้าวิ่งไปถ้ายังไม่ถึง leaf
            currentNode = currentNode.right; // วิ่งไปทางขวา เพราะตอบ yes
            showCurrentQuestion(); // แสดง question
        }
    }

    private void processNo() { // ตอบ no
        if (currentNode.isleaf()) { // ถ้าตอบ no
            try { // เริ่มเรียนรู้ข้อมูลใหม่เพื่อไป update Tree
                String correctPerson = JOptionPane.showInputDialog(frame, "Who is it?"); // ถาม
                if (correctPerson == null || correctPerson.trim().isEmpty()) return; // รับคำตอบที่ถูก
                String newQuestion = JOptionPane.showInputDialog(frame, "Please provide a questio n that separates " + currentNode.questionorname + " and " + correctPerson); // ถามว่าต่างกันยังไง
                if (newQuestion == null || newQuestion.trim().isEmpty()) return; // รับคำถามว่าต่างจากอีกคนยังไง
                Node oldGuess = new Node(currentNode.questionorname); // เก็บ old
                currentNode.questionorname = newQuestion; // set คำถามใหม่เป็น node แทน
                int reply = JOptionPane.showConfirmDialog(frame, "If the answer is 'yes', is it " + correctPerson + "?", "Confirm", JOptionPane.YES_NO_OPTION); // คิดว่าจะวางซ้ายหรือขวา
                if (reply == JOptionPane.YES_OPTION) { // ถ้าตอบ yes
                    currentNode.right = new Node(correctPerson); // คนใหม่ที่แอดเข้ามาอยู่ขวา
                    currentNode.left = oldGuess; // คนเก่าอยู่ซ้าย
                } else { // ถ้าไม่
                    currentNode.left = new Node(correctPerson); // คนใหม่ที่แอดเข้ามาอยู่ซ้าย
                    currentNode.right = oldGuess; // คนเก่าอยู่ขวา
                }
                game.saveTreeToFile(game.getRoot(), "assets/data/questions.txt"); // เรียก savefile
                Object[] options = {"OK!", "Exit"}; // ให้เลือก OK Exit ว่าจะเริ่มหรือจะออกเกมเลย
                int choice = JOptionPane.showOptionDialog(
                    frame, 
                    "Thanks! I learned something new!\nDo you to try again?", 
                    "JinnyJa",         
                    JOptionPane.YES_NO_OPTION,   
                    JOptionPane.QUESTION_MESSAGE,
                    null,                       
                    options,                    
                    options[0] 
                );
                if (choice == JOptionPane.YES_OPTION) { // ถ้าใช่เริ่มใหม่
                    restartGame();
                } else if (choice == JOptionPane.NO_OPTION) { // ถ้าไม่ปิดเลย
                    System.exit(0);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else { // ถ้ายังไม่ถึง leaf
            pathStack.push(currentNode); // เก็บเข้า Stack
            currentNode = currentNode.left; // วิ่งไปทางซ้าย
            showCurrentQuestion(); // แสดง
        }
    }

    private void processBack() { // ไว้สำหรับย้อนกลับ
        if (!pathStack.isEmpty()) { // ถ้า Stack ไม่ว่าง
            currentNode = pathStack.pop(); // pop ออกเพื่อย้อนกลับ
            showCurrentQuestion(); // แสดงคำถาม
        } else {
            JOptionPane.showMessageDialog(frame, "You are at the first question!"); // ให้ alert ว่ายุคำถามแรกแล้ว
        }
    }

    private void showCurrentQuestion() { // ไว้แสดงคำถามและคำตอบ
        if (currentNode.isleaf()) { // ถ้า leaf จะปริ้นชื่อคนออกมา
            speechBubble.setText("<html><div style='text-align: center;'>Is it<br>" + currentNode.questionorname + "?</div></html>");
        } else { //ปริ้นคำถามออกมา
            speechBubble.setText("<html><div style='text-align: center;'>" + currentNode.questionorname + "</div></html>");
        }
    }

    private void restartGame() {
        currentNode = game.getRoot(); // ปรับ node เปน root
        pathStack.clear(); // clear stack
        showCurrentQuestion(); // เริ่ม question ได้เลย
    }

    private JButton createRoundButton(String text, int fontSize) { // สำหรับเรียกใช้ในการสร้างปุ่ม
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isPressed()) {
                    g.setColor(new Color(255, 128, 0));
                } else if (getModel().isRollover()) {
                    g.setColor(new Color(255, 160, 40));
                } else {
                    g.setColor(new Color(255, 140, 0));
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Georgia", Font.BOLD, fontSize));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}