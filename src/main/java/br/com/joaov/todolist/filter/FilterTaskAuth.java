package br.com.joaov.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.joaov.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
                // Pegar a autenticação (usuario e senha)

                var authorization = request.getHeader("Authorization");
                var authEnconded = authorization.substring("Basic".length()).trim();
                byte[] authDecode = Base64.getDecoder().decode(authEnconded);
                var authString = new String(authDecode);
                

                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];


                // Validar usuário

                var user = this.userRepository.findByUsername(username);
                if(user == null){
                    response.sendError(401, "Usuário sem permissão");
                } else {
                    var BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                // Validar senha

                // Segue viagem

                filterChain.doFilter(request, response);
            }

    }


    
}
