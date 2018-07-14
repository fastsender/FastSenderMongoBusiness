package br.com.fastsender.mongo.bo.impl;

import br.com.fastsender.mongo.bo.interf.XmlBoInterf;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * @author lucas
 */
public class XmlBoImpl implements XmlBoInterf {

    private static Logger LOG4J = Logger.getLogger(XmlBoImpl.class);
    
    List<DBObject> xmlNfseList;
    
    @Override
    public List<DBObject> recuperaXmls(DB dbMongo){
        try{
            LOG4J.info("INICIANDO CONSULTA XML NFSE PENDENTES");
            xmlNfseList = new ArrayList<>();
            DBCollection collec = dbMongo.getCollection("dados");
            DBCursor cursor = collec.find();
            cursor.forEachRemaining(c -> this.xmlNfseList.add(c));
            LOG4J.info("FINALIZADA CONSULTA XML NFSE PENDENTES");
            return xmlNfseList;
        }catch(Exception e){
            LOG4J.info("ERRO AO CONSULTAR XML NFSE PENDENTES ->"+e.getMessage());
            return null;
        }
    }

    @Override
    public void inserirXml(DB dbMongo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
