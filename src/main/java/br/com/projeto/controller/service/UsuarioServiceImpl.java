package br.com.projeto.controller.service;

import br.com.projeto.model.Contato;
import br.com.projeto.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class UsuarioServiceImpl implements IObjetoPersistencia<Usuario>{

    @PersistenceContext(unitName = "AgendawebDS")
    private EntityManager em;

    private Usuario usuario;
    private Usuario usuarioSelecionado;

    public UsuarioServiceImpl() {
        this.usuario = new Usuario();
    }

    @Override
    public void salvar(Usuario obj) {
        this.em.merge(obj);
    }

    @Override
    public void excluir(Usuario obj) {
        if (obj != null) {
            this.usuarioSelecionado = this.buscar(obj);
            // Verifica se a entidade está gerenciada
            if (this.usuarioSelecionado != null) {
                // Remove a entidade
                this.em.remove(this.usuario);
            }
        }
    }

    @Override
    public void editar(Usuario obj) {

    }

    @Override
    public List<Usuario> listar() {
        return List.of();
    }

    @Override
    public Usuario buscar(Usuario obj) {
        return this.em.find(obj.getClass(), obj.getId());
    }
}
