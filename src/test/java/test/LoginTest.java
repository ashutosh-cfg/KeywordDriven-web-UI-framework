package test;

import org.testng.annotations.Test;

import keywordEngine.KeywordEngine;


public class LoginTest {
	public KeywordEngine keywordeng;
	@Test
	public void loginTest() {
		keywordeng = new KeywordEngine();
		keywordeng.startExecution("login");
	}
}
