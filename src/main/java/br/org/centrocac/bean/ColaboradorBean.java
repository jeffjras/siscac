/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.entidade.tipo.TipoPerfilUsuario;
import br.org.centrocac.rn.ColaboradorRN;
import br.org.centrocac.util.UtilBean;

/**
 *
 * @author BPMLAB-02
 */
@ManagedBean
@RequestScoped
public class ColaboradorBean {

    //Entidade
    private Colaborador colaborador = new Colaborador();

    //RN
    private final ColaboradorRN COLABORADOR_RN = new ColaboradorRN();

    private List<Colaborador> colaboradores;

    public ColaboradorBean() {
    }

    @PostConstruct
    public void init() {
        colaboradores = COLABORADOR_RN.listar();
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public void salvar() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        colaborador = getUsuarioLogado();
        if (COLABORADOR_RN.salvar(colaborador)) {
            FacesMessage fm = new FacesMessage("Sucesso", "Conta salva com sucesso");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            currentInstance.addMessage(null, fm);
        } else {
            FacesMessage fm = new FacesMessage("Erro", "Não foi possível salvar os dados da Conta");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            currentInstance.addMessage(null, fm);
        }
    }

    public void salvarNovoCadastro() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        if (COLABORADOR_RN.salvar(colaborador)) {
            FacesMessage fm = new FacesMessage("Sucesso", "Cadastro realizado com sucesso. ");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            currentInstance.addMessage(null, fm);
            try {
                currentInstance.getCurrentInstance().getExternalContext().redirect("novoCadastroSucesso.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            FacesMessage fm = new FacesMessage("Erro", "Não foi possível salvar os dados da Conta");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            currentInstance.addMessage(null, fm);
        }
    }

    public void excluir() {
        colaborador = getUsuarioLogado();
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        try {
            if (COLABORADOR_RN.excluir(colaborador)) {
                FacesMessage fm = new FacesMessage("Sucesso", "Colaborador excluído com sucesso.");
                fm.setSeverity(FacesMessage.SEVERITY_INFO);
                currentInstance.addMessage(null, fm);
                colaboradores = COLABORADOR_RN.listar();
            } else {
                FacesMessage fm = new FacesMessage("Erro", "Não foi possível excluir o Colaborador");
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                currentInstance.addMessage(null, fm);
            }
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            currentInstance.getCurrentInstance().getExternalContext().redirect("../index.xhtml?a=-1");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Colaborador getUsuarioLogado() {
        return UtilBean.obterContaLogada();
    }

   public String getPerfilDoUsuarioLogado() {
        Colaborador u = getUsuarioLogado();
        if (u != null) {
            final TipoPerfilUsuario Perfil = TipoPerfilUsuario.obter(u.getPerfil());
            return Perfil.getDescricao();
        }
        return null;
    }
}
