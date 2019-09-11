package br.edu.utfpr.pb.aula2.dao;

import br.edu.utfpr.pb.aula2.model.Usuario;

public class UsuarioDao extends GenericDao<Usuario, Long> {

    public UsuarioDao() {
        super(Usuario.class);
    }
}