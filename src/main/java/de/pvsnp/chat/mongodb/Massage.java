/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pvsnp.chat.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import de.pvsnp.chat.api.main.ChatAPI;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

/**
 *
 * @author christian
 */
public class Massage {
    
    private @Getter @Setter String key;
    private ChatAPI main;
    
    private @Getter @Setter ArrayList<String> massages;
    
    MongoCollection massage;
    
    private FindIterable<Document>find;
	
    private Document document;

    public Massage(String key, ChatAPI main) {
        this.key = key;
        this.main = main;
        this.massage=main.getManager().getMassages();
        this.find = massage.find(Filters.eq("name", key));
        this.document = find.first();
        
        massages = new ArrayList<>();
    }
    
    public static Massage getMassage(ChatAPI main, String key){
        return new Massage(key, main);
    }
    
    public void connect(){
        if(document != null)
            get();
        document = new Document("name", key)
                .append("msg", new ArrayList<>());
        massage.insertOne(document);
        get();
    }
    
    public void save(){
        Document doc = new Document("name", key)
                .append("msg", massages);
        massage.updateOne(Filters
                        .eq("name", key), new Document("$set", doc));
    }
    
    
    public void addMassage(String msg){
        massages.add(msg);
    }
    
    private void get(){
        setMassages((ArrayList<String>) document.get("msg"));
    }
}
