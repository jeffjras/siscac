package br.org.centrocac.spring;


import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.rn.ColaboradorRN;
import  br.org.centrocac.util.DonazioneUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

public class AuthenticationHandler implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String username = a.getName();
        String password = DonazioneUtil.encriptarSHA256(a.getCredentials().toString());
        ColaboradorRN colaboradorRN = new ColaboradorRN();
        if (colaboradorRN.autenticar(username, password)) {
            Colaborador c = colaboradorRN.obter(username);
            GrantedAuthorityImpl ga = new GrantedAuthorityImpl("ROLE_"+c.getPerfil());
            List<GrantedAuthority> auts = new ArrayList<>();
            auts.add(ga);
            UsernamePasswordAuthenticationToken resposta = new UsernamePasswordAuthenticationToken(username, password, auts);
            return resposta;
        } else {
            throw new BadCredentialsException("Email ou Senha inválidos");

        }
    }

    @Override
    public boolean supports(Class<? extends Object> type) {
        return true;
    }

}
