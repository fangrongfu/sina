/**
 * 
 */
package sina.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**   
* @author         fangrongfu  
* @version        V1.0     
* @Date           2017年8月3日 上午10:16:02   
*/
public class Regular {
	//用正则表达式处理字符串
	public String regular(String url){
		Pattern pattern = Pattern.compile("(http://file\\.finance\\.sina\\.com\\.cn/[\\d+\\.]+[\\w+].{58})");
		if(url == null){
			return null;
		}
		Matcher matcher = pattern.matcher(url);
		if(matcher.find()){
			if(matcher.group(1) == null){
				return null;
			}
			return matcher.group(1);
		}
		return null;
	}
}
