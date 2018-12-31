package logic.creature;


import logic.Bullet.Bullet;

import java.util.ArrayList;

public abstract class Creature implements Runnable{
    private Thread t;
    public  String description = "";
    public static volatile int Number;
    public int ID;
    public String name;
    public double layoutX;
    public double layoutY;
    public int maxHealth;
    public int strength;
    public int attackRange;
    public int tempHealth;
    public boolean directionRight = true;
    public CreatureImage creatureImage;
    public ArrayList<Bullet> bullets = new ArrayList<>();


    public String tellName() {
        return this.name;
    };
    public void move(double x, double y){
        if (x < 0 && x>=-10 )
            return;
        if (x >830 && x<=840)
            return;
        if (y <0 && y>=-10)
            return;
        if (y > 340 && y<=350)
            return;
        if (x<-10)
            x = 0;
        if (x>840)
            x = 830;
        if (y< -10)
            y = 0;
        if (y > 350)
            y = 340;
        System.out.println(x);
        System.out.println(y);
        layoutX = x;
        layoutY = y;
        creatureImage.setX(x);
        creatureImage.setY(y);
    }
    public void moveLeft(){
        directionRight = false;
        creatureImage.setCurrentImage(creatureImage.leftImage);
        System.out.println("moveUp");
        move(layoutX - 10, layoutY);
    }
    public void moveRight() {
        directionRight = true;
        creatureImage.setCurrentImage(creatureImage.rightImage);
        System.out.println("moveDown");
        move(layoutX + 10, layoutY);
    }
    public void moveDown() {
        move(layoutX, layoutY + 10);
    }
    public void moveUp() {
        move(layoutX, layoutY - 10);
    }
    public abstract void attack();
    public void isAttack(int damage) {
        System.out.println(tellName() + "is attacked");
        if (tempHealth >= damage) {
            tempHealth -= damage;
        } else
            tempHealth = 0;
        creatureImage.changeBlood(maxHealth, tempHealth);
    };
    public boolean isDead() {
        return tempHealth == 0;
    }


    @Override
    public void run() {
        while (!isDead()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bury();
    }

    private void bury() {
        creatureImage.hideAll();
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
        }
        t.start();
    }
}
