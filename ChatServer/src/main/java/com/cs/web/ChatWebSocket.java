package com.cs.web;

import java.io.IOException;
import java.util.*;

import com.cs.cache.Cache;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatWebSocket {
	
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Client " + session.getId() + " has come online");
    }
    
    @OnClose
    public void onClose(Session session) {
        System.out.println("Client " +  session.getId() + " left");
       
        String sessionID = session.getId();
        String userName = Cache.map2.get(sessionID);
        
        Cache.map1.remove(userName);
        Cache.map2.remove(sessionID);
    }
    
    @OnMessage
    public void onMessage(String message, Session session) {
    	
    	String[] arrOfStr = message.split("~");
   
    	if (arrOfStr.length == 1) {
    		System.out.println("User is " + message);
    		System.out.println("Session is " + session);
            Cache.map1.put(message, session);
            Cache.map2.put(session.getId(), message);
            
            System.out.println("Hash map is:");
            for (Map.Entry<String, Session> ele: Cache.map1.entrySet())
    		    System.out.println("Key : " + ele.getKey() + "\t" + "Value : " + ele.getValue());
    	}
    	else if (arrOfStr.length == 4) {

    		//String messageType = arrOfStr[0];
    		String mesg = arrOfStr[1];
    		String sender = arrOfStr[2];
    		String reciever = arrOfStr[3];
    		
    		Session sessionObj = Cache.map1.get(reciever);
    		
    		try {
    			sessionObj.getBasicRemote().sendText(mesg + "~" + sender);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
    
    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}
