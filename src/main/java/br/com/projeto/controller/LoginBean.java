package br.com.projeto.controller;

import br.com.projeto.controller.service.IAuthService;
import br.com.projeto.model.Login;
import br.com.projeto.model.Contato;
import br.com.projeto.model.Usuario;
import br.com.projeto.model.dao.LoginDAO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    @NotNull
    @Size(min = 1, message = "Username cannot be empty")
    private String username;

    @NotNull
    @Size(min = 1, message = "Password cannot be empty")
    private String password;

    @Inject
    private IAuthService authService;

    @Inject
    private LoginDAO loginDAO;

    private Login loginAutenticado;

    private boolean logado;

    public LoginBean() {
        this.loginAutenticado = new Login();
        this.loginAutenticado.setUsuario(new Usuario());
        this.setLogado(false);
    }

    private final String CONTEXTPATH = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    private static final Logger logger = Logger.getLogger(LoginBean.class.getName());

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public String getUsuarioAutenticado() {
        return this.loginAutenticado.getUsuario().getNome();
    }

    public Usuario getUsuarioLogado() {
        return this.loginAutenticado.getUsuario();
    }

    public String login() {
        this.loginAutenticado = this.loginDAO.findByUsername(this.username);
        logger.info("Tentando login com usuário: " + loginAutenticado.getUsername());
        if (loginAutenticado != null && authService.authenticate(username, password, loginAutenticado)) {
            try {
                this.setLogado(true);
                logger.info("Login bem-sucedido para usuário: " + this.loginAutenticado.getUsername());
                FacesContext.getCurrentInstance().getExternalContext().redirect(this.CONTEXTPATH + "/pages/home/home.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            logger.warning("Falha no login para usuário: " + this.username);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login failed. Invalid username or password."));
            return null;
        }
    }


    public void logout() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect(this.CONTEXTPATH + "/pages/login/login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
