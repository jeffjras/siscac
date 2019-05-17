/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.dao;

import br.org.centrocac.entidade.Acao;
import br.org.centrocac.entidade.Colaborador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author fabio
 */
public class ColaboradorDAO extends GenericDAO<Colaborador> {

    public Colaborador obter(String email) {
        EntityManager em = getEntityManager();
        String sql = "SELECT c from Colaborador c "
                + "where c.email = :email ";
        Query query = em.createQuery(sql);
        Colaborador resposta = null;

        try {

            List<Colaborador> colaboradores = (List<Colaborador>) query.setParameter("email", email).getResultList();
            if (colaboradores != null
                    && colaboradores.size() == 1) {
                resposta = colaboradores.get(0);
                em.refresh(resposta);
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public boolean autenticar(String email, String senha) {
        EntityManager em = getEntityManager();
        String sql = "Select c from Colaborador c "
                + "where c.email = :email "
                + "and c.senha = :senha ";
        //+ "and c.confirmacao is null";
        Query query = em.createQuery(sql);

        boolean resposta = Boolean.FALSE;

        try {
            List<Colaborador> colaborador = (List<Colaborador>) query
                    .setParameter("email", email)
                    .setParameter("senha", senha)
                    .getResultList();

            if (colaborador != null && colaborador.size() == 1) {
                resposta = Boolean.TRUE;
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public List<Colaborador> obterTodosVoluntarios() {
        EntityManager em = getEntityManager();
        String sql = "SELECT * FROM colaborador c INNER JOIN colaborador_acao ca on ca.colaborador_id = c.id";
        Query query = em.createNativeQuery(sql, Colaborador.class);
        List<Colaborador> resposta = null;

        try {
            resposta = (List<Colaborador>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public List<Colaborador> obterTodosVoluntariosPorAcao(Acao acao) {
        EntityManager em = getEntityManager();
        String sql = "SELECT * FROM colaborador c INNER JOIN colaborador_acao ca on ca.colaborador_id = c.id where ca.acao_id = ?";
        Query query = em.createNativeQuery(sql, Colaborador.class);
        query.setParameter(1, acao.getId());
        List<Colaborador> resposta = null;

        try {
            resposta = (List<Colaborador>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
    
    public List<Colaborador> obterTodosVoluntariosDisponiveisParaAcao(Integer id) {
        EntityManager em = getEntityManager();
        String sql = "SELECT distinct * FROM colaborador c where c.id not in (SELECT distinct c.id FROM colaborador c INNER JOIN colaborador_acao ca on ca.colaborador_id = c.id where ca.acao_id = ?)";
        Query query = em.createNativeQuery(sql, Colaborador.class);
        query.setParameter(1, id);
        List<Colaborador> resposta = null;

        try {
            resposta = (List<Colaborador>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }
}
