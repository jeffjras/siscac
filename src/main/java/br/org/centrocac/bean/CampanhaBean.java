package br.org.centrocac.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

//import br.org.centocac.util.exception.ErroSistema;
import br.org.centrocac.dao.CampanhaDAO;
import br.org.centrocac.entidade.Campanha;
import br.org.centrocac.rn.CampanhaRN;
import java.io.IOException;

@ManagedBean
@ViewScoped
public class CampanhaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Campanha campanha = new Campanha();
    transient private CampanhaDAO campanhaDao = new CampanhaDAO();
    private Date dataHoje = new Date();
    private Campanha campanhaSelecionada = new Campanha();
    private CampanhaRN campanhaRN = new CampanhaRN();

    private String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

    @PostConstruct
    public void posInit() {
        this.imprimeId();

        System.out.println("init campanha bean!!");
        if (id != null) {
            if (id.isEmpty() == false) {
                System.out.println(Integer.valueOf(id).toString());
                this.findOneC(Integer.valueOf(id));
            }
        }

    }

    public void findOneC(Integer id) {
        System.out.println("carregar uma entidade. id: " + id.toString());
        campanha = campanhaRN.obter(id);
        campanhaSelecionada = campanhaRN.obter(id);
        System.out.println("Id campanha: " + campanha.getId().toString());
        this.imprime();
    }

    public CampanhaBean() {

    }

    public void salvar() throws IOException {
        if (campanha.getId() == null) {

            campanha.setArrecadado(BigDecimal.valueOf(0.00));
            campanha.setCadastro(dataHoje);
            if (campanha.getDataFim().before(dataHoje)) {
                System.out.println("condição de data fim anterior a data de criação......");
                adicionarMensagem("Erro!",
                        "Data de Fim da campanha não pode ser anterior ou igual a data de sua criação!",
                        FacesMessage.SEVERITY_WARN);
            } else {
                System.out.println("Salvando nova campanha......");

                campanhaRN.salvar(campanha);
                adicionarMensagem("Salvo!", "Campanha: " + campanha.getNome() + " criada.", FacesMessage.SEVERITY_INFO);
                campanha = new Campanha();
                FacesContext.getCurrentInstance().getExternalContext().redirect("../administrador/listarCampanha.xhtml");
            }
        } else {
            System.out.println("Atualizando campanha......" + campanha.getId().toString());
            this.imprime();
            campanha.setCadastro(campanhaSelecionada.getCadastro());
            campanhaRN.salvar(campanha);
            adicionarMensagem("Salvo!", "Campanha: " + campanha.getNome() + " atualizada.", FacesMessage.SEVERITY_INFO);
            campanha = new Campanha();
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrador/listarCampanha.xhtml");
        }
    }

    public String cancelar() {
        return "/administrador/listarCampanha?faces-redirect=true";
    }

    public void limpar() {
        campanha = new Campanha();
    }

    public void adicionarMensagem(String sumario, String detalhe, FacesMessage.Severity tipoErro) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
    }

    public String goToListaCampanha() {
        return "listarCampanha.xhtml?faces-redirect=true";
    }

    public void imprime() {
        System.out.println("Id:" + campanha.getId() + " / ");
        System.out.println("Nome:" + campanha.getNome());
        System.out.println("Descrição:" + campanha.getDescricao());
        System.out.println("Habilitada" + campanha.getHabilitada());
        System.out.println("Data Fim" + campanha.getDataFim());
        System.out.println("Data Cadastro" + campanha.getCadastro());
    }

    public void imprimeId() {
        System.out.println("Id: " + id);
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public Campanha getCampanhaSelecionada() {
        return campanhaSelecionada;
    }

    public void setCampanhaSelecionada(Campanha campanhaSelecionada) {
        this.campanhaSelecionada = campanhaSelecionada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
