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

    private void setupFrame() {
        frame = new JFrame("Jinny Ja UI");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private void setupBackground() {
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

    private void setupLogo() {
        ImageIcon logoIcon = new ImageIcon("assets/images/jinnyja.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(400, -30, 600, 400);
    }

    private void setupBody() {
        ImageIcon bodyIcon = new ImageIcon("assets/images/bbb.png");
        Image bodyImage = bodyIcon.getImage().getScaledInstance(600, 700, Image.SCALE_SMOOTH);
        bodyLabel = new JLabel(new ImageIcon(bodyImage));
        bodyLabel.setBounds(-10, -40, 600, 700);

        ImageIcon body2Icon = new ImageIcon("assets/images/bbb2.png");
        Image body2Image = body2Icon.getImage().getScaledInstance(600, 700, Image.SCALE_SMOOTH);
        body2Label = new JLabel(new ImageIcon(body2Image));
        body2Label.setBounds(0, 100, 400, 600);
    }

    private void setupPlayButton() {
        playButton = createRoundButton("Play", 40);
        playButton.setBounds(600, 400, 220, 90);
        playButton.addActionListener(e -> showLetsGoPage());
    }

    private void setupMusic() {
        musicPlayer = new MusicPlayer();
        musicPlayer.playMusic("assets/audio/megh-raag.wav");
    }

    private void setupLayeredPane() {
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 600));
        bgLabel.setSize(1000, 600);

        layeredPane.add(bgLabel, Integer.valueOf(0));
        layeredPane.add(logoLabel, Integer.valueOf(1));
        layeredPane.add(bodyLabel, Integer.valueOf(2));
        layeredPane.add(playButton, Integer.valueOf(3));

        frame.setContentPane(layeredPane);
    }

    private void setupMuteButton() {
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

    private void showLetsGoPage() {
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

    private void startGame() {
        letsGoButton.setVisible(false);
        speechBubble.setVisible(false);

        setupSpeechBubble();
        setupGameButtons();

        try {
            game = new PersonGuessGame("assets/data/questions.txt");
            currentNode = game.getRoot();
;
            pathStack.clear();
            showCurrentQuestion();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setupBox() {
        ImageIcon boxIcon = new ImageIcon("assets/images/box.png");
        Image boxImage = boxIcon.getImage().getScaledInstance(900, 550, Image.SCALE_SMOOTH);
        boxLabel = new JLabel(new ImageIcon(boxImage));
        boxLabel.setBounds(150, -120, 900, 550);
        layeredPane.add(boxLabel, Integer.valueOf(3));
    }

    private void setupSpeechBubble() {
        speechBubble = new JLabel();
        speechBubble.setFont(new Font("Georgia", Font.BOLD, 35));
        speechBubble.setForeground(Color.WHITE);
        speechBubble.setBounds(420, 50, 400, 200);
        speechBubble.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(speechBubble, Integer.valueOf(4));
    }

    private void setupGameButtons() {
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

    private void processYes() {
        if (currentNode.isleaf()) {
            JOptionPane.showMessageDialog(frame, "Yay! I guessed it right!");
            restartGame();
        } else {
            pathStack.push(currentNode);
            currentNode = currentNode.right;
            showCurrentQuestion();
        }
    }

    private void processNo() {
        if (currentNode.isleaf()) {
            try {
                String correctPerson = JOptionPane.showInputDialog(frame, "Who is it?");
                if (correctPerson == null || correctPerson.trim().isEmpty()) return;

                String newQuestion = JOptionPane.showInputDialog(frame, "Please provide a question that separates " + currentNode.questionorname + " and " + correctPerson);
                if (newQuestion == null || newQuestion.trim().isEmpty()) return;

                Node oldGuess = new Node(currentNode.questionorname);
                currentNode.questionorname = newQuestion;

                int reply = JOptionPane.showConfirmDialog(frame, "If the answer is 'yes', is it " + correctPerson + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    currentNode.right = new Node(correctPerson);
                    currentNode.left = oldGuess;
                } else {
                    currentNode.left = new Node(correctPerson);
                    currentNode.right = oldGuess;
                }

                game.saveTreeToFile(game.getRoot(), "assets/data/questions.txt");
                JOptionPane.showMessageDialog(frame, "Thanks! I learned something new!");
                restartGame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            pathStack.push(currentNode);
            currentNode = currentNode.left;
            showCurrentQuestion();
        }
    }

    private void processBack() {
        if (!pathStack.isEmpty()) {
            currentNode = pathStack.pop();
            showCurrentQuestion();
        } else {
            JOptionPane.showMessageDialog(frame, "You are at the first question!");
        }
    }

    private void showCurrentQuestion() {
        if (currentNode.isleaf()) {
            speechBubble.setText("<html><div style='text-align: center;'>Is it<br>" + currentNode.questionorname + "?</div></html>");
        } else {
            speechBubble.setText("<html><div style='text-align: center;'>" + currentNode.questionorname + "</div></html>");
        }
    }

    private void restartGame() {
        currentNode = game.getRoot();
        pathStack.clear();
        showCurrentQuestion();
    }

    private JButton createRoundButton(String text, int fontSize) {
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