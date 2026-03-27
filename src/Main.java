import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Strange Sushi Game");
        frame.setSize(1000, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //----------------------- Menu Panel -----------------------------
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(Color.BLACK); // black background

        // title label
        JLabel titleLabel = new JLabel("Strange Sushi Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);

        JButton howtoBtn = new JButton("How to Play");
        howtoBtn.setFont(new Font("Courier New", Font.BOLD, 24));
        howtoBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"How to play: \n" +
                    " 1. Choose all the ingredient to make Sushi!" + "\n" +
                    " 2. Then click 'Submit' to serve order" + "\n" +
                    " 3. Made mistake? Click 'Clear' to choose ingredient again" + "\n" +
                    " 4. Click 'Recipe' to look at the recipe"
                    ,"How to play?",JOptionPane.INFORMATION_MESSAGE);
        });

        // start button
        JButton startBtn = new JButton("Start Game");
        startBtn.setFont(new Font("Courier New", Font.BOLD, 24));

        // center the button
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.BLACK);
        btnPanel.add(startBtn);
        btnPanel.add(howtoBtn);

        menuPanel.add(titleLabel, BorderLayout.CENTER);
        menuPanel.add(btnPanel, BorderLayout.SOUTH);

        // switch to game on click
        startBtn.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(new GamePannel(frame));
            frame.revalidate();
            frame.repaint();
        });

        frame.add(menuPanel);
        frame.setVisible(true);
    }
}