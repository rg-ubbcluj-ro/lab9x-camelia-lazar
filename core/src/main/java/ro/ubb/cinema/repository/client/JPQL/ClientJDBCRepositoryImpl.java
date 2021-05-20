package ro.ubb.cinema.repository.client.JPQL;

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

@Component("ClientJDBCRepositoryJPQLImpl")
@NoRepositoryBean
public class ClientJDBCRepositoryImpl extends CustomRepositorySupport implements ClientJDBCRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(ClientJDBCRepository.class);

    @Override
    @Transactional
    public Client customFindOneLevel1(long id) {
        log.trace("Method [customFindOneLevel1] in {JPQL} started.");

        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "SELECT DISTINCT c FROM Client c " +
                        "LEFT JOIN FETCH c.tickets t " +
                        "WHERE c.identifier = ?1"
        )
                .setParameter(1, id)
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("clientGraphLevel1"));

        Client client = (Client) query.getSingleResult();
        log.trace("Method [customFindOneLevel1] in {JPQL} ended with result = {}.", client);

        return client;
    }

    @Override
    @Transactional
    public List<Client> customFindAllLevel1() {
        log.trace("Method [customFindAllLevel1] in {JPQL} started.");

        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "SELECT DISTINCT c FROM Client c " +
                        "LEFT JOIN FETCH c.tickets t"
        )
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("clientGraphLevel1"));

        @SuppressWarnings("unchecked")
        List<Client> clients = query.getResultList();
        log.trace("Method [customFindAllLevel1] in {JPQL} ended with result = {}.", clients);

        return clients;
    }
}
