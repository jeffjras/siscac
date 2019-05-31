/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.bean;

import br.org.centrocac.boletofacilsdk.BoletoFacil;
import br.org.centrocac.boletofacilsdk.enums.BoletoFacilEnvironment;
import br.org.centrocac.boletofacilsdk.exceptions.BoletoFacilException;
import br.org.centrocac.boletofacilsdk.model.entities.Charge;
import br.org.centrocac.boletofacilsdk.model.entities.Payer;
import br.org.centrocac.boletofacilsdk.model.response.ChargeResponse;
import br.org.centrocac.entidade.Campanha;
import br.org.centrocac.entidade.Doacao;
import br.org.centrocac.entidade.ItemCampanha;
import br.org.centrocac.entidade.ItemDoacao;
import br.org.centrocac.rn.CampanhaRN;
import br.org.centrocac.rn.DoacaoRN;
import br.org.centrocac.util.UtilBean;
import com.mysql.cj.util.Util;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ANDRE
 */
@ManagedBean
@ViewScoped
public class ItemDoacaoAdminBean {

    private String idDoacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

    private ItemDoacao itemDoacao = new ItemDoacao();
    private Campanha campanha = null;
    private ItemCampanha itemCampanha = null;

    //listas
    private List<Campanha> campanhas = new ArrayList();
    private List<ItemDoacao> itensDoacao = new ArrayList();

    //entidades
    private Doacao doacao = new Doacao();

    //RN
    private DoacaoRN doacaoRN = new DoacaoRN();
    private CampanhaRN campanhaRN = new CampanhaRN();

    //BoletoFacil
    private BoletoFacil boletoFacil;
    private String linkBoleto1;
    
    String token = "35DE84C75A0579F0350F04342BE73A76A28376AAD9F8A7A390A8E4DFB6FCABC8";
    
    private String linkBoleto;
    
    @PostConstruct
    public void init() {
        doacao = doacaoRN.obter(Integer.parseInt(idDoacao));
        campanhas = campanhaRN.obterTodos();
        itemDoacao.setDoacao(doacao);
        itemDoacao.setValor(BigDecimal.ZERO);
        System.out.println("oi 1");
        System.out.println(doacao.getId().toString());
    }

    public void setarCampanha() {
        System.out.println("entrar setar campanha");
        if (itemDoacao.getCampanha() != null) {
            //UtilBean.criarMensagemDeAviso("campanha:" + itemDoacao.getCampanha().getNome());
            System.out.println("campanha:" + itemDoacao.getCampanha().getNome());
        }
    }

