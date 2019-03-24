/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.dao;

import br.org.centrocac.entidade.Colaborador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
                + "and c.senha = :senha "
                + "and c.confirmacao is null";
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

}
