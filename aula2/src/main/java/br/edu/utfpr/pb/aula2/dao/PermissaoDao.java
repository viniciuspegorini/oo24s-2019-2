package br.edu.utfpr.pb.aula2.dao;

import br.edu.utfpr.pb.aula2.model.Permissao;
import br.edu.utfpr.pb.aula2.util.EntityManagerUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class PermissaoDao {

    @PersistenceContext(unitName = "aula2-PU")
    private EntityManager em;

    public PermissaoDao() {
        this.em = EntityManagerUtil.getEntityManager();
    }

    public void insert(Permissao permissao) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(permissao);
        em.flush();
        t.commit();
    }

    public void update(Permissao permissao) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(permissao);
        em.flush();
        t.commit();
    }

    public Permissao getById(Integer id) {
        return em.find(Permissao.class, id);
    }
    
    public List<Permissao> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Permissao> query = builder.createQuery(Permissao.class);
        
        query.from(Permissao.class);
        
        return em.createQuery(query).getResultList();
    }
       
    public void delete(Integer id) {
        Permissao c = getById(id);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(c);
        em.remove(c);
        em.flush();
        t.commit();
    }
    
}



