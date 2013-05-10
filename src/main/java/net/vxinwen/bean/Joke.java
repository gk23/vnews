package net.vxinwen.bean;

import java.sql.Timestamp;

public class Joke {
    private long id;
    private String image;
    private String title;
    private String body;
    private Timestamp publishTime;
    private String url;
    private String source;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String imageAddress) {
        this.image = imageAddress;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String content) {
        this.body = content;
    }
    public Timestamp getPublishTime() {
        return publishTime;
    }
    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

}
