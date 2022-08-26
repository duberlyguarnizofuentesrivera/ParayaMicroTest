package com.duberlyguarnizo.repository;
//CDI entity repository

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import com.duberlyguarnizo.model.PoliticiansManager;

@RequestScoped
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class PoliticianManagerRepository {
    @Inject
    EntityManager entityManager;

    //CRUD
    public void createPolitician(PoliticiansManager politician) {
        entityManager.persist(politician);
    }

    public PoliticiansManager updatePolitician(PoliticiansManager politician) {
        return entityManager.merge(politician);
    }

    public void deletePolitician(Long id) {
        PoliticiansManager politician = findPoliticianById(id);
        if (politician != null) {
            entityManager.remove(politician);
        }
    }

    public PoliticiansManager findPoliticianById(Long id) {
        return entityManager.find(PoliticiansManager.class, id);
    }

    public PoliticiansManager findPoliticianByEmail(String email) {
        TypedQuery<PoliticiansManager> query = entityManager.createQuery("SELECT p FROM PoliticiansManager p WHERE p.email = :email", PoliticiansManager.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    public Iterable<PoliticiansManager> findPoliticianByName(String name) {
        TypedQuery<PoliticiansManager> query = entityManager.createQuery("SELECT p FROM PoliticiansManager p WHERE p.name = :name", PoliticiansManager.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public Iterable<PoliticiansManager> findPoliticianByLastName(String lastName) {
        TypedQuery<PoliticiansManager> query = entityManager.createQuery("SELECT p FROM PoliticiansManager p WHERE p.lastName = :lastName", PoliticiansManager.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    public Iterable<PoliticiansManager> findPoliticianByCountry(String country) {
        TypedQuery<PoliticiansManager> query = entityManager.createQuery("SELECT p FROM PoliticiansManager p WHERE p.country = :country", PoliticiansManager.class);
        query.setParameter("country", country);
        return query.getResultList();
    }

    public Iterable<PoliticiansManager> findPoliticiansByAge(int age) {
        TypedQuery<PoliticiansManager> query = entityManager.createQuery("SELECT p FROM PoliticiansManager p WHERE p.age = :age", PoliticiansManager.class);
        query.setParameter("age", age);
        return query.getResultList();
    }

    //list all politicians
    public Iterable<PoliticiansManager> findAllPoliticians() {
        return entityManager.createQuery("SELECT p FROM PoliticiansManager p", PoliticiansManager.class).getResultList();
    }

    //search by similar name
    public Iterable<PoliticiansManager> findPoliticiansBySimilarName(String name) {
        TypedQuery<PoliticiansManager> query = entityManager.createQuery("SELECT p FROM PoliticiansManager p WHERE p.name LIKE :name", PoliticiansManager.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

}
