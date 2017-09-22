package sina.service;

import java.util.ArrayList;
import java.util.List;

import sina.dao.Dao;
import sina.entity.Notice;

/**
 * @author  fangrongfu
 * @version 1.0
 * @time    2017年8月30日下午3:57:51
 */
public class UpdateNotice {
	public static void main(String[] args) {
		Dao dao = new Dao();
		List<Notice> list = new ArrayList<Notice>();
		list = dao.selectNotice();
		/*String a = "2017-08-05";
		System.out.println(a.length());
		String b = "08-05";
		System.out.println(b.length());
		String c = "2017-".concat(b);
		System.out.println(c);*/
		for(Notice notice : list) {
			Notice n = new Notice();
			int i = notice.getN_time().length();
			if(i < 8) {
				n.setN_id(notice.getN_id());
				n.setN_time("2017-".concat(notice.getN_time()));
				int j = dao.updateNotice(n);
			}
		}
	}
}
