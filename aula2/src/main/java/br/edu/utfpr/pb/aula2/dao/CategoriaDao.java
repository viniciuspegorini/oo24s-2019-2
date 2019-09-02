package br.edu.utfpr.pb.aula2.dao;

import br.edu.utfpr.pb.aula2.model.Categoria;
import br.edu.utfpr.pb.aula2.util.EntityManagerUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

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
}



