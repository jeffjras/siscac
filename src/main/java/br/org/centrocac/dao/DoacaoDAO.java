/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.dao;

import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.entidade.Doacao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author fabio
 */
public class DoacaoDAO extends GenericDAO<Doacao> {

    public List<Doacao> obter(Colaborador colaborador) {
        EntityManager em = getEntityManager();
        String sql = "select d from Doacao d "
                + "where "
                + "d.colaborador = :colaborador";
        Query query = em.createQuery(sql);
        List<Doacao> resposta = null;
        try {
            resposta = (List<Doacao>) query.
                    setParameter("colaborador", colaborador).
                    getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
}
