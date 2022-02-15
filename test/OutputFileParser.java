import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputFileParser {

    public static Output parseOutputFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String firstLine = reader.readLine();
            int numSlides = Integer.parseInt(firstLine);
            List<Output.SlideInformation> slideInformationList = new ArrayList<>();
            for (int i = 0; i < numSlides; i++) {
                String line = reader.readLine();
                List<Integer> photoIdList = Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                slideInformationList.add(new Output.SlideInformation(photoIdList));
            }

            reader.close();

            return new Output(slideInformationList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
