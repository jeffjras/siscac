/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.dao;

import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.entidade.Doacao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

/**
 *
 * @author fabio
 */
public class DoacaoDAO extends GenericDAO<Doacao> {
    
    public Doacao obterDoacao(Integer id){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select d from Doacao d where d.id = :id");
        query.setParameter("id", id);
        query.getResultList();
        Doacao d = new Doacao();
        
        return d;        
    }

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
    
    public Boolean existeDoacao(Integer idcolaborador, Date cadastro){
        Boolean existe;
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select d from Doacao d where d.colaborador.id = :idColaborador and d.cadastro = :cadastro");
        List<Doacao> resposta = new ArrayList();
        try {
            resposta = query.setParameter("idColaborador", idcolaborador)
                    .setParameter("cadastro", cadastro,TemporalType.DATE)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        if (resposta.size() >= 1){
            existe = true;
        } else {
            existe = false;
        }
        for (Doacao doacao : resposta) {
            System.out.println("index: " + resposta.indexOf(doacao) + " " + doacao.getId().toString()  );
        }
        return existe;
    }

    public Doacao obterPorColaboradorCadastro(Integer idcolaborador, Date cadastro) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select d from Doacao d where d.colaborador.id = :idColaborador and d.cadastro = :cadastro");
        List<Doacao> resposta = new ArrayList();
        try {
            resposta = query.setParameter("idColaborador", idcolaborador)
                    .setParameter("cadastro", cadastro,TemporalType.DATE)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            em.close();
        }
        Doacao d = new Doacao();
        for (Doacao doacao : resposta) {
            d = doacao;
            System.out.println("index: " + resposta.indexOf(doacao) + " " + doacao.getId().toString()  );
        }
        return d;
    }
}
