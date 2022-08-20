package com.cs.web;

import java.io.IOException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class EchoWebSocket {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen on server side: " + session.getId());    
        
        try {
			session.getBasicRemote().sendText("Server Message: onOpen on server was called.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose on server side: " +  session.getId());
    }
    
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMessage on server side, SessionID=" + session.getId() + ". Message=" + message);
        
        try {
            session.getBasicRemote().sendText("Server Message in UpperCase: " + message.toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}
