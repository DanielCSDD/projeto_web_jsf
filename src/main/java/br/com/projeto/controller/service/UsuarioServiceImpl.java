package br.com.projeto.controller.service;

import br.com.projeto.model.Contato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class UsuarioServiceImpl implements IObjetoPersistencia<Contato>{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Contato obj) {
        this.em.persist(obj);
    }

    @Override
    public void excluir(Contato obj) {

    }

    @Override
    public void editar(Contato obj) {

    }

    @Override
    public List<Contato> listar() {
        return List.of();
    }

    @Override
    public Contato buscar(Contato obj) {
        return null;
    }
}
