# Aula1 - oo24s
## Revisão de JDBC
O projeto **aula1** servirá como base para uma revisão sobre JDBC. O projeto foi criado utilizando o **[Maven](https://maven.apache.org/)** para gerenciamento das dependências. As dependências são adicionadas no arquivo ***pom.xml***. No código abaixo é apresentado o arquivo pom.xml utilizado no projeto aula1, nos elementos ***\<dependency>*** estão as dependências dos drivers jdbc dos bancos *postgresql* e do *mysql*.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>br.edu.utfpr.pb</groupId>
    <artifactId>aula1</artifactId>
    <version>1.0</version>
    <packaging>jar</packaging>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>  
    <dependencies>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.1.4</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>6.0.6</version>
        </dependency>
    </dependencies>
</project>
```
Os pacotes de código fonte do projeto são o **model, dao, db e main**. No pacote **model** estão as classes *Categoria* e *Produto*. No pacote **dao** estão as classes que fazem as operações de CRUD para Categoria e Produto. No pacote **db** está a classe com as configurações para conexão com o banco de dados. E no pacote **main** está a classe para testar as operações de CRUD.

No método ***insert()*** da classe *CategoriaDao* está um exemplo de como não fazer uma operação de Insert com JDBC, pois está suscetível ao uso de SQLInjection. Para testar basta utilizar os comandos SQL nos comentários presentes na classe *Main*.

