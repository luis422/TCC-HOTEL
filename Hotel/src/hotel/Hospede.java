package hotel;

public class Hospede {

    int idHospede = 0;
    String nome = "";
    String sexo = "";// valores possíveis: "F" e "M"
    String nascimento; //date mysql FORMATO PADRÃO É: AAAA-MM-DD
    String cidade = "";
    String cpf = "";
    String rg = "";
    String telefone = "";

    public void gravar() {
        new Conection().executeComand("INSERT INTO hospedes(nome,sexo,nascimento,cidade,cpf,rg,telefone) VALUES ('"
                + nome + "','" + sexo + "','" + nascimento + "','" + cidade + "','"
                + cpf + "','" + rg + "','" + telefone + "');", "Cadastro do hóspede");
    }

    public void atualizar() {
        new Conection().executeComand("UPDATE hospedes SET nome='" + nome + "',sexo='" + sexo + "',telefone='"
                + telefone + "',cidade='" + cidade + "' WHERE cpf = '" + cpf + "';", "Modificação do hóspede");
    }

}
