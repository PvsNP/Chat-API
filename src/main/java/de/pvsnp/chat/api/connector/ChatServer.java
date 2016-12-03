/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pvsnp.chat.api.connector;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author christian
 */
public class ChatServer {
    
    private @Getter  @Setter int port;
    private @Getter @Setter String ip;
    public @Getter @Setter boolean keepalive; 
    
    public ChatServer(int port, String ip) {
        this.port = port;
        this.ip = ip;
        this.keepalive = true;
    }
    
    public void connect(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // (1)
    }
    
    
    
}
