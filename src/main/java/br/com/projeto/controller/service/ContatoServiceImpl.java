package br.com.projeto.controller.service;

import br.com.projeto.model.Contato;
import br.com.projeto.model.dao.ContatoDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Named
@ApplicationScoped
@Default
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
        if(obj.getId() == null){
            this.em.persist(obj);
        } else {
            this.em.merge(obj);
        }
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
