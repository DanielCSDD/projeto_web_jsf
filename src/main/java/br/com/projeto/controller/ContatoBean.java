package br.com.projeto.controller;

import br.com.projeto.model.Contato;
import br.com.projeto.model.dao.UsuarioDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class ContatoBean implements Serializable {
    @Inject
    private LoginBean loginBean;

    @Inject
    private UsuarioDAO usuarioDAO;

    public List<Contato> getContatos() {
        return usuarioDAO.getContacts(loginBean.getUsuarioLogado().getId());
    }
}
