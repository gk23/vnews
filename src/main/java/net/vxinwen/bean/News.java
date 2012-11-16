package net.vxinwen.bean;


public class News {

	private String imageAddress;
	private String title;
	private String[][] sections;


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
    public void setSections(String[][] sections) {
        this.sections = sections;
    }
    public String[][] getSections() {
        return sections;
    }
}
