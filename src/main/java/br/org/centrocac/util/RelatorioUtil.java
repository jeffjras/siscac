/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.util;

import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ByteArrayResource;

import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toshiaki
 */
public class RelatorioUtil {

    private FacesContext context;
    private HttpServletResponse response;
    private ByteArrayOutputStream baos;
    private Connection connection;
    private String SUBREPORT_TESTE;
    private String SUBREPORT_CAPA;
    private String SUBREPORT_CAPA_IMAGEM;
    private String SUBREPORT_CAPA_PARECERRELATORIO;
    private String SUBREPORT_DIDATICA;
    private String SUBREPORT_PESQUISA;
    private String SUB_SUBREPORT_PESQUISA;
    private String SUBREPORT_EXTENSAO;
    private String SUB_SUBREPORT_EXTENSAO;
    private String SUBREPORT_GESTAOEREPRESENTACAO;
    private String SUBREPORT_QUALIDADE;
    private String SUBREPORT_DISTRIBUICAOCH_E_AFASTAMENTO;
    //progressao
    private String SUBREPORT_PROGRESSAO_CAPA_IMG;
    private String SUBREPORT_PROGRESSAO_CAPA;
    private String SUBREPORT_PROGRESSAO_IDENTIFICACAO;
    //progressao2Radocs
    private String SUBREPORT_PROGRESSAO_ENSINO;
    private String SUBREPORT_PROGRESSAO_PESQUISA;
    private String SUBREPORT_PROGRESSAO_EXTENSAO;
    private String SUBREPORT_PROGRESSAO_GESTAO_E_REPRESENTACAO;
    private String SUBREPORT_PROGRESSAO_QUALIDADE;
    //progressao3Radocs
    private String SUBREPORT_PROGRESSAO_ENSINO3;
    private String SUBREPORT_PROGRESSAO_PESQUISA3;
    private String SUBREPORT_PROGRESSAO_EXTENSAO3;
    private String SUBREPORT_PROGRESSAO_GESTAO_E_REPRESENTACAO3;
    private String SUBREPORT_PROGRESSAO_QUALIDADE3;
    //progressao4Radocs
    private String SUBREPORT_PROGRESSAO_ENSINO4;
    private String SUBREPORT_PROGRESSAO_PESQUISA4;
    private String SUBREPORT_PROGRESSAO_EXTENSAO4;
    private String SUBREPORT_PROGRESSAO_GESTAO_E_REPRESENTACAO4;
    private String SUBREPORT_PROGRESSAO_QUALIDADE4;

    //progressao2Radocs
    private String SUBREPORT_PROGRESSAO_RESULTADO;

