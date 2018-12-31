package logic.Bullet;


public class CalabashBrotherBullet extends Bullet {
    public CalabashBrotherBullet(int damage, int range, boolean directionRight, double x, double y) {
        bulletImage = new BulletImage("bullet_left.png","bullet_right.png", "bullet_boom.png");
        this.damage = damage;
        this.range = range;
        move(x, y);
        setDirection(directionRight);
        height = bulletImage.currentImage.getImage().getHeight();
        width = bulletImage.currentImage.getImage().getWidth();
    }
}
