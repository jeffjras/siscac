/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.spring;

import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.rn.ColaboradorRN;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 *
 * @author bpmlab
 */
public class LoginHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    private final ColaboradorRN RN = new ColaboradorRN();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws IOException, ServletException {
        String username = a.getName();
        Colaborador usuario = RN.obter(username);
        String pagina = "/index.xhtml";
        if (usuario != null) {
            pagina = "/usuario/main.xhtml";
        }
        setDefaultTargetUrl(pagina);
        super.onAuthenticationSuccess(request, response, a);
    }

}
