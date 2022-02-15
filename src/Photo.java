import java.util.List;

public class Photo {
    Direction direction;
    List<String> tags;

    public Photo(Direction direction, List<String> tags) {
        this.direction = direction;
        this.tags = tags;
    }

    enum Direction {
        VERTICAL,
        HORIZONTAL;
    }
}
