package br.edu.utfpr.pb.oo24s.aula4.model;

import br.edu.utfpr.pb.oo24s.aula4.converter.BooleanConverter;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 100, nullable = false)
    private String nome;
    
    @Column(length = 100, nullable = false)
    private String email;
    
    @Column(length = 512, nullable = false)
    private String senha;
    
    @Convert(converter = BooleanConverter.class)
    @Column(nullable = false, columnDefinition = "char(1) default 'V'")
    private Boolean ativo;
    
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    
    //mapeamento das permissões
    @ManyToMany
    @JoinTable(name = "usuario_permissao", 
            joinColumns = {
                @JoinColumn( name = "usuario_id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                @JoinColumn( name = "permissao_id", nullable = false, updatable = false)
            })
    private List<Permissao> permissoes;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", ativo=" + ativo + ", dataNascimento=" + dataNascimento + '}';
    }

}
