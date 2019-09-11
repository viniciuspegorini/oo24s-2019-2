# Aula2 - oo24s
## Introdução ao Mapeamento Objeto Relacional em Java

### Introdução
O projeto **aula2** servirá como base para apresentar os conceitos iniciais sobre Mapeamento Objeto Relacional (ORM). O *framework* ORM utilizado no projeto foi o Hibernate adotando o padrão da API de persistência de dados ***Java Persistence API*** (**JPA**).

### Hibernate
O _framework_ Hibernate é um projeto que tem por objetivo fornecer uma solução completa para o problema de gerenciamento de dados persistentes em Java. Atualmente, o Hibernate não é apenas um _framework_ ORM, mas também um conjunto de ferramentas para o gerenciamento de dados que vai além do ORM.

### Projeto exemplo
O projeto foi criado utilizando o **[Maven](https://maven.apache.org/)** para gerenciamento das dependências. As dependências são adicionadas no arquivo ***pom.xml***. No código abaixo é apresentado o arquivo pom.xml utilizado no projeto aula2, nos elementos ***\<dependency>*** estão as dependências do driver jdbc do banco *postgresql* e as dependências do Hibernate.

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
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
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