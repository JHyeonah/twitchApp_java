package mentee.gwi02.twitchapp2.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {
    @SerializedName("_total")
    @Expose
    private Integer total;
    @SerializedName("channels")
    @Expose
    private List<ChannelData.Stream.Channel> channels = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<ChannelData.Stream.Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelData.Stream.Channel> channels) {
        this.channels = channels;
    }
}
