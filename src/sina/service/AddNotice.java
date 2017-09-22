package sina.service;

import java.util.List;

import sina.dao.Dao;
import sina.entity.Notice;
import sina.entity.Notices;

public class AddNotice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 创建dao对象
		Dao dao = new Dao();
		/*
		 * Notice notice = new Notice(); for(int i = 32778; i < 41986; i++) {
		 * notice.setN_id(i); dao.updateNotices(notice); }
		 */
		// 调用dao对象中的selectNotices()方法获取公告信息
		List<Notices> list = dao.selectNotices();
		// 将notices表中的信息遍历出来，并且分好类别加入到notice中
		/*
		 * for(int i = 0; i < list.size(); i++) { //创建notice实体类并且添加数据 Notice notice =
		 * new Notice(); notice.setN_name(list.get(i).getN_name());
		 * notice.setN_code(list.get(i).getN_code());
		 * notice.setN_title(list.get(i).getN_title());
		 * notice.setN_time(list.get(i).getN_time());
		 * notice.setN_content(list.get(i).getN_content());
		 * notice.setN_url(list.get(i).getN_url()); notice.setN_event("年度报告");
		 * //调用addNotice()方法添加数据到数据库 dao.addNotice(notice); }
		 */
		for (int i = 0; i < 14133; i++) {
			// 创建notice实体类并且添加数据
			Notice notice = new Notice();
			notice.setN_name(list.get(i).getN_name());
			notice.setN_code(list.get(i).getN_code());
			notice.setN_title(list.get(i).getN_title());
			notice.setN_time(list.get(i).getN_time());
			notice.setN_content(list.get(i).getN_content());
			notice.setN_url(list.get(i).getN_url());
			notice.setN_event("第一季度报告");
			// 调用addNotice()方法添加数据到数据库
			dao.addNotice(notice);
		}
		for (int i = 14133; i < 24494; i++) {
			// 创建notice实体类并且添加数据
			Notice notice = new Notice();
			notice.setN_name(list.get(i).getN_name());
			notice.setN_code(list.get(i).getN_code());
			notice.setN_title(list.get(i).getN_title());
			notice.setN_time(list.get(i).getN_time());
			notice.setN_content(list.get(i).getN_content());
			notice.setN_url(list.get(i).getN_url());
			notice.setN_event("中期报告");
			// 调用addNotice()方法添加数据到数据库
			dao.addNotice(notice);
		}
		for (int i = 24494; i < 33702; i++) {
			// 创建notice实体类并且添加数据
			Notice notice = new Notice();
			notice.setN_name(list.get(i).getN_name());
			notice.setN_code(list.get(i).getN_code());
			notice.setN_title(list.get(i).getN_title());
			notice.setN_time(list.get(i).getN_time());
			notice.setN_content(list.get(i).getN_content());
			notice.setN_url(list.get(i).getN_url());
			notice.setN_event("三季度报告");
			// 调用addNotice()方法添加数据到数据库
			dao.addNotice(notice);
		}
		for (int i = 33702; i < list.size(); i++) {
			// 创建notice实体类并且添加数据
			Notice notice = new Notice();
			notice.setN_name(list.get(i).getN_name());
			notice.setN_code(list.get(i).getN_code());
			notice.setN_title(list.get(i).getN_title());
			notice.setN_time(list.get(i).getN_time());
			notice.setN_content(list.get(i).getN_content());
			notice.setN_url(list.get(i).getN_url());
			notice.setN_event("年度报告");
			// 调用addNotice()方法添加数据到数据库
			dao.addNotice(notice);
		}
	}
}
