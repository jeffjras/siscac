/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.rn;
import br.org.centrocac.dao.DoacaoDAO;
import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.entidade.Doacao;
import java.util.Date;
import java.util.List;

/**
 *
 * @author toshiaki
 */
public class DoacaoRN {

    private final DoacaoDAO dao = new DoacaoDAO();

    public boolean salvar(Doacao obj) {
        if (obj == null) {
            return false;
        } else {
            if (obj.getId() == null || obj.getId() == 0) {
                return dao.criar(obj);
            } else {
                return dao.alterar(obj);
            }
        }
    }

    public boolean excluir(Doacao obj) {
        if (obj == null || obj.getId() == null) {
            return false;
        } else {
            return dao.excluir(obj);
        }
    }

    public List<Doacao> obterTodos() {
        return dao.obterTodos(Doacao.class);
    }

    public Doacao obter(Integer id) {
        // return dao.obterDoacao(id);
        return dao.obter(Doacao.class,id);
    }
    
    public List<Doacao> obterDoacoesPorColaborador(Colaborador colaborador){
        return dao.obterDoacoesPorColaborador(colaborador);
    }
    
    public Boolean existeDoacao(Integer colaborador, Date cadastro){
        return dao.existeDoacao(colaborador, cadastro);
    }
    
    public Doacao obeterPorColaboradorCadastro(Integer idcolaborador, Date cadastro){
        return dao.obterPorColaboradorCadastro(idcolaborador, cadastro);
    }

    public List<Doacao> pesquisarEntreDatas(Date dataInicio, Date dataFim) {
        return dao.obterDoacoesEntreDatas(dataInicio, dataFim);
    }

}
