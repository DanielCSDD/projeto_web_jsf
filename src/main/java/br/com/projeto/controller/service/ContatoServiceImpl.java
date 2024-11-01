package br.com.projeto.controller.service;

import br.com.projeto.model.Contato;
import br.com.projeto.model.dao.ContatoDAO;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class ContatoServiceImpl implements IObjetoPersistencia<Contato>{

    @PersistenceContext(unitName = "AgendawebDS")
    private transient EntityManager em;

    @Inject
    private ContatoDAO contatoDAO;

    private Contato contato;
    private Contato contatoSelecionado;

    public ContatoServiceImpl() {
        this.contato = new Contato();
    }

    @Override
    public void salvar(Contato obj) {
        this.em.merge(obj);
    }

    @Override
    public void excluir(Contato obj) {
        if (obj != null) {
            this.contatoSelecionado = this.buscar(obj);
            // Verifica se a entidade está gerenciada
            if (this.contatoSelecionado != null) {
                this.contatoDAO.excluirContatoPorId(this.contatoSelecionado.getId(), this.contatoSelecionado.getUsuario().getId());
            }
        }
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
        return this.em.find(obj.getClass(), obj.getId());
    }
}
