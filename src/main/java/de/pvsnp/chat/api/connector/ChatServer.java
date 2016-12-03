/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pvsnp.chat.api.connector;

import de.pvsnp.chat.api.handler.EchoServerHandler;
import de.pvsnp.chat.api.main.ChatAPI;
import de.pvsnp.chat.mongodb.Massage;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author christian
 */
public class ChatServer {
    
    private @Getter  @Setter int port;
    public @Getter @Setter boolean keepalive; 
    
    private @Getter ArrayList<Channel> c;
    
    public ChatServer(int port) {
        this.port = port;
        this.keepalive = true;
        c = new ArrayList<>();
    }
    
    public void connect(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // (1)
        
        try {
            ServerBootstrap bootstrap = new ServerBootstrap(); // (2)
            bootstrap.group(bossGroup, workerGroup); // (3)
            bootstrap.channel(NioServerSocketChannel.class);// (4)
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() { // 5
			@Override
			protected void initChannel(SocketChannel channel) throws Exception {
				System.out.println("Ein Computer hat sich verbunden. IP: " + channel.remoteAddress().getHostName()); // (6)
                                channel.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")), // (2)
							new LineBasedFrameDecoder(1024), // (3)
							new StringDecoder(Charset.forName("UTF-8")), // (2)
							new EchoServerHandler());
                                c.add(channel);
                                sendMassage(channel);
			}
            });// (7)
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, keepalive); // (8)
            ChannelFuture future = bootstrap.bind(port).sync(); // (9)
            System.out.println("Server gestartet!");
            future.channel().closeFuture().sync(); // (10)
            } catch (Exception ex) {
		ex.printStackTrace();
            } finally {
		bossGroup.shutdownGracefully(); // (11)
                workerGroup.shutdownGracefully(); // (11)
            }
	}
    public void sendMassage(Channel c){
        Massage m = ChatAPI.getInstace().getMassage("msg");
        m.connect();
        ArrayList<String> massages = m.getMassages();
        int size = massages.size();
        if(size == 0){
            return;
        }
        size= size - 51;
        if(size < 0)
            size =0;
        for(int i = size;i< massages.size(); i++){
            c.writeAndFlush(massages.get(i));
        }
    }
    }

    
    
    

