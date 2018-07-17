package br.com.fastsender.mongo.bo.interf;

import com.mongodb.DB;
import com.mongodb.DBObject;
import java.util.List;

/**
 * @author lucas
 */
public interface XmlBoInterf {
    
    public List<DBObject> recuperaXmls(DB dbMongo);
    
    public void inserirXml(DB dbMongo, String xml);
   
}
