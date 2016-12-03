/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pvsnp.chat.mongodb;

/**
 *
 * @author christian
 */

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class MongoManager {
	
	private String hostname;
	private int port;
	private String databaseName;
        private String user;   // the name of the database in which the user is defined
        private char[] password;
	private String databaseAuth;
	
	private @Getter MongoClient client;
	private @Getter MongoDatabase database;
	
	public MongoManager(String hostname, int port, String databaseName, String user, String password, String databaseAuth) {
		this.hostname = hostname;
		this.port = port;
		this.databaseName = databaseName;
                this.user = user;
                this.password = password.toCharArray();
                this.databaseAuth = databaseAuth;
		}
	
	public void connect(){
                MongoCredential credential = MongoCredential.createScramSha1Credential(user,
                                                                       databaseAuth,
                                                                       password);
            List<MongoCredential> list = new ArrayList<MongoCredential>();
            list.add(credential);
		this.client = new MongoClient(new ServerAddress(hostname, port),list);
                
		this.database = client.getDatabase(databaseName);
	}
	
	public void disconect(){
		this.client.close();
	}
	
	
}

