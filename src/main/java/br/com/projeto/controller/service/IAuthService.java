package br.com.projeto.controller.service;

import br.com.projeto.model.Login;

public interface IAuthService {
    boolean authenticate(String username, String password, Login entity);
}
