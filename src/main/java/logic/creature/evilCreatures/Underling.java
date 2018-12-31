package logic.creature.evilCreatures;

import javafx.scene.layout.Pane;
import logic.Bullet.Bullet;
import logic.Bullet.UnderlingBullet;
import logic.battle.BattleClient;
import logic.creature.CreatureImage;

public class Underling extends EvilCreature {
      public Underling(){
        System.out.println("create Underling");
        directionRight = false;
        ID = Number++;
        name = "喽啰";
        maxHealth = 1000;
        attackRange = 600;
        strength = 300;
        tempHealth = maxHealth;
        creatureImage = new CreatureImage();
        creatureImage.setImage("underling_left.png", "underling_left.png", "underling_right.png", "underling_left.png");
    }

    @Override
    public void attack() {
        System.out.println("evil attack");
        Bullet tempBullet;
        if (directionRight) {
            tempBullet = new UnderlingBullet(strength, attackRange, true, layoutX, layoutY+50);
        } else
            tempBullet = new UnderlingBullet(strength, attackRange, false, layoutX, layoutY+50);
        tempBullet.targetList.add(BattleClient.getInstance().player);
        synchronized (EvilLeague.class) {
            EvilLeague.getInstance().evilBullets.add(tempBullet);
        }
        tempBullet.start();
    }
}
