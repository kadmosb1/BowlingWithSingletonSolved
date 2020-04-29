import java.util.ArrayList;
import java.util.List;

public class Bowling {

    private static Bowling singleton;

    public static Bowling getInstance() {

        if (singleton == null) {
            singleton = new Bowling();
        }

        return singleton;
    }

    public final List<Lane> lanes;

    private Bowling() {
        this.lanes = new ArrayList<>();
        this.lanes.add(new Lane(1));
        this.lanes.add(new Lane(2));
        this.lanes.add(new Lane(3));
        this.lanes.add(new Lane(4));
    }
}