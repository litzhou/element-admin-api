package com.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class TestMain {

	/**
	 * 生成密码测试
	 */
	@Test
	public void genpwd() {
		// MD5,"原密码","盐",加密次数
		String pwd = new SimpleHash("MD5", "123", "test", 1024).toString();
		System.out.println(pwd);

	}

}
