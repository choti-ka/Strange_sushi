import javax.swing.*;

public class Ghost extends Customer  {
    private int ghostType;
    private String jumpscareImage;
    private boolean triggerJumpscare;
    private GameManager gm;

    //-------------------Constructor-------------------
    public Ghost(int ghostType, GameManager gameManager) {
        super();
        this.gm = gameManager;
        this.ghostType = ghostType;

        String[] ghostImages = {
                "Ghost/ghost.png",
                "Ghost/ghost_face.png",
                "Ghost/ghost_forceexit.png",
                "Ghost/ghost_takehalf.png",
                "Ghost/foxy.png"
        };
        String[] jumpscareImages = {
                "Ghost/jump_ghost.png",
                "Ghost/ghost_face_kill.png",
                "Ghost/jump_force.png",
                "Ghost/jump_takehalf.png",
                "Ghost/jump_foxy.gif"
        };
        setCustomerPic(ghostImages[ghostType - 1]); //  sets parent's customerPic as ghost one
        this.jumpscareImage = jumpscareImages[ghostType - 1]; // change to jumpscare pic
    }
    //-------------------Getters-------------------
    public String getJumpscareImage() { return jumpscareImage; }
    public boolean isTriggerJumpscare() { return triggerJumpscare; }
    public int getGhostType() { return ghostType; }

    //-------------------React-------------------
    @Override
    public void react(GameManager gm) {
        switch(ghostType) {
            case 1:
                triggerJumpscare = true;
                break;
            case 2:
                triggerJumpscare = true;
                gm.forceGameOver();
                break;
            case 3:
                triggerJumpscare = true;
                break;
            case 4:
                triggerJumpscare = true;
                gm.takeHalf();
                break;
            case 5:
                triggerJumpscare = true;
                break;
        }
    }
    @Override
    public void react(GameManager gm, int penalty) {
        // call the normal ghost reaction
        this.react(gm);
    }
    //-------------------OnLeave-------------------
    @Override
    public void onLeave(GameManager gm) {
        gm.forceGameOver();
    }
}
