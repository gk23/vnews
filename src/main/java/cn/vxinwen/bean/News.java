package cn.vxinwen.bean;

import java.util.List;

public class News {

	private String imageAddress;
	private String title;
	private List<String> sections;
	private String content;

    private String summary;

	public String getImageAddress() {
		return imageAddress;
	}
	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getSections() {
		return sections;
	}
	public void setSections(List<String> sections) {
		this.sections = sections;
	}
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
