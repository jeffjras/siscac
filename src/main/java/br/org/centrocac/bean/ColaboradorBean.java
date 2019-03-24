/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.rn.ColaboradorRN;
import br.org.centrocac.util.UtilBean;

/**
 *
 * @author BPMLAB-02
 */
public class ColaboradorBean {

    //Entidade
    private Colaborador colaborador = new Colaborador();

    //RN
    private final ColaboradorRN COLABORADOR_RN = new ColaboradorRN();

    public ColaboradorBean() {
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public String salvar() {
        if (COLABORADOR_RN.salvar(colaborador)) {
            UtilBean.criarMensagemDeAviso("Cadastro efetudo com sucesso!");
            return null;
        } else {
            UtilBean.criarMensagemDeErro("Não foi possível efetuar o cadastro!");
            return null;
        }
    }

    public String excluir() {
        if (COLABORADOR_RN.excluir(colaborador)) {
            UtilBean.criarMensagemDeAviso("Colaborador excluído com sucesso!");
            return null;
        } else {
            UtilBean.criarMensagemDeErro("Colaborador excluído com sucesso!");
            return null;
        }
    }

}
