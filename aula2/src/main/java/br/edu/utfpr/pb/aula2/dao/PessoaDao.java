package br.edu.utfpr.pb.aula2.dao;

import br.edu.utfpr.pb.aula2.model.Pessoa;

public class PessoaDao  extends GenericDao<Pessoa, Long> {

    public PessoaDao() {
        super(Pessoa.class);
    }
    
}