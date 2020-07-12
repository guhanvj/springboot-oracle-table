package mycompany;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author guhanvj
 */

@SpringBootApplication
public class OracleTableComparisonApplication extends SpringBootServletInitializer {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public Session getSession() {
        entityManager=entityManager.getEntityManagerFactory().createEntityManager();
        return entityManager.unwrap(Session.class);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OracleTableComparisonApplication.class);
    }

    private static final Logger log = LoggerFactory.getLogger(OracleTableComparisonApplication.class);
    public static void main(String args[]){
        SpringApplication.run(OracleTableComparisonApplication.class,args);
    }
}
