import org.junit.Test;
import static org.junit.Assert.*;

public class BowlingTest {

    @Test
    public void testEquals () {
        Bowling banen1 = Bowling.getInstance ();
        Bowling banen2 = Bowling.getInstance ();

        assertSame (banen1, banen2);
    }
}