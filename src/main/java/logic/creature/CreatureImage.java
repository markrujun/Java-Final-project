package logic.creature;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class CreatureImage {
    public ImageView currentImage;
    public ImageView upImage = new ImageView();
    public ImageView leftImage = new ImageView();
    public ImageView selectImage = new ImageView();
    public ImageView downImage = new ImageView();
    public ImageView rightImage = new ImageView();
    public ImageView fightImage = new ImageView();
    public Pane greenBloodImage = new Pane();
    public Pane redBloodImage = new Pane();
    private ArrayList<ImageView> imageList = new ArrayList<>();

    public CreatureImage() {
        imageList.add(leftImage);
        imageList.add(rightImage);
        imageList.add(upImage);
        imageList.add(downImage);
        imageList.add(fightImage);
        greenBloodImage.setStyle("-fx-background-color: green");
        redBloodImage.setStyle("-fx-background-color: red");
        greenBloodImage.setPrefHeight(20);
        redBloodImage.setPrefHeight(20);
        greenBloodImage.setLayoutX(leftImage.getLayoutX());
        greenBloodImage.setLayoutY(leftImage.getLayoutY()-30);
        redBloodImage.setLayoutX(leftImage.getLayoutX());
        redBloodImage.setLayoutY(leftImage.getLayoutY()-20);
        currentImage = new ImageView(selectImage.getImage());
        imageList.add(currentImage);
    }

    public void setImage(String right) {
        this.setImage(right, right, right, right, right, right);
    }
    public void setImage(String select, String right) {
        this.setImage(select, right, right, right, right, right);
    }
    public void setImage(String select, String right, String fight) {
        this.setImage(select, right, right, right, right, fight);
    }public void setImage(String select, String left, String right, String fight) {
        this.setImage(select, right, left, right, right, fight);
    }
    public void setImage(String select, String up, String left, String down, String right, String fight){
        selectImage.setImage(new Image(select));
        leftImage.setImage(new Image(left));
        rightImage.setImage(new Image(right));
        upImage.setImage(new Image(up));
        downImage.setImage(new Image(down));
        fightImage.setImage(new Image(fight));
        greenBloodImage.setPrefWidth(leftImage.getImage().getWidth());
        redBloodImage.setPrefWidth(leftImage.getImage().getWidth());
    }
    void setX(double x) {
        for (ImageView imageView: imageList) {
            imageView.setX(x);
        }
        greenBloodImage.setLayoutX(x);
        redBloodImage.setLayoutX(x);
    }
    void setY(double y) {
        for (ImageView imageView: imageList) {
            imageView.setY(y);
        }
        greenBloodImage.setLayoutY(y-20);
        redBloodImage.setLayoutY(y-20);
    }

    public void setCurrentImage(ImageView current) {
        this.currentImage.setImage(current.getImage());
    }

    public void changeBlood(int maxHealth, int tempHealth) {
        greenBloodImage.setPrefWidth(tempHealth * 1.0/maxHealth * currentImage.getImage().getWidth());
    }

    public void hideAll() {
        for (ImageView image: imageList
             ) {
            image.setVisible(false);
        }
        greenBloodImage.setPrefWidth(0);
        redBloodImage.setPrefWidth(0);
        currentImage.setImage(null);
    }
}
