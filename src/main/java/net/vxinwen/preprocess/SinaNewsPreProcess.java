package net.vxinwen.preprocess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SinaNewsPreProcess {

	public static String[][] getSections(String content){
		Document doc = Jsoup.parse(content);
        Elements ps = doc.getElementsByTag("p");
        String[][] sections = new String[ps.size()][];
        int offsize = 0;
        StringBuilder sb;
        char c;
        List<String> sentences;
        for (Element section : ps) {
            // 添加每句，以句号或叹号结尾
            String sectionContent = section.text();
            if (sectionContent.contains("。") || sectionContent.contains("！")) {
                sentences = new ArrayList<String>();
                sb = new StringBuilder();
                for (int i = 0; i < sectionContent.length(); i++) {
                    c = sectionContent.charAt(i);
                    if (c != '。' && c != '！') {
                        sb.append(c);
                    } else {
                        sb.append(c);
                        sentences.add(sb.toString().trim().replace("　", ""));
                        sb = new StringBuilder();
                    }
                }
                sections[offsize++] =sentences.toArray(new String[0]);
            }
        }
        // 只保存包含句号的段落，sections[offsize+1]之后的数组都为null，所以舍去
        return Arrays.copyOf(sections, offsize);
	}
	
}
