package hotel;

public class Quarto {

    int numero = 0;
    int andar = 0;
    double preco = 0;
    String tipo = "";
    String descricao = "";
    String disponivel = "";// valores possíveis: "S" e "N"

    public void gravar() {
        new Conection().executeComand("INSERT INTO quartos(numero,andar,tipo,descricao,preco,disponivel) VALUES ("
                + numero + "," + andar + ",'" + tipo + "','" + descricao + "'," + preco + ",'S');", "Cadastro do quarto");
    }

    public void atualizar() {
        new Conection().executeComand("UPDATE quartos SET preco=" + preco + ",tipo='" + tipo + "',descricao='"
                + descricao + "',andar=" + andar + ",disponivel='" + disponivel + "' WHERE numero = '" + numero + "';", "Modificação do quarto");
    }

}
