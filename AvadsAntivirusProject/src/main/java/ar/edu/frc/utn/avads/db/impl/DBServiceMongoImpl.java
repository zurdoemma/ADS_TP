/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.frc.utn.avads.db.impl;

import ar.edu.frc.utn.avads.db.service.DBService;
import ar.edu.frc.utn.avads.main.AvadsMain;
import com.mongodb.BasicDBObject;
 
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Projections.excludeId;
import com.mongodb.util.JSON;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bson.Document;
/**
 *
 * @author ecarballo
 */
public class DBServiceMongoImpl implements DBService{
    
    private static MongoClient mongoClient = null;
    public static DB db = null;
    public static MongoDatabase mdb = null;
    
    @Override
    public void startDB() {

        String DB_SRV_USR = AvadsMain.propC.getProperty("db.mongo.user");
        String DB_SRV_PWD = AvadsMain.propC.getProperty("db.mongo.pwd");
        String DB_URL = AvadsMain.propC.getProperty("db.mongo.url");
        String DB_PORT = AvadsMain.propC.getProperty("db.mongo.puerto");
        String dbName = AvadsMain.propC.getProperty("db.mongo.nombre.db");

        String mongoClientURI = "mongodb://" + DB_SRV_USR + ":" + DB_SRV_PWD + "@" + DB_URL + ":" + DB_PORT + "/" + dbName; 
        MongoClientURI connectionString = new MongoClientURI(mongoClientURI);

        MongoClientOptions.builder().sslEnabled(true).build();            
        mongoClient = new MongoClient(connectionString);
        db = mongoClient.getDB(dbName);
        mdb = mongoClient.getDatabase(dbName);
         
    }
    
    @Override
    public void closeDB(){
        mongoClient.close();
    }
    
    @Override
    public void insertCollection(String nomCollection, String collection){
        
        DBCollection collectionDB = db.getCollection(nomCollection);
        DBObject dbObject = (DBObject)JSON.parse(collection);
        collectionDB.insert(dbObject);
    }
    
    @Override
    public List<String> getAllCollection(String nomCollection){
        
        MongoCollection<Document> collectionDB = mdb.getCollection(nomCollection);   
        MongoCursor<Document> iterator = collectionDB.find().projection(excludeId()).iterator();

        List<String> list = new ArrayList<String>();
        while (iterator.hasNext()) 
        {
            Document doc = iterator.next();
            list.add(doc.toJson());
        }
        
        return list;
    }
    
    @Override
    public Long getMaxIdProceso(String nomCollection, String atribute) {
        
        DBCollection collectionDB = db.getCollection(nomCollection);
        DBObject busMax = new BasicDBObject(atribute, -1);
        DBCursor res = collectionDB.find().sort(busMax).limit(1);
        Iterator<DBObject> iter = res.iterator();
        
        if(iter.hasNext()) return Long.parseLong(String.valueOf(iter.next().get(atribute)));
        else return -1L;
    }

    @Override
    public List<String> findObjectCollection(String nomCollection, String atribute, Object value) {
        
        DBCollection collectionDB = db.getCollection(nomCollection);
        
        DBObject removeIdProjection = new BasicDBObject("_id", 0);
        BasicDBObject whereQuery = new BasicDBObject();
        
        if(value.getClass().equals(String.class)) whereQuery.put(atribute, String.valueOf(value));
        if(value.getClass().equals(Long.class)) whereQuery.put(atribute, Long.parseLong(String.valueOf(value)));
        if(value.getClass().equals(Integer.class)) whereQuery.put(atribute, Integer.parseInt(String.valueOf(value)));
        if(value.getClass().equals(Double.class)) whereQuery.put(atribute, Double.parseDouble(String.valueOf(value)));
        
        DBCursor cursor = collectionDB.find(whereQuery, removeIdProjection);
        List<String> list = new ArrayList<String>();
        while (cursor.hasNext()) 
        {
            list.add(cursor.next().toString());
        }
        
        return list;
    }
    
