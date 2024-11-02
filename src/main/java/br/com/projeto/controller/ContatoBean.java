package br.com.projeto.controller;

import br.com.projeto.controller.service.ContatoServiceImpl;
import br.com.projeto.controller.service.UsuarioServiceImpl;
import br.com.projeto.model.Contato;
import br.com.projeto.model.Usuario;
import br.com.projeto.model.dao.UsuarioDAO;
import br.com.projeto.util.DownloadPDF;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

@Named
@SessionScoped
public class ContatoBean implements Serializable {
    @Inject
    private LoginBean loginBean;

    @Inject
    private UsuarioDAO usuarioDAO;

    private Contato contato;

    private Usuario usuarioLogado;

    private DownloadPDF downloadPDF;;

    @Inject
    private transient ContatoServiceImpl contatoService;

    private static final Logger logger = Logger.getLogger(ContatoBean.class.getName());

    private final String CONTEXTPATH = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    public ContatoBean() {
        this.contato = new Contato();
        this.downloadPDF = new DownloadPDF();
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

    public void redirectCadastrar() throws IOException {
        logger.info("Redirecionando para tela de cadastro!");
        this.contato = new Contato();
        FacesContext.getCurrentInstance().getExternalContext().redirect(this.CONTEXTPATH + "/pages/contato/novo.xhtml");
    }

    @Transactional
    public void salvar() throws IOException {
        recuperandoUsuarioLogado();
        this.contato.setUsuario(this.usuarioLogado);
        this.contatoService.salvar(this.contato);
        redirectHome();
    }

    @Transactional
    public void redirectEditar(Contato contato) throws IOException {
        this.contato = contato;
        FacesContext.getCurrentInstance().getExternalContext().redirect(this.CONTEXTPATH + "/pages/contato/editar.xhtml");
    }

    public void contatoExcluir(Contato contato){
        this.contato = contato;
    }

    // Método para excluir o contato
    @Transactional
    public void excluir() throws IOException {
        this.contatoService.excluir(this.contato);
        FacesContext.getCurrentInstance().getExternalContext().redirect(this.CONTEXTPATH + "/pages/home/home.xhtml");
    }

    @Transactional
    public void redirectHome() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(this.CONTEXTPATH + "/pages/home/home.xhtml");
    }

    @Transactional
    public void dowloadPDF() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            // Passa o PDF para o método de download
            InputStream inputStream = new ByteArrayInputStream(this.downloadPDF.exportarPDF(baos, this.getContatos()));
            OutputStream outputStream = response.getOutputStream();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=\"agendaweb_contatos.pdf\"");

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            facesContext.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recuperandoUsuarioLogado() {
        this.usuarioLogado  = loginBean.getUsuarioLogado();
    }
}
