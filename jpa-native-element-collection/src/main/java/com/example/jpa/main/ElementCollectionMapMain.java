package com.example.jpa.main;

import com.example.jpa.domain.Document;
import com.example.jpa.domain.PropValue;
import com.example.jpa.helper.EMF;

import java.util.HashMap;
import java.util.Map;

public class ElementCollectionMapMain {
    public static void main(String[] args) {
        EMF.init();
        save();
        addUpdateRemoveMap();
        EMF.close();
    }

    private static void save() {
        var em = EMF.createEntityManager();
        var tx = em.getTransaction();
        try {
            tx.begin();
            Map<String, PropValue> props = new HashMap<>();
            props.put("p1", new PropValue("v1", true));
            props.put("p2", new PropValue("v2", true));
            Document doc = new Document("1", "제목", "내용", props);
            em.persist(doc);
            tx.commit();
            // insert into doc (content, title, id) values (?, ?, ?)
            // insert into doc_prop (doc_id, name, value) values (?, ?, ?)
            // insert into doc_prop (doc_id, name, value) values (?, ?, ?)
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void addUpdateRemoveMap() {
        var em = EMF.createEntityManager();
        var tx = em.getTransaction();
        try {
            tx.begin();
            Document doc = em.find(Document.class, "1");
            doc.setProp("p1", new PropValue("v1new", true)); // update
            doc.setProp("p10", new PropValue("v10", false)); // add
            doc.removeProp("p2"); // remove

            tx.commit();
            /*
            delete from doc_prop where doc_id=? and name=?
            update doc_prop set value=? where doc_id=? and name=?
            insert into doc_prop (doc_id, name, value) values (?, ?, ?)
             */
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
