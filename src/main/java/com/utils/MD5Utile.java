package com.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Utile {
	// 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public MD5Utile() {
    }
    // 返回为16进制
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回为10进制
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
        	//在这里更换为对应的返回形势
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String GetMD5Code(String password) {
        String resultString = null;
        try {
            resultString = new String(password);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
        }
        return resultString;
    }
    
    //测试类
    public static void main(String[] args) {
    	        System.out.println(MD5Utile.GetMD5Code("1234"));
    }
    
}
