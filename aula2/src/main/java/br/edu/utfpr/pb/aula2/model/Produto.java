package br.edu.utfpr.pb.aula2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "O campo 'nome' é obrigatório.")
    @Column(name = "nome", length = 254, nullable = false)
    private String nome;
    
    @NotNull(message = "O campo 'valor' é obrigatório.")
    @Min(value = 1, message = "O 'valor' mínimo é 1.00")
    @Max(value = 9999, message = "O 'valor' máximo é 9999.00")
    @Column(name = "valor",nullable = false)
    private Double valor;
    
    @NotEmpty(message = "O campo 'descricao' é obrigatório.")
    @Column(name = "descricao", length = 1024, nullable = false)
    private String descricao;
    
    @NotNull(message = "O campo 'categoria' é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;
    
    public Produto(){
    }

    public Produto(Long id, String nome, Double valor, String descricao, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", valor=" + valor + ", descricao=" + descricao + ", categoria=" + categoria + '}';
    }
}
