package com.ztel.framework.util;

/**
 * 系统中用到的公共方法类
 * @author lcf
 *
 */
public class PubUtils {

	/**
	 * 用户串抽取
	 * @param userStr 选择用户后，用户串 格式为姓名[工号];姓名[工号];姓名[工号]...  如：谢静[0502];殷兴强[0061];...
	 * @return 只返回工号串：0502;0061...
	 */
	public static String userstrExtract(String userStr){
		String rlt = "";
		String[] userstrArr = userStr.split(";");
		if(userstrArr!=null&&userstrArr.length>0){
			for(int i=0;i<userstrArr.length;i++){
				String  usertmp = userstrArr[i].substring(userstrArr[i].indexOf("[")+1, userstrArr[i].indexOf("]"));
				 rlt = rlt + usertmp+";";
			}
		}
		return rlt;
	}
	
	public static void main(String[] args) throws Exception {
		String user = "谢静[0502];殷兴强[0061];";
		String rlt = userstrExtract(user);
		System.out.println(rlt);
	}
	
	
}
