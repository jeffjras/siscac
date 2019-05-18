/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.rn;

import br.org.centrocac.dao.CampanhaDAO;
import br.org.centrocac.entidade.Campanha;
import java.util.Date;
import java.util.List;

/**
 *
 * @author toshiaki
 */
public class CampanhaRN {

    private final CampanhaDAO dao = new CampanhaDAO();

    public boolean salvar(Campanha obj) {
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

    public boolean excluir(Campanha obj) {
        if (obj == null || obj.getId() == null) {
            return false;
        } else {
            return dao.excluir(obj);
        }
    }

    public List<Campanha> obterTodos() {
        return dao.obterTodos(Campanha.class);
    }

    public Campanha obter(Integer id) {
        return dao.obter(Campanha.class, id);
    }

    public List<Campanha> obter(Date inicio, Date fim, boolean habilitado) {
        return dao.obter(inicio, fim, habilitado);
    }
}
