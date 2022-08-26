package com.duberlyguarnizo.util;

//CDI entity manager producer

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class EntityManagerProducer {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    //@Produces
    @Produces
    @Dependent
    @Default
    public EntityManager getEntityManagerFactory() {
        return entityManagerFactory.createEntityManager();
    }
    @PostConstruct
    public void init() {
        System.out.println("EntityManagerProducer.init");
        if (entityManagerFactory == null) {
            System.out.println("Hey! EntityManagerProducer.init entityManagerFactory is null! Creating a new one!");
            entityManagerFactory = Persistence.createEntityManagerFactory("payara-micro-test");
        }
    }

    public void closeEntityManager(EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
