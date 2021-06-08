package com.pos.controllers;

import com.pos.models.InvoiceEntity;
import com.pos.models.ProductoEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class Productos {

    public static List getAbarrotes() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");

    	EntityManager em = factory.createEntityManager();
    	Query q = em.createQuery("select p from ProductoEntity p where p.type = 'abarrotes'");

    	return q.getResultList();
    }

    public static List getBebidas() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");

        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select p from ProductoEntity p where p.type = 'bebidas'");

        return q.getResultList();
    }

    public static List getBotanas() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");

        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select p from ProductoEntity p where p.type = 'botanas'");

        return q.getResultList();
    }

    public static void insertInvoice(InvoiceEntity invoice) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");

        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(invoice);
        em.getTransaction().commit();
    }
}