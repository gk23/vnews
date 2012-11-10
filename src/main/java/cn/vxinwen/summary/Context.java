package cn.vxinwen.summary;

import cn.vxinwen.summary.strategy.SimpleSumm;

public class Context {
	private Summ summ;
	public String summarize(String content){
		summ = new SimpleSumm();
		return summ.summarize(content);
	}
}
