package hotel;

public class Pagamento {

    int idPagamento = 0;
    double valor = 0;
    String descricao = "";
    String Fpagamento = "";
    String data = "";

    public void gravar() {
        new Conection().executeComand("INSERT INTO pagamento(descricao,valor ,Fpagamento ,data ) VALUES ('"
                + descricao + "'," + valor + ",'" + Fpagamento + "','" + data + "');", "Cadastro do pagamento");
    }

    public void atualizar() {
        new Conection().executeComand("UPDATE pagamento SET valor = " + valor
                + ",Fpagamento = '" + Fpagamento + "',descricao = '" + descricao
                + "',data = '" + data + "' WHERE idPagamento = " + idPagamento + " ;", "Modificação do pagamento");
    }
}
