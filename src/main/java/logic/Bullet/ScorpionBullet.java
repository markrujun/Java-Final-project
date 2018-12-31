package logic.Bullet;

public class ScorpionBullet extends Bullet {
    public ScorpionBullet(int damage, int range, boolean directionRight, double x, double y) {
        bulletImage = new BulletImage("ScorpionBullet_left.png","ScorpionBullet_right.png", "ScorpionBullet_boom.png");
        this.damage = damage;
        this.range = range;
        move(x, y);
        setDirection(directionRight);
        height = bulletImage.currentImage.getImage().getHeight();
        width = bulletImage.currentImage.getImage().getWidth();
    }
}
