import java.util.List;

public class Output {

    private final int numSlides; // Number of slides in the slideshow

    private final List<SlideInformation> slideInformationList;

    public Output(List<SlideInformation> slideInformationList) {
        this.numSlides = slideInformationList.size();
        this.slideInformationList = slideInformationList;
    }

    public int getNumSlides() {
        return numSlides;
    }

    public List<SlideInformation> getSlideInformationList() {
        return slideInformationList;
    }

    static class SlideInformation {
        private final List<Integer> photoIdList;

        public SlideInformation(List<Integer> photoIdList) {
            this.photoIdList = photoIdList;
        }

        public List<Integer> getPhotoIdList() {
            return photoIdList;
        }
    }
}
