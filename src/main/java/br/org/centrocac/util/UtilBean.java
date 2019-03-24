/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.util;

import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.rn.ColaboradorRN;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author bpmlab
 */
public class UtilBean {

    /**
     * Recupera, do escopo da requisição, o valor do parâmetro com nome
     * informado.
     *
     * @param parametro nome do parâmetro que guarda o valor que se deseja
     * recuperar
     * @return
     */
    public static String obterValor(String parametro) {
        if (parametro == null) {
            return null;
        } else {
            FacesContext currentInstance = FacesContext.getCurrentInstance();
            return currentInstance.getExternalContext().getRequestParameterMap().get(parametro);
        }
    }

    public static void adicionarValor(String parametro, String valor) {
        if (parametro != null && valor != null) {
            FacesContext currentInstance = FacesContext.getCurrentInstance();
            currentInstance.getExternalContext().getRequestParameterMap().put(parametro, valor);
        }
    }

    public static void criarMensagemDeInformacao(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_INFO, resumo, detalhe);
    }

    public static void criarMensagemDeInformacao(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", detalhe);
    }

    public static void criarMensagemDeAviso(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_WARN, resumo, detalhe);
    }

    public static void criarMensagemDeAviso(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_WARN, "Alerta", detalhe);
    }

    public static void criarMensagemDeErro(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_ERROR, resumo, detalhe);
    }

    public static void criarMensagemDeErro(String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro", detalhe);
    }

    private static void criarMensagem(FacesMessage.Severity tipo, String resumo, String detalhe) {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(resumo, detalhe);
        fm.setSeverity(tipo);
        currentInstance.addMessage(null, fm);
    }

    public static String obterEmailDaSessao() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext e = facesContext.getExternalContext();
        if (e != null) {
            return e.getRemoteUser();
        } else {
            return null;
        }
    }

    public static Object daSessao(String chave) {
        if (chave == null) {
            return null;
        } else {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessao = externalContext.getSessionMap();
            return sessao.get(chave);
        }
    }

    public static void naSessao(String chave, Object valor) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessao = externalContext.getSessionMap();
        //Preciosismo: remover antes de inserir
        sessao.remove(chave);
        sessao.put(chave, valor);
    }

    public static Colaborador obterContaLogada() {
        final String CHAVE_CONTA = "conta";
        Object objColaborador = UtilBean.daSessao(CHAVE_CONTA);
        if (objColaborador == null) {
            String email = UtilBean.obterEmailDaSessao();
            ColaboradorRN colaboradorRN = new ColaboradorRN();
            Colaborador colaborador = colaboradorRN.obter(email);
            Colaborador contaTemp = null;
            //Clonando o usuário logado
            if (colaborador != null) {
                contaTemp = new Colaborador();
                contaTemp.setId(colaborador.getId());
                contaTemp.setNome(colaborador.getNome());
                contaTemp.setEmail(colaborador.getEmail());
                contaTemp.setPerfil(colaborador.getPerfil());
                contaTemp.setConfirmacao(colaborador.getConfirmacao());
            }
            UtilBean.naSessao(CHAVE_CONTA, contaTemp);
            return contaTemp;
        } else {
            return (Colaborador) objColaborador;
        }
    }

    public static void atualizarContaLogada(Colaborador colaborador) {
        final String CHAVE_PESSOA = "conta";
        if (colaborador != null) {
            naSessao(CHAVE_PESSOA, colaborador);
        }
    }

}
