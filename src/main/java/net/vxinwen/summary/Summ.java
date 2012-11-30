package net.vxinwen.summary;


public interface Summ {
	/**
	 * 
	 * @param sections 二维数组，第一维为段落数组，第二维为每个段落的句子数组。
	 * @return 文章的摘要
	 */
	String summarize(String[][] sections);
	
}
