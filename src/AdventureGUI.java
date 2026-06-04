import javax.swing.*;
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
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input field
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        JButton submitButton = new JButton("Submit");

        // Simple student-level event handlers
        submitButton.addActionListener(e -> handleInput());
        inputField.addActionListener(e -> handleInput());

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        
        // Starts the game text display
        printText(game.getCurrentRoomDescription());
        updateRoomDisplay();
    }

    private void handleInput() {
        String input = inputField.getText().trim();
        inputField.setText("");
        
        if (!input.isEmpty()) {
            printText("> " + input);
            
            // HOOK: If the player types "shop", open your visual popup shop!
            if (input.equalsIgnoreCase("shop")) {
                shop.openShop(game.getPlayer());
            } else {
                // Otherwise, pass it to the game engine like normal
                printText(game.processCommand(input));
            }
            
            updateRoomDisplay();
        }
    }

    private void printText(String text) {
        outputArea.append(text + "\n");
    }

    private void updateRoomDisplay() {
        // Safe check: loads images dynamically matching the Room IDs
        String roomId = game.getPlayer().getCurrentRoomId();
        try {
            ImageIcon icon = new ImageIcon("images/" + roomId + ".png");
            Image img = icon.getImage().getScaledInstance(800, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            // Safe fallback if an image is missing so your game doesn't crash on the teacher
            imageLabel.setIcon(null); 
        }
    }
}