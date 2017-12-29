package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ValidateUtil {

//电话验证
  public static boolean phoneValidate(String thing){
	  String phone="^((13[0-9])|(15[0-3,5-9])|(18[0-9])|(14[7,5])|(17[6-8]))\\d{8}|(170[0,5,9])\\d{7}$";
	if(thing!=null&&!thing.equals("")){
		if(matchers(phone,thing).matches()){
			return true;
		}
		}
	return false;
  }
  
  //邮箱验证
  public static boolean emailValidate(String thing){
	  String email="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	if(thing!=null&&!thing.equals("")){
		if(matchers(email,thing).matches()){
			return true;
		}
		}
	return false;
  }
  
  //code验证
  public static boolean codeValidate(String thing){
	  String code="^\\d{8,10}$";
	if(thing!=null&&!thing.equals("")){
		if(matchers(code,thing).matches()){
			return true;
		}
		}
	return false;
  }
//中文.字母.数字验证
  public static boolean czsValidate(String thing){
	  String czs="^[a-zA-Z0-9\u4E00-\u9FA5]+$";
	if(thing!=null&&!thing.equals("")){
		if(matchers(czs,thing).matches()){
			return true;
		}
		}
	return false;
  }
  
//15位或18位身份证验证 最后一位可以为字母
  public static boolean identityValidate(String thing){
	  String identity="(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
	if(thing!=null&&!thing.equals("")){
		if(matchers(identity,thing).matches()){
			return true;
		}
		}
	return false;
  }
  
  //正则验证
  public static Matcher matchers(String type,String thing){
	  Pattern p = Pattern.compile(type);  
	  Matcher m = p.matcher(thing);
	  return m;
  }
  //空验证
  public static boolean NULL(Object obj){
	  if(obj!=null&&!obj.equals("")){
		  return true;
	  }
	  return false;
  } 
 /**
  * 验证对应的密码是否符合安全,验证码已经切割掉所有的空格
  * @param password
  * @return
  */
  public static boolean passwordValidate(String password) {
	  return password.matches("^[A-Za-z0-9.,!@#$%^&*]{6,16}$");  
}
  

 
  
}
