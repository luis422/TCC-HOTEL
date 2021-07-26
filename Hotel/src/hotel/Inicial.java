package hotel;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Inicial extends javax.swing.JFrame {

    //I LOVE COFFEEEEEEEEEE!!!!
    String usuario = "Erro";
    String nome = "Erro";

    public Inicial(String user, String nome) {
        initComponentsAntes();
        initComponents();
        saudacao(nome);
        esconderPaiFilho();
        esconderBtnFuncoes();
        panelInicio.setVisible(true);
        usuario = user;
        logadoComoUsuario.setText(usuario);
        Image iconFrameInicial = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imgs/iconFrame.png"));
        setIconImage(iconFrameInicial);

        //thread do relogio
        Thread ThreadRelogio = new Thread(new Runnable() {
            @Override
            public void run() {
                while (1 == 1) {
                    String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
                    relogio.setText(hora);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e);
                    }
                }
            }
        });
        ThreadRelogio.start();//começando o thread do relogio
    }

    public void saudacao(String nome) {//defini a frase que aparece na tela
        int manhaTardeNoite = Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));
        String frase;
        if (manhaTardeNoite >= 05 && manhaTardeNoite < 12) {//entre 5 da manhã e antes do meio dia
            frase = "Bom dia ";
        } else if (manhaTardeNoite >= 12 && manhaTardeNoite < 19) {//entre meio dia e antes das 7 da noite
            frase = "Boa tarde ";
        } else {//outro horário
            frase = "Boa noite ";
        }
        quadro.setText(frase + nome);
    }

    /*coloque uma barra aqui para retirar o comentário  ---> */
    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException ex) {
            return false;
        }
        return true;
    }/**/
    
    /**
     *
     * @param entradastr
     * @param saidastr
     * @return 
     */
    public int comparaDatas (String entradastr, String saidastr) {
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
        Date saida = new Date();
        Date entrada = new Date();
        Date hoje = new Date();
        String hojestr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try {
            hoje = data.parse(hojestr);
            saida = data.parse(saidastr);
            entrada = data.parse(entradastr);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro na formatação da data");
        }
        if (entrada.before(saida)) {//data de entrada antes que a de saida (OK)
            return 1;
        } else if (entrada.after(hoje)) {//data de entrada depois de hoje (OK)
            return 2;
        } else if (entrada.equals(hoje)) {//data de entrada igual a hoje (OK)
            return 3;
        } else if (saida.equals(entrada)) {//data de saida igual a entrada (OK)
            return 4;
        } else if (saida.before(entrada)) {//data de saida menor que a entrada (ERRO)
            return 5;
        } else if (entrada.before(hoje)) {//data de entrada menor que hoje (ERRO)
            return 6;
        } else {
            return 0;// erro inesperado
        }
    }
    
    public void resetCampos() {
        
        //JTable
        DefaultTableModel model = (DefaultTableModel) selectHospedeExcluir.getModel();
        model.setNumRows(0);
        DefaultTableModel model1 = (DefaultTableModel) selectFuncReserva.getModel();
        model1.setNumRows(0);
        DefaultTableModel model2 = (DefaultTableModel) selectHospedeReserva.getModel();
        model2.setNumRows(0);
        DefaultTableModel model3 = (DefaultTableModel) selectQuartosExcluir.getModel();
        model3.setNumRows(0);
        DefaultTableModel model4 = (DefaultTableModel) selectReservasExcluir.getModel();
        model4.setNumRows(0);
        DefaultTableModel model5 = (DefaultTableModel) selectPagamentosExcluir.getModel();
        model5.setNumRows(0);
        DefaultTableModel model6 = (DefaultTableModel) selectLembretesExcluir.getModel();
        model6.setNumRows(0);
        DefaultTableModel model7 = (DefaultTableModel) selectFuncVer.getModel();
        model7.setNumRows(0);
        DefaultTableModel model8 = (DefaultTableModel) selectHospedeVer.getModel();
        model8.setNumRows(0);
        DefaultTableModel model9 = (DefaultTableModel) selectQuartosVer.getModel();
        model9.setNumRows(0);
        DefaultTableModel model10 = (DefaultTableModel) selectReservasVer.getModel();
        model10.setNumRows(0);
        DefaultTableModel model11 = (DefaultTableModel) selectPagamentosVer.getModel();
        model11.setNumRows(0);
        DefaultTableModel model12 = (DefaultTableModel) selectLembretesVer.getModel();
        model12.setNumRows(0);

//JLabel
        
    //CADASTRAR
        
        //funcionario
            nomeFuncionarioTxt.setText("");
            rgFuncionarioTxt.setText("");
            cpfFuncionarioTxt.setText("");
            emaiFuncionarioTxt.setText("");
            telefoneFuncionarioTxt.setText("");
            enderecoFuncionarioTxt.setText("");
            cargoFuncionarioTxt.setText("");
            usuarioFuncionarioTxt.setText("");
            senhaFuncionarioTxt.setText("");
        //hospede
            nomeHospedeTxt.setText("");
            rgHospedeTxt.setText("");
            cpfHospedeTxt.setText("");
            telefoneHospedeTxt.setText("");
            cidadeHospedeTxt.setText("");
        //quarto
            numQuartoTxt.setText("");
            andarQuartoTxt.setText("");
            precoQuartoTxt.setText("");
            tipoQuartoTxt.setText("");
            descricaoQuartoTxt.setText("");
        //reserva
            nomeHospReservaTxt.setText("");
            numQuartoReservaTxt.setText("");
            descricaoQuartoReserva.setText("");
        //pagamento
            valorPagTxt.setText("");
            formaPagTxt.setText("");
            descPagTxt.setText("");
        //lembrete
            assuntoLemTxt.setText("");
            descLemTxt.setText("");
        
        
    //EXCLUIR
        
        //funcionario
            nomeFuncExcluirTxt.setText("");
            descricaoFuncExcluir.setText("");
        //hospede
            descHospExcluir.setText("");
            nomeHospExcluirTxt.setText("");
        //quarto
            numQuaExcluirTxt.setText("");
            descQuaExcluir.setText("");
        //reserva
            nomeResExcluirTxt.setText("");
            descResExcluir.setText("");
        //pagamento
            descPagExcluirTxt.setText("");
            descPagExcluir.setText("");
        //lembrete
            descLemExcluirTxt.setText("");
            descLemExcluir.setText("");
        
        
    //VER
        
        //funcionario
            nomeFuncVerTxt.setText("");
            descricaoFuncVer.setText("");
        //hospede
            nomeHospVerTxt.setText("");
            descHospVer.setText("");
        //quarto
            numQuaVerTxt.setText("");
            descQuaVer.setText("");
        //reserva
            nomeResVerTxt.setText("");
            descResVer.setText("");
        //pagamento
            descPagVerTxt.setText("");
            descPagVer.setText("");
        //lembrete
            descLemVerTxt.setText("");
            descLemVer.setText("");
        
        
    //EDITAR
        
        //funcionario
            cpfModFuncTxt.setText("");
            nomeFuncTxtMod.setText("");
            emaiFuncTxtMod.setText("");
            telefoneFuncTxtMod.setText("");
            enderecoFuncTxtMod.setText("");
            cargoFuncTxtMod.setText("");
            usuarioFuncTxtMod.setText("");
            senhaFuncTxtMod.setText("");
        //hospede
            cpfHospedeTxtMod.setText("");
            nomeHospedeTxtMod.setText("");
            telefoneHospedeTxtMod.setText("");
            cidadeHospedeTxtMod.setText("");
        //quarto
            numQuartoTxtMod.setText("");
            andarQuartoTxtMod.setText("");
            precoQuartoTxtMod.setText("");
            tipoQuartoTxtMod.setText("");
            descricaoQuartoTxtMod.setText("");
        //reserva
            numQuaResModTxt.setText("");
            idReservaMod.setText("");
            idHospResModTxt.setText("");
            nomeHospResTxt.setText("");
            consumoResModTxt.setText("");
        //pagamento
            idPagModTxt.setText("");
            formaPagModTxt.setText("");
            valorPagModTxt.setText("");
            descPagModTxt.setText("");
        //lembrete
            idLemModTxt.setText("");
            assuntoLemModTxt.setText("");
            descLemModTxt.setText("");
        
//JComboBox
        sexoHospedeTxt.setSelectedItem("F");
        sexoFuncTxtMod.setSelectedItem("F");
        sexoHospedeTxtMod.setSelectedItem("F");
        diaNascHospedeTxt.setSelectedItem("01");
        mesNascHospedeTxt.setSelectedItem("01");
        anoNascHospedeTxt.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
        sexoFuncionarioTxt.setSelectedItem("F");
        estadoResModTxt.setSelectedItem("Reservado");
        diaNascFuncionarioTxt.setSelectedItem("01");
        mesNascFuncionarioTxt.setSelectedItem("01");
        anoNascFuncionarioTxt.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
        diaConNovoFunc.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
        mesConNovoFunc.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
        anoConNovoFunc.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
        cargaHorariaFuncionarioTxt.setSelectedItem("1");
        cargaHorariaFuncTxtMod.setSelectedItem("1");
        diaEntradaReserva.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
        mesEntradaReserva.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
        anoEntradaReserva.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
        diaSaidaReserva.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
        mesSaidaReserva.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
        anoSaidaReserva.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
        diaEntradaReservaMod.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
        mesEntradaReservaMod.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
        anoEntradaReservaMod.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
        diaSaidaReservaMod.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
        mesSaidaReservaMod.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
        anoSaidaReservaMod.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
        diaPag.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
        mesPag.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
        anoPag.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
        diaLem1.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
        mesLem1.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
        anoLem1.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
        diaPagMod.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
        mesPagMod.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
        anoPagMod.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
        diaLem.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
        mesLem.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
        anoLem.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
        disponibilidadeQuartoTxtMod.setSelectedItem("S");

        //JCheckBox
        excluirReservasHosp.setSelected(false);//JCheckBox para excluir todas as reservas do hóspede
        excluirReservasQua.setSelected(false);//JCheckBox para excluir todas as reservas do quarto
        UserPassNew.setSelected(false);//JCheckBox para cadastrar usuario e senha do funcionario
        UserPassMod.setSelected(false);//JCheckBox para atualizar usuario e senha do funcionario

        /*Aspecto desabilitado por padrão no cadastro e na modificação de usuarios e senhas dos funcionarios
         *
         *       Tela de Modificação       */
        usuarioFuncTxtMod.setBackground(new Color(230, 230, 230));
        usuarioFuncionarioMod.setForeground(new Color(230, 230, 230));
        usuarioFuncTxtMod.disable();
        senhaFuncionarioMod.setForeground(new Color(230, 230, 230));
        senhaFuncTxtMod.setBackground(new Color(230, 230, 230));
        senhaFuncTxtMod.disable();

        /*       Tela de Cadastro       */
        usuarioFuncionarioTxt.setBackground(new Color(230, 230, 230));
        usuarioFuncionario.setForeground(new Color(230, 230, 230));
        usuarioFuncionarioTxt.disable();
        senhaFuncionarioTxt.setBackground(new Color(230, 230, 230));
        senhaFuncionario.setForeground(new Color(230, 230, 230));
        senhaFuncionarioTxt.disable();
    }

    public void corBtnGeral() {//defini todas as cores dos botões pricipais como desativado(azul-escuro)
        inicioBtn.setBackground(new Color(0, 73, 147));
        funcionarioBtn.setBackground(new Color(0, 73, 147));
        hospedeBtn.setBackground(new Color(0, 73, 147));
        quartoBtn.setBackground(new Color(0, 73, 147));
        reservaBtn.setBackground(new Color(0, 73, 147));
        pagamentoBtn.setBackground(new Color(0, 73, 147));
        lembreteBtn.setBackground(new Color(0, 73, 147));

    }

    public void esconderBtnFuncoes() {//esconde todos os botões de todas as funções

        //botões das funções
        cadastrarFuncFuncaoBtn.setVisible(false);
        cadastrarHospFuncaoBtn.setVisible(false);
        cadastrarQuaFuncaoBtn.setVisible(false);
        cadastrarResFuncaoBtn.setVisible(false);
        cadastrarPagFuncaoBtn.setVisible(false);
        cadastrarLemFuncaoBtn.setVisible(false);

        excluirHospFuncaoBtn.setVisible(false);
        excluirFuncFuncaoBtn.setVisible(false);
        excluirQuaFuncaoBtn.setVisible(false);
        excluirResFuncaoBtn.setVisible(false);
        excluirPagFuncaoBtn.setVisible(false);
        excluirLemFuncaoBtn.setVisible(false);

        verFuncFuncaoBtn.setVisible(false);
        verHospFuncaoBtn.setVisible(false);
        verQuaFuncaoBtn.setVisible(false);
        verResFuncaoBtn.setVisible(false);
        verPagFuncaoBtn.setVisible(false);
        verLemFuncaoBtn.setVisible(false);

        modFuncFuncaoBtn.setVisible(false);
        modHospFuncaoBtn.setVisible(false);
        modQuaFuncaoBtn.setVisible(false);
        modResFuncaoBtn.setVisible(false);
        modPagFuncaoBtn.setVisible(false);
        modLemFuncaoBtn.setVisible(false);

    }

    public void corBtnFuncoes() {//defini todas as cores dos botões de função de alguma aba como desativado(cinza) e as cores de todos os textos dos botões como branco

        //Botões de Funções CADASTRAR,EXCLUIR,VER
        cadastrarFuncFuncaoBtn.setBackground(new Color(0, 226, 175));
        cadastrarHospFuncaoBtn.setBackground(new Color(0, 226, 175));
        cadastrarQuaFuncaoBtn.setBackground(new Color(0, 226, 175));
        cadastrarResFuncaoBtn.setBackground(new Color(0, 226, 175));
        cadastrarPagFuncaoBtn.setBackground(new Color(0, 226, 175));
        cadastrarLemFuncaoBtn.setBackground(new Color(0, 226, 175));

        excluirHospFuncaoBtn.setBackground(new Color(226, 0, 0));
        excluirFuncFuncaoBtn.setBackground(new Color(226, 0, 0));
        excluirQuaFuncaoBtn.setBackground(new Color(226, 0, 0));
        excluirResFuncaoBtn.setBackground(new Color(226, 0, 0));
        excluirPagFuncaoBtn.setBackground(new Color(226, 0, 0));
        excluirLemFuncaoBtn.setBackground(new Color(226, 0, 0));

        verFuncFuncaoBtn.setBackground(new Color(226, 128, 0));
        verHospFuncaoBtn.setBackground(new Color(226, 128, 0));
        verQuaFuncaoBtn.setBackground(new Color(226, 128, 0));
        verResFuncaoBtn.setBackground(new Color(226, 128, 0));
        verPagFuncaoBtn.setBackground(new Color(226, 128, 0));
        verLemFuncaoBtn.setBackground(new Color(226, 128, 0));

        modHospFuncaoBtn.setBackground(new Color(171, 71, 255));
        modFuncFuncaoBtn.setBackground(new Color(171, 71, 255));
        modQuaFuncaoBtn.setBackground(new Color(171, 71, 255));
        modResFuncaoBtn.setBackground(new Color(171, 71, 255));
        modPagFuncaoBtn.setBackground(new Color(171, 71, 255));
        modLemFuncaoBtn.setBackground(new Color(171, 71, 255));

        //Label's dos botões de Funções CADASTRAR,EXCLUIR,VER
        cadastrarFunciFuncao.setForeground(new Color(255, 255, 255));
        cadastrarHospFuncao.setForeground(new Color(255, 255, 255));
        cadastrarQuaFuncao.setForeground(new Color(255, 255, 255));
        cadastrarResFuncao.setForeground(new Color(255, 255, 255));
        cadastrarPagFuncao.setForeground(new Color(255, 255, 255));
        cadastrarLemFuncao.setForeground(new Color(255, 255, 255));

        excluirHospFuncao.setForeground(new Color(255, 255, 255));
        excluirFuncFuncao.setForeground(new Color(255, 255, 255));
        excluirQuaFuncao.setForeground(new Color(255, 255, 255));
        excluirResFuncao.setForeground(new Color(255, 255, 255));
        excluirPagFuncao.setForeground(new Color(255, 255, 255));
        excluirLemFuncao.setForeground(new Color(255, 255, 255));

        verHospFuncao.setForeground(new Color(255, 255, 255));
        verFuncFuncao.setForeground(new Color(255, 255, 255));
        verQuaFuncao.setForeground(new Color(255, 255, 255));
        verResFuncao.setForeground(new Color(255, 255, 255));
        verPagFuncao.setForeground(new Color(255, 255, 255));
        verLemFuncao.setForeground(new Color(255, 255, 255));

        modHospFuncao.setForeground(new Color(255, 255, 255));
        modFuncFuncao.setForeground(new Color(255, 255, 255));
        modQuaFuncao.setForeground(new Color(255, 255, 255));
        modResFuncao.setForeground(new Color(255, 255, 255));
        modPagFuncao.setForeground(new Color(255, 255, 255));
        modLemFuncao.setForeground(new Color(255, 255, 255));

    }

    public void esconderPaiFilho() {//Esconde todos os panel's pais e filhos, e reseta todos os label's de erro

        panelInicio.setVisible(false);//pai

        panelFuncionario.setVisible(false);//pai
        cadastrarFuncionario.setVisible(false);//filho
        excluirFuncionario.setVisible(false);//filho
        verFuncionario.setVisible(false);//filho
        modificarFuncionario.setVisible(false);//filho

        panelHospede.setVisible(false);//pai
        cadastrarHospede.setVisible(false);//filho
        excluirHospede.setVisible(false);//filho
        verHospede.setVisible(false);//filho
        modificarHospede.setVisible(false);//filho

        panelQuarto.setVisible(false);//pai
        cadastrarQuarto.setVisible(false);//filho
        excluirQuarto.setVisible(false);//filho
        verQuarto.setVisible(false);//filho
        modificarQuarto.setVisible(false);//filho

        panelReserva.setVisible(false);//pai
        cadastrarReserva.setVisible(false);//filho
        excluirReserva.setVisible(false);//filho
        verReserva.setVisible(false);//filho
        modificarReserva.setVisible(false);//filho

        panelPagamento.setVisible(false);//pai
        cadastrarPagamento.setVisible(false);//filho
        excluirPagamento.setVisible(false);//filho
        verPagamento.setVisible(false);//filho
        modificarPagamento.setVisible(false);//filho

        panelLembrete.setVisible(false);//pai
        cadastrarLembrete.setVisible(false);//filho
        excluirLembrete.setVisible(false);//filho
        verLembrete.setVisible(false);//filho
        modificarLembrete.setVisible(false);//filho

        info.setVisible(false);//pai

        //label's dos erros
        erroQuarto.setText("");
        erroHospede.setText("");
        erroFunc.setText("");
        erroReserva.setText("");
        erroPag.setText("");
        erroLem.setText("");
        erroFuncMod.setText("");
        erroHospMod.setText("");
        erroQuaMod.setText("");
        erroResMod.setText("");
        erroPagMod.setText("");
        erroLem1.setText("");

        //infoIcon
        infoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/info-gray.png")));
    }

    public void modFunc(String sql) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + new Conection().ipDB + ":3306/hotel?useTimezone=true&serverTimezone=UTC",
                    new Conection().userDB, new Conection().passDB);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            nomeFuncTxtMod.setText("");
            sexoFuncTxtMod.setSelectedItem("F");
            cargaHorariaFuncTxtMod.setSelectedItem("1");
            emaiFuncTxtMod.setText("");
            telefoneFuncTxtMod.setText("");
            enderecoFuncTxtMod.setText("");
            cargoFuncTxtMod.setText("");
            usuarioFuncTxtMod.setText("");
            senhaFuncTxtMod.setText("");
            while (rs.next()) {
                nomeFuncTxtMod.setText(rs.getString("nome"));
                switch (rs.getString("sexo")) {
                    case "F":
                        sexoFuncTxtMod.setSelectedItem("F");
                        break;
                    case "M":
                        sexoFuncTxtMod.setSelectedItem("M");
                        break;
                    case "Outros":
                        sexoFuncTxtMod.setSelectedItem("Outros");
                        break;
                    default:
                        System.out.println("Erro selecionar sexo, tela de modificação de funcionários");
                        break;
                }
                switch (rs.getInt("cargaHoraria")) {
                    case 1:
                        cargaHorariaFuncTxtMod.setSelectedItem("1");
                        break;
                    case 2:
                        cargaHorariaFuncTxtMod.setSelectedItem("2");
                        break;
                    case 3:
                        cargaHorariaFuncTxtMod.setSelectedItem("3");
                        break;
                    case 4:
                        cargaHorariaFuncTxtMod.setSelectedItem("4");
                        break;
                    case 5:
                        cargaHorariaFuncTxtMod.setSelectedItem("5");
                        break;
                    case 6:
                        cargaHorariaFuncTxtMod.setSelectedItem("6");
                        break;
                    case 7:
                        cargaHorariaFuncTxtMod.setSelectedItem("7");
                        break;
                    case 8:
                        cargaHorariaFuncTxtMod.setSelectedItem("8");
                        break;
                    case 9:
                        cargaHorariaFuncTxtMod.setSelectedItem("9");
                        break;
                    case 10:
                        cargaHorariaFuncTxtMod.setSelectedItem("10");
                        break;
                    case 11:
                        cargaHorariaFuncTxtMod.setSelectedItem("11");
                        break;
                    case 12:
                        cargaHorariaFuncTxtMod.setSelectedItem("12");
                        break;
                    default:
                        System.out.println("Erro selecionar sexo, tela de modificação de funcionários");
                        MessageStatus m = new MessageStatus(usuario, nome, "Erro ao selecionar sexo do funcionário.", "erro");
                        m.setLocationRelativeTo(null);
                        m.setVisible(true);
                        break;
                }
                emaiFuncTxtMod.setText(rs.getString("email"));
                telefoneFuncTxtMod.setText(rs.getString("telefone"));
                enderecoFuncTxtMod.setText(rs.getString("endereco"));
                cargoFuncTxtMod.setText(rs.getString("cargo"));
                usuarioFuncTxtMod.setText(rs.getString("usuario"));
                senhaFuncTxtMod.setText(rs.getString("senha"));
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void modHosp(String sql) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + new Conection().ipDB + ":3306/hotel?useTimezone=true&serverTimezone=UTC",
                    new Conection().userDB, new Conection().passDB);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            nomeHospedeTxtMod.setText("");
            sexoHospedeTxtMod.setSelectedItem("F");
            telefoneHospedeTxtMod.setText("");
            cidadeHospedeTxtMod.setText("");
            while (rs.next()) {
                nomeHospedeTxtMod.setText(rs.getString("nome"));
                switch (rs.getString("sexo")) {
                    case "F":
                        sexoHospedeTxtMod.setSelectedItem("F");
                        break;
                    case "M":
                        sexoHospedeTxtMod.setSelectedItem("M");
                        break;
                    case "Outros":
                        sexoHospedeTxtMod.setSelectedItem("Outros");
                        break;
                    default:
                        System.out.println("Erro selecionar sexo, tela de modificação de hóspedes");
                        break;
                }
                telefoneHospedeTxtMod.setText(rs.getString("telefone"));
                cidadeHospedeTxtMod.setText(rs.getString("cidade"));
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void modQua(String sql) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + new Conection().ipDB + ":3306/hotel?useTimezone=true&serverTimezone=UTC",
                    new Conection().userDB, new Conection().passDB);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            precoQuartoTxtMod.setText("");
            andarQuartoTxtMod.setText("");
            tipoQuartoTxtMod.setText("");
            descricaoQuartoTxtMod.setText("");
            disponibilidadeQuartoTxtMod.setSelectedItem("S");
            while (rs.next()) {
                precoQuartoTxtMod.setText(rs.getString("preco").replace(".",","));
                andarQuartoTxtMod.setText(rs.getString("andar"));
                tipoQuartoTxtMod.setText(rs.getString("tipo"));
                descricaoQuartoTxtMod.setText(rs.getString("descricao"));
                switch (rs.getString("disponivel")) {
                    case "S":
                        disponibilidadeQuartoTxtMod.setSelectedItem("S");
                        break;
                    case "N":
                        disponibilidadeQuartoTxtMod.setSelectedItem("N");
                        break;
                }
            }
            erroQuaMod.setText("");
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void modPag(String sql) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + new Conection().ipDB + ":3306/hotel?useTimezone=true&serverTimezone=UTC",
                    new Conection().userDB, new Conection().passDB);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            formaPagModTxt.setText("");
            valorPagModTxt.setText("");
            descPagModTxt.setText("");
            diaPagMod.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
            mesPagMod.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
            anoPagMod.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
            while (rs.next()) {
                formaPagModTxt.setText(rs.getString("Fpagamento"));
                valorPagModTxt.setText(rs.getString("valor").replace(".",","));
                descPagModTxt.setText(rs.getString("descricao"));
                diaPagMod.setSelectedItem(new SimpleDateFormat("dd").format(rs.getDate("data")));
                mesPagMod.setSelectedItem(new SimpleDateFormat("MM").format(rs.getDate("data")));
                anoPagMod.setSelectedItem(new SimpleDateFormat("yyyy").format(rs.getDate("data")));
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void modLem(String sql) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + new Conection().ipDB + ":3306/hotel?useTimezone=true&serverTimezone=UTC",
                    new Conection().userDB, new Conection().passDB);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                assuntoLemModTxt.setText(rs.getString("assunto"));
                descLemModTxt.setText(rs.getString("descricao"));
                diaLem1.setSelectedItem(new SimpleDateFormat("dd").format(rs.getDate("data")));
                mesLem1.setSelectedItem(new SimpleDateFormat("MM").format(rs.getDate("data")));
                anoLem1.setSelectedItem(new SimpleDateFormat("yyyy").format(rs.getDate("data")));
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void modRes(String sql) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + new Conection().ipDB + ":3306/hotel?useTimezone=true&serverTimezone=UTC",
                    new Conection().userDB, new Conection().passDB);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            idHospResModTxt.setText("");
            nomeHospResTxt.setText("");
            consumoResModTxt.setText("");
            numQuaResModTxt.setText("");
            diaEntradaReservaMod.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
            mesEntradaReservaMod.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
            anoEntradaReservaMod.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
            diaSaidaReservaMod.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
            mesSaidaReservaMod.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
            anoSaidaReservaMod.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
            estadoResModTxt.setSelectedItem("Reservado");
            while (rs.next()) {
                int id = rs.getInt("id_hospede");
                idHospResModTxt.setText(rs.getString("id_hospede"));
                nomeHospResTxt.setText(" " + new Conection().nameHospReserva(id + ""));
                diaEntradaReservaMod.setSelectedItem(new SimpleDateFormat("dd").format(rs.getDate("dataEntrada")));
                mesEntradaReservaMod.setSelectedItem(new SimpleDateFormat("MM").format(rs.getDate("dataEntrada")));
                anoEntradaReservaMod.setSelectedItem(new SimpleDateFormat("yyyy").format(rs.getDate("dataEntrada")));
                diaSaidaReservaMod.setSelectedItem(new SimpleDateFormat("dd").format(rs.getDate("dataSaida")));
                mesSaidaReservaMod.setSelectedItem(new SimpleDateFormat("MM").format(rs.getDate("dataSaida")));
                anoSaidaReservaMod.setSelectedItem(new SimpleDateFormat("yyyy").format(rs.getDate("dataSaida")));
                estadoResModTxt.setSelectedItem(rs.getString("estado"));
                numQuaResModTxt.setText(rs.getString("num_quarto"));
                consumoResModTxt.setText(rs.getString("consumo").replace(".",","));
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    //==========-----SELECT HÓSPEDES NA TABELA-----==========\\
    public void selectHospJTable(String sql) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://" + new Conection().ipDB + ":3306/hotel?useTimezone=true&serverTimezone=UTC",
                    new Conection().userDB, new Conection().passDB);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = (DefaultTableModel) selectHospedeExcluir.getModel();
            model.setNumRows(0);

            DefaultTableModel model1 = (DefaultTableModel) selectHospedeReserva.getModel();
            model1.setNumRows(0);

            DefaultTableModel model2 = (DefaultTableModel) selectReservasExcluir.getModel();
            model2.setNumRows(0);

            DefaultTableModel model3 = (DefaultTableModel) selectHospedeVer.getModel();
            model3.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idHospede"),
                    rs.getString("nome"),
                    rs.getString("sexo"),
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("nascimento")),
                    rs.getString("cpf"),
                    rs.getString("rg"),
                    rs.getString("telefone"),
                    rs.getString("cidade")
                });
                model1.addRow(new Object[]{
                    rs.getInt("idHospede"),
                    rs.getString("nome"),
                    rs.getString("sexo"),
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("nascimento")),
                    rs.getString("cpf"),
                    rs.getString("rg"),
                    rs.getString("telefone"),
                    rs.getString("cidade")
                });
                model2.addRow(new Object[]{
                    rs.getInt("idHospede"),
                    rs.getString("nome"),
                    rs.getString("sexo"),
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("nascimento")),
                    rs.getString("cpf"),
                    rs.getString("rg"),
                    rs.getString("telefone"),
                    rs.getString("cidade")
                });
                model3.addRow(new Object[]{
                    rs.getInt("idHospede"),
                    rs.getString("nome"),
                    rs.getString("sexo"),
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("nascimento")),
                    rs.getString("cpf"),
                    rs.getString("rg"),
                    rs.getString("telefone"),
                    rs.getString("cidade")
                });
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("selectHospJTable,o erro foi " + ex);
            MessageStatus m = new MessageStatus(usuario, nome, "Erro inesperado ao selecionar hóspede.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        }
    }

    //==========-----SELECT FUNCIONÁRIOS NA TABELA-----==========\\
    public void selectFuncJTable(String sql) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://" + new Conection().ipDB + ":3306/hotel?useTimezone=true&serverTimezone=UTC",
                    new Conection().userDB, new Conection().passDB);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = (DefaultTableModel) selectFuncReserva.getModel();
            model.setNumRows(0);
            DefaultTableModel model1 = (DefaultTableModel) selectFuncVer.getModel();
            model1.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idFuncionario"),//0
                    rs.getString("nome"),//1
                    rs.getString("sexo"),//2
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("nascimento")),//3
                    rs.getString("endereco"),//4
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dataContratacao")),//5
                    rs.getString("cargo"),//6
                    rs.getDouble("cargaHoraria"),//7
                    rs.getString("cpf"),//8
                    rs.getString("rg"),//9
                    rs.getString("telefone"),//10
                    rs.getString("email"),//11
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dataCadastro")),//12
                });
                model1.addRow(new Object[]{
                    rs.getInt("idFuncionario"),//0
                    rs.getString("nome"),//1
                    rs.getString("sexo"),//2
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("nascimento")),//3
                    rs.getString("endereco"),//4
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dataContratacao")),//5
                    rs.getString("cargo"),//6
                    rs.getDouble("cargaHoraria"),//7
                    rs.getString("cpf"),//8
                    rs.getString("rg"),//9
                    rs.getString("telefone"),//10
                    rs.getString("email"),//11
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dataCadastro")),//12
                });
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("selectFuncJTable,o erro foi " + ex);
            MessageStatus m = new MessageStatus(usuario, nome, "Erro inesperado ao selecionar funcionário.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        }
    }

    //==========-----SELECT PAGAMENTOS NA TABELA-----==========\\
    public void selectPagJTable(String sql) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://" + new Conection().ipDB + ":3306/hotel?useTimezone=true&serverTimezone=UTC",
                    new Conection().userDB, new Conection().passDB);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = (DefaultTableModel) selectPagamentosExcluir.getModel();
            model.setNumRows(0);
            DefaultTableModel model1 = (DefaultTableModel) selectPagamentosVer.getModel();
            model1.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idPagamento"),//0
                    rs.getDouble("valor"),//1
                    rs.getString("Fpagamento"),//2
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("data")),//3
                    rs.getString("descricao")//4
                });
                model1.addRow(new Object[]{
                    rs.getInt("idPagamento"),//0
                    rs.getDouble("valor"),//1
                    rs.getString("Fpagamento"),//2
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("data")),//3
                    rs.getString("descricao")//4
                });
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("selectPagJTable,o erro foi " + ex);
            MessageStatus m = new MessageStatus(usuario, nome, "Erro inesperado ao selecionar pagamento.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        }
    }

    //==========-----SELECT LEMBRETES NA TABELA-----==========\\
    public void selectLemJTable(String sql) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://" + new Conection().ipDB + ":3306/hotel?useTimezone=true&serverTimezone=UTC",
                    new Conection().userDB, new Conection().passDB);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = (DefaultTableModel) selectLembretesExcluir.getModel();
            model.setNumRows(0);

            DefaultTableModel model2 = (DefaultTableModel) selectLembretesVer.getModel();
            model2.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idLembrete"),//0
                    rs.getString("nomeUsuario"),//1
                    rs.getString("assunto"),//2
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("data")),//3
                    rs.getString("descricao")//4
                });
                model2.addRow(new Object[]{
                    rs.getInt("idLembrete"),//0
                    rs.getString("nomeUsuario"),//1
                    rs.getString("assunto"),//2
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("data")),//3
                    rs.getString("descricao")//4
                });
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("selectLemJTable,o erro foi " + ex);
            MessageStatus m = new MessageStatus(usuario, nome, "Erro inesperado ao selecionar lembrete.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        }
    }

    //==========-----SELECT QUARTOS NA TABELA-----==========\\
    public void selectQuaJTable(String sql) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://" + new Conection().ipDB + ":3306/hotel?useTimezone=true&serverTimezone=UTC",
                    new Conection().userDB, new Conection().passDB);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = (DefaultTableModel) selectQuartoReserva.getModel();
            model.setNumRows(0);
            DefaultTableModel model1 = (DefaultTableModel) selectQuartosExcluir.getModel();
            model1.setNumRows(0);
            DefaultTableModel model2 = (DefaultTableModel) selectQuartosVer.getModel();
            model2.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("numero"),
                    rs.getInt("andar"),
                    rs.getString("tipo"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getString("disponivel")
                });
                model1.addRow(new Object[]{
                    rs.getInt("numero"),
                    rs.getInt("andar"),
                    rs.getString("tipo"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getString("disponivel")
                });
                model2.addRow(new Object[]{
                    rs.getInt("numero"),
                    rs.getInt("andar"),
                    rs.getString("tipo"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getString("disponivel")
                });
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("selectQuaJTable,o erro foi " + ex);
            MessageStatus m = new MessageStatus(usuario, nome, "Erro inesperado ao selecionar quarto.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        }
    }

    //==========-----SELECT RESERVAS NA TABELA-----==========\\
    public void selectResJTable(String sql) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://" + new Conection().ipDB + ":3306/hotel?useTimezone=true&serverTimezone=UTC",
                    new Conection().userDB, new Conection().passDB);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = (DefaultTableModel) selectReservasExcluir.getModel();
            model.setNumRows(0);
            DefaultTableModel model1 = (DefaultTableModel) selectReservasVer.getModel();
            model1.setNumRows(0);

            while (rs.next()) {
                int id = rs.getInt("id_hospede");
                model.addRow(new Object[]{
                    rs.getInt("idReserva"),
                    new Conection().nameHospReserva(id + ""),
                    rs.getInt("num_quarto"),
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dataEntrada")),
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dataSaida")),
                    rs.getDouble("consumo"),
                    rs.getString("estado")
                });
                model1.addRow(new Object[]{
                    rs.getInt("idReserva"),
                    new Conection().nameHospReserva(id + ""),
                    rs.getInt("num_quarto"),
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dataEntrada")),
                    new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dataSaida")),
                    rs.getDouble("consumo"),
                    rs.getString("estado")
                });
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("selectResJTable,o erro foi " + ex);
            MessageStatus m = new MessageStatus(usuario, nome, "Erro inesperado ao selecionar reserva.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    public void initComponentsAntes() {

        PanelTopo = new javax.swing.JPanel();
        sairTxt = new javax.swing.JLabel();
        minimizarTxt = new javax.swing.JLabel();
        PanelEsquerdo = new javax.swing.JPanel();
        inicioBtn = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        inicioTxt = new javax.swing.JLabel();
        funcionarioBtn = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        funcionarioTxt = new javax.swing.JLabel();
        hospedeBtn = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        hospedeTxt = new javax.swing.JLabel();
        quartoBtn = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        quartoTxt = new javax.swing.JLabel();
        reservaBtn = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        reservaTxt = new javax.swing.JLabel();
        pagamentoBtn = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        transacaoTxt1 = new javax.swing.JLabel();
        lembreteBtn = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lembretesTxt = new javax.swing.JLabel();
        iconHotelTxt = new javax.swing.JLabel();
        infoIcon = new javax.swing.JLabel();
        FuncoesAbas = new javax.swing.JPanel();
        saudacaoQuadro = new javax.swing.JScrollPane();
        quadro = new javax.swing.JTextArea();
        cadastrarLemFuncaoBtn = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        cadastrarLemFuncao = new javax.swing.JLabel();
        iconC5 = new javax.swing.JLabel();
        cadastrarResFuncaoBtn = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        cadastrarResFuncao = new javax.swing.JLabel();
        iconC = new javax.swing.JLabel();
        cadastrarHospFuncaoBtn = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        cadastrarHospFuncao = new javax.swing.JLabel();
        iconC4 = new javax.swing.JLabel();
        cadastrarPagFuncaoBtn = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        cadastrarPagFuncao = new javax.swing.JLabel();
        iconC2 = new javax.swing.JLabel();
        cadastrarQuaFuncaoBtn = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        cadastrarQuaFuncao = new javax.swing.JLabel();
        iconC3 = new javax.swing.JLabel();
        cadastrarFuncFuncaoBtn = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        cadastrarFunciFuncao = new javax.swing.JLabel();
        iconC1 = new javax.swing.JLabel();
        excluirLemFuncaoBtn = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        excluirLemFuncao = new javax.swing.JLabel();
        iconE5 = new javax.swing.JLabel();
        excluirResFuncaoBtn = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        excluirResFuncao = new javax.swing.JLabel();
        iconE4 = new javax.swing.JLabel();
        excluirQuaFuncaoBtn = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        excluirQuaFuncao = new javax.swing.JLabel();
        iconE1 = new javax.swing.JLabel();
        excluirHospFuncaoBtn = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        excluirHospFuncao = new javax.swing.JLabel();
        iconE = new javax.swing.JLabel();
        excluirFuncFuncaoBtn = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        excluirFuncFuncao = new javax.swing.JLabel();
        iconE2 = new javax.swing.JLabel();
        excluirPagFuncaoBtn = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        excluirPagFuncao = new javax.swing.JLabel();
        iconE3 = new javax.swing.JLabel();
        verResFuncaoBtn = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        verResFuncao = new javax.swing.JLabel();
        iconO1 = new javax.swing.JLabel();
        verHospFuncaoBtn = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        verHospFuncao = new javax.swing.JLabel();
        iconO4 = new javax.swing.JLabel();
        verFuncFuncaoBtn = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        verFuncFuncao = new javax.swing.JLabel();
        iconO3 = new javax.swing.JLabel();
        verQuaFuncaoBtn = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        verQuaFuncao = new javax.swing.JLabel();
        iconO2 = new javax.swing.JLabel();
        verLemFuncaoBtn = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        verLemFuncao = new javax.swing.JLabel();
        iconO5 = new javax.swing.JLabel();
        verPagFuncaoBtn = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        verPagFuncao = new javax.swing.JLabel();
        iconO = new javax.swing.JLabel();
        modResFuncaoBtn = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        modResFuncao = new javax.swing.JLabel();
        iconO6 = new javax.swing.JLabel();
        modHospFuncaoBtn = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        modHospFuncao = new javax.swing.JLabel();
        iconO7 = new javax.swing.JLabel();
        modFuncFuncaoBtn = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        modFuncFuncao = new javax.swing.JLabel();
        iconO8 = new javax.swing.JLabel();
        modQuaFuncaoBtn = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        modQuaFuncao = new javax.swing.JLabel();
        iconO9 = new javax.swing.JLabel();
        modLemFuncaoBtn = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        modLemFuncao = new javax.swing.JLabel();
        iconO10 = new javax.swing.JLabel();
        modPagFuncaoBtn = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        modPagFuncao = new javax.swing.JLabel();
        iconO11 = new javax.swing.JLabel();
        PanelPrincipal = new javax.swing.JPanel();
        gambiarraLinhaBranca = new javax.swing.JPanel();
        panelInicio = new javax.swing.JPanel();
        logoInicio = new javax.swing.JLabel();
        panelFuncionario = new javax.swing.JPanel();
        cadastrarFuncionario = new javax.swing.JPanel();
        UserPassNew = new javax.swing.JCheckBox();
        cadastrarNovoFuncionario = new javax.swing.JLabel();
        nomeFuncionario = new javax.swing.JLabel();
        nomeFuncionarioTxt = new javax.swing.JTextField();
        sexoFuncionario = new javax.swing.JLabel();
        sexoFuncionarioTxt = new javax.swing.JComboBox<>();
        nascimentoFuncionario = new javax.swing.JLabel();
        anoNascFuncionarioTxt = new javax.swing.JComboBox<>();
        diaNascFuncionarioTxt = new javax.swing.JComboBox<>();
        mesNascFuncionarioTxt = new javax.swing.JComboBox<>();
        rgFuncionario = new javax.swing.JLabel();
        rgFuncionarioTxt = new javax.swing.JTextField();
        cpfFuncionario = new javax.swing.JLabel();
        cpfFuncionarioTxt = new javax.swing.JTextField();
        emailFuncionario = new javax.swing.JLabel();
        emaiFuncionarioTxt = new javax.swing.JTextField();
        telefoneFuncionario = new javax.swing.JLabel();
        telefoneFuncionarioTxt = new javax.swing.JTextField();
        dataContratacaoFuncionario = new javax.swing.JLabel();
        diaConNovoFunc = new javax.swing.JComboBox<>();
        mesConNovoFunc = new javax.swing.JComboBox<>();
        anoConNovoFunc = new javax.swing.JComboBox<>();
        cargaHorariaFuncionario = new javax.swing.JLabel();
        cargaHorariaFuncionarioTxt = new javax.swing.JComboBox<>();
        cargoFuncionario = new javax.swing.JLabel();
        cargoFuncionarioTxt = new javax.swing.JTextField();
        enderecoFuncionario = new javax.swing.JLabel();
        enderecoFuncionarioTxt = new javax.swing.JTextField();
        infoFuncionario = new javax.swing.JLabel();
        erroFunc = new javax.swing.JLabel();
        usuarioFuncionario = new javax.swing.JLabel();
        usuarioFuncionarioTxt = new javax.swing.JTextField();
        senhaFuncionario = new javax.swing.JLabel();
        senhaFuncionarioTxt = new javax.swing.JTextField();
        cadastrarFuncionarioBtn = new javax.swing.JButton();
        excluirFuncionario = new javax.swing.JPanel();
        selectFuncExcluirFunc = new javax.swing.JScrollPane();
        selectFuncReserva = new javax.swing.JTable();
        infoFuncExcluirFunc = new javax.swing.JScrollPane();
        descricaoFuncExcluir = new javax.swing.JTextPane();
        nomeFuncExcluirTxt = new javax.swing.JTextField();
        nomeFuncExcluir = new javax.swing.JLabel();
        excluirFunc = new javax.swing.JLabel();
        descFuncExcluir = new javax.swing.JLabel();
        excluirFuncBtn = new javax.swing.JButton();
        verFuncionario = new javax.swing.JPanel();
        selectFuncVerFunc = new javax.swing.JScrollPane();
        selectFuncVer = new javax.swing.JTable();
        infoFuncVerFunc = new javax.swing.JScrollPane();
        descricaoFuncVer = new javax.swing.JTextPane();
        nomeFuncVerTxt = new javax.swing.JTextField();
        nomeFuncVer = new javax.swing.JLabel();
        verFunc = new javax.swing.JLabel();
        descFuncVer = new javax.swing.JLabel();
        modificarFuncionario = new javax.swing.JPanel();
        UserPassMod = new javax.swing.JCheckBox();
        modFuncionario = new javax.swing.JLabel();
        nomeFuncionarioMod = new javax.swing.JLabel();
        nomeFuncTxtMod = new javax.swing.JTextField();
        sexoFuncionarioMod = new javax.swing.JLabel();
        sexoFuncTxtMod = new javax.swing.JComboBox<>();
        emailFuncionarioMod = new javax.swing.JLabel();
        emaiFuncTxtMod = new javax.swing.JTextField();
        telefoneFuncionarioMod = new javax.swing.JLabel();
        telefoneFuncTxtMod = new javax.swing.JTextField();
        cargaHorariaFuncionarioMod = new javax.swing.JLabel();
        cargaHorariaFuncTxtMod = new javax.swing.JComboBox<>();
        cargoFuncionarioMod = new javax.swing.JLabel();
        cargoFuncTxtMod = new javax.swing.JTextField();
        enderecoFuncionarioMod = new javax.swing.JLabel();
        enderecoFuncTxtMod = new javax.swing.JTextField();
        erroFuncMod = new javax.swing.JLabel();
        usuarioFuncionarioMod = new javax.swing.JLabel();
        usuarioFuncTxtMod = new javax.swing.JTextField();
        senhaFuncionarioMod = new javax.swing.JLabel();
        senhaFuncTxtMod = new javax.swing.JTextField();
        modificarFuncionarioBtn = new javax.swing.JButton();
        cpfModFuncTxt = new javax.swing.JTextField();
        insiraRgFunc = new javax.swing.JLabel();
        panelHospede = new javax.swing.JPanel();
        cadastrarHospede = new javax.swing.JPanel();
        cadastrarNovoHospede = new javax.swing.JLabel();
        nomeHospede = new javax.swing.JLabel();
        nomeHospedeTxt = new javax.swing.JTextField();
        sexoHospedeTxt = new javax.swing.JComboBox<>();
        sexoHospede = new javax.swing.JLabel();
        nascimentoHospede = new javax.swing.JLabel();
        diaNascHospedeTxt = new javax.swing.JComboBox<>();
        mesNascHospedeTxt = new javax.swing.JComboBox<>();
        anoNascHospedeTxt = new javax.swing.JComboBox<>();
        rgHospedeTxt = new javax.swing.JTextField();
        rgHospede = new javax.swing.JLabel();
        cpfHospede = new javax.swing.JLabel();
        cpfHospedeTxt = new javax.swing.JTextField();
        telefoneHospede = new javax.swing.JLabel();
        telefoneHospedeTxt = new javax.swing.JTextField();
        cadastrarHospedeBtn = new javax.swing.JButton();
        cidadeHospede = new javax.swing.JLabel();
        cidadeHospedeTxt = new javax.swing.JTextField();
        erroHospede = new javax.swing.JLabel();
        excluirHospede = new javax.swing.JPanel();
        selectHospExcluir = new javax.swing.JScrollPane();
        selectHospedeExcluir = new javax.swing.JTable();
        infoHospExcluirHosp = new javax.swing.JScrollPane();
        descHospExcluir = new javax.swing.JTextPane();
        nomeHospExcluirTxt = new javax.swing.JTextField();
        nomeHospExcluir = new javax.swing.JLabel();
        excluirHosp = new javax.swing.JLabel();
        infoHospExcluir = new javax.swing.JLabel();
        excluirHospBtn = new javax.swing.JButton();
        excluirReservasHosp = new javax.swing.JCheckBox();
        verHospede = new javax.swing.JPanel();
        selectHospVer = new javax.swing.JScrollPane();
        selectHospedeVer = new javax.swing.JTable();
        infoHospVerHosp = new javax.swing.JScrollPane();
        descHospVer = new javax.swing.JTextPane();
        nomeHospVerTxt = new javax.swing.JTextField();
        nomeHospVer = new javax.swing.JLabel();
        verHosp = new javax.swing.JLabel();
        infoHospVer = new javax.swing.JLabel();
        modificarHospede = new javax.swing.JPanel();
        modHospede = new javax.swing.JLabel();
        nomeHospedeMod = new javax.swing.JLabel();
        nomeHospedeTxtMod = new javax.swing.JTextField();
        sexoHospedeTxtMod = new javax.swing.JComboBox<>();
        sexoHospedeMod = new javax.swing.JLabel();
        cpfHospedeTxtMod = new javax.swing.JTextField();
        telefoneHospedeMod = new javax.swing.JLabel();
        telefoneHospedeTxtMod = new javax.swing.JTextField();
        modificarHospedeBtn = new javax.swing.JButton();
        cidadeHospedeMod = new javax.swing.JLabel();
        cidadeHospedeTxtMod = new javax.swing.JTextField();
        erroHospMod = new javax.swing.JLabel();
        insiraRgHosp = new javax.swing.JLabel();
        panelQuarto = new javax.swing.JPanel();
        cadastrarQuarto = new javax.swing.JPanel();
        cadastrarNovoQuarto = new javax.swing.JLabel();
        numQuarto = new javax.swing.JLabel();
        numQuartoTxt = new javax.swing.JTextField();
        andarQuarto = new javax.swing.JLabel();
        andarQuartoTxt = new javax.swing.JTextField();
        tipoQuarto = new javax.swing.JLabel();
        tipoQuartoTxt = new javax.swing.JTextField();
        descricaoQuarto = new javax.swing.JLabel();
        descricaoQuartoTxt = new javax.swing.JTextField();
        precoQuarto = new javax.swing.JLabel();
        cadastrarQuartoBtn = new javax.swing.JButton();
        erroQuarto = new javax.swing.JLabel();
        precoQuartoTxt = new javax.swing.JFormattedTextField();
        excluirQuarto = new javax.swing.JPanel();
        selectQuartoExcluir = new javax.swing.JScrollPane();
        selectQuartosExcluir = new javax.swing.JTable();
        numQuaExcluirTxt = new javax.swing.JTextField();
        numQuaExcluir = new javax.swing.JLabel();
        excluirQua = new javax.swing.JLabel();
        infoQuaExcluir = new javax.swing.JLabel();
        excluirQuaBtn = new javax.swing.JButton();
        excluirReservasQua = new javax.swing.JCheckBox();
        infoQuaExcluirQua = new javax.swing.JScrollPane();
        descQuaExcluir = new javax.swing.JTextPane();
        verQuarto = new javax.swing.JPanel();
        selectQuartoVer = new javax.swing.JScrollPane();
        selectQuartosVer = new javax.swing.JTable();
        numQuaVerTxt = new javax.swing.JTextField();
        numQuaVer = new javax.swing.JLabel();
        verQua = new javax.swing.JLabel();
        infoQuaVer = new javax.swing.JLabel();
        infoQuaVerQua = new javax.swing.JScrollPane();
        descQuaVer = new javax.swing.JTextPane();
        modificarQuarto = new javax.swing.JPanel();
        modQuarto = new javax.swing.JLabel();
        numQuartoMod = new javax.swing.JLabel();
        numQuartoTxtMod = new javax.swing.JTextField();
        andarQuartoMod = new javax.swing.JLabel();
        andarQuartoTxtMod = new javax.swing.JTextField();
        tipoQuartoMod = new javax.swing.JLabel();
        tipoQuartoTxtMod = new javax.swing.JTextField();
        descricaoQuartoMod = new javax.swing.JLabel();
        descricaoQuartoTxtMod = new javax.swing.JTextField();
        precoQuartoMod = new javax.swing.JLabel();
        modificarQuartoBtn = new javax.swing.JButton();
        erroQuaMod = new javax.swing.JLabel();
        disponibilidadeQuartoMod = new javax.swing.JLabel();
        disponibilidadeQuartoTxtMod = new javax.swing.JComboBox<>();
        precoQuartoTxtMod = new javax.swing.JFormattedTextField();
        panelReserva = new javax.swing.JPanel();
        cadastrarReserva = new javax.swing.JPanel();
        cadastrarNovaReserva = new javax.swing.JLabel();
        numQuartoReserva = new javax.swing.JLabel();
        numQuartoReservaTxt = new javax.swing.JTextField();
        idHospedeReserva = new javax.swing.JLabel();
        nomeHospReservaTxt = new javax.swing.JTextField();
        dataEntradaReserva = new javax.swing.JLabel();
        mesEntradaReserva = new javax.swing.JComboBox<>();
        anoEntradaReserva = new javax.swing.JComboBox<>();
        verQuartoReservaBtn = new javax.swing.JButton();
        dataSaidaReserva = new javax.swing.JLabel();
        diaSaidaReserva = new javax.swing.JComboBox<>();
        mesSaidaReserva = new javax.swing.JComboBox<>();
        anoSaidaReserva = new javax.swing.JComboBox<>();
        erroReserva = new javax.swing.JLabel();
        cadastrarReservaBtn = new javax.swing.JButton();
        diaEntradaReserva = new javax.swing.JComboBox<>();
        selectQuartosReservas = new javax.swing.JScrollPane();
        selectQuartoReserva = new javax.swing.JTable();
        selectHospedesReservas = new javax.swing.JScrollPane();
        selectHospedeReserva = new javax.swing.JTable();
        infoSelectReserva = new javax.swing.JLabel();
        infoTabelaReservas = new javax.swing.JScrollPane();
        descricaoQuartoReserva = new javax.swing.JTextPane();
        verHospedeReservaBtn = new javax.swing.JButton();
        tableNewRes = new javax.swing.JLabel();
        excluirReserva = new javax.swing.JPanel();
        selectReservaExcluir = new javax.swing.JScrollPane();
        selectReservasExcluir = new javax.swing.JTable();
        nomeResExcluirTxt = new javax.swing.JTextField();
        numResExcluir = new javax.swing.JLabel();
        excluirRes = new javax.swing.JLabel();
        infoResExcluir = new javax.swing.JLabel();
        infoResExcluirRes = new javax.swing.JScrollPane();
        descResExcluir = new javax.swing.JTextPane();
        excluirResBtn = new javax.swing.JButton();
        verReserva = new javax.swing.JPanel();
        selectReservaVer = new javax.swing.JScrollPane();
        selectReservasVer = new javax.swing.JTable();
        nomeResVerTxt = new javax.swing.JTextField();
        numResVer = new javax.swing.JLabel();
        verRes = new javax.swing.JLabel();
        infoResVer = new javax.swing.JLabel();
        infoResVerRes = new javax.swing.JScrollPane();
        descResVer = new javax.swing.JTextPane();
        modificarReserva = new javax.swing.JPanel();
        modReserva = new javax.swing.JLabel();
        InsiraIdRes = new javax.swing.JLabel();
        idReservaMod = new javax.swing.JTextField();
        idHospResMod = new javax.swing.JLabel();
        numQuaResModTxt = new javax.swing.JTextField();
        numQuaResMod = new javax.swing.JLabel();
        dataEntradaReservaMod = new javax.swing.JLabel();
        mesEntradaReservaMod = new javax.swing.JComboBox<>();
        anoEntradaReservaMod = new javax.swing.JComboBox<>();
        dataSaidaReservaMod = new javax.swing.JLabel();
        diaSaidaReservaMod = new javax.swing.JComboBox<>();
        mesSaidaReservaMod = new javax.swing.JComboBox<>();
        anoSaidaReservaMod = new javax.swing.JComboBox<>();
        diaEntradaReservaMod = new javax.swing.JComboBox<>();
        nomeHospResTxt = new javax.swing.JLabel();
        estadoResMod = new javax.swing.JLabel();
        idHospResModTxt = new javax.swing.JTextField();
        modificarReservaBtn = new javax.swing.JButton();
        nomeHospRes1 = new javax.swing.JLabel();
        erroResMod = new javax.swing.JLabel();
        consumoResMod = new javax.swing.JLabel();
        estadoResModTxt = new javax.swing.JComboBox<>();
        consumoResModTxt = new javax.swing.JFormattedTextField();
        panelPagamento = new javax.swing.JPanel();
        cadastrarPagamento = new javax.swing.JPanel();
        cadastrarNovoPag = new javax.swing.JLabel();
        valorPag = new javax.swing.JLabel();
        descPag = new javax.swing.JLabel();
        dataPag = new javax.swing.JLabel();
        diaPag = new javax.swing.JComboBox<>();
        mesPag = new javax.swing.JComboBox<>();
        anoPag = new javax.swing.JComboBox<>();
        formaPag = new javax.swing.JLabel();
        formaPagTxt = new javax.swing.JTextField();
        cadastrarPagBtn = new javax.swing.JButton();
        erroPag = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descPagTxt = new javax.swing.JTextArea();
        valorPagTxt = new javax.swing.JFormattedTextField();
        excluirPagamento = new javax.swing.JPanel();
        selectPagamentoExcluir = new javax.swing.JScrollPane();
        selectPagamentosExcluir = new javax.swing.JTable();
        valorPagExcluir = new javax.swing.JLabel();
        excluirPag = new javax.swing.JLabel();
        infoPagExcluir = new javax.swing.JLabel();
        excluirPagBtn = new javax.swing.JButton();
        infoPagExcluirPag = new javax.swing.JScrollPane();
        descPagExcluir = new javax.swing.JTextPane();
        descPagExcluirTxt = new javax.swing.JTextField();
        verPagamento = new javax.swing.JPanel();
        selectPagamentoVer = new javax.swing.JScrollPane();
        selectPagamentosVer = new javax.swing.JTable();
        valorPagVer = new javax.swing.JLabel();
        verPag = new javax.swing.JLabel();
        infoPagVer = new javax.swing.JLabel();
        infoPagVerPag = new javax.swing.JScrollPane();
        descPagVer = new javax.swing.JTextPane();
        descPagVerTxt = new javax.swing.JTextField();
        modificarPagamento = new javax.swing.JPanel();
        modPag = new javax.swing.JLabel();
        valorPagMod = new javax.swing.JLabel();
        descPagMod = new javax.swing.JLabel();
        dataPagMod = new javax.swing.JLabel();
        diaPagMod = new javax.swing.JComboBox<>();
        mesPagMod = new javax.swing.JComboBox<>();
        anoPagMod = new javax.swing.JComboBox<>();
        formaPagMod = new javax.swing.JLabel();
        modificarPagBtn = new javax.swing.JButton();
        erroPagMod = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        descPagModTxt = new javax.swing.JTextArea();
        idPagMod = new javax.swing.JLabel();
        idPagModTxt = new javax.swing.JTextField();
        formaPagModTxt = new javax.swing.JTextField();
        valorPagModTxt = new javax.swing.JFormattedTextField();
        panelLembrete = new javax.swing.JPanel();
        cadastrarLembrete = new javax.swing.JPanel();
        cadastrarNovoLem = new javax.swing.JLabel();
        descLem = new javax.swing.JLabel();
        dataLem = new javax.swing.JLabel();
        diaLem = new javax.swing.JComboBox<>();
        mesLem = new javax.swing.JComboBox<>();
        anoLem = new javax.swing.JComboBox<>();
        cadastrarLemBtn = new javax.swing.JButton();
        erroLem = new javax.swing.JLabel();
        assuntoLem = new javax.swing.JLabel();
        assuntoLemTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descLemTxt = new javax.swing.JTextArea();
        excluirLembrete = new javax.swing.JPanel();
        selectLembreteExcluir = new javax.swing.JScrollPane();
        selectLembretesExcluir = new javax.swing.JTable();
        descLemExcluirLabel = new javax.swing.JLabel();
        excluirLem = new javax.swing.JLabel();
        infoLemExcluir = new javax.swing.JLabel();
        excluirLemBtn = new javax.swing.JButton();
        infoLemExcluirLem = new javax.swing.JScrollPane();
        descLemExcluir = new javax.swing.JTextPane();
        descLemExcluirTxt = new javax.swing.JTextField();
        verLembrete = new javax.swing.JPanel();
        selectLembreteVer = new javax.swing.JScrollPane();
        selectLembretesVer = new javax.swing.JTable();
        descLemVerLabel = new javax.swing.JLabel();
        verLem = new javax.swing.JLabel();
        infoLemVer = new javax.swing.JLabel();
        infoLemVerLem = new javax.swing.JScrollPane();
        descLemVer = new javax.swing.JTextPane();
        descLemVerTxt = new javax.swing.JTextField();
        modificarLembrete = new javax.swing.JPanel();
        modLem = new javax.swing.JLabel();
        descLemMod = new javax.swing.JLabel();
        dataLemMod = new javax.swing.JLabel();
        diaLem1 = new javax.swing.JComboBox<>();
        mesLem1 = new javax.swing.JComboBox<>();
        anoLem1 = new javax.swing.JComboBox<>();
        cadastrarLemBtn1 = new javax.swing.JButton();
        erroLem1 = new javax.swing.JLabel();
        idLemMod = new javax.swing.JLabel();
        idLemModTxt = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        descLemModTxt = new javax.swing.JTextArea();
        assuntoLemMod = new javax.swing.JLabel();
        assuntoLemModTxt = new javax.swing.JTextField();
        info = new javax.swing.JPanel();
        func1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        func2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        func3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        func4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        line1 = new javax.swing.JPanel();
        line2 = new javax.swing.JPanel();
        line3 = new javax.swing.JPanel();
        line4 = new javax.swing.JPanel();
        iconF1 = new javax.swing.JPanel();
        iconF2 = new javax.swing.JPanel();
        iconF3 = new javax.swing.JPanel();
        iconF4 = new javax.swing.JPanel();
        func1ICON = new javax.swing.JLabel();
        func2ICON = new javax.swing.JLabel();
        func3ICON = new javax.swing.JLabel();
        func4ICON = new javax.swing.JLabel();
        equipe = new javax.swing.JLabel();
        logadoComo = new javax.swing.JLabel();
        logadoComoUsuario = new javax.swing.JLabel();
        logoInicio1 = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        relogio = new javax.swing.JLabel();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1100, 600));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelTopo.setBackground(new java.awt.Color(0, 46, 93));
        PanelTopo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sairTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-sair.png"))); // NOI18N
        sairTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sairTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sairTxtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sairTxtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sairTxtMouseExited(evt);
            }
        });
        PanelTopo.add(sairTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(916, 16, -1, 10));

        minimizarTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-minimizar.png"))); // NOI18N
        minimizarTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimizarTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizarTxtMouseClicked(evt);
            }
        });
        PanelTopo.add(minimizarTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(896, 16, 14, 10));

        getContentPane().add(PanelTopo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 940, 50));

        PanelEsquerdo.setBackground(new java.awt.Color(0, 30, 60));
        PanelEsquerdo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inicioBtn.setBackground(new java.awt.Color(0, 19, 39));
        inicioBtn.setToolTipText("");
        inicioBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inicioBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                inicioBtnMousePressed(evt);
            }
        });
        inicioBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(0, 93, 187));
        jPanel7.setPreferredSize(new java.awt.Dimension(3, 100));
        inicioBtn.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        inicioTxt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        inicioTxt.setForeground(new java.awt.Color(255, 255, 255));
        inicioTxt.setText("Início");
        inicioBtn.add(inicioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        PanelEsquerdo.add(inicioBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 160, -1));

        funcionarioBtn.setBackground(new java.awt.Color(0, 73, 147));
        funcionarioBtn.setToolTipText("");
        funcionarioBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        funcionarioBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                funcionarioBtnMousePressed(evt);
            }
        });
        funcionarioBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(0, 93, 187));
        jPanel9.setPreferredSize(new java.awt.Dimension(3, 100));
        funcionarioBtn.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        funcionarioTxt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        funcionarioTxt.setForeground(new java.awt.Color(255, 255, 255));
        funcionarioTxt.setText("Funcionários");
        funcionarioBtn.add(funcionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        PanelEsquerdo.add(funcionarioBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 160, 40));

        hospedeBtn.setBackground(new java.awt.Color(0, 73, 147));
        hospedeBtn.setToolTipText("");
        hospedeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hospedeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hospedeBtnMousePressed(evt);
            }
        });
        hospedeBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(0, 93, 187));
        jPanel10.setPreferredSize(new java.awt.Dimension(3, 100));
        hospedeBtn.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        hospedeTxt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        hospedeTxt.setForeground(new java.awt.Color(255, 255, 255));
        hospedeTxt.setText("Hóspedes");
        hospedeBtn.add(hospedeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        PanelEsquerdo.add(hospedeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 160, 40));

        quartoBtn.setBackground(new java.awt.Color(0, 73, 147));
        quartoBtn.setToolTipText("");
        quartoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quartoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                quartoBtnMousePressed(evt);
            }
        });
        quartoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(0, 93, 187));
        jPanel11.setPreferredSize(new java.awt.Dimension(3, 100));
        quartoBtn.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        quartoTxt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        quartoTxt.setForeground(new java.awt.Color(255, 255, 255));
        quartoTxt.setText("Quartos");
        quartoBtn.add(quartoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        PanelEsquerdo.add(quartoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 160, 40));

        reservaBtn.setBackground(new java.awt.Color(0, 73, 147));
        reservaBtn.setToolTipText("");
        reservaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reservaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reservaBtnMousePressed(evt);
            }
        });
        reservaBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(0, 93, 187));
        jPanel12.setPreferredSize(new java.awt.Dimension(3, 100));
        reservaBtn.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        reservaTxt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        reservaTxt.setForeground(new java.awt.Color(255, 255, 255));
        reservaTxt.setText("Reservas");
        reservaBtn.add(reservaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        PanelEsquerdo.add(reservaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 160, 40));

        pagamentoBtn.setBackground(new java.awt.Color(0, 73, 147));
        pagamentoBtn.setToolTipText("");
        pagamentoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pagamentoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pagamentoBtnMousePressed(evt);
            }
        });
        pagamentoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(0, 93, 187));
        jPanel13.setPreferredSize(new java.awt.Dimension(3, 100));
        pagamentoBtn.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        transacaoTxt1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        transacaoTxt1.setForeground(new java.awt.Color(255, 255, 255));
        transacaoTxt1.setText("Pagamentos");
        pagamentoBtn.add(transacaoTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        PanelEsquerdo.add(pagamentoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 160, 40));

        lembreteBtn.setBackground(new java.awt.Color(0, 73, 147));
        lembreteBtn.setToolTipText("");
        lembreteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lembreteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lembreteBtnMousePressed(evt);
            }
        });
        lembreteBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(0, 93, 187));
        jPanel15.setPreferredSize(new java.awt.Dimension(3, 100));
        lembreteBtn.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        lembretesTxt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lembretesTxt.setForeground(new java.awt.Color(255, 255, 255));
        lembretesTxt.setText("Lembretes");
        lembreteBtn.add(lembretesTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        PanelEsquerdo.add(lembreteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 160, 40));

        iconHotelTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-hotel.png"))); // NOI18N
        PanelEsquerdo.add(iconHotelTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 18, 140, 150));

        infoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/info-gray.png"))); // NOI18N
        infoIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                infoIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                infoIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                infoIconMouseExited(evt);
            }
        });
        PanelEsquerdo.add(infoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 565, -1, -1));

        getContentPane().add(PanelEsquerdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 600));

        FuncoesAbas.setBackground(new java.awt.Color(0, 46, 93));
        FuncoesAbas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        saudacaoQuadro.setBorder(null);

        quadro.setEditable(false);
        quadro.setBackground(new java.awt.Color(0, 93, 187));
        quadro.setColumns(20);
        quadro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quadro.setForeground(new java.awt.Color(255, 255, 255));
        quadro.setLineWrap(true);
        quadro.setRows(1);
        quadro.setText("Bom dia Administrador");
        quadro.setWrapStyleWord(true);
        quadro.setAutoscrolls(false);
        quadro.setFocusable(false);
        quadro.setMargin(new java.awt.Insets(9, 20, 0, 10));
        quadro.setMaximumSize(new java.awt.Dimension(290, 39));
        quadro.setMinimumSize(new java.awt.Dimension(290, 39));
        quadro.setRequestFocusEnabled(false);
        quadro.setSelectionColor(new java.awt.Color(0, 78, 158));
        saudacaoQuadro.setViewportView(quadro);
        quadro.getAccessibleContext().setAccessibleName("");
        quadro.getAccessibleContext().setAccessibleDescription("");

        FuncoesAbas.add(saudacaoQuadro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 60));

        cadastrarLemFuncaoBtn.setBackground(new java.awt.Color(0, 179, 136));
        cadastrarLemFuncaoBtn.setToolTipText("");
        cadastrarLemFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrarLemFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cadastrarLemFuncaoBtnMousePressed(evt);
            }
        });
        cadastrarLemFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel32.setBackground(new java.awt.Color(0, 114, 88));
        jPanel32.setPreferredSize(new java.awt.Dimension(3, 100));
        cadastrarLemFuncaoBtn.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        cadastrarLemFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cadastrarLemFuncao.setForeground(new java.awt.Color(255, 255, 255));
        cadastrarLemFuncao.setText("Cadastrar");
        cadastrarLemFuncaoBtn.add(cadastrarLemFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconC5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconC.png"))); // NOI18N
        cadastrarLemFuncaoBtn.add(iconC5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        FuncoesAbas.add(cadastrarLemFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 230, -1));

        cadastrarResFuncaoBtn.setBackground(new java.awt.Color(0, 179, 136));
        cadastrarResFuncaoBtn.setToolTipText("");
        cadastrarResFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrarResFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cadastrarResFuncaoBtnMousePressed(evt);
            }
        });
        cadastrarResFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel24.setBackground(new java.awt.Color(0, 114, 88));
        jPanel24.setPreferredSize(new java.awt.Dimension(3, 100));
        cadastrarResFuncaoBtn.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        cadastrarResFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cadastrarResFuncao.setForeground(new java.awt.Color(255, 255, 255));
        cadastrarResFuncao.setText("Cadastrar");
        cadastrarResFuncaoBtn.add(cadastrarResFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconC.png"))); // NOI18N
        cadastrarResFuncaoBtn.add(iconC, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 50, 40));

        FuncoesAbas.add(cadastrarResFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 230, 40));

        cadastrarHospFuncaoBtn.setBackground(new java.awt.Color(0, 179, 136));
        cadastrarHospFuncaoBtn.setToolTipText("");
        cadastrarHospFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrarHospFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cadastrarHospFuncaoBtnMousePressed(evt);
            }
        });
        cadastrarHospFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(0, 114, 88));
        jPanel17.setPreferredSize(new java.awt.Dimension(3, 100));
        cadastrarHospFuncaoBtn.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        cadastrarHospFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cadastrarHospFuncao.setForeground(new java.awt.Color(255, 255, 255));
        cadastrarHospFuncao.setText("Cadastrar");
        cadastrarHospFuncaoBtn.add(cadastrarHospFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconC4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconC.png"))); // NOI18N
        cadastrarHospFuncaoBtn.add(iconC4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 50, 40));

        FuncoesAbas.add(cadastrarHospFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 230, 40));

        cadastrarPagFuncaoBtn.setBackground(new java.awt.Color(0, 179, 136));
        cadastrarPagFuncaoBtn.setToolTipText("");
        cadastrarPagFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrarPagFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cadastrarPagFuncaoBtnMousePressed(evt);
            }
        });
        cadastrarPagFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel27.setBackground(new java.awt.Color(0, 114, 88));
        jPanel27.setPreferredSize(new java.awt.Dimension(3, 100));
        cadastrarPagFuncaoBtn.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        cadastrarPagFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cadastrarPagFuncao.setForeground(new java.awt.Color(255, 255, 255));
        cadastrarPagFuncao.setText("Cadastrar");
        cadastrarPagFuncaoBtn.add(cadastrarPagFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconC2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconC.png"))); // NOI18N
        cadastrarPagFuncaoBtn.add(iconC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 50, 40));

        FuncoesAbas.add(cadastrarPagFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 230, 40));

        cadastrarQuaFuncaoBtn.setBackground(new java.awt.Color(0, 179, 136));
        cadastrarQuaFuncaoBtn.setToolTipText("");
        cadastrarQuaFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrarQuaFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cadastrarQuaFuncaoBtnMousePressed(evt);
            }
        });
        cadastrarQuaFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(0, 114, 88));
        jPanel21.setPreferredSize(new java.awt.Dimension(3, 100));
        cadastrarQuaFuncaoBtn.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        cadastrarQuaFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cadastrarQuaFuncao.setForeground(new java.awt.Color(255, 255, 255));
        cadastrarQuaFuncao.setText("Cadastrar");
        cadastrarQuaFuncaoBtn.add(cadastrarQuaFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconC3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconC.png"))); // NOI18N
        cadastrarQuaFuncaoBtn.add(iconC3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 50, 40));

        FuncoesAbas.add(cadastrarQuaFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 230, 40));

        cadastrarFuncFuncaoBtn.setBackground(new java.awt.Color(0, 179, 136));
        cadastrarFuncFuncaoBtn.setToolTipText("");
        cadastrarFuncFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrarFuncFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cadastrarFuncFuncaoBtnMousePressed(evt);
            }
        });
        cadastrarFuncFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(0, 114, 88));
        jPanel14.setPreferredSize(new java.awt.Dimension(3, 100));
        cadastrarFuncFuncaoBtn.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        cadastrarFunciFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cadastrarFunciFuncao.setForeground(new java.awt.Color(255, 255, 255));
        cadastrarFunciFuncao.setText("Cadastrar");
        cadastrarFuncFuncaoBtn.add(cadastrarFunciFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconC.png"))); // NOI18N
        cadastrarFuncFuncaoBtn.add(iconC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 50, 40));

        FuncoesAbas.add(cadastrarFuncFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 230, 40));

        excluirLemFuncaoBtn.setBackground(new java.awt.Color(226, 0, 0));
        excluirLemFuncaoBtn.setToolTipText("");
        excluirLemFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluirLemFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                excluirLemFuncaoBtnMousePressed(evt);
            }
        });
        excluirLemFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel31.setBackground(new java.awt.Color(114, 0, 0));
        jPanel31.setPreferredSize(new java.awt.Dimension(3, 100));
        excluirLemFuncaoBtn.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        excluirLemFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        excluirLemFuncao.setForeground(new java.awt.Color(255, 255, 255));
        excluirLemFuncao.setText("Excluir");
        excluirLemFuncaoBtn.add(excluirLemFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconE.png"))); // NOI18N
        excluirLemFuncaoBtn.add(iconE5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        FuncoesAbas.add(excluirLemFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 230, 40));

        excluirResFuncaoBtn.setBackground(new java.awt.Color(226, 0, 0));
        excluirResFuncaoBtn.setToolTipText("");
        excluirResFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluirResFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                excluirResFuncaoBtnMousePressed(evt);
            }
        });
        excluirResFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel25.setBackground(new java.awt.Color(114, 0, 0));
        jPanel25.setPreferredSize(new java.awt.Dimension(3, 100));
        excluirResFuncaoBtn.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        excluirResFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        excluirResFuncao.setForeground(new java.awt.Color(255, 255, 255));
        excluirResFuncao.setText("Excluir");
        excluirResFuncaoBtn.add(excluirResFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconE.png"))); // NOI18N
        excluirResFuncaoBtn.add(iconE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        FuncoesAbas.add(excluirResFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 230, 40));

        excluirQuaFuncaoBtn.setBackground(new java.awt.Color(226, 0, 0));
        excluirQuaFuncaoBtn.setToolTipText("");
        excluirQuaFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluirQuaFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                excluirQuaFuncaoBtnMousePressed(evt);
            }
        });
        excluirQuaFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(114, 0, 0));
        jPanel22.setPreferredSize(new java.awt.Dimension(3, 100));
        excluirQuaFuncaoBtn.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        excluirQuaFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        excluirQuaFuncao.setForeground(new java.awt.Color(255, 255, 255));
        excluirQuaFuncao.setText("Excluir");
        excluirQuaFuncaoBtn.add(excluirQuaFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconE.png"))); // NOI18N
        excluirQuaFuncaoBtn.add(iconE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        FuncoesAbas.add(excluirQuaFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 230, 40));

        excluirHospFuncaoBtn.setBackground(new java.awt.Color(226, 0, 0));
        excluirHospFuncaoBtn.setToolTipText("");
        excluirHospFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluirHospFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                excluirHospFuncaoBtnMousePressed(evt);
            }
        });
        excluirHospFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(114, 0, 0));
        jPanel18.setPreferredSize(new java.awt.Dimension(3, 100));
        excluirHospFuncaoBtn.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        excluirHospFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        excluirHospFuncao.setForeground(new java.awt.Color(255, 255, 255));
        excluirHospFuncao.setText("Excluir");
        excluirHospFuncaoBtn.add(excluirHospFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconE.png"))); // NOI18N
        excluirHospFuncaoBtn.add(iconE, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        FuncoesAbas.add(excluirHospFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 230, 40));

        excluirFuncFuncaoBtn.setBackground(new java.awt.Color(226, 0, 0));
        excluirFuncFuncaoBtn.setToolTipText("");
        excluirFuncFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluirFuncFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                excluirFuncFuncaoBtnMousePressed(evt);
            }
        });
        excluirFuncFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel20.setBackground(new java.awt.Color(114, 0, 0));
        jPanel20.setPreferredSize(new java.awt.Dimension(3, 100));
        excluirFuncFuncaoBtn.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        excluirFuncFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        excluirFuncFuncao.setForeground(new java.awt.Color(255, 255, 255));
        excluirFuncFuncao.setText("Excluir");
        excluirFuncFuncaoBtn.add(excluirFuncFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconE.png"))); // NOI18N
        excluirFuncFuncaoBtn.add(iconE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        FuncoesAbas.add(excluirFuncFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 230, 40));

        excluirPagFuncaoBtn.setBackground(new java.awt.Color(226, 0, 0));
        excluirPagFuncaoBtn.setToolTipText("");
        excluirPagFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluirPagFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                excluirPagFuncaoBtnMousePressed(evt);
            }
        });
        excluirPagFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel28.setBackground(new java.awt.Color(114, 0, 0));
        jPanel28.setPreferredSize(new java.awt.Dimension(3, 100));
        excluirPagFuncaoBtn.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        excluirPagFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        excluirPagFuncao.setForeground(new java.awt.Color(255, 255, 255));
        excluirPagFuncao.setText("Excluir");
        excluirPagFuncaoBtn.add(excluirPagFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconE.png"))); // NOI18N
        excluirPagFuncaoBtn.add(iconE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        FuncoesAbas.add(excluirPagFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 230, -1));

        verResFuncaoBtn.setBackground(new java.awt.Color(226, 128, 0));
        verResFuncaoBtn.setToolTipText("");
        verResFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verResFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                verResFuncaoBtnMousePressed(evt);
            }
        });
        verResFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel26.setBackground(new java.awt.Color(114, 75, 0));
        jPanel26.setPreferredSize(new java.awt.Dimension(3, 100));
        verResFuncaoBtn.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        verResFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        verResFuncao.setForeground(new java.awt.Color(255, 255, 255));
        verResFuncao.setText("Ver");
        verResFuncaoBtn.add(verResFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconO1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconO.png"))); // NOI18N
        verResFuncaoBtn.add(iconO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        FuncoesAbas.add(verResFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 230, 40));

        verHospFuncaoBtn.setBackground(new java.awt.Color(226, 128, 0));
        verHospFuncaoBtn.setToolTipText("");
        verHospFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verHospFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                verHospFuncaoBtnMousePressed(evt);
            }
        });
        verHospFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(114, 75, 0));
        jPanel16.setPreferredSize(new java.awt.Dimension(3, 100));
        verHospFuncaoBtn.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        verHospFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        verHospFuncao.setForeground(new java.awt.Color(255, 255, 255));
        verHospFuncao.setText("Ver");
        verHospFuncaoBtn.add(verHospFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconO4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconO.png"))); // NOI18N
        verHospFuncaoBtn.add(iconO4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        FuncoesAbas.add(verHospFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 230, 40));

        verFuncFuncaoBtn.setBackground(new java.awt.Color(226, 128, 0));
        verFuncFuncaoBtn.setToolTipText("");
        verFuncFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verFuncFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                verFuncFuncaoBtnMousePressed(evt);
            }
        });
        verFuncFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel19.setBackground(new java.awt.Color(114, 75, 0));
        jPanel19.setPreferredSize(new java.awt.Dimension(3, 100));
        verFuncFuncaoBtn.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        verFuncFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        verFuncFuncao.setForeground(new java.awt.Color(255, 255, 255));
        verFuncFuncao.setText("Ver");
        verFuncFuncaoBtn.add(verFuncFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconO3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconO.png"))); // NOI18N
        verFuncFuncaoBtn.add(iconO3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        FuncoesAbas.add(verFuncFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 230, 40));

        verQuaFuncaoBtn.setBackground(new java.awt.Color(226, 128, 0));
        verQuaFuncaoBtn.setToolTipText("");
        verQuaFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verQuaFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                verQuaFuncaoBtnMousePressed(evt);
            }
        });
        verQuaFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(114, 75, 0));
        jPanel23.setPreferredSize(new java.awt.Dimension(3, 100));
        verQuaFuncaoBtn.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        verQuaFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        verQuaFuncao.setForeground(new java.awt.Color(255, 255, 255));
        verQuaFuncao.setText("Ver");
        verQuaFuncaoBtn.add(verQuaFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconO2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconO.png"))); // NOI18N
        verQuaFuncaoBtn.add(iconO2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        FuncoesAbas.add(verQuaFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 230, 40));

        verLemFuncaoBtn.setBackground(new java.awt.Color(226, 128, 0));
        verLemFuncaoBtn.setToolTipText("");
        verLemFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verLemFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                verLemFuncaoBtnMousePressed(evt);
            }
        });
        verLemFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel30.setBackground(new java.awt.Color(114, 75, 0));
        jPanel30.setPreferredSize(new java.awt.Dimension(3, 100));
        verLemFuncaoBtn.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        verLemFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        verLemFuncao.setForeground(new java.awt.Color(255, 255, 255));
        verLemFuncao.setText("Ver");
        verLemFuncaoBtn.add(verLemFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconO5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconO.png"))); // NOI18N
        verLemFuncaoBtn.add(iconO5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        FuncoesAbas.add(verLemFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 230, 40));

        verPagFuncaoBtn.setBackground(new java.awt.Color(226, 128, 0));
        verPagFuncaoBtn.setToolTipText("");
        verPagFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verPagFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                verPagFuncaoBtnMousePressed(evt);
            }
        });
        verPagFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel29.setBackground(new java.awt.Color(114, 75, 0));
        jPanel29.setPreferredSize(new java.awt.Dimension(3, 100));
        verPagFuncaoBtn.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        verPagFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        verPagFuncao.setForeground(new java.awt.Color(255, 255, 255));
        verPagFuncao.setText("Ver");
        verPagFuncaoBtn.add(verPagFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconO.png"))); // NOI18N
        verPagFuncaoBtn.add(iconO, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        FuncoesAbas.add(verPagFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 230, 40));

        modResFuncaoBtn.setBackground(new java.awt.Color(171, 71, 255));
        modResFuncaoBtn.setToolTipText("");
        modResFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modResFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                modResFuncaoBtnMousePressed(evt);
            }
        });
        modResFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel33.setBackground(new java.awt.Color(102, 0, 102));
        jPanel33.setPreferredSize(new java.awt.Dimension(3, 100));
        modResFuncaoBtn.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        modResFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        modResFuncao.setForeground(new java.awt.Color(255, 255, 255));
        modResFuncao.setText("Editar");
        modResFuncaoBtn.add(modResFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconO6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconM.png"))); // NOI18N
        modResFuncaoBtn.add(iconO6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, -1));

        FuncoesAbas.add(modResFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 230, 40));

        modHospFuncaoBtn.setBackground(new java.awt.Color(140, 0, 240));
        modHospFuncaoBtn.setToolTipText("");
        modHospFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modHospFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                modHospFuncaoBtnMousePressed(evt);
            }
        });
        modHospFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel34.setBackground(new java.awt.Color(102, 0, 102));
        jPanel34.setPreferredSize(new java.awt.Dimension(3, 100));
        modHospFuncaoBtn.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        modHospFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        modHospFuncao.setForeground(new java.awt.Color(255, 255, 255));
        modHospFuncao.setText("Editar");
        modHospFuncaoBtn.add(modHospFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconO7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconM.png"))); // NOI18N
        modHospFuncaoBtn.add(iconO7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, -1));

        FuncoesAbas.add(modHospFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 230, 40));

        modFuncFuncaoBtn.setBackground(new java.awt.Color(140, 0, 240));
        modFuncFuncaoBtn.setToolTipText("");
        modFuncFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modFuncFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                modFuncFuncaoBtnMousePressed(evt);
            }
        });
        modFuncFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel35.setBackground(new java.awt.Color(102, 0, 102));
        jPanel35.setPreferredSize(new java.awt.Dimension(3, 100));
        modFuncFuncaoBtn.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        modFuncFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        modFuncFuncao.setForeground(new java.awt.Color(255, 255, 255));
        modFuncFuncao.setText("Editar");
        modFuncFuncaoBtn.add(modFuncFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconO8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconM.png"))); // NOI18N
        modFuncFuncaoBtn.add(iconO8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, -1));

        FuncoesAbas.add(modFuncFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 230, 40));

        modQuaFuncaoBtn.setBackground(new java.awt.Color(140, 0, 240));
        modQuaFuncaoBtn.setToolTipText("");
        modQuaFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modQuaFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                modQuaFuncaoBtnMousePressed(evt);
            }
        });
        modQuaFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel36.setBackground(new java.awt.Color(102, 0, 102));
        jPanel36.setPreferredSize(new java.awt.Dimension(3, 100));
        modQuaFuncaoBtn.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        modQuaFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        modQuaFuncao.setForeground(new java.awt.Color(255, 255, 255));
        modQuaFuncao.setText("Editar");
        modQuaFuncaoBtn.add(modQuaFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconO9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconM.png"))); // NOI18N
        modQuaFuncaoBtn.add(iconO9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, -1));

        FuncoesAbas.add(modQuaFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 230, 40));

        modLemFuncaoBtn.setBackground(new java.awt.Color(140, 0, 240));
        modLemFuncaoBtn.setToolTipText("");
        modLemFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modLemFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                modLemFuncaoBtnMousePressed(evt);
            }
        });
        modLemFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel37.setBackground(new java.awt.Color(102, 0, 102));
        jPanel37.setPreferredSize(new java.awt.Dimension(3, 100));
        modLemFuncaoBtn.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        modLemFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        modLemFuncao.setForeground(new java.awt.Color(255, 255, 255));
        modLemFuncao.setText("Editar");
        modLemFuncaoBtn.add(modLemFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconO10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconM.png"))); // NOI18N
        modLemFuncaoBtn.add(iconO10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, -1));

        FuncoesAbas.add(modLemFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 230, 40));

        modPagFuncaoBtn.setBackground(new java.awt.Color(140, 0, 240));
        modPagFuncaoBtn.setToolTipText("");
        modPagFuncaoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modPagFuncaoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                modPagFuncaoBtnMousePressed(evt);
            }
        });
        modPagFuncaoBtn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel38.setBackground(new java.awt.Color(102, 0, 102));
        jPanel38.setPreferredSize(new java.awt.Dimension(3, 100));
        modPagFuncaoBtn.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        modPagFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        modPagFuncao.setForeground(new java.awt.Color(255, 255, 255));
        modPagFuncao.setText("Editar");
        modPagFuncaoBtn.add(modPagFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        iconO11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconM.png"))); // NOI18N
        modPagFuncaoBtn.add(iconO11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, -1));

        FuncoesAbas.add(modPagFuncaoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 230, 40));

        getContentPane().add(FuncoesAbas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 230, 550));

        PanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        PanelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        gambiarraLinhaBranca.setBackground(new java.awt.Color(255, 255, 255));
        PanelPrincipal.add(gambiarraLinhaBranca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 710, 10));

        panelInicio.setBackground(new java.awt.Color(245, 245, 245));
        panelInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/logoInicio.png"))); // NOI18N
        logoInicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        panelInicio.add(logoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, 540));

        PanelPrincipal.add(panelInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 710, 490));

        panelFuncionario.setBackground(new java.awt.Color(245, 245, 245));
        panelFuncionario.setMaximumSize(new java.awt.Dimension(710, 500));
        panelFuncionario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cadastrarFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        cadastrarFuncionario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        UserPassNew.setBackground(new java.awt.Color(255, 255, 255));
        UserPassNew.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        UserPassNew.setText("Com usuário e senha");
        UserPassNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserPassNewMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                UserPassNewMouseReleased(evt);
            }
        });
        cadastrarFuncionario.add(UserPassNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 423, 130, 30));

        cadastrarNovoFuncionario.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cadastrarNovoFuncionario.setForeground(new java.awt.Color(0, 93, 187));
        cadastrarNovoFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cadastrarNovoFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconCF.png"))); // NOI18N
        cadastrarFuncionario.add(cadastrarNovoFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        nomeFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeFuncionario.setForeground(new java.awt.Color(77, 77, 77));
        nomeFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nomeFuncionario.setText("Nome");
        cadastrarFuncionario.add(nomeFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 128, 30));

        nomeFuncionarioTxt.setBackground(new java.awt.Color(204, 204, 204));
        nomeFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeFuncionarioTxt.setForeground(new java.awt.Color(77, 77, 77));
        nomeFuncionarioTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nomeFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomeFuncionarioTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomeFuncionarioTxtKeyTyped(evt);
            }
        });
        cadastrarFuncionario.add(nomeFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 360, 30));

        sexoFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sexoFuncionario.setForeground(new java.awt.Color(77, 77, 77));
        sexoFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sexoFuncionario.setText("Sexo");
        cadastrarFuncionario.add(sexoFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 128, 30));

        sexoFuncionarioTxt.setBackground(new java.awt.Color(0, 179, 136));
        sexoFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sexoFuncionarioTxt.setForeground(new java.awt.Color(255, 255, 255));
        sexoFuncionarioTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F", "M", "Outros" }));
        sexoFuncionarioTxt.setBorder(null);
        sexoFuncionarioTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sexoFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sexoFuncionarioTxtKeyPressed(evt);
            }
        });
        cadastrarFuncionario.add(sexoFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 70, 30));

        nascimentoFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nascimentoFuncionario.setForeground(new java.awt.Color(77, 77, 77));
        nascimentoFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nascimentoFuncionario.setText("Nascimento");
        cadastrarFuncionario.add(nascimentoFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, -1, 30));

        anoNascFuncionarioTxt.setBackground(new java.awt.Color(0, 179, 136));
        anoNascFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        anoNascFuncionarioTxt.setForeground(new java.awt.Color(255, 255, 255));
        anoNascFuncionarioTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
        anoNascFuncionarioTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anoNascFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                anoNascFuncionarioTxtKeyPressed(evt);
            }
        });
        cadastrarFuncionario.add(anoNascFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 60, 30));

        diaNascFuncionarioTxt.setBackground(new java.awt.Color(0, 179, 136));
        diaNascFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diaNascFuncionarioTxt.setForeground(new java.awt.Color(255, 255, 255));
        diaNascFuncionarioTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        diaNascFuncionarioTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        diaNascFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diaNascFuncionarioTxtKeyPressed(evt);
            }
        });
        cadastrarFuncionario.add(diaNascFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, 30));

        mesNascFuncionarioTxt.setBackground(new java.awt.Color(0, 179, 136));
        mesNascFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesNascFuncionarioTxt.setForeground(new java.awt.Color(255, 255, 255));
        mesNascFuncionarioTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        mesNascFuncionarioTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mesNascFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mesNascFuncionarioTxtKeyPressed(evt);
            }
        });
        cadastrarFuncionario.add(mesNascFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, -1, 30));

        rgFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rgFuncionario.setForeground(new java.awt.Color(77, 77, 77));
        rgFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rgFuncionario.setText("RG");
        cadastrarFuncionario.add(rgFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 128, 30));

        rgFuncionarioTxt.setBackground(new java.awt.Color(204, 204, 204));
        rgFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rgFuncionarioTxt.setForeground(new java.awt.Color(77, 77, 77));
        rgFuncionarioTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        rgFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rgFuncionarioTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rgFuncionarioTxtKeyTyped(evt);
            }
        });
        cadastrarFuncionario.add(rgFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 360, 30));

        cpfFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cpfFuncionario.setForeground(new java.awt.Color(77, 77, 77));
        cpfFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cpfFuncionario.setText("CPF");
        cadastrarFuncionario.add(cpfFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 128, 30));

        cpfFuncionarioTxt.setBackground(new java.awt.Color(204, 204, 204));
        cpfFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cpfFuncionarioTxt.setForeground(new java.awt.Color(77, 77, 77));
        cpfFuncionarioTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cpfFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cpfFuncionarioTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cpfFuncionarioTxtKeyTyped(evt);
            }
        });
        cadastrarFuncionario.add(cpfFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 360, 30));

        emailFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailFuncionario.setForeground(new java.awt.Color(77, 77, 77));
        emailFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        emailFuncionario.setText("Email");
        cadastrarFuncionario.add(emailFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 128, 30));

        emaiFuncionarioTxt.setBackground(new java.awt.Color(204, 204, 204));
        emaiFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emaiFuncionarioTxt.setForeground(new java.awt.Color(77, 77, 77));
        emaiFuncionarioTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        emaiFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emaiFuncionarioTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emaiFuncionarioTxtKeyTyped(evt);
            }
        });
        cadastrarFuncionario.add(emaiFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 360, 30));

        telefoneFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telefoneFuncionario.setForeground(new java.awt.Color(77, 77, 77));
        telefoneFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        telefoneFuncionario.setText("Telefone");
        cadastrarFuncionario.add(telefoneFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 128, 30));

        telefoneFuncionarioTxt.setBackground(new java.awt.Color(204, 204, 204));
        telefoneFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telefoneFuncionarioTxt.setForeground(new java.awt.Color(77, 77, 77));
        telefoneFuncionarioTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        telefoneFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                telefoneFuncionarioTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefoneFuncionarioTxtKeyTyped(evt);
            }
        });
        cadastrarFuncionario.add(telefoneFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 360, 30));

        dataContratacaoFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dataContratacaoFuncionario.setForeground(new java.awt.Color(77, 77, 77));
        dataContratacaoFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dataContratacaoFuncionario.setText("Data de Contratação");
        cadastrarFuncionario.add(dataContratacaoFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, 30));

        diaConNovoFunc.setBackground(new java.awt.Color(0, 179, 136));
        diaConNovoFunc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diaConNovoFunc.setForeground(new java.awt.Color(255, 255, 255));
        diaConNovoFunc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        diaConNovoFunc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        diaConNovoFunc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diaConNovoFuncKeyPressed(evt);
            }
        });
        cadastrarFuncionario.add(diaConNovoFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, -1, 30));

        mesConNovoFunc.setBackground(new java.awt.Color(0, 179, 136));
        mesConNovoFunc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesConNovoFunc.setForeground(new java.awt.Color(255, 255, 255));
        mesConNovoFunc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        mesConNovoFunc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mesConNovoFunc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mesConNovoFuncKeyPressed(evt);
            }
        });
        cadastrarFuncionario.add(mesConNovoFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, -1, 30));

        anoConNovoFunc.setBackground(new java.awt.Color(0, 179, 136));
        anoConNovoFunc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        anoConNovoFunc.setForeground(new java.awt.Color(255, 255, 255));
        anoConNovoFunc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
        anoConNovoFunc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anoConNovoFunc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                anoConNovoFuncKeyPressed(evt);
            }
        });
        cadastrarFuncionario.add(anoConNovoFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, -1, 30));

        cargaHorariaFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cargaHorariaFuncionario.setForeground(new java.awt.Color(77, 77, 77));
        cargaHorariaFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cargaHorariaFuncionario.setText("Carga Horária Diária");
        cadastrarFuncionario.add(cargaHorariaFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, -1, 30));

        cargaHorariaFuncionarioTxt.setBackground(new java.awt.Color(0, 179, 136));
        cargaHorariaFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cargaHorariaFuncionarioTxt.setForeground(new java.awt.Color(255, 255, 255));
        cargaHorariaFuncionarioTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cargaHorariaFuncionarioTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cargaHorariaFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cargaHorariaFuncionarioTxtKeyPressed(evt);
            }
        });
        cadastrarFuncionario.add(cargaHorariaFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, -1, 30));

        cargoFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cargoFuncionario.setForeground(new java.awt.Color(77, 77, 77));
        cargoFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cargoFuncionario.setText("Cargo");
        cadastrarFuncionario.add(cargoFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 128, 30));

        cargoFuncionarioTxt.setBackground(new java.awt.Color(204, 204, 204));
        cargoFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cargoFuncionarioTxt.setForeground(new java.awt.Color(77, 77, 77));
        cargoFuncionarioTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cargoFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cargoFuncionarioTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cargoFuncionarioTxtKeyTyped(evt);
            }
        });
        cadastrarFuncionario.add(cargoFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 360, 30));

        enderecoFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        enderecoFuncionario.setForeground(new java.awt.Color(77, 77, 77));
        enderecoFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        enderecoFuncionario.setText("Endereço");
        cadastrarFuncionario.add(enderecoFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 128, 30));

        enderecoFuncionarioTxt.setBackground(new java.awt.Color(204, 204, 204));
        enderecoFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        enderecoFuncionarioTxt.setForeground(new java.awt.Color(77, 77, 77));
        enderecoFuncionarioTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        enderecoFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enderecoFuncionarioTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                enderecoFuncionarioTxtKeyTyped(evt);
            }
        });
        cadastrarFuncionario.add(enderecoFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 360, 30));

        infoFuncionario.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        infoFuncionario.setText("*A data e a hora do cadastro é inserida automaticamente");
        cadastrarFuncionario.add(infoFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, 20));

        erroFunc.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        erroFunc.setForeground(new java.awt.Color(255, 0, 0));
        erroFunc.setText("erro");
        cadastrarFuncionario.add(erroFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 370, 20));

        usuarioFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        usuarioFuncionario.setForeground(new java.awt.Color(77, 77, 77));
        usuarioFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        usuarioFuncionario.setText("Usuário");
        cadastrarFuncionario.add(usuarioFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 140, 30));

        usuarioFuncionarioTxt.setBackground(new java.awt.Color(204, 204, 204));
        usuarioFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        usuarioFuncionarioTxt.setForeground(new java.awt.Color(77, 77, 77));
        usuarioFuncionarioTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        usuarioFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuarioFuncionarioTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usuarioFuncionarioTxtKeyTyped(evt);
            }
        });
        cadastrarFuncionario.add(usuarioFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 130, 30));

        senhaFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        senhaFuncionario.setForeground(new java.awt.Color(77, 77, 77));
        senhaFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        senhaFuncionario.setText("Senha");
        cadastrarFuncionario.add(senhaFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 70, 30));

        senhaFuncionarioTxt.setBackground(new java.awt.Color(204, 204, 204));
        senhaFuncionarioTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        senhaFuncionarioTxt.setForeground(new java.awt.Color(77, 77, 77));
        senhaFuncionarioTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        senhaFuncionarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                senhaFuncionarioTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                senhaFuncionarioTxtKeyTyped(evt);
            }
        });
        cadastrarFuncionario.add(senhaFuncionarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, 130, 30));

        cadastrarFuncionarioBtn.setBackground(new java.awt.Color(0, 179, 136));
        cadastrarFuncionarioBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cadastrarFuncionarioBtn.setForeground(new java.awt.Color(255, 255, 255));
        cadastrarFuncionarioBtn.setText("Cadastrar");
        cadastrarFuncionarioBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrarFuncionarioBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrarFuncionarioBtnMouseClicked(evt);
            }
        });
        cadastrarFuncionario.add(cadastrarFuncionarioBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, -1, 30));

        panelFuncionario.add(cadastrarFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        excluirFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        excluirFuncionario.setMaximumSize(new java.awt.Dimension(710, 500));
        excluirFuncionario.setMinimumSize(new java.awt.Dimension(710, 500));
        excluirFuncionario.setPreferredSize(new java.awt.Dimension(710, 500));
        excluirFuncionario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectFuncReserva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectFuncReserva.setForeground(new java.awt.Color(77, 77, 77));
        selectFuncReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Sexo", "Nasc", "Endereço", "Data Contratação", "Cargo", "Carga horária", "CPF", "RG", "Telefone", "Email", "Data Cadastro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectFuncReserva.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        selectFuncReserva.getTableHeader().setReorderingAllowed(false);
        selectFuncReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectFuncReservaMouseClicked(evt);
            }
        });
        selectFuncExcluirFunc.setViewportView(selectFuncReserva);

        excluirFuncionario.add(selectFuncExcluirFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 670, 180));

        descricaoFuncExcluir.setEditable(false);
        descricaoFuncExcluir.setBackground(new java.awt.Color(240, 240, 240));
        descricaoFuncExcluir.setBorder(null);
        descricaoFuncExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descricaoFuncExcluir.setForeground(new java.awt.Color(77, 77, 77));
        infoFuncExcluirFunc.setViewportView(descricaoFuncExcluir);

        excluirFuncionario.add(infoFuncExcluirFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 530, 170));

        nomeFuncExcluirTxt.setBackground(new java.awt.Color(204, 204, 204));
        nomeFuncExcluirTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeFuncExcluirTxt.setForeground(new java.awt.Color(77, 77, 77));
        nomeFuncExcluirTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nomeFuncExcluirTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomeFuncExcluirTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomeFuncExcluirTxtKeyTyped(evt);
            }
        });
        excluirFuncionario.add(nomeFuncExcluirTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 190, 30));

        nomeFuncExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeFuncExcluir.setForeground(new java.awt.Color(77, 77, 77));
        nomeFuncExcluir.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nomeFuncExcluir.setText("Nome");
        excluirFuncionario.add(nomeFuncExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 40, 30));

        excluirFunc.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        excluirFunc.setForeground(new java.awt.Color(0, 93, 187));
        excluirFunc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        excluirFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconExcluirF.png"))); // NOI18N
        excluirFuncionario.add(excluirFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        descFuncExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descFuncExcluir.setForeground(new java.awt.Color(77, 77, 77));
        descFuncExcluir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descFuncExcluir.setText("Informações");
        excluirFuncionario.add(descFuncExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 80, 30));

        excluirFuncBtn.setBackground(new java.awt.Color(226, 0, 0));
        excluirFuncBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        excluirFuncBtn.setForeground(new java.awt.Color(255, 255, 255));
        excluirFuncBtn.setText("Excluir");
        excluirFuncBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluirFuncBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                excluirFuncBtnMouseClicked(evt);
            }
        });
        excluirFuncionario.add(excluirFuncBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 440, 90, 30));

        panelFuncionario.add(excluirFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));
        excluirFuncionario.getAccessibleContext().setAccessibleName("");

        verFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        verFuncionario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectFuncVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectFuncVer.setForeground(new java.awt.Color(77, 77, 77));
        selectFuncVer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Sexo", "Nasc", "Endereço", "Data Contratação", "Cargo", "Carga horária", "CPF", "RG", "Telefone", "Email", "Data Cadastro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectFuncVer.getTableHeader().setReorderingAllowed(false);
        selectFuncVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectFuncVerMouseClicked(evt);
            }
        });
        selectFuncVerFunc.setViewportView(selectFuncVer);

        verFuncionario.add(selectFuncVerFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 670, 180));

        descricaoFuncVer.setEditable(false);
        descricaoFuncVer.setBackground(new java.awt.Color(240, 240, 240));
        descricaoFuncVer.setBorder(null);
        descricaoFuncVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descricaoFuncVer.setForeground(new java.awt.Color(77, 77, 77));
        infoFuncVerFunc.setViewportView(descricaoFuncVer);

        verFuncionario.add(infoFuncVerFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 670, 170));

        nomeFuncVerTxt.setBackground(new java.awt.Color(204, 204, 204));
        nomeFuncVerTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeFuncVerTxt.setForeground(new java.awt.Color(77, 77, 77));
        nomeFuncVerTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nomeFuncVerTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomeFuncVerTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomeFuncVerTxtKeyTyped(evt);
            }
        });
        verFuncionario.add(nomeFuncVerTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 190, 30));

        nomeFuncVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeFuncVer.setForeground(new java.awt.Color(77, 77, 77));
        nomeFuncVer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nomeFuncVer.setText("Nome");
        verFuncionario.add(nomeFuncVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 40, 30));

        verFunc.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        verFunc.setForeground(new java.awt.Color(0, 93, 187));
        verFunc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        verFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconVerF.png"))); // NOI18N
        verFuncionario.add(verFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        descFuncVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descFuncVer.setForeground(new java.awt.Color(77, 77, 77));
        descFuncVer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descFuncVer.setText("Informações");
        verFuncionario.add(descFuncVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 80, 30));

        panelFuncionario.add(verFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        modificarFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        modificarFuncionario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        UserPassMod.setBackground(new java.awt.Color(255, 255, 255));
        UserPassMod.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        UserPassMod.setText("Modificar usuário e senha");
        UserPassMod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserPassModMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                UserPassModMouseReleased(evt);
            }
        });
        modificarFuncionario.add(UserPassMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 150, 30));

        modFuncionario.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        modFuncionario.setForeground(new java.awt.Color(0, 93, 187));
        modFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconEditarF.png"))); // NOI18N
        modificarFuncionario.add(modFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        nomeFuncionarioMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeFuncionarioMod.setForeground(new java.awt.Color(77, 77, 77));
        nomeFuncionarioMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nomeFuncionarioMod.setText("Nome");
        modificarFuncionario.add(nomeFuncionarioMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 128, 30));

        nomeFuncTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        nomeFuncTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeFuncTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        nomeFuncTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nomeFuncTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomeFuncTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomeFuncTxtModKeyTyped(evt);
            }
        });
        modificarFuncionario.add(nomeFuncTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 360, 30));

        sexoFuncionarioMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sexoFuncionarioMod.setForeground(new java.awt.Color(77, 77, 77));
        sexoFuncionarioMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sexoFuncionarioMod.setText("Sexo");
        modificarFuncionario.add(sexoFuncionarioMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 128, 30));

        sexoFuncTxtMod.setBackground(new java.awt.Color(140, 0, 240));
        sexoFuncTxtMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sexoFuncTxtMod.setForeground(new java.awt.Color(255, 255, 255));
        sexoFuncTxtMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F", "M", "Outros" }));
        sexoFuncTxtMod.setBorder(null);
        sexoFuncTxtMod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sexoFuncTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sexoFuncTxtModKeyPressed(evt);
            }
        });
        modificarFuncionario.add(sexoFuncTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 70, 30));

        emailFuncionarioMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailFuncionarioMod.setForeground(new java.awt.Color(77, 77, 77));
        emailFuncionarioMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        emailFuncionarioMod.setText("Email");
        modificarFuncionario.add(emailFuncionarioMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 128, 30));

        emaiFuncTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        emaiFuncTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emaiFuncTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        emaiFuncTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        emaiFuncTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emaiFuncTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emaiFuncTxtModKeyTyped(evt);
            }
        });
        modificarFuncionario.add(emaiFuncTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 360, 30));

        telefoneFuncionarioMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telefoneFuncionarioMod.setForeground(new java.awt.Color(77, 77, 77));
        telefoneFuncionarioMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        telefoneFuncionarioMod.setText("Telefone");
        modificarFuncionario.add(telefoneFuncionarioMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 128, 30));

        telefoneFuncTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        telefoneFuncTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telefoneFuncTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        telefoneFuncTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        telefoneFuncTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                telefoneFuncTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefoneFuncTxtModKeyTyped(evt);
            }
        });
        modificarFuncionario.add(telefoneFuncTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 360, 30));

        cargaHorariaFuncionarioMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cargaHorariaFuncionarioMod.setForeground(new java.awt.Color(77, 77, 77));
        cargaHorariaFuncionarioMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cargaHorariaFuncionarioMod.setText("Carga Horária Diária");
        modificarFuncionario.add(cargaHorariaFuncionarioMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, -1, 30));

        cargaHorariaFuncTxtMod.setBackground(new java.awt.Color(140, 0, 240));
        cargaHorariaFuncTxtMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cargaHorariaFuncTxtMod.setForeground(new java.awt.Color(255, 255, 255));
        cargaHorariaFuncTxtMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cargaHorariaFuncTxtMod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cargaHorariaFuncTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cargaHorariaFuncTxtModKeyPressed(evt);
            }
        });
        modificarFuncionario.add(cargaHorariaFuncTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, -1, 30));

        cargoFuncionarioMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cargoFuncionarioMod.setForeground(new java.awt.Color(77, 77, 77));
        cargoFuncionarioMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cargoFuncionarioMod.setText("Cargo");
        modificarFuncionario.add(cargoFuncionarioMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 128, 30));

        cargoFuncTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        cargoFuncTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cargoFuncTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        cargoFuncTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cargoFuncTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cargoFuncTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cargoFuncTxtModKeyTyped(evt);
            }
        });
        modificarFuncionario.add(cargoFuncTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 360, 30));

        enderecoFuncionarioMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        enderecoFuncionarioMod.setForeground(new java.awt.Color(77, 77, 77));
        enderecoFuncionarioMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        enderecoFuncionarioMod.setText("Endereço");
        modificarFuncionario.add(enderecoFuncionarioMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 128, 30));

        enderecoFuncTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        enderecoFuncTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        enderecoFuncTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        enderecoFuncTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        enderecoFuncTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enderecoFuncTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                enderecoFuncTxtModKeyTyped(evt);
            }
        });
        modificarFuncionario.add(enderecoFuncTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 360, 30));

        erroFuncMod.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        erroFuncMod.setForeground(new java.awt.Color(255, 0, 0));
        erroFuncMod.setText("erro");
        modificarFuncionario.add(erroFuncMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 370, 20));

        usuarioFuncionarioMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        usuarioFuncionarioMod.setForeground(new java.awt.Color(77, 77, 77));
        usuarioFuncionarioMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        usuarioFuncionarioMod.setText("Usuário");
        modificarFuncionario.add(usuarioFuncionarioMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 130, 30));

        usuarioFuncTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        usuarioFuncTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        usuarioFuncTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        usuarioFuncTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        usuarioFuncTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuarioFuncTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usuarioFuncTxtModKeyTyped(evt);
            }
        });
        modificarFuncionario.add(usuarioFuncTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 130, 30));

        senhaFuncionarioMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        senhaFuncionarioMod.setForeground(new java.awt.Color(77, 77, 77));
        senhaFuncionarioMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        senhaFuncionarioMod.setText("Senha");
        modificarFuncionario.add(senhaFuncionarioMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, 70, 30));

        senhaFuncTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        senhaFuncTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        senhaFuncTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        senhaFuncTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        senhaFuncTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                senhaFuncTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                senhaFuncTxtModKeyTyped(evt);
            }
        });
        modificarFuncionario.add(senhaFuncTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, 130, 30));

        modificarFuncionarioBtn.setBackground(new java.awt.Color(140, 0, 240));
        modificarFuncionarioBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        modificarFuncionarioBtn.setForeground(new java.awt.Color(255, 255, 255));
        modificarFuncionarioBtn.setText("Modificar");
        modificarFuncionarioBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modificarFuncionarioBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarFuncionarioBtnMouseClicked(evt);
            }
        });
        modificarFuncionario.add(modificarFuncionarioBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 430, -1, 30));

        cpfModFuncTxt.setBackground(new java.awt.Color(204, 204, 204));
        cpfModFuncTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cpfModFuncTxt.setForeground(new java.awt.Color(77, 77, 77));
        cpfModFuncTxt.setBorder(null);
        cpfModFuncTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpfModFuncTxtMouseClicked(evt);
            }
        });
        cpfModFuncTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cpfModFuncTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cpfModFuncTxtKeyTyped(evt);
            }
        });
        modificarFuncionario.add(cpfModFuncTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 360, 30));

        insiraRgFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        insiraRgFunc.setForeground(new java.awt.Color(77, 77, 77));
        insiraRgFunc.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        insiraRgFunc.setText("Insira o CPF");
        modificarFuncionario.add(insiraRgFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 130, 30));

        panelFuncionario.add(modificarFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        PanelPrincipal.add(panelFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 710, 490));

        panelHospede.setBackground(new java.awt.Color(245, 245, 245));
        panelHospede.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cadastrarHospede.setBackground(new java.awt.Color(255, 255, 255));
        cadastrarHospede.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cadastrarNovoHospede.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cadastrarNovoHospede.setForeground(new java.awt.Color(0, 93, 187));
        cadastrarNovoHospede.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cadastrarNovoHospede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconCH.png"))); // NOI18N
        cadastrarHospede.add(cadastrarNovoHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        nomeHospede.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeHospede.setForeground(new java.awt.Color(77, 77, 77));
        nomeHospede.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nomeHospede.setText("Nome");
        cadastrarHospede.add(nomeHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 40, 30));

        nomeHospedeTxt.setBackground(new java.awt.Color(204, 204, 204));
        nomeHospedeTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeHospedeTxt.setForeground(new java.awt.Color(77, 77, 77));
        nomeHospedeTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nomeHospedeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomeHospedeTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomeHospedeTxtKeyTyped(evt);
            }
        });
        cadastrarHospede.add(nomeHospedeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 360, 30));

        sexoHospedeTxt.setBackground(new java.awt.Color(0, 179, 136));
        sexoHospedeTxt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sexoHospedeTxt.setForeground(new java.awt.Color(255, 255, 255));
        sexoHospedeTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F", "M", "Outros" }));
        sexoHospedeTxt.setBorder(new javax.swing.border.MatteBorder(null));
        sexoHospedeTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sexoHospedeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sexoHospedeTxtKeyPressed(evt);
            }
        });
        cadastrarHospede.add(sexoHospedeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 70, 30));

        sexoHospede.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sexoHospede.setForeground(new java.awt.Color(77, 77, 77));
        sexoHospede.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sexoHospede.setText("Sexo");
        cadastrarHospede.add(sexoHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 170, 40, 30));

        nascimentoHospede.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nascimentoHospede.setForeground(new java.awt.Color(77, 77, 77));
        nascimentoHospede.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nascimentoHospede.setText("Nascimento");
        cadastrarHospede.add(nascimentoHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, 30));

        diaNascHospedeTxt.setBackground(new java.awt.Color(0, 179, 136));
        diaNascHospedeTxt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diaNascHospedeTxt.setForeground(new java.awt.Color(255, 255, 255));
        diaNascHospedeTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        diaNascHospedeTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        diaNascHospedeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diaNascHospedeTxtKeyPressed(evt);
            }
        });
        cadastrarHospede.add(diaNascHospedeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, -1, 30));

        mesNascHospedeTxt.setBackground(new java.awt.Color(0, 179, 136));
        mesNascHospedeTxt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesNascHospedeTxt.setForeground(new java.awt.Color(255, 255, 255));
        mesNascHospedeTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        mesNascHospedeTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mesNascHospedeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mesNascHospedeTxtKeyPressed(evt);
            }
        });
        cadastrarHospede.add(mesNascHospedeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, -1, 30));

        anoNascHospedeTxt.setBackground(new java.awt.Color(0, 179, 136));
        anoNascHospedeTxt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        anoNascHospedeTxt.setForeground(new java.awt.Color(255, 255, 255));
        anoNascHospedeTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
        anoNascHospedeTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anoNascHospedeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                anoNascHospedeTxtKeyPressed(evt);
            }
        });
        cadastrarHospede.add(anoNascHospedeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 60, 30));

        rgHospedeTxt.setBackground(new java.awt.Color(204, 204, 204));
        rgHospedeTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rgHospedeTxt.setForeground(new java.awt.Color(77, 77, 77));
        rgHospedeTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        rgHospedeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rgHospedeTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rgHospedeTxtKeyTyped(evt);
            }
        });
        cadastrarHospede.add(rgHospedeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 360, 30));

        rgHospede.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rgHospede.setForeground(new java.awt.Color(77, 77, 77));
        rgHospede.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rgHospede.setText("RG");
        cadastrarHospede.add(rgHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 220, 30, 30));

        cpfHospede.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cpfHospede.setForeground(new java.awt.Color(77, 77, 77));
        cpfHospede.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cpfHospede.setText("CPF");
        cadastrarHospede.add(cpfHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 270, 30, 30));

        cpfHospedeTxt.setBackground(new java.awt.Color(204, 204, 204));
        cpfHospedeTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cpfHospedeTxt.setForeground(new java.awt.Color(77, 77, 77));
        cpfHospedeTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cpfHospedeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cpfHospedeTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cpfHospedeTxtKeyTyped(evt);
            }
        });
        cadastrarHospede.add(cpfHospedeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 360, 30));

        telefoneHospede.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telefoneHospede.setForeground(new java.awt.Color(77, 77, 77));
        telefoneHospede.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        telefoneHospede.setText("Telefone");
        cadastrarHospede.add(telefoneHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 320, 60, 30));

        telefoneHospedeTxt.setBackground(new java.awt.Color(204, 204, 204));
        telefoneHospedeTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telefoneHospedeTxt.setForeground(new java.awt.Color(77, 77, 77));
        telefoneHospedeTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        telefoneHospedeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                telefoneHospedeTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefoneHospedeTxtKeyTyped(evt);
            }
        });
        cadastrarHospede.add(telefoneHospedeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 360, 30));

        cadastrarHospedeBtn.setBackground(new java.awt.Color(0, 179, 136));
        cadastrarHospedeBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cadastrarHospedeBtn.setForeground(new java.awt.Color(255, 255, 255));
        cadastrarHospedeBtn.setText("Cadastrar");
        cadastrarHospedeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrarHospedeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrarHospedeBtnMouseClicked(evt);
            }
        });
        cadastrarHospede.add(cadastrarHospedeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, -1, 30));

        cidadeHospede.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cidadeHospede.setForeground(new java.awt.Color(77, 77, 77));
        cidadeHospede.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cidadeHospede.setText("Cidade");
        cadastrarHospede.add(cidadeHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 50, 30));

        cidadeHospedeTxt.setBackground(new java.awt.Color(204, 204, 204));
        cidadeHospedeTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cidadeHospedeTxt.setForeground(new java.awt.Color(77, 77, 77));
        cidadeHospedeTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cidadeHospedeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cidadeHospedeTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cidadeHospedeTxtKeyTyped(evt);
            }
        });
        cadastrarHospede.add(cidadeHospedeTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 360, 30));

        erroHospede.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        erroHospede.setForeground(new java.awt.Color(255, 0, 0));
        erroHospede.setText("erro");
        cadastrarHospede.add(erroHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 360, 20));

        panelHospede.add(cadastrarHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 500));

        excluirHospede.setBackground(new java.awt.Color(255, 255, 255));
        excluirHospede.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluirHospede.setMaximumSize(new java.awt.Dimension(710, 500));
        excluirHospede.setMinimumSize(new java.awt.Dimension(710, 500));
        excluirHospede.setPreferredSize(new java.awt.Dimension(710, 500));
        excluirHospede.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectHospedeExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectHospedeExcluir.setForeground(new java.awt.Color(77, 77, 77));
        selectHospedeExcluir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Sexo", "Nasc", "CPF", "RG", "Telefone", "Cidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectHospedeExcluir.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        selectHospedeExcluir.getTableHeader().setReorderingAllowed(false);
        selectHospedeExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectHospedeExcluirMouseClicked(evt);
            }
        });
        selectHospExcluir.setViewportView(selectHospedeExcluir);
        if (selectHospedeExcluir.getColumnModel().getColumnCount() > 0) {
            selectHospedeExcluir.getColumnModel().getColumn(0).setPreferredWidth(26);
        }

        excluirHospede.add(selectHospExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 670, 180));

        descHospExcluir.setEditable(false);
        descHospExcluir.setBackground(new java.awt.Color(240, 240, 240));
        descHospExcluir.setBorder(null);
        descHospExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descHospExcluir.setForeground(new java.awt.Color(77, 77, 77));
        descHospExcluir.setCaretColor(new java.awt.Color(77, 77, 77));
        descHospExcluir.setDisabledTextColor(new java.awt.Color(77, 77, 77));
        descHospExcluir.setEnabled(false);
        descHospExcluir.setSelectedTextColor(new java.awt.Color(77, 77, 77));
        descHospExcluir.setSelectionColor(new java.awt.Color(77, 77, 77));
        infoHospExcluirHosp.setViewportView(descHospExcluir);
        descHospExcluir.getAccessibleContext().setAccessibleName("");

        excluirHospede.add(infoHospExcluirHosp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 530, 160));

        nomeHospExcluirTxt.setBackground(new java.awt.Color(204, 204, 204));
        nomeHospExcluirTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeHospExcluirTxt.setForeground(new java.awt.Color(77, 77, 77));
        nomeHospExcluirTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nomeHospExcluirTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomeHospExcluirTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomeHospExcluirTxtKeyTyped(evt);
            }
        });
        excluirHospede.add(nomeHospExcluirTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 190, 30));

        nomeHospExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeHospExcluir.setForeground(new java.awt.Color(77, 77, 77));
        nomeHospExcluir.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nomeHospExcluir.setText("Nome");
        excluirHospede.add(nomeHospExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 40, 30));

        excluirHosp.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        excluirHosp.setForeground(new java.awt.Color(0, 93, 187));
        excluirHosp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        excluirHosp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconExcluirH.png"))); // NOI18N
        excluirHospede.add(excluirHosp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        infoHospExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoHospExcluir.setForeground(new java.awt.Color(77, 77, 77));
        infoHospExcluir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoHospExcluir.setText("Informações");
        excluirHospede.add(infoHospExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 80, 30));

        excluirHospBtn.setBackground(new java.awt.Color(226, 0, 0));
        excluirHospBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        excluirHospBtn.setForeground(new java.awt.Color(255, 255, 255));
        excluirHospBtn.setText("Excluir");
        excluirHospBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                excluirHospBtnMouseClicked(evt);
            }
        });
        excluirHospede.add(excluirHospBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 90, 30));

        excluirReservasHosp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        excluirReservasHosp.setForeground(new java.awt.Color(77, 77, 77));
        excluirReservasHosp.setText("Excluir Reservas");
        excluirHospede.add(excluirReservasHosp, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 400, 120, -1));

        panelHospede.add(excluirHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 500));

        verHospede.setBackground(new java.awt.Color(255, 255, 255));
        verHospede.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectHospedeVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectHospedeVer.setForeground(new java.awt.Color(77, 77, 77));
        selectHospedeVer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Sexo", "Nasc", "CPF", "RG", "Telefone", "Cidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectHospedeVer.getTableHeader().setReorderingAllowed(false);
        selectHospedeVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectHospedeVerMouseClicked(evt);
            }
        });
        selectHospVer.setViewportView(selectHospedeVer);
        if (selectHospedeVer.getColumnModel().getColumnCount() > 0) {
            selectHospedeVer.getColumnModel().getColumn(0).setPreferredWidth(26);
        }

        verHospede.add(selectHospVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 670, 180));

        descHospVer.setEditable(false);
        descHospVer.setBackground(new java.awt.Color(240, 240, 240));
        descHospVer.setBorder(null);
        descHospVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descHospVer.setForeground(new java.awt.Color(77, 77, 77));
        descHospVer.setCaretColor(new java.awt.Color(77, 77, 77));
        descHospVer.setDisabledTextColor(new java.awt.Color(77, 77, 77));
        infoHospVerHosp.setViewportView(descHospVer);

        verHospede.add(infoHospVerHosp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 670, 160));

        nomeHospVerTxt.setBackground(new java.awt.Color(204, 204, 204));
        nomeHospVerTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeHospVerTxt.setForeground(new java.awt.Color(77, 77, 77));
        nomeHospVerTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nomeHospVerTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomeHospVerTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomeHospVerTxtKeyTyped(evt);
            }
        });
        verHospede.add(nomeHospVerTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 190, 30));

        nomeHospVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeHospVer.setForeground(new java.awt.Color(77, 77, 77));
        nomeHospVer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nomeHospVer.setText("Nome");
        verHospede.add(nomeHospVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 40, 30));

        verHosp.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        verHosp.setForeground(new java.awt.Color(0, 93, 187));
        verHosp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        verHosp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconVerH.png"))); // NOI18N
        verHospede.add(verHosp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        infoHospVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoHospVer.setForeground(new java.awt.Color(77, 77, 77));
        infoHospVer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoHospVer.setText("Informações");
        verHospede.add(infoHospVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 80, 30));

        panelHospede.add(verHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        modificarHospede.setBackground(new java.awt.Color(255, 255, 255));
        modificarHospede.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        modHospede.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        modHospede.setForeground(new java.awt.Color(0, 93, 187));
        modHospede.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modHospede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconEditarH.png"))); // NOI18N
        modificarHospede.add(modHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        nomeHospedeMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeHospedeMod.setForeground(new java.awt.Color(77, 77, 77));
        nomeHospedeMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nomeHospedeMod.setText("Nome");
        modificarHospede.add(nomeHospedeMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 40, 30));

        nomeHospedeTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        nomeHospedeTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeHospedeTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        nomeHospedeTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nomeHospedeTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomeHospedeTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomeHospedeTxtModKeyTyped(evt);
            }
        });
        modificarHospede.add(nomeHospedeTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 360, 30));

        sexoHospedeTxtMod.setBackground(new java.awt.Color(140, 0, 240));
        sexoHospedeTxtMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sexoHospedeTxtMod.setForeground(new java.awt.Color(255, 255, 255));
        sexoHospedeTxtMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F", "M", "Outros" }));
        sexoHospedeTxtMod.setBorder(new javax.swing.border.MatteBorder(null));
        sexoHospedeTxtMod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sexoHospedeTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sexoHospedeTxtModKeyPressed(evt);
            }
        });
        modificarHospede.add(sexoHospedeTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 70, 30));

        sexoHospedeMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sexoHospedeMod.setForeground(new java.awt.Color(77, 77, 77));
        sexoHospedeMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sexoHospedeMod.setText("Sexo");
        modificarHospede.add(sexoHospedeMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 40, 30));

        cpfHospedeTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        cpfHospedeTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cpfHospedeTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        cpfHospedeTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cpfHospedeTxtMod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpfHospedeTxtModMouseClicked(evt);
            }
        });
        cpfHospedeTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cpfHospedeTxtModKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cpfHospedeTxtModKeyTyped(evt);
            }
        });
        modificarHospede.add(cpfHospedeTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 360, 30));

        telefoneHospedeMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telefoneHospedeMod.setForeground(new java.awt.Color(77, 77, 77));
        telefoneHospedeMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        telefoneHospedeMod.setText("Telefone");
        modificarHospede.add(telefoneHospedeMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 60, 30));

        telefoneHospedeTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        telefoneHospedeTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telefoneHospedeTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        telefoneHospedeTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        telefoneHospedeTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                telefoneHospedeTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefoneHospedeTxtModKeyTyped(evt);
            }
        });
        modificarHospede.add(telefoneHospedeTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 360, 30));

        modificarHospedeBtn.setBackground(new java.awt.Color(140, 0, 240));
        modificarHospedeBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        modificarHospedeBtn.setForeground(new java.awt.Color(255, 255, 255));
        modificarHospedeBtn.setText("Modificar");
        modificarHospedeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modificarHospedeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarHospedeBtnMouseClicked(evt);
            }
        });
        modificarHospede.add(modificarHospedeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, -1, 30));

        cidadeHospedeMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cidadeHospedeMod.setForeground(new java.awt.Color(77, 77, 77));
        cidadeHospedeMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cidadeHospedeMod.setText("Cidade");
        modificarHospede.add(cidadeHospedeMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 50, 30));

        cidadeHospedeTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        cidadeHospedeTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cidadeHospedeTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        cidadeHospedeTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cidadeHospedeTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cidadeHospedeTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cidadeHospedeTxtModKeyTyped(evt);
            }
        });
        modificarHospede.add(cidadeHospedeTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 360, 30));

        erroHospMod.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        erroHospMod.setForeground(new java.awt.Color(255, 0, 0));
        erroHospMod.setText("erro");
        modificarHospede.add(erroHospMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, 360, 20));

        insiraRgHosp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        insiraRgHosp.setForeground(new java.awt.Color(77, 77, 77));
        insiraRgHosp.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        insiraRgHosp.setText("Insira o CPF");
        modificarHospede.add(insiraRgHosp, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 110, 30));

        panelHospede.add(modificarHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        PanelPrincipal.add(panelHospede, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 710, 490));

        panelQuarto.setBackground(new java.awt.Color(245, 245, 245));
        panelQuarto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cadastrarQuarto.setBackground(new java.awt.Color(255, 255, 255));
        cadastrarQuarto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cadastrarNovoQuarto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cadastrarNovoQuarto.setForeground(new java.awt.Color(0, 93, 187));
        cadastrarNovoQuarto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cadastrarNovoQuarto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconCQ.png"))); // NOI18N
        cadastrarQuarto.add(cadastrarNovoQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        numQuarto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numQuarto.setForeground(new java.awt.Color(77, 77, 77));
        numQuarto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        numQuarto.setText("Nº do quarto");
        cadastrarQuarto.add(numQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 128, 30));

        numQuartoTxt.setBackground(new java.awt.Color(204, 204, 204));
        numQuartoTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numQuartoTxt.setForeground(new java.awt.Color(77, 77, 77));
        numQuartoTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        numQuartoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                numQuartoTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numQuartoTxtKeyTyped(evt);
            }
        });
        cadastrarQuarto.add(numQuartoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 130, 30));

        andarQuarto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        andarQuarto.setForeground(new java.awt.Color(77, 77, 77));
        andarQuarto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        andarQuarto.setText("Andar");
        cadastrarQuarto.add(andarQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 50, 30));

        andarQuartoTxt.setBackground(new java.awt.Color(204, 204, 204));
        andarQuartoTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        andarQuartoTxt.setForeground(new java.awt.Color(77, 77, 77));
        andarQuartoTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        andarQuartoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                andarQuartoTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                andarQuartoTxtKeyTyped(evt);
            }
        });
        cadastrarQuarto.add(andarQuartoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 130, 30));

        tipoQuarto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tipoQuarto.setForeground(new java.awt.Color(77, 77, 77));
        tipoQuarto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tipoQuarto.setText("Tipo");
        cadastrarQuarto.add(tipoQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 128, 30));

        tipoQuartoTxt.setBackground(new java.awt.Color(204, 204, 204));
        tipoQuartoTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tipoQuartoTxt.setForeground(new java.awt.Color(77, 77, 77));
        tipoQuartoTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tipoQuartoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tipoQuartoTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tipoQuartoTxtKeyTyped(evt);
            }
        });
        cadastrarQuarto.add(tipoQuartoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 360, 30));

        descricaoQuarto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descricaoQuarto.setForeground(new java.awt.Color(77, 77, 77));
        descricaoQuarto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        descricaoQuarto.setText("Descrição");
        cadastrarQuarto.add(descricaoQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 128, 30));

        descricaoQuartoTxt.setBackground(new java.awt.Color(204, 204, 204));
        descricaoQuartoTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descricaoQuartoTxt.setForeground(new java.awt.Color(77, 77, 77));
        descricaoQuartoTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        descricaoQuartoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descricaoQuartoTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descricaoQuartoTxtKeyTyped(evt);
            }
        });
        cadastrarQuarto.add(descricaoQuartoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 360, 30));

        precoQuarto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        precoQuarto.setForeground(new java.awt.Color(77, 77, 77));
        precoQuarto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        precoQuarto.setText("Preço R$");
        cadastrarQuarto.add(precoQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 130, 30));

        cadastrarQuartoBtn.setBackground(new java.awt.Color(0, 179, 136));
        cadastrarQuartoBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cadastrarQuartoBtn.setForeground(new java.awt.Color(255, 255, 255));
        cadastrarQuartoBtn.setText("Cadastrar");
        cadastrarQuartoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrarQuartoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrarQuartoBtnMouseClicked(evt);
            }
        });
        cadastrarQuarto.add(cadastrarQuartoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 430, -1, 30));

        erroQuarto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        erroQuarto.setForeground(new java.awt.Color(255, 0, 0));
        erroQuarto.setText("erro");
        cadastrarQuarto.add(erroQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 360, 20));

        precoQuartoTxt.setBackground(new java.awt.Color(204, 204, 204));
        precoQuartoTxt.setBorder(null);
        precoQuartoTxt.setForeground(new java.awt.Color(77, 77, 77));
        precoQuartoTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.00"))));
        precoQuartoTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        precoQuartoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                precoQuartoTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precoQuartoTxtKeyTyped(evt);
            }
        });
        cadastrarQuarto.add(precoQuartoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 130, 30));

        panelQuarto.add(cadastrarQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 500));

        excluirQuarto.setBackground(new java.awt.Color(255, 255, 255));
        excluirQuarto.setMaximumSize(new java.awt.Dimension(710, 500));
        excluirQuarto.setMinimumSize(new java.awt.Dimension(710, 500));
        excluirQuarto.setPreferredSize(new java.awt.Dimension(710, 500));
        excluirQuarto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectQuartosExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectQuartosExcluir.setForeground(new java.awt.Color(77, 77, 77));
        selectQuartosExcluir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Andar", "Tipo", "Descrição", "Preço", "Disponível"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectQuartosExcluir.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        selectQuartosExcluir.getTableHeader().setReorderingAllowed(false);
        selectQuartosExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectQuartosExcluirMouseClicked(evt);
            }
        });
        selectQuartoExcluir.setViewportView(selectQuartosExcluir);
        selectQuartosExcluir.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (selectQuartosExcluir.getColumnModel().getColumnCount() > 0) {
            selectQuartosExcluir.getColumnModel().getColumn(0).setPreferredWidth(26);
        }

        excluirQuarto.add(selectQuartoExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 670, 180));

        numQuaExcluirTxt.setBackground(new java.awt.Color(204, 204, 204));
        numQuaExcluirTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numQuaExcluirTxt.setForeground(new java.awt.Color(77, 77, 77));
        numQuaExcluirTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        numQuaExcluirTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numQuaExcluirTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numQuaExcluirTxtKeyTyped(evt);
            }
        });
        excluirQuarto.add(numQuaExcluirTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 130, 30));

        numQuaExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numQuaExcluir.setForeground(new java.awt.Color(77, 77, 77));
        numQuaExcluir.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        numQuaExcluir.setText("Nº");
        excluirQuarto.add(numQuaExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 40, 30));

        excluirQua.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        excluirQua.setForeground(new java.awt.Color(0, 93, 187));
        excluirQua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        excluirQua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconExcluirQ.png"))); // NOI18N
        excluirQuarto.add(excluirQua, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        infoQuaExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoQuaExcluir.setForeground(new java.awt.Color(77, 77, 77));
        infoQuaExcluir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoQuaExcluir.setText("Informações");
        excluirQuarto.add(infoQuaExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 80, 30));

        excluirQuaBtn.setBackground(new java.awt.Color(226, 0, 0));
        excluirQuaBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        excluirQuaBtn.setForeground(new java.awt.Color(255, 255, 255));
        excluirQuaBtn.setText("Excluir");
        excluirQuaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluirQuaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                excluirQuaBtnMouseClicked(evt);
            }
        });
        excluirQuarto.add(excluirQuaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 90, 30));

        excluirReservasQua.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        excluirReservasQua.setForeground(new java.awt.Color(77, 77, 77));
        excluirReservasQua.setText("Excluir Reservas");
        excluirQuarto.add(excluirReservasQua, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 400, 120, -1));

        descQuaExcluir.setEditable(false);
        descQuaExcluir.setBackground(new java.awt.Color(240, 240, 240));
        descQuaExcluir.setBorder(null);
        descQuaExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descQuaExcluir.setForeground(new java.awt.Color(77, 77, 77));
        descQuaExcluir.setCaretColor(new java.awt.Color(77, 77, 77));
        descQuaExcluir.setDisabledTextColor(new java.awt.Color(77, 77, 77));
        descQuaExcluir.setEnabled(false);
        descQuaExcluir.setSelectedTextColor(new java.awt.Color(77, 77, 77));
        descQuaExcluir.setSelectionColor(new java.awt.Color(77, 77, 77));
        infoQuaExcluirQua.setViewportView(descQuaExcluir);

        excluirQuarto.add(infoQuaExcluirQua, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 530, 160));

        panelQuarto.add(excluirQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 500));

        verQuarto.setBackground(new java.awt.Color(255, 255, 255));
        verQuarto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectQuartosVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectQuartosVer.setForeground(new java.awt.Color(77, 77, 77));
        selectQuartosVer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Andar", "Tipo", "Descrição", "Preço", "Disponível"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectQuartosVer.getTableHeader().setReorderingAllowed(false);
        selectQuartosVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectQuartosVerMouseClicked(evt);
            }
        });
        selectQuartoVer.setViewportView(selectQuartosVer);
        selectQuartosVer.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (selectQuartosVer.getColumnModel().getColumnCount() > 0) {
            selectQuartosVer.getColumnModel().getColumn(0).setPreferredWidth(26);
        }

        verQuarto.add(selectQuartoVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 670, 180));

        numQuaVerTxt.setBackground(new java.awt.Color(204, 204, 204));
        numQuaVerTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numQuaVerTxt.setForeground(new java.awt.Color(77, 77, 77));
        numQuaVerTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        numQuaVerTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numQuaVerTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numQuaVerTxtKeyTyped(evt);
            }
        });
        verQuarto.add(numQuaVerTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 130, 30));

        numQuaVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numQuaVer.setForeground(new java.awt.Color(77, 77, 77));
        numQuaVer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        numQuaVer.setText("Nº");
        verQuarto.add(numQuaVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 40, 30));

        verQua.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        verQua.setForeground(new java.awt.Color(0, 93, 187));
        verQua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        verQua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconVerQ.png"))); // NOI18N
        verQuarto.add(verQua, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        infoQuaVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoQuaVer.setForeground(new java.awt.Color(77, 77, 77));
        infoQuaVer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoQuaVer.setText("Informações");
        verQuarto.add(infoQuaVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 80, 30));

        descQuaVer.setEditable(false);
        descQuaVer.setBackground(new java.awt.Color(240, 240, 240));
        descQuaVer.setBorder(null);
        descQuaVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descQuaVer.setForeground(new java.awt.Color(77, 77, 77));
        descQuaVer.setCaretColor(new java.awt.Color(77, 77, 77));
        descQuaVer.setDisabledTextColor(new java.awt.Color(77, 77, 77));
        descQuaVer.setEnabled(false);
        descQuaVer.setSelectedTextColor(new java.awt.Color(77, 77, 77));
        descQuaVer.setSelectionColor(new java.awt.Color(77, 77, 77));
        infoQuaVerQua.setViewportView(descQuaVer);

        verQuarto.add(infoQuaVerQua, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 670, 160));

        panelQuarto.add(verQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        modificarQuarto.setBackground(new java.awt.Color(255, 255, 255));
        modificarQuarto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        modQuarto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        modQuarto.setForeground(new java.awt.Color(0, 93, 187));
        modQuarto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modQuarto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconEditarQ.png"))); // NOI18N
        modificarQuarto.add(modQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        numQuartoMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numQuartoMod.setForeground(new java.awt.Color(77, 77, 77));
        numQuartoMod.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        numQuartoMod.setText("Insira o Nº do quarto");
        modificarQuarto.add(numQuartoMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 140, 30));

        numQuartoTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        numQuartoTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numQuartoTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        numQuartoTxtMod.setText("Digite o nº do quarto que deseja modificar");
        numQuartoTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        numQuartoTxtMod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                numQuartoTxtModMouseClicked(evt);
            }
        });
        numQuartoTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                numQuartoTxtModKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numQuartoTxtModKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numQuartoTxtModKeyTyped(evt);
            }
        });
        modificarQuarto.add(numQuartoTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 270, 30));

        andarQuartoMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        andarQuartoMod.setForeground(new java.awt.Color(77, 77, 77));
        andarQuartoMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        andarQuartoMod.setText("Andar");
        modificarQuarto.add(andarQuartoMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 50, 30));

        andarQuartoTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        andarQuartoTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        andarQuartoTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        andarQuartoTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        andarQuartoTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                andarQuartoTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                andarQuartoTxtModKeyTyped(evt);
            }
        });
        modificarQuarto.add(andarQuartoTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 130, 30));

        tipoQuartoMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tipoQuartoMod.setForeground(new java.awt.Color(77, 77, 77));
        tipoQuartoMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tipoQuartoMod.setText("Tipo");
        modificarQuarto.add(tipoQuartoMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 128, 30));

        tipoQuartoTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        tipoQuartoTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tipoQuartoTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        tipoQuartoTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tipoQuartoTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tipoQuartoTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tipoQuartoTxtModKeyTyped(evt);
            }
        });
        modificarQuarto.add(tipoQuartoTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 360, 30));

        descricaoQuartoMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descricaoQuartoMod.setForeground(new java.awt.Color(77, 77, 77));
        descricaoQuartoMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        descricaoQuartoMod.setText("Descrição");
        modificarQuarto.add(descricaoQuartoMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 128, 30));

        descricaoQuartoTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        descricaoQuartoTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descricaoQuartoTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        descricaoQuartoTxtMod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        descricaoQuartoTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descricaoQuartoTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descricaoQuartoTxtModKeyTyped(evt);
            }
        });
        modificarQuarto.add(descricaoQuartoTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 360, 30));

        precoQuartoMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        precoQuartoMod.setForeground(new java.awt.Color(77, 77, 77));
        precoQuartoMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        precoQuartoMod.setText("Preço R$");
        modificarQuarto.add(precoQuartoMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 130, 30));

        modificarQuartoBtn.setBackground(new java.awt.Color(140, 0, 240));
        modificarQuartoBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        modificarQuartoBtn.setForeground(new java.awt.Color(255, 255, 255));
        modificarQuartoBtn.setText("Modificar");
        modificarQuartoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modificarQuartoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarQuartoBtnMouseClicked(evt);
            }
        });
        modificarQuarto.add(modificarQuartoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 430, -1, 30));

        erroQuaMod.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        erroQuaMod.setForeground(new java.awt.Color(255, 0, 0));
        erroQuaMod.setText("erro");
        modificarQuarto.add(erroQuaMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 360, 20));

        disponibilidadeQuartoMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        disponibilidadeQuartoMod.setForeground(new java.awt.Color(77, 77, 77));
        disponibilidadeQuartoMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        disponibilidadeQuartoMod.setText("Disponibilidade");
        modificarQuarto.add(disponibilidadeQuartoMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 128, 30));

        disponibilidadeQuartoTxtMod.setBackground(new java.awt.Color(140, 0, 240));
        disponibilidadeQuartoTxtMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        disponibilidadeQuartoTxtMod.setForeground(new java.awt.Color(255, 255, 255));
        disponibilidadeQuartoTxtMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "N" }));
        modificarQuarto.add(disponibilidadeQuartoTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 130, 30));

        precoQuartoTxtMod.setBackground(new java.awt.Color(204, 204, 204));
        precoQuartoTxtMod.setBorder(null);
        precoQuartoTxtMod.setForeground(new java.awt.Color(77, 77, 77));
        precoQuartoTxtMod.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.00"))));
        precoQuartoTxtMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        precoQuartoTxtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                precoQuartoTxtModKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precoQuartoTxtModKeyTyped(evt);
            }
        });
        modificarQuarto.add(precoQuartoTxtMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 130, 30));

        panelQuarto.add(modificarQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        PanelPrincipal.add(panelQuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 710, 490));

        panelReserva.setBackground(new java.awt.Color(245, 245, 245));
        panelReserva.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cadastrarReserva.setBackground(new java.awt.Color(255, 255, 255));
        cadastrarReserva.setMaximumSize(new java.awt.Dimension(710, 480));
        cadastrarReserva.setMinimumSize(new java.awt.Dimension(710, 480));
        cadastrarReserva.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cadastrarNovaReserva.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cadastrarNovaReserva.setForeground(new java.awt.Color(0, 93, 187));
        cadastrarNovaReserva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cadastrarNovaReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconCR.png"))); // NOI18N
        cadastrarReserva.add(cadastrarNovaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        numQuartoReserva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numQuartoReserva.setForeground(new java.awt.Color(77, 77, 77));
        numQuartoReserva.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        numQuartoReserva.setText("Nº do quarto");
        cadastrarReserva.add(numQuartoReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, 30));

        numQuartoReservaTxt.setBackground(new java.awt.Color(204, 204, 204));
        numQuartoReservaTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numQuartoReservaTxt.setForeground(new java.awt.Color(77, 77, 77));
        numQuartoReservaTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        numQuartoReservaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                numQuartoReservaTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numQuartoReservaTxtKeyTyped(evt);
            }
        });
        cadastrarReserva.add(numQuartoReservaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 150, 30));

        idHospedeReserva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idHospedeReserva.setForeground(new java.awt.Color(77, 77, 77));
        idHospedeReserva.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        idHospedeReserva.setText("Nome do Hóspede");
        cadastrarReserva.add(idHospedeReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, 30));

        nomeHospReservaTxt.setBackground(new java.awt.Color(204, 204, 204));
        nomeHospReservaTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeHospReservaTxt.setForeground(new java.awt.Color(77, 77, 77));
        nomeHospReservaTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nomeHospReservaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomeHospReservaTxtKeyTyped(evt);
            }
        });
        cadastrarReserva.add(nomeHospReservaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 150, 30));

        dataEntradaReserva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dataEntradaReserva.setForeground(new java.awt.Color(77, 77, 77));
        dataEntradaReserva.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dataEntradaReserva.setText("Data de Entrada");
        cadastrarReserva.add(dataEntradaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, -1, 30));

        mesEntradaReserva.setBackground(new java.awt.Color(0, 179, 136));
        mesEntradaReserva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesEntradaReserva.setForeground(new java.awt.Color(255, 255, 255));
        mesEntradaReserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        mesEntradaReserva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mesEntradaReserva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mesEntradaReservaKeyPressed(evt);
            }
        });
        cadastrarReserva.add(mesEntradaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 50, 30));

        anoEntradaReserva.setBackground(new java.awt.Color(0, 179, 136));
        anoEntradaReserva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        anoEntradaReserva.setForeground(new java.awt.Color(255, 255, 255));
        anoEntradaReserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
        anoEntradaReserva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anoEntradaReserva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                anoEntradaReservaKeyPressed(evt);
            }
        });
        cadastrarReserva.add(anoEntradaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 330, 60, 30));

        verQuartoReservaBtn.setBackground(new java.awt.Color(0, 179, 136));
        verQuartoReservaBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        verQuartoReservaBtn.setForeground(new java.awt.Color(255, 255, 255));
        verQuartoReservaBtn.setText("Ver");
        verQuartoReservaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verQuartoReservaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verQuartoReservaBtnMouseClicked(evt);
            }
        });
        cadastrarReserva.add(verQuartoReservaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, 60, 30));

        dataSaidaReserva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dataSaidaReserva.setForeground(new java.awt.Color(77, 77, 77));
        dataSaidaReserva.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dataSaidaReserva.setText("Data de Saída");
        cadastrarReserva.add(dataSaidaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 90, 30));

        diaSaidaReserva.setBackground(new java.awt.Color(0, 179, 136));
        diaSaidaReserva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diaSaidaReserva.setForeground(new java.awt.Color(255, 255, 255));
        diaSaidaReserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        diaSaidaReserva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        diaSaidaReserva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diaSaidaReservaKeyPressed(evt);
            }
        });
        cadastrarReserva.add(diaSaidaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 380, 50, 30));

        mesSaidaReserva.setBackground(new java.awt.Color(0, 179, 136));
        mesSaidaReserva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesSaidaReserva.setForeground(new java.awt.Color(255, 255, 255));
        mesSaidaReserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        mesSaidaReserva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mesSaidaReserva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mesSaidaReservaKeyPressed(evt);
            }
        });
        cadastrarReserva.add(mesSaidaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 50, 30));

        anoSaidaReserva.setBackground(new java.awt.Color(0, 179, 136));
        anoSaidaReserva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        anoSaidaReserva.setForeground(new java.awt.Color(255, 255, 255));
        anoSaidaReserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
        anoSaidaReserva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anoSaidaReserva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                anoSaidaReservaKeyPressed(evt);
            }
        });
        cadastrarReserva.add(anoSaidaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, 60, 30));

        erroReserva.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        erroReserva.setForeground(new java.awt.Color(255, 0, 0));
        erroReserva.setText("erro");
        cadastrarReserva.add(erroReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, 340, -1));

        cadastrarReservaBtn.setBackground(new java.awt.Color(0, 179, 136));
        cadastrarReservaBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cadastrarReservaBtn.setForeground(new java.awt.Color(255, 255, 255));
        cadastrarReservaBtn.setText("Cadastrar");
        cadastrarReservaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrarReservaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrarReservaBtnMouseClicked(evt);
            }
        });
        cadastrarReserva.add(cadastrarReservaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 440, -1, 30));

        diaEntradaReserva.setBackground(new java.awt.Color(0, 179, 136));
        diaEntradaReserva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diaEntradaReserva.setForeground(new java.awt.Color(255, 255, 255));
        diaEntradaReserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        diaEntradaReserva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        diaEntradaReserva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diaEntradaReservaKeyPressed(evt);
            }
        });
        cadastrarReserva.add(diaEntradaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, 50, 30));

        selectQuartoReserva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectQuartoReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Andar", "Tipo", "Descrição", "Preço", "Disponivel"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectQuartoReserva.getTableHeader().setReorderingAllowed(false);
        selectQuartoReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectQuartoReservaMouseClicked(evt);
            }
        });
        selectQuartosReservas.setViewportView(selectQuartoReserva);

        cadastrarReserva.add(selectQuartosReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 460, 210));

        selectHospedeReserva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectHospedeReserva.setForeground(new java.awt.Color(77, 77, 77));
        selectHospedeReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Sexo", "Nasc", "CPF", "RG", "Telefone", "Cidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectHospedeReserva.getTableHeader().setReorderingAllowed(false);
        selectHospedeReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectHospedeReservaMouseClicked(evt);
            }
        });
        selectHospedesReservas.setViewportView(selectHospedeReserva);
        if (selectHospedeReserva.getColumnModel().getColumnCount() > 0) {
            selectHospedeReserva.getColumnModel().getColumn(0).setPreferredWidth(26);
        }

        cadastrarReserva.add(selectHospedesReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 460, 210));

        infoSelectReserva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoSelectReserva.setForeground(new java.awt.Color(77, 77, 77));
        infoSelectReserva.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoSelectReserva.setText("Descrição do Quarto");
        infoSelectReserva.setToolTipText("");
        cadastrarReserva.add(infoSelectReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 220, 30));

        descricaoQuartoReserva.setEditable(false);
        descricaoQuartoReserva.setBackground(new java.awt.Color(230, 230, 230));
        descricaoQuartoReserva.setBorder(null);
        descricaoQuartoReserva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descricaoQuartoReserva.setForeground(new java.awt.Color(77, 77, 77));
        descricaoQuartoReserva.setText("Não foi possível obter as informações necessárias");
        descricaoQuartoReserva.setCaretColor(new java.awt.Color(77, 77, 77));
        descricaoQuartoReserva.setDisabledTextColor(new java.awt.Color(77, 77, 77));
        descricaoQuartoReserva.setEnabled(false);
        descricaoQuartoReserva.setSelectionColor(new java.awt.Color(77, 77, 77));
        infoTabelaReservas.setViewportView(descricaoQuartoReserva);

        cadastrarReserva.add(infoTabelaReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 220, 180));

        verHospedeReservaBtn.setBackground(new java.awt.Color(0, 179, 136));
        verHospedeReservaBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        verHospedeReservaBtn.setForeground(new java.awt.Color(255, 255, 255));
        verHospedeReservaBtn.setText("Ver");
        verHospedeReservaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verHospedeReservaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verHospedeReservaBtnMouseClicked(evt);
            }
        });
        cadastrarReserva.add(verHospedeReservaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 60, 30));

        tableNewRes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableNewRes.setForeground(new java.awt.Color(77, 77, 77));
        tableNewRes.setText("Tabela de Quartos");
        cadastrarReserva.add(tableNewRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, 30));

        panelReserva.add(cadastrarReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 500));

        excluirReserva.setBackground(new java.awt.Color(255, 255, 255));
        excluirReserva.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectReservasExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectReservasExcluir.setForeground(new java.awt.Color(77, 77, 77));
        selectReservasExcluir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "nome", "nº quarto", "entrada", "saida", "consumo", "estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectReservasExcluir.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        selectReservasExcluir.getTableHeader().setReorderingAllowed(false);
        selectReservasExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectReservasExcluirMouseClicked(evt);
            }
        });
        selectReservaExcluir.setViewportView(selectReservasExcluir);
        selectReservasExcluir.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (selectReservasExcluir.getColumnModel().getColumnCount() > 0) {
            selectReservasExcluir.getColumnModel().getColumn(0).setPreferredWidth(26);
            selectReservasExcluir.getColumnModel().getColumn(5).setHeaderValue("consumo");
        }

        excluirReserva.add(selectReservaExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 670, 180));

        nomeResExcluirTxt.setBackground(new java.awt.Color(204, 204, 204));
        nomeResExcluirTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeResExcluirTxt.setForeground(new java.awt.Color(77, 77, 77));
        nomeResExcluirTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nomeResExcluirTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomeResExcluirTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomeResExcluirTxtKeyTyped(evt);
            }
        });
        excluirReserva.add(nomeResExcluirTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 200, 30));

        numResExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numResExcluir.setForeground(new java.awt.Color(77, 77, 77));
        numResExcluir.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        numResExcluir.setText("Nome do Hóspede");
        excluirReserva.add(numResExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, 30));

        excluirRes.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        excluirRes.setForeground(new java.awt.Color(0, 93, 187));
        excluirRes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        excluirRes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconExcluirR.png"))); // NOI18N
        excluirReserva.add(excluirRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        infoResExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoResExcluir.setForeground(new java.awt.Color(77, 77, 77));
        infoResExcluir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoResExcluir.setText("Informações");
        excluirReserva.add(infoResExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 80, 30));

        descResExcluir.setEditable(false);
        descResExcluir.setBackground(new java.awt.Color(240, 240, 240));
        descResExcluir.setBorder(null);
        descResExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descResExcluir.setForeground(new java.awt.Color(77, 77, 77));
        descResExcluir.setCaretColor(new java.awt.Color(77, 77, 77));
        descResExcluir.setDisabledTextColor(new java.awt.Color(77, 77, 77));
        descResExcluir.setEnabled(false);
        descResExcluir.setSelectedTextColor(new java.awt.Color(77, 77, 77));
        descResExcluir.setSelectionColor(new java.awt.Color(77, 77, 77));
        infoResExcluirRes.setViewportView(descResExcluir);

        excluirReserva.add(infoResExcluirRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 530, 150));

        excluirResBtn.setBackground(new java.awt.Color(226, 0, 0));
        excluirResBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        excluirResBtn.setForeground(new java.awt.Color(255, 255, 255));
        excluirResBtn.setText("Excluir");
        excluirResBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluirResBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                excluirResBtnMouseClicked(evt);
            }
        });
        excluirReserva.add(excluirResBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 90, 30));

        panelReserva.add(excluirReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        verReserva.setBackground(new java.awt.Color(255, 255, 255));
        verReserva.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectReservasVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectReservasVer.setForeground(new java.awt.Color(77, 77, 77));
        selectReservasVer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "nome", "nº quarto", "entrada", "saida", "consumo", "estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectReservasVer.getTableHeader().setReorderingAllowed(false);
        selectReservasVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectReservasVerMouseClicked(evt);
            }
        });
        selectReservaVer.setViewportView(selectReservasVer);
        selectReservasVer.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (selectReservasVer.getColumnModel().getColumnCount() > 0) {
            selectReservasVer.getColumnModel().getColumn(0).setPreferredWidth(26);
            selectReservasVer.getColumnModel().getColumn(5).setHeaderValue("consumo");
        }

        verReserva.add(selectReservaVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 670, 180));

        nomeResVerTxt.setBackground(new java.awt.Color(204, 204, 204));
        nomeResVerTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeResVerTxt.setForeground(new java.awt.Color(77, 77, 77));
        nomeResVerTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nomeResVerTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomeResVerTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomeResVerTxtKeyTyped(evt);
            }
        });
        verReserva.add(nomeResVerTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 200, 30));

        numResVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numResVer.setForeground(new java.awt.Color(77, 77, 77));
        numResVer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        numResVer.setText("Nome do Hóspede");
        verReserva.add(numResVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, 30));

        verRes.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        verRes.setForeground(new java.awt.Color(0, 93, 187));
        verRes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        verRes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconVerR.png"))); // NOI18N
        verReserva.add(verRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        infoResVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoResVer.setForeground(new java.awt.Color(77, 77, 77));
        infoResVer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoResVer.setText("Informações");
        verReserva.add(infoResVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 80, 30));

        descResVer.setEditable(false);
        descResVer.setBackground(new java.awt.Color(240, 240, 240));
        descResVer.setBorder(null);
        descResVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descResVer.setForeground(new java.awt.Color(77, 77, 77));
        descResVer.setCaretColor(new java.awt.Color(77, 77, 77));
        descResVer.setDisabledTextColor(new java.awt.Color(77, 77, 77));
        descResVer.setEnabled(false);
        descResVer.setSelectedTextColor(new java.awt.Color(77, 77, 77));
        descResVer.setSelectionColor(new java.awt.Color(77, 77, 77));
        infoResVerRes.setViewportView(descResVer);

        verReserva.add(infoResVerRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 670, 150));

        panelReserva.add(verReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        modificarReserva.setBackground(new java.awt.Color(255, 255, 255));
        modificarReserva.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        modReserva.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        modReserva.setForeground(new java.awt.Color(0, 93, 187));
        modReserva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconEditarR.png"))); // NOI18N
        modificarReserva.add(modReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        InsiraIdRes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        InsiraIdRes.setForeground(new java.awt.Color(77, 77, 77));
        InsiraIdRes.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        InsiraIdRes.setText("Insira o ID da Reserva");
        modificarReserva.add(InsiraIdRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 150, 30));

        idReservaMod.setBackground(new java.awt.Color(204, 204, 204));
        idReservaMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idReservaMod.setForeground(new java.awt.Color(77, 77, 77));
        idReservaMod.setBorder(null);
        idReservaMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idReservaModKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idReservaModKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idReservaModKeyTyped(evt);
            }
        });
        modificarReserva.add(idReservaMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 180, 30));

        idHospResMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idHospResMod.setForeground(new java.awt.Color(77, 77, 77));
        idHospResMod.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        idHospResMod.setText("ID do Hóspede");
        modificarReserva.add(idHospResMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 100, 30));

        numQuaResModTxt.setBackground(new java.awt.Color(204, 204, 204));
        numQuaResModTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numQuaResModTxt.setForeground(new java.awt.Color(77, 77, 77));
        numQuaResModTxt.setBorder(null);
        numQuaResModTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                numQuaResModTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numQuaResModTxtKeyTyped(evt);
            }
        });
        modificarReserva.add(numQuaResModTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 180, 30));

        numQuaResMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numQuaResMod.setForeground(new java.awt.Color(77, 77, 77));
        numQuaResMod.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        numQuaResMod.setText("Nº do quarto");
        modificarReserva.add(numQuaResMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 90, 30));

        dataEntradaReservaMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dataEntradaReservaMod.setForeground(new java.awt.Color(77, 77, 77));
        dataEntradaReservaMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dataEntradaReservaMod.setText("Data de Entrada");
        modificarReserva.add(dataEntradaReservaMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 110, 30));

        mesEntradaReservaMod.setBackground(new java.awt.Color(140, 0, 240));
        mesEntradaReservaMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesEntradaReservaMod.setForeground(new java.awt.Color(255, 255, 255));
        mesEntradaReservaMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        mesEntradaReservaMod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mesEntradaReservaMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mesEntradaReservaModKeyPressed(evt);
            }
        });
        modificarReserva.add(mesEntradaReservaMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, 50, 30));

        anoEntradaReservaMod.setBackground(new java.awt.Color(140, 0, 240));
        anoEntradaReservaMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        anoEntradaReservaMod.setForeground(new java.awt.Color(255, 255, 255));
        anoEntradaReservaMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
        anoEntradaReservaMod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anoEntradaReservaMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                anoEntradaReservaModKeyPressed(evt);
            }
        });
        modificarReserva.add(anoEntradaReservaMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, 60, 30));

        dataSaidaReservaMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dataSaidaReservaMod.setForeground(new java.awt.Color(77, 77, 77));
        dataSaidaReservaMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dataSaidaReservaMod.setText("Data de Saída");
        modificarReserva.add(dataSaidaReservaMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 90, 30));

        diaSaidaReservaMod.setBackground(new java.awt.Color(140, 0, 240));
        diaSaidaReservaMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diaSaidaReservaMod.setForeground(new java.awt.Color(255, 255, 255));
        diaSaidaReservaMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        diaSaidaReservaMod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        diaSaidaReservaMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diaSaidaReservaModKeyPressed(evt);
            }
        });
        modificarReserva.add(diaSaidaReservaMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 50, 30));

        mesSaidaReservaMod.setBackground(new java.awt.Color(140, 0, 240));
        mesSaidaReservaMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesSaidaReservaMod.setForeground(new java.awt.Color(255, 255, 255));
        mesSaidaReservaMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        mesSaidaReservaMod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mesSaidaReservaMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mesSaidaReservaModKeyPressed(evt);
            }
        });
        modificarReserva.add(mesSaidaReservaMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 50, 30));

        anoSaidaReservaMod.setBackground(new java.awt.Color(140, 0, 240));
        anoSaidaReservaMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        anoSaidaReservaMod.setForeground(new java.awt.Color(255, 255, 255));
        anoSaidaReservaMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
        anoSaidaReservaMod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anoSaidaReservaMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                anoSaidaReservaModKeyPressed(evt);
            }
        });
        modificarReserva.add(anoSaidaReservaMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 60, 30));

        diaEntradaReservaMod.setBackground(new java.awt.Color(140, 0, 240));
        diaEntradaReservaMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diaEntradaReservaMod.setForeground(new java.awt.Color(255, 255, 255));
        diaEntradaReservaMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        diaEntradaReservaMod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        diaEntradaReservaMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diaEntradaReservaModKeyPressed(evt);
            }
        });
        modificarReserva.add(diaEntradaReservaMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 50, 30));

        nomeHospResTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeHospResTxt.setForeground(new java.awt.Color(77, 77, 77));
        nomeHospResTxt.setText(" ");
        nomeHospResTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        nomeHospResTxt.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        nomeHospResTxt.setRequestFocusEnabled(false);
        modificarReserva.add(nomeHospResTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 290, 30));

        estadoResMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        estadoResMod.setForeground(new java.awt.Color(77, 77, 77));
        estadoResMod.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        estadoResMod.setText("Estado da Reserva");
        modificarReserva.add(estadoResMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 120, 30));

        idHospResModTxt.setBackground(new java.awt.Color(204, 204, 204));
        idHospResModTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idHospResModTxt.setForeground(new java.awt.Color(77, 77, 77));
        idHospResModTxt.setBorder(null);
        idHospResModTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idHospResModTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idHospResModTxtKeyTyped(evt);
            }
        });
        modificarReserva.add(idHospResModTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 180, 30));

        modificarReservaBtn.setBackground(new java.awt.Color(140, 0, 240));
        modificarReservaBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        modificarReservaBtn.setForeground(new java.awt.Color(255, 255, 255));
        modificarReservaBtn.setText("Modificar");
        modificarReservaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modificarReservaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarReservaBtnMouseClicked(evt);
            }
        });
        modificarReserva.add(modificarReservaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, -1, 30));

        nomeHospRes1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeHospRes1.setForeground(new java.awt.Color(77, 77, 77));
        nomeHospRes1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nomeHospRes1.setText("Nome do Hóspede: ");
        modificarReserva.add(nomeHospRes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 130, 30));

        erroResMod.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        erroResMod.setForeground(new java.awt.Color(255, 0, 0));
        erroResMod.setText("erro");
        modificarReserva.add(erroResMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 360, 20));

        consumoResMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        consumoResMod.setForeground(new java.awt.Color(77, 77, 77));
        consumoResMod.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        consumoResMod.setText("Consumo R$");
        modificarReserva.add(consumoResMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 90, 30));

        estadoResModTxt.setBackground(new java.awt.Color(140, 0, 240));
        estadoResModTxt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        estadoResModTxt.setForeground(new java.awt.Color(255, 255, 255));
        estadoResModTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Reservado", "Em estadia", "Concluído" }));
        estadoResModTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        estadoResModTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                estadoResModTxtKeyPressed(evt);
            }
        });
        modificarReserva.add(estadoResModTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 180, 30));

        consumoResModTxt.setBackground(new java.awt.Color(204, 204, 204));
        consumoResModTxt.setBorder(null);
        consumoResModTxt.setForeground(new java.awt.Color(77, 77, 77));
        consumoResModTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.00"))));
        consumoResModTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        consumoResModTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                consumoResModTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                consumoResModTxtKeyTyped(evt);
            }
        });
        modificarReserva.add(consumoResModTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 180, 30));

        panelReserva.add(modificarReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        PanelPrincipal.add(panelReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 710, 490));

        panelPagamento.setBackground(new java.awt.Color(245, 245, 245));
        panelPagamento.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cadastrarPagamento.setBackground(new java.awt.Color(255, 255, 255));
        cadastrarPagamento.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cadastrarNovoPag.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cadastrarNovoPag.setForeground(new java.awt.Color(0, 93, 187));
        cadastrarNovoPag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconCP.png"))); // NOI18N
        cadastrarPagamento.add(cadastrarNovoPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        valorPag.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valorPag.setForeground(new java.awt.Color(77, 77, 77));
        valorPag.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        valorPag.setText("Valor R$");
        cadastrarPagamento.add(valorPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 60, 30));

        descPag.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descPag.setForeground(new java.awt.Color(77, 77, 77));
        descPag.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        descPag.setText("Descrição");
        cadastrarPagamento.add(descPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 60, 30));

        dataPag.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dataPag.setForeground(new java.awt.Color(77, 77, 77));
        dataPag.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dataPag.setText("Data");
        cadastrarPagamento.add(dataPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 60, 30));

        diaPag.setBackground(new java.awt.Color(0, 179, 136));
        diaPag.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diaPag.setForeground(new java.awt.Color(255, 255, 255));
        diaPag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        diaPag.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        diaPag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diaPagKeyPressed(evt);
            }
        });
        cadastrarPagamento.add(diaPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 50, 30));

        mesPag.setBackground(new java.awt.Color(0, 179, 136));
        mesPag.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesPag.setForeground(new java.awt.Color(255, 255, 255));
        mesPag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        mesPag.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mesPag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mesPagKeyPressed(evt);
            }
        });
        cadastrarPagamento.add(mesPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 50, 30));

        anoPag.setBackground(new java.awt.Color(0, 179, 136));
        anoPag.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        anoPag.setForeground(new java.awt.Color(255, 255, 255));
        anoPag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
        anoPag.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anoPag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                anoPagKeyPressed(evt);
            }
        });
        cadastrarPagamento.add(anoPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 60, 30));

        formaPag.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        formaPag.setForeground(new java.awt.Color(77, 77, 77));
        formaPag.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        formaPag.setText("Forma de Pagamento");
        cadastrarPagamento.add(formaPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 140, 30));

        formaPagTxt.setBackground(new java.awt.Color(204, 204, 204));
        formaPagTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        formaPagTxt.setForeground(new java.awt.Color(77, 77, 77));
        formaPagTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        formaPagTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formaPagTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formaPagTxtKeyTyped(evt);
            }
        });
        cadastrarPagamento.add(formaPagTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 320, 30));

        cadastrarPagBtn.setBackground(new java.awt.Color(0, 179, 136));
        cadastrarPagBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cadastrarPagBtn.setForeground(new java.awt.Color(255, 255, 255));
        cadastrarPagBtn.setText("Cadastrar");
        cadastrarPagBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrarPagBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrarPagBtnMouseClicked(evt);
            }
        });
        cadastrarPagamento.add(cadastrarPagBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 440, -1, 30));

        erroPag.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        erroPag.setForeground(new java.awt.Color(255, 0, 0));
        erroPag.setText("erro");
        cadastrarPagamento.add(erroPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, 310, -1));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        descPagTxt.setBackground(new java.awt.Color(204, 204, 204));
        descPagTxt.setColumns(20);
        descPagTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descPagTxt.setForeground(new java.awt.Color(77, 77, 77));
        descPagTxt.setLineWrap(true);
        descPagTxt.setRows(5);
        descPagTxt.setWrapStyleWord(true);
        descPagTxt.setMargin(new java.awt.Insets(5, 5, 5, 5));
        descPagTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descPagTxtKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(descPagTxt);

        cadastrarPagamento.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 320, 120));

        valorPagTxt.setBackground(new java.awt.Color(204, 204, 204));
        valorPagTxt.setBorder(null);
        valorPagTxt.setForeground(new java.awt.Color(77, 77, 77));
        valorPagTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valorPagTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                valorPagTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valorPagTxtKeyTyped(evt);
            }
        });
        cadastrarPagamento.add(valorPagTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 180, 30));

        panelPagamento.add(cadastrarPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 500));

        excluirPagamento.setBackground(new java.awt.Color(255, 255, 255));
        excluirPagamento.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectPagamentosExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectPagamentosExcluir.setForeground(new java.awt.Color(77, 77, 77));
        selectPagamentosExcluir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "valor", "forma de pagamento", "data", "descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectPagamentosExcluir.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        selectPagamentosExcluir.getTableHeader().setReorderingAllowed(false);
        selectPagamentosExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectPagamentosExcluirMouseClicked(evt);
            }
        });
        selectPagamentoExcluir.setViewportView(selectPagamentosExcluir);
        selectPagamentosExcluir.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (selectPagamentosExcluir.getColumnModel().getColumnCount() > 0) {
            selectPagamentosExcluir.getColumnModel().getColumn(0).setPreferredWidth(26);
        }

        excluirPagamento.add(selectPagamentoExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 670, 180));

        valorPagExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valorPagExcluir.setForeground(new java.awt.Color(77, 77, 77));
        valorPagExcluir.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        valorPagExcluir.setText("Descrição");
        excluirPagamento.add(valorPagExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, 30));

        excluirPag.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        excluirPag.setForeground(new java.awt.Color(0, 93, 187));
        excluirPag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        excluirPag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconExcluirP.png"))); // NOI18N
        excluirPagamento.add(excluirPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        infoPagExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoPagExcluir.setForeground(new java.awt.Color(77, 77, 77));
        infoPagExcluir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoPagExcluir.setText("Informações");
        excluirPagamento.add(infoPagExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 80, 30));

        excluirPagBtn.setBackground(new java.awt.Color(226, 0, 0));
        excluirPagBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        excluirPagBtn.setForeground(new java.awt.Color(255, 255, 255));
        excluirPagBtn.setText("Excluir");
        excluirPagBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluirPagBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                excluirPagBtnMouseClicked(evt);
            }
        });
        excluirPagamento.add(excluirPagBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 90, 30));

        descPagExcluir.setEditable(false);
        descPagExcluir.setBackground(new java.awt.Color(240, 240, 240));
        descPagExcluir.setBorder(null);
        descPagExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descPagExcluir.setForeground(new java.awt.Color(77, 77, 77));
        descPagExcluir.setCaretColor(new java.awt.Color(77, 77, 77));
        descPagExcluir.setDisabledTextColor(new java.awt.Color(77, 77, 77));
        descPagExcluir.setEnabled(false);
        descPagExcluir.setSelectedTextColor(new java.awt.Color(77, 77, 77));
        descPagExcluir.setSelectionColor(new java.awt.Color(77, 77, 77));
        infoPagExcluirPag.setViewportView(descPagExcluir);

        excluirPagamento.add(infoPagExcluirPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 530, 150));

        descPagExcluirTxt.setBackground(new java.awt.Color(204, 204, 204));
        descPagExcluirTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descPagExcluirTxt.setForeground(new java.awt.Color(77, 77, 77));
        descPagExcluirTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        descPagExcluirTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descPagExcluirTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descPagExcluirTxtKeyTyped(evt);
            }
        });
        excluirPagamento.add(descPagExcluirTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 220, 30));

        panelPagamento.add(excluirPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        verPagamento.setBackground(new java.awt.Color(255, 255, 255));
        verPagamento.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectPagamentosVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectPagamentosVer.setForeground(new java.awt.Color(77, 77, 77));
        selectPagamentosVer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "valor", "forma de pagamento", "data", "descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectPagamentosVer.getTableHeader().setReorderingAllowed(false);
        selectPagamentosVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectPagamentosVerMouseClicked(evt);
            }
        });
        selectPagamentoVer.setViewportView(selectPagamentosVer);
        selectPagamentosVer.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (selectPagamentosVer.getColumnModel().getColumnCount() > 0) {
            selectPagamentosVer.getColumnModel().getColumn(0).setPreferredWidth(26);
        }

        verPagamento.add(selectPagamentoVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 670, 180));

        valorPagVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valorPagVer.setForeground(new java.awt.Color(77, 77, 77));
        valorPagVer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        valorPagVer.setText("Descrição");
        verPagamento.add(valorPagVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, 30));

        verPag.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        verPag.setForeground(new java.awt.Color(0, 93, 187));
        verPag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        verPag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconVerP.png"))); // NOI18N
        verPagamento.add(verPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        infoPagVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoPagVer.setForeground(new java.awt.Color(77, 77, 77));
        infoPagVer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoPagVer.setText("Informações");
        verPagamento.add(infoPagVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 80, 30));

        descPagVer.setEditable(false);
        descPagVer.setBackground(new java.awt.Color(240, 240, 240));
        descPagVer.setBorder(null);
        descPagVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descPagVer.setForeground(new java.awt.Color(77, 77, 77));
        descPagVer.setCaretColor(new java.awt.Color(77, 77, 77));
        descPagVer.setDisabledTextColor(new java.awt.Color(77, 77, 77));
        descPagVer.setEnabled(false);
        descPagVer.setSelectedTextColor(new java.awt.Color(77, 77, 77));
        descPagVer.setSelectionColor(new java.awt.Color(77, 77, 77));
        infoPagVerPag.setViewportView(descPagVer);

        verPagamento.add(infoPagVerPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 670, 150));

        descPagVerTxt.setBackground(new java.awt.Color(204, 204, 204));
        descPagVerTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descPagVerTxt.setForeground(new java.awt.Color(77, 77, 77));
        descPagVerTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        descPagVerTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descPagVerTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descPagVerTxtKeyTyped(evt);
            }
        });
        verPagamento.add(descPagVerTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 220, 30));

        panelPagamento.add(verPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        modificarPagamento.setBackground(new java.awt.Color(255, 255, 255));
        modificarPagamento.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        modPag.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        modPag.setForeground(new java.awt.Color(0, 93, 187));
        modPag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconEditarP.png"))); // NOI18N
        modificarPagamento.add(modPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        valorPagMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valorPagMod.setForeground(new java.awt.Color(77, 77, 77));
        valorPagMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        valorPagMod.setText("Valor R$");
        modificarPagamento.add(valorPagMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 60, 30));

        descPagMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descPagMod.setForeground(new java.awt.Color(77, 77, 77));
        descPagMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        descPagMod.setText("Descrição");
        modificarPagamento.add(descPagMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 60, 30));

        dataPagMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dataPagMod.setForeground(new java.awt.Color(77, 77, 77));
        dataPagMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dataPagMod.setText("Data");
        modificarPagamento.add(dataPagMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 60, 30));

        diaPagMod.setBackground(new java.awt.Color(140, 0, 240));
        diaPagMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diaPagMod.setForeground(new java.awt.Color(255, 255, 255));
        diaPagMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        diaPagMod.setSelectedIndex(1);
        diaPagMod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        diaPagMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diaPagModKeyPressed(evt);
            }
        });
        modificarPagamento.add(diaPagMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 50, 30));

        mesPagMod.setBackground(new java.awt.Color(140, 0, 240));
        mesPagMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesPagMod.setForeground(new java.awt.Color(255, 255, 255));
        mesPagMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        mesPagMod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mesPagMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mesPagModKeyPressed(evt);
            }
        });
        modificarPagamento.add(mesPagMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, 50, 30));

        anoPagMod.setBackground(new java.awt.Color(140, 0, 240));
        anoPagMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        anoPagMod.setForeground(new java.awt.Color(255, 255, 255));
        anoPagMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
        anoPagMod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anoPagMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                anoPagModKeyPressed(evt);
            }
        });
        modificarPagamento.add(anoPagMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, 60, 30));

        formaPagMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        formaPagMod.setForeground(new java.awt.Color(77, 77, 77));
        formaPagMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        formaPagMod.setText("Forma de Pagamento");
        modificarPagamento.add(formaPagMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 140, 30));

        modificarPagBtn.setBackground(new java.awt.Color(140, 0, 240));
        modificarPagBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        modificarPagBtn.setForeground(new java.awt.Color(255, 255, 255));
        modificarPagBtn.setText("Modificar");
        modificarPagBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modificarPagBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarPagBtnMouseClicked(evt);
            }
        });
        modificarPagamento.add(modificarPagBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 440, -1, 30));

        erroPagMod.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        erroPagMod.setForeground(new java.awt.Color(255, 0, 0));
        erroPagMod.setText("erro");
        modificarPagamento.add(erroPagMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, 310, -1));

        jScrollPane3.setBackground(new java.awt.Color(204, 204, 204));

        descPagModTxt.setBackground(new java.awt.Color(204, 204, 204));
        descPagModTxt.setColumns(20);
        descPagModTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descPagModTxt.setForeground(new java.awt.Color(77, 77, 77));
        descPagModTxt.setLineWrap(true);
        descPagModTxt.setRows(5);
        descPagModTxt.setWrapStyleWord(true);
        descPagModTxt.setBorder(null);
        descPagModTxt.setMargin(new java.awt.Insets(5, 5, 5, 5));
        descPagModTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descPagModTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descPagModTxtKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(descPagModTxt);

        modificarPagamento.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 320, 120));

        idPagMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idPagMod.setForeground(new java.awt.Color(77, 77, 77));
        idPagMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        idPagMod.setText("Insira o ID do Pagamento");
        modificarPagamento.add(idPagMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 160, 30));

        idPagModTxt.setBackground(new java.awt.Color(204, 204, 204));
        idPagModTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idPagModTxt.setForeground(new java.awt.Color(77, 77, 77));
        idPagModTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        idPagModTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idPagModTxtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idPagModTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idPagModTxtKeyTyped(evt);
            }
        });
        modificarPagamento.add(idPagModTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 210, 30));

        formaPagModTxt.setBackground(new java.awt.Color(204, 204, 204));
        formaPagModTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        formaPagModTxt.setForeground(new java.awt.Color(77, 77, 77));
        formaPagModTxt.setBorder(null);
        formaPagModTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formaPagModTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formaPagModTxtKeyTyped(evt);
            }
        });
        modificarPagamento.add(formaPagModTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 320, 30));

        valorPagModTxt.setBackground(new java.awt.Color(204, 204, 204));
        valorPagModTxt.setBorder(null);
        valorPagModTxt.setForeground(new java.awt.Color(77, 77, 77));
        valorPagModTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.00"))));
        valorPagModTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        valorPagModTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                valorPagModTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valorPagModTxtKeyTyped(evt);
            }
        });
        modificarPagamento.add(valorPagModTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 180, 30));

        panelPagamento.add(modificarPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        PanelPrincipal.add(panelPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 710, 490));

        panelLembrete.setBackground(new java.awt.Color(255, 255, 255));
        panelLembrete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cadastrarLembrete.setBackground(new java.awt.Color(255, 255, 255));
        cadastrarLembrete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cadastrarNovoLem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cadastrarNovoLem.setForeground(new java.awt.Color(0, 93, 187));
        cadastrarNovoLem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cadastrarNovoLem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconCL.png"))); // NOI18N
        cadastrarLembrete.add(cadastrarNovoLem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        descLem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descLem.setForeground(new java.awt.Color(77, 77, 77));
        descLem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        descLem.setText("Descrição");
        cadastrarLembrete.add(descLem, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 60, 30));

        dataLem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dataLem.setForeground(new java.awt.Color(77, 77, 77));
        dataLem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dataLem.setText("Para o dia");
        cadastrarLembrete.add(dataLem, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 80, 30));

        diaLem.setBackground(new java.awt.Color(0, 179, 136));
        diaLem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diaLem.setForeground(new java.awt.Color(255, 255, 255));
        diaLem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        diaLem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        diaLem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diaLemKeyPressed(evt);
            }
        });
        cadastrarLembrete.add(diaLem, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 50, 30));

        mesLem.setBackground(new java.awt.Color(0, 179, 136));
        mesLem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesLem.setForeground(new java.awt.Color(255, 255, 255));
        mesLem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        mesLem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mesLem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mesLemKeyPressed(evt);
            }
        });
        cadastrarLembrete.add(mesLem, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 50, 30));

        anoLem.setBackground(new java.awt.Color(0, 179, 136));
        anoLem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        anoLem.setForeground(new java.awt.Color(255, 255, 255));
        anoLem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2100", "2099", "2098", "2097", "2096", "2095", "2094", "2093", "2092", "2091", "2090", "2089", "2088", "2087", "2086", "2085", "2084", "2083", "2082", "2081", "2080", "2079", "2078", "2077", "2076", "2075", "2074", "2073", "2072", "2071", "2070", "2069", "2068", "2067", "2066", "2065", "2064", "2063", "2062", "2061", "2060", "2059", "2058", "2057", "2056", "2055", "2054", "2053", "2052", "2051", "2050", "2049", "2048", "2047", "2046", "2045", "2044", "2043", "2042", "2041", "2040", "2039", "2038", "2037", "2036", "2035", "2034", "2033", "2032", "2031", "2030", "2029", "2028", "2027", "2026", "2025", "2024", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
        anoLem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anoLem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                anoLemKeyPressed(evt);
            }
        });
        cadastrarLembrete.add(anoLem, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 360, 60, 30));

        cadastrarLemBtn.setBackground(new java.awt.Color(0, 179, 136));
        cadastrarLemBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cadastrarLemBtn.setForeground(new java.awt.Color(255, 255, 255));
        cadastrarLemBtn.setText("Cadastrar");
        cadastrarLemBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrarLemBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrarLemBtnMouseClicked(evt);
            }
        });
        cadastrarLembrete.add(cadastrarLemBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 440, -1, 30));

        erroLem.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        erroLem.setForeground(new java.awt.Color(255, 0, 0));
        erroLem.setText("erro");
        cadastrarLembrete.add(erroLem, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, 310, -1));

        assuntoLem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        assuntoLem.setForeground(new java.awt.Color(77, 77, 77));
        assuntoLem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        assuntoLem.setText("Assunto");
        cadastrarLembrete.add(assuntoLem, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 60, 30));

        assuntoLemTxt.setBackground(new java.awt.Color(204, 204, 204));
        assuntoLemTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        assuntoLemTxt.setForeground(new java.awt.Color(77, 77, 77));
        assuntoLemTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        assuntoLemTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                assuntoLemTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                assuntoLemTxtKeyTyped(evt);
            }
        });
        cadastrarLembrete.add(assuntoLemTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 440, 30));

        descLemTxt.setBackground(new java.awt.Color(204, 204, 204));
        descLemTxt.setColumns(20);
        descLemTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descLemTxt.setForeground(new java.awt.Color(77, 77, 77));
        descLemTxt.setLineWrap(true);
        descLemTxt.setRows(8);
        descLemTxt.setToolTipText("");
        descLemTxt.setWrapStyleWord(true);
        descLemTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        descLemTxt.setCaretColor(new java.awt.Color(77, 77, 77));
        descLemTxt.setDisabledTextColor(new java.awt.Color(77, 77, 77));
        descLemTxt.setMargin(new java.awt.Insets(7, 5, 5, 7));
        descLemTxt.setMaximumSize(new java.awt.Dimension(240, 136));
        descLemTxt.setMinimumSize(new java.awt.Dimension(240, 136));
        descLemTxt.setSelectedTextColor(new java.awt.Color(77, 77, 77));
        descLemTxt.setSelectionColor(new java.awt.Color(255, 255, 255));
        descLemTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descLemTxtKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(descLemTxt);

        cadastrarLembrete.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 440, 180));

        panelLembrete.add(cadastrarLembrete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        excluirLembrete.setBackground(new java.awt.Color(255, 255, 255));
        excluirLembrete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectLembretesExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectLembretesExcluir.setForeground(new java.awt.Color(77, 77, 77));
        selectLembretesExcluir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome de usuário", "assunto", "data", "descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectLembretesExcluir.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        selectLembretesExcluir.getTableHeader().setReorderingAllowed(false);
        selectLembretesExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectLembretesExcluirMouseClicked(evt);
            }
        });
        selectLembreteExcluir.setViewportView(selectLembretesExcluir);
        selectLembretesExcluir.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (selectLembretesExcluir.getColumnModel().getColumnCount() > 0) {
            selectLembretesExcluir.getColumnModel().getColumn(0).setPreferredWidth(26);
        }

        excluirLembrete.add(selectLembreteExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 670, 180));

        descLemExcluirLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descLemExcluirLabel.setForeground(new java.awt.Color(77, 77, 77));
        descLemExcluirLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        descLemExcluirLabel.setText("Descrição");
        excluirLembrete.add(descLemExcluirLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, 30));

        excluirLem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        excluirLem.setForeground(new java.awt.Color(0, 93, 187));
        excluirLem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        excluirLem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconExcluirL.png"))); // NOI18N
        excluirLembrete.add(excluirLem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        infoLemExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoLemExcluir.setForeground(new java.awt.Color(77, 77, 77));
        infoLemExcluir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoLemExcluir.setText("Informações");
        excluirLembrete.add(infoLemExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 80, 30));

        excluirLemBtn.setBackground(new java.awt.Color(226, 0, 0));
        excluirLemBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        excluirLemBtn.setForeground(new java.awt.Color(255, 255, 255));
        excluirLemBtn.setText("Excluir");
        excluirLemBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excluirLemBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                excluirLemBtnMouseClicked(evt);
            }
        });
        excluirLembrete.add(excluirLemBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 90, 30));

        descLemExcluir.setEditable(false);
        descLemExcluir.setBackground(new java.awt.Color(240, 240, 240));
        descLemExcluir.setBorder(null);
        descLemExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descLemExcluir.setForeground(new java.awt.Color(77, 77, 77));
        descLemExcluir.setCaretColor(new java.awt.Color(77, 77, 77));
        descLemExcluir.setDisabledTextColor(new java.awt.Color(77, 77, 77));
        descLemExcluir.setEnabled(false);
        descLemExcluir.setSelectedTextColor(new java.awt.Color(77, 77, 77));
        descLemExcluir.setSelectionColor(new java.awt.Color(77, 77, 77));
        infoLemExcluirLem.setViewportView(descLemExcluir);

        excluirLembrete.add(infoLemExcluirLem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 530, 150));

        descLemExcluirTxt.setBackground(new java.awt.Color(204, 204, 204));
        descLemExcluirTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descLemExcluirTxt.setForeground(new java.awt.Color(77, 77, 77));
        descLemExcluirTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        descLemExcluirTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descLemExcluirTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descLemExcluirTxtKeyTyped(evt);
            }
        });
        excluirLembrete.add(descLemExcluirTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 230, 30));

        panelLembrete.add(excluirLembrete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        verLembrete.setBackground(new java.awt.Color(255, 255, 255));
        verLembrete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectLembretesVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectLembretesVer.setForeground(new java.awt.Color(77, 77, 77));
        selectLembretesVer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome de usuário", "assunto", "data", "descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selectLembretesVer.getTableHeader().setReorderingAllowed(false);
        selectLembretesVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectLembretesVerMouseClicked(evt);
            }
        });
        selectLembreteVer.setViewportView(selectLembretesVer);
        selectLembretesVer.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (selectLembretesVer.getColumnModel().getColumnCount() > 0) {
            selectLembretesVer.getColumnModel().getColumn(0).setPreferredWidth(26);
        }

        verLembrete.add(selectLembreteVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 670, 180));

        descLemVerLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descLemVerLabel.setForeground(new java.awt.Color(77, 77, 77));
        descLemVerLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        descLemVerLabel.setText("Descrição");
        verLembrete.add(descLemVerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, 30));

        verLem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        verLem.setForeground(new java.awt.Color(0, 93, 187));
        verLem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        verLem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconVerL.png"))); // NOI18N
        verLembrete.add(verLem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        infoLemVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoLemVer.setForeground(new java.awt.Color(77, 77, 77));
        infoLemVer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoLemVer.setText("Informações");
        verLembrete.add(infoLemVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 80, 30));

        descLemVer.setEditable(false);
        descLemVer.setBackground(new java.awt.Color(240, 240, 240));
        descLemVer.setBorder(null);
        descLemVer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descLemVer.setForeground(new java.awt.Color(77, 77, 77));
        descLemVer.setCaretColor(new java.awt.Color(77, 77, 77));
        descLemVer.setDisabledTextColor(new java.awt.Color(77, 77, 77));
        descLemVer.setEnabled(false);
        descLemVer.setSelectedTextColor(new java.awt.Color(77, 77, 77));
        descLemVer.setSelectionColor(new java.awt.Color(77, 77, 77));
        infoLemVerLem.setViewportView(descLemVer);

        verLembrete.add(infoLemVerLem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 670, 150));

        descLemVerTxt.setBackground(new java.awt.Color(204, 204, 204));
        descLemVerTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descLemVerTxt.setForeground(new java.awt.Color(77, 77, 77));
        descLemVerTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        descLemVerTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descLemVerTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descLemVerTxtKeyTyped(evt);
            }
        });
        verLembrete.add(descLemVerTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 230, 30));

        panelLembrete.add(verLembrete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        modificarLembrete.setBackground(new java.awt.Color(255, 255, 255));
        modificarLembrete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        modLem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        modLem.setForeground(new java.awt.Color(0, 93, 187));
        modLem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modLem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/iconEditarL.png"))); // NOI18N
        modificarLembrete.add(modLem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        descLemMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descLemMod.setForeground(new java.awt.Color(77, 77, 77));
        descLemMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        descLemMod.setText("Descrição");
        modificarLembrete.add(descLemMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 60, 30));

        dataLemMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dataLemMod.setForeground(new java.awt.Color(77, 77, 77));
        dataLemMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dataLemMod.setText("Para o dia");
        modificarLembrete.add(dataLemMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 80, 30));

        diaLem1.setBackground(new java.awt.Color(140, 0, 240));
        diaLem1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diaLem1.setForeground(new java.awt.Color(255, 255, 255));
        diaLem1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        diaLem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        diaLem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diaLem1KeyPressed(evt);
            }
        });
        modificarLembrete.add(diaLem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 50, 30));

        mesLem1.setBackground(new java.awt.Color(140, 0, 240));
        mesLem1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesLem1.setForeground(new java.awt.Color(255, 255, 255));
        mesLem1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        mesLem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mesLem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mesLem1KeyPressed(evt);
            }
        });
        modificarLembrete.add(mesLem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 50, 30));

        anoLem1.setBackground(new java.awt.Color(140, 0, 240));
        anoLem1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        anoLem1.setForeground(new java.awt.Color(255, 255, 255));
        anoLem1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2100", "2099", "2098", "2097", "2096", "2095", "2094", "2093", "2092", "2091", "2090", "2089", "2088", "2087", "2086", "2085", "2084", "2083", "2082", "2081", "2080", "2079", "2078", "2077", "2076", "2075", "2074", "2073", "2072", "2071", "2070", "2069", "2068", "2067", "2066", "2065", "2064", "2063", "2062", "2061", "2060", "2059", "2058", "2057", "2056", "2055", "2054", "2053", "2052", "2051", "2050", "2049", "2048", "2047", "2046", "2045", "2044", "2043", "2042", "2041", "2040", "2039", "2038", "2037", "2036", "2035", "2034", "2033", "2032", "2031", "2030", "2029", "2028", "2027", "2026", "2025", "2024", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
        anoLem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anoLem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                anoLem1KeyPressed(evt);
            }
        });
        modificarLembrete.add(anoLem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 60, 30));

        cadastrarLemBtn1.setBackground(new java.awt.Color(140, 0, 240));
        cadastrarLemBtn1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cadastrarLemBtn1.setForeground(new java.awt.Color(255, 255, 255));
        cadastrarLemBtn1.setText("Modificar");
        cadastrarLemBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cadastrarLemBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrarLemBtn1MouseClicked(evt);
            }
        });
        modificarLembrete.add(cadastrarLemBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 440, -1, 30));

        erroLem1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        erroLem1.setForeground(new java.awt.Color(255, 0, 0));
        erroLem1.setText("erro");
        modificarLembrete.add(erroLem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, 310, -1));

        idLemMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idLemMod.setForeground(new java.awt.Color(77, 77, 77));
        idLemMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        idLemMod.setText("Insira o ID do Lembrete");
        modificarLembrete.add(idLemMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 160, 30));

        idLemModTxt.setBackground(new java.awt.Color(204, 204, 204));
        idLemModTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idLemModTxt.setForeground(new java.awt.Color(77, 77, 77));
        idLemModTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        idLemModTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idLemModTxtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idLemModTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idLemModTxtKeyTyped(evt);
            }
        });
        modificarLembrete.add(idLemModTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 250, 30));

        descLemModTxt.setBackground(new java.awt.Color(204, 204, 204));
        descLemModTxt.setColumns(20);
        descLemModTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descLemModTxt.setForeground(new java.awt.Color(77, 77, 77));
        descLemModTxt.setLineWrap(true);
        descLemModTxt.setRows(8);
        descLemModTxt.setToolTipText("");
        descLemModTxt.setWrapStyleWord(true);
        descLemModTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        descLemModTxt.setCaretColor(new java.awt.Color(77, 77, 77));
        descLemModTxt.setDisabledTextColor(new java.awt.Color(77, 77, 77));
        descLemModTxt.setMargin(new java.awt.Insets(7, 5, 5, 7));
        descLemModTxt.setMaximumSize(new java.awt.Dimension(240, 136));
        descLemModTxt.setMinimumSize(new java.awt.Dimension(240, 136));
        descLemModTxt.setSelectedTextColor(new java.awt.Color(77, 77, 77));
        descLemModTxt.setSelectionColor(new java.awt.Color(255, 255, 255));
        descLemModTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descLemModTxtKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(descLemModTxt);

        modificarLembrete.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 440, 180));

        assuntoLemMod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        assuntoLemMod.setForeground(new java.awt.Color(77, 77, 77));
        assuntoLemMod.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        assuntoLemMod.setText("Assunto");
        modificarLembrete.add(assuntoLemMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 60, 30));

        assuntoLemModTxt.setBackground(new java.awt.Color(204, 204, 204));
        assuntoLemModTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        assuntoLemModTxt.setForeground(new java.awt.Color(77, 77, 77));
        assuntoLemModTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        assuntoLemModTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                assuntoLemModTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                assuntoLemModTxtKeyTyped(evt);
            }
        });
        modificarLembrete.add(assuntoLemModTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 440, 30));

        panelLembrete.add(modificarLembrete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        PanelPrincipal.add(panelLembrete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 710, 490));

        info.setBackground(new java.awt.Color(255, 255, 255));
        info.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        func1.setBackground(new java.awt.Color(0, 30, 60));
        func1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/func1.png"))); // NOI18N
        func1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 145, 224));

        info.add(func1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 145, 224));

        func2.setBackground(new java.awt.Color(0, 30, 60));
        func2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/func2.png"))); // NOI18N
        func2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 145, 224));

        info.add(func2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 145, 224));

        func3.setBackground(new java.awt.Color(0, 30, 60));
        func3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/func3.png"))); // NOI18N
        func3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 145, 224));

        info.add(func3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 145, 224));

        func4.setBackground(new java.awt.Color(0, 30, 60));
        func4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/func4.png"))); // NOI18N
        func4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 145, 224));

        info.add(func4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 145, 224));

        line1.setBackground(new java.awt.Color(0, 30, 60));
        info.add(line1, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 53, 137, 2));

        line2.setBackground(new java.awt.Color(0, 30, 60));
        info.add(line2, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 61, 106, 2));

        line3.setBackground(new java.awt.Color(0, 30, 60));
        info.add(line3, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 70, 84, 2));

        line4.setBackground(new java.awt.Color(0, 30, 60));
        line4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        info.add(line4, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 78, 52, 2));

        iconF1.setBackground(new java.awt.Color(0, 30, 60));
        info.add(iconF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 357, 110, 96));

        iconF2.setBackground(new java.awt.Color(0, 30, 60));
        info.add(iconF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 372, 100, 81));

        iconF3.setBackground(new java.awt.Color(0, 30, 60));
        info.add(iconF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(389, 374, 96, 70));

        iconF4.setBackground(new java.awt.Color(0, 30, 60));
        info.add(iconF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 370, 84, 84));
        info.add(func1ICON, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 358, 95, 95));
        info.add(func2ICON, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 373, 96, 80));
        info.add(func3ICON, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 375, 95, 67));
        info.add(func4ICON, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 370, 83, 83));

        equipe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/equipe.png"))); // NOI18N
        info.add(equipe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 490));

        PanelPrincipal.add(info, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 710, 490));

        logadoComo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        logadoComo.setForeground(new java.awt.Color(77, 77, 77));
        logadoComo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-user-32.png"))); // NOI18N
        logadoComo.setText("Logado como: ");
        PanelPrincipal.add(logadoComo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 160, 40));

        logadoComoUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        logadoComoUsuario.setForeground(new java.awt.Color(77, 77, 77));
        logadoComoUsuario.setText("admin");
        PanelPrincipal.add(logadoComoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 250, 40));

        logoInicio1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/logoInicio.png"))); // NOI18N
        logoInicio1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        logoInicio1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        PanelPrincipal.add(logoInicio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 500));

        logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-logout.png"))); // NOI18N
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutMouseEntered(evt);
            }
        });
        PanelPrincipal.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        relogio.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        relogio.setForeground(new java.awt.Color(77, 77, 77));
        relogio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-clock.png"))); // NOI18N
        relogio.setText("23:59:59");
        PanelPrincipal.add(relogio, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 140, 40));

        getContentPane().add(PanelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 710, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private String string;

    /**
     * Get the value of string
     *
     * @return the value of string
     */
    public String getString() {
        return string;
    }

    /**
     * Set the value of string
     *
     * @param string new value of string
     */
    public void setString(String string) {
        this.string = string;
    }

    private transient final java.beans.PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private void sairTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sairTxtMouseClicked
        System.exit(0);
    }//GEN-LAST:event_sairTxtMouseClicked

    private void minimizarTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarTxtMouseClicked
        this.setState(1);
    }//GEN-LAST:event_minimizarTxtMouseClicked

    private void nomeFuncionarioTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeFuncionarioTxtKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || nomeFuncionarioTxt.getText().length() >= 100) {
            evt.consume();
        }
    }//GEN-LAST:event_nomeFuncionarioTxtKeyTyped

    private void emaiFuncionarioTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emaiFuncionarioTxtKeyTyped
        String caracteres = "@.0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || emaiFuncionarioTxt.getText().length() > 50) {
            evt.consume();
        }
    }//GEN-LAST:event_emaiFuncionarioTxtKeyTyped

    private void telefoneFuncionarioTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefoneFuncionarioTxtKeyTyped
        String caracteres = "0987654321-";
        if (!caracteres.contains(evt.getKeyChar() + "") || telefoneFuncionarioTxt.getText().length() >= 12) {
            evt.consume();
        } else {
            if (telefoneFuncionarioTxt.getText().length() == 7) {
                telefoneFuncionarioTxt.setText(telefoneFuncionarioTxt.getText() + "-");
            }
        }
    }//GEN-LAST:event_telefoneFuncionarioTxtKeyTyped

    private void rgFuncionarioTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rgFuncionarioTxtKeyTyped
        String caracteres = "0987654321.-";
        if (!caracteres.contains(evt.getKeyChar() + "") || rgFuncionarioTxt.getText().length() >= 12) {
            evt.consume();
        }
    }//GEN-LAST:event_rgFuncionarioTxtKeyTyped

    private void cpfFuncionarioTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cpfFuncionarioTxtKeyTyped
        String caracteres = "0987654321.-/xX";
        if (!caracteres.contains(evt.getKeyChar() + "") || cpfFuncionarioTxt.getText().length() >= 14) {
            evt.consume();
        }
    }//GEN-LAST:event_cpfFuncionarioTxtKeyTyped

    private void enderecoFuncionarioTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enderecoFuncionarioTxtKeyTyped
        String caracteres = " .,º0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || enderecoFuncionario.getText().length() > 200) {
            evt.consume();
        }
    }//GEN-LAST:event_enderecoFuncionarioTxtKeyTyped

    private void cargoFuncionarioTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cargoFuncionarioTxtKeyTyped
        String caracteres = " 0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || cargoFuncionario.getText().length() > 50) {
            evt.consume();
        }
    }//GEN-LAST:event_cargoFuncionarioTxtKeyTyped

    private void cadastrarFuncionarioBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarFuncionarioBtnMouseClicked
        erroFunc.setText("");
        if (nomeFuncionarioTxt.getText().equals("") || rgFuncionarioTxt.getText().equals("") || cpfFuncionarioTxt.getText().equals("") || emaiFuncionarioTxt.getText().equals("") || telefoneFuncionarioTxt.getText().equals("") || enderecoFuncionarioTxt.getText().equals("") || cargoFuncionarioTxt.getText().equals("")) {
            erroFunc.setText("Um ou mais campos foram deixados vazios!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram deixados vazios.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (cpfFuncionarioTxt.getText().length() != 14 && rgFuncionarioTxt.getText().length() != 12) {
            erroFunc.setText("Campo RG e CPF não foram preenchidos corretamente!");
            MessageStatus m = new MessageStatus(usuario, nome, "Campo RG e CPF não foram preenchidos corretamente.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);

        } else if (rgFuncionarioTxt.getText().length() != 12) {
            erroFunc.setText("Campo RG não foi preenchido corretamente!");
            MessageStatus m = new MessageStatus(usuario, nome, "Campo RG não foi preenchido corretamente.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);

        } else if (UserPassNew.isSelected() && (senhaFuncionarioTxt.getText().equals("") || usuarioFuncionarioTxt.getText().equals(""))) {
            erroFunc.setText("Campo(s) de usuário e senha não foram preenchidos corretamente!");
            MessageStatus m = new MessageStatus(usuario, nome, "Campo(s) de usuário e senha não foram preenchidos corretamente.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (cpfFuncionarioTxt.getText().length() != 14) {
            erroFunc.setText("Campo CPF não foi preenchido corretamente!");
            MessageStatus m = new MessageStatus(usuario, nome, "Campo CPF não foi preenchido corretamente.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (telefoneFuncionarioTxt.getText().length() <10 ||  telefoneFuncionarioTxt.getText().length() >12) {
            erroFunc.setText("Campo Telefone não foi preenchido corretamente!");
            MessageStatus m = new MessageStatus(usuario, nome, "Campo Telefone não foi preenchido corretamente.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);

        } else if (telefoneFuncionarioTxt.getText().length() > 16 || cpfFuncionarioTxt.getText().length() > 14 || rgFuncionarioTxt.getText().length() > 12 || nomeFuncionarioTxt.getText().length() > 100 || emaiFuncionarioTxt.getText().length() > 50 || enderecoFuncionarioTxt.getText().length() > 200 || cargoFuncionarioTxt.getText().length() > 50 || usuarioFuncionarioTxt.getText().length() > 100 || senhaFuncionarioTxt.getText().length() > 100) {
            erroFunc.setText("Um ou mais campos foram preenchidos com muitos caracteres!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram preenchidos com muitos caracteres.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);

        } else {
            Funcionario f = new Funcionario();
            f.nome = nomeFuncionarioTxt.getText().replace("'", "");
            f.sexo = (String) sexoFuncionarioTxt.getSelectedItem();
            f.nascimento = anoNascFuncionarioTxt.getSelectedItem() + "-" + mesNascFuncionarioTxt.getSelectedItem() + "-" + diaNascFuncionarioTxt.getSelectedItem();
            f.rg = rgFuncionarioTxt.getText().replace("'", "");
            f.cpf = cpfFuncionarioTxt.getText().replace("'", "");
            f.email = emaiFuncionarioTxt.getText().replace("'", "");
            f.telefone = telefoneFuncionarioTxt.getText().replace("'", "");
            f.endereco = enderecoFuncionarioTxt.getText().replace("'", "");
            f.dataContratacao = anoConNovoFunc.getSelectedItem() + "-" + mesConNovoFunc.getSelectedItem() + "-" + diaConNovoFunc.getSelectedItem();
            f.cargaHoraria = (String) cargaHorariaFuncionarioTxt.getSelectedItem();
            f.cargo = cargoFuncionarioTxt.getText().replace("'", "");
            if (UserPassNew.isSelected()) {
                f.usuario = usuarioFuncionarioTxt.getText().replace("'", "");
                f.senha = senhaFuncionarioTxt.getText().replace("'", "");
                f.gravar(usuario, nome, 1);
            } else {
                f.gravar(usuario, nome, 0);
            }
            resetCampos();
        }
    }//GEN-LAST:event_cadastrarFuncionarioBtnMouseClicked

    private void nomeFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            sexoFuncionarioTxt.requestFocus();
        }
    }//GEN-LAST:event_nomeFuncionarioTxtKeyPressed

    private void sexoFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sexoFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            diaNascFuncionarioTxt.requestFocus();
        }
    }//GEN-LAST:event_sexoFuncionarioTxtKeyPressed

    private void mesNascFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mesNascFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            anoNascFuncionarioTxt.requestFocus();
        }
    }//GEN-LAST:event_mesNascFuncionarioTxtKeyPressed

    private void diaNascFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diaNascFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            mesNascFuncionarioTxt.requestFocus();
        }
    }//GEN-LAST:event_diaNascFuncionarioTxtKeyPressed

    private void anoNascFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anoNascFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            rgFuncionarioTxt.requestFocus();
        }
    }//GEN-LAST:event_anoNascFuncionarioTxtKeyPressed

    private void cpfFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cpfFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            emaiFuncionarioTxt.requestFocus();
        }
    }//GEN-LAST:event_cpfFuncionarioTxtKeyPressed

    private void rgFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rgFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cpfFuncionarioTxt.requestFocus();
        }
    }//GEN-LAST:event_rgFuncionarioTxtKeyPressed

    private void emaiFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emaiFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            telefoneFuncionarioTxt.requestFocus();
        }
    }//GEN-LAST:event_emaiFuncionarioTxtKeyPressed

    private void telefoneFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefoneFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            enderecoFuncionarioTxt.requestFocus();
        }
    }//GEN-LAST:event_telefoneFuncionarioTxtKeyPressed

    private void diaConNovoFuncKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diaConNovoFuncKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            mesConNovoFunc.requestFocus();
        }
    }//GEN-LAST:event_diaConNovoFuncKeyPressed

    private void enderecoFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enderecoFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            diaConNovoFunc.requestFocus();
        }
    }//GEN-LAST:event_enderecoFuncionarioTxtKeyPressed

    private void mesConNovoFuncKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mesConNovoFuncKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            anoConNovoFunc.requestFocus();
        }
    }//GEN-LAST:event_mesConNovoFuncKeyPressed

    private void anoConNovoFuncKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anoConNovoFuncKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cargaHorariaFuncionarioTxt.requestFocus();
        }
    }//GEN-LAST:event_anoConNovoFuncKeyPressed

    private void cargaHorariaFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cargaHorariaFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cargoFuncionarioTxt.requestFocus();
        }
    }//GEN-LAST:event_cargaHorariaFuncionarioTxtKeyPressed

    private void cargoFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cargoFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            usuarioFuncionarioTxt.requestFocus();
        }
    }//GEN-LAST:event_cargoFuncionarioTxtKeyPressed

    private void nomeHospedeTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeHospedeTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            sexoHospedeTxt.requestFocus();
        }
    }//GEN-LAST:event_nomeHospedeTxtKeyPressed

    private void nomeHospedeTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeHospedeTxtKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || nomeHospedeTxt.getText().length() >= 100) {
            evt.consume();
        }
    }//GEN-LAST:event_nomeHospedeTxtKeyTyped

    private void sexoHospedeTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sexoHospedeTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            diaNascHospedeTxt.requestFocus();
        }
    }//GEN-LAST:event_sexoHospedeTxtKeyPressed

    private void diaNascHospedeTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diaNascHospedeTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            mesNascHospedeTxt.requestFocus();
        }
    }//GEN-LAST:event_diaNascHospedeTxtKeyPressed

    private void mesNascHospedeTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mesNascHospedeTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            anoNascHospedeTxt.requestFocus();
        }
    }//GEN-LAST:event_mesNascHospedeTxtKeyPressed

    private void anoNascHospedeTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anoNascHospedeTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            rgHospedeTxt.requestFocus();
        }
    }//GEN-LAST:event_anoNascHospedeTxtKeyPressed

    private void rgHospedeTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rgHospedeTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cpfHospedeTxt.requestFocus();
        }
    }//GEN-LAST:event_rgHospedeTxtKeyPressed

    private void rgHospedeTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rgHospedeTxtKeyTyped
        String caracteres = "0987654321.-";
        if (!caracteres.contains(evt.getKeyChar() + "") || rgHospedeTxt.getText().length() >= 12) {
            evt.consume();
        }
    }//GEN-LAST:event_rgHospedeTxtKeyTyped

    private void cpfHospedeTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cpfHospedeTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            telefoneHospedeTxt.requestFocus();
        }
    }//GEN-LAST:event_cpfHospedeTxtKeyPressed

    private void cpfHospedeTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cpfHospedeTxtKeyTyped
        String caracteres = "0987654321.-/";
        if (!caracteres.contains(evt.getKeyChar() + "") || cpfHospedeTxt.getText().length() >= 14) {
            evt.consume();
        }
    }//GEN-LAST:event_cpfHospedeTxtKeyTyped

    private void telefoneHospedeTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefoneHospedeTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cidadeHospedeTxt.requestFocus();
        }
    }//GEN-LAST:event_telefoneHospedeTxtKeyPressed

    private void telefoneHospedeTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefoneHospedeTxtKeyTyped
        String caracteres = "0987654321-";
        if (!caracteres.contains(evt.getKeyChar() + "") || telefoneHospedeTxt.getText().length() >= 12) {
            evt.consume();
        }
    }//GEN-LAST:event_telefoneHospedeTxtKeyTyped

    private void cadastrarHospedeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarHospedeBtnMouseClicked
        erroHospede.setText("");
        if (nomeHospedeTxt.getText().equals("") || rgHospedeTxt.getText().equals("") || cpfHospedeTxt.getText().equals("") || telefoneHospedeTxt.getText().equals("")) {
            erroHospede.setText("Um ou mais campos foram deixados vazios!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram deixados vazios.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (cpfHospedeTxt.getText().length() != 14 && rgHospedeTxt.getText().length() != 12) {
            erroHospede.setText("Campo CPF e RG foram preenchidos incorretamente!");
            MessageStatus m = new MessageStatus(usuario, nome, "Campo CPF e RG foram preenchidos incorretamente.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (rgHospedeTxt.getText().length() != 12) {
            erroHospede.setText("Campo RG foi preenchido incorretamente!");
            MessageStatus m = new MessageStatus(usuario, nome, "Campo RG foi preenchido incorretamente.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (cpfHospedeTxt.getText().length() != 14) {
            erroHospede.setText("Campo CPF não foi preenchido corretamente!");
            MessageStatus m = new MessageStatus(usuario, nome, "Campo CPF não foi preenchido corretamente.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);

        } else if (telefoneHospedeTxt.getText().length() != 12) {
            erroHospede.setText("Campo Telefone não foi preenchido corretamente!");
            MessageStatus m = new MessageStatus(usuario, nome, "Campo Telefone não foi preenchido corretamente.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (nomeHospedeTxt.getText().length() > 100 || rgHospedeTxt.getText().length() > 16 || cpfHospedeTxt.getText().length() > 14 || telefoneHospedeTxt.getText().length() > 16 || cidadeHospedeTxt.getText().length() > 50) {
            erroHospede.setText("Um ou mais campos foram preenchidos com muitos caracteres!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram preenchidos com muitos caracteres.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else {
            Hospede f = new Hospede();
            f.nome = nomeHospedeTxt.getText().replace("'", "");
            f.sexo = (String) sexoHospedeTxt.getSelectedItem();
            f.nascimento = anoNascHospedeTxt.getSelectedItem() + "-" + mesNascHospedeTxt.getSelectedItem() + "-" + diaNascHospedeTxt.getSelectedItem();
            f.rg = rgHospedeTxt.getText().replace("'", "");
            f.cpf = cpfHospedeTxt.getText().replace("'", "");
            f.telefone = telefoneHospedeTxt.getText().replace("'", "");
            f.cidade = cidadeHospedeTxt.getText().replace("'", "");
            f.gravar();
            resetCampos();
        }
    }//GEN-LAST:event_cadastrarHospedeBtnMouseClicked

    private void numQuartoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numQuartoTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            andarQuartoTxt.requestFocus();
        }
    }//GEN-LAST:event_numQuartoTxtKeyPressed

    private void numQuartoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numQuartoTxtKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "") || numQuartoTxt.getText().length() >= 4) {
            evt.consume();
        }
    }//GEN-LAST:event_numQuartoTxtKeyTyped

    private void andarQuartoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_andarQuartoTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            precoQuartoTxt.requestFocus();
        }
    }//GEN-LAST:event_andarQuartoTxtKeyPressed

    private void andarQuartoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_andarQuartoTxtKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "") || andarQuartoTxt.getText().length() >= 3) {
            evt.consume();
        }
    }//GEN-LAST:event_andarQuartoTxtKeyTyped

    private void tipoQuartoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipoQuartoTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            descricaoQuartoTxt.requestFocus();
        }
    }//GEN-LAST:event_tipoQuartoTxtKeyPressed

    private void tipoQuartoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipoQuartoTxtKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || tipoQuartoTxt.getText().length() >= 25) {
            evt.consume();
        }
    }//GEN-LAST:event_tipoQuartoTxtKeyTyped

    private void cadastrarQuartoBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarQuartoBtnMouseClicked
        erroQuarto.setText("");
        if (numQuartoTxt.getText().equals("") || andarQuartoTxt.getText().equals("") || tipoQuartoTxt.getText().equals("") || descricaoQuartoTxt.getText().equals("") || precoQuartoTxt.getText().equals("")) {
            erroQuarto.setText("Um ou mais campos foram deixados vazios!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram deixados vazios.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (numQuartoTxt.getText().length() > 4 || andarQuartoTxt.getText().length() > 3 || precoQuartoTxt.getText().length() > 10 || tipoQuartoTxt.getText().length() > 25 || descricaoQuartoTxt.getText().length() > 500) {
            erroQuarto.setText("Um ou mais campos foram preenchidos com muitos caracteres!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram preenchidos com muitos caracteres.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else {
            Quarto q = new Quarto();
            q.numero = Integer.parseInt(numQuartoTxt.getText().replace("'", ""));
            q.andar = Integer.parseInt(andarQuartoTxt.getText().replace("'", ""));
            q.preco = Double.parseDouble(precoQuartoTxt.getText().replace(",", "."));
            q.tipo = tipoQuartoTxt.getText().replace("'", "");
            q.descricao = descricaoQuartoTxt.getText().replace("'", "");
            q.gravar();
            resetCampos();
        }
    }//GEN-LAST:event_cadastrarQuartoBtnMouseClicked

    private void numQuartoReservaTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numQuartoReservaTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            nomeHospReservaTxt.requestFocus();
        }
    }//GEN-LAST:event_numQuartoReservaTxtKeyPressed

    private void numQuartoReservaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numQuartoReservaTxtKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "") || numQuartoReservaTxt.getText().length() >= 4) {
            evt.consume();
        }
    }//GEN-LAST:event_numQuartoReservaTxtKeyTyped

    private void nomeHospReservaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeHospReservaTxtKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || nomeHospReservaTxt.getText().length() >= 100) {
            evt.consume();
        }
    }//GEN-LAST:event_nomeHospReservaTxtKeyTyped

    private void mesEntradaReservaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mesEntradaReservaKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            anoEntradaReserva.requestFocus();
        }
    }//GEN-LAST:event_mesEntradaReservaKeyPressed

    private void anoEntradaReservaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anoEntradaReservaKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            diaSaidaReserva.requestFocus();
        }
    }//GEN-LAST:event_anoEntradaReservaKeyPressed

    private void diaSaidaReservaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diaSaidaReservaKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            mesSaidaReserva.requestFocus();
        }
    }//GEN-LAST:event_diaSaidaReservaKeyPressed

    private void mesSaidaReservaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mesSaidaReservaKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            anoSaidaReserva.requestFocus();
        }
    }//GEN-LAST:event_mesSaidaReservaKeyPressed

    private void anoSaidaReservaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anoSaidaReservaKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cadastrarReservaBtn.requestFocus();
        }
    }//GEN-LAST:event_anoSaidaReservaKeyPressed

    private void descricaoQuartoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descricaoQuartoTxtKeyTyped
        String caracteres = " 1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || descricaoQuartoTxt.getText().length() >= 500) {
            evt.consume();
        }
    }//GEN-LAST:event_descricaoQuartoTxtKeyTyped

    private void descricaoQuartoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descricaoQuartoTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cadastrarQuartoBtn.requestFocus();
        }
    }//GEN-LAST:event_descricaoQuartoTxtKeyPressed

    private void diaPagKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diaPagKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            mesPag.requestFocus();
        }
    }//GEN-LAST:event_diaPagKeyPressed

    private void mesPagKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mesPagKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            anoPag.requestFocus();
        }
    }//GEN-LAST:event_mesPagKeyPressed

    private void anoPagKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anoPagKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            descPagTxt.requestFocus();
        }
    }//GEN-LAST:event_anoPagKeyPressed

    private void cadastrarPagBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarPagBtnMouseClicked
        erroPag.setText("");
        if (descPagTxt.getText().equals("") || valorPagTxt.getText().equals("") || formaPagTxt.getText().equals("")) {
            erroPag.setText("Um ou mais campos foram deixados vazios!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram deixados vazios.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (descPagTxt.getText().length() > 200 || valorPagTxt.getText().length() > 15 || formaPagTxt.getText().length() > 50) {
            erroPag.setText("Um ou mais campos foram preenchidos com muitos caracteres!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram preenchidos com muitos caracteres.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else {
            Pagamento p = new Pagamento();
            //p.idFluxo = Integer.parseInt(idFluxoPagTxt.getText().replace("'", ""));
            p.descricao = descPagTxt.getText().replace("'", "");
            p.valor = Double.parseDouble(valorPagTxt.getText().replace(",", "."));
            p.data = anoPag.getSelectedItem() + "-" + mesPag.getSelectedItem() + "-" + diaPag.getSelectedItem();
            p.Fpagamento = formaPagTxt.getText().replace("'", "");
            p.gravar();
            resetCampos();
        }
    }//GEN-LAST:event_cadastrarPagBtnMouseClicked

    private void formaPagTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formaPagTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            valorPagTxt.requestFocus();
        }
    }//GEN-LAST:event_formaPagTxtKeyPressed

    private void usuarioFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            senhaFuncionarioTxt.requestFocus();
        }
    }//GEN-LAST:event_usuarioFuncionarioTxtKeyPressed

    private void senhaFuncionarioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaFuncionarioTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cadastrarFuncionarioBtn.requestFocus();
        }
    }//GEN-LAST:event_senhaFuncionarioTxtKeyPressed

    private void diaEntradaReservaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diaEntradaReservaKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            mesEntradaReserva.requestFocus();
        }
    }//GEN-LAST:event_diaEntradaReservaKeyPressed

    private void cadastrarReservaBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarReservaBtnMouseClicked
        int linha = selectHospedeReserva.getSelectedRow();
        erroReserva.setText("");
        
        String entrada = anoEntradaReserva.getSelectedItem() + "-" + mesEntradaReserva.getSelectedItem() + "-" + diaEntradaReserva.getSelectedItem();
        String saida = anoSaidaReserva.getSelectedItem() + "-" + mesSaidaReserva.getSelectedItem() + "-" + diaSaidaReserva.getSelectedItem();
        
        if (numQuartoReservaTxt.getText().equals("") || nomeHospReservaTxt.getText().equals("")) {
            erroReserva.setText("Um ou mais campos foram deixados vazios!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram deixados vazios.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (comparaDatas(entrada, saida) == 5){
            erroResMod.setText("Data de saida menor do que a de entrada");
            MessageStatus m = new MessageStatus(usuario, nome, "Data de saida menor do que a de entrada.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (comparaDatas(entrada, saida) == 6){
            erroResMod.setText("Data de entrada menor do que a de hoje");
            MessageStatus m = new MessageStatus(usuario, nome, "Data de entrada menor do que a de hoje.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else {
            Reserva r = new Reserva();
            r.id_hospede = Integer.parseInt(selectHospedeReserva.getValueAt(linha, 0).toString());
            r.num_quarto = Integer.parseInt(numQuartoReservaTxt.getText().replace("'", ""));
            r.dataEntrada = anoEntradaReserva.getSelectedItem() + "-" + mesEntradaReserva.getSelectedItem() + "-" + diaEntradaReserva.getSelectedItem();
            r.dataSaida = anoSaidaReserva.getSelectedItem() + "-" + mesSaidaReserva.getSelectedItem() + "-" + diaSaidaReserva.getSelectedItem();
            r.gravar();
            resetCampos();
        }
    }//GEN-LAST:event_cadastrarReservaBtnMouseClicked

    private void verQuartoReservaBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verQuartoReservaBtnMouseClicked
        selectHospedesReservas.setVisible(false);
        selectQuartosReservas.setVisible(true);
        descricaoQuartoReserva.setText("");
        infoSelectReserva.setText("Descrição do Quarto");
        tableNewRes.setText("Tabela de Quartos");
        selectQuaJTable("SELECT * FROM quartos WHERE numero like '%" + numQuartoReservaTxt.getText().replace("'", "") + "%' and disponivel = 'S';");
    }//GEN-LAST:event_verQuartoReservaBtnMouseClicked

    private void selectHospedeReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectHospedeReservaMouseClicked
        int linha = selectHospedeReserva.getSelectedRow();
        descricaoQuartoReserva.setText(
                "•CPF:   " + selectHospedeReserva.getValueAt(linha, 4).toString()
                + "\n•RG:   " + selectHospedeReserva.getValueAt(linha, 5).toString()
                + "\n•Telefone:   " + selectHospedeReserva.getValueAt(linha, 6).toString()
                + "\n•Cidade:   " + selectHospedeReserva.getValueAt(linha, 7).toString());
        nomeHospReservaTxt.setText(selectHospedeReserva.getValueAt(linha, 1).toString());
    }//GEN-LAST:event_selectHospedeReservaMouseClicked

    private void selectQuartoReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectQuartoReservaMouseClicked
        int linha = selectQuartoReserva.getSelectedRow();
        descricaoQuartoReserva.setText(selectQuartoReserva.getValueAt(linha, 3).toString());
        numQuartoReservaTxt.setText(selectQuartoReserva.getValueAt(linha, 0).toString());
    }//GEN-LAST:event_selectQuartoReservaMouseClicked

    private void verHospedeReservaBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verHospedeReservaBtnMouseClicked
        selectQuartosReservas.setVisible(false);
        selectHospedesReservas.setVisible(true);
        descricaoQuartoReserva.setText("");
        infoSelectReserva.setText("Informações do Hóspede");
        tableNewRes.setText("Tabela de Hóspedes");
        selectHospJTable("SELECT * FROM hospedes WHERE nome LIKE '%" + nomeHospReservaTxt.getText().replace("'", "") + "%';");
    }//GEN-LAST:event_verHospedeReservaBtnMouseClicked

    private void sairTxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sairTxtMouseEntered
        sairTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-sair-red.png")));
    }//GEN-LAST:event_sairTxtMouseEntered

    private void sairTxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sairTxtMouseExited
        sairTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icon-sair.png")));
    }//GEN-LAST:event_sairTxtMouseExited

    private void cidadeHospedeTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cidadeHospedeTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cadastrarHospedeBtn.requestFocus();
        }
    }//GEN-LAST:event_cidadeHospedeTxtKeyPressed

    private void usuarioFuncionarioTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioFuncionarioTxtKeyTyped
        String caracteres = "0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || usuarioFuncionario.getText().length() >= 100) {
            evt.consume();
        }
    }//GEN-LAST:event_usuarioFuncionarioTxtKeyTyped

    private void senhaFuncionarioTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaFuncionarioTxtKeyTyped
        String caracteres = "0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || senhaFuncionario.getText().length() >= 100) {
            evt.consume();
        }
    }//GEN-LAST:event_senhaFuncionarioTxtKeyTyped

    private void cidadeHospedeTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cidadeHospedeTxtKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || cidadeHospedeTxt.getText().length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_cidadeHospedeTxtKeyTyped

    private void selectFuncReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectFuncReservaMouseClicked
        int linha = selectFuncReserva.getSelectedRow();
        descricaoFuncExcluir.setText(
                "•Cargo:   " + selectFuncReserva.getValueAt(linha, 6).toString()
                + "\n•Data de contratação:   " + selectFuncReserva.getValueAt(linha, 5).toString()
                + "\n•Data de cadastro:   " + selectFuncReserva.getValueAt(linha, 12).toString()
                + "\n•Endereço:   " + selectFuncReserva.getValueAt(linha, 4).toString()
                + "\n•CPF:   " + selectFuncReserva.getValueAt(linha, 8).toString()
                + "\n•RG:   " + selectFuncReserva.getValueAt(linha, 9).toString()
                + "\n•Telefone:   " + selectFuncReserva.getValueAt(linha, 10).toString()
                + "\n•Email:   " + selectFuncReserva.getValueAt(linha, 11).toString());
    }//GEN-LAST:event_selectFuncReservaMouseClicked

    private void nomeFuncExcluirTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeFuncExcluirTxtKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_nomeFuncExcluirTxtKeyTyped

    private void excluirFuncBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirFuncBtnMouseClicked
        int linha = selectFuncReserva.getSelectedRow();
        if (linha != -1) {
            new Conection().executeComand("DELETE FROM funcionarios WHERE idFuncionario = " + (selectFuncReserva.getValueAt(linha, 0).toString()) + ";", "Exclusão do funcionário");
            System.out.println("Excluindo o ID: " + (selectFuncReserva.getValueAt(linha, 0).toString()) + " do banco de dados...");
            selectFuncJTable("SELECT * FROM funcionarios WHERE nome like '%" + nomeFuncExcluirTxt.getText().replace("'", "") + "%';");
            descricaoFuncExcluir.setText("");
        } else {
            MessageStatus m = new MessageStatus(usuario, nome, "Erro, nenhuma linha selecionada.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        }
    }//GEN-LAST:event_excluirFuncBtnMouseClicked

    private void formaPagTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formaPagTxtKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || formaPagTxt.getText().length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_formaPagTxtKeyTyped

    private void nomeHospExcluirTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeHospExcluirTxtKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_nomeHospExcluirTxtKeyTyped

    private void excluirHospBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirHospBtnMouseClicked
        int linha = selectHospedeExcluir.getSelectedRow();
        if (excluirReservasHosp.isSelected()) {
            if (linha != -1) {
                new Conection().executeComand("DELETE FROM reservas WHERE id_hospede = " + (selectHospedeExcluir.getValueAt(linha, 0).toString()) + ";", "");
                new Conection().executeComand("DELETE FROM hospedes WHERE idHospede = " + (selectHospedeExcluir.getValueAt(linha, 0).toString()) + ";", "Exclusão do hóspede");
                System.out.println("Excluindo o ID: " + (selectHospedeExcluir.getValueAt(linha, 0).toString()) + " do banco de dados...");
            } else {
                MessageStatus m = new MessageStatus(usuario, nome, "Erro, nenhuma linha selecionada.", "erro");
                m.setLocationRelativeTo(null);
                m.setVisible(true);
            }
        } else {
            MessageStatus m = new MessageStatus(usuario, nome, "Para excluir o hóspede é preciso excluir todas as reservas dele", "aviso");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        }
    }//GEN-LAST:event_excluirHospBtnMouseClicked

    private void selectHospedeExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectHospedeExcluirMouseClicked
        int linha = selectHospedeExcluir.getSelectedRow();
        descHospExcluir.setText(
                "•CPF:   " + selectHospedeExcluir.getValueAt(linha, 4).toString()
                + "\n•RG:   " + selectHospedeExcluir.getValueAt(linha, 5).toString()
                + "\n•Telefone:   " + selectHospedeExcluir.getValueAt(linha, 6).toString()
                + "\n•Cidade:   " + selectHospedeExcluir.getValueAt(linha, 7).toString());
    }//GEN-LAST:event_selectHospedeExcluirMouseClicked

    private void selectQuartosExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectQuartosExcluirMouseClicked
        int linha = selectQuartosExcluir.getSelectedRow();
        descQuaExcluir.setText(
                "•Tipo:   " + selectQuartosExcluir.getValueAt(linha, 2).toString()
                + "\n•Descrição:   " + selectQuartosExcluir.getValueAt(linha, 3).toString()
                + "\n•Preço:   R$" + selectQuartosExcluir.getValueAt(linha, 4).toString());
    }//GEN-LAST:event_selectQuartosExcluirMouseClicked

    private void excluirQuaBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirQuaBtnMouseClicked
        int linha = selectQuartosExcluir.getSelectedRow();
        if (excluirReservasQua.isSelected()) {
            if (linha != -1) {
                new Conection().executeComand("DELETE FROM reservas WHERE num_quarto = " + (selectQuartosExcluir.getValueAt(linha, 0).toString()) + ";", "");
                new Conection().executeComand("DELETE FROM quartos WHERE numero = " + (selectQuartosExcluir.getValueAt(linha, 0).toString()) + ";", "Exclusão do quarto");
                System.out.println("Excluindo o ID: " + (selectQuartosExcluir.getValueAt(linha, 0).toString()) + " do banco de dados...");
            } else {
                MessageStatus m = new MessageStatus(usuario, nome, "Erro, nenhuma linha selecionada.", "erro");
                m.setLocationRelativeTo(null);
                m.setVisible(true);
            }
        } else {
            MessageStatus m = new MessageStatus(usuario, nome, "Para excluir o quarto é preciso excluir todas as reservas dele.", "aviso");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        }
        selectQuaJTable("SELECT * FROM quartos WHERE numero LIKE '" + numQuaExcluirTxt.getText().replace("'", "") + "%';");
    }//GEN-LAST:event_excluirQuaBtnMouseClicked

    private void infoIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoIconMouseEntered
        infoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/info-white.png")));
    }//GEN-LAST:event_infoIconMouseEntered

    private void infoIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoIconMouseExited
        if (info.isVisible() == true) {
            infoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/info-white.png")));
        } else {
            infoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/info-gray.png")));
        }
    }//GEN-LAST:event_infoIconMouseExited

    private void infoIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoIconMouseClicked
        corBtnGeral();
        corBtnFuncoes();
        esconderPaiFilho();
        esconderBtnFuncoes();
        info.setVisible(true);
        infoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/info-white.png")));
        //----------------------------------------------------------------------
        //animação da tela de infoirmações sobre o software
        Thread funcsAnimation = new Thread(new Runnable() {
            @Override
            public void run() {
                //animação do funcionario 1
                for (int c = 715; c >= 60; c--) {
                    try {
                        Thread.sleep(0, 50);
                    } catch (InterruptedException ex) {
                    }
                    func1.setBounds(c, 120, 145, 224);
                    func2.setBounds(715, 120, 145, 224);
                    func3.setBounds(715, 120, 145, 224);
                    func4.setBounds(715, 120, 145, 224);
                }
                //animação do funcionario 2
                for (int c = 715; c >= 210; c--) {
                    try {
                        Thread.sleep(0, 50);
                    } catch (InterruptedException ex) {
                    }
                    func1.setBounds(60, 120, 145, 224);
                    func2.setBounds(c, 120, 145, 224);
                    func3.setBounds(715, 120, 145, 224);
                    func4.setBounds(715, 120, 145, 224);
                }
                //animação do funcionario 3
                for (int c = 715; c >= 360; c--) {
                    try {
                        Thread.sleep(0, 50);
                    } catch (InterruptedException ex) {
                    }
                    func1.setBounds(60, 120, 145, 224);
                    func2.setBounds(210, 120, 145, 224);
                    func3.setBounds(c, 120, 145, 224);
                    func4.setBounds(715, 120, 145, 224);
                }
                //animação do funcionario 4
                for (int c = 715; c >= 510; c--) {
                    try {
                        Thread.sleep(0, 50);
                    } catch (InterruptedException ex) {
                    }
                    func1.setBounds(60, 120, 145, 224);
                    func2.setBounds(210, 120, 145, 224);
                    func3.setBounds(360, 120, 145, 224);
                    func4.setBounds(c, 120, 145, 224);
                }
            }
        });
        //fim da animação
        //----------------------------------------------------------------------
        //animação das linhas da tela de informações
        Thread iconsAnimation = new Thread(new Runnable() {
            @Override
            public void run() {
                iconF1.setVisible(true);
                iconF2.setVisible(true);
                iconF3.setVisible(true);
                iconF4.setVisible(true);
                //tempo antes de começar a 1ª animação
                try {
                    Thread.sleep(700);
                } catch (Exception e) {
                }
                //animação do 1º icone
                for (int c = 96; c >= 0; c--) {
                    try {
                        Thread.sleep(4);
                    } catch (Exception e) {
                    }
                    iconF1.setBounds(70, 357, 110, c);
                    iconF2.setBounds(230, 372, 100, 81);
                    iconF3.setBounds(389, 374, 96, 70);
                    iconF4.setBounds(549, 370, 84, 84);
                }
                //tempo antes de começar a 2ª animação
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                //animação do 2º icone
                for (int c = 81; c >= 0; c--) {
                    try {
                        Thread.sleep(4);
                    } catch (Exception e) {
                    }
                    iconF1.setBounds(70, 357, 110, 0);
                    iconF2.setBounds(230, 372, 100, c);
                    iconF3.setBounds(389, 374, 96, 70);
                    iconF4.setBounds(549, 370, 84, 84);
                }
                //tempo antes de começar a 3ª animação
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }
                //animação do 3º icone
                for (int c = 70; c >= 0; c--) {
                    try {
                        Thread.sleep(4);
                    } catch (Exception e) {
                    }
                    iconF1.setBounds(70, 357, 110, 0);
                    iconF2.setBounds(230, 372, 100, 0);
                    iconF3.setBounds(389, 374, 96, c);
                    iconF4.setBounds(549, 370, 84, 84);
                }
                //tempo antes de comaçar a 4ª animação
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }
                //animação do 4º icone
                for (int c = 84; c >= 0; c--) {
                    try {
                        Thread.sleep(4);
                    } catch (Exception e) {
                    }
                    iconF1.setBounds(70, 357, 110, 0);
                    iconF2.setBounds(230, 372, 100, 0);
                    iconF3.setBounds(389, 374, 96, 0);
                    iconF4.setBounds(549, 370, 84, c);
                }
                iconF1.setVisible(false);
                iconF2.setVisible(false);
                iconF3.setVisible(false);
                iconF4.setVisible(false);
            }
        });
        //fim da animação dos icones
        //----------------------------------------------------------------------
        //animação das linhas da tela de informações
        Thread linesAnimation = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int c = 137; c >= 0; c--) {
                    line1.setVisible(true);
                    line2.setVisible(true);
                    line3.setVisible(true);
                    line4.setVisible(true);
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                    }
                    line1.setBounds(77, 53, c, 2);
                    line2.setBounds(93, 61, 106, 2);
                    line3.setBounds(104, 70, 84, 2);
                    line4.setBounds(119, 78, 52, 2);
                    if (c <= 106) {
                        line1.setBounds(77, 53, c, 2);
                        line2.setBounds(93, 61, c, 2);
                        line3.setBounds(104, 70, 84, 2);
                        line4.setBounds(119, 78, 52, 2);
                    }
                    if (c <= 84) {
                        line1.setBounds(77, 53, c, 2);
                        line2.setBounds(93, 61, c, 2);
                        line3.setBounds(104, 70, c, 2);
                        line4.setBounds(119, 78, 52, 2);
                    }
                    if (c <= 52) {
                        line1.setBounds(77, 53, c, 2);
                        line2.setBounds(93, 61, c, 2);
                        line3.setBounds(104, 70, c, 2);
                        line4.setBounds(119, 78, c, 2);
                    }
                }
                line1.setVisible(false);
                line2.setVisible(false);
                line3.setVisible(false);
                line4.setVisible(false);
            }
        });
        //fim da animação das linhas
        //----------------------------------------------------------------------
        if (iconsAnimation.getState().equals("RUNNABLE")) {
            iconsAnimation.stop();
            System.out.println("thread executando");
        }
        iconsAnimation.start();
        linesAnimation.start();
        funcsAnimation.start();
    }//GEN-LAST:event_infoIconMouseClicked

    private void numQuaExcluirTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numQuaExcluirTxtKeyReleased
        selectQuaJTable("SELECT * FROM quartos WHERE numero LIKE '" + numQuaExcluirTxt.getText().replace("'", "") + "%';");
        descQuaExcluir.setText("");
    }//GEN-LAST:event_numQuaExcluirTxtKeyReleased

    private void nomeHospExcluirTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeHospExcluirTxtKeyReleased
        descHospExcluir.setText("");
        selectHospJTable("SELECT * FROM hospedes WHERE nome LIKE '%" + nomeHospExcluirTxt.getText().replace("'", "") + "%';");
    }//GEN-LAST:event_nomeHospExcluirTxtKeyReleased

    private void selectReservasExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectReservasExcluirMouseClicked
        int linha = selectReservasExcluir.getSelectedRow();

        //Hospede[] h = new Conection().selectHospedes("select nome from reservas,hospedes where id_hospede = id_hospede and id_hospede = "+selectReservasExcluir.getValueAt(linha, 1).toString()+";");
        descResExcluir.setText(
                "•ID da reserva:   " + selectReservasExcluir.getValueAt(linha, 0).toString()
                + "\n•Nome do Hóspede:   " + selectReservasExcluir.getValueAt(linha, 1).toString()
                + "\n•Nº do quarto:   " + selectReservasExcluir.getValueAt(linha, 2).toString()
                + "\n•Data de Entrada:   " + selectReservasExcluir.getValueAt(linha, 3).toString()
                + "\n•Data de Saída:   " + selectReservasExcluir.getValueAt(linha, 4).toString()
                + "\n•Consumo:   R$" + selectReservasExcluir.getValueAt(linha, 5).toString()
                + "\n•Estado da reserva:   " + selectReservasExcluir.getValueAt(linha, 6).toString()
        );
    }//GEN-LAST:event_selectReservasExcluirMouseClicked

    private void nomeResExcluirTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeResExcluirTxtKeyReleased
        selectResJTable("SELECT * FROM reservas,hospedes where id_hospede = idHospede AND nome LIKE '%" + nomeResExcluirTxt.getText().replace("'", "") + "%';");
        descQuaExcluir.setText("");
    }//GEN-LAST:event_nomeResExcluirTxtKeyReleased

    private void excluirResBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirResBtnMouseClicked
        int linha = selectReservasExcluir.getSelectedRow();
        if (linha != -1) {
            new Conection().executeComand("DELETE FROM reservas WHERE idReserva = " + (selectReservasExcluir.getValueAt(linha, 0).toString()) + ";", "Exclusão da reserva");
            System.out.println("Excluindo o ID: " + (selectReservasExcluir.getValueAt(linha, 0).toString()) + " do banco de dados...");
            descResExcluir.setText("");
        } else {
            MessageStatus m = new MessageStatus(usuario, nome, "Erro, nenhuma linha selecionada.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        }
        selectResJTable("SELECT idReserva,id_hospede,nome,num_quarto,dataEntrada,dataSaida,estado,consumo FROM reservas,hospedes WHERE id_hospede = idHospede AND nome like '%" + nomeResExcluirTxt.getText().replace("'", "") + "%'");
    }//GEN-LAST:event_excluirResBtnMouseClicked

    private void nomeFuncExcluirTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeFuncExcluirTxtKeyReleased
        descricaoFuncExcluir.setText("");
        selectFuncJTable("SELECT * FROM funcionarios WHERE nome like '%" + nomeFuncExcluirTxt.getText().replace("'", "") + "%';");
    }//GEN-LAST:event_nomeFuncExcluirTxtKeyReleased

    private void nomeResExcluirTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeResExcluirTxtKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_nomeResExcluirTxtKeyTyped

    private void diaLemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diaLemKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            mesLem.requestFocus();
        }
    }//GEN-LAST:event_diaLemKeyPressed

    private void mesLemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mesLemKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            anoLem.requestFocus();
        }
    }//GEN-LAST:event_mesLemKeyPressed

    private void anoLemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anoLemKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cadastrarLemBtn.requestFocus();
        }
    }//GEN-LAST:event_anoLemKeyPressed

    private void cadastrarLemBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarLemBtnMouseClicked
        erroLem.setText("");
        if (assuntoLemTxt.getText().equals("") || descLemTxt.getText().equals("")) {
            erroLem.setText("Um ou mais campos foram deixados vazios");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram deixados vazios.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);

        } else {
            Lembrete l = new Lembrete();
            l.assunto = assuntoLemTxt.getText().replace("'", " ");
            l.descricao = descLemTxt.getText().replace("'", " ");
            l.data = anoLem.getSelectedItem() + "-" + mesLem.getSelectedItem() + "-" + diaLem.getSelectedItem();
            l.gravar(logadoComoUsuario.getText());
            resetCampos();
        }
    }//GEN-LAST:event_cadastrarLemBtnMouseClicked

    private void assuntoLemTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_assuntoLemTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            descLemTxt.requestFocus();
        }
    }//GEN-LAST:event_assuntoLemTxtKeyPressed

    private void assuntoLemTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_assuntoLemTxtKeyTyped
        String caracteres = "0123456789.- abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || assuntoLemTxt.getText().length() >= 100) {
            evt.consume();
        }
    }//GEN-LAST:event_assuntoLemTxtKeyTyped

    private void descLemTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descLemTxtKeyTyped
        String caracteres = "0123456789.,- abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || descLemTxt.getText().length() >= 250) {
            evt.consume();
        }
    }//GEN-LAST:event_descLemTxtKeyTyped

    private void numQuaExcluirTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numQuaExcluirTxtKeyTyped
        String caracteres = "0123456789";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_numQuaExcluirTxtKeyTyped

    private void selectPagamentosExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectPagamentosExcluirMouseClicked
        int linha = selectPagamentosExcluir.getSelectedRow();
        descPagExcluir.setText(
                "•Valor:   " + selectPagamentosExcluir.getValueAt(linha, 1).toString()
                + "\n•Forma de Pagamento:   " + selectPagamentosExcluir.getValueAt(linha, 2).toString()
                + "\n•Data:   " + selectPagamentosExcluir.getValueAt(linha, 3).toString()
                + "\n•Descrição:   " + selectPagamentosExcluir.getValueAt(linha, 4).toString());
    }//GEN-LAST:event_selectPagamentosExcluirMouseClicked

    private void excluirPagBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirPagBtnMouseClicked
        int linha = selectPagamentosExcluir.getSelectedRow();
        if (linha != -1) {
            new Conection().executeComand("DELETE FROM pagamento WHERE idPagamento = " + (selectPagamentosExcluir.getValueAt(linha, 0).toString()) + ";", "Exclusão do pagamento");
            System.out.println("Excluindo o ID: " + (selectPagamentosExcluir.getValueAt(linha, 0).toString()) + " do banco de dados...");
            descPagExcluir.setText("");
        } else {
            MessageStatus m = new MessageStatus(usuario, nome, "Erro, nenhuma linha selecionada.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        }
        selectPagJTable("SELECT * FROM pagamento WHERE valor LIKE '" + descPagExcluirTxt.getText().replace("'", "") + "%';");
    }//GEN-LAST:event_excluirPagBtnMouseClicked

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_logoutMouseClicked

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        logout.setToolTipText("Logar novamente usando outro usuário");
    }//GEN-LAST:event_logoutMouseEntered

    private void selectLembretesExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectLembretesExcluirMouseClicked
        int linha = selectLembretesExcluir.getSelectedRow();
        descLemExcluir.setText(
                "•Nome de usuário:   " + selectLembretesExcluir.getValueAt(linha, 1).toString()
                + "\n•Assunto:   " + selectLembretesExcluir.getValueAt(linha, 2).toString()
                + "\n•Data:   " + selectLembretesExcluir.getValueAt(linha, 3).toString()
                + "\n•Descrição:   " + selectLembretesExcluir.getValueAt(linha, 4).toString());
    }//GEN-LAST:event_selectLembretesExcluirMouseClicked

    private void excluirLemBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirLemBtnMouseClicked
        int linha = selectLembretesExcluir.getSelectedRow();
        if (linha != -1) {
            new Conection().executeComand("DELETE FROM lembrete WHERE idLembrete = " + (selectLembretesExcluir.getValueAt(linha, 0).toString()) + ";", "Exclusão do lembrete");
            System.out.println("Excluindo o ID: " + (selectLembretesExcluir.getValueAt(linha, 0).toString()) + " do banco de dados...");
            descPagExcluir.setText("");
        } else {
            MessageStatus m = new MessageStatus(usuario, nome, "Erro, nenhuma linha selecionada.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        }
        selectLemJTable("SELECT * FROM lembrete WHERE descricao LIKE '%" + descLemExcluirTxt.getText().replace("'", "") + "%';");
    }//GEN-LAST:event_excluirLemBtnMouseClicked

    private void descPagExcluirTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descPagExcluirTxtKeyReleased
        selectPagJTable("SELECT * FROM pagamento WHERE descricao LIKE '%" + descPagExcluirTxt.getText().replace("'", "") + "%';");
        descPagExcluir.setText("");
    }//GEN-LAST:event_descPagExcluirTxtKeyReleased

    private void descPagExcluirTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descPagExcluirTxtKeyTyped
        String caracteres = "0123456789.,- abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_descPagExcluirTxtKeyTyped

    private void descLemExcluirTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descLemExcluirTxtKeyReleased
        selectLemJTable("SELECT * FROM lembrete WHERE descricao LIKE '%" + descLemExcluirTxt.getText().replace("'", "") + "%';");
        descLemExcluir.setText("");
    }//GEN-LAST:event_descLemExcluirTxtKeyReleased

    private void descLemExcluirTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descLemExcluirTxtKeyTyped
        String caracteres = "0123456789.,- abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_descLemExcluirTxtKeyTyped

    private void descPagTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descPagTxtKeyTyped
        String caracteres = "0123456789.,- abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || descPagTxt.getText().length() >= 200) {
            evt.consume();
        }
    }//GEN-LAST:event_descPagTxtKeyTyped

    private void selectFuncVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectFuncVerMouseClicked
        int linha = selectFuncVer.getSelectedRow();
        descricaoFuncVer.setText(
                "•Cargo:   " + selectFuncVer.getValueAt(linha, 6).toString()
                + "\n•Data de contratação:   " + selectFuncVer.getValueAt(linha, 5).toString()
                + "\n•Data de cadastro:   " + selectFuncVer.getValueAt(linha, 12).toString()
                + "\n•Endereço:   " + selectFuncVer.getValueAt(linha, 4).toString()
                + "\n•CPF:   " + selectFuncVer.getValueAt(linha, 8).toString()
                + "\n•RG:   " + selectFuncVer.getValueAt(linha, 9).toString()
                + "\n•Telefone:   " + selectFuncVer.getValueAt(linha, 10).toString()
                + "\n•Email:   " + selectFuncVer.getValueAt(linha, 11).toString());
    }//GEN-LAST:event_selectFuncVerMouseClicked

    private void nomeFuncVerTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeFuncVerTxtKeyReleased
        descricaoFuncVer.setText("");
        selectFuncJTable("SELECT * FROM funcionarios WHERE nome like '%" + nomeFuncVerTxt.getText().replace("'", "") + "%';");
    }//GEN-LAST:event_nomeFuncVerTxtKeyReleased

    private void nomeFuncVerTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeFuncVerTxtKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_nomeFuncVerTxtKeyTyped

    private void selectHospedeVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectHospedeVerMouseClicked
        int linha = selectHospedeVer.getSelectedRow();
        descHospVer.setText(
                "•CPF:   " + selectHospedeVer.getValueAt(linha, 4).toString()
                + "\n•RG:   " + selectHospedeVer.getValueAt(linha, 5).toString()
                + "\n•Telefone:   " + selectHospedeVer.getValueAt(linha, 6).toString()
                + "\n•Cidade:   " + selectHospedeVer.getValueAt(linha, 7).toString());
    }//GEN-LAST:event_selectHospedeVerMouseClicked

    private void nomeHospVerTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeHospVerTxtKeyReleased
        descHospVer.setText("");
        selectHospJTable("SELECT * FROM hospedes WHERE nome like '%" + nomeHospVerTxt.getText().replace("'", "") + "%';");
    }//GEN-LAST:event_nomeHospVerTxtKeyReleased

    private void nomeHospVerTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeHospVerTxtKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_nomeHospVerTxtKeyTyped

    private void selectQuartosVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectQuartosVerMouseClicked
        int linha = selectQuartosVer.getSelectedRow();
        descQuaVer.setText(
                "•Tipo:   " + selectQuartosVer.getValueAt(linha, 2).toString()
                + "\n•Descrição:   " + selectQuartosVer.getValueAt(linha, 3).toString()
                + "\n•Preço:   R$" + selectQuartosVer.getValueAt(linha, 4).toString());
    }//GEN-LAST:event_selectQuartosVerMouseClicked

    private void numQuaVerTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numQuaVerTxtKeyReleased
        selectQuaJTable("SELECT * FROM quartos WHERE numero like '" + numQuaVerTxt.getText().replace("'", "") + "%';");
        descQuaVer.setText("");
    }//GEN-LAST:event_numQuaVerTxtKeyReleased

    private void numQuaVerTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numQuaVerTxtKeyTyped
        String caracteres = "0123456789";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_numQuaVerTxtKeyTyped

    private void selectReservasVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectReservasVerMouseClicked
        int linha = selectReservasVer.getSelectedRow();
        descResVer.setText(
                "•ID da reserva:   " + selectReservasVer.getValueAt(linha, 0).toString()
                + "\n•Nome do Hóspede:   " + selectReservasVer.getValueAt(linha, 1).toString()
                + "\n•Nº do quarto:   " + selectReservasVer.getValueAt(linha, 2).toString()
                + "\n•Data de Entrada:   " + selectReservasVer.getValueAt(linha, 3).toString()
                + "\n•Data de Saída:   " + selectReservasVer.getValueAt(linha, 4).toString()
                + "\n•Consumo:   R$" + selectReservasVer.getValueAt(linha, 5).toString()
                + "\n•Estado da reserva:   " + selectReservasVer.getValueAt(linha, 6).toString()
        );
    }//GEN-LAST:event_selectReservasVerMouseClicked

    private void nomeResVerTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeResVerTxtKeyReleased
        selectResJTable("SELECT * FROM reservas,hospedes where id_hospede = idHospede AND nome like '%" + nomeResVerTxt.getText().replace("'", "") + "%';");
        descResVer.setText("");
    }//GEN-LAST:event_nomeResVerTxtKeyReleased

    private void nomeResVerTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeResVerTxtKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_nomeResVerTxtKeyTyped

    private void selectPagamentosVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectPagamentosVerMouseClicked
        int linha = selectPagamentosVer.getSelectedRow();
        descPagVer.setText(
                "•Valor:   " + selectPagamentosVer.getValueAt(linha, 1).toString()
                + "\n•Forma de Pagamento:   " + selectPagamentosVer.getValueAt(linha, 2).toString()
                + "\n•Data:   " + selectPagamentosVer.getValueAt(linha, 3).toString()
                + "\n•Descrição:   " + selectPagamentosVer.getValueAt(linha, 4).toString());
    }//GEN-LAST:event_selectPagamentosVerMouseClicked

    private void descPagVerTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descPagVerTxtKeyReleased
        selectPagJTable("SELECT * FROM pagamento WHERE descricao LIKE '%" + descPagVerTxt.getText().replace("'", "") + "%';");
        descPagVer.setText("");
    }//GEN-LAST:event_descPagVerTxtKeyReleased

    private void descPagVerTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descPagVerTxtKeyTyped
        String caracteres = "0123456789.,- abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_descPagVerTxtKeyTyped

    private void selectLembretesVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectLembretesVerMouseClicked
        int linha = selectLembretesVer.getSelectedRow();
        descLemVer.setText(
                "•Nome de usuário:   " + selectLembretesVer.getValueAt(linha, 1).toString()
                + "\n•Assunto:   " + selectLembretesVer.getValueAt(linha, 2).toString()
                + "\n•Data:   " + selectLembretesVer.getValueAt(linha, 3).toString()
                + "\n•Descrição:   " + selectLembretesVer.getValueAt(linha, 4).toString());
    }//GEN-LAST:event_selectLembretesVerMouseClicked

    private void descLemVerTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descLemVerTxtKeyReleased
        selectLemJTable("SELECT * FROM lembrete WHERE descricao LIKE '%" + descLemVerTxt.getText().replace("'", "") + "%';");
        descLemVer.setText("");
    }//GEN-LAST:event_descLemVerTxtKeyReleased

    private void descLemVerTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descLemVerTxtKeyTyped
        String caracteres = "0123456789.,- abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_descLemVerTxtKeyTyped

    private void nomeFuncTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeFuncTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            sexoFuncTxtMod.requestFocus();
        }
    }//GEN-LAST:event_nomeFuncTxtModKeyPressed

    private void nomeFuncTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeFuncTxtModKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || nomeFuncTxtMod.getText().length() >= 100) {
            evt.consume();
        }
    }//GEN-LAST:event_nomeFuncTxtModKeyTyped

    private void sexoFuncTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sexoFuncTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cargaHorariaFuncTxtMod.requestFocus();
        }
    }//GEN-LAST:event_sexoFuncTxtModKeyPressed

    private void emaiFuncTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emaiFuncTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            telefoneFuncTxtMod.requestFocus();
        }
    }//GEN-LAST:event_emaiFuncTxtModKeyPressed

    private void emaiFuncTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emaiFuncTxtModKeyTyped
        String caracteres = "@.0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || emaiFuncTxtMod.getText().length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_emaiFuncTxtModKeyTyped

    private void telefoneFuncTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefoneFuncTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            enderecoFuncTxtMod.requestFocus();
        }
    }//GEN-LAST:event_telefoneFuncTxtModKeyPressed

    private void telefoneFuncTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefoneFuncTxtModKeyTyped
        String caracteres = "0987654321-";
        if (!caracteres.contains(evt.getKeyChar() + "") || telefoneFuncTxtMod.getText().length() >= 12) {
            evt.consume();
        }
    }//GEN-LAST:event_telefoneFuncTxtModKeyTyped

    private void cargaHorariaFuncTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cargaHorariaFuncTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            emaiFuncTxtMod.requestFocus();
        }
    }//GEN-LAST:event_cargaHorariaFuncTxtModKeyPressed

    private void cargoFuncTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cargoFuncTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            usuarioFuncTxtMod.requestFocus();
        }
    }//GEN-LAST:event_cargoFuncTxtModKeyPressed

    private void cargoFuncTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cargoFuncTxtModKeyTyped
        String caracteres = " 0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || cargoFuncTxtMod.getText().length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_cargoFuncTxtModKeyTyped

    private void enderecoFuncTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enderecoFuncTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cargoFuncTxtMod.requestFocus();
        }
    }//GEN-LAST:event_enderecoFuncTxtModKeyPressed

    private void enderecoFuncTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enderecoFuncTxtModKeyTyped
        String caracteres = " .,º0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || enderecoFuncTxtMod.getText().length() >= 200) {
            evt.consume();
        }
    }//GEN-LAST:event_enderecoFuncTxtModKeyTyped

    private void usuarioFuncTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioFuncTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            senhaFuncTxtMod.requestFocus();
        }
    }//GEN-LAST:event_usuarioFuncTxtModKeyPressed

    private void usuarioFuncTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioFuncTxtModKeyTyped
        String caracteres = "0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || usuarioFuncTxtMod.getText().length() >= 100) {
            evt.consume();
        }
    }//GEN-LAST:event_usuarioFuncTxtModKeyTyped

    private void senhaFuncTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaFuncTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            modificarFuncionarioBtn.requestFocus();
        }
    }//GEN-LAST:event_senhaFuncTxtModKeyPressed

    private void senhaFuncTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaFuncTxtModKeyTyped
        String caracteres = "0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || senhaFuncTxtMod.getText().length() >= 100) {
            evt.consume();
        }
    }//GEN-LAST:event_senhaFuncTxtModKeyTyped

    private void modificarFuncionarioBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarFuncionarioBtnMouseClicked
        erroFuncMod.setText("");
        if (nomeFuncTxtMod.getText().equals("") || emaiFuncTxtMod.getText().equals("") || telefoneFuncTxtMod.getText().equals("") || enderecoFuncTxtMod.getText().equals("") || cargoFuncTxtMod.getText().equals("")) {
            erroFuncMod.setText("Um ou mais campos foram deixados vazios!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram deixados vazios.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (telefoneFuncTxtMod.getText().length() > 12 || telefoneFuncTxtMod.getText().length() < 10) {
            erroFuncMod.setText("Campo Telefone não foi preenchido corretamente!");
            MessageStatus m = new MessageStatus(usuario, nome, "Campo Telefone não foi preenchido corretamente.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if ((usuarioFuncTxtMod.getText().equals("") && UserPassMod.isSelected()) || (senhaFuncTxtMod.getText().equals("") && UserPassMod.isSelected()) || (usuarioFuncTxtMod.getText().equals("") && senhaFuncTxtMod.getText().equals("") && UserPassMod.isSelected())) {
            erroFuncMod.setText("Campos usuários e senha não foram preenchidos corretamente!");
            MessageStatus m = new MessageStatus(usuario, nome, "Campos usuários e senha não foram preenchidos corretamente.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (telefoneFuncTxtMod.getText().length() > 16 || cpfModFuncTxt.getText().length() > 14 || nomeFuncTxtMod.getText().length() > 100 || emaiFuncTxtMod.getText().length() > 50 || enderecoFuncTxtMod.getText().length() > 200 || cargoFuncTxtMod.getText().length() > 50 || usuarioFuncTxtMod.getText().length() > 100 || senhaFuncTxtMod.getText().length() > 100) {
            erroFunc.setText("Um ou mais campos foram preenchidos com muitos caracteres!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram preenchidos com muitos caracteres.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else {
            Funcionario f = new Funcionario();
            f.cpf = cpfModFuncTxt.getText().replace("'", "");
            f.nome = nomeFuncTxtMod.getText().replace("'", "");
            f.sexo = (String) sexoFuncTxtMod.getSelectedItem();
            f.email = emaiFuncTxtMod.getText().replace("'", "");
            f.telefone = telefoneFuncTxtMod.getText().replace("'", "");
            f.endereco = enderecoFuncTxtMod.getText().replace("'", "");
            f.cargaHoraria = (String) cargaHorariaFuncTxtMod.getSelectedItem();
            f.cargo = cargoFuncTxtMod.getText().replace("'", "");
            if (UserPassMod.isSelected()) {
                f.usuario = usuarioFuncTxtMod.getText().replace("'", "");
                f.senha = senhaFuncTxtMod.getText().replace("'", "");
                f.atualizar(usuario, nome, 1);
            } else {
                f.atualizar(usuario, nome, 0);
            }
            resetCampos();
        }
    }//GEN-LAST:event_modificarFuncionarioBtnMouseClicked

    private void cpfModFuncTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpfModFuncTxtMouseClicked
        if (cpfModFuncTxt.getText().equals("Digite o CPF do funcionário que deseja modificar")) {
            cpfModFuncTxt.setText("");
        }
    }//GEN-LAST:event_cpfModFuncTxtMouseClicked

    private void cpfModFuncTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cpfModFuncTxtKeyTyped
        String caracteres = "0987654321.-/xX";
        if (!caracteres.contains(evt.getKeyChar() + "") || cpfModFuncTxt.getText().length() >= 14) {
            evt.consume();
        }
    }//GEN-LAST:event_cpfModFuncTxtKeyTyped

    private void nomeHospedeTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeHospedeTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            sexoHospedeTxtMod.requestFocus();
        }
    }//GEN-LAST:event_nomeHospedeTxtModKeyPressed

    private void nomeHospedeTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeHospedeTxtModKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || nomeHospedeTxtMod.getText().length() >= 100) {
            evt.consume();
        }
    }//GEN-LAST:event_nomeHospedeTxtModKeyTyped

    private void sexoHospedeTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sexoHospedeTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            telefoneHospedeTxtMod.requestFocus();
        }
    }//GEN-LAST:event_sexoHospedeTxtModKeyPressed

    private void cpfHospedeTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cpfHospedeTxtModKeyTyped
        String caracteres = "0987654321.-/xX";
        if (!caracteres.contains(evt.getKeyChar() + "") || cpfHospedeTxtMod.getText().length() >= 14) {
            evt.consume();
        }
    }//GEN-LAST:event_cpfHospedeTxtModKeyTyped

    private void telefoneHospedeTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefoneHospedeTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cidadeHospedeTxtMod.requestFocus();
        }
    }//GEN-LAST:event_telefoneHospedeTxtModKeyPressed

    private void telefoneHospedeTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefoneHospedeTxtModKeyTyped
        String caracteres = "0987654321-";
        if (!caracteres.contains(evt.getKeyChar() + "") || telefoneHospedeTxtMod.getText().length() >= 12) {
            evt.consume();
        }
    }//GEN-LAST:event_telefoneHospedeTxtModKeyTyped

    private void modificarHospedeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarHospedeBtnMouseClicked
        erroHospMod.setText("");
        if (nomeHospedeTxtMod.getText().equals("") || telefoneHospedeTxtMod.getText().equals("") || cidadeHospedeTxtMod.getText().equals("")) {
            erroHospMod.setText("Um ou mais campos foram deixados vazios!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram deixados vazios.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);

        } else if (telefoneHospedeTxtMod.getText().length() > 12 || telefoneHospedeTxtMod.getText().length() < 10) {
            erroHospMod.setText("Campo Telefone não foi preenchido corretamente!");
            MessageStatus m = new MessageStatus(usuario, nome, "Campo Telefone não foi preenchido corretamente.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (nomeHospedeTxtMod.getText().length() > 100 || cpfHospedeTxtMod.getText().length() > 14 || telefoneHospedeTxtMod.getText().length() > 16 || cidadeHospedeTxtMod.getText().length() > 50) {
            erroHospede.setText("Um ou mais campos foram preenchidos com muitos caracteres!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram preenchidos com muitos caracteres.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else {
            Hospede h = new Hospede();
            h.cpf = cpfHospedeTxtMod.getText().replace("'", "");
            h.nome = nomeHospedeTxtMod.getText().replace("'", "");
            h.sexo = (String) sexoHospedeTxtMod.getSelectedItem();
            h.telefone = telefoneHospedeTxtMod.getText().replace("'", "");
            h.cidade = cidadeHospedeTxtMod.getText().replace("'", "");
            h.atualizar();
            resetCampos();
        }
    }//GEN-LAST:event_modificarHospedeBtnMouseClicked

    private void cidadeHospedeTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cidadeHospedeTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            modificarHospedeBtn.requestFocus();
        }
    }//GEN-LAST:event_cidadeHospedeTxtModKeyPressed

    private void cidadeHospedeTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cidadeHospedeTxtModKeyTyped
        String caracteres = " 0987654321abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || cidadeHospedeTxtMod.getText().length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_cidadeHospedeTxtModKeyTyped

    private void cpfHospedeTxtModMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpfHospedeTxtModMouseClicked
        if (cpfHospedeTxtMod.getText().equals("Digite o CPF do hóspede que deseja modificar")) {
            cpfHospedeTxtMod.setText("");
        }
    }//GEN-LAST:event_cpfHospedeTxtModMouseClicked

    private void numQuartoTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numQuartoTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            precoQuartoTxtMod.requestFocus();
        }
    }//GEN-LAST:event_numQuartoTxtModKeyPressed

    private void numQuartoTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numQuartoTxtModKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "") || numQuartoTxtMod.getText().length() >= 4) {
            evt.consume();
        }
        
        if (numQuartoTxtMod.getText().equals("") && 
                (precoQuartoTxtMod.getText().length() > 0 || 
                andarQuartoTxtMod.getText().length() > 0 || 
                tipoQuartoTxtMod.getText().length() > 0 || 
                descricaoQuartoTxtMod.getText().length() > 0) ) {
            
            precoQuartoTxtMod.setText("");
            andarQuartoTxtMod.setText("");
            tipoQuartoTxtMod.setText("");
            descricaoQuartoTxtMod.setText("");
            disponibilidadeQuartoTxtMod.setSelectedItem("S");
        }
    }//GEN-LAST:event_numQuartoTxtModKeyTyped

    private void andarQuartoTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_andarQuartoTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            tipoQuartoTxtMod.requestFocus();
        }
    }//GEN-LAST:event_andarQuartoTxtModKeyPressed

    private void andarQuartoTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_andarQuartoTxtModKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "") || andarQuartoTxtMod.getText().length() >= 3) {
            evt.consume();
        }
    }//GEN-LAST:event_andarQuartoTxtModKeyTyped

    private void tipoQuartoTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipoQuartoTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            descricaoQuartoTxtMod.requestFocus();
        }
    }//GEN-LAST:event_tipoQuartoTxtModKeyPressed

    private void tipoQuartoTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipoQuartoTxtModKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || tipoQuartoTxtMod.getText().length() >= 25) {
            evt.consume();
        }
    }//GEN-LAST:event_tipoQuartoTxtModKeyTyped

    private void descricaoQuartoTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descricaoQuartoTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            disponibilidadeQuartoTxtMod.requestFocus();
        }
    }//GEN-LAST:event_descricaoQuartoTxtModKeyPressed

    private void descricaoQuartoTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descricaoQuartoTxtModKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || descricaoQuartoTxtMod.getText().length() >= 500) {
            evt.consume();
        }
    }//GEN-LAST:event_descricaoQuartoTxtModKeyTyped

    private void modificarQuartoBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarQuartoBtnMouseClicked
        erroQuaMod.setText("");
        if (precoQuartoTxtMod.getText().equals("") || andarQuartoTxtMod.getText().equals("") || tipoQuartoTxtMod.getText().equals("") || descricaoQuartoTxtMod.getText().equals("")) {
            erroQuaMod.setText("Um ou mais campos foram deixados vazios!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram deixados vazios.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (precoQuartoTxtMod.getText().length() > 10 || andarQuartoTxtMod.getText().length() > 3 || tipoQuartoTxtMod.getText().length() > 25 || descricaoQuartoTxtMod.getText().length() > 500) {
            erroQuaMod.setText("Um ou mais campos foram preenchidos com muitos caracteres!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram preenchidos com muitos caracteres.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else {
            Quarto q = new Quarto();
            q.numero = Integer.parseInt(numQuartoTxtMod.getText().replace("'", ""));
            q.andar = Integer.parseInt(andarQuartoTxtMod.getText().replace("'", ""));
            q.preco = Double.parseDouble(precoQuartoTxtMod.getText().replace(",", "."));
            q.tipo = tipoQuartoTxtMod.getText().replace("'", "");
            q.descricao = descricaoQuartoTxtMod.getText().replace("'", "");
            q.disponivel = (String) disponibilidadeQuartoTxtMod.getSelectedItem();
            q.atualizar();
            resetCampos();
        }
    }//GEN-LAST:event_modificarQuartoBtnMouseClicked

    private void mesEntradaReservaModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mesEntradaReservaModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            anoEntradaReservaMod.requestFocus();
        }
    }//GEN-LAST:event_mesEntradaReservaModKeyPressed

    private void anoEntradaReservaModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anoEntradaReservaModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            diaSaidaReservaMod.requestFocus();
        }
    }//GEN-LAST:event_anoEntradaReservaModKeyPressed

    private void diaSaidaReservaModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diaSaidaReservaModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            mesSaidaReservaMod.requestFocus();
        }
    }//GEN-LAST:event_diaSaidaReservaModKeyPressed

    private void mesSaidaReservaModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mesSaidaReservaModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            anoSaidaReservaMod.requestFocus();
        }
    }//GEN-LAST:event_mesSaidaReservaModKeyPressed

    private void anoSaidaReservaModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anoSaidaReservaModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            modificarReservaBtn.requestFocus();
        }
    }//GEN-LAST:event_anoSaidaReservaModKeyPressed

    private void diaEntradaReservaModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diaEntradaReservaModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            mesEntradaReservaMod.requestFocus();
        }
    }//GEN-LAST:event_diaEntradaReservaModKeyPressed

    private void modificarReservaBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarReservaBtnMouseClicked
        erroResMod.setText("");
        
        String entrada = anoEntradaReservaMod.getSelectedItem() + "-" + mesEntradaReservaMod.getSelectedItem() + "-" + diaEntradaReservaMod.getSelectedItem();
        String saida = anoSaidaReservaMod.getSelectedItem() + "-" + mesSaidaReservaMod.getSelectedItem() + "-" + diaSaidaReservaMod.getSelectedItem();
        
        if (estadoResModTxt.getSelectedItem().equals("") || numQuaResModTxt.getText().equals("")) {
            erroResMod.setText("Um ou mais campos foram deixados vazios!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram deixados vazios.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (idHospResModTxt.getText().length() > 11 || consumoResModTxt.getText().length() > 15 || numQuaResModTxt.getText().length() > 4) {
            erroResMod.setText("Um ou mais campos foram preenchidos com muitos caracteres.");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram preenchidos com muitos caracteres.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (comparaDatas(entrada, saida) == 5){
            erroResMod.setText("Data de saida menor do que a de entrada");
            MessageStatus m = new MessageStatus(usuario, nome, "Data de saida menor do que a de entrada.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (comparaDatas(entrada, saida) == 6){
            erroResMod.setText("Data de entrada menor do que a de hoje");
            MessageStatus m = new MessageStatus(usuario, nome, "Data de entrada menor do que a de hoje.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else {
            System.out.println("CERTO");
            Reserva r = new Reserva();
            r.id_hospede = Integer.parseInt(idHospResModTxt.getText().replace("'", ""));
            r.num_quarto = Integer.parseInt(numQuaResModTxt.getText().replace("'", ""));
            r.consumo = Double.parseDouble(consumoResModTxt.getText().replace(",", "."));
            r.dataEntrada = anoEntradaReservaMod.getSelectedItem() + "-" + mesEntradaReservaMod.getSelectedItem() + "-" + diaEntradaReservaMod.getSelectedItem();
            r.dataSaida = anoSaidaReservaMod.getSelectedItem() + "-" + mesSaidaReservaMod.getSelectedItem() + "-" + diaSaidaReservaMod.getSelectedItem();
            r.estado = (String) estadoResModTxt.getSelectedItem();
            r.idReserva = Integer.parseInt(idReservaMod.getText().replace("'", ""));
            r.atualizar();
            resetCampos();
        }
        
            
        
    }//GEN-LAST:event_modificarReservaBtnMouseClicked

    private void idReservaModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idReservaModKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "") || idReservaMod.getText().length() >= 11) {
            evt.consume();
        }
    }//GEN-LAST:event_idReservaModKeyTyped

    private void idHospResModTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idHospResModTxtKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "") || idHospResModTxt.getText().length() >= 11) {
            evt.consume();
        }
    }//GEN-LAST:event_idHospResModTxtKeyTyped

    private void numQuaResModTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numQuaResModTxtKeyTyped
        String caracteres = "0987654321.";
        if (!caracteres.contains(evt.getKeyChar() + "") || numQuaResModTxt.getText().length() >= 4) {
            evt.consume();
        }
    }//GEN-LAST:event_numQuaResModTxtKeyTyped

    private void numQuartoTxtModMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numQuartoTxtModMouseClicked
        if (numQuartoTxtMod.getText().equals("Digite o nº do quarto que deseja modificar")) {
            numQuartoTxtMod.setText("");
        }
    }//GEN-LAST:event_numQuartoTxtModMouseClicked

    private void numQuartoTxtModKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numQuartoTxtModKeyReleased
        modQua("SELECT * FROM quartos WHERE numero = " + numQuartoTxtMod.getText().replace("'", "") + ";");
    }//GEN-LAST:event_numQuartoTxtModKeyReleased

    private void idReservaModKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idReservaModKeyReleased
        modRes("SELECT * FROM reservas WHERE idReserva = '" + idReservaMod.getText().replace("'", "") + "';");
    }//GEN-LAST:event_idReservaModKeyReleased

    private void estadoResModTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_estadoResModTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            diaEntradaReservaMod.requestFocus();
        }
    }//GEN-LAST:event_estadoResModTxtKeyPressed

    private void cpfModFuncTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cpfModFuncTxtKeyReleased
        modFunc("SELECT * FROM funcionarios WHERE cpf ='" + cpfModFuncTxt.getText().replace("'", "") + "';");
    }//GEN-LAST:event_cpfModFuncTxtKeyReleased

    private void cpfHospedeTxtModKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cpfHospedeTxtModKeyReleased
        modHosp("SELECT * FROM hospedes WHERE cpf ='" + cpfHospedeTxtMod.getText().replace("'", "") + "';");
    }//GEN-LAST:event_cpfHospedeTxtModKeyReleased

    private void idReservaModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idReservaModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            idHospResModTxt.requestFocus();
        }
    }//GEN-LAST:event_idReservaModKeyPressed

    private void idHospResModTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idHospResModTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            consumoResModTxt.requestFocus();
        }
    }//GEN-LAST:event_idHospResModTxtKeyPressed

    private void numQuaResModTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numQuaResModTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            estadoResModTxt.requestFocus();
        }
    }//GEN-LAST:event_numQuaResModTxtKeyPressed

    private void diaPagModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diaPagModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            mesPagMod.requestFocus();
        }
    }//GEN-LAST:event_diaPagModKeyPressed

    private void mesPagModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mesPagModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            anoPagMod.requestFocus();
        }
    }//GEN-LAST:event_mesPagModKeyPressed

    private void anoPagModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anoPagModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            descPagModTxt.requestFocus();
        }
    }//GEN-LAST:event_anoPagModKeyPressed

    private void modificarPagBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarPagBtnMouseClicked
        erroPagMod.setText("");
        if (descPagModTxt.getText().equals("") || valorPagModTxt.getText().equals("") || formaPagModTxt.getText().equals("")) {
            erroPagMod.setText("Um ou mais campos foram deixados vazios!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram deixados vazios.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (descPagModTxt.getText().length() > 200 || valorPagModTxt.getText().length() > 15 || formaPagModTxt.getText().length() > 50) {
            erroPagMod.setText("Um ou mais campos foram preenchidos com muitos caracteres!");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram preenchidos com muitos caracteres.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else {
            Pagamento p = new Pagamento();
            p.descricao = descPagModTxt.getText().replace("'", "");
            p.valor = Double.parseDouble(valorPagModTxt.getText().replace(",", "."));
            p.data = (String) anoPagMod.getSelectedItem() + "-" + (String) mesPagMod.getSelectedItem() + "-" + (String) diaPagMod.getSelectedItem();
            p.Fpagamento = formaPagModTxt.getText().replace("'", "");
            p.idPagamento = Integer.parseInt(idPagModTxt.getText());
            p.atualizar();
            resetCampos();
        }
    }//GEN-LAST:event_modificarPagBtnMouseClicked

    private void descPagModTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descPagModTxtKeyTyped
        String caracteres = "0123456789.,- abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || descPagModTxt.getText().length() >= 200) {
            evt.consume();
        }
    }//GEN-LAST:event_descPagModTxtKeyTyped

    private void idPagModTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idPagModTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            formaPagModTxt.requestFocus();
        }
    }//GEN-LAST:event_idPagModTxtKeyPressed

    private void idPagModTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idPagModTxtKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "") || idPagModTxt.getText().length() >= 11) {
            evt.consume();
        }
    }//GEN-LAST:event_idPagModTxtKeyTyped

    private void idPagModTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idPagModTxtKeyReleased
        modPag("SELECT * FROM pagamento WHERE idPagamento = " + idPagModTxt.getText().replace("'", "") + ";");
    }//GEN-LAST:event_idPagModTxtKeyReleased

    private void formaPagModTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formaPagModTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            valorPagModTxt.requestFocus();
        }
    }//GEN-LAST:event_formaPagModTxtKeyPressed

    private void descPagModTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descPagModTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            modificarPagBtn.requestFocus();
        }
    }//GEN-LAST:event_descPagModTxtKeyPressed

    private void diaLem1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diaLem1KeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            mesLem1.requestFocus();
        }
    }//GEN-LAST:event_diaLem1KeyPressed

    private void mesLem1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mesLem1KeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            anoLem1.requestFocus();
        }
    }//GEN-LAST:event_mesLem1KeyPressed

    private void anoLem1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anoLem1KeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            cadastrarLemBtn1.requestFocus();
        }
    }//GEN-LAST:event_anoLem1KeyPressed

    private void cadastrarLemBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarLemBtn1MouseClicked
        erroLem1.setText("");
        if (assuntoLemModTxt.getText().equals("") || descLemModTxt.getText().equals("") || idLemModTxt.getText().equals("")) {
            erroLem1.setText("Um ou mais campos foram deixados vazios");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram deixados vazios.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else if (assuntoLemModTxt.getText().length() > 100 || descLemModTxt.getText().length() > 250 || idLemModTxt.getText().length() > 11) {
            erroLem1.setText("Um ou mais campos foram deixados vazios");
            MessageStatus m = new MessageStatus(usuario, nome, "Um ou mais campos foram deixados vazios.", "erro");
            m.setLocationRelativeTo(null);
            m.setVisible(true);
        } else {
            Lembrete l = new Lembrete();
            l.assunto = assuntoLemModTxt.getText().replace("'", " ");
            l.descricao = descLemModTxt.getText().replace("'", " ");
            l.idLembrete = Integer.parseInt(idLemModTxt.getText());
            l.data = anoLem1.getSelectedItem() + "-" + mesLem1.getSelectedItem() + "-" + diaLem1.getSelectedItem();
            l.atualizar();
            resetCampos();
        }
    }//GEN-LAST:event_cadastrarLemBtn1MouseClicked

    private void idLemModTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idLemModTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            assuntoLemModTxt.requestFocus();
        }
    }//GEN-LAST:event_idLemModTxtKeyPressed

    private void idLemModTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idLemModTxtKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "") || idLemModTxt.getText().length() >= 11) {
            evt.consume();
        }
    }//GEN-LAST:event_idLemModTxtKeyTyped

    private void descLemModTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descLemModTxtKeyTyped
        String caracteres = "0123456789.,- abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || descLemModTxt.getText().length() >= 250) {
            evt.consume();
        }
    }//GEN-LAST:event_descLemModTxtKeyTyped

    private void assuntoLemModTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_assuntoLemModTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            descLemModTxt.requestFocus();
        }
    }//GEN-LAST:event_assuntoLemModTxtKeyPressed

    private void assuntoLemModTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_assuntoLemModTxtKeyTyped
        String caracteres = "0123456789.- abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || assuntoLemModTxt.getText().length() >= 100) {
            evt.consume();
        }
    }//GEN-LAST:event_assuntoLemModTxtKeyTyped

    private void idLemModTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idLemModTxtKeyReleased
        modLem("SELECT * FROM lembrete WHERE idLembrete = " + idLemModTxt.getText().replace("'", "") + ";");
    }//GEN-LAST:event_idLemModTxtKeyReleased

    private void UserPassModMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserPassModMouseClicked
        if (UserPassMod.isSelected()) {
            usuarioFuncTxtMod.setBackground(new Color(204, 204, 204));
            usuarioFuncionarioMod.setForeground(new Color(77, 77, 77));
            usuarioFuncTxtMod.enable();
            senhaFuncionarioMod.setForeground(new Color(77, 77, 77));
            senhaFuncTxtMod.setBackground(new Color(204, 204, 204));
            senhaFuncTxtMod.enable();
        } else {
            usuarioFuncTxtMod.setBackground(new Color(230, 230, 230));
            usuarioFuncionarioMod.setForeground(new Color(230, 230, 230));
            usuarioFuncTxtMod.disable();
            senhaFuncionarioMod.setForeground(new Color(230, 230, 230));
            senhaFuncTxtMod.setBackground(new Color(230, 230, 230));
            senhaFuncTxtMod.disable();
        }
    }//GEN-LAST:event_UserPassModMouseClicked

    private void UserPassNewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserPassNewMouseClicked
        if (UserPassNew.isSelected()) {
            usuarioFuncionarioTxt.setBackground(new Color(204, 204, 204));
            usuarioFuncionario.setForeground(new Color(77, 77, 77));
            usuarioFuncionarioTxt.enable();
            senhaFuncionarioTxt.setBackground(new Color(204, 204, 204));
            senhaFuncionario.setForeground(new Color(77, 77, 77));
            senhaFuncionarioTxt.enable();
        } else if (!UserPassNew.isSelected()) {
            usuarioFuncionarioTxt.setBackground(new Color(230, 230, 230));
            usuarioFuncionario.setForeground(new Color(230, 230, 230));
            usuarioFuncionarioTxt.disable();
            senhaFuncionarioTxt.setBackground(new Color(230, 230, 230));
            senhaFuncionario.setForeground(new Color(230, 230, 230));
            senhaFuncionarioTxt.disable();
        }
    }//GEN-LAST:event_UserPassNewMouseClicked

    private void UserPassNewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserPassNewMouseReleased
        if (UserPassNew.isSelected()) {
            usuarioFuncionarioTxt.setBackground(new Color(204, 204, 204));
            usuarioFuncionario.setForeground(new Color(77, 77, 77));
            usuarioFuncionarioTxt.enable();
            senhaFuncionarioTxt.setBackground(new Color(204, 204, 204));
            senhaFuncionario.setForeground(new Color(77, 77, 77));
            senhaFuncionarioTxt.enable();
        } else if (!UserPassNew.isSelected()) {
            usuarioFuncionarioTxt.setBackground(new Color(230, 230, 230));
            usuarioFuncionario.setForeground(new Color(230, 230, 230));
            usuarioFuncionarioTxt.disable();
            senhaFuncionarioTxt.setBackground(new Color(230, 230, 230));
            senhaFuncionario.setForeground(new Color(230, 230, 230));
            senhaFuncionarioTxt.disable();
        }
    }//GEN-LAST:event_UserPassNewMouseReleased

    private void UserPassModMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserPassModMouseReleased
        if (UserPassMod.isSelected()) {
            usuarioFuncTxtMod.setBackground(new Color(204, 204, 204));
            usuarioFuncionarioMod.setForeground(new Color(77, 77, 77));
            usuarioFuncTxtMod.enable();
            senhaFuncionarioMod.setForeground(new Color(77, 77, 77));
            senhaFuncTxtMod.setBackground(new Color(204, 204, 204));
            senhaFuncTxtMod.enable();
        } else {
            usuarioFuncTxtMod.setBackground(new Color(230, 230, 230));
            usuarioFuncionarioMod.setForeground(new Color(230, 230, 230));
            usuarioFuncTxtMod.disable();
            senhaFuncionarioMod.setForeground(new Color(230, 230, 230));
            senhaFuncTxtMod.setBackground(new Color(230, 230, 230));
            senhaFuncTxtMod.disable();
        }
    }//GEN-LAST:event_UserPassModMouseReleased

    private void formaPagModTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formaPagModTxtKeyTyped
        String caracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáàãâéèêíìóòôõúùûÁÀÃÂÉÈÊÍÌÓÒÔÕÚÙÛçÇ";
        if (!caracteres.contains(evt.getKeyChar() + "") || formaPagModTxt.getText().length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_formaPagModTxtKeyTyped

    private void inicioBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inicioBtnMousePressed
        corBtnGeral();
        esconderPaiFilho();
        esconderBtnFuncoes();
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        panelInicio.setVisible(true);
        inicioBtn.setBackground(new Color(0, 19, 39));
    }//GEN-LAST:event_inicioBtnMousePressed

    private void funcionarioBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_funcionarioBtnMousePressed
        corBtnGeral();//cor dos botoes gerais desativados
        esconderPaiFilho();//esconde os panel's principais
        esconderBtnFuncoes();//esconde os botoes de funções
        corBtnFuncoes();//cor dos botoes de funcoes fiam desativadas(cinza)
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        funcionarioBtn.setBackground(new Color(0, 19, 39));//cor do proprio botao geral de funcionarios
        cadastrarFuncFuncaoBtn.setVisible(true);//botao de cadastrar novo funcionario visível
        verFuncFuncaoBtn.setVisible(true);//botao de ver funcionarios visível
        excluirFuncFuncaoBtn.setVisible(true);//botão de excluir
        modFuncFuncaoBtn.setVisible(true);//botão de modificar
    }//GEN-LAST:event_funcionarioBtnMousePressed

    private void hospedeBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hospedeBtnMousePressed
        corBtnGeral();//cores dos botões gerais ficam desativados(azul-escuro)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        esconderBtnFuncoes();//esconde os botões de funções
        corBtnFuncoes();//cor dos botoes de funcoes fiam desativadas(cinza)
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        hospedeBtn.setBackground(new Color(0, 19, 39));//cor do próprio botão
        cadastrarHospFuncaoBtn.setVisible(true);//botão de cadastrar hospedes
        excluirHospFuncaoBtn.setVisible(true);//botão de excluir hospedes
        verHospFuncaoBtn.setVisible(true);////botão de ver hospedes
        modHospFuncaoBtn.setVisible(true);////botão de ver hospedes
    }//GEN-LAST:event_hospedeBtnMousePressed

    private void quartoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quartoBtnMousePressed
        corBtnGeral();
        esconderPaiFilho();
        esconderBtnFuncoes();
        corBtnFuncoes();//cor dos botoes de funcoes fiam desativadas(cinza)
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        quartoBtn.setBackground(new Color(0, 19, 39));
        cadastrarQuaFuncaoBtn.setVisible(true);
        excluirQuaFuncaoBtn.setVisible(true);
        verQuaFuncaoBtn.setVisible(true);
        modQuaFuncaoBtn.setVisible(true);
    }//GEN-LAST:event_quartoBtnMousePressed

    private void reservaBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reservaBtnMousePressed
        corBtnGeral();
        esconderPaiFilho();
        esconderBtnFuncoes();
        corBtnFuncoes();//cor dos botoes de funcoes fiam desativadas(cinza)
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        reservaBtn.setBackground(new Color(0, 19, 39));
        cadastrarResFuncaoBtn.setVisible(true);
        excluirResFuncaoBtn.setVisible(true);
        verResFuncaoBtn.setVisible(true);
        modResFuncaoBtn.setVisible(true);
    }//GEN-LAST:event_reservaBtnMousePressed

    private void pagamentoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pagamentoBtnMousePressed
        corBtnGeral();
        esconderPaiFilho();
        esconderBtnFuncoes();
        corBtnFuncoes();//cor dos botoes de funcoes fiam desativadas(cinza)
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        pagamentoBtn.setBackground(new Color(0, 19, 39));
        cadastrarPagFuncaoBtn.setVisible(true);
        excluirPagFuncaoBtn.setVisible(true);
        verPagFuncaoBtn.setVisible(true);
        modPagFuncaoBtn.setVisible(true);
    }//GEN-LAST:event_pagamentoBtnMousePressed

    private void lembreteBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lembreteBtnMousePressed
        corBtnGeral();
        esconderPaiFilho();
        esconderBtnFuncoes();
        corBtnFuncoes();//cor dos botoes de funcoes fiam desativadas(cinza)
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        lembreteBtn.setBackground(new Color(0, 19, 39));
        cadastrarLemFuncaoBtn.setVisible(true);
        excluirLemFuncaoBtn.setVisible(true);
        verLemFuncaoBtn.setVisible(true);
        modLemFuncaoBtn.setVisible(true);
    }//GEN-LAST:event_lembreteBtnMousePressed

    private void cadastrarLemFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarLemFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        cadastrarLemFuncaoBtn.setVisible(true);//próprio botão visível
        cadastrarLemFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        cadastrarLemFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelLembrete.setVisible(true);//panel pai
        cadastrarLembrete.setVisible(true);//panel filho
    }//GEN-LAST:event_cadastrarLemFuncaoBtnMousePressed

    private void cadastrarResFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarResFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        cadastrarResFuncaoBtn.setVisible(true);//próprio botão visível
        cadastrarResFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        cadastrarResFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelReserva.setVisible(true);//panel pai
        cadastrarReserva.setVisible(true);//panel filho

    }//GEN-LAST:event_cadastrarResFuncaoBtnMousePressed

    private void cadastrarHospFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarHospFuncaoBtnMousePressed
        corBtnFuncoes();//cor dos botoes de funções desativadas(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        panelHospede.setVisible(true);//panel pai
        cadastrarHospede.setVisible(true);//panel filho
        cadastrarHospFuncaoBtn.setVisible(true);//próprio botão visível
        cadastrarHospFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão
        cadastrarHospFuncao.setForeground(new Color(0, 0, 0));//texto do próprio botao
    }//GEN-LAST:event_cadastrarHospFuncaoBtnMousePressed

    private void cadastrarPagFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarPagFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        cadastrarPagFuncaoBtn.setVisible(true);//próprio botão visível
        cadastrarPagFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        cadastrarPagFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelPagamento.setVisible(true);//panel pai
        cadastrarPagamento.setVisible(true);//panel filho
    }//GEN-LAST:event_cadastrarPagFuncaoBtnMousePressed

    private void cadastrarQuaFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarQuaFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        cadastrarQuaFuncaoBtn.setVisible(true);//próprio botão visível
        cadastrarQuaFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        cadastrarQuaFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelQuarto.setVisible(true);//panel pai
        cadastrarQuarto.setVisible(true);//panel filho
    }//GEN-LAST:event_cadastrarQuaFuncaoBtnMousePressed

    private void cadastrarFuncFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarFuncFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        cadastrarFuncFuncaoBtn.setVisible(true);//próprio botão visível
        cadastrarFuncFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        cadastrarFunciFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelFuncionario.setVisible(true);//panel pai
        cadastrarFuncionario.setVisible(true);//panel filho
        //deixa desativado por padrão os campos de usuario e senha do cadastro de funcionario
        usuarioFuncionarioTxt.setBackground(new Color(230, 230, 230));
        usuarioFuncionario.setForeground(new Color(230, 230, 230));
        usuarioFuncionarioTxt.disable();
        senhaFuncionarioTxt.setBackground(new Color(230, 230, 230));
        senhaFuncionario.setForeground(new Color(230, 230, 230));
        senhaFuncionarioTxt.disable();
    }//GEN-LAST:event_cadastrarFuncFuncaoBtnMousePressed

    private void excluirLemFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirLemFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        excluirLemFuncaoBtn.setVisible(true);//próprio botão visível
        excluirLemFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        excluirLemFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelLembrete.setVisible(true);//panel pai
        excluirLembrete.setVisible(true);//panel filho
        selectLemJTable("SELECT * FROM lembrete WHERE descricao LIKE '%';");
    }//GEN-LAST:event_excluirLemFuncaoBtnMousePressed

    private void excluirResFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirResFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        excluirResFuncaoBtn.setVisible(true);//próprio botão visível
        excluirResFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        excluirResFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelReserva.setVisible(true);//panel pai
        excluirReserva.setVisible(true);//panel filho
        selectResJTable("SELECT * FROM reservas,hospedes where id_hospede = idHospede AND nome LIKE '%';");
    }//GEN-LAST:event_excluirResFuncaoBtnMousePressed

    private void excluirQuaFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirQuaFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        excluirQuaFuncaoBtn.setVisible(true);//próprio botão visível
        excluirQuaFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        excluirQuaFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelQuarto.setVisible(true);//panel pai
        excluirQuarto.setVisible(true);//panel filho
        selectQuaJTable("SELECT * FROM quartos WHERE numero LIKE '%';");
    }//GEN-LAST:event_excluirQuaFuncaoBtnMousePressed

    private void excluirHospFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirHospFuncaoBtnMousePressed
        corBtnFuncoes();//cor dos botoes de funções desativadas(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        panelHospede.setVisible(true);//panel pai
        excluirHospede.setVisible(true);//panel filho
        excluirHospFuncaoBtn.setVisible(true);//próprio botão visível
        excluirHospFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão
        excluirHospFuncao.setForeground(new Color(0, 0, 0));//texto do próprio botao
        selectHospJTable("SELECT * FROM hospedes WHERE nome LIKE '%';");
    }//GEN-LAST:event_excluirHospFuncaoBtnMousePressed

    private void excluirFuncFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirFuncFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        excluirFuncFuncaoBtn.setVisible(true);//próprio botão visível
        excluirFuncFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        excluirFuncFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelFuncionario.setVisible(true);//panel pai
        excluirFuncionario.setVisible(true);//panel filho
        selectFuncJTable("SELECT * FROM funcionarios WHERE nome LIKE '%';");
    }//GEN-LAST:event_excluirFuncFuncaoBtnMousePressed

    private void excluirPagFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_excluirPagFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        excluirPagFuncaoBtn.setVisible(true);//próprio botão visível
        excluirPagFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        excluirPagFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelPagamento.setVisible(true);//panel pai
        excluirPagamento.setVisible(true);//panel filho
        selectPagJTable("SELECT * FROM pagamento WHERE descricao LIKE '%';");
    }//GEN-LAST:event_excluirPagFuncaoBtnMousePressed

    private void verResFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verResFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        verResFuncaoBtn.setVisible(true);//próprio botão visível
        verResFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        verResFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelReserva.setVisible(true);//panel pai
        verReserva.setVisible(true);//panel filho
        selectResJTable("SELECT * FROM reservas,hospedes where id_hospede = idHospede AND nome LIKE '%';");
    }//GEN-LAST:event_verResFuncaoBtnMousePressed

    private void verHospFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verHospFuncaoBtnMousePressed
        corBtnFuncoes();//cor dos botoes de funções desativadas(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        verHospFuncaoBtn.setVisible(true);//próprio botão visível
        verHospFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão
        verHospFuncao.setForeground(new Color(0, 0, 0));//texto do próprio botao
        panelHospede.setVisible(true);//panel pai
        verHospede.setVisible(true);//panel filho
        selectHospJTable("SELECT * FROM hospedes WHERE nome LIKE '%';");
    }//GEN-LAST:event_verHospFuncaoBtnMousePressed

    private void verFuncFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verFuncFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        verFuncFuncaoBtn.setVisible(true);//próprio botão visível
        verFuncFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        verFuncFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelFuncionario.setVisible(true);//panel pai
        verFuncionario.setVisible(true);//panel filho
        selectFuncJTable("SELECT * FROM funcionarios WHERE nome LIKE '%';");
    }//GEN-LAST:event_verFuncFuncaoBtnMousePressed

    private void verQuaFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verQuaFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        verQuaFuncaoBtn.setVisible(true);//próprio botão visível
        verQuaFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        verQuaFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelQuarto.setVisible(true);//panel pai
        verQuarto.setVisible(true);//panel filho
        selectQuaJTable("SELECT * FROM quartos WHERE numero LIKE '%';");
    }//GEN-LAST:event_verQuaFuncaoBtnMousePressed

    private void verLemFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verLemFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        verLemFuncaoBtn.setVisible(true);//próprio botão visível
        verLemFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        verLemFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelLembrete.setVisible(true);//panel pai
        verLembrete.setVisible(true);//panel filho
        selectLemJTable("SELECT * FROM lembrete WHERE descricao LIKE '%';");
    }//GEN-LAST:event_verLemFuncaoBtnMousePressed

    private void verPagFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verPagFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        verPagFuncaoBtn.setVisible(true);//próprio botão visível
        verPagFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        verPagFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelPagamento.setVisible(true);//panel pai
        verPagamento.setVisible(true);//panel filho
        selectPagJTable("SELECT * FROM pagamento WHERE descricao LIKE '%';");
    }//GEN-LAST:event_verPagFuncaoBtnMousePressed

    private void modResFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modResFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        modResFuncaoBtn.setVisible(true);//próprio botão visível
        modResFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        modResFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelReserva.setVisible(true);//panel pai
        modificarReserva.setVisible(true);//panel filho
    }//GEN-LAST:event_modResFuncaoBtnMousePressed

    private void modHospFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modHospFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        modHospFuncaoBtn.setVisible(true);//próprio botão visível
        modHospFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        modHospFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelHospede.setVisible(true);//panel pai
        modificarHospede.setVisible(true);//panel filho
    }//GEN-LAST:event_modHospFuncaoBtnMousePressed

    private void modFuncFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modFuncFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        modFuncFuncaoBtn.setVisible(true);//próprio botão visível
        modFuncFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        modFuncFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelFuncionario.setVisible(true);//panel pai
        modificarFuncionario.setVisible(true);//panel filho
        usuarioFuncTxtMod.setBackground(new Color(230, 230, 230));
        usuarioFuncionarioMod.setForeground(new Color(230, 230, 230));
        usuarioFuncTxtMod.disable();
        senhaFuncionarioMod.setForeground(new Color(230, 230, 230));
        senhaFuncTxtMod.setBackground(new Color(230, 230, 230));
        senhaFuncTxtMod.disable();
    }//GEN-LAST:event_modFuncFuncaoBtnMousePressed

    private void modQuaFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modQuaFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        modQuaFuncaoBtn.setVisible(true);//próprio botão visível
        modQuaFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        modQuaFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelQuarto.setVisible(true);//panel pai
        modificarQuarto.setVisible(true);//panel filho
    }//GEN-LAST:event_modQuaFuncaoBtnMousePressed

    private void modLemFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modLemFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        modLemFuncaoBtn.setVisible(true);//próprio botão visível
        modLemFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        modLemFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelLembrete.setVisible(true);//panel pai
        modificarLembrete.setVisible(true);//panel filho
    }//GEN-LAST:event_modLemFuncaoBtnMousePressed

    private void modPagFuncaoBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modPagFuncaoBtnMousePressed
        corBtnFuncoes();//esconde todos os panel's e chama a função corBtnFuncoes(); que defini a cor de todos os botões para desativado(cinza)
        esconderPaiFilho();//esconde todos os panel's pais e filhos
        resetCampos();//reseta todos os campos existentes dentro do panel principal
        modPagFuncaoBtn.setVisible(true);//próprio botão visível
        modPagFuncaoBtn.setBackground(new Color(255, 255, 255));//cor do próprio botão da função de cadastrar novo funcionario
        modPagFuncao.setForeground(new Color(0, 0, 0));//cor do texto do botao da função de cadastrar novo funcionario
        panelPagamento.setVisible(true);//panel pai
        modificarPagamento.setVisible(true);//panel filho
    }//GEN-LAST:event_modPagFuncaoBtnMousePressed

    private void precoQuartoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precoQuartoTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            tipoQuartoTxt.requestFocus();
        }
    }//GEN-LAST:event_precoQuartoTxtKeyPressed

    private void precoQuartoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precoQuartoTxtKeyTyped
        String caracteres = "0987654321,";
        String virgula = ",";
        if (!caracteres.contains(evt.getKeyChar() + "") || precoQuartoTxt.getText().length() >= 10 || (precoQuartoTxt.getText().contains(",")) && virgula.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_precoQuartoTxtKeyTyped

    private void precoQuartoTxtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precoQuartoTxtModKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            andarQuartoTxtMod.requestFocus();
        }
    }//GEN-LAST:event_precoQuartoTxtModKeyPressed

    private void precoQuartoTxtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precoQuartoTxtModKeyTyped
        String caracteres = "0987654321,";
        String virgula = ",";
        if (!caracteres.contains(evt.getKeyChar() + "") || precoQuartoTxtMod.getText().length() >= 10 || (precoQuartoTxtMod.getText().contains(",") && virgula.contains(evt.getKeyChar() + ""))) {
            evt.consume();
        }
    }//GEN-LAST:event_precoQuartoTxtModKeyTyped

    private void valorPagTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorPagTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            diaPag.requestFocus();
        }
    }//GEN-LAST:event_valorPagTxtKeyPressed

    private void valorPagTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorPagTxtKeyTyped
        String caracteres = "0987654321,";
        String virgula = ",";
        if (!caracteres.contains(evt.getKeyChar() + "") || valorPagTxt.getText().length() >= 15 || (valorPagTxt.getText().contains(",") && virgula.contains(evt.getKeyChar() + ""))) {
            evt.consume();
        }
    }//GEN-LAST:event_valorPagTxtKeyTyped

    private void valorPagModTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorPagModTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            diaPagMod.requestFocus();
        }
    }//GEN-LAST:event_valorPagModTxtKeyPressed

    private void valorPagModTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorPagModTxtKeyTyped
        String caracteres = "0987654321,";
        String virgula = ",";
        if (!caracteres.contains(evt.getKeyChar() + "") || valorPagModTxt.getText().length() >= 15 || (valorPagModTxt.getText().contains(",") && virgula.contains(evt.getKeyChar() + ""))) {
            evt.consume();
        }
    }//GEN-LAST:event_valorPagModTxtKeyTyped

    private void consumoResModTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consumoResModTxtKeyTyped
        String caracteres = "0987654321,";
        String virgula = ",";
        if (!caracteres.contains(evt.getKeyChar() + "") || consumoResModTxt.getText().length() >= 15 || (consumoResModTxt.getText().contains(",") && virgula.contains(evt.getKeyChar() + ""))) {
            evt.consume();
        }
    }//GEN-LAST:event_consumoResModTxtKeyTyped

    private void consumoResModTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consumoResModTxtKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            numQuaResModTxt.requestFocus();
        }
    }//GEN-LAST:event_consumoResModTxtKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicial("", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FuncoesAbas;
    private javax.swing.JLabel InsiraIdRes;
    private javax.swing.JPanel PanelEsquerdo;
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JPanel PanelTopo;
    private javax.swing.JCheckBox UserPassMod;
    private javax.swing.JCheckBox UserPassNew;
    private javax.swing.JLabel andarQuarto;
    private javax.swing.JLabel andarQuartoMod;
    private javax.swing.JTextField andarQuartoTxt;
    private javax.swing.JTextField andarQuartoTxtMod;
    private javax.swing.JComboBox<String> anoConNovoFunc;
    private javax.swing.JComboBox<String> anoEntradaReserva;
    private javax.swing.JComboBox<String> anoEntradaReservaMod;
    private javax.swing.JComboBox<String> anoLem;
    private javax.swing.JComboBox<String> anoLem1;
    private javax.swing.JComboBox<String> anoNascFuncionarioTxt;
    private javax.swing.JComboBox<String> anoNascHospedeTxt;
    private javax.swing.JComboBox<String> anoPag;
    private javax.swing.JComboBox<String> anoPagMod;
    private javax.swing.JComboBox<String> anoSaidaReserva;
    private javax.swing.JComboBox<String> anoSaidaReservaMod;
    private javax.swing.JLabel assuntoLem;
    private javax.swing.JLabel assuntoLemMod;
    private javax.swing.JTextField assuntoLemModTxt;
    private javax.swing.JTextField assuntoLemTxt;
    private javax.swing.JPanel cadastrarFuncFuncaoBtn;
    private javax.swing.JLabel cadastrarFunciFuncao;
    private javax.swing.JPanel cadastrarFuncionario;
    private javax.swing.JButton cadastrarFuncionarioBtn;
    private javax.swing.JLabel cadastrarHospFuncao;
    private javax.swing.JPanel cadastrarHospFuncaoBtn;
    private javax.swing.JPanel cadastrarHospede;
    private javax.swing.JButton cadastrarHospedeBtn;
    private javax.swing.JButton cadastrarLemBtn;
    private javax.swing.JButton cadastrarLemBtn1;
    private javax.swing.JLabel cadastrarLemFuncao;
    private javax.swing.JPanel cadastrarLemFuncaoBtn;
    private javax.swing.JPanel cadastrarLembrete;
    private javax.swing.JLabel cadastrarNovaReserva;
    private javax.swing.JLabel cadastrarNovoFuncionario;
    private javax.swing.JLabel cadastrarNovoHospede;
    private javax.swing.JLabel cadastrarNovoLem;
    private javax.swing.JLabel cadastrarNovoPag;
    private javax.swing.JLabel cadastrarNovoQuarto;
    private javax.swing.JButton cadastrarPagBtn;
    private javax.swing.JLabel cadastrarPagFuncao;
    private javax.swing.JPanel cadastrarPagFuncaoBtn;
    private javax.swing.JPanel cadastrarPagamento;
    private javax.swing.JLabel cadastrarQuaFuncao;
    private javax.swing.JPanel cadastrarQuaFuncaoBtn;
    private javax.swing.JPanel cadastrarQuarto;
    private javax.swing.JButton cadastrarQuartoBtn;
    private javax.swing.JLabel cadastrarResFuncao;
    private javax.swing.JPanel cadastrarResFuncaoBtn;
    private javax.swing.JPanel cadastrarReserva;
    private javax.swing.JButton cadastrarReservaBtn;
    private javax.swing.JComboBox<String> cargaHorariaFuncTxtMod;
    private javax.swing.JLabel cargaHorariaFuncionario;
    private javax.swing.JLabel cargaHorariaFuncionarioMod;
    private javax.swing.JComboBox<String> cargaHorariaFuncionarioTxt;
    private javax.swing.JTextField cargoFuncTxtMod;
    private javax.swing.JLabel cargoFuncionario;
    private javax.swing.JLabel cargoFuncionarioMod;
    private javax.swing.JTextField cargoFuncionarioTxt;
    private javax.swing.JLabel cidadeHospede;
    private javax.swing.JLabel cidadeHospedeMod;
    private javax.swing.JTextField cidadeHospedeTxt;
    private javax.swing.JTextField cidadeHospedeTxtMod;
    private javax.swing.JLabel consumoResMod;
    private javax.swing.JFormattedTextField consumoResModTxt;
    private javax.swing.JLabel cpfFuncionario;
    private javax.swing.JTextField cpfFuncionarioTxt;
    private javax.swing.JLabel cpfHospede;
    private javax.swing.JTextField cpfHospedeTxt;
    private javax.swing.JTextField cpfHospedeTxtMod;
    private javax.swing.JTextField cpfModFuncTxt;
    private javax.swing.JLabel dataContratacaoFuncionario;
    private javax.swing.JLabel dataEntradaReserva;
    private javax.swing.JLabel dataEntradaReservaMod;
    private javax.swing.JLabel dataLem;
    private javax.swing.JLabel dataLemMod;
    private javax.swing.JLabel dataPag;
    private javax.swing.JLabel dataPagMod;
    private javax.swing.JLabel dataSaidaReserva;
    private javax.swing.JLabel dataSaidaReservaMod;
    private javax.swing.JLabel descFuncExcluir;
    private javax.swing.JLabel descFuncVer;
    private javax.swing.JTextPane descHospExcluir;
    private javax.swing.JTextPane descHospVer;
    private javax.swing.JLabel descLem;
    private javax.swing.JTextPane descLemExcluir;
    private javax.swing.JLabel descLemExcluirLabel;
    private javax.swing.JTextField descLemExcluirTxt;
    private javax.swing.JLabel descLemMod;
    private javax.swing.JTextArea descLemModTxt;
    private javax.swing.JTextArea descLemTxt;
    private javax.swing.JTextPane descLemVer;
    private javax.swing.JLabel descLemVerLabel;
    private javax.swing.JTextField descLemVerTxt;
    private javax.swing.JLabel descPag;
    private javax.swing.JTextPane descPagExcluir;
    private javax.swing.JTextField descPagExcluirTxt;
    private javax.swing.JLabel descPagMod;
    private javax.swing.JTextArea descPagModTxt;
    private javax.swing.JTextArea descPagTxt;
    private javax.swing.JTextPane descPagVer;
    private javax.swing.JTextField descPagVerTxt;
    private javax.swing.JTextPane descQuaExcluir;
    private javax.swing.JTextPane descQuaVer;
    private javax.swing.JTextPane descResExcluir;
    private javax.swing.JTextPane descResVer;
    private javax.swing.JTextPane descricaoFuncExcluir;
    private javax.swing.JTextPane descricaoFuncVer;
    private javax.swing.JLabel descricaoQuarto;
    private javax.swing.JLabel descricaoQuartoMod;
    private javax.swing.JTextPane descricaoQuartoReserva;
    private javax.swing.JTextField descricaoQuartoTxt;
    private javax.swing.JTextField descricaoQuartoTxtMod;
    private javax.swing.JComboBox<String> diaConNovoFunc;
    private javax.swing.JComboBox<String> diaEntradaReserva;
    private javax.swing.JComboBox<String> diaEntradaReservaMod;
    private javax.swing.JComboBox<String> diaLem;
    private javax.swing.JComboBox<String> diaLem1;
    private javax.swing.JComboBox<String> diaNascFuncionarioTxt;
    private javax.swing.JComboBox<String> diaNascHospedeTxt;
    private javax.swing.JComboBox<String> diaPag;
    private javax.swing.JComboBox<String> diaPagMod;
    private javax.swing.JComboBox<String> diaSaidaReserva;
    private javax.swing.JComboBox<String> diaSaidaReservaMod;
    private javax.swing.JLabel disponibilidadeQuartoMod;
    private javax.swing.JComboBox<String> disponibilidadeQuartoTxtMod;
    private javax.swing.JTextField emaiFuncTxtMod;
    private javax.swing.JTextField emaiFuncionarioTxt;
    private javax.swing.JLabel emailFuncionario;
    private javax.swing.JLabel emailFuncionarioMod;
    private javax.swing.JTextField enderecoFuncTxtMod;
    private javax.swing.JLabel enderecoFuncionario;
    private javax.swing.JLabel enderecoFuncionarioMod;
    private javax.swing.JTextField enderecoFuncionarioTxt;
    private javax.swing.JLabel equipe;
    private javax.swing.JLabel erroFunc;
    private javax.swing.JLabel erroFuncMod;
    private javax.swing.JLabel erroHospMod;
    private javax.swing.JLabel erroHospede;
    private javax.swing.JLabel erroLem;
    private javax.swing.JLabel erroLem1;
    private javax.swing.JLabel erroPag;
    private javax.swing.JLabel erroPagMod;
    private javax.swing.JLabel erroQuaMod;
    private javax.swing.JLabel erroQuarto;
    private javax.swing.JLabel erroResMod;
    private javax.swing.JLabel erroReserva;
    private javax.swing.JLabel estadoResMod;
    private javax.swing.JComboBox<String> estadoResModTxt;
    private javax.swing.JLabel excluirFunc;
    private javax.swing.JButton excluirFuncBtn;
    private javax.swing.JLabel excluirFuncFuncao;
    private javax.swing.JPanel excluirFuncFuncaoBtn;
    private javax.swing.JPanel excluirFuncionario;
    private javax.swing.JLabel excluirHosp;
    private javax.swing.JButton excluirHospBtn;
    private javax.swing.JLabel excluirHospFuncao;
    private javax.swing.JPanel excluirHospFuncaoBtn;
    private javax.swing.JPanel excluirHospede;
    private javax.swing.JLabel excluirLem;
    private javax.swing.JButton excluirLemBtn;
    private javax.swing.JLabel excluirLemFuncao;
    private javax.swing.JPanel excluirLemFuncaoBtn;
    private javax.swing.JPanel excluirLembrete;
    private javax.swing.JLabel excluirPag;
    private javax.swing.JButton excluirPagBtn;
    private javax.swing.JLabel excluirPagFuncao;
    private javax.swing.JPanel excluirPagFuncaoBtn;
    private javax.swing.JPanel excluirPagamento;
    private javax.swing.JLabel excluirQua;
    private javax.swing.JButton excluirQuaBtn;
    private javax.swing.JLabel excluirQuaFuncao;
    private javax.swing.JPanel excluirQuaFuncaoBtn;
    private javax.swing.JPanel excluirQuarto;
    private javax.swing.JLabel excluirRes;
    private javax.swing.JButton excluirResBtn;
    private javax.swing.JLabel excluirResFuncao;
    private javax.swing.JPanel excluirResFuncaoBtn;
    private javax.swing.JPanel excluirReserva;
    private javax.swing.JCheckBox excluirReservasHosp;
    private javax.swing.JCheckBox excluirReservasQua;
    private javax.swing.JLabel formaPag;
    private javax.swing.JLabel formaPagMod;
    private javax.swing.JTextField formaPagModTxt;
    private javax.swing.JTextField formaPagTxt;
    private javax.swing.JPanel func1;
    private javax.swing.JLabel func1ICON;
    private javax.swing.JPanel func2;
    private javax.swing.JLabel func2ICON;
    private javax.swing.JPanel func3;
    private javax.swing.JLabel func3ICON;
    private javax.swing.JPanel func4;
    private javax.swing.JLabel func4ICON;
    private javax.swing.JPanel funcionarioBtn;
    private javax.swing.JLabel funcionarioTxt;
    private javax.swing.JPanel gambiarraLinhaBranca;
    private javax.swing.JPanel hospedeBtn;
    private javax.swing.JLabel hospedeTxt;
    private javax.swing.JLabel iconC;
    private javax.swing.JLabel iconC1;
    private javax.swing.JLabel iconC2;
    private javax.swing.JLabel iconC3;
    private javax.swing.JLabel iconC4;
    private javax.swing.JLabel iconC5;
    private javax.swing.JLabel iconE;
    private javax.swing.JLabel iconE1;
    private javax.swing.JLabel iconE2;
    private javax.swing.JLabel iconE3;
    private javax.swing.JLabel iconE4;
    private javax.swing.JLabel iconE5;
    private javax.swing.JPanel iconF1;
    private javax.swing.JPanel iconF2;
    private javax.swing.JPanel iconF3;
    private javax.swing.JPanel iconF4;
    private javax.swing.JLabel iconHotelTxt;
    private javax.swing.JLabel iconO;
    private javax.swing.JLabel iconO1;
    private javax.swing.JLabel iconO10;
    private javax.swing.JLabel iconO11;
    private javax.swing.JLabel iconO2;
    private javax.swing.JLabel iconO3;
    private javax.swing.JLabel iconO4;
    private javax.swing.JLabel iconO5;
    private javax.swing.JLabel iconO6;
    private javax.swing.JLabel iconO7;
    private javax.swing.JLabel iconO8;
    private javax.swing.JLabel iconO9;
    private javax.swing.JLabel idHospResMod;
    private javax.swing.JTextField idHospResModTxt;
    private javax.swing.JLabel idHospedeReserva;
    private javax.swing.JLabel idLemMod;
    private javax.swing.JTextField idLemModTxt;
    private javax.swing.JLabel idPagMod;
    private javax.swing.JTextField idPagModTxt;
    private javax.swing.JTextField idReservaMod;
    private javax.swing.JPanel info;
    private javax.swing.JScrollPane infoFuncExcluirFunc;
    private javax.swing.JScrollPane infoFuncVerFunc;
    private javax.swing.JLabel infoFuncionario;
    private javax.swing.JLabel infoHospExcluir;
    private javax.swing.JScrollPane infoHospExcluirHosp;
    private javax.swing.JLabel infoHospVer;
    private javax.swing.JScrollPane infoHospVerHosp;
    private javax.swing.JLabel infoIcon;
    private javax.swing.JLabel infoLemExcluir;
    private javax.swing.JScrollPane infoLemExcluirLem;
    private javax.swing.JLabel infoLemVer;
    private javax.swing.JScrollPane infoLemVerLem;
    private javax.swing.JLabel infoPagExcluir;
    private javax.swing.JScrollPane infoPagExcluirPag;
    private javax.swing.JLabel infoPagVer;
    private javax.swing.JScrollPane infoPagVerPag;
    private javax.swing.JLabel infoQuaExcluir;
    private javax.swing.JScrollPane infoQuaExcluirQua;
    private javax.swing.JLabel infoQuaVer;
    private javax.swing.JScrollPane infoQuaVerQua;
    private javax.swing.JLabel infoResExcluir;
    private javax.swing.JScrollPane infoResExcluirRes;
    private javax.swing.JLabel infoResVer;
    private javax.swing.JScrollPane infoResVerRes;
    private javax.swing.JLabel infoSelectReserva;
    private javax.swing.JScrollPane infoTabelaReservas;
    private javax.swing.JPanel inicioBtn;
    private javax.swing.JLabel inicioTxt;
    private javax.swing.JLabel insiraRgFunc;
    private javax.swing.JLabel insiraRgHosp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel lembreteBtn;
    private javax.swing.JLabel lembretesTxt;
    private javax.swing.JPanel line1;
    private javax.swing.JPanel line2;
    private javax.swing.JPanel line3;
    private javax.swing.JPanel line4;
    private javax.swing.JLabel logadoComo;
    private javax.swing.JLabel logadoComoUsuario;
    private javax.swing.JLabel logoInicio;
    private javax.swing.JLabel logoInicio1;
    private javax.swing.JLabel logout;
    private javax.swing.JComboBox<String> mesConNovoFunc;
    private javax.swing.JComboBox<String> mesEntradaReserva;
    private javax.swing.JComboBox<String> mesEntradaReservaMod;
    private javax.swing.JComboBox<String> mesLem;
    private javax.swing.JComboBox<String> mesLem1;
    private javax.swing.JComboBox<String> mesNascFuncionarioTxt;
    private javax.swing.JComboBox<String> mesNascHospedeTxt;
    private javax.swing.JComboBox<String> mesPag;
    private javax.swing.JComboBox<String> mesPagMod;
    private javax.swing.JComboBox<String> mesSaidaReserva;
    private javax.swing.JComboBox<String> mesSaidaReservaMod;
    private javax.swing.JLabel minimizarTxt;
    private javax.swing.JLabel modFuncFuncao;
    private javax.swing.JPanel modFuncFuncaoBtn;
    private javax.swing.JLabel modFuncionario;
    private javax.swing.JLabel modHospFuncao;
    private javax.swing.JPanel modHospFuncaoBtn;
    private javax.swing.JLabel modHospede;
    private javax.swing.JLabel modLem;
    private javax.swing.JLabel modLemFuncao;
    private javax.swing.JPanel modLemFuncaoBtn;
    private javax.swing.JLabel modPag;
    private javax.swing.JLabel modPagFuncao;
    private javax.swing.JPanel modPagFuncaoBtn;
    private javax.swing.JLabel modQuaFuncao;
    private javax.swing.JPanel modQuaFuncaoBtn;
    private javax.swing.JLabel modQuarto;
    private javax.swing.JLabel modResFuncao;
    private javax.swing.JPanel modResFuncaoBtn;
    private javax.swing.JLabel modReserva;
    private javax.swing.JPanel modificarFuncionario;
    private javax.swing.JButton modificarFuncionarioBtn;
    private javax.swing.JPanel modificarHospede;
    private javax.swing.JButton modificarHospedeBtn;
    private javax.swing.JPanel modificarLembrete;
    private javax.swing.JButton modificarPagBtn;
    private javax.swing.JPanel modificarPagamento;
    private javax.swing.JPanel modificarQuarto;
    private javax.swing.JButton modificarQuartoBtn;
    private javax.swing.JPanel modificarReserva;
    private javax.swing.JButton modificarReservaBtn;
    private javax.swing.JLabel nascimentoFuncionario;
    private javax.swing.JLabel nascimentoHospede;
    private javax.swing.JLabel nomeFuncExcluir;
    private javax.swing.JTextField nomeFuncExcluirTxt;
    private javax.swing.JTextField nomeFuncTxtMod;
    private javax.swing.JLabel nomeFuncVer;
    private javax.swing.JTextField nomeFuncVerTxt;
    private javax.swing.JLabel nomeFuncionario;
    private javax.swing.JLabel nomeFuncionarioMod;
    private javax.swing.JTextField nomeFuncionarioTxt;
    private javax.swing.JLabel nomeHospExcluir;
    private javax.swing.JTextField nomeHospExcluirTxt;
    private javax.swing.JLabel nomeHospRes1;
    private javax.swing.JLabel nomeHospResTxt;
    private javax.swing.JTextField nomeHospReservaTxt;
    private javax.swing.JLabel nomeHospVer;
    private javax.swing.JTextField nomeHospVerTxt;
    private javax.swing.JLabel nomeHospede;
    private javax.swing.JLabel nomeHospedeMod;
    private javax.swing.JTextField nomeHospedeTxt;
    private javax.swing.JTextField nomeHospedeTxtMod;
    private javax.swing.JTextField nomeResExcluirTxt;
    private javax.swing.JTextField nomeResVerTxt;
    private javax.swing.JLabel numQuaExcluir;
    private javax.swing.JTextField numQuaExcluirTxt;
    private javax.swing.JLabel numQuaResMod;
    private javax.swing.JTextField numQuaResModTxt;
    private javax.swing.JLabel numQuaVer;
    private javax.swing.JTextField numQuaVerTxt;
    private javax.swing.JLabel numQuarto;
    private javax.swing.JLabel numQuartoMod;
    private javax.swing.JLabel numQuartoReserva;
    private javax.swing.JTextField numQuartoReservaTxt;
    private javax.swing.JTextField numQuartoTxt;
    private javax.swing.JTextField numQuartoTxtMod;
    private javax.swing.JLabel numResExcluir;
    private javax.swing.JLabel numResVer;
    private javax.swing.JPanel pagamentoBtn;
    private javax.swing.JPanel panelFuncionario;
    private javax.swing.JPanel panelHospede;
    private javax.swing.JPanel panelInicio;
    private javax.swing.JPanel panelLembrete;
    private javax.swing.JPanel panelPagamento;
    private javax.swing.JPanel panelQuarto;
    private javax.swing.JPanel panelReserva;
    private javax.swing.JLabel precoQuarto;
    private javax.swing.JLabel precoQuartoMod;
    private javax.swing.JFormattedTextField precoQuartoTxt;
    private javax.swing.JFormattedTextField precoQuartoTxtMod;
    private javax.swing.JTextArea quadro;
    private javax.swing.JPanel quartoBtn;
    private javax.swing.JLabel quartoTxt;
    private javax.swing.JLabel relogio;
    private javax.swing.JPanel reservaBtn;
    private javax.swing.JLabel reservaTxt;
    private javax.swing.JLabel rgFuncionario;
    private javax.swing.JTextField rgFuncionarioTxt;
    private javax.swing.JLabel rgHospede;
    private javax.swing.JTextField rgHospedeTxt;
    private javax.swing.JLabel sairTxt;
    private javax.swing.JScrollPane saudacaoQuadro;
    private javax.swing.JScrollPane selectFuncExcluirFunc;
    private javax.swing.JTable selectFuncReserva;
    private javax.swing.JTable selectFuncVer;
    private javax.swing.JScrollPane selectFuncVerFunc;
    private javax.swing.JScrollPane selectHospExcluir;
    private javax.swing.JScrollPane selectHospVer;
    private javax.swing.JTable selectHospedeExcluir;
    private javax.swing.JTable selectHospedeReserva;
    private javax.swing.JTable selectHospedeVer;
    private javax.swing.JScrollPane selectHospedesReservas;
    private javax.swing.JScrollPane selectLembreteExcluir;
    private javax.swing.JScrollPane selectLembreteVer;
    private javax.swing.JTable selectLembretesExcluir;
    private javax.swing.JTable selectLembretesVer;
    private javax.swing.JScrollPane selectPagamentoExcluir;
    private javax.swing.JScrollPane selectPagamentoVer;
    private javax.swing.JTable selectPagamentosExcluir;
    private javax.swing.JTable selectPagamentosVer;
    private javax.swing.JScrollPane selectQuartoExcluir;
    private javax.swing.JTable selectQuartoReserva;
    private javax.swing.JScrollPane selectQuartoVer;
    private javax.swing.JTable selectQuartosExcluir;
    private javax.swing.JScrollPane selectQuartosReservas;
    private javax.swing.JTable selectQuartosVer;
    private javax.swing.JScrollPane selectReservaExcluir;
    private javax.swing.JScrollPane selectReservaVer;
    private javax.swing.JTable selectReservasExcluir;
    private javax.swing.JTable selectReservasVer;
    private javax.swing.JTextField senhaFuncTxtMod;
    private javax.swing.JLabel senhaFuncionario;
    private javax.swing.JLabel senhaFuncionarioMod;
    private javax.swing.JTextField senhaFuncionarioTxt;
    private javax.swing.JComboBox<String> sexoFuncTxtMod;
    private javax.swing.JLabel sexoFuncionario;
    private javax.swing.JLabel sexoFuncionarioMod;
    private javax.swing.JComboBox<String> sexoFuncionarioTxt;
    private javax.swing.JLabel sexoHospede;
    private javax.swing.JLabel sexoHospedeMod;
    private javax.swing.JComboBox<String> sexoHospedeTxt;
    private javax.swing.JComboBox<String> sexoHospedeTxtMod;
    private javax.swing.JLabel tableNewRes;
    private javax.swing.JTextField telefoneFuncTxtMod;
    private javax.swing.JLabel telefoneFuncionario;
    private javax.swing.JLabel telefoneFuncionarioMod;
    private javax.swing.JTextField telefoneFuncionarioTxt;
    private javax.swing.JLabel telefoneHospede;
    private javax.swing.JLabel telefoneHospedeMod;
    private javax.swing.JTextField telefoneHospedeTxt;
    private javax.swing.JTextField telefoneHospedeTxtMod;
    private javax.swing.JLabel tipoQuarto;
    private javax.swing.JLabel tipoQuartoMod;
    private javax.swing.JTextField tipoQuartoTxt;
    private javax.swing.JTextField tipoQuartoTxtMod;
    private javax.swing.JLabel transacaoTxt1;
    private javax.swing.JTextField usuarioFuncTxtMod;
    private javax.swing.JLabel usuarioFuncionario;
    private javax.swing.JLabel usuarioFuncionarioMod;
    private javax.swing.JTextField usuarioFuncionarioTxt;
    private javax.swing.JLabel valorPag;
    private javax.swing.JLabel valorPagExcluir;
    private javax.swing.JLabel valorPagMod;
    private javax.swing.JFormattedTextField valorPagModTxt;
    private javax.swing.JFormattedTextField valorPagTxt;
    private javax.swing.JLabel valorPagVer;
    private javax.swing.JLabel verFunc;
    private javax.swing.JLabel verFuncFuncao;
    private javax.swing.JPanel verFuncFuncaoBtn;
    private javax.swing.JPanel verFuncionario;
    private javax.swing.JLabel verHosp;
    private javax.swing.JLabel verHospFuncao;
    private javax.swing.JPanel verHospFuncaoBtn;
    private javax.swing.JPanel verHospede;
    private javax.swing.JButton verHospedeReservaBtn;
    private javax.swing.JLabel verLem;
    private javax.swing.JLabel verLemFuncao;
    private javax.swing.JPanel verLemFuncaoBtn;
    private javax.swing.JPanel verLembrete;
    private javax.swing.JLabel verPag;
    private javax.swing.JLabel verPagFuncao;
    private javax.swing.JPanel verPagFuncaoBtn;
    private javax.swing.JPanel verPagamento;
    private javax.swing.JLabel verQua;
    private javax.swing.JLabel verQuaFuncao;
    private javax.swing.JPanel verQuaFuncaoBtn;
    private javax.swing.JPanel verQuarto;
    private javax.swing.JButton verQuartoReservaBtn;
    private javax.swing.JLabel verRes;
    private javax.swing.JLabel verResFuncao;
    private javax.swing.JPanel verResFuncaoBtn;
    private javax.swing.JPanel verReserva;
    // End of variables declaration//GEN-END:variables
}
