package hibernate.search.test;

import hibernate.search.test.search.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class HibernateSearchApplication implements CommandLineRunner {

    @Autowired
    private SearchUtil searchUtil;

    public static void main(String[] args) {
        SpringApplication.run(HibernateSearchApplication.class, args);
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        searchUtil.searchUser();
    }
}
