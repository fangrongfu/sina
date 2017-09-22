package sina.service;

import java.util.ArrayList;
import java.util.List;

import sina.dao.Dao;
import sina.entity.Journalism;
import sina.util.ListFor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/** 
* @author:  fangrongfu; 
* @version: 1.0;
* @date：      2017年7月31日 下午4:20:06;
*/
public class MyProNetEase implements PageProcessor{
	// 设置配置信息，重复1次，休眠时间1000毫秒
	private Site site = Site.me().setRetryTimes(1).setSleepTime(1000);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("爬虫");
		while(true){
			Spider.create(new MyProNetEase()).addUrl("http://money.163.com/").thread(5).run();
		}	
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	public void process(Page page) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		Journalism jour = new Journalism();
		Dao dao = new Dao();
		String s = "http://money.163.com/17/0731/07/CQLJ6EKS0025816E.html";
		page.addTargetRequests(page.getHtml().links()
				.regex("(http://money\\.163\\.com/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+.*)").all());
		String j_url = page.getHtml().links()
				.regex("(http://money\\.163\\.com/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+.*)").get();
		//System.out.println("获取链接"+url);
		String title = page.getHtml().xpath("//div[@class='post_content_main']/h1/text()").toString();
		String time = page.getHtml().xpath("//div[@class='post_time_source']/text()").toString();
		list = page.getHtml().xpath("//div[@class='post_text']/p/text()").all();
		ListFor lf = new ListFor();
		String content = lf.forList(list);
		if("".equals(content) || (dao.selectJournalism(title))) {
			jour.setJ_title(title);
			jour.setJ_time(time);
			jour.setJ_digest("");
			jour.setJ_content(content);
			jour.setJ_url(j_url);
		}else {
			String content1 = content.substring(0, 100);
			String content3 = content1.concat("......");
			jour.setJ_title(title);
			jour.setJ_time(time.substring(1, 11));
			jour.setJ_digest(content3);
			jour.setJ_content(content);
			jour.setJ_url(j_url);
			//System.out.println("获取链接"+url);
		}
		dao.add(jour);
		page.putField("page", jour);
	}
}
