package br.org.centrocac.entidade.tipo;

public enum TipoPerfilUsuario {

    ADMINISTRADOR('A', "Administrador"),
    DOADOR('C', "Colaborador"),
    COLABORADOR('U', "Usuário");

    private Character valor;
    private String descricao;

    private TipoPerfilUsuario(Character tipo, String descricao) {
        this.valor = tipo;
        this.descricao = descricao;
    }

    public static TipoPerfilUsuario obter(char valor) {
        TipoPerfilUsuario resposta = COLABORADOR;
        TipoPerfilUsuario[] values = values();
        for (TipoPerfilUsuario t : values) {
            if (t.getValor() == valor) {
                resposta = t;
                return resposta;
            }
        }
        return resposta;
    }

    public boolean isAdministrador() {
        return equals(ADMINISTRADOR);
    }

    public boolean isDoador() {
        return equals(DOADOR);
    }

    public boolean isColaborador() {
        return equals(COLABORADOR);
    }

    public Character getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

}
