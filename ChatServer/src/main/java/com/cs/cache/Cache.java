package com.cs.cache;

import java.util.HashMap;

import jakarta.websocket.Session;

public class Cache {
	
	public static HashMap<String, Session> map1 = new HashMap<>();
	public static HashMap<String, String> map2 = new HashMap<>();
	public static HashMap<Session, String> map3 = new HashMap<>();
}
