package Game;

import javax.swing.*;
import Mobs.HostileMob;
import java.awt.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class AdventureGUI {
    private JFrame frame;
    private JTextArea outputArea;
    private JTextField inputField;
    private JLabel imageLabel;
    private Game game;
    private JPanel startScreen;
    private JScrollPane scrollPane;
    private JPanel endScreen;

    public AdventureGUI(Game game) {
        this.game = game;
        this.game.setGUI(this);
        buildGUI();
    }

    private void buildGUI() {
        // Start screen
        startScreen = new JPanel();
        startScreen.setLayout(new BorderLayout());
        startScreen.setPreferredSize(new Dimension(800, 600));
        startScreen.setBackground(new Color(77, 32, 40));

        ImageIcon titleImage = new ImageIcon("images/start_screen.png");
        Image start_screen = titleImage.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledImage = new ImageIcon(start_screen);

        JLabel bgLabel = new JLabel(scaledImage);
        bgLabel.setHorizontalAlignment(JLabel.CENTER);
        startScreen.add(bgLabel, BorderLayout.CENTER);

        JLabel title = new JLabel("Elixir of the Alligator", JLabel.CENTER);
        title.setFont(new Font("Cambria", Font.BOLD, 72));
        title.setForeground(new Color(203, 141, 22));
        title.setHorizontalAlignment(JLabel.CENTER);

        startScreen.add(title, BorderLayout.NORTH);

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Cambria", Font.BOLD, 56));
        startButton.setBackground(new Color(77, 32, 40));
        startButton.setForeground(new Color(230, 194, 124));
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 141, 22), 4),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)));

        startButton.addActionListener(e -> startGame());
        startScreen.add(startButton, BorderLayout.SOUTH);

        frame = new JFrame("Elixir of the Alligator");
        ImageIcon icon = new ImageIcon("images/EA_icon.png");
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(new Color(77, 32, 40));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // In-game gui
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(77, 32, 40));

        outputArea = new JTextArea();
        outputArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 141, 22), 5),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)));
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        outputArea.setBackground(new Color(77, 32, 40));
        outputArea.setForeground(new Color(230, 194, 124));
        outputArea.setFont(new Font("Cambria", Font.PLAIN, 21));

        scrollPane = new JScrollPane(outputArea);

        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();

        verticalBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(203, 141, 22);
                this.trackColor = new Color(77, 32, 40);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        });

        scrollPane.getViewport().setBackground(new Color(77, 32, 40));
        scrollPane.setBackground(new Color(77, 32, 40));

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(new Color(77, 32, 40));

        inputField = new JTextField();
        inputField.setFont(new Font("Cambria", Font.BOLD, 21));
        inputField.setBackground(new Color(230, 194, 124));
        inputField.setForeground(new Color(77, 32, 40));
        inputField.setCaretColor(new Color(77, 32, 40));
        inputField.setBorder(BorderFactory.createLineBorder(new Color(230, 194, 124)));

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(110, 35));
        submitButton.setFont(new Font("Cambria", Font.BOLD, 25));
        submitButton.setBackground(new Color(203, 141, 22));
        submitButton.setForeground(new Color(230, 194, 124));
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createLineBorder(new Color(230, 194, 124)));

        submitButton.addActionListener(e -> handleInput());
        inputField.addActionListener(e -> handleInput());

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.EAST);

        frame.add(startScreen, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // End screen
        endScreen = new JPanel();
        endScreen.setBackground(new Color(77, 32, 40));
        endScreen.setLayout(new BorderLayout());

        JLabel endText = new JLabel("The end... until next time! Thank you for playing!", JLabel.CENTER);
        endText.setFont(new Font("Cambria", Font.BOLD, 56));
        endText.setForeground(new Color(203, 141, 22));

        endScreen.add(endText, BorderLayout.CENTER);

        frame.add(endScreen, BorderLayout.CENTER);
        endScreen.setVisible(false);

        frame.setVisible(true);
        updateRoomDisplay();
    }

    public void handleInput() {
        HostileMob target = null;
        if (game.isFightActive() && game.getFights() != null) {
            target = game.getFights().getMob();
        }

        String input = inputField.getText().trim();
        inputField.setText("");

        if (!input.isEmpty()) {
            printText("> " + input);

            String response = game.processCommand(input, target);
            if (response != null && !response.isEmpty()) {
                printText(response);
            }

            updateRoomDisplay();
        }
    }

    private void startGame() {

        if (startScreen != null && startScreen.getParent() != null) {
            frame.remove(startScreen);
        }

        frame.add(imageLabel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();

        SwingUtilities.invokeLater(() -> {
            game.start();
            updateRoomDisplay();
            inputField.requestFocusInWindow();
        });
    }

    public void endScreen() {
        startScreen.setVisible(false);
        imageLabel.setVisible(false);
        scrollPane.setVisible(false);
        inputField.setVisible(false);
        endScreen.setVisible(true);
    }

    public String handleInputNoCmdParser() {
        String input = inputField.getText().trim();
        inputField.setText("");

        if (!input.isEmpty()) {
            printText("> " + input);
        }
        return input;
    }

    public void printText(String text) {
        outputArea.append(text + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    void updateRoomDisplay() {
        String roomId = game.getPlayer().getCurrentRoomId();
        try {
            ImageIcon icon = new ImageIcon("images/" + roomId + ".png");
            Image img = icon.getImage().getScaledInstance(800, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imageLabel.setIcon(null);
        }
    }
}