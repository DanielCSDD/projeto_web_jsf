package br.com.projeto.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;

public class ContatoDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    // Método para remover o contato pelo ID usando SQL nativo
    @Transactional
    public void excluirContatoPorId(Long idContato, Long idUsuario) {
        try {
            String sql = "DELETE FROM contato WHERE id_contato = :id AND usuario_id_usuario = :id_usuario";
            em.createNativeQuery(sql)
                    .setParameter("id", idContato)
                    .setParameter("id_usuario", idUsuario)
                    .executeUpdate();
            System.out.println("Contato excluído com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao excluir o contato.");
        }
    }
}
