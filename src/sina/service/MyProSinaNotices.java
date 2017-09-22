package sina.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sina.dao.Dao;
import sina.entity.Notices;
import sina.util.Regular;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/** 
* @author:  fangrongfu; 
* @version: 1.0;
* @date：       2017年8月1日 下午12:46:38;
*/
public class MyProSinaNotices implements PageProcessor {
	// 设置配置信息，重复1次，休眠时间1000毫秒
	private Site site = Site.me().setRetryTimes(1).setSleepTime(500);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("爬虫");
		while (true) {
			Spider.create(new MyProSinaNotices()).addUrl("http://finance.sina.com.cn/stock/").thread(5).run();
		}
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	public void process(Page page) {
		// TODO Auto-generated method stub
		String n_code = null;
		String title = null;
		String n_name = null;
		Notices notices = new Notices();
		Regular regular = new Regular();
		Dao d = new Dao();
		page.addTargetRequests(page.getHtml().links()
				.regex("(http://vip\\.stock\\.finance\\.sina\\.com\\.cn/corp/view/[\\w\\-]+.*)").all());
		//获取网页标题进行处理
		title = page.getHtml().xpath("title/text()").toString();
		String n_title = page.getHtml().xpath("//div[@class='tagmain']/table/thead/tr/th/text()").toString();
		String n_time = page.getHtml().xpath("//div[@class='tagmain']/table/tbody//td[@class='graybgH2']/text()")
				.toString();
		String n_content = page.getHtml().xpath("//div[@id='content']/pre/text()").toString();
		String url = page.getHtml().xpath("//div[@class='tagmain']/table/tbody/tr/td/a").toString();
		Pattern pattern = Pattern.compile("[0-9]{6}");
		Matcher matcher = pattern.matcher(title);
		if(matcher.find() && matcher.group(0) != null){
			n_code = matcher.group(0);
			int index1 =title.indexOf("(");
			n_name = title.substring(0, index1);
		}
		String n_url = regular.regular(url);
		if(!(d.selectNotices(n_url)) || n_time == null){
			notices.setN_name(null);
			notices.setN_code(null);
			notices.setN_title(null);
			notices.setN_time(null);
			notices.setN_content(null);
			notices.setN_url(null);
			page.putField("page", notices);
		}else{
			notices.setN_name(n_name);
			notices.setN_code(n_code);
			notices.setN_title(n_title);
			notices.setN_time(n_time.substring(5, n_time.length()));
			notices.setN_content(n_content);
			notices.setN_url(n_url);
			d.addNotices(notices);
			page.putField("page", notices);
		}
	}
}
