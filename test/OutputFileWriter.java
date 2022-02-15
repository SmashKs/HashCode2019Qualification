import java.io.PrintWriter;

public class OutputFileWriter {

    public static void writeToOutputFile(Output output, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
