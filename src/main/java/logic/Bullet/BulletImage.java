package logic.Bullet;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class BulletImage {
    public ImageView leftImage= new ImageView();
    public ImageView rightImage= new ImageView();
    public ImageView boomImage= new ImageView();
    public ImageView currentImage= new ImageView();
    public ArrayList<ImageView> imageList = new ArrayList<>();

    public BulletImage(String left, String right, String boom) {
        leftImage.setImage(new Image(left));
        boomImage.setImage(new Image(boom));
        rightImage.setImage(new Image(right));
        imageList.add(currentImage);
        imageList.add(leftImage);
        imageList.add(rightImage);
        imageList.add(boomImage);
    }

    public void setCurrentImage(boolean directionRight) {
        if (directionRight) {
            currentImage.setImage(rightImage.getImage());
        } else {
            currentImage.setImage(leftImage.getImage());
        }
    }
    void setX(double x) {
        for (ImageView imageView: imageList) {
            imageView.setX(x);
        }
    }
    void setY(double y) {
        for (ImageView imageView: imageList) {
            imageView.setY(y);
        }
    }

    public void setVisible(boolean b) {
        for (ImageView imageView: imageList) {
            imageView.setVisible(b);
        }
    }
}
