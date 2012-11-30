package net.vxinwen.summary;

import net.vxinwen.summary.strategy.SimpleSumm;


public class SummaryGenerator {
	private Summ summ;
	public String summarize(String[][] sections){
		summ = new SimpleSumm();
		return summ.summarize(sections);
	}
}
