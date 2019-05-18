/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.dao;

import br.org.centrocac.entidade.Campanha;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author fabio
 */
public class CampanhaDAO extends GenericDAO<Campanha> {

    /**
     * Retorna as campanhas cuja data de encerramento estão no limite de datas
     * informadas (entre início e fim). Apenas campanhas habilitadas serão 
     * retornadas.
     *
     * @param inicio
     * @param fim
     * @return Lista de campanhas
     */
    public List<Campanha> obter(Date inicio, Date fim, boolean ativo) {
        EntityManager em = getEntityManager();
        String sql = "select c from Campanha c "
                + "where "
                + "c.dataFim between :inicio and :fim and "
                + "c.habilitada = :habilitada ";
        Query query = em.createQuery(sql);
        List<Campanha> resposta = null;
        try {
            resposta = (List<Campanha>) query.
                    setParameter("inicio", inicio).
                    setParameter("fim", fim).
                    setParameter("habilitada", ativo).
                    getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
}
