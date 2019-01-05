import org.junit.Test;
import util.LogWriter;

/**
 * @author：markrujun
 * @create：2019-01-05 14:02
 */
public class LogWriterTest {
    @Test
    public void logTest() {
        LogWriter.getInstance().start();
        LogWriter.write("test Writer");
        LogWriter.treadEnd = true;
    }
}
