package br.com.projeto.controller.service;

import br.com.projeto.model.Login;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Named;

@Named
@ApplicationScoped
@Default
public class AuthServiceImpl implements IAuthService {
    @Override
    public boolean authenticate(String username, String password, Login entity) {
        return username.equals(entity.getUsername()) && password.equals(entity.getPassword());
    }
}
