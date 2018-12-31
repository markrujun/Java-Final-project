package logic.creature.evilCreatures;

import Config.APP_Config;
import logic.Bullet.Bullet;
import logic.battle.BattleClient;
import logic.creature.League;

import java.util.ArrayList;
import java.util.Iterator;

public class EvilLeague extends League implements Runnable {
    public ArrayList<EvilCreature> evilCreatures = new ArrayList<>();
    public ArrayList<EvilCreature> currentEvilSet = new ArrayList<>();
    private static EvilLeague obj;
    public boolean evilLeagueLose = false;
    public boolean treadEnd = false;
    public ArrayList<Bullet> evilBullets = new ArrayList<>();

    private Thread t;
    private EvilLeague(){
        evilCreatures.add(new Underling());
        evilCreatures.add(new Underling());
        evilCreatures.add(new Underling());
        evilCreatures.add(new Underling());
        evilCreatures.add(new Scorpion());
        evilCreatures.add(new Underling());
        evilCreatures.add(new Scorpion());
        evilCreatures.add(new Scorpion());
        evilCreatures.add(new Scorpion());
    }

    public static EvilLeague getInstance() {
        if (obj == null) {
            synchronized (EvilLeague.class) {
                if (obj == null) {
                    obj = new EvilLeague();
                }
            }
        }
        return obj;
    }
    private void getOneSet(int number) {
        ArrayList<EvilCreature> tempList = new ArrayList<>();
        for (EvilCreature creature: evilCreatures) {
            if (creature.tempHealth>0) {
                tempList.add(creature);
                creature.move(APP_Config.SCENE_X_MAX - 50, (tempList.indexOf(creature)+1)* (APP_Config.SCENE_Y_MAX - APP_Config.SCENE_Y_MIN)/(number) +APP_Config.SCENE_Y_MIN  );
                creature.start();
            }
            if (tempList.size() >= number) {
                 break;
            }
        }

        if (tempList.size()==0)
            evilLeagueLose = true;
        currentEvilSet = tempList;
        BattleClient.getInstance().drawEvilCreature();
    }

    @Override
    public void run() {
        while (!evilLeagueLose && !treadEnd) {
            if (currentEvilSet.size() == 0) {
                getOneSet(3);
            }
            synchronized (currentEvilSet) {
                for (Iterator<EvilCreature> it = currentEvilSet.iterator(); it.hasNext(); ) {
                    EvilCreature creature = it.next();
                    if (creature.isDead()) {
                        synchronized (currentEvilSet) {
                            it.remove();
                        }
                    } else {
                        randomWalkAndAttack(creature);
                    }
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("evil lose");
    }

    private void randomWalkAndAttack(EvilCreature creature) {
        int num =(int)(Math.random() * 11);
        if (0<=num && num <3) {
            creature.moveLeft();
        } else if (num < 5) {
            creature.moveRight();
        } else if(num < 7)
            creature.moveUp();
        else if(num <9)
            creature.moveDown();
        else creature.attack();
    }

    public void start() {
        if (t == null){
            t = new Thread(this);
        }
        t.start();
    }
}
