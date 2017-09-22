package sina.util;

import java.util.List;

/** 
* @author:  fangrongfu; 
* @version: 1.0;
* @date：        2017年7月26日 上午10:40:53;
*/
public class ListFor {
	public String forList(List list){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < list.size(); i++){
			String s = (String) list.get(i);
			sb.append(s);
		}
		if(sb == null){
			return null;
		}
		return sb.toString();
	}
}
