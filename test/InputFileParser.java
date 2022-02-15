import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                Set<String> tags = new HashSet<>();
                for (int j = 0; j < numOfTags; j++) {
                    tags.add(line[j + 2].toString());
                }
                photos.add(new Photo(dir, new ArrayList<>(tags)));
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