    public RelatorioUtil() {
        this.context = FacesContext.getCurrentInstance();
        this.response = (HttpServletResponse) context.getExternalContext().getResponse();
        this.SUBREPORT_TESTE = context.getExternalContext().getRealPath("WEB-INF/relatorios/teste2.jasper");
//        this.SUBREPORT_CAPA = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreports/radocCapa1.jasper");
//        this.SUBREPORT_CAPA_IMAGEM = context.getExternalContext().getRealPath("WEB-INF/relatorios/imagens/Brasão_ufra_mini.png");
//        this.SUBREPORT_CAPA_PARECERRELATORIO = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreports/radoc_ParecerRelatorio.jasper");
//        this.SUBREPORT_DIDATICA = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreports/radoc_Ensino.jasper");
//        this.SUBREPORT_PESQUISA = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreports/radoc_Pesquisa.jasper");
//        this.SUB_SUBREPORT_PESQUISA = context.getExternalContext().getRealPath("WEB-INF/relatorios/projetoPesquisaTemplate.jasper");
//        this.SUBREPORT_EXTENSAO = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreports/radoc_Extensao.jasper");
//        this.SUB_SUBREPORT_EXTENSAO = context.getExternalContext().getRealPath("WEB-INF/relatorios/projetoExtensaoTemplate.jasper");
//        this.SUBREPORT_GESTAOEREPRESENTACAO = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreports/radoc_Gestao_E_Representacao.jasper");
//        this.SUBREPORT_QUALIDADE = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreports/radoc_Qualificacao.jasper");
//        this.SUBREPORT_DISTRIBUICAOCH_E_AFASTAMENTO = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreports/radoc_AfastamentoEDistribuicaoDeCH.jasper");
//        //progressao
//        this.SUBREPORT_PROGRESSAO_CAPA_IMG = context.getExternalContext().getRealPath("WEB-INF/relatorios/imagens/ufra.png");
//        this.SUBREPORT_PROGRESSAO_CAPA = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao/progressao_CAPA.jasper");
//        this.SUBREPORT_PROGRESSAO_IDENTIFICACAO = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao/progressao_Identificacao.jasper");
//        //progressao2Radocs
//        this.SUBREPORT_PROGRESSAO_ENSINO = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao2Radocs/progressao_Ensino.jasper");
//        this.SUBREPORT_PROGRESSAO_PESQUISA = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao2Radocs/progressao_Pesquisa.jasper");
//        this.SUBREPORT_PROGRESSAO_EXTENSAO = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao2Radocs/progressao_Extensao.jasper");
//        this.SUBREPORT_PROGRESSAO_GESTAO_E_REPRESENTACAO = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao2Radocs/progressao_GestaoERepresentacao.jasper");
//        this.SUBREPORT_PROGRESSAO_QUALIDADE = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao2Radocs/progressao_Qualificacao.jasper");
//        //progressao3Radocs
//        this.SUBREPORT_PROGRESSAO_ENSINO3 = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao3Radocs/progressao_Ensino_3R.jasper");
//        this.SUBREPORT_PROGRESSAO_PESQUISA3 = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao3Radocs/progressao_Pesquisa_3R.jasper");
//        this.SUBREPORT_PROGRESSAO_EXTENSAO3 = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao3Radocs/progressao_Extensao_3R.jasper");
//        this.SUBREPORT_PROGRESSAO_GESTAO_E_REPRESENTACAO3 = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao3Radocs/progressao_GestaoERepresentacao_3R.jasper");
//        this.SUBREPORT_PROGRESSAO_QUALIDADE3 = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao3Radocs/progressao_Qualificacao_3R.jasper");
//        //progressao4Radocs
//        this.SUBREPORT_PROGRESSAO_ENSINO4 = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao4Radocs/progressao_Ensino_4R.jasper");
//        this.SUBREPORT_PROGRESSAO_PESQUISA4 = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao4Radocs/progressao_Pesquisa_4R.jasper");
//        this.SUBREPORT_PROGRESSAO_EXTENSAO4 = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao4Radocs/progressao_Extensao_4R.jasper");
//        this.SUBREPORT_PROGRESSAO_GESTAO_E_REPRESENTACAO4 = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao4Radocs/progressao_GestaoERepresentacao_4R.jasper");
//        this.SUBREPORT_PROGRESSAO_QUALIDADE4 = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao4Radocs/progressao_Qualificacao_4R.jasper");
//
//        //progressao2Radocs
//        this.SUBREPORT_PROGRESSAO_RESULTADO = context.getExternalContext().getRealPath("WEB-INF/relatorios/subreportsProgressao/progressao_Resultado.jasper");
    }

    public void gerarPDF(Integer id) {
        Map<String, Object> parametros = new HashMap<>();
        baos = new ByteArrayOutputStream();
        try {

            System.out.println("oi");
            System.out.println(context.getExternalContext().getRealPath("/WEB-INF/relatorios/teste2.jrxml"));
            //caminho generico para todos os relatorios jrxml
            String jrxml_path = context.getExternalContext().getRealPath("/WEB-INF/relatorios/teste2.jrxml");
            //compila o relatório/ transforma o arquivo .jrxml para .jasper
            System.out.println("oi2");
            System.out.println(jrxml_path);
            System.out.println("oi3");
            System.out.println(SUBREPORT_TESTE);
            JasperReport RELATORIO_COPILADO_PARA_JASPER = JasperCompileManager.compileReport(jrxml_path);
            //executa o relatório
            JasperPrint impressao = JasperFillManager.fillReport(RELATORIO_COPILADO_PARA_JASPER, parametros, getConexao());

            //pega os dados que estao no relatorio copilado e joga para o array de Bytes
            JasperExportManager.exportReportToPdfStream(impressao, baos);

            //limpa os possiveis dados que estejam no response
            response.reset();

            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            // essa config(inline) faz com que o pdf se abra no proprio browser.Para mudar para download basta mudar de 'inline' para 'attachment'
            response.setHeader("Content-disposition", "inline; filename=relatorio.pdf");

            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();

            //avisa ao jsf que a resposta da requisição ja esta pronta
            context.responseComplete();

            fecharConexao();

            System.out.println("FOI GERADO O PDF ");
        } catch (JRException | IOException e) {
            throw new RuntimeException("Erro ao gerar Relatorio PDF", e);
        }

    }

