package logic.creature.calabashBrothers;

import logic.Bullet.Bullet;
import logic.Bullet.CalabashBrotherBullet;
import logic.battle.BattleClient;
import logic.creature.Creature;
import util.LogWriter;


public abstract class CalabashBrother extends Creature  {
    CalabashBrotherEnum thisCalabashBrother;

    @Override
    public void attack() {
        Bullet tempBullet;
        if (directionRight) {
            tempBullet = new CalabashBrotherBullet(strength, attackRange, true, layoutX, layoutY+50);
        } else
            tempBullet = new CalabashBrotherBullet(strength, attackRange, false, layoutX, layoutY+50);
        tempBullet.targetList.addAll(BattleClient.getInstance().evilLeague.currentEvilSet);
        synchronized (Bullet.class) {
            bullets.add(tempBullet);
        }
        tempBullet.start();
        LogWriter.write(tellName() + "发出了子弹，" + "位置：x:" + layoutX + "  ,y: " + layoutY);

    }
}

