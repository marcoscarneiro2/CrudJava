import java.sql.*;

public class DBManager {

    private static String user = "postgres";
    private static String pass = "postgres";
    private static Connection connection;
    public static void initConnection(){
        try{
            connection  = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pecas",user,pass);
            System.out.println("Conectado com exito");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //Adicionar produto
    public static void adicionarProduto(String codigo,String descricao, String preco,int idgrupo){
        try {
            PreparedStatement stmn = connection.prepareStatement("INSERT INTO produto (codigo, descricao,preco,idgrupo) Values (?,?,?,?)");
            stmn.setString(1,codigo);
            stmn.setString(2,descricao);
            stmn.setString(3,preco);
            stmn.setInt(4,idgrupo);

            stmn.execute();
            System.out.println("Produto Cadastrado");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //Retornar os dados do Banco
    public static void getProduto(){
        try {
            PreparedStatement stmn = connection.prepareStatement("select id_produto, codigo, produto.descricao pd,preco, grupo.descricao gd from produto inner join grupo on grupo.id_grupo = produto.idgrupo order by codigo");
            ResultSet result = stmn.executeQuery();

            System.out.println("id|codigo|descricao|preco|grupo");
            while (result.next()){
                long id_produto = result.getLong("id_produto");
                String codigo = result.getString("codigo");
                String pd = result.getString("pd");
                String preco = result.getString("preco");
                String gd = result.getString("gd");



                System.out.println(id_produto+"\t"+codigo+"\t"+pd+"\t"+preco+"\t"+gd);
            }

        }catch (Exception throwables){
            throwables.printStackTrace();
        }

    }
    //Editar registro do banco
    public static void atualizarProduto(long id_produto, String codigo, String descricao,String preco,long idgrupo){
        try {
            PreparedStatement stmn = connection.prepareStatement("Update produto set codigo = ?, descricao =? , preco =?,idgrupo =? where id_produto = ?");
            stmn.setString(1,codigo);
            stmn.setString(2,descricao);
            stmn.setString(3,preco);
            stmn.setLong(4,idgrupo);
            stmn.setLong(5,id_produto);

            int row = stmn.executeUpdate();
            if(row == 0){
                System.out.println("Informação não alterada");
            }else{
                System.out.println("Dados atualizados");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //Deletar dados do Banco
    public static void deletarProduto(long id_produto) {
        PreparedStatement stmn = null;
        try {
            stmn = connection.prepareStatement("Delete from produto where id_produto = ?");
            stmn.setLong(1, id_produto);

            int row = stmn.executeUpdate();
            if (row == 0) {
                System.out.println("Não foi possível excluir o registro do id " + id_produto);
            } else {
                System.out.println("Produto exluído com sucesso");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Encerrar conexão
    public static void CloseConnection(){
        try {
            connection.close();
            System.out.println("Fechando Sistema");
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}

