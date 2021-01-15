import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Scanner;

public class Principal {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        DBManager.initConnection();

        int opc;

        do{
            System.out.println("\n1 - Ver produtos");
            System.out.println("2 - Adicionar produto");
            System.out.println("3 - Editar produto");
            System.out.println("4 - Excluir produto");
            System.out.println("5 - Fechar sistema\n");
            opc = scanner.nextInt();

            if(opc == 1){
                verProduto();
            }else if(opc == 2){
                adicionarProduto();
            }else if(opc == 3){
                editarProduto();
            }else if(opc == 4){
                excluirProduto();
            }else if(opc == 5){
                DBManager.CloseConnection();
            }else{
                System.out.println("Opção Inválida");
            }

        }while(opc !=5);

    }
    public static void verProduto(){

        DBManager.getProduto();
    }
    public static void adicionarProduto(){
        System.out.println("Código do Produto");
        scanner.nextLine();
        String codigo = scanner.nextLine();

        System.out.println("Descrição do Produto");
        String descricao = scanner.nextLine();

        System.out.println("Preço do Produto");
        String preco = scanner.nextLine();

        System.out.println("Tipo de Grupo: \nDigite\n 1- PASTILHAS \n 2- AMORTECEDOR \n 3- BATERIAS");
        int idgrupo = scanner.nextInt();

        DBManager.adicionarProduto(codigo,descricao,preco,idgrupo);
    }
    public static void editarProduto(){
        DBManager.getProduto();

        System.out.println("Digite o Id do produto que deseja editar");
        long id_produto = scanner.nextInt();

        System.out.println("Digite um novo código do Produto");
        scanner.nextLine();
        String codigo = scanner.nextLine();

        System.out.println("Descreva novamente o Produto");
        String descricao = scanner.nextLine();

        System.out.println("Novo preço do Produto");
        String preco = scanner.nextLine();

        System.out.println("Tipo de Grupo: \nDigite\n 1- PASTILHAS \n 2- AMORTECEDOR \n 3- BATERIAS");
        int idgrupo = scanner.nextInt();

        DBManager.atualizarProduto(id_produto,codigo,descricao,preco,idgrupo);

    }
    public static void excluirProduto(){
        DBManager.getProduto();
        System.out.println("Digite o Id do produto que deseja excluir");
        long id_produto = scanner.nextInt();
        DBManager.deletarProduto(id_produto);
    }
}
