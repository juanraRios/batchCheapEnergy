package com.juanrarios.springbatch.rest.notification;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.juanrarios.springbatch.rest.dao.FirebaseUtil;

public class Notification {

	private static final String FIREBASE_URL = "https://cheapenergytabs.firebaseio.com/";
	private static final String NOTIFICATIONS = "/notifications/";
	private static final String USER = "john-doe";

	public void pushNotification() {
		Map<String, Object> data = new LinkedHashMap<>();

		data.put("firstName", "John");
		data.put("lastName", "Doe");
		data.put("description", "has sent you friend request!");
		data.put("read", "false");
		data.put("imgSrc", "../../img/john.jpg");
		data.put("date", new Date());
		String url = FIREBASE_URL + NOTIFICATIONS + USER;
		FirebaseUtil.writeToList(url, data);
	}
}
