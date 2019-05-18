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
import br.org.centrocac.util.UtilBean;
import java.io.IOException;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CampanhaBeanOld implements Serializable {

    private static final long serialVersionUID = 1L;
    private Campanha campanha = new Campanha();
    transient private CampanhaDAO campanhaDao = new CampanhaDAO();
    private List<Campanha> campanhas = new ArrayList<>();
    private Date dataHoje = new Date();
    private Campanha campanhaSelecionada = new Campanha();
    private Date dataInicio = null, dataFim = null;
    private Boolean ativo = true;

    private Object id;

    @PostConstruct
    public void posInit() {

        campanhas = campanhaDao.obterTodos(Campanha.class);
        System.out.println("init campanha bean!!");
        //System.out.println(UtilBean.daSessao("id").toString());
        id = UtilBean.daSessao("id");
        if (id != null) {
            System.out.println("Id" + id.toString());
            findOne((Integer) id);
        }
    }

    public void findOne(Integer id) {
        campanha = campanhaDao.obter(Campanha.class, id);
    }

    public CampanhaBeanOld() {

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
                campanhaDao.criar(campanha);
                adicionarMensagem("Salvo!", "Campanha: " + campanha.getNome() + " criada.", FacesMessage.SEVERITY_INFO);
                campanha = new Campanha();
                FacesContext.getCurrentInstance().getExternalContext().redirect("../administrador/listarCampanha.xhtml");
            }
        } else {
            System.out.println("Atualizando campanha......" + campanha.getId().toString());
            campanha.setCadastro(dataHoje);
            campanhaDao.alterar(campanha);
            adicionarMensagem("Salvo!", "Campanha: " + campanha.getNome() + " atualizada.", FacesMessage.SEVERITY_INFO);
            campanha = new Campanha();
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrador/listarCampanha.xhtml");
        }
    }

    public String cancelar() {
        return "/administrador/listarCampanha?faces-redirect=true";
    }
    public List<Campanha> listarTodos() {
        if (dataInicio != null && dataFim != null) {
            campanhas = campanhaDao.obter(dataInicio, dataFim, ativo);
            return campanhas;

        } else {
            campanhas = campanhaDao.obterTodos(Campanha.class);
            return campanhas;
        }
    }

    public void limpar() {
        campanha = new Campanha();
    }

    public void adicionarMensagem(String sumario, String detalhe, FacesMessage.Severity tipoErro) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
    }

    public String goToEditar() {
        campanha = campanhaSelecionada;
        System.out.println("CampanhaBean/editar/" + campanha.getId() + " / " + campanhaSelecionada.getId());
        return "campanha.xhtml?faces-redirect=true";
    }

    public String goToListaCampanha() {
        return "listarCampanha.xhtml?faces-redirect=true";
    }

    public void imprime() {
        System.out.println("Id:" + campanha.getId() + " / " + campanhaSelecionada.getId());
        System.out.println("Nome:" + campanha.getNome());
        System.out.println("Descrição:" + campanha.getDescricao());
        System.out.println("Habilitada" + campanha.getHabilitada());
        System.out.println("Data Fim" + campanha.getDataFim());
        System.out.println("Data Cadastro" + campanha.getCadastro());
    }

    public List<Campanha> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(List<Campanha> campanhas) {
        this.campanhas = campanhas;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
