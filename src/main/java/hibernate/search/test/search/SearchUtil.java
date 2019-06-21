package hibernate.search.test.search;

import hibernate.search.test.model.Post;
import lombok.SneakyThrows;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class SearchUtil {

    @PersistenceContext
    private EntityManager entityManager;

    @SneakyThrows
    @Transactional
    public void searchUser(){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Post.class)
                .get();
//        Query query = queryBuilder
//                .keyword()
//                .onFields("user.name")
//                .matching("Jane")
//                .createQuery();
        Query query = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("title")
                .matching("Title_01")
                .createQuery();
        FullTextQuery jpaQuery = fullTextEntityManager
                .createFullTextQuery(query, Post.class);
//        jpaQuery.setFirstResult(2);
//        jpaQuery.setMaxResults(2);
//        jpaQuery.setSort(queryBuilder.sort().byField("id").desc().createSort());
        List<Post> userList = jpaQuery.getResultList();
        userList.forEach(System.out::println);
    }
}
