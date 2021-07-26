package hotel;

public class Lembrete {

    int idLembrete = 0;
    String nomeUsuario = "";
    String assunto = "";
    String data = "";
    String descricao = "";

    public void gravar(String user) {
        nomeUsuario = user;
        new Conection().executeComand("INSERT INTO lembrete(nomeUsuario,assunto,data,descricao) VALUES('"
                + nomeUsuario + "','" + assunto + "','" + data + "','" + descricao + "');", "Cadastro do lembrete");
    }

    public void atualizar() {
        new Conection().executeComand("UPDATE lembrete SET assunto='" + assunto + "',descricao='"
                + descricao + "',data='" + data + "' WHERE idLembrete = " + idLembrete + ";", "Modificação do lembrete");
    }

}
