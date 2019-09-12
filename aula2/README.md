# Aula2 - oo24s
## Introdução ao Mapeamento Objeto Relacional em Java

### Introdução
O projeto **aula2** servirá como base para apresentar os conceitos iniciais sobre Mapeamento Objeto Relacional (ORM). O *framework* ORM utilizado no projeto foi o Hibernate adotando o padrão da API de persistência de dados ***Java Persistence API*** (**JPA**).

### Hibernate
O _framework_ Hibernate é um projeto que tem por objetivo fornecer uma solução completa para o problema de gerenciamento de dados persistentes em Java. Atualmente, o Hibernate não é apenas um _framework_ ORM, mas também um conjunto de ferramentas para o gerenciamento de dados que vai além do ORM.

### Projeto exemplo
O projeto foi criado utilizando o **[Maven](https://maven.apache.org/)** para gerenciamento das dependências. As dependências são adicionadas no arquivo ***pom.xml***. No código abaixo é apresentado o arquivo pom.xml utilizado no projeto aula2, nos elementos ***\<dependency>*** estão as dependências do driver jdbc do banco *postgresql* e as dependências do Hibernate. A dependência ***hibernate-entitymanager*** inclui as dependêcias necessárias para o funcionamento do Hibernate.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>br.edu.utfpr.pb</groupId>
    <artifactId>aula2</artifactId>
    <version>1.0</version>
    <packaging>jar</packaging>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-entitymanager</artifactId>
            <version>5.4.4.Final</version>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.2.6</version>
        </dependency>
    </dependencies>
</project>
```
#### O arquivo '*persistence.xml*'
O próximo passo será adicionar o arquivo **persistence.xml** ao projeto. Esse é um arquivo de configuração usado pelo JPA. O conteúdo desse arquivo possui informações como a _url de conexão do banco de dados_, _usuário do banco de dados_, _senha do banco de dados_. O arquivo deve ser salvo em: ***raiz-do-projeto\src\main\resources\META-INF\\*** .
```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.1" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
  <persistence-unit name="aula2-PU" transaction-type="RESOURCE_LOCAL">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <properties>
      <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/oo24s-aula2"/>
      <property name="javax.persistence.jdbc.user" value="postgres"/>
      <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
      <property name="javax.persistence.jdbc.password" value="root"/>
      
      <property name="hibernate.hbm2ddl.auto" value="create-drop" /> <!-- update -->
      <property name="hibernate.show_sql" value="true" />
      <property name="hibernate.format_sql" value="false" />
    </properties>
  </persistence-unit>
</persistence>
```
No arquivo criado a propriedade **name** da tag **\<persistence-unit />** é informado o nome da unidade de persistência que será utilizado dentro da aplicação. O conteúdo da tag **\<provider>** também é importante, pois esse valor irá variar de acordo com a implementação JPA utilizada. Dentro da tag de **propriedades** além dos dados de conexão com o banco de dados, também são realizadas configurações específicas do Hibernate. 
A propriedade *"hibernate.hbm2ddl.auto"* define a estratédia utilizada na criação das tabelas e campos do banco de dados. Por exemplo, com o valor **create-drop** toda vez que a aplicação for iniciada, será criada novamente a estrutura do banco de dados. Trocando o valor por **update** a estrutura do banco será criada caso ainda não exista, ou apenas atualizada com os novos campos e tabelas.

#### Organização do Projeto
Os pacotes de código fonte do projeto são o **model, dao, util e main**. No pacote **model** estão as classes *Categoria* e *Produto*. No pacote **dao** estão as classes responsáveis pela persistência dos objetos de Categoria e Produto. No pacote **util** está a classe com a criação do EntityManagerFactory. E no pacote **main** está a classe para testar as operações de CRUD.

### Mapeamento Objeto Relacional das Classes do pacote Model
No pacote **model** estão as classes Categoria e Produto. Inicialmente será realizado o Mapeamento Objeto Relacional (ORM) da classe Categoria. 

O mapeamento inicia pela *annotation* **_@Entity_**, toda entidade que será persistida necessita dessa anotação. A segunda anotação presente na classe é **_@Table_**, na qual foi informado o nome da tabela que será utilizada na persistência no banco de dados*.

> \*Nesse exemplo foi utilizado a anotação ***@Table***, mas essa anotação é opcional. Caso seja utilizada apenas a anotação *@Entity* o Hibernate entende que o banco de dados irá possuir uma tabela com o mesmo nome que a classe, ou seja, no caso desse exemplo a tabela também se chamaria '*categoria*'.

```java
package br.edu.utfpr.pb.aula2.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity                    
@Table(name = "categoria") 
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

	// métodos construtores()
	// métodos getters()/setters()
```
> @Entity - toda entidade que será persistida deve possuir a anotação.

> @Table - anotação opcional, serve para informar o nome da tabela que está sendo mapeada no banco de dados.

> @Id - anotação que define qual atributo corresponde à chave-primária do banco de dados. Essa anotação é obrigatória em toda entidade que será persistida.

> @GeneratedValue - define a estratégia para geração da chave primária. Nesse exemplo será utilizado geração automática de valores para chave primária (*auto-increment*).

> @Column - a anotação para definição de coluna é opcional. Caso seja utilizada é possível definir o nome da coluna, o tamanho do campo, e outras informações que dependem do tipo de dados do atributo que está sendo mapeado. Caso opte por não utilizar essa anotação, o *framework* JPA irá  considerar o *nome do atributo* como *nome da coluna* no banco de dados, e o tipo de dados do banco será definido de acordo com os padrões do *framework* utilizado (ex.: se será varchar, nvarchar, int ou bigInt, etc.).

Agora será apresentado o mapeamento da classe **Produto**. O mapeamento de toda entidade persistida irá iniciar-se pela anotação **_@Entity_**. Nota-se também, que nesse segundo exemplo, as anotações **_@Table, @Id, @GeneratedValue_** e **_@Column_**, também estão presentes. A difirença nesse exemplo fica por conta do mapeamento do atributo 'categoria'. Como existe um relacionamento entre Produto e Categoria no banco de dados, esse relacionamento deverá ser mapeado pelo *framework* ORM no modelo orientado a objetos. Esse mapeamento ocorre por meio da anotação **_@ManyToOne_** (Muitos para Um).

```java
package br.edu.utfpr.pb.aula2.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", length = 254, nullable = false)
    private String nome;
    
    @Column(name = "valor",nullable = false)
    private Double valor;
    
    @Column(name = "descricao", length = 1024, nullable = false)
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

	// métodos construtores()
	// métodos getters()/setters()
```
> @ManyToOne - indica que existe uma associação entre Produto e Categoria. 
> @JoinColumn - essa anotação é utilizada para definir o nome da coluna que possui a chave-estrangeira requerida pela associação (definida na propriedade ***name***). Caso essa anotação não seja adicionada, será utilizado o nome do campo.

### Persistindo os dados no banco
Após realizar o mapeamento ORM é necessário criar as classes e métodos que serão utilizados para realizar as operações de *Create Read, Update e Delete* (CRUD).
No pacote **util** está a classe EntityManagerUtil, que será utilizada para instanciar o EntityManager, o qual permite iniciar uma nova sessão com o banco de dados. O EntityManager será o contexto utilizado para todas as operações com o banco de dados.
```java
package br.edu.utfpr.pb.aula2.util;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
    private static EntityManagerFactory emf; 
    public static EntityManager getEntityManager(){
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("aula2-PU");
        }
        return emf.createEntityManager();
    }
}
```
O próximo passo é criar as classes responsáveis por utilizar o EntityManager para persistência dos dados. Essas classes estão no pacote **dao**. A primeira classe criada sera a **CategoriaDao**, na qual estão os métodos para persistencia dos dados da entidade **Categoria**.

```java
package br.edu.utfpr.pb.aula2.dao;
import br.edu.utfpr.pb.aula2.model.Categoria;
import br.edu.utfpr.pb.aula2.util.EntityManagerUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class CategoriaDao {
    private EntityManager em;

    public CategoriaDao() {
        this.em = EntityManagerUtil.getEntityManager();
    }

    public void insert(Categoria categoria) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(categoria);
        em.flush();
        t.commit();
    }

    public void update(Categoria categoria) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(categoria);
        em.flush();
        t.commit();
    }

    public Categoria getById(Integer id) {
        return em.find(Categoria.class, id);
    }
    
    public List<Categoria> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);
        query.from(Categoria.class);
        return em.createQuery(query).getResultList();
    }
       
    public void delete(Integer id) {
        Categoria c = getById(id);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(c);
        em.remove(c);
        em.flush();
        t.commit();
    }
}
```

No método construtor *CategoriaDao(*) o atributo *em* recebe uma instância do EntityManagerUtil. O método *insert()* recebe como entrada uma Categoria, então é iniciada uma transação. Após criar uma transação a categoria 
é adicionada no *persistence context*. Nesse momento o Hibernate sabe que o objeto será armazenado no banco, mas a chamada ao banco não ocorreu ainda. Somente com o commit da transação que o Hibernate verifica o *persistence context* e executa o comando SQL de INSERT. Os demais métodos de necessitam de atualização no banco de dados (*update* e *delete*) seguem o mesmo padrão, inicia-se uma transação, passa para o *persistence context* e, por fim, é realizado o *commit* no banco de dados.
Na classe CategoriaDao também foram implementados os métodos getById() que retorna uma Categoria de acordo com o **ID** e o getAll() que retorna uma lista de Categorias. Como são consultas de leitura de dados, não  foi necessário iniciar e realizar o *commit* da transação.