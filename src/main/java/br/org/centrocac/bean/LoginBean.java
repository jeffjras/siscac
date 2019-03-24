/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.util.UtilBean;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author AdminBPMLAB
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    public boolean erroAoAutenticar(Map<String, String> parametrosDaRequisicao) {
        boolean resposta = false;
        for (String chave : parametrosDaRequisicao.keySet()) {
            if (chave.equals("e") && parametrosDaRequisicao.get(chave).equals("-1")) {
                resposta = true;
                break;
            }
        }
        return resposta;
    }

    public Colaborador getContaLogada() {
        return UtilBean.obterContaLogada();
    }

}
