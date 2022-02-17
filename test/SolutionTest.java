import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SolutionTest {
    public static Stream<Arguments> dataSource() {
        return Stream.of(Arguments.of("input/a.txt", "output/a.txt"),
                         // Arguments.of("input/b.txt", "output/b.txt"),
                         Arguments.of("input/c.txt", "output/c.txt"),
                         // Arguments.of("input/d.txt", "output/d.txt"),
                         Arguments.of("input/e.txt", "output/e.txt"));
    }

    @ParameterizedTest
    @MethodSource("dataSource")
    public void testForRandomSelection(String inputFilePath, String outputFilePath) {
        Input input = InputFileParser.parseInputFile(inputFilePath);
        Solution solution = new Solution1();
        OutputFileWriter.writeToOutputFile(solution.solution(input), outputFilePath);
        Output answer = OutputFileParser.parseOutputFile(outputFilePath);
        long score = ScoreCalculator.calculateScore(input, answer);
        System.out.println(score);
    }
}
