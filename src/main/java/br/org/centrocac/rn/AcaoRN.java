/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.rn;

import br.org.centrocac.dao.AcaoDAO;
import br.org.centrocac.entidade.Acao;
import java.util.List;

/**
 *
 * @author toshiaki
 */
public class AcaoRN {
    private final AcaoDAO ACAO_DAO = new AcaoDAO();

    public boolean salvar(Acao obj) {
        if (obj == null) {
            return false;
        } else {
            if (obj.getId() == null || obj.getId() == 0) {
                return ACAO_DAO.criar(obj);
            } else {
                return ACAO_DAO.alterar(obj);
            }
        }
    }

    public boolean excluir(Acao obj) {
        if (obj == null || obj.getId() == null) {
            return false;
        } else {
            return ACAO_DAO.excluir(obj);
        }
    }

    public List<Acao> obterTodos() {
        return ACAO_DAO.obterTodos(Acao.class);
    }

    public Acao obter(Integer id) {
        return ACAO_DAO.obter(Acao.class, id);
    }

}
