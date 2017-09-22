package sina.entity;

/**
 * @author  fangrongfu
 * @version 1.0
 * @time    2017年8月21日下午10:52:06
 */
public class Journalism {
	private int j_id;//爬取的新闻的ID
	private String j_title;//爬取的新闻发布的标题
	private String j_time;//爬取的新闻发布的时间
	private String j_digest;//爬取的新闻发布的时间
	private String j_content;//爬取的新闻发布的内容
	private String j_url;//爬取的新闻内容的链接
	/**
	 * @return the j_id
	 */
	public int getJ_id() {
		return j_id;
	}
	/**
	 * @param j_id the j_id to set
	 */
	public void setJ_id(int j_id) {
		this.j_id = j_id;
	}
	/**
	 * @return the j_title
	 */
	public String getJ_title() {
		return j_title;
	}
	/**
	 * @param j_title the j_title to set
	 */
	public void setJ_title(String j_title) {
		this.j_title = j_title;
	}
	/**
	 * @return the j_time
	 */
	public String getJ_time() {
		return j_time;
	}
	/**
	 * @param j_time the j_time to set
	 */
	public void setJ_time(String j_time) {
		this.j_time = j_time;
	}
	/**
	 * @return the j_digest
	 */
	public String getJ_digest() {
		return j_digest;
	}
	/**
	 * @param j_digest the j_digest to set
	 */
	public void setJ_digest(String j_digest) {
		this.j_digest = j_digest;
	}
	/**
	 * @return the j_content
	 */
	public String getJ_content() {
		return j_content;
	}
	/**
	 * @param j_content the j_content to set
	 */
	public void setJ_content(String j_content) {
		this.j_content = j_content;
	}
	/**
	 * @return the j_url
	 */
	public String getJ_url() {
		return j_url;
	}
	/**
	 * @param j_url the j_url to set
	 */
	public void setJ_url(String j_url) {
		this.j_url = j_url;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Journalism [j_id=" + j_id + ", j_title=" + j_title + ", j_time=" + j_time + ", j_digest=" + j_digest
				+ ", j_content=" + j_content + ", j_url=" + j_url + "]";
	}
}
