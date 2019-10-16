package br.edu.utfpr.pb.oo24s.aula4.dao;

import br.edu.utfpr.pb.oo24s.aula4.model.Usuario;
import java.util.List;
import javax.persistence.Query;

public class UsuarioDao extends GenericDao<Usuario, Long> {

    public UsuarioDao() {
        super(Usuario.class);
    }
    
    public List<Usuario> findByPermissaoNome(String nome) {
        Query q = em.createQuery("Select u from Usuario u "
                + "join fetch u.permissoes p where p.nome = :nome");
        q.setParameter("nome", nome);
        return q.getResultList();
    }
}