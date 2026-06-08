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
    private Shop shop;

    public AdventureGUI(Game game) {
        this.game = game;
        this.game.setGUI(this);
        this.shop = new Shop();
        buildGUI();
    }

    private void buildGUI() {
        frame = new JFrame("Text Adventure Game");
        frame.getContentPane().setBackground(new Color(77, 32, 40));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(77, 32, 40));

        frame.add(imageLabel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 141, 22), 5),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)));
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        // COLOURS
        outputArea.setBackground(new Color(77, 32, 40));
        outputArea.setForeground(new Color(230, 194, 124));
        outputArea.setFont(new Font("Cambria", Font.PLAIN, 21));

        JScrollPane scrollPane = new JScrollPane(outputArea);

        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();

        verticalBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(203, 141, 22);
                this.trackColor = new Color(77, 32, 40);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        });

        frame.add(scrollPane, BorderLayout.CENTER);

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
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        printText(game.getCurrentRoomDescription());
        updateRoomDisplay();
    }

    public void handleInput() {
        if (game.isFightActive()) {
            game.getFights();
            this.handleInput(game.getFights().getMob());
            if (!(game.getIsWaiting())) {
                if (!(game.getFights().runTurn())) {
                    game.endFight();
                }
                return;
            }
        }
        this.handleInput(null);
    }

    public void handleInput(HostileMob target) {
        String input = inputField.getText().trim();
        inputField.setText("");

        if (!input.isEmpty()) {
            printText("> " + input);

            if (input.equalsIgnoreCase("shop")) {
                shop.openShop(game.getPlayer());
            } else {
                printText(game.processCommand(input, target));
            }

            updateRoomDisplay();
        }
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
    }

    private void updateRoomDisplay() {

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