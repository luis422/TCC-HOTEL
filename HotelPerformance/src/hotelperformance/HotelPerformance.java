package hotelperformance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class HotelPerformance {

    
    public static void main(String[] args) {

        for (int c = 0; c <= 1000000; c++) {
            Pagamento p = new Pagamento();
            p.Fpagamento = "Cartão de Crédito";
            p.data = "2002-08-01";
            p.descricao = "Descrição do pagamento " + c;
            p.valor = c * c / 2;

            executaComando("INSERT INTO pagamento(descricao,valor ,Fpagamento ,data ) VALUES ('"
                    + p.descricao + "'," + p.valor + ",'" + p.Fpagamento + "','" + p.data + "');");
            System.out.println("Cadastrando pagamento " + c);
        }
    }
    /*método básico para executar comandos(coloque uma barra depois do asterísco a seguir)*/
    public static void executaComando(String comando) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hotel?useTimezone=true&serverTimezone=UTC",
                    "root", "1234");
            Statement stmt = con.createStatement();//import java.sql.statement;
            stmt.execute(comando);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }/**/
        
    }
    
