package br.edu.utfpr.pb.oo24s.aula4.dao;

import br.edu.utfpr.pb.oo24s.aula4.model.Pessoa;

public class PessoaDao  extends GenericDao<Pessoa, Long> {

    public PessoaDao() {
        super(Pessoa.class);
    }
    
}