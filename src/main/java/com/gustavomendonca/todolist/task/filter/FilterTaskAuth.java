package com.gustavomendonca.todolist.task.filter;

import com.gustavomendonca.todolist.user.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {

        /**
         * Pegar o usuario e senha
         *
         * validar o usuario
         *
         * validar a senha
         *
         * liberar
         */

        //Pegar o usuario e senha
        var authorization = request.getHeader("Authorization");
        System.out.println("auth");
        System.out.println(authorization);

        var authEncoded = authorization.substring("Basic".length()).trim();
        byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

        var authString = new String(authDecoded);

        System.out.println(authString);

        String[] credentials = authString.split(":");
        String username = credentials[0];
        String password = credentials[1];

        System.out.println(username);
        System.out.println(password);

        filterChain.doFilter(request, response);

    }
}
