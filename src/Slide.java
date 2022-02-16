import java.util.List;

public class Slide {
    List<Photo> photos;
    List<String> tags;
    Photo.Direction direction;

    public Slide(List<Photo> photos, List<String> tags) {
        this.photos = photos;
        this.tags = tags;
        this.direction = photos.size() == 2 ? Photo.Direction.VERTICAL : Photo.Direction.HORIZONTAL;
    }
}
