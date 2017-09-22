package sina.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sina.dao.Dao;
import sina.entity.Firm;
import sina.entity.Notices;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class SinaSNotices implements PageProcessor {
	private Notices notices = new Notices();
	private static Dao dao = new Dao();
	private Firm firm = new Firm();
	// 设置配置信息，重复1次，休眠时间1000毫秒
	private Site site = Site.me().setRetryTimes(1).setSleepTime(3000);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Firm> f = dao.selectFirm();
		for (Firm firm : f) {
			String url = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_BulletinSan/stockid/";
			String urlS = url.concat(firm.getF_code()).concat("/page_type/sjdbg.phtml");
			// 三季度报告
			Spider.create(new SinaSNotices()).addUrl(urlS).thread(5).run();
		}
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	public void process(Page page) {
		String n_code = null;
		String title = null;
		String n_name = null;
		List<String> urls = page.getHtml().xpath("*//[@class='datelist']/ul/a").links().all();
		page.addTargetRequests(urls);
		// 获取网页标题进行处理
		title = page.getHtml().xpath("title/text()").toString();
		String n_title = page.getHtml().xpath("//div[@class='tagmain']/table/thead/tr/th/text()").toString();
		String n_time = page.getHtml().xpath("//div[@class='tagmain']/table/tbody//td[@class='graybgH2']/text()")
				.toString();
		String n_content = page.getHtml().xpath("//div[@id='content']/pre/text()").toString();
		String n_url = page.getHtml().xpath("//div[@class='tagmain']/table/thead/tr/th/font/a").links().get();
		Pattern pattern = Pattern.compile("[0-9]{6}");
		Matcher matcher = pattern.matcher(title);
		if (matcher.find() && matcher.group(0) != null) {
			n_code = matcher.group(0);
			int index1 = title.indexOf("(");
			n_name = title.substring(0, index1);
		}
		if (!(dao.selectNotices(n_url)) || n_time == null) {
		} else {
			notices.setN_name(n_name);
			notices.setN_code(n_code);
			notices.setN_title(n_title);
			notices.setN_time(n_time.substring(5, n_time.length()));
			notices.setN_content(n_content);
			notices.setN_url(n_url);
			dao.addNotices(notices);
		}
	}
}
