package br.senai.sp.info.pweb.jucacontrol.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class PersistenceConfig {

	/**
	 * Cria um objeto com as informa��es de conex�o com o banco de dados para ser utilizado pelo Hibernate
	 * @return
	 */
	// Injetar� toda vez que algu�m precisar do DataSource, uma forma de determinar uma classe para ser injetada pelo Spring
	@Bean
	// � b�sicamente a ConnectionFactory
	public DataSource getDataSource() { 
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/jucacontrol_tt3?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("root132");
		return dataSource;
	}
	
	public Properties getHibernateProperties() {
		// � um mapa de chaves e valores
		Properties props = new Properties(); 
		// Toda vez que ele dispara um SQL ele mostra no console
		props.setProperty("hibernate.show_sql", "true"); 
		// hbm2ddl =  Hibernate Mapping to Data Definition Language
		 /* validate n�o muda o banco de dados e diz que a aplica��o est� ok
		 * update � quando faz-se adi��es em algum campo de alguma tabela (ex: colocar uma nova string ele atualiza, mudar o length n�o)
		 * create cria o esquema destruindo tudo que havia anteriormente (quando estamos desenvolvendo, � bom usar o create para ter certeza de tudo certo)
		 create-drop � um pouco obscuro o uso, aparentemente quando a sess�o do hibernate � terminada, ele dropa o banco*/
		props.setProperty("hibernate.hbm2ddl.auto", "create"); 
		// Faz a tradu��o do comando para a linguagem de um banco de dados
		// Determina para o Hibernate que ser� utilizado o MySQL como dialeto
		// Dialect � a classe que realiza as tradu��es de SQL das opera��es do banco de dados
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return props;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(getDataSource());
		factoryBean.setHibernateProperties(getHibernateProperties());
		// Define em qual pacote se encontram os Models da aplica��o para que o hibernate possa mapea-los
		factoryBean.setPackagesToScan("br.senai.sp.info.pweb.jucacontrol.models");
		return factoryBean;
	}
}