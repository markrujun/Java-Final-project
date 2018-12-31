package logic.creature.evilCreatures;

import logic.Bullet.Bullet;
import logic.Bullet.ScorpionBullet;
import logic.battle.BattleClient;
import logic.creature.CreatureImage;
import util.LogWriter;

public class Scorpion extends EvilCreature {
    public Scorpion(){
        System.out.println("create Underling");
        directionRight = false;
        ID = Number++;
        name = "喽啰";
        maxHealth = 2000;
        attackRange = 900;
        strength = 700;
        tempHealth = maxHealth;
        creatureImage = new CreatureImage();
        creatureImage.setImage("Scorpion_left.png", "Scorpion_left.png", "Scorpion_right.png", "Scorpion_left.png");
    }

    @Override
    public void attack() {
        System.out.println("evil attack");
        Bullet tempBullet;
        if (directionRight) {
            tempBullet = new ScorpionBullet(strength, attackRange, true, layoutX, layoutY+50);
        } else
            tempBullet = new ScorpionBullet(strength, attackRange, false, layoutX, layoutY+50);
        tempBullet.targetList.add(BattleClient.getInstance().player);
        synchronized (EvilLeague.class) {
            EvilLeague.getInstance().evilBullets.add(tempBullet);
        }
        tempBullet.start();
        LogWriter.write(tellName() + "发出了子弹，" + "位置：x:" + layoutX + "  ,y: " + layoutY);

    }
}
