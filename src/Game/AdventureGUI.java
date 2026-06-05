package Game;
import javax.swing.*;

import Mobs.HostileMob;

import java.awt.*;

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(imageLabel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        // COLOURS
        outputArea.setBackground(new Color(77, 32, 40));
        outputArea.setForeground(new Color(230, 194, 124));
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        JButton submitButton = new JButton("Submit");

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