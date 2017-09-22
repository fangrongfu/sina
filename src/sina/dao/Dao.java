package sina.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sina.entity.Company;
import sina.entity.Firm;
import sina.entity.Journalism;
import sina.entity.Notice;
import sina.entity.Notices;
import sina.util.JdbcConnection;

/**
 * @author: fangrongfu;
 * @version: 1.0; 
 * @date： 2017年7月27日 下午3:45:57;
 */
public class Dao {
	private String sql = null;// 动态SQL语句
	private PreparedStatement ps = null;// 创建sql语句执行对象
	private Connection con = JdbcConnection.getConnection();// 调用工具类进行本地数据库连接
	private Connection con1 = JdbcConnection.getConnectionOne();// 调用工具类进行外部数据库连接

	// JDBC添加数据到数据库，添加新闻信息到服务器数据库
	public void add(Journalism jour) {
		try {
			sql = "insert into journalism values(null,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			if ("".equals(jour.getJ_digest()) || jour.getJ_content() == null || jour.getJ_title() == null
					|| jour.getJ_time() == null) {
				System.out.println("空数据");
			} else {
				ps.setString(1, jour.getJ_title());
				ps.setString(2, jour.getJ_time());
				ps.setString(3, jour.getJ_digest());
				ps.setString(4, jour.getJ_content());
				ps.setString(5, jour.getJ_url());
				int i = ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// JDBC添加数据到数据库，通过文章标题查询数据库信息来判断添加信息是否重复
	public boolean selectJournalism(String title) {
		sql = "select * from journalism where j_title = ?";
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			rs = ps.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}

	// JDBC添加数据到数据库,将公告信息添加到外部数据库
	public void addNotices(Notices notices) {
		try {
			sql = "insert into notices values(null,?,?,?,?,?,?)";
			ps = con1.prepareStatement(sql);
			if (notices.getN_url() == null || notices.getN_name() == null || notices.getN_code() == null
					|| notices.getN_title()== null || notices.getN_time() == null || notices.getN_content()
					== null) {
				System.out.println("空数据");
			} else {
				ps.setString(1, notices.getN_name());
				ps.setString(2, notices.getN_code());
				ps.setString(3, notices.getN_title());
				ps.setString(4, notices.getN_time());
				ps.setString(5, notices.getN_content());
				ps.setString(6, notices.getN_url());
				int i = ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// JDBC添加数据到数据库，通过n_url查询数据库信息来判断添加信息是否重复
	public boolean selectNotices(String n_url) {
		sql = "select * from notices where n_url = ?";
		ResultSet rs = null;
		try {
			ps = con1.prepareStatement(sql);
			ps.setString(1, n_url);
			rs = ps.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(rs == null) {
			return false;
		} 
		return true;
	}
	
	// 查询公告表中的公告id以及公告的日期
	public List<Notice> selectNotice() {
		sql = "select n_id,n_time from notice";
		ResultSet rs = null;
		List<Notice> list = new ArrayList<Notice>();
		try {
			ps = con1.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setN_id(rs.getInt(1));
				notice.setN_time(rs.getString(2));
				list.add(notice);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return list;
	}
	
	// 修改公告表中的公告日期
	public int updateNotice(Notice notice) {
		sql = "update notice set n_time = ? where n_id = ?";
		int i = 0;
		try {
			ps = con1.prepareStatement(sql);
			ps.setString(1, notice.getN_time());
			ps.setInt(2, notice.getN_id());
			i = ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}
	
	// 将公司名字和股票代码添加到服务器数据库中firm表中
	public void addFirm(Firm firm) {
		sql = "insert into firm values(null,?,?)";
		try {
			ps = con1.prepareStatement(sql);
			ps.setString(1, firm.getF_name());
			ps.setString(2, firm.getF_code());
			int i = ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	// 查询firm表中的信息
	public List<Firm> selectFirm() {
		ResultSet rs = null;
		sql = "select f_name,f_code from firm";
		List<Firm> list = new ArrayList<Firm>();
		try {
			ps = con1.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Firm firm = new Firm();
				firm.setF_name(rs.getString(1));
				firm.setF_code(rs.getString(2));
				list.add(firm);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return list;
	}
	
	//查询服务器数据库中notices表中数据
	public List<Notices> selectNotices(){
		ResultSet rs = null;
		sql = "select * from notices";
		List<Notices> list = new ArrayList<Notices>();
		try {
			ps = con1.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Notices notices = new Notices();
				notices.setN_id(rs.getInt(1));
				notices.setN_name(rs.getString(2));
				notices.setN_code(rs.getString(3));
				notices.setN_title(rs.getString(4));
				notices.setN_time(rs.getString(5));
				notices.setN_content(rs.getString(6));
				notices.setN_url(rs.getString(7));
				list.add(notices);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return list;
	}
	
	// 将公告信息分好类加入到notice中
	public void addNotice(Notice notice) {
		sql = "insert into notice values(null,?,?,?,?,?,?,?)";
		try {
			ps = con1.prepareStatement(sql);
			ps.setString(1, notice.getN_name());
			ps.setString(2, notice.getN_code());
			ps.setString(3, notice.getN_title());
			ps.setString(4, notice.getN_time());
			ps.setString(5, notice.getN_content());
			ps.setString(6, notice.getN_url());
			ps.setString(7, notice.getN_event());
			int i = ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	// 修改公告表中的公告日期
	public int updateNotices(Notice notice) {
		sql = "update notice set n_event = ? where n_id = ?";
		int i = 0;
		try {
			ps = con1.prepareStatement(sql);
			ps.setString(1, "三季度报告");
			ps.setInt(2, notice.getN_id());
			i = ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}
}
