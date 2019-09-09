package br.edu.utfpr.pb.aula2.dao;

import br.edu.utfpr.pb.aula2.model.Usuario;
import br.edu.utfpr.pb.aula2.util.EntityManagerUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class UsuarioDao {

    @PersistenceContext(unitName = "aula2-PU")
    private EntityManager em;

    public UsuarioDao() {
        this.em = EntityManagerUtil.getEntityManager();
    }

    public void insert(Usuario usuario) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(usuario);
        em.flush();
        t.commit();
    }

    public void update(Usuario usuario) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(usuario);
        em.flush();
        t.commit();
    }

    public Usuario getById(Integer id) {
        return em.find(Usuario.class, id);
    }
    
    public List<Usuario> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
        
        query.from(Usuario.class);
        
        return em.createQuery(query).getResultList();
    }
       
    public void delete(Integer id) {
        Usuario c = getById(id);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(c);
        em.remove(c);
        em.flush();
        t.commit();
    }
    
}



