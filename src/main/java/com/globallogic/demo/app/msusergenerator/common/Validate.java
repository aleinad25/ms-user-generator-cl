package com.globallogic.demo.app.msusergenerator.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	
	public static void passwordValidate(String password) throws PasswordException {
		String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])";
		Pattern pattern = Pattern.compile(passwordPattern);
		Matcher matcher = pattern.matcher(password);
		if (!matcher.find()) {
			throw new PasswordException();
		}
	}
	
	public static void emailValidate(String email) throws PasswordException {
		Pattern pat =
				Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mat = pat.matcher(email);
		if (!mat.find()) {
			throw new EmailFormatException();
		}
	}
}
