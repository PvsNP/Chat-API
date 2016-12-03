/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pvsnp.chat.api.main;

import de.pvsnp.chat.api.connector.ChatClient;
import de.pvsnp.chat.api.connector.ChatServer;
import de.pvsnp.chat.mongodb.Massage;
import de.pvsnp.chat.mongodb.MongoManager;
import lombok.Getter;

/**
 *
 * @author christian
 */
public class ChatAPI {
    
    private static ChatAPI instance;
    private MongoManager manager;
    
    private @Getter ChatClient cc;
    private @Getter ChatServer cs;
    
    private int port = 8111;
    private String ip = "127.0.0.1";
    
    public static void main(String[] args){
        instance = new ChatAPI();
        instance.load();
    }
    
    public static ChatAPI getInstace(){
        return instance;
    }
    private void load(){
       registerMongoDB();
       cc = new ChatClient(port, ip);
       cs = new ChatServer(port);
        
    }
    
    public Massage getMassage(String key){
        return  Massage.getMassage(this, key);
    }
    
     public void registerMongoDB(){      
        manager = new MongoManager("127.0.0.1", 27017, "Chatapi","Letsplaybar","mama40", "admin");
        manager.connect();
    }
     
     public MongoManager getManager(){
         return manager;
     }
}
