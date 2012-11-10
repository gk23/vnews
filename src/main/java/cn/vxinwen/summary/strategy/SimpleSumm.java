package cn.vxinwen.summary.strategy;

import java.util.List;

import cn.vxinwen.summary.Summ;

/**
 * 每段随机抽取一句
 * 
 * @author gk23
 *
 */
public class SimpleSumm implements Summ{

    private static int MAX_WORD_COUNT = 140;
	/**
	 * 
	 */
	public String summarize(List<String> sections) {
		String test="中国";
		System.out.println(test.split("。"));
		return null;
	}
	public static void main(String[] args) {
        String test="你好，中国。我是中国人。(完)。";
        String[] res = test.split("。");
        System.out.println(res.length);
        System.out.println(res[0]+"==="+res[1]+"===="+res[2]);
    }
}
