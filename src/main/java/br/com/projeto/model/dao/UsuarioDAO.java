package br.com.projeto.model.dao;

import br.com.projeto.model.Contato;
import br.com.projeto.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

public class UsuarioDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Usuario findByUsername(String username) {
        return em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Transactional
    public List<Contato> getContacts(Long userId) {
        return em.createQuery("SELECT c FROM Contato c WHERE c.usuario.id = :userId", Contato.class)
                .setParameter("userId", userId)
                .getResultList();

    }
}