    public void gerarPDFProgressao(Integer id, Integer qtdRadocs) {
        String jrxml_path = "";
        Map<String, Object> parametros = new HashMap<>();
        //progressao
        parametros.put("paramProgressaoPrincipalId", id);//feito
        parametros.put("SUBREPORT_PROGRESSAO_CAPA_IMG", SUBREPORT_PROGRESSAO_CAPA_IMG);//feito
        parametros.put("SUBREPORT_PROGRESSAO_CAPA", SUBREPORT_PROGRESSAO_CAPA);//feito
        parametros.put("SUBREPORT_PROGRESSAO_IDENTIFICACAO", SUBREPORT_PROGRESSAO_IDENTIFICACAO);//feito
        //progressao2Radocs
        if (qtdRadocs.equals(2)) {
            parametros.put("SUBREPORT_PROGRESSAO_ENSINO", SUBREPORT_PROGRESSAO_ENSINO);//feito
            parametros.put("SUBREPORT_PROGRESSAO_PESQUISA", SUBREPORT_PROGRESSAO_PESQUISA);//feito
            parametros.put("SUBREPORT_PROGRESSAO_EXTENSAO", SUBREPORT_PROGRESSAO_EXTENSAO);//feito
            parametros.put("SUBREPORT_PROGRESSAO_GESTAOEREPRESENTACAO", SUBREPORT_PROGRESSAO_GESTAO_E_REPRESENTACAO);//feito
            parametros.put("SUBREPORT_PROGRESSAO_QUALIDADE", SUBREPORT_PROGRESSAO_QUALIDADE);//feito
        }
        //progressao3Radocs
        if (qtdRadocs.equals(3)) {
            parametros.put("SUBREPORT_PROGRESSAO_ENSINO", SUBREPORT_PROGRESSAO_ENSINO3);//feito
            parametros.put("SUBREPORT_PROGRESSAO_PESQUISA", SUBREPORT_PROGRESSAO_PESQUISA3);//feito
            parametros.put("SUBREPORT_PROGRESSAO_EXTENSAO", SUBREPORT_PROGRESSAO_EXTENSAO3);//feito
            parametros.put("SUBREPORT_PROGRESSAO_GESTAOEREPRESENTACAO", SUBREPORT_PROGRESSAO_GESTAO_E_REPRESENTACAO3);//feito
            parametros.put("SUBREPORT_PROGRESSAO_QUALIDADE", SUBREPORT_PROGRESSAO_QUALIDADE3);//feito
        }
        //progressao4Radocs
        if (qtdRadocs.equals(4)) {
            parametros.put("SUBREPORT_PROGRESSAO_ENSINO", SUBREPORT_PROGRESSAO_ENSINO4);//feito
            parametros.put("SUBREPORT_PROGRESSAO_PESQUISA", SUBREPORT_PROGRESSAO_PESQUISA4);//feito
            parametros.put("SUBREPORT_PROGRESSAO_EXTENSAO", SUBREPORT_PROGRESSAO_EXTENSAO4);//feito
            parametros.put("SUBREPORT_PROGRESSAO_GESTAOEREPRESENTACAO", SUBREPORT_PROGRESSAO_GESTAO_E_REPRESENTACAO4);//feito
            parametros.put("SUBREPORT_PROGRESSAO_QUALIDADE", SUBREPORT_PROGRESSAO_QUALIDADE4);//feito
        }
        //progressao
        parametros.put("SUBREPORT_PROGRESSAO_RESULTADO", SUBREPORT_PROGRESSAO_RESULTADO);//feito
        baos = new ByteArrayOutputStream();
        try {

            //caminho generico para todos os relatorios jrxml
            if (qtdRadocs.equals(2)) {
                jrxml_path = context.getExternalContext().getRealPath("WEB-INF/relatorios/progressao.jrxml");
            }
            if (qtdRadocs.equals(3)) {
                jrxml_path = context.getExternalContext().getRealPath("WEB-INF/relatorios/progressao3R.jrxml");
            }
            if (qtdRadocs.equals(4)) {
                jrxml_path = context.getExternalContext().getRealPath("WEB-INF/relatorios/progressao4R.jrxml");
            }
            
            //compila o relatório/ transforma o arquivo .jrxml para .jasper
            JasperReport RELATORIO_COPILADO_PARA_JASPER = JasperCompileManager.compileReport(jrxml_path);

            //executa o relatório
            JasperPrint impressao = JasperFillManager.fillReport(RELATORIO_COPILADO_PARA_JASPER, parametros, getConexao());

            //pega os dados que estao no relatorio copilado e joga para o array de Bytes
            JasperExportManager.exportReportToPdfStream(impressao, baos);

            //limpa os possiveis dados que estejam no response
            response.reset();

            response.setContentType("application/pdf");
            response.setContentLength(baos.size());

            // essa config(inline) faz com que o pdf se abra no proprio browser.Para mudar para download basta mudar de 'inline' para 'attachment'
            response.setHeader("Content-disposition", "inline; filename=relatorio.pdf");

            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();

            //avisa ao jsf que a resposta da requisição ja esta pronta
            context.responseComplete();

            fecharConexao();

            System.out.println("FOI GERADO O PDF ");
        } catch (JRException | IOException e) {
            throw new RuntimeException("Erro ao gerar Relatorio PDF", e);
        }

    }

    public Connection getConexao() {
        try {
            // Necessario para fazer um pré carregamento do drive do mysql
            Class.forName("com.mysql.jdbc.Driver");
            // Trocar o usuario e senha caso necessário
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/donazione?autoReconnect=true", "root", "root");
            return connection;
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelatorioUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public void fecharConexao() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
