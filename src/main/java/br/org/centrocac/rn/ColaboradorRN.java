/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.centrocac.rn;

import br.org.centrocac.dao.ColaboradorDAO;
import br.org.centrocac.entidade.Colaborador;
import br.org.centrocac.util.DonazioneUtil;
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

    public boolean excluir(Colaborador colaborador) {
        if (colaborador == null || colaborador.getId() == null) {
            return false;
        } else {
            return COLABORADOR_DAO.excluir(colaborador);
        }
    }

    List<Colaborador> obterTodos() {
        return COLABORADOR_DAO.obterTodos(Colaborador.class);
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

}
