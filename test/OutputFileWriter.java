import java.io.PrintWriter;
import java.util.Collections;
import java.util.stream.Collectors;

public class OutputFileWriter {

    public static void writeToOutputFile(Output output, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            writer.println(output.getNumSlides());
            for (Output.SlideInformation slide : output.getSlideInformationList()) {
                writer.println(slide.getPhotoIdList().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
                );
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
