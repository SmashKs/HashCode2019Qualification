import java.util.List;

public class Output {

    private final int numSlides; // Number of slides in the slideshow

    private final List<SlideInformation> slideInformationList;

    public Output(int numSlides, List<SlideInformation> slideInformationList) {
        this.numSlides = numSlides;
        this.slideInformationList = slideInformationList;
    }

    public int getNumSlides() {
        return numSlides;
    }

    public List<SlideInformation> getSlideInformationList() {
        return slideInformationList;
    }

    class SlideInformation {
        private final List<Integer> photoIdList;

        public SlideInformation(List<Integer> photoIdList) {
            this.photoIdList = photoIdList;
        }

        public List<Integer> getPhotoIdList() {
            return photoIdList;
        }
    }
}
