package logic.Bullet;


import Annotation.Description;
import Config.APP_Config;
import logic.creature.Creature;

import java.util.ArrayList;

public class Bullet implements Runnable {
    int damage;
    int range;
    private boolean directionRight;
    private double x;
    private double y;
    double height;
    double width;
    private boolean isFlying = true;
    public BulletImage bulletImage;
    private Thread t;
    public ArrayList<Creature> targetList = new ArrayList<>();


    void setDirection(boolean directionRight) {
        this.directionRight = directionRight;
        bulletImage.setCurrentImage(directionRight);
    }

    void move(double x, double y) {
        this.x = x;
        this.y = y;
        this.bulletImage.setX(x);
        this.bulletImage.setY(y);
    }
    @Description(todo = "判断子弹是否击中目标")
    private void judge(double x, double y) {
        for (Creature target: targetList
             ) {
            if (target.creatureImage.currentImage.getImage() == null)continue;
            double x1 = target.layoutX;
            double y1 = target.layoutY;
            double w1 = target.creatureImage.currentImage.getImage().getWidth();
            double h1 = target.creatureImage.currentImage.getImage().getHeight();
            System.out.println(x1 + "," + y1 + "," + w1 +  "," + h1);
            if ((x > x1 - width) &&
                    (x < x1+w1) &&
                    (y > y1 - height) &&
                    (y < y1 + h1)) {
                target.isAttack(damage);
                boom();
                isFlying = false;
            }
        }
    }

    private void boom() {
        bulletImage.currentImage.setImage(bulletImage.boomImage.getImage());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int tempRange = 0;
        while (tempRange < range && isFlying) {
            System.out.println("run");
            if (directionRight){
                move(x + 25, y);
                judge(x+25, y);
            } else {
                move(x - 25, y);
                judge(x-25, y);
            }
            tempRange +=10;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (x> APP_Config.SCENE_X_MAX+50 || x<APP_Config.SCENE_X_MIN -50)
                break;
        }
        bulletImage.setVisible(false);
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
        }
        t.start();
    }
}
