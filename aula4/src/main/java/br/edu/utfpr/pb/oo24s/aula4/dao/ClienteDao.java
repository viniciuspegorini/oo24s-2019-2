package br.edu.utfpr.pb.oo24s.aula4.dao;

import br.edu.utfpr.pb.oo24s.aula4.model.Cliente;

public class ClienteDao  extends GenericDao<Cliente, Long> {

    public ClienteDao() {
        super(Cliente.class);
    }
}