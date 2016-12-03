/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pvsnp.chat.api.handler;

import de.pvsnp.chat.api.main.ChatAPI;
import de.pvsnp.chat.mongodb.Massage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 *
 * @author christian
 */
public class EchoServerHandler extends ChannelHandlerAdapter{
    
    
	public void channelActive(ChannelHandlerContext ctx) throws Exception { // (2)
		System.out.println("Verbindung zum Client bereit!"); // (3)
	}
        
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof String) { // (7)
			String message = (String) msg; // (7b)
			System.out.println(message); // (8)
                        Massage m = ChatAPI.getInstace().getMassage("msg");
                        m.connect();
                        m.addMassage(message);
                        m.save();
			for(Channel c: ChatAPI.getInstace().getCs().getC())
                            if(!c.equals(ctx.channel()))
                                c.writeAndFlush(message); // (9)
		}
	}
}
