package hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class Conection {

    String nameClass;
    String userClass;
    String passDB = "";			//senha do banco de dados
    String userDB = "root";		//usuário do banco de dados
    String nameDB = "hotel";	//nome do banco de dados
    String ipDB = "127.0.0.1";	//ip do banco de dados
    String portDB = "3306";		//porta do ip do banco de dados
    

    //==========-----EXECUTA COMANDOS SQL SEM RETORNO(INSERT,UPDATE,DELETE,etc.)-----==========\\
    public void executeComand(String comand, String msg) /*throws SQLException*/ {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://" + ipDB + ":"
                    + portDB + "/" + nameDB + "?useTimezone=true&serverTimezone=UTC", userDB, passDB)) {
                Statement stmt = con.createStatement();
                stmt.execute(comand);
                if (msg.equals("")) {
                    System.out.println("Conexão efetuada sem mensagem!");
                } else if (msg.length() > 0) {
                    System.out.println("Conexão efetuada com mensagem!");
                    if (msg.contains("Cadastro")) {
                        MessageStatus m = new MessageStatus(userClass, nameClass, msg + " efetuado com sucesso.", "sucesso");
                        m.setLocationRelativeTo(null);
                        m.setVisible(true);
                    } else if (msg.contains("Modificação") || msg.contains("Exclusão")) {
                        MessageStatus m = new MessageStatus(userClass, nameClass, msg + " efetuada com sucesso.", "sucesso");
                        m.setLocationRelativeTo(null);
                        m.setVisible(true);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLIntegrityConstraintViolationException e) {
            //JOptionPane.showMessageDialog(null, e);
            System.out.println("Erro! campo duplicado:\n" + e);
            if (msg.equals("")) {
            } else if (msg.length() > 0) {
                MessageStatus m = new MessageStatus(userClass, nameClass, "Erro ao efetuar " + msg.toLowerCase() + ". Já cadastrado.", "erro");
                m.setLocationRelativeTo(null);
                m.setVisible(true);
            } else {
                MessageStatus m = new MessageStatus(userClass, nameClass, "Erro inesperado.", "erro");
                m.setLocationRelativeTo(null);
                m.setVisible(true);
            }
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, e);
            System.out.println("Erro inesperado: " + e);
            if (msg.equals("")) {
            } else if (msg.length() > 0) {

            } else {
                MessageStatus m = new MessageStatus(userClass, nameClass, "Erro inesperado.", "erro");
                m.setLocationRelativeTo(null);
                m.setVisible(true);
            }
        }
    }

    //==========-----SELECT DE NOME DO HÓSPEDE NA TABEL ADE RESERVA(recebe o idReserva como parâmetro)-----==========\\
    public String nameHospReserva(String id) {
        String nome = "Erro";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://" + ipDB + ":"
                    + portDB + "/" + nameDB + "?useTimezone=true&serverTimezone=UTC", userDB, passDB)) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT nome FROM reservas,hospedes WHERE id_hospede = idHospede AND id_hospede = " + id + ";");

                while (rs.next()) {
                    nome = rs.getString("nome");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return nome;
    }

    //==========-----SELECT DE NOME DO HÓSPEDE NA TABEL ADE RESERVA(recebe o idReserva como parâmetro)-----==========\\
    public boolean quartoLivre(int n, String dataEntrada, String dataSaida) {
        String numero = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://" + ipDB + ":"
                    + portDB + "/" + nameDB + "?useTimezone=true&serverTimezone=UTC", userDB, passDB)) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT num_quarto, dataEntrada, dataSaida FROM reservas,quartos WHERE dataEntrada >= '" + dataEntrada + " 00:00:00' AND dataSaida <= '" + dataSaida + " 00:00:00' AND num_quarto = " + n + ";");

                while (rs.next()) {
                    numero = rs.getString("num_quarto");    //    Se tiver alguma resposta de que o quarto já foi alugado,
                }
            }                                           //  o numero do quarto será colocado na string, que será verificada no if abaixo
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        if (numero.equals("")) {//quando o quarto está livre
            //quando o quarto está alugado
            return true;
        } else {//quando o quarto não está livre
            MessageStatus m = new MessageStatus(userClass, nameClass, "O quarto " + numero + " está indisponível.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
            return false;
        }
    }

    //==========-----SELECT DE HOSPEDES-----=========v\\
    public Hospede[] selectHospedes(String comand) {
        Hospede[] hpd = new Hospede[1000];
        int cont = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://" + ipDB + ":"
                    + portDB + "/" + nameDB + "?useTimezone=true&serverTimezone=UTC", userDB, passDB)) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(comand);

                while (rs.next()) {
                    Hospede h = new Hospede();
                    h.idHospede = rs.getInt("idHospede");
                    h.nome = rs.getString("nome");
                    h.sexo = rs.getString("sexo");
                    h.nascimento = new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("nascimento"));
                    h.cidade = rs.getString("cidade");
                    h.cpf = rs.getString("cpf");
                    h.rg = rs.getString("rg");
                    h.telefone = rs.getString("telefone");
                    hpd[cont] = h;
                    cont++;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return hpd;
    }

    //==========-----SELECT DE FUNCIONARIOS-----==========\\
    public Funcionario[] selectFuncionarios(String comand) {
        Funcionario[] fcnr = new Funcionario[1000];
        int cont = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://" + ipDB + ":"
                    + portDB + "/" + nameDB + "?useTimezone=true&serverTimezone=UTC", userDB, passDB)) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(comand);

                while (rs.next()) {
                    Funcionario f = new Funcionario();
                    f.idFuncionario = rs.getInt("idFuncionario");
                    f.nome = rs.getString("nome");
                    f.sexo = rs.getString("sexo");
                    f.nascimento = new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("nascimento"));
                    f.endereco = rs.getString("endereco");
                    f.dataContratacao = new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dataContratacao"));
                    f.cargo = rs.getString("cargo");
                    f.cargaHoraria = rs.getString("cargaHoraria");
                    f.cpf = rs.getString("cpf");
                    f.rg = rs.getString("rg");
                    f.telefone = rs.getString("telefone");
                    f.email = rs.getString("email");
                    f.dataCadastro = new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dataCadastro"));
                    f.usuario = rs.getString("usuairo");
                    f.senha = rs.getString("senha");
                    fcnr[cont] = f;
                    cont++;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return fcnr;
    }

    //==========-----SELECT DE RESERVAS-----==========\\
    public Reserva[] selectReservas(String comand) {
        Reserva[] rsv = new Reserva[1000];
        int cont = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://" + ipDB + ":"
                    + portDB + "/" + nameDB + "?useTimezone=true&serverTimezone=UTC", userDB, passDB)) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(comand);

                while (rs.next()) {
                    Reserva r = new Reserva();
                    r.id_hospede = rs.getInt("id_hospede");
                    r.idReserva = rs.getInt("idReserva");
                    r.num_quarto = rs.getInt("num_quarto");
                    r.consumo = rs.getDouble("consumo");
                    r.estado = rs.getString("estado");
                    r.dataEntrada = new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dataEntrada"));
                    r.dataSaida = new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dataSaida"));
                    rsv[cont] = r;
                    cont++;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return rsv;
    }

    //==========-----SELECT DE LEMBRETES-----==========\\
    public Lembrete[] selectLembretes(String comand) {
        Lembrete[] lbt = new Lembrete[1000];
        int cont = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://" + ipDB + ":"
                    + portDB + "/" + nameDB + "?useTimezone=true&serverTimezone=UTC", userDB, passDB)) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(comand);

                while (rs.next()) {
                    Lembrete l = new Lembrete();
                    l.idLembrete = rs.getInt("idLembrete");
                    l.assunto = rs.getString("assunto");
                    l.descricao = rs.getString("descricao");
                    l.nomeUsuario = rs.getString("nomeUsuario");
                    l.data = new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("data"));
                    lbt[cont] = l;
                    cont++;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lbt;
    }

    //==========-----SELECT DE PAGAMENTOS-----==========\\
    public Pagamento[] selectPagamentos(String comand) {
        Pagamento[] pgm = new Pagamento[1000];
        int cont = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://" + ipDB + ":"
                    + portDB + "/" + nameDB + "?useTimezone=true&serverTimezone=UTC", userDB, passDB)) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(comand);

                while (rs.next()) {
                    Pagamento p = new Pagamento();
                    p.idPagamento = rs.getInt("idPagamento");
                    p.Fpagamento = rs.getString("Fpagamento");
                    p.descricao = rs.getString("descricao");
                    p.valor = rs.getDouble("valor");
                    p.data = new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("data"));
                    pgm[cont] = p;
                    cont++;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return pgm;
    }

    /*método básico para executar comandos(coloque uma barra depois do asterísco a seguir)*
    public void executaComando(String comando) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://" + ipDB + ":"
                + portDB + "/" + nameDB + "?useTimezone=true&serverTimezone=UTC", "root", "1234");
            Statement stmt = con.createStatement();
            stmt.execute(comando);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }/**/
}
