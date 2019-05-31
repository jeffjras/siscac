/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.dao;

import br.org.centrocac.entidade.Acao;
import br.org.centrocac.entidade.Campanha;
import br.org.centrocac.entidade.Colaborador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author toshiaki
 */
public class AcaoDAO extends GenericDAO<Acao> {
 
    public List<Acao> obterTodosAcaoPorVoluntario(Colaborador voluntario) {
        EntityManager em = getEntityManager();
        String sql = "SELECT * FROM acao a INNER JOIN colaborador_acao ca on ca.acao_id = a.id where ca.colaborador_id = :voluntarioId";
        Query query = em.createNativeQuery(sql);
        List<Acao> resposta = null;

        try {
            resposta = (List<Acao>) query.setParameter("voluntarioId", voluntario.getId()).getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
    
    public List<Acao> obterTodosAcoes() {
        EntityManager em = getEntityManager();
        String sql = "SELECT * FROM acao a INNER JOIN colaborador_acao ca on ca.acao_id = a.id";
        Query query = em.createNativeQuery(sql, Acao.class);
        List<Acao> resposta = null;

        try {
            resposta = (List<Acao>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public List<Acao> obterTodosAcoesPorVoluntario(Colaborador voluntario) {
        EntityManager em = getEntityManager();
        String sql = "SELECT * FROM acao a INNER JOIN colaborador_acao ca on ca.acao_id = a.id where ca.colaborador_id = ?";
        Query query = em.createNativeQuery(sql, Acao.class);
        query.setParameter(1, voluntario.getId());
        List<Acao> resposta = null;

        try {
            resposta = (List<Acao>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
    
    public List<Acao> obterTodosAcoesDisponiveisParaCampanha(Integer campanhaId) {
        EntityManager em = getEntityManager();
        String sql = "SELECT  *  FROM  acao a  left JOIN  campanha_acao ca on ca.acao_id = a.id where a.id not in (SELECT ac.id FROM acao ac Inner join campanha_acao ca on ca.acao_id = ac.id where ca.campanha_id = ?)";
        Query query = em.createNativeQuery(sql, Acao.class);
        query.setParameter(1, campanhaId);
        List<Acao> resposta = null;

        try {
            resposta = (List<Acao>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
}
