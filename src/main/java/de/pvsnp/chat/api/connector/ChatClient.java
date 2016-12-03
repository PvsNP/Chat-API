/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pvsnp.chat.api.connector;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author christian
 */
public class ChatClient {
    
    private @Getter @Setter int port;
    private @Getter @Setter String ip;

    public ChatClient(int port, String ip) {
        this.port = port;
        this.ip = ip;
    }
    
    public void connect(){
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // (2)
		try {
			Bootstrap bootstrap = new Bootstrap(); // (3)
			bootstrap.group(workerGroup); // (4)
			bootstrap.channel(NioSocketChannel.class); // (5)
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true); // (6)
			bootstrap.handler(new ChannelInitializer<SocketChannel>() { // (7)
				@Override
				protected void initChannel(SocketChannel channel) throws Exception {
					System.out.println("Verbindung zum Server hergestellt!");
				}
			});
			bootstrap.connect(ip, port).sync().channel().closeFuture().sync(); // (8)
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully(); // (9)
		}

    }
    
}