    public void salvarDoacao() throws IOException {
        System.out.println("oi2");
        System.out.println(doacao.getItemDoacaoList());
        if (campanha != null) {
            System.out.println("campanha diferente de null condição");
            itemDoacao.setCampanha(campanha);
            itemDoacao.setItemCampanha(itemCampanha);

        }
        itensDoacao = doacao.getItemDoacaoList();
        itensDoacao.add(itemDoacao);
        doacao.setTotal(doacao.getTotal().add(itemDoacao.getValor()));
        System.out.println("campanha :" + itemDoacao.getCampanha());

        doacao.setItemDoacaoList(itensDoacao);
        if (campanha != null) {
            if (itemDoacao.getValor().doubleValue() >= campanha.getDoacaoMinima().doubleValue() || itemDoacao.getQtde() > 0) {
                if (doacaoRN.salvar(doacao)) {
                	
                	boletoFacil = new BoletoFacil(BoletoFacilEnvironment.SANDBOX, token);				
                	Payer payer = new Payer();
            		payer.setName(doacao.getColaborador().getNome());
            		payer.setCpfCnpj(doacao.getColaborador().getCpfOuCnpj());

            		Charge charge = new Charge();
            		charge.setDescription("Boleto de Doação ao CAC");
            			//	+ " "+itemDoacao.getCampanha().getNome() +" Com a descriçao: "+itemDoacao.getDescricao()+" em: "+ new Date(System.currentTimeMillis()));
            		charge.setAmount(itemDoacao.getValor());
            		charge.setPayer(payer);
            		charge.setPaymentTypes("BOLETO,CREDIT_CARD");

            		try {
            			ChargeResponse response = boletoFacil.issueCharge(charge);			
            			if (response.isSuccess()) {
            				for (Charge c : response.getData().getCharges()) {
            					System.out.println("");
            					System.out.println(c);    					
            					setLinkBoleto(c.getCheckoutUrl()); //getLink();
            					System.out.println(getLinkBoleto());
            				}
            			}			
            		} catch (BoletoFacilException e) {
            			System.out.println(e.getMessage());
            		} catch (Exception e) {
            			e.printStackTrace();
            		}
            		UtilBean.criarMensagemDeInformacao("Doação salva com sucesso!");
            		linkBoleto1 = getLinkBoleto();
            		UtilBean.criarMensagemDeAviso("Pagamento disponivel em: ",getLinkBoleto()); //getLinkBoleto()
            		
//            		FacesContext.getCurrentInstance().getExternalContext().redirect(getLinkBoleto());
	
                	
                	
                    UtilBean.criarMensagemDeInformacao("Doação salva com sucesso!");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/doacao.xhtml");
                } else {
                    UtilBean.criarMensagemDeErro("erro ao salvar");
                }
            } else {
                UtilBean.criarMensagemDeAviso("Valor mínimo de doação para esta campanha é de " + campanha.getDoacaoMinima().toString());
            }
        } else {
            if (doacaoRN.salvar(doacao)) {
            	boletoFacil = new BoletoFacil(BoletoFacilEnvironment.SANDBOX, token);				
            	Payer payer = new Payer();
        		payer.setName(doacao.getColaborador().getNome());
        		payer.setCpfCnpj(doacao.getColaborador().getCpfOuCnpj());

        		Charge charge = new Charge();
        		charge.setDescription("Boleto de Doação ao CAC");
        			//	+ " "+itemDoacao.getCampanha().getNome() +" Com a descriçao: "+itemDoacao.getDescricao()+" em: "+ new Date(System.currentTimeMillis()));
        		charge.setAmount(itemDoacao.getValor());
        		charge.setPayer(payer);
        		charge.setPaymentTypes("BOLETO,CREDIT_CARD");

        		try {
        			ChargeResponse response = boletoFacil.issueCharge(charge);			
        			if (response.isSuccess()) {
        				for (Charge c : response.getData().getCharges()) {
        					System.out.println("");
        					System.out.println(c);    					
        					setLinkBoleto(c.getCheckoutUrl()); //getLink();
        					System.out.println(getLinkBoleto());
        				}
        			}			
        		} catch (BoletoFacilException e) {
        			System.out.println(e.getMessage());
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        		UtilBean.criarMensagemDeInformacao("Doação salva com sucesso!");
        		System.out.println("link " + getLinkBoleto());
        		linkBoleto1 = getLinkBoleto();
        		UtilBean.criarMensagemDeAviso("Pagamento disponivel em: ",getLinkBoleto()); //getLinkBoleto()
        		
//        		FacesContext.getCurrentInstance().getExternalContext().redirect(getLinkBoleto());

            	
                UtilBean.criarMensagemDeInformacao("Doação salva com sucesso!");
                FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/doacao.xhtml");
            } else {
                UtilBean.criarMensagemDeErro("erro ao salvar");
            }
        }

    }

    public String goToDoacao() {
        return "doacao.xhtml??faces-redirect=true";
    }

    public String getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(String idDoacao) {
        this.idDoacao = idDoacao;
    }

    public ItemDoacao getItemDoacao() {
        return itemDoacao;
    }

    public void setItemDoacao(ItemDoacao itemDoacao) {
        this.itemDoacao = itemDoacao;
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

    public String getLinkBoleto() {
		return linkBoleto;
	}

	public void setLinkBoleto(String linkBoleto) {
		this.linkBoleto = linkBoleto;
	}

	public String getLinkBoleto1() {
		return linkBoleto1;
	}

	public void setLinkBoleto1(String linkBoleto1) {
		this.linkBoleto1 = linkBoleto1;
	}

    public ItemCampanha getItemCampanha() {
        return itemCampanha;
    }

    public void setItemCampanha(ItemCampanha itemCampanha) {
        this.itemCampanha = itemCampanha;
    }
        
        
	
}
