package br.edu.utfpr.pb.aula2.dao;

import br.edu.utfpr.pb.aula2.util.EntityManagerUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDao <T, ID extends Serializable> {

    protected EntityManager em;
    
    private Class<T> persistedClass;
    
    protected GenericDao() { }
    
    protected GenericDao(Class<T> persistedClass) {
        this.persistedClass = persistedClass;
        this.em = EntityManagerUtil.getEntityManager();
    }
    
    public void insert(T entity) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(entity);
        em.flush();
        t.commit();
    }
    
    public void update(T entity) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(entity);
        em.flush();
        t.commit();
    }
    
    public T getById(ID id) {
        return em.find(persistedClass, id);
    }
    
    public List<T> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistedClass);
        query.from(persistedClass);
        return em.createQuery(query).getResultList();
    }
    
    public void delete(ID id) {
        T entity = getById(id);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(entity);
        em.remove(entity);
        em.flush();
        t.commit();
    }
}
