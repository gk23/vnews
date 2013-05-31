package net.vxinwen.bean;
/**
 * 热门微博
 * 
 * @author gk23
 *
 */
public class Weibo {
    /**
     * 微博分类，有全部，娱乐，财经，科技等
     */
    private WeiboCategory category;

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
    public WeiboCategory getCategory() {
        return category;
    }
    public void setCategory(WeiboCategory category) {
        this.category = category;
    }
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
    /**
     * 热门微博全部类别
     *
     * @author gk23
     *
     */
    enum WeiboCategory{
        All("全部","9999"),
        YuLe("娱乐","1099"),
        CaiJing("财经","1299"),
        Keji("科技","2099"),
        ShiShang("时尚","1599"),
        JiangKang("健康","2199"),
        TiYu("体育","1399"),
        WenHua("文化","1499"),
        XingZuo("星座","1699"),
        YouMo("幽默","1899"),
        ZheYan("哲言","1999"),
        SheHui("社会","1799"),
        ShiPin("视频","1199");
        private String cnName;
        private String url;
        WeiboCategory(String cnName,String url){
            this.cnName = cnName;
            this.url="http://hot.weibo.com/?v="+url;
        }
        public String getCnName(){
            return this.cnName;
        }
        public String getUrl(){
            return this.url;
        }
    };
}
