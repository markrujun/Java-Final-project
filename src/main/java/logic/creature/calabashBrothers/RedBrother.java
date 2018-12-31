package logic.creature.calabashBrothers;

import logic.creature.CreatureImage;

public class RedBrother extends CalabashBrother {
    private static RedBrother obj;
    public RedBrother() {
        ID = Number++;
        thisCalabashBrother = CalabashBrotherEnum.RED;
        maxHealth = 3000;
        attackRange = 200;
        strength = 500;
        tempHealth = maxHealth;
        creatureImage = new CreatureImage();
        creatureImage.setImage("/select_red.png","/red_left.png","/red_right.png","/red_right.png");
    }
    public static RedBrother getInstance() {
        if(obj == null) {
            synchronized (RedBrother.class) {
                if(obj == null) {
                    obj = new RedBrother();
                }
            }
        }
        return obj;
    }

}
