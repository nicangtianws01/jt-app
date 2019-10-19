package com.jt.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class Test01 {
	@Test
	public void test01(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String dirPath = "D:/file/image/" + sdf.format(new Date());
		System.out.println(dirPath);
	}
	@Test
	public void a1() {
		String s1 = "abcdefgABCdefgabcabc";
		String s2 = "abc";
		String strTmp = s1.toLowerCase();
		int count = 0;
		while(strTmp.indexOf(s2) > -1) {
			count ++;
			strTmp = strTmp.substring(strTmp.indexOf(s2) + s2.length());
		}
		System.out.println(count);

	}
}
