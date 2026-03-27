public abstract class GameChar {
    public abstract void react(GameManager gm);
    //what each customer react when get wrong order
    public abstract void onLeave(GameManager gm);
    //what each customer do when timer run out
}