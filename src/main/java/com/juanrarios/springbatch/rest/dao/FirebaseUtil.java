package com.juanrarios.springbatch.rest.dao;

import java.util.Map;
import java.util.Random;

import com.firebase.client.Firebase;

public class FirebaseUtil {
 public static void writeToList(String url, Map<String, Object> map) {
	 
	 Random randomGenerator = new Random();
	 int num = randomGenerator.nextInt(1000);
     Firebase listRef = new Firebase(url + "/data/");
     map.put("_id", num);
     Firebase push = listRef.child("" + num); // or .child(Long.toString(num));
     push.setValue(map);
 }
}