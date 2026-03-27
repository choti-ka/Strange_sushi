public class GameManager {
    private double coin;
    private int day = 1;
    private double rent;
    private int mistake;
    private boolean gameOver;
    private double jumpScare;
    private int customerCount;
    private boolean ghostSpawnedToday;

    public int getDay(){ return day;}
    public int getMistake(){ return mistake;}
    public void setCoin() {
        this.coin = 500; //start money
    }
    public double getCoin(){return coin;}
    public void addCoin(double coin){ //add money if correct order
            this.coin += coin;
    } // add coin
    public void takeCoin(double coin) {
        this.coin -= coin;//take coin
        setGameOver(); //check game over
    }
    public void setRent(){
        this.rent = 250; //start rent
    }
    public double getRent() { return rent; }
    public void payRent(){this.coin -= this.rent;} //pay rent
    public void takeHalf() {this.coin /=2;}
    public boolean isGhostSpawnedToday() { return ghostSpawnedToday; } // get ghost chance
    public void setGhostSpawnedToday(boolean value) { this.ghostSpawnedToday = value; }
    public int getCustomerCount() { return customerCount; }
    public double getJumpScare() { return jumpScare; }
    //-----------------------Add Mistake-----------------------------
    public void addMistake() { //add mistake if order wrong(total mistake reset everyday)
        mistake++;
        setGameOver(); // check if game over
    }
    //-----------------------Game Over-----------------------
    public void setGameOver(){ // money <= 0 gameover
        if(this.coin <= 0){
            this.gameOver = true;
        }
    }
    public void forceGameOver() {
        this.gameOver = true;
    }
    public boolean isGameOver(){
        return gameOver;
    }
    //---------------------Add Customer-------------------------------
    public void addCustomer(){ // add customer for each day
        customerCount++;
    } // add customer the nextday from customer counter the day before
    public boolean shouldSpawnGhost() {
        if(!ghostSpawnedToday && Math.random() < jumpScare) {
            ghostSpawnedToday = true;
            return true;
        }
        return false;
    }
    //----------------------Spawn Ghost----------------------------
   public Ghost spawnGhost() {
        if(shouldSpawnGhost()) {
           int randomType = (int)(Math.random() * 5) + 1;
            return new Ghost(randomType, this);
        }
        return null; // no ghost today
    }
    //----------------------Next Day----------------------------
    public void nextDay(){//at the end of the day
        ghostSpawnedToday = false;
        payRent();
        setGameOver(); // check if game over
        day++;
        rent += 50;
        jumpScare += 0.3;
        if(jumpScare > 0.8) jumpScare = 0.8;
        mistake = 0; // reset mistake
        customerCount = 0; //reset customer count
    }
}
