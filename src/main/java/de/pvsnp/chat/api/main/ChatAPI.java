/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pvsnp.chat.api.main;

import de.pvsnp.chat.mongodb.MongoManager;

/**
 *
 * @author christian
 */
public class ChatAPI {
    
    private static ChatAPI instance;
    private MongoManager manager;
    
    public static void main(String[] args){
        instance = new ChatAPI();
        instance.load();
    }
    
    protected ChatAPI getInstace(){
        return instance;
    }
    private void load(){
       registerMongoDB();
        
    }
    
     public void registerMongoDB(){      
        manager = new MongoManager("127.0.0.1", 27017, "Chatapi","Letsplaybar","mama40", "admin");
        manager.connect();
    }
     
     public MongoManager getManager(){
         return manager;
     }
}
