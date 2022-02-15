import java.util.List;

public class Photo {
    Direction direction;
    List<String> tags;
    int id;

    public Photo(Direction direction, List<String> tags, int id) {
        this.direction = direction;
        this.tags = tags;
        this.id = id;
    }

    enum Direction {
        VERTICAL,
        HORIZONTAL
    }
}
