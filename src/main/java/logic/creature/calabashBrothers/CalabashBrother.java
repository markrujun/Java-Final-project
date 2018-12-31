package logic.creature.calabashBrothers;

import logic.Bullet.Bullet;
import logic.Bullet.CalabashBrotherBullet;
import logic.battle.BattleClient;
import logic.creature.Creature;
import logic.creature.evilCreatures.EvilLeague;


public abstract class CalabashBrother extends Creature  {
    protected CalabashBrotherEnum thisCalabashBrother;
//    private Thread t;
//    public  String description = "";
//    public String tellName() {
//        return this.thisCalabashBrother.name;
//    }
//
//    @Override
//    public void run() {
//        while(true) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void start(){
//        if(t == null){
//            t = new Thread(this, "redBrother");
//        }
//        t.start();
//    }

    @Override
    public void attack() {
        Bullet tempBullet;
        if (directionRight) {
            tempBullet = new CalabashBrotherBullet(strength, attackRange, true, layoutX, layoutY+50);
        } else
            tempBullet = new CalabashBrotherBullet(strength, attackRange, false, layoutX, layoutY+50);
        tempBullet.targetList.addAll(BattleClient.getInstance().evilLeague.currentEvilSet);
        synchronized (bullets) {
            bullets.add(tempBullet);
        }
        tempBullet.start();
    }
}

