package br.edu.utfpr.pb.aula2.dao;

import br.edu.utfpr.pb.aula2.model.Cliente;

public class ClienteDao  extends GenericDao<Cliente, Long> {

    public ClienteDao() {
        super(Cliente.class);
    }
}