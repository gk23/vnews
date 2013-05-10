package net.vxinwen.bean;

import java.sql.Timestamp;

public class News {
	private long id;
	private String image;
	private String title;
	private String[][] sections;
	private String category;
	private String url;
	private String body;
	private String summary;
	private String source;
    /**
     * 暂时没用，以后用于用户定制新闻，组件替代category字段
     */
    private String tags;
    private Timestamp publishTime;
    private Timestamp modifyTime;
	public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }



	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String content) {
		this.body = content;
	}

	public void setSections(String[][] sections) {
		this.sections = sections;
	}

	public String[][] getSections() {
		return sections;
	}
}
