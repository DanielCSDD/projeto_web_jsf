package br.com.projeto.model.dao;

import br.com.projeto.model.Login;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

public class LoginDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Login findByUsername(String username) {
        try {
            return em.createQuery("SELECT l FROM Login l WHERE l.username = :username", Login.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void save(Login login) {
        em.persist(login);
    }

    @Transactional
    public void update(Login login) {
        em.merge(login);
    }

    @Transactional
    public void delete(String username) {
        Login login = findByUsername(username);
        if (login != null) {
            em.remove(login);
        }
    }

    @Transactional
    public List<Login> findAll() {
        return em.createQuery("SELECT l FROM Login l", Login.class).getResultList();
    }
}
