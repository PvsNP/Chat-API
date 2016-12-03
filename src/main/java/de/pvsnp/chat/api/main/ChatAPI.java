/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pvsnp.chat.api.main;

/**
 *
 * @author christian
 */
public class ChatAPI {
    
    private static ChatAPI instance;
    
    public static void main(String[] args){
        instance = new ChatAPI();
        instance.load();
    }
    
    protected ChatAPI getInstace(){
        return instance;
    }
    private void load(){
       
    }
}
