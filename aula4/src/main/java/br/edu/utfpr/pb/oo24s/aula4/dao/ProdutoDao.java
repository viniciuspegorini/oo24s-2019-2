package br.edu.utfpr.pb.oo24s.aula4.dao;

import br.edu.utfpr.pb.oo24s.aula4.model.Produto;
import java.util.List;
import javax.persistence.Query;

public class ProdutoDao extends GenericDao<Produto, Long> {

    public ProdutoDao() {
        super(Produto.class);
    }
    
     public List<Produto> findByCategoriaDescricaoLike(String descricao) {
        Query query = em.createQuery("Select p From Produto p "
                              + "Where p.categoria.descricao LIKE :descricao");
        query.setParameter("descricao", "%" + descricao + "%");
        
        return query.getResultList();
    }
}
/*
    private EntityManager em;
    
    public ProdutoDao() {
        this.em = EntityManagerUtil.getEntityManager();
    }
    
    public void insert(Produto produto) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(produto);
        em.flush();
        t.commit();
    }
    
    public void update(Produto produto) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(produto);
        em.flush();
        t.commit();
    }
    
    public Produto getById(Long id) {
        return em.find(Produto.class, id);
    }
    
    public List<Produto> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        
        query.from(Produto.class);
        
        return em.createQuery(query).getResultList();
    }
       
    public void delete(Long id) {
        Produto c = getById(id);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(c);
        em.remove(c);
        em.flush();
        t.commit();
    }
    
    public List<Produto> findByCategoriaDescricaoLike(String descricao) {
        Query query = em.createQuery("Select p From Produto p "
                              + "Where p.categoria.descricao LIKE :descricao");
        query.setParameter("descricao", "%" + descricao + "%");
        
        return query.getResultList();
    }
} */