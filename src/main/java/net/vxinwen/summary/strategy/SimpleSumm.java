package net.vxinwen.summary.strategy;

import java.util.List;

import org.apache.log4j.Logger;

import net.vxinwen.summary.Summ;

/**
 * 每段随机抽取一句
 * 
 * @author gk23
 * 
 */
public class SimpleSumm implements Summ {

	private static int MAX_WORD = 140;
	private int remainWord = MAX_WORD;
	private int maxSentence = 0;
	private boolean[][] isChosed; // 默认都为false
	private Logger logger = Logger.getLogger(SimpleSumm.class);

	public void init(String[][] content) {
		logger.debug("The length of conntent to be summarized is "
				+ content.length);
		// 初始化选择句子的标志位,计算最大段落的句子数
		int sectionCount = content.length;
		isChosed = new boolean[sectionCount][];
		int i = 0;
		for (String[] section : content) {
			isChosed[i] = new boolean[section.length];
			for (boolean flag : isChosed[i])
				flag = false;
			maxSentence = section.length > maxSentence ? section.length
					: maxSentence;
			i++;
		}
	}

	/**
	 * content: 每句需要带标点，即句号或叹号的标点符号。
	 */
	public String summarize(String[][] content) {
		init(content);
		if (isChosed == null || isChosed.length == 0)
			return null;
		int sectionCount = content.length;
		// 取首句
		isChosed[0][0] = true;
		remainWord -= content[0][0].length();

		// 取尾部句子
		if (sectionCount > 1 && !isExceeded(content[sectionCount - 1][0])) {
			isChosed[sectionCount - 1][0] = true;
			remainWord -= content[sectionCount - 1][0].length();
		}

		// 遍历所有false的句子，按照首句，取中间句子，先首句，再次句
		for (int i = 0; i < maxSentence; i++) {
			for (int j = 0; j < content.length; j++) {
				if (content[j].length <= i || isChosed[j][i])
					continue;
				if (!isExceeded(content[j][i])) {
					remainWord -= content[j][i].length();
					isChosed[j][i] = true;
				}
			}
		}

		// 按顺序组合所有选择的句子
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < isChosed[i].length; j++) {
				if (isChosed[i][j])
					sb.append(content[i][j]);
			}
		}
		return sb.toString();
	}

	public boolean isExceeded(String toBeAddedString) {
		// isExceeded为true则不能添加此句
		return toBeAddedString.length() > remainWord;
	}

	public static void main(String[] args) {
		String test = "一二3";
		System.out.println(test.length());
		boolean[] flags = new boolean[100];
		for (boolean f : flags)
			if (f) {
				System.out.println(f);
			}
	}
}
