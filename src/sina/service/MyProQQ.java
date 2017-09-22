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
* @date：        2017年7月31日 下午4:17:00;
*/
public class MyProQQ implements PageProcessor{
	// 设置配置信息，重复1次，休眠时间1000毫秒
	private Site site = Site.me().setRetryTimes(1).setSleepTime(1000);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("爬虫");
		while(true){
			Spider.create(new MyProQQ()).addUrl("http://finance.qq.com/").thread(5).run();
		}	
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	public void process(Page page) {
		// TODO Auto-generated method stub
		String content = null;
		List<String> list = new ArrayList<String>();
		Journalism jour = new Journalism();
		Dao dao = new Dao();
		page.addTargetRequests(
				page.getHtml().links().regex("(http://finance\\.qq\\.com/a/[\\w\\-]+/[\\w\\-]+.*)").all());
		String j_url = page.getHtml().links().regex("(http://finance\\.qq\\.com/a/[\\w\\-]+/[\\w\\-]+.*)").get();
		String title = page.getHtml().xpath("//div[@class='hd']/h1/text()").toString();
		String time = page.getHtml().xpath("//span[@class='a_time']/text()").toString();
		list = page.getHtml().xpath("//div[@class='Cnt-Main-Article-QQ']//p[@class='text']/text()").all();
		ListFor lf = new ListFor();
		content = lf.forList(list);
		if("".equals(content) || (dao.selectJournalism(title))){
			jour.setJ_content(null);
			jour.setJ_title(title);
			jour.setJ_time(time);
			jour.setJ_digest("");
			jour.setJ_url(j_url);
		}else {
			String content1 = content.substring(0, 100);
			String content3 = content1.concat("......");
			jour.setJ_title(title);
			jour.setJ_time(time.substring(0, 11));
			jour.setJ_digest(content3);
			jour.setJ_content(content);
			jour.setJ_url(j_url);
		}
		dao.add(jour);
		page.putField("page", jour);
	}
}
