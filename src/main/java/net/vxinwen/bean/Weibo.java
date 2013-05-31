package net.vxinwen.bean;

public class Weibo {
    private String userName;
    private String icon;
    private String content;
    private String image;
    private String bigImage;
    private String publishTime;
    private String temperature;
    /**
     * 转发数
     */
    private String repostCount;
    private String repostLink;
    /**
     *  评论数
     */
    private String commentCount;
    private String commentLink;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getBigImage() {
        return bigImage;
    }
    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }
    public String getPublishTime() {
        return publishTime;
    }
    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
    public String getTemperature() {
        return temperature;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public String getRepostCount() {
        return repostCount;
    }
    public void setRepostCount(String repostCount) {
        this.repostCount = repostCount;
    }
    public String getRepostLink() {
        return repostLink;
    }
    public void setRepostLink(String repostLink) {
        this.repostLink = repostLink;
    }
    public String getCommentCount() {
        return commentCount;
    }
    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }
    public String getCommentLink() {
        return commentLink;
    }
    public void setCommentLink(String commentLink) {
        this.commentLink = commentLink;
    }
}
