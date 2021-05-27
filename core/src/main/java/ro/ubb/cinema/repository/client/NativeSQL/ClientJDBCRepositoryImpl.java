package ro.ubb.cinema.repository.client.NativeSQL;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.cinema.domain.entities.Client;
import ro.ubb.cinema.repository.CustomRepositorySupport;
import ro.ubb.cinema.repository.client.ClientJDBCRepository;
import ro.ubb.cinema.repository.client.ClientJDBCRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("ClientJDBCRepositoryNativeSQLImpl")
@NoRepositoryBean
public class ClientJDBCRepositoryImpl  extends CustomRepositorySupport implements ClientJDBCRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(ClientJDBCRepository.class);

    @Transactional
    @Override
    public Client customFindOneLevel1(long id) {
        log.trace("Method [customFindOneLevel1] in {NativeSQL} started.");

        EntityManager entityManager = getEntityManager();
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        Query query = session.createSQLQuery("SELECT DISTINCT {a.*}, {b.*} " +
                "FROM client a " +
                "LEFT JOIN Ticket b on a.identifier = b.clientId " +
                "WHERE a.identifier = ?1")
                .setParameter(1, id)
                .addEntity("a",Client.class)
                .addJoin("b", "a.tickets")
                .addEntity("a",Client.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        Client client = (Client) query.getSingleResult();
        log.trace("Method [customFindOneLevel1] in {NativeSQL} ended with result = {}.", client);

        return client;
    }

    @Override
    @Transactional
    public List<Client> customFindAllLevel1() {
        log.trace("Method [customFindAllLevel1] in {NativeSQL} started.");

        EntityManager entityManager = getEntityManager();
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        Query query = session.createSQLQuery("SELECT DISTINCT {a.*}, {b.*} " +
                "FROM Client a " +
                "LEFT JOIN Ticket b on a.identifier = b.clientId ")
                .addEntity("a",Client.class)
                .addJoin("b", "a.tickets")
                .addEntity("a",Client.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Client> clients = query.getResultList();
        log.trace("Method [customFindAllLevel1] in {NativeSQL} ended with result = {}.", clients);

        return clients;
    }
}
