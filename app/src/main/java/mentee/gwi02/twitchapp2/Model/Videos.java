package mentee.gwi02.twitchapp2.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Videos {

    @SerializedName("_total")
    @Expose
    private Integer total;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public class Video {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private Object description;
        @SerializedName("description_html")
        @Expose
        private Object descriptionHtml;
        @SerializedName("broadcast_id")
        @Expose
        private String broadcastId;
        @SerializedName("broadcast_type")
        @Expose
        private String broadcastType;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("tag_list")
        @Expose
        private String tagList;
        @SerializedName("views")
        @Expose
        private Integer views;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("viewable")
        @Expose
        private String viewable;
        @SerializedName("viewable_at")
        @Expose
        private Object viewableAt;
        @SerializedName("published_at")
        @Expose
        private String publishedAt;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("recorded_at")
        @Expose
        private String recordedAt;
        @SerializedName("game")
        @Expose
        private String game;
        @SerializedName("communities")
        @Expose
        private Object communities;
        @SerializedName("length")
        @Expose
        private Integer length;
        @SerializedName("preview")
        @Expose
        private Preview preview;
        @SerializedName("animated_preview_url")
        @Expose
        private String animatedPreviewUrl;
        /*
        @SerializedName("thumbnails")
        @Expose
        private Thumbnails thumbnails;
        @SerializedName("fps")
        @Expose
        private Fps fps;
        @SerializedName("seek_previews_url")
        @Expose
        private String seekPreviewsUrl;
        @SerializedName("resolutions")
        @Expose
        private Resolutions resolutions;
        @SerializedName("restriction")
        @Expose
        private String restriction;
        */
        @SerializedName("channel")
        @Expose
        private ChannelData.Stream.Channel channel;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Object getDescriptionHtml() {
            return descriptionHtml;
        }

        public void setDescriptionHtml(Object descriptionHtml) {
            this.descriptionHtml = descriptionHtml;
        }

        public String getBroadcastId() {
            return broadcastId;
        }

        public void setBroadcastId(String broadcastId) {
            this.broadcastId = broadcastId;
        }

        public String getBroadcastType() {
            return broadcastType;
        }

        public void setBroadcastType(String broadcastType) {
            this.broadcastType = broadcastType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTagList() {
            return tagList;
        }

        public void setTagList(String tagList) {
            this.tagList = tagList;
        }

        public Integer getViews() {
            return views;
        }

        public void setViews(Integer views) {
            this.views = views;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getViewable() {
            return viewable;
        }

        public void setViewable(String viewable) {
            this.viewable = viewable;
        }

        public Object getViewableAt() {
            return viewableAt;
        }

        public void setViewableAt(Object viewableAt) {
            this.viewableAt = viewableAt;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRecordedAt() {
            return recordedAt;
        }

        public void setRecordedAt(String recordedAt) {
            this.recordedAt = recordedAt;
        }

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public Object getCommunities() {
            return communities;
        }

        public void setCommunities(Object communities) {
            this.communities = communities;
        }

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public Preview getPreview() {
            return preview;
        }

        public void setPreview(Preview preview) {
            this.preview = preview;
        }

        public String getAnimatedPreviewUrl() {
            return animatedPreviewUrl;
        }

        public void setAnimatedPreviewUrl(String animatedPreviewUrl) {
            this.animatedPreviewUrl = animatedPreviewUrl;
        }
        /*
            public Thumbnails getThumbnails() {
                return thumbnails;
            }

            public void setThumbnails(Thumbnails thumbnails) {
                this.thumbnails = thumbnails;
            }

            public Fps getFps() {
                return fps;
            }

            public void setFps(Fps fps) {
                this.fps = fps;
            }

            public String getSeekPreviewsUrl() {
                return seekPreviewsUrl;
            }

            public void setSeekPreviewsUrl(String seekPreviewsUrl) {
                this.seekPreviewsUrl = seekPreviewsUrl;
            }

            public Resolutions getResolutions() {
                return resolutions;
            }

            public void setResolutions(Resolutions resolutions) {
                this.resolutions = resolutions;
            }

            public String getRestriction() {
                return restriction;
            }

            public void setRestriction(String restriction) {
                this.restriction = restriction;
            }
        */
        public ChannelData.Stream.Channel getChannel() {
            return channel;
        }

        public void setChannel(ChannelData.Stream.Channel channel) {
            this.channel = channel;
        }

        public class Preview {

            @SerializedName("small")
            @Expose
            private String small;
            @SerializedName("medium")
            @Expose
            private String medium;
            @SerializedName("large")
            @Expose
            private String large;
            @SerializedName("template")
            @Expose
            private String template;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getTemplate() {
                return template;
            }

            public void setTemplate(String template) {
                this.template = template;
            }

        }
    }
}
