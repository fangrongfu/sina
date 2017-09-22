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
* @date：      2017年7月27日 下午3:54:28;
*/
public class MyProSina implements PageProcessor{
	// 设置配置信息，重复1次，休眠时间1000毫秒
	private Site site = Site.me().setRetryTimes(1).setSleepTime(1000);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("爬虫");
		while(true){
			Spider.create(new MyProSina()).addUrl("http://finance.sina.com.cn/").thread(5).run();
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
		Dao d = new Dao();
		// String re =
		// "http://finance.sina.com.cn/chanjing/(\\w+)/([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))/(\\w+).*";
		// page.addTargetRequests(page.getHtml().links().regex("re").all());
		// page.addTargetRequests(page.getHtml().links().regex("http://finance.sina.com.cn/chanjing/gsnews/2017-07-28/doc-ifyinwmp0455775.shtml").all());
		/*
		 * page.addTargetRequests(page.getHtml().links().regex(
		 * "http://finance.sina.com.cn/chanjing/").all());
		 * page.addTargetRequests(page.getHtml().links().regex(
		 * "http://finance.sina.com.cn/chanjing/gsnews/").all());
		 * page.addTargetRequests(page.getHtml().links().regex(
		 * "http://finance.sina.com.cn/chanjing/gsnews/2017-07-31").all());
		 */
		// page.addTargetRequests(page.getHtml().links().regex("(http://finance\\.sina\\.com\\.cn/(\\w+)/)").all());
		page.addTargetRequests(page.getHtml().links()
				.regex("(http://finance\\.sina\\.com\\.cn/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+.*)").all());
		String j_url = page.getHtml().links()
				.regex("(http://finance\\.sina\\.com\\.cn/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+.*)").get();
		String title = page.getHtml().xpath("//div[@class='page-header']/h1/text()").toString();
		String time = page.getHtml().xpath("//span[@class='time-source']/text()").toString();
		list = page.getHtml().xpath("//div[@class='article article_16']/p/text()").all();
		ListFor lf = new ListFor();
		String content = lf.forList(list);
		if("".equals(content) || (d.selectJournalism(title))) {
			jour.setJ_title(title);
			jour.setJ_time(time);
			jour.setJ_digest("");
			jour.setJ_content(content);
			jour.setJ_url(j_url);
		}else {
			String content1 = content.substring(0, 100);
			String content3 = content1.concat("......");
			String timeOne = time.replaceAll("年", "-");
			String timeTwo = timeOne.replaceAll("月", "-");
			time = timeTwo.substring(1, 11);
			jour.setJ_title(title);
			jour.setJ_time(time);
			jour.setJ_digest(content3);
			jour.setJ_content(content);
			jour.setJ_url(j_url);
			//System.out.println("这是测试数据"+jour);
		}
		d.add(jour);
		page.putField("page", jour);
		/*
		 * page.putField("title",
		 * page.getHtml().xpath("//div[@class='page-header']/h1/text()").
		 * toString()); page.putField("time",
		 * page.getHtml().xpath("//span[@class='time-source']/text()").toString(
		 * )); list =
		 * page.getHtml().xpath("//div[@class='article article_16']/p/text()").
		 * all(); ListFor lf = new ListFor(); String content = lf.forList(list);
		 */
		// page.putField("page",page);
	}
}
