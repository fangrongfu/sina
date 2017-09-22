package sina.entity;
/** 
* @author:  fangrongfu; 
* @version: 1.0;
* @date：        2017年8月2日 上午11:08:17;
*/
public class Notice {
	private int n_id;// 公司公告表的ID
	private String n_name;//  公司的名称
	private String n_code;//  公司的股票代码
	private String n_title;//  公司公告的标题
	private String n_time;//  公司公告的时间
	private String n_content;//  公司公告的内容
	private String n_url;//  公司公告PDF下载的url
	private String n_event;//  公司公告的事件类型
	/**
	 * @return the n_id
	 */
	public int getN_id() {
		return n_id;
	}
	/**
	 * @param n_id the n_id to set
	 */
	public void setN_id(int n_id) {
		this.n_id = n_id;
	}
	/**
	 * @return the n_name
	 */
	public String getN_name() {
		return n_name;
	}
	/**
	 * @param n_name the n_name to set
	 */
	public void setN_name(String n_name) {
		this.n_name = n_name;
	}
	/**
	 * @return the n_code
	 */
	public String getN_code() {
		return n_code;
	}
	/**
	 * @param n_code the n_code to set
	 */
	public void setN_code(String n_code) {
		this.n_code = n_code;
	}
	/**
	 * @return the n_title
	 */
	public String getN_title() {
		return n_title;
	}
	/**
	 * @param n_title the n_title to set
	 */
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	/**
	 * @return the n_time
	 */
	public String getN_time() {
		return n_time;
	}
	/**
	 * @param n_time the n_time to set
	 */
	public void setN_time(String n_time) {
		this.n_time = n_time;
	}
	/**
	 * @return the n_content
	 */
	public String getN_content() {
		return n_content;
	}
	/**
	 * @param n_content the n_content to set
	 */
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	/**
	 * @return the n_url
	 */
	public String getN_url() {
		return n_url;
	}
	/**
	 * @param n_url the n_url to set
	 */
	public void setN_url(String n_url) {
		this.n_url = n_url;
	}
	/**
	 * @return the n_event
	 */
	public String getN_event() {
		return n_event;
	}
	/**
	 * @param n_event the n_event to set
	 */
	public void setN_event(String n_event) {
		this.n_event = n_event;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Notice [n_id=" + n_id + ", n_name=" + n_name + ", n_code=" + n_code + ", n_title=" + n_title
				+ ", n_time=" + n_time + ", n_content=" + n_content + ", n_url=" + n_url + ", n_event=" + n_event + "]";
	}
}
