/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.rn;

import br.org.centrocac.dao.ColaboradorDAO;
import br.org.centrocac.entidade.Acao;
import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.util.DonazioneUtil;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author BPMLAB-02
 */
public class ColaboradorRN {

    private final ColaboradorDAO COLABORADOR_DAO = new ColaboradorDAO();

    public boolean salvar(Colaborador colaborador) {
        if (colaborador == null) {
            return false;
        } else {
            if (colaborador.getId() == null || colaborador.getId() == 0) {
                colaborador.setSenha(DonazioneUtil.encriptarSHA256(colaborador.getSenha()));
                return COLABORADOR_DAO.criar(colaborador);
            } else {
                colaborador.setSenha(DonazioneUtil.encriptarSHA256(colaborador.getSenha()));
                return COLABORADOR_DAO.alterar(colaborador);
            }
        }
    }

    public List<Colaborador> listar() {
        return COLABORADOR_DAO.obterTodos(Colaborador.class);
    }

    public boolean excluir(Colaborador colaborador) {
        if (colaborador == null || colaborador.getId() == null) {
            return false;
        } else {
            return COLABORADOR_DAO.excluir(colaborador);
        }
    }

    public List<Colaborador> obterTodos() {
        return COLABORADOR_DAO.obterTodos(Colaborador.class);
    }

    public List<Colaborador> obterTodosColaboradoresDisponiveisParACAO(Integer id) {
        return COLABORADOR_DAO.obterTodosVoluntariosDisponiveisParaAcao(id);
    }

    public Colaborador obter(Integer id) {
        return COLABORADOR_DAO.obter(Colaborador.class, id);
    }

    public Colaborador obter(String email) {
        return COLABORADOR_DAO.obter(email);
    }

    public boolean autenticar(String email, String senha) {
        return COLABORADOR_DAO.autenticar(email, senha);
    }

    public List<Colaborador> obterTodosVoluntariosPorAcao(Acao acao) {
        System.out.println(acao);
        List<Colaborador> resposta = new ArrayList<>();
        if (acao == null) {
            System.out.println("aco null");
            resposta = COLABORADOR_DAO.obterTodosVoluntarios();
        } else {
            System.out.println("aco not null");
            resposta = COLABORADOR_DAO.obterTodosVoluntariosPorAcao(acao);
        }
        return resposta;
    }
}
