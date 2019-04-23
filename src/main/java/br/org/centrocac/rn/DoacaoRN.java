/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.rn;
import br.org.centrocac.dao.DoacaoDAO;
import br.org.centrocac.entidade.Doacao;
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
        return dao.obter(Doacao.class, id);
    }

}
