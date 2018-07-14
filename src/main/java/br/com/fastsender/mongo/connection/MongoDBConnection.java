package br.com.fastsender.mongo.connection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * @author lucas
 */
public class MongoDBConnection {

    private static Logger LOG4J = Logger.getLogger(MongoDBConnection.class);
    private static final String HOST = "localhost";
    private static final int PORT = 27017;
    private static final String DB_NAME = "fastsender";
    private Mongo mongo = null;
    private DB db = null;

    private final boolean STARTED = false;

    public DB getDB() {
        if (mongo == null) {
            mongo = new Mongo(HOST, PORT);
            db = mongo.getDB(DB_NAME);
            if (db != null) {
                LOG4J.info("MONGO DB STATUS - [CONNECTED]");
            }else{
                LOG4J.error("MONGO DB STATUS - [DISCONECTED] | FALHA AO INICIAR MONGO DB");
            }
        }
        return db;
    }

    public void insert() {
        BasicDBObject doc = new BasicDBObject("username", "higormed").
                append("nome", "Higor Medeiros").
                append("cidade", "Porto Alegre");

        DBCollection collec = db.getCollection("dados");

        collec.insert(doc);

        //lista as coleções
        DBCursor cursor = collec.find();
        int i = 1;
        while (cursor.hasNext()) {
            System.out.println("Documento Inserido: " + i);
            System.out.println(cursor.next());
            i++;
        }

        System.out.println("Banco de dados armazenados:");
        List<String> dbs = mongo.getDatabaseNames();
        for (String dbStr : dbs) {
            System.out.println(dbStr);
        }

        System.out.println("fim execucao");

    }
}
