package hotel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Funcionario {

    int idFuncionario = 0;
    String nome = "";
    String sexo = "";//enum no mysql, valores possíveis: "F" e "M"
    String nascimento = "";//date mysql FORMATO PADRÃO É: "AAAA-MM-DD"
    String endereco = "";
    String dataContratacao = "";//date mysql FORMATO PADRÃO É: "AAAA-MM-DD"
    String cargo = "";
    String cargaHoraria = "";
    String cpf = "";
    String rg = "";
    String telefone = "";
    String email = "";
    String dataCadastro = "";//datetime no mysql FORMATO PADRÃO É "AAAA-MM-DD HH:MM:SS"
    String usuario = "";
    String senha = "";

    public void gravar(String user, String name, int login) {
        String data = new SimpleDateFormat("yyyy-MM-dd").format(new Date());//pega a data do sistema no formato AAAA-MM-DD
        String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());//pega a hora do sistema no formato HH:MM:SS
        String dataCadFunc = data + " " + hora;//concatena data e hora respectivamente com um espaço entre

        switch (login) {
            case 1:
                new Conection().executeComand("INSERT INTO funcionarios (usuario,senha,nome,sexo,nascimento,endereco,dataContratacao,cargo,cargaHoraria,cpf,rg,telefone,email,dataCadastro)"
                        + " VALUES ('" + usuario + "','" + senha + "','" + nome + "','" + sexo + "','"
                        + nascimento + "','" + endereco + "','"
                        + dataContratacao + "','" + cargo + "'," + cargaHoraria + ",'"
                        + cpf + "','" + rg + "','" + telefone + "','" + email + "','" + dataCadFunc + "');", "Cadastro do funcionário");
                break;
            case 0:
                new Conection().executeComand("INSERT INTO funcionarios (usuario,senha,nome,sexo,nascimento,endereco,dataContratacao,cargo,cargaHoraria,cpf,rg,telefone,email,dataCadastro)"
                        + " VALUES (null,null,'" + nome + "','" + sexo + "','"
                        + nascimento + "','" + endereco + "','"
                        + dataContratacao + "','" + cargo + "'," + cargaHoraria + ",'"
                        + cpf + "','" + rg + "','" + telefone + "','" + email + "','" + dataCadFunc + "');", "Cadastro do funcionário");
                break;
            default:
                MessageStatus m = new MessageStatus(user, name, "Erro inesperado.", "erro");
                m.setLocationRelativeTo(null);
                m.setVisible(true);
                break;
        }
    }

    public void atualizar(String user, String name, int login) {

        switch (login) {
            case 1:
                new Conection().executeComand("UPDATE funcionarios SET nome='" + nome + "',endereco='" + endereco + "',"
                        + "sexo='" + sexo + "',email='" + email + "',telefone='" + telefone + "',cargaHoraria=" + cargaHoraria + ","
                        + "cargo='" + cargo + "',usuario='" + usuario + "',senha='" + senha + "' WHERE cpf = '" + cpf + "';", "Modificação do funcionário");
                break;
            case 0:
                new Conection().executeComand("UPDATE funcionarios SET nome='" + nome + "',endereco='" + endereco + "',"
                        + "sexo='" + sexo + "',email='" + email + "',telefone='" + telefone + "',cargaHoraria=" + cargaHoraria + ","
                        + "cargo='" + cargo + "' WHERE cpf = '" + cpf + "';", "Modificação do funcionário");
                break;
            default:
                MessageStatus m = new MessageStatus(user, name, "Erro inesperado.", "erro");
                m.setLocationRelativeTo(null);
                m.setVisible(true);
                break;
        }
    }

}
