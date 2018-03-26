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
	 * Cria um objeto com as informações de conexão com o banco de dados para ser utilizado pelo Hibernate
	 * @return
	 */
	// Injetará toda vez que alguém precisar do DataSource, uma forma de determinar uma classe para ser injetada pelo Spring
	@Bean
	// É básicamente a ConnectionFactory
	public DataSource getDataSource() { 
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/jucacontrol_tt3?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("root132");
		return dataSource;
	}
	
	public Properties getHibernateProperties() {
		// É um mapa de chaves e valores
		Properties props = new Properties(); 
		// Toda vez que ele dispara um SQL ele mostra no console
		props.setProperty("hibernate.show_sql", "true"); 
		// hbm2ddl =  Hibernate Mapping to Data Definition Language
		 /* validate não muda o banco de dados e diz que a aplicação está ok
		 * update é quando faz-se adições em algum campo de alguma tabela (ex: colocar uma nova string ele atualiza, mudar o length não)
		 * create cria o esquema destruindo tudo que havia anteriormente (quando estamos desenvolvendo, é bom usar o create para ter certeza de tudo certo)
		 create-drop é um pouco obscuro o uso, aparentemente quando a sessão do hibernate é terminada, ele dropa o banco*/
		props.setProperty("hibernate.hbm2ddl.auto", "create"); 
		// Faz a tradução do comando para a linguagem de um banco de dados
		// Determina para o Hibernate que será utilizado o MySQL como dialeto
		// Dialect é a classe que realiza as traduções de SQL das operações do banco de dados
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return props;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(getDataSource());
		factoryBean.setHibernateProperties(getHibernateProperties());
		// Define em qual pacote se encontram os Models da aplicação para que o hibernate possa mapea-los
		factoryBean.setPackagesToScan("br.senai.sp.info.pweb.jucacontrol.models");
		return factoryBean;
	}
}