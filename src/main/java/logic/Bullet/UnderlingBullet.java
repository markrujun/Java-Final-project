package logic.Bullet;

public class UnderlingBullet extends Bullet {
    public UnderlingBullet(int damage, int range, boolean directionRight, double x, double y) {
        bulletImage = new BulletImage("UnderlingBullet_left.png","UnderlingBullet_right.png", "UnderlingBullet_boom.png");
        this.damage = damage;
        this.range = range;
        move(x, y);
        setDirection(directionRight);
        height = bulletImage.currentImage.getImage().getHeight();
        width = bulletImage.currentImage.getImage().getWidth();
    }
}
