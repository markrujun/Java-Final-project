package gui;

import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import logic.battle.BattleClient;
import logic.creature.calabashBrothers.*;

import java.util.ArrayList;

import static Config.APP_Config.SCENE_HEIGHT;
import static Config.APP_Config.SCENE_WIDTH;


public class Controller {
    @FXML
    private GridPane selectGridPane;
    @FXML
    private Pane battlePane;

    @FXML private void initialize(){
        createSelect();
    }

    public void selectKeyPress(Event val) {
        System.out.println(val);
    }
    public void selectMouseEnter(Event val) {
        System.out.println(val);

    }
    public void selectMouseClick(Event val) {
        System.out.println(val);

    }
    public void selectMousePress(Event val) {
        System.out.println(val);

    }
    public void selectMouseExit(Event val) {
        System.out.println(val);

    }
    public void createSelect() {
        final ArrayList<Pane> selectPaneList = new ArrayList<>();
        ArrayList<CalabashBrother> CBList = new ArrayList<>();
        selectGridPane.setPrefWidth(SCENE_WIDTH);
        selectGridPane.setPrefHeight(SCENE_HEIGHT);
        RedBrother redBrother = RedBrother.getInstance();
        OrangeBrother orangeBrother = OrangeBrother.getInstance();
        YellowBrother yellowBrother = YellowBrother.getInstance();
        GreenBrother greenBrother = GreenBrother.getInstance();
        CyanBrother cyanBrother = CyanBrother.getInstance();
        BlueBrother blueBrother = BlueBrother.getInstance();
        PurpleBrother purpleBrother = PurpleBrother.getInstance();
        CBList.add(redBrother);
        CBList.add(orangeBrother);
        CBList.add(yellowBrother);
        CBList.add(greenBrother);
        CBList.add(cyanBrother);
        CBList.add(blueBrother);
        CBList.add(purpleBrother);
        for (final CalabashBrother CB: CBList) {
            final Pane tempPane = new Pane();
            selectPaneList.add(tempPane);
            int paneWidth = SCENE_WIDTH / CBList.size();
            tempPane.setPrefWidth(paneWidth);
            tempPane.setPrefHeight(SCENE_HEIGHT);
            ImageView selectImageView = CB.creatureImage.selectImage;
            selectImageView.setFitWidth(paneWidth);
            selectImageView.setFitHeight(paneWidth*1.5);
            double imageViewHeight = selectImageView.getFitHeight();
            tempPane.getChildren().add(0,selectImageView);
            Text nameText = new Text();
            Text healthText = new Text();
            Text attackRangeText = new Text();
            Text strengthText = new Text();
            Text descriptionText = new Text();
            ArrayList<Text> textArrayList = new ArrayList<>();
            textArrayList.add(0,nameText);
            textArrayList.add(1,healthText);
            textArrayList.add(2,attackRangeText);
            textArrayList.add(3,strengthText);
            textArrayList.add(4,descriptionText);
            nameText.setText(CB.tellName());
            healthText.setText("生命值：" + CB.maxHealth);
            attackRangeText.setText("攻击距离：" + CB.attackRange);
            strengthText.setText("攻击力距离：" + CB.strength);
            descriptionText.setText("简介：" + CB.description);
            for (Text text: textArrayList) {
                text.setWrappingWidth(paneWidth-20);
                text.setLayoutY(imageViewHeight+30+textArrayList.indexOf(text) * 20);
                text.setStyle("-fx-start-margin: 30");
            }
            tempPane.getChildren().add(1, nameText);
            tempPane.getChildren().add(2, healthText);
            tempPane.getChildren().add(3, attackRangeText);
            tempPane.getChildren().add(4, strengthText);
            tempPane.getChildren().add(5, descriptionText);

            tempPane.setOnMouseEntered(event -> {
                tempPane.setCursor(Cursor.HAND);
                tempPane.setStyle("-fx-border-color: red");
            });
            tempPane.setOnMouseExited(event -> tempPane.setStyle("-fx-border-color: grey"));
            tempPane.setOnMouseClicked(event -> {
                endSelect();
                final MouseEvent event1 = event;
                System.out.println(battlePane);
                Task task = new Task() {
                    @Override
                    protected Object call() {
                        BattleClient.getInstance(selectPaneList.indexOf(event1.getSource()), battlePane).init();
                        return null;
                    }
                };
                new Thread(task).start();
            });
            selectGridPane.addColumn(CBList.indexOf(CB),tempPane);
        }
    }
    public void endSelect() {
        selectGridPane.setVisible(false);
        battlePane.setVisible(true);
    }
}
