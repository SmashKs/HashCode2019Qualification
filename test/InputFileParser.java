import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputFileParser {

    public static Input parseInputFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            long numOfLines = Long.parseLong(reader.readLine());

            List<Photo> photos = new ArrayList<>();

            for (int i = 0 ; i < numOfLines ; i++) {
                String[] line = reader.readLine().split(" ");
                Photo.Direction dir = line[0].equals("V") ? Photo.Direction.VERTICAL : Photo.Direction.HORIZONTAL;
                long numOfTags = Long.parseLong(line[1]);
                List<String> tags = new ArrayList<>();
                for (int j = 0; j < numOfTags; j++) {
                    tags.add(line[j + 2]);
                }
                photos.add(new Photo(dir, tags, i));
            }

            reader.close();

            return new Input(photos);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
