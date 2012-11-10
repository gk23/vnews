package cn.vxinwen.summary;

import java.util.List;

import cn.vxinwen.summary.strategy.SimpleSumm;

public class Context {
	private Summ summ;
	public String summarize(List<String> sections){
		summ = new SimpleSumm();
		return summ.summarize(sections);
	}
}
