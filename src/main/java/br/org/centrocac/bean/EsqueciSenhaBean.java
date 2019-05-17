/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.rn.ColaboradorRN;
import br.org.centrocac.util.UtilBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author toshiaki
 */
@ManagedBean
@ViewScoped
public class EsqueciSenhaBean {

    private String userEmail;
    private Colaborador usuario;
    private String modoDeEnvio;
    private List<String> modoDeEnvioList = new ArrayList<>();

    // RN
    private ColaboradorRN rn = new ColaboradorRN();

    // AUX
    private String outcome;


    @PostConstruct
    private void postInit() {
        modoDeEnvioList.add("email");
        modoDeEnvio = "email";
        outcome = "index.xhtml?faces-redirect=true";
    }

    public void pesquisarUsuarioPorEmail() {
        usuario = rn.obter(userEmail);
        System.out.println("-----------------------------");
        System.out.println("usuario");
        System.out.println(usuario);
        if (usuario == null) {
            System.out.println("entrou no erro");
            UtilBean.criarMensagemDeErro("Usuário não encontrado em nosso sistema.");
        }
    }

    private void limparCampos() {
        userEmail = "";
        usuario = null;
    }

    public String cancelar() {
        limparCampos();
        return outcome;
    }

    public void voltar() {
        limparCampos();
    }

    public String redefinirSenha() {
        if (rn.redefinirSenha(usuario)) {
            System.out.println("Esqueci bean, envio de email realizado com sucesso");
            UtilBean.criarMensagemDeInformacao("Nova senha enviado ao email: " + userEmail);
            return outcome;
        } else {
            System.out.println("Esqueci bean, ERRO no envio de email");
            UtilBean.criarMensagemDeErro("Erro no processo!!!");
            return null;
        }
    }

    public List<String> getModoDeEnvioList() {
        return modoDeEnvioList;
    }

    public void setModoDeEnvioList(List<String> modoDeEnvioList) {
        this.modoDeEnvioList = modoDeEnvioList;
    }

    public String getModoDeEnvio() {
        return modoDeEnvio;
    }

    public void setModoDeEnvio(String modoDeEnvio) {
        this.modoDeEnvio = modoDeEnvio;
    }

    public Colaborador getUsuario() {
        return usuario;
    }

    public void setUsuario(Colaborador usuario) {
        this.usuario = usuario;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
