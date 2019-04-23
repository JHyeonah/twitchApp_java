package mentee.gwi02.twitchapp2.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Follows {
    @SerializedName("_total")
    @Expose
    private Integer total;
    @SerializedName("follows")
    @Expose
    private List<Follow> follows = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Follow> getFollows() {
        return follows;
    }

    public void setFollows(List<Follow> follows) {
        this.follows = follows;
    }

    public class Follow {

        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("channel")
        @Expose
        private ChannelData.Stream.Channel channel;
        @SerializedName("notifications")
        @Expose
        private Boolean notifications;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public ChannelData.Stream.Channel getChannel() {
            return channel;
        }

        public void setChannel(ChannelData.Stream.Channel channel) {
            this.channel = channel;
        }

        public Boolean getNotifications() {
            return notifications;
        }

        public void setNotifications(Boolean notifications) {
            this.notifications = notifications;
        }

    }
}
