package mentee.gwi02.twitchapp2.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recommend {

    @SerializedName("featured")
    @Expose
    private List<Featured> featured = null;

    public List<Featured> getFeatured() {
        return featured;
    }

    public void setFeatured(List<Featured> featured) {
        this.featured = featured;
    }

    public class Featured {
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("priority")
        @Expose
        private Integer priority;
        @SerializedName("scheduled")
        @Expose
        private Boolean scheduled;
        @SerializedName("sponsored")
        @Expose
        private Boolean sponsored;
        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("stream")
        @Expose
        private Example.Stream stream;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public Boolean getScheduled() {
            return scheduled;
        }

        public void setScheduled(Boolean scheduled) {
            this.scheduled = scheduled;
        }

        public Boolean getSponsored() {
            return sponsored;
        }

        public void setSponsored(Boolean sponsored) {
            this.sponsored = sponsored;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Example.Stream getStream() {
            return stream;
        }

        public void setStream(Example.Stream stream) {
            this.stream = stream;
        }
    }

}
