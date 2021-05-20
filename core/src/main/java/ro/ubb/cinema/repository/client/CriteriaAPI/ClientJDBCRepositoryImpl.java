package ro.ubb.cinema.repository.client.CriteriaAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.cinema.domain.entities.Client;
import ro.ubb.cinema.domain.entities.Client_;
import ro.ubb.cinema.repository.CustomRepositorySupport;
import ro.ubb.cinema.repository.cinema.CinemaJDBCRepository;
import ro.ubb.cinema.repository.client.ClientJDBCRepository;
import ro.ubb.cinema.repository.client.ClientJDBCRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Component("ClientJDBCRepositoryCriteriaAPIImpl")
@NoRepositoryBean
public class ClientJDBCRepositoryImpl extends CustomRepositorySupport implements ClientJDBCRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(ClientJDBCRepository.class);

    @Override
    @Transactional
    public Client customFindOneLevel1(long id) {
        log.trace("Method [customFindOneLevel1] in {CriteriaAPI} started.");

        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        query.distinct(Boolean.TRUE);
        Root<Client> root = query.from(Client.class);
        query.where(criteriaBuilder.equal(root.get("identifier"), id));
        query.select(root);
        root.fetch(Client_.tickets, JoinType.LEFT);

        Client client = entityManager.createQuery(query)
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("clientGraphLevel1"))
                .getSingleResult();

        log.trace("Method [customFindOneLevel1] in {CriteriaAPI} ended with result = {}.", client);

        return client;
    }

    @Override
    @Transactional
    public List<Client> customFindAllLevel1() {
        EntityManager entityManager = getEntityManager();

        log.trace("Method [customFindAllLevel1] in {CriteriaAPI} started.");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        query.distinct(Boolean.TRUE);
        Root<Client> root = query.from(Client.class);
        root.fetch(Client_.tickets, JoinType.LEFT);

        List<Client> clients = entityManager.createQuery(query).setHint("javax.persistence.fetchgraph",
                entityManager.getEntityGraph("clientGraphLevel1")).getResultList();

        log.trace("Method [customFindAllLevel1] in {CriteriaAPI} ended with result = {}.", clients);

        return clients;
    }
}