    @Override
    public List<String> findObjectCollectionMultipleAtributes(String nomCollection, Map<String,Object> atributeValue) {
        
        DBCollection collectionDB = db.getCollection(nomCollection);
        
        DBObject removeIdProjection = new BasicDBObject("_id", 0);
        BasicDBObject whereQuery = new BasicDBObject();
        
        for (Map.Entry<String, Object> entry : atributeValue.entrySet())
        {
            if(entry.getValue().getClass().equals(String.class)) whereQuery.put(entry.getKey(), String.valueOf(entry.getValue()));
            if(entry.getValue().getClass().equals(Integer.class)) whereQuery.put(entry.getKey(), Integer.parseInt(String.valueOf(entry.getValue())));
            if(entry.getValue().getClass().equals(Long.class)) whereQuery.put(entry.getKey(), Long.parseLong(String.valueOf(entry.getValue())));            
            if(entry.getValue().getClass().equals(Double.class)) whereQuery.put(entry.getKey(), Double.parseDouble(String.valueOf(entry.getValue())));  
        }
        
        DBCursor cursor = collectionDB.find(whereQuery, removeIdProjection);
        List<String> list = new ArrayList<String>();
        while (cursor.hasNext()) 
        {
            list.add(cursor.next().toString());
        }
        
        return list;
    }    
        
    @Override
    public boolean removeObjectCollection(String nomCollection, String atribute, Object value) {
        
        DBCollection collectionDB = db.getCollection(nomCollection);
        
        BasicDBObject documentR = new BasicDBObject();
        if(value.getClass().equals(String.class)) documentR.put(atribute, String.valueOf(value));
        if(value.getClass().equals(Long.class)) documentR.put(atribute, Long.parseLong(String.valueOf(value)));
        if(value.getClass().equals(Integer.class)) documentR.put(atribute, Integer.parseInt(String.valueOf(value)));
        if(value.getClass().equals(Double.class)) documentR.put(atribute, Double.parseDouble(String.valueOf(value)));
        
        if(collectionDB.remove(documentR).toString().contains("n=0")) return false;
        else return true;
    }  
    
    @Override
    public Long getCountObjectsCollection(String nomCollection) {
        
        DBCollection collectionDB = db.getCollection(nomCollection);
        return collectionDB.count();
    }    
    
    @Override
    public boolean updateObjectCollection(String nomCollection, Map<String, Object> atributeValueSearch, Map<String, Object> atributeValueUpdate) {
        
        DBCollection collectionDB = db.getCollection(nomCollection);
        
        BasicDBObject newDocument = new BasicDBObject();
        
        for (Map.Entry<String, Object> entry : atributeValueUpdate.entrySet())
        {
            if(entry.getValue().getClass().equals(String.class)) newDocument.put(entry.getKey(), String.valueOf(entry.getValue()));
            if(entry.getValue().getClass().equals(Integer.class)) newDocument.put(entry.getKey(), Integer.parseInt(String.valueOf(entry.getValue())));
            if(entry.getValue().getClass().equals(Long.class)) newDocument.put(entry.getKey(), Long.parseLong(String.valueOf(entry.getValue())));            
            if(entry.getValue().getClass().equals(Double.class)) newDocument.put(entry.getKey(), Double.parseDouble(String.valueOf(entry.getValue())));  
        }
        DBObject updateAVAux = new BasicDBObject("$set", newDocument);
        
        BasicDBObject searchQuery = new BasicDBObject();
        for (Map.Entry<String, Object> entry : atributeValueSearch.entrySet())
        {
            if(entry.getValue().getClass().equals(String.class)) searchQuery.append(entry.getKey(), String.valueOf(entry.getValue()));
            if(entry.getValue().getClass().equals(Integer.class)) searchQuery.append(entry.getKey(), Integer.parseInt(String.valueOf(entry.getValue())));
            if(entry.getValue().getClass().equals(Long.class)) searchQuery.append(entry.getKey(), Long.parseLong(String.valueOf(entry.getValue())));            
            if(entry.getValue().getClass().equals(Double.class)) searchQuery.append(entry.getKey(), Double.parseDouble(String.valueOf(entry.getValue())));  
        }        

        if(collectionDB.update(searchQuery, updateAVAux).toString().contains("n=0")) return false;
        else return true;
    }
}
