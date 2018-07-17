package br.com.fastsender.mongo.bo.impl;

import br.com.fastsender.mongo.bo.interf.XmlBoInterf;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * @author lucas
 */
public class XmlBoImpl implements XmlBoInterf {

    private static Logger LOG4J = Logger.getLogger(XmlBoImpl.class);

    private List<DBObject> xmlNfseList;
    private HashMap<String, Object> xmlNfseHash;
    private HashMap<String, Object> dataHoraInclusao;
    private HashMap<String, Object> situacao;

    private Calendar c = new GregorianCalendar();
    private BasicDBObject doc;

    private List<String> xmlList = null;
    private int i = 0;
    private int count;
    @Override
    public List<DBObject> recuperaXmls(DB dbMongo) {
        try {
            LOG4J.info("INICIANDO CONSULTA XML NFSE PENDENTES");
            xmlNfseList = new ArrayList<>();
            DBCollection collec = dbMongo.getCollection("xmlNfse");
            DBCursor cursor = collec.find();
            cursor.forEachRemaining(c -> 
                    testeCount(c)
            );
            LOG4J.info("FINALIZADA CONSULTA XML NFSE PENDENTES");
            return xmlNfseList;
        } catch (Exception e) {
            LOG4J.info("ERRO AO CONSULTAR XML NFSE PENDENTES ->" + e.getMessage());
            return null;
        }
    }
    public void testeCount(DBObject c){
        i++;
        System.out.println("XML["+i+"] LIDO ==>"+ c.toString());
    }
    
    @Override
    public void inserirXml(DB dbMongo, String xml) {
        LOG4J.info("GRAVANDO XMLS RECEBIDOS VIA WEB-SERVICE - [QUANTIDADE XML]: ");
        doc = new BasicDBObject();
        doc.append("xml_nfse", xml).
            append("data_hora_inclusao", c.getTime())
            .append("situacao", 0);
        
        DBCollection collec = dbMongo.getCollection("xmlNfse");
        collec.insert(doc);
        count++;
        LOG4J.info("XML NUMERO ["+count+"] INSERIDO COM SUCESSO");
        System.out.println("XML NUMERO ["+count+"] INSERIDO COM SUCESSO");
    }

    public void retornaNfseHash(List<String> xmlList) {
        HashMap<String, Object> nfseHash;
        nfseHash = new HashMap<>();
        setXmlNfseHash(null);
        xmlList.forEach(xml -> {
            nfseHash.put("xml_nfse", xml);
        });

        nfseHash.forEach((t, u) -> {
            doc.append(t, u);
        });
    }

    public void retornaDataInclusao(List<String> xmlList) {
        HashMap<String, Object> nfseDataInclusao;
        nfseDataInclusao = new HashMap<>();
        xmlList.forEach(xml -> {
            nfseDataInclusao.put("data_hora_inclusao", xml);

        });

        nfseDataInclusao.forEach((t, u) -> {
            doc.append(t, u);
        });
    }

    public void retornaSituacao(List<String> xmlList) {
        HashMap<String, Object> nfseSituacao;
        nfseSituacao = new HashMap<>();
        xmlList.forEach(xml -> {
            nfseSituacao.put("situacao", "0");
        });

        nfseSituacao.forEach((t, u) -> {
            doc.append(t, u);
        });
    }

    public HashMap<String, Object> getXmlNfseHash() {
        return xmlNfseHash != null ? new HashMap<>() : xmlNfseHash;
    }

    public void setXmlNfseHash(HashMap<String, Object> xmlNfseHash) {
        this.xmlNfseHash = xmlNfseHash;
    }

    public HashMap<String, Object> getDataHoraInclusao() {
        return dataHoraInclusao != null ? new HashMap<>() : dataHoraInclusao;
    }

    public void setDataHoraInclusao(HashMap<String, Object> dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

    public HashMap<String, Object> getSituacao() {
        return situacao != null ? new HashMap<>() : situacao;
    }

    public void setSituacao(HashMap<String, Object> situacao) {
        this.situacao = situacao;
    }

}
