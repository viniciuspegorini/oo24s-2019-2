package br.edu.utfpr.pb.aula2.dao;

import br.edu.utfpr.pb.aula2.util.EntityManagerUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

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
    
    public boolean isValid(T entity) {
        final Validator validator = Validation.buildDefaultValidatorFactory()
                                              .getValidator();
        return validator.validate(entity).isEmpty();
    }
    
    public String getErrors(T entity) {
        final Validator validator = Validation.buildDefaultValidatorFactory()
                                              .getValidator();
        final Set<ConstraintViolation<T>> violations = validator.validate(entity);
        
        String errors = "";
        if (!violations.isEmpty()) {
            errors = violations.stream().map(
                    violation -> violation.getMessage() + "\n")
                    .reduce(errors, String::concat);
        }
        return errors;
    }
}
