/**
 * 
 */
package sina.entity;

/**   
* @author         fangrongfu  
* @version        V1.0     
* @Date           2017年8月6日 下午7:31:13   
*/
public class Company {
	private int c_id;//  公司的id
	private String c_name;//  公司的名称
	private String c_code;//  公司的股票代码
	private String c_fullname;//  公司的全称
	private String c_ceo;//  公司的董事长
	private String c_tell;//  公司的电话
	/**
	 * @return the c_id
	 */
	public int getC_id() {
		return c_id;
	}
	/**
	 * @param c_id the c_id to set
	 */
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	/**
	 * @return the c_name
	 */
	public String getC_name() {
		return c_name;
	}
	/**
	 * @param c_name the c_name to set
	 */
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	/**
	 * @return the c_code
	 */
	public String getC_code() {
		return c_code;
	}
	/**
	 * @param c_code the c_code to set
	 */
	public void setC_code(String c_code) {
		this.c_code = c_code;
	}
	/**
	 * @return the c_fullname
	 */
	public String getC_fullname() {
		return c_fullname;
	}
	/**
	 * @param c_fullname the c_fullname to set
	 */
	public void setC_fullname(String c_fullname) {
		this.c_fullname = c_fullname;
	}
	/**
	 * @return the c_ceo
	 */
	public String getC_ceo() {
		return c_ceo;
	}
	/**
	 * @param c_ceo the c_ceo to set
	 */
	public void setC_ceo(String c_ceo) {
		this.c_ceo = c_ceo;
	}
	/**
	 * @return the c_tell
	 */
	public String getC_tell() {
		return c_tell;
	}
	/**
	 * @param c_tell the c_tell to set
	 */
	public void setC_tell(String c_tell) {
		this.c_tell = c_tell;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [c_id=" + c_id + ", c_name=" + c_name + ", c_code=" + c_code + ", c_fullname=" + c_fullname
				+ ", c_ceo=" + c_ceo + ", c_tell=" + c_tell + "]";
	}
}
