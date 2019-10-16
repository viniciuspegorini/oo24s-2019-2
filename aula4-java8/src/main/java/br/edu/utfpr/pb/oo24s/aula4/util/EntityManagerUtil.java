package br.edu.utfpr.pb.oo24s.aula4.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
    private static EntityManagerFactory emf;
    
    public static EntityManager getEntityManager(){
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("aula2-PU");
        }
        return emf.createEntityManager();
    }
}
