import Annotation.Description;
import javafx.scene.layout.Pane;
import logic.battle.BattleClient;
import org.junit.Test;

/**
 * @author：markrujun
 * @create：2018-12-31 11:03
 */
public class BattleClientTest {
    @Test
    @Description(todo = "测试是否正确实现单例模式")
    public void createBattleClient() {
        BattleClient bc1 = BattleClient.getInstance(3, new Pane());
        BattleClient bc2 = BattleClient.getInstance();
        assert bc1 == bc2;
    }
}
