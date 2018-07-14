package br.com.fastsender.mongo.facade;

import br.com.fastsender.mongo.bo.impl.XmlBoImpl;
import com.mongodb.DB;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lucas
 */
public class XmlFacade {

    private XmlBoImpl xmlBo;
    private List<DBObject> xmlNfseList;
    
    public List<DBObject> recuperaXmls(DB db) {
        getXmlNfseList().addAll(getXmlBo().recuperaXmls(db));
        return getXmlNfseList().isEmpty() ? getXmlNfseList() : null;
    }

    //Getters and setters
    public XmlBoImpl getXmlBo() {
        if (xmlBo == null){
            this.xmlBo = new XmlBoImpl();
        }
        return xmlBo;
    }
    
    public void setXmlBo(XmlBoImpl xmlBo) {
        this.xmlBo = xmlBo;
    }

    public List<DBObject> getXmlNfseList() {
        return xmlNfseList == null ? new ArrayList<>() : null;
    }

    public void setXmlNfseList(List<DBObject> xmlNfseList) {
        this.xmlNfseList = xmlNfseList;
    }

}
