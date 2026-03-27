public class Customer extends GameChar {
    private int timeLimit;
    private int timeRemaining;
    private int mistakeCount;
    private Menu order;
    private String customerPic;
    //-------------------------Customer Pic--------------------------
    public Customer() {
        String[] customerImages = {
                "Customer/customer0.png",
                "Customer/customer1.png",
                "Customer/customer2.png",
                "Customer/customer3.png",
                "Customer/customer4.png",
                "Customer/customer5.png",
                "Customer/customer6.png",
                "Customer/customer7.png",
                "Customer/customer8.png",
                "Customer/customer9.png",
                "Customer/customer10.png",
                "Customer/customer11.png",
                "Customer/customer12.png"
        };
        int randomIndex = (int)(Math.random() * customerImages.length);
        this.customerPic = customerImages[randomIndex];
    }
    //--------------------------------------------------------------
    public void setCustomerPic(String pic) { this.customerPic = pic; } //get customer pic
    public int getTimeRemaining() { return timeRemaining; } // get remaining time
    public int getMistakeCount() { return mistakeCount; } // get mistake count
    public Menu getOrder() { return order; } // get order
    public String getCustomerPic() {return customerPic;} // get customer pic
    public void setOrder(Menu order) { this.order = order; } // set order from randomorder
    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
        this.timeRemaining = timeLimit; // start countdown from timeLimit
    }
    //-----------------------Timer run down-------------------------
    public void decrementTime() {
        if(timeRemaining > 0) {
            timeRemaining--;
        }
    }
    //-----------------------Add Mistake for each customer-------------------------
    public void addMistake() {
        mistakeCount++; // this specific customer's mistakes
    }
    //------------------------Behavior for customer-------------------------
    @Override
    public void react(GameManager gm){ //when wrong order
        addMistake();
        gm.addMistake();
        gm.takeCoin(35);
    };

    public void react(GameManager gm, int penalty) { //more day = more penalty
        addMistake();
        gm.addMistake();
        gm.takeCoin(penalty);
    }
    @Override
    public void onLeave(GameManager gm2){ //when time run out
        gm2.takeCoin(45);
    };
}
