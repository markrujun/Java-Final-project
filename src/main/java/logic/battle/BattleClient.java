package logic.battle;

import Config.APP_Config;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import logic.creature.evilCreatures.EvilCreature;
import logic.creature.evilCreatures.EvilLeague;
import logic.creature.calabashBrothers.*;
import util.LogWriter;

import java.util.Arrays;
import java.util.Stack;


public class BattleClient {
    private static BattleClient  obj;
    private final Pane battlePane;
    public EvilLeague evilLeague;
    public CalabashBrother player;
    private BattleClient(int CalabashIndex, Pane battlePane) {
        this.battlePane = battlePane;
        switch (CalabashIndex){
            case 0:
                player = RedBrother.getInstance();
                LogWriter.write("大娃准备出战");
                break;
            case 1:
                player = new OrangeBrother();
                LogWriter.write("二娃准备出战");

                break;
            case 2:
                player = new YellowBrother();
                LogWriter.write("三娃准备出战");
                break;
            case 3:
                player = new GreenBrother();
                LogWriter.write("四娃准备出战");
                break;
            case 4:
                player = new CyanBrother();
                LogWriter.write("五娃准备出战");
                break;
            case 5:
                player = new BlueBrother();
                LogWriter.write("六娃准备出战");
                break;
            case 6:
                player = new PurpleBrother();
                LogWriter.write("七娃准备出战");
                break;
        }
    }

    public static BattleClient getInstance(int calabashIndex, Pane battlePane) {
        if (obj == null) {
            synchronized (BattleClient.class) {
                if (obj == null) {
                    obj = new BattleClient(calabashIndex, battlePane);
                }
            }
        }
        return obj;
    }
    public static BattleClient getInstance() {
        return obj;
    }

    public void init() {
        playerInit();
        evilLeagueInit();
        threadBegin();
    }
    private void threadBegin() {
        // 子弹线程开启
        new Thread(() -> {
            int playBulletSize = player.bullets.size();
            int evilBulletSize = evilLeague.evilBullets.size();
            while (true) {
                // 葫芦娃子弹
                if (playBulletSize != player.bullets.size()) {
                    for (int i = playBulletSize; i < player.bullets.size(); i++) {
                        int finalI = i;
                        synchronized (battlePane) {
                            Platform.runLater(() -> {
                                battlePane.getChildren().add(player.bullets.get(finalI).bulletImage.currentImage);
                            });
                        }
                        playBulletSize++;
                    }
                }
                // 怪物子弹
                if (evilBulletSize != evilLeague.evilBullets.size()) {
                    for(int i = evilBulletSize; i<evilLeague.evilBullets.size();i++) {
                        int finalI = i;
                        synchronized (battlePane) {
                            Platform.runLater(() -> {
                                battlePane.getChildren().add(evilLeague.evilBullets.get(finalI).bulletImage.currentImage);
                            });
                        }
                        evilBulletSize++;
                    }
                }
                if (player.isDead()) {
                    LogWriter.treadEnd = true;
                    evilLeague.treadEnd = true;
                    Platform.runLater(() -> {
                        Image end_image = new Image("lose.png");
                        ImageView end = new ImageView(end_image);
                        end.setLayoutX((APP_Config.SCENE_WIDTH/2.0)-end_image.getWidth()/2 );
                        end.setLayoutY((APP_Config.SCENE_HEIGHT/2.0) - end_image.getHeight()/2);
                        battlePane.getChildren().add(end);
                    });
                    break;
                }
                if (evilLeague.evilLeagueLose) {
                    LogWriter.treadEnd = true;
                    player.tempHealth = 0;

                    Platform.runLater(() -> {
                        Image end_image = new Image("win.png");
                        ImageView end = new ImageView(end_image);
                        end.setLayoutX((APP_Config.SCENE_WIDTH / 2.0) - (end_image.getWidth() / 2));
                        end.setLayoutY((APP_Config.SCENE_HEIGHT/2.0) - end_image.getHeight()/2);
                        battlePane.getChildren().add(end);
                    });
                    break;
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void drawEvilCreature() {
        Platform.runLater(() -> {
                for (EvilCreature evilCreature: evilLeague.currentEvilSet
                ) {
                    evilCreature.creatureImage.setCurrentImage(evilCreature.creatureImage.fightImage);
                    battlePane.getChildren().add(evilCreature.creatureImage.redBloodImage);
                    battlePane.getChildren().add(evilCreature.creatureImage.greenBloodImage);
                    battlePane.getChildren().add(evilCreature.creatureImage.currentImage);
                }
        });
    }
    private void playerInit() {
        player.creatureImage.setCurrentImage(player.creatureImage.fightImage);
        Platform.runLater(() -> {
                battlePane.getChildren().add(player.creatureImage.redBloodImage);
                battlePane.getChildren().add(player.creatureImage.greenBloodImage);
                battlePane.getChildren().add(player.creatureImage.currentImage);
                battlePane.requestFocus();
                Stack<KeyCode> keyCodes = new Stack<>();
                battlePane.setOnKeyPressed(event -> {
                    if (!player.isDead()){
                        KeyCode code = event.getCode();
                        if (keyCodes.indexOf(code) < 0) {
                            switch (code) {
                                case RIGHT:
                                    if (keyCodes.indexOf(KeyCode.LEFT) >= 0) {
                                        keyCodes.remove(KeyCode.LEFT);
                                        keyCodes.push(KeyCode.RIGHT);
                                    } else
                                        keyCodes.push(KeyCode.RIGHT);
                                    break;
                                case LEFT:
                                    if (keyCodes.indexOf(KeyCode.RIGHT) >= 0) {
                                        keyCodes.remove(KeyCode.RIGHT);
                                        keyCodes.push(KeyCode.LEFT);
                                    } else
                                        keyCodes.push(KeyCode.LEFT);
                                    break;
                                case UP:
                                    if (keyCodes.indexOf(KeyCode.DOWN) >= 0) {
                                        keyCodes.remove(KeyCode.DOWN);
                                        keyCodes.push(KeyCode.UP);
                                    } else
                                        keyCodes.push(KeyCode.UP);
                                    break;
                                case DOWN:
                                    if (keyCodes.indexOf(KeyCode.UP) >= 0) {
                                        keyCodes.remove(KeyCode.UP);
                                        keyCodes.push(KeyCode.DOWN);
                                    } else
                                        keyCodes.push(KeyCode.DOWN);
                                    break;
                            }
                        } else {
                            keyCodes.remove(code);
                            keyCodes.push(code);
                        }
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (keyCodes.indexOf(KeyCode.LEFT) >= 0) {
                            player.moveLeft();
                        }
                        if (keyCodes.indexOf(KeyCode.RIGHT) >= 0) {
                            player.moveRight();
                        }
                        if (keyCodes.indexOf(KeyCode.UP) >= 0) {
                            player.moveUp();
                        }
                        if (keyCodes.indexOf(KeyCode.DOWN) >= 0) {
                            player.moveDown();
                        }

                    }
                });
                battlePane.setOnKeyReleased(event -> {
                    if (!player.isDead()) {
                        {
                            KeyCode code = event.getCode();
                            keyCodes.remove(code);
                            switch (code) {
                                case X:
                                    player.attack();
                                    break;
                            }
                        }
                    }
                });
        });
        player.move(50, 50);
        player.start();
    }
    private void evilLeagueInit() {
        LogWriter.write("邪恶阵容初始化");
        evilLeague = EvilLeague.getInstance();
        evilLeague.start();
    }

}
