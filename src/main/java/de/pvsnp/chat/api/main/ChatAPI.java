/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pvsnp.chat.api.main;

import de.pvsnp.chat.mongodb.MongoManager;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
       
    }
    
     public void registerMongoDB(){
        File file = new File("Configs/","MongoDB.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.options().copyDefaults(true);
        cfg.addDefault("IP", "localhost");
        cfg.addDefault("Port", 27017);
        cfg.addDefault("Datenbank", "Minecraft");
        cfg.addDefault("User", "User");
        cfg.addDefault("Password", "Password");
        cfg.addDefault("AuthDatabase", "admin");
        
            try {
                cfg.save(file);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         manager = new MongoManager(cfg.getString("IP"), cfg.getInt("Port"), cfg.getString("Datenbank"),cfg.getString("User"),cfg.getString("Password"), cfg.getString("AuthDatabase"));
		manager.connect();
    }
     
     public MongoManager getManager(){
         return manager;
     }
}
