package hotel;

/*  para retirar as linhas do -Xlint use configuração personalizada
 *  com o argumento "-Xlint:unchecked" ou "-Xlint:deprecation"
 */
public class Principal {

    public static void main(String[] args) {

        Login l = new Login();
        l.setAutoRequestFocus(true);
        l.setFocusable(true);
        l.requestFocus();
        l.setVisible(true);
        //Inicial n = new Inicial("admin", "Administrador");n.setAutoRequestFocus(true);n.setFocusable(true);n.requestFocus();n.setVisible(true);

        /*INSERT AUTOMATICO PARA TESTES (put one bar before the asterisch --->)*
        for (int c=1;c<=100;c++){
            Quarto q = new Quarto();
            q.numero = c;
            if (c>=1 && c<=10){
                q.andar = 1;
            }else if (c>=11 && c<=20) {
                q.andar = 2;
            }else if (c>=21 && c<=30) {
                q.andar = 3;
            }else if (c>=31 && c<=40) {
                q.andar = 4;
            }else if (c>=41 && c<=50) {
                q.andar = 5;
            }else if (c>=51 && c<=60) {
                q.andar = 6;
            }else if (c>=61 && c<=70) {
                q.andar = 7;
            }else if (c>=71 && c<=80) {
                q.andar = 8;
            }else if (c>=81 && c<=90) {
                q.andar = 9;
            }else if (c>=91 && c<=100) {
                q.andar = 10;}

            q.descricao = "Descrição do quarto " +q.numero+" do andar "+q.andar+"!";
            q.disponivel = "S";
            q.preco = 5000.+q.numero;
            q.tipo = "Tipo "+q.numero+q.andar;
            new Conection().executeComand("INSERT INTO quartos(numero,andar,tipo,descricao,preco,disponivel) VALUES ("
                    + q.numero + "," + q.andar + ",'" + q.tipo + "','" + q.descricao + "'," + q.preco + ",'S');","");
            System.out.println("Gravando quarto número: "+q.numero);
        }/**/
    }
}
