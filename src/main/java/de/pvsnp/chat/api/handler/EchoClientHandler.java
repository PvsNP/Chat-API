/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pvsnp.chat.api.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 *
 * @author christian
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

        
	public void channelActive(ChannelHandlerContext ctx) throws Exception { // (2)
		System.out.println("Verbindung zum Server aktiv!"); // (3)
	}
   
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof String) { // (7)
			String message = (String) msg; // (7b)
			                 giveMassageAsJson(message);
		}
	}
        
        private void giveMassageAsJson(String msg){
            String[] data = msg.split(": ", 0);
            
        }
        
        
}
