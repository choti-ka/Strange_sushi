import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePannel extends JPanel { //to make GamePanel class panel **doesn't have to add JPanel
    private final ArrayList<String> playerSelection; // tracks what player clicked

    private final GameManager gameManager;  // to talk to GameManager
    private Customer currentCustomer; // who is currently at the counter
    private final JLabel customerLabel;     // shows customer image
    private final JLabel coinLabel;         // shows current money
    private final JLabel dayLabel;          // shows current day
    private final Recipe recipe;            // for random dish orders
    private final JLabel timerLabel;        // shows timer countdown
    private Timer gameTimer;          // counts down customer timer
    private final JLabel orderLabel;        // customer order
    private final JLabel selectionLabel;    // shows what player has clicked
    private boolean jumpscareTriggered = false; // prevents double jumpscare

    //----------------------- Background -----------------------------
    // background image
    private final Image bgImage = new ImageIcon("bg.png").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
    //----------------------- Style Helper -----------------------------
    // reusable method to set font and color for any label
    private void styleLabel(JLabel label, int size) {
        label.setFont(new Font("Courier New", Font.BOLD, size));
        label.setForeground(Color.WHITE);
    }
    //----------------------- Game Panel -----------------------------
    public GamePannel(JFrame frame) {
        gameManager = new GameManager();
        gameManager.setCoin();   // start money
        gameManager.setRent();   // start rent
        recipe = new Recipe();
        playerSelection = new ArrayList<>();
        //----------------------- JLabel ----------------------------
        coinLabel = new JLabel("Coins: 500    ");
        dayLabel = new JLabel("Day: 1");
        timerLabel = new JLabel("Time: 30");
        customerLabel = new JLabel();
        selectionLabel = new JLabel("Selected: none"); // shows clicked ingredient

        styleLabel(coinLabel, 18);
        styleLabel(dayLabel, 18);
        styleLabel(timerLabel, 18);
        selectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        styleLabel(selectionLabel, 24);


        setLayout(new BorderLayout());
        //----------------------- Top Panel 2 rows -----------------------------
        // row 1: coins and day | row 2: timer on its own line
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(800, 80));

        JPanel row1 = new JPanel(); // coins and day row
        row1.setOpaque(false);
        row1.add(coinLabel);
        row1.add(dayLabel);

        JPanel row2 = new JPanel(); // timer row
        row2.setOpaque(false);
        row2.add(timerLabel);

        topPanel.add(row1);
        topPanel.add(row2);
        add(topPanel, BorderLayout.NORTH);
        //----------------------- JLabel Ingredients -----------------------------
        JButton riceBtn = new JButton("Rice");
        riceBtn.setPreferredSize(new Dimension(100, 40));
        riceBtn.addActionListener(e -> {
            playerSelection.add("Rice");
            updateSelection();
        });
        JButton ikuraBtn = new JButton("Ikura");
        ikuraBtn.setPreferredSize(new Dimension(100, 40));
        ikuraBtn.addActionListener(e -> {
            playerSelection.add("Ikura");
            updateSelection();
        });
        JButton crabBtn = new JButton("Crab");
        crabBtn.setPreferredSize(new Dimension(100, 40));
        crabBtn.addActionListener(e -> {
            playerSelection.add("Crab");
            updateSelection();
        });
        JButton shrimpBtn = new JButton("Shrimp");
        shrimpBtn.setPreferredSize(new Dimension(100, 40));
        shrimpBtn.addActionListener(e -> {
            playerSelection.add("Shrimp");
            updateSelection();
        });
        JButton tunaBtn = new JButton("Tuna");
        tunaBtn.setPreferredSize(new Dimension(100, 40));
        tunaBtn.addActionListener(e -> {
            playerSelection.add("Tuna");
            updateSelection();
        });
        JButton seaweedBtn = new JButton("Seaweed");
        seaweedBtn.setPreferredSize(new Dimension(100, 40));
        seaweedBtn.addActionListener(e -> {
            playerSelection.add("Seaweed");
            updateSelection();
        });
        JButton salmonBtn = new JButton("Salmon");
        salmonBtn.setPreferredSize(new Dimension(100, 40));
        salmonBtn.addActionListener(e -> {
            playerSelection.add("Salmon");
            updateSelection();
        });
        //---------------------- Other Buttons -----------------------------
        JButton submitBtn = new JButton("Submit");
        submitBtn.setPreferredSize(new Dimension(125, 30));
        submitBtn.addActionListener(e -> checkOrder());

        JButton recipeBtn = new JButton("Recipe Book");
        recipeBtn.setPreferredSize(new Dimension(125, 30));
        recipeBtn.addActionListener(e -> showRecipe());

        JButton clearBtn = new JButton("Clear");
        clearBtn.setPreferredSize(new Dimension(125, 30));
        clearBtn.addActionListener(e -> {
            playerSelection.clear();
            selectionLabel.setText("Selected: none");

        });
        //----------------------- Bottom Panel -----------------------------
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setOpaque(false);

        JPanel ingredientPanel = new JPanel(); // row 1
        ingredientPanel.setOpaque(false);
        ingredientPanel.add(riceBtn);
        ingredientPanel.add(shrimpBtn);
        ingredientPanel.add(salmonBtn);
        ingredientPanel.add(tunaBtn);
        ingredientPanel.add(crabBtn);
        ingredientPanel.add(ikuraBtn);
        ingredientPanel.add(seaweedBtn);

        JPanel actionPanel = new JPanel(); // row 2
        actionPanel.setOpaque(false);
        actionPanel.add(recipeBtn);
        actionPanel.add(clearBtn);
        actionPanel.add(submitBtn);


        bottomPanel.add(ingredientPanel); // ingredients on top
        bottomPanel.add(actionPanel);     // submit and recipe below
        add(bottomPanel, BorderLayout.SOUTH);
        //----------------------- Center Panel -----------------------------
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);

        orderLabel = new JLabel("Order: ", SwingConstants.CENTER); // centered text
        styleLabel(orderLabel, 22);
        centerPanel.add(orderLabel, BorderLayout.NORTH);     // order on top
        centerPanel.add(customerLabel, BorderLayout.CENTER); // customer in middle
        centerPanel.add(selectionLabel, BorderLayout.SOUTH); // selection at bottom
        add(centerPanel, BorderLayout.CENTER);
        SoundManager.playBGM(); // start music
        spawnCustomer();        // spawn first customer
    }
    //----------------------- Update Selection Label -----------------------------
    // shows what ingredients player has clicked so far
    private void updateSelection() {
        selectionLabel.setText("Selected: " + String.join(" + ", playerSelection));
    }
    //----------------------- Check Order -----------------------------
    public void checkOrder() {
        ArrayList<String> required = currentCustomer.getOrder().getIngredients();
        if (playerSelection.size() == required.size() && playerSelection.containsAll(required))
            // doesn't allow player to click same things twice and pass
        {
            if(gameManager.getDay()>3){
               gameManager.addCoin((gameManager.getDay()*12)-gameManager.getDay()); //more day = more income per order ex day 5 = 5*12 = 60 - 5 = 55
            } else {
                 gameManager.addCoin(45); // correct = add coin
            }
            gameManager.addCustomer();
            updateLabels();
            if (gameManager.getCustomerCount() >= 5) { // 5 customer per days
                endDay();
            } else {
                spawnCustomer();
            }
        } else {
            if(gameManager.getDay() > 3) {
                currentCustomer.react(gameManager, 50); // harder days = bigger penalty = mistake -> pay more
            } else {
                currentCustomer.react(gameManager); // normal penalty days 1-3
            }
            playerSelection.clear();                  // reset after wrong order
            selectionLabel.setText("Selected: none"); // reset label

            if (currentCustomer instanceof Ghost ghost) { // check if jumpscare should trigger
                if (ghost.isTriggerJumpscare()) {
                    triggerJumpscare();
                }
            } else {
                // regular customer anger meter -> rage quits
                if (currentCustomer.getMistakeCount() >= 3) {
                    gameManager.takeCoin(50); // extra penalty
                    spawnCustomer();          // customer leaves angry
                    JOptionPane.showMessageDialog(null,"Customer Angry!\n" + "They just left..." ,
                            "Customer left",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    //----------------------- Spawn Customer -----------------------------
    public void spawnCustomer() {
        jumpscareTriggered = false; // reset jumpscare flag for new customer
        if (gameManager.isGameOver()) { //check game over before spawn new customer
            SoundManager.playGameOver();
            JOptionPane.showMessageDialog(null, "GAME OVER!");
            System.exit(0);
            return;
        }
        Ghost ghost = gameManager.spawnGhost();
          if (ghost != null) {
            currentCustomer = ghost; // spawn ghost if chance hits
            SoundManager.stopBGM(); // stop normal music
            SoundManager.playSus(); // play sus music for ghost!
        } else {
            currentCustomer = new Customer(); // spawn regular customer
            SoundManager.stopSus();  // stop sus music
            SoundManager.playBGM(); // make sure BGM playing for regular customer
        }
        currentCustomer.setOrder(recipe.getRandomDish()); // set new random order
        orderLabel.setText("Order: " + currentCustomer.getOrder().getDishName());
        currentCustomer.setTimeLimit(25);

        // scale customer image to fit screen
        ImageIcon icon = new ImageIcon(currentCustomer.getCustomerPic());
        Image scaled = icon.getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH);
        customerLabel.setIcon(new ImageIcon(scaled));

        playerSelection.clear();                  // clear old selection
        selectionLabel.setText("Selected: none"); // reset selection label
        startTimer();
    }
    //----------------------- Recipe Book -----------------------------
    // shows recipe image as popup
    public void showRecipe() {
        ImageIcon recipeImage = new ImageIcon("Recipe.png");
        Image scaled = recipeImage.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        JOptionPane.showMessageDialog(null,
                "",
                "Recipe Book",
                JOptionPane.PLAIN_MESSAGE,
                new ImageIcon(scaled));
    }
    //----------------------- Update Labels -----------------------------
    public void updateLabels() {
        coinLabel.setText("Coins: " + gameManager.getCoin());
        dayLabel.setText("Day: " + gameManager.getDay());
        timerLabel.setText("Time: " + currentCustomer.getTimeRemaining());
        orderLabel.setText("Order: " + currentCustomer.getOrder().getDishName());
    }
    //----------------------- Start Timer -----------------------------
    public void startTimer() {
        if (gameTimer != null) gameTimer.stop(); // stop old timer
        gameTimer = new Timer(1000, e -> {
            currentCustomer.decrementTime();      // decrease time
            updateLabels();
            if (currentCustomer.getTimeRemaining() <= 0) { // if time run out
                gameTimer.stop();
                currentCustomer.onLeave(gameManager); // customer leaves
                if (currentCustomer instanceof Ghost) {
                    // ghost leave = jumpscare then game over
                    if (!jumpscareTriggered) { // only trigger if not already triggered
                        triggerJumpscare();
                    }
                } else {
                    spawnCustomer(); // regular customer leave
                }
            }

            if(Math.random() < 0.005) { // 0.5% chance every second
                SoundManager.playWhisper();
            }
            if(Math.random() < 0.005) { // 0.5% chance every second
                SoundManager.playIseeyou();
            }
        });
        gameTimer.start();
    }
    //----------------------- Jump Scare -----------------------------
    private Timer heartTimer;
    public void triggerJumpscare() {
        if (gameTimer != null) gameTimer.stop(); //stop time while jumpscare to prevent when ghost timer run out player will get jumpscare again
        if (jumpscareTriggered) return; // prevent double jumpscare
        jumpscareTriggered = true;      // mark as trigger
        SoundManager.stopBGM();  // stop BGM
        SoundManager.stopSus();  // stop sus music
        SoundManager.playJump(); // play scream sound

        if (currentCustomer instanceof Ghost ghost) {
            // fullscreen on top
            JWindow scary = new JWindow();
            scary.setSize(800, 800);
            scary.getContentPane().setBackground(Color.BLACK); // black background

            ImageIcon gifIcon = new ImageIcon(ghost.getJumpscareImage());
            JLabel jumpLabel = new JLabel(gifIcon);
            jumpLabel.setBackground(Color.BLACK);
            jumpLabel.setOpaque(true);
            scary.add(jumpLabel);
            scary.setLocationRelativeTo(null); // center on screen
            scary.setVisible(true);            // show window
            scary.setAlwaysOnTop(true);        // always on top of everything
            heartTimer = new Timer(4500, e -> { // wait 6 seconds for scream to finish
                scary.dispose();          // close scary window
                SoundManager.playHeart(); // play heartbeat after scare
                // check game over
                if (gameManager.isGameOver()) {
                    SoundManager.playGameOver();
                    JOptionPane.showMessageDialog(null, "GAME OVER!");
                    System.exit(0);
                }
                // ghost type 3 = force exit after scare
                if (ghost.getGhostType() == 3) {
                    JOptionPane.showMessageDialog(null,
                            "⅄ᴏꓵ HᴀΛᴇ ʙEᴇИ Mᴀʁɴ...",
                            "ꁍꍟ꓅ ꆂꐇ꓅",
                            JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
                spawnCustomer();
                heartTimer.stop();      // stop heart timer
                SoundManager.playBGM();// restart BGM after scare
            });
            heartTimer.start(); // start heart timer
        }
    }
    //----------------------- End of the Day -----------------------------
    public void endDay() {
        gameTimer.stop();
        int servedToday = gameManager.getCustomerCount(); // save values
        double rentToday = gameManager.getRent();
        gameManager.nextDay(); // resets customerCount, rent
        // show end of day sum
        JOptionPane.showMessageDialog(null,
                "Day " + (gameManager.getDay() - 1) + " complete!\n" + // -1
                        "Customers served: " + servedToday + "\n" +
                        "Rent paid: " + (gameManager.getRent() - 50) + "\n" +
                        "Remaining Coins: " + gameManager.getCoin(),
                "End of Day",
                JOptionPane.INFORMATION_MESSAGE);
        if (gameManager.isGameOver()) { // check game over after pay rent
            SoundManager.playGameOver();
            JOptionPane.showMessageDialog(null, "GAME OVER!");
            System.exit(0);
        } else {
            spawnCustomer(); // new day = new customer
        }
    }
}
