package hotel;

public class Reserva {

    int idReserva = 0;
    int id_hospede = 0;
    int num_quarto = 0;
    double consumo = 0;
    String dataEntrada = "";//datetime
    String dataSaida = "";//datetime
    String estado = "";//10 caracteres

    public void gravar() {

        if (new Conection().quartoLivre(num_quarto, dataEntrada, dataSaida)) {
            new Conection().executeComand("INSERT INTO reservas(id_hospede ,num_quarto ,dataEntrada ,dataSaida ,estado ,consumo) VALUES ("
                    + id_hospede + "," + num_quarto + ",'" + dataEntrada + "','" + dataSaida + "','Reservado'," + consumo + ");", "Cadastro da reserva");
            new Conection().executeComand("UPDATE quartos SET disponivel = 'N' WHERE numero = " + num_quarto + ";", "");
            System.out.println("Gravou a reserva");
        } else {
            System.out.println("Não gravaou a reserva");
        }
    }

    public void atualizar() {
        if (estado.equals("Concluído")) {
            new Conection().executeComand("UPDATE quartos SET disponivel = 'S' WHERE numero = " + num_quarto + ";", "");
        }
        new Conection().executeComand("UPDATE reservas SET dataEntrada='" + dataEntrada + "',dataSaida='" + dataSaida + "',consumo="
                + consumo + ",estado='" + estado + "',num_quarto='" + num_quarto + "',id_hospede='" + id_hospede
                + "' WHERE idReserva = " + idReserva + ";", "Modificação da reserva");
    }
}
