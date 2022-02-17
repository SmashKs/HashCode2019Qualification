import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution1 implements Solution {
    /**
     * 1. Vertical photos
     * Idea - Pair photos with different tags together
     * Idea - total # of tags should be roughly same
     * a) Calculate total # of tags
     * b) Get average # of tags
     * c) Use average to pair vertical photos together
     * d) Combine to consider as horizontal photo
     * <p>
     * <p>
     * 2. Sort horizontal photos
     * Idea - Greedy (choose best photo at each step)
     * a) Choose random photo (with many tags) -> can optimise this
     * b) Calculate scores for all photos
     * c) Choose best photo
     * d) Start from b) again
     * <p>
     * <p>
     * min (# of tags of slide 1, # of tags of side 2) / 2
     */
    @Override
    public Output solution(final Input input) {
        List<Output.SlideInformation> res = new ArrayList<>();
        ArrayList<Photo> photos = new ArrayList<>(input.photos);
        // *** step 1
        ArrayList<Slide> slides = new ArrayList<>();
        slides.addAll(mergeTheVerticalPhoto(photos));
        slides.addAll(extractHorizontalPhotoAsSlide(photos));
        // *** step 2
        // pick a random photo
        Slide slide = slides.get(new Random().nextInt(slides.size()));
        slides.remove(slide);
        res.add(convert(slide));

        while (!slides.isEmpty()) {
            Slide matchedSlide = pickHighestScorePhoto(slides, slide);
            slides.remove(matchedSlide);
            res.add(convert(matchedSlide));
            slide = matchedSlide;
        }

        return new Output(res);
    }

    private List<Slide> mergeTheVerticalPhoto(List<Photo> photos) {
        List<Photo> verticalPhotos = photos.stream()
                                           .filter(photo -> photo.direction == Photo.Direction.VERTICAL)
                                           .sorted((p1, p2) -> p2.tags.size() - p1.tags.size())
                                           .collect(Collectors.toList());
        List<Slide> slides = new ArrayList<>();
        // blinding pick front and tail
        int left = 0, right = verticalPhotos.size() - 1;
        while (left < right) {
            Photo leftPhoto = verticalPhotos.get(left++), rightPhoto = verticalPhotos.get(right--);
            // combine the tags
            Set<String> leftTags = new HashSet<>(leftPhoto.tags);
            leftTags.addAll(new HashSet<>(rightPhoto.tags));
            List<String> intersection = new ArrayList<>(leftTags);
            slides.add(new Slide(List.of(leftPhoto, rightPhoto), intersection));
        }

        return slides;
    }

    private List<Slide> extractHorizontalPhotoAsSlide(List<Photo> photos) {
        return photos.stream()
                     .filter(photo -> photo.direction == Photo.Direction.HORIZONTAL)
                     .map(photo -> new Slide(List.of(photo), photo.tags))
                     .collect(Collectors.toList());
    }

    private Slide pickHighestScorePhoto(List<Slide> slides, Slide slide) {
        long highestScore = Long.MIN_VALUE;
        Slide matchSlide = null;
        for (final Slide s : slides) {
            long matchScore = pair(slide, s);
            if (matchScore > highestScore) {
                highestScore = matchScore;
                matchSlide = s;
            }
            highestScore = Math.max(highestScore, pair(slide, s));
        }
        return matchSlide;
    }

    private long pair(Slide s1, Slide s2) {
        Set<String> tags1 = new HashSet<>(s1.tags), tags2 = new HashSet<>(s2.tags);
        Set<String> p1Subtract = new HashSet<>(s1.tags), p2Subtract = new HashSet<>(s2.tags), intersection = new HashSet<>(
            s1.tags);
        p1Subtract.removeAll(tags2);
        p2Subtract.removeAll(tags1);
        intersection.retainAll(tags2);
        return Math.min(intersection.size(), Math.min(p1Subtract.size(), p2Subtract.size()));
    }

    private Output.SlideInformation convert(Slide slide) {
        return new Output.SlideInformation(slide.photos.stream().map(photo -> photo.id).collect(Collectors.toList()));
    }
}
