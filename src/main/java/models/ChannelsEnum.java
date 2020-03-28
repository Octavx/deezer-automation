package models;

public enum ChannelsEnum {
    RAP("Rap"),
    ROCK("Rock"),
    ELECTRONIC("Electronic"),
    JAZZ("Jazz");

    private String channelName;

    private ChannelsEnum(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }
}
