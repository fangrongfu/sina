package sina.service;

import java.util.List;

import sina.dao.Dao;
import sina.entity.Firm;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class FirmNameAndCode implements PageProcessor{
	// 设置配置信息，重复1次，休眠时间1000毫秒
	private Site site = Site.me().setRetryTimes(1).setSleepTime(3000);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("爬虫");
		Spider.create(new FirmNameAndCode()).addUrl("http://quote.eastmoney.com/stocklist.html").thread(5).run();
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	public void process(Page page) {
		List<String> str = page.getHtml().xpath("//div[@id='quotesearch']/ul/li/a/text()").all();
		//System.out.println(str.size());
		//一季度报告
		String s = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_BulletinYi/stockid/300663/page_type/yjdbg.phtml";
		//中期报告
		String s1 = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_BulletinZhong/stockid/300663/page_type/zqbg.phtml";
		//三季度报告
		String s2 = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_BulletinSan/stockid/300663/page_type/sjdbg.phtml";
		//年度报告
		String s3 = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_Bulletin/stockid/300663/page_type/ndbg.phtml";
		for (int i = 0; i < str.size(); i++) {
			int index1 = str.get(i).indexOf("(");
			String f_name = str.get(i).substring(0, index1);
			String f_code = str.get(i).substring(index1 + 1, str.get(i).length() - 1);
			Firm firm = new Firm();
			firm.setF_name(f_name);
			firm.setF_code(f_code);
			Dao dao = new Dao();
			dao.addFirm(firm);
		}
	}
}
