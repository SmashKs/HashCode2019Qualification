import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreCalculator {

    public static long calculateScore(Input input, Output output) {

        List<Output.SlideInformation> slideInformationList = output.getSlideInformationList();
        long points = 0L;

        for (int i = 0; i < output.getNumSlides() - 1; i++) {
            Output.SlideInformation slide1 = slideInformationList.get(i);
            Output.SlideInformation slide2 = slideInformationList.get(i + 1);

            List<String> slide1TagList = getTagsOf(slide1, input);
            List<String> slide2TagList = getTagsOf(slide2, input);

            points += getScoreOf(slide1TagList, slide2TagList);
        }

        return points;
    }

    private static List<String> getTagsOf(Output.SlideInformation slide, Input input) {
        return slide.getPhotoIdList().stream()
                .flatMap(photoId -> input.photos.stream()
                        .filter(photo -> photo.id == photoId)
                        .flatMap(photo -> photo.tags.stream())
                ).collect(Collectors.toList());
    }

    private static long getScoreOf(final List<String> slide1TagList, final List<String> slide2TagList) {

        List<String> sharedTags = new ArrayList<>(slide1TagList);
        sharedTags.retainAll(slide2TagList);

        List<String> onlyInSlide1Tags = new ArrayList<>(slide1TagList);
        onlyInSlide1Tags.removeAll(sharedTags);

        List<String> onlyInSlide2Tags = new ArrayList<>(slide2TagList);
        onlyInSlide2Tags.removeAll(sharedTags);

        long tempMin = Math.min(sharedTags.size(), onlyInSlide1Tags.size());
        return Math.min(tempMin, onlyInSlide2Tags.size());
    }
}
