package br.com.projeto.controller;

import br.com.projeto.controller.service.UsuarioServiceImpl;
import br.com.projeto.model.Contato;
import br.com.projeto.model.Usuario;
import br.com.projeto.model.dao.UsuarioDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ContatoBean implements Serializable {
    @Inject
    private LoginBean loginBean;

    @Inject
    private UsuarioDAO usuarioDAO;

    private Contato contato;

    @Inject
    private UsuarioServiceImpl usuarioService;

    private static final Logger logger = Logger.getLogger(ContatoBean.class.getName());

    private final String CONTEXTPATH = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    public ContatoBean() {
        this.contato = new Contato();
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Contato> getContatos() {
        return usuarioDAO.getContacts(loginBean.getUsuarioLogado().getId());
    }

    public void redirectCadastro() throws IOException {
        logger.info("Redirecionando para tela de cadastro!");
        FacesContext.getCurrentInstance().getExternalContext().redirect(this.CONTEXTPATH + "/pages/contato/novo.xhtml");
    }

    @Transactional
    public void novo() throws IOException {
        Usuario usuarioLogado = loginBean.getUsuarioLogado();
        this.contato.setUsuario(usuarioLogado);
        this.usuarioService.salvar(this.contato);
        limparFormulario();
        FacesContext.getCurrentInstance().getExternalContext().redirect(this.CONTEXTPATH + "/pages/home/home.xhtml");
    }

    public void limparFormulario(){
        this.contato = new Contato();
    }
}
