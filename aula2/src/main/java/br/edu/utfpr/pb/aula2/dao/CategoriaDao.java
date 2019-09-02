package br.edu.utfpr.pb.aula2.dao;

import br.edu.utfpr.pb.aula2.model.Categoria;
import br.edu.utfpr.pb.aula2.util.EntityManagerUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class CategoriaDao {

    @PersistenceContext(unitName = "aula2-PU")
    private EntityManager em;

    public CategoriaDao() {
        this.em = EntityManagerUtil.getEntityManager();
    }

    public void insert(Categoria categoria) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(categoria);
        em.flush();
        t.commit();
    }

    public void update(Categoria categoria) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(categoria);
        em.flush();
        t.commit();
    }

    public Categoria getById(Integer id) {
        return em.find(Categoria.class, id);
    }
    
    public List<Categoria> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);
        
        query.from(Categoria.class);
        
        return em.createQuery(query).getResultList();
    }
       
    public void delete(Integer id) {
        Categoria c = getById(id);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(c);
        em.remove(c);
        em.flush();
        t.commit();
    }
    
    
    // Select * from Categoria Where descricao like '%cat%'
    public List<Categoria> findByDescricaoLike(String descricao) {
        Query query = em.createQuery("Select c From Categoria c "
                                   + "Where c.descricao LIKE :descricao");
        query.setParameter("descricao", "%" + descricao + "%");
        
        return query.getResultList();
    }
}



