
package br.org.centrocac.dao;

import br.org.centrocac.entidade.Campanha;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
    public List<Campanha> obter(Date inicio, Date fim, Boolean habilitada) {
        EntityManager em = getEntityManager();
        String sql = "select c from Campanha c "
                + "where "
                + "c.dataFim >= :inicio and "
                + "c.dataFim <= :fim and "
                + "c.habilitada = :habilitada ";
        Query query = em.createQuery(sql);
        List<Campanha> resposta = null;
        try {
            resposta = (List<Campanha>) query.
                    setParameter("inicio", inicio).
                    setParameter("fim", fim).
                    setParameter("habilitada", habilitada).
                    getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public List<Campanha> obterTodos(Boolean habilitada) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select c from Campanha c where c.habilitada = :habilitada").
                setParameter("habilitada", habilitada);
        List<Campanha> resposta = null;
        try {
            resposta = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        return resposta;
    }

    public void atualizar(Campanha c) {
        EntityManager em = getEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.merge(c);
            t.commit();
        } catch (Exception e) {
            System.out.println("error atulizar campanha");
            e.printStackTrace(System.err);
            if (t != null && t.isActive()) {
                t.rollback();
            }
        } finally {
            em.close();
        }
    }

}
