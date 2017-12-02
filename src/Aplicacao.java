import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Aplicacao {
    
    private static List<Produto> produtos;
    private static int c;
       
    public static void main(String [] args) {
        produtos = new ArrayList<Produto>();
        c=1;
        int escolha;
        boolean vai = true;
        
        while(vai){
            System.out.println(">> ESCOLHA UMAS DAS OPÇÕES <<");
            System.out.println(">> 1 << Cadastrar um produto\n>> 2 << Alterar um produto\n>> 3 << Alterar uma venda de um produto\n>> 4 << Imprimir total de vendas para os anos\n>> 5 << Imprimir total de vendas para cada mês\n>> 6 << Imprimir produto mais vendido\n>> 7 << Sair\n>> 8 << Imprimir todos os produtos");
            System.out.print(">> ");
            Scanner sc = new Scanner(System.in);
            escolha = sc.nextInt();
            
            switch (escolha) {
                case 1 :
                    System.out.println("\n>> Digite o nome do produto a ser cadastrado: ");
                    System.out.print(">> ");
                    Scanner sc1 = new Scanner(System.in);
                    String n1;
                    n1 = sc1.nextLine();
                    if(cadastraProduto(n1)) {
                        for (int i = 1; i<=5 ;i++ ) {
                            for (int j = 1; j <= 12; j++) {
                                Random random = new Random();
                                int qnt = random.nextInt(100);
                                cadastraVenda(c-1, j, i, qnt);                               
                            }                           
                        }
                        
                        System.out.println(">> Produto cadastrado com sucesso!\n");
                    } else {
                        System.out.println(">> Produto NÃO cadastrado!\n");
                    }             
                    break;
                case 2 :
                    System.out.println("\n>> Digite o codigo do produto a ser alterado: ");
                    System.out.print(">> ");
                    int pCodigo;
                    pCodigo = sc.nextInt();
                    System.out.println("\n>> Digite o novo nome do produto: ");
                    System.out.print(">> ");
                    Scanner sc2 = new Scanner(System.in);
                    String n2;
                    n2 = sc2.nextLine();
                    if(alterarProduto(pCodigo,n2)) {
                        System.out.println(">> Produto alterado com sucesso!\n");
                    } else {
                        System.out.println(">> Produto NÃO alterado!\n");
                    } 
                    break;
                case 3 :
                    imprimirProdutos();                   
                    break;
                case 4 :
                    imprimirProdutos();                   
                    break;
                case 5 :
                    imprimirProdutos();                   
                    break;
                case 6 :
                    imprimirProdutos();                   
                    break;
                case 7 :
                    vai = false;                   
                    break;
                case 8 :
                    imprimirProdutos();                   
                    break;
                default :
                    System.out.println("\n>> Digite um comando válido!");
                    break;

            }
        }
    }
    public static boolean cadastraProduto(String nome){
        Produto p = new Produto();
        p.setNome(nome);
        p.setCodigo(c);
        c++;
        return produtos.add(p);
    }
    public static boolean cadastraVenda(int codigo, int mes, int ano, int quantidade){
        System.out.println("-----------1----- ");
        Venda v  = new Venda();
        System.out.println("-------------2--- ");
        v.setMes(mes);
        System.out.println("-------------3--- ");
        v.setAno(ano);
        System.out.println("------------4---- ");
        v.setQuantidade(quantidade);
        System.out.println("-----------5----- ");
        for (Produto p : produtos ) {
            System.out.println("---------6------- ");
            if (p.getCodigo() == codigo){
                System.out.println("------7---------- ");
                List<Venda> vendas = p.getVendas() ;
                System.out.println("-------8--------- ");
                vendas.add(v);    
                System.out.println("----------9------ ");
                p.setVendas(vendas);
                System.out.println("----------10------ ");
                System.out.println("---------------- "+p.getVendas().toString());
                return true;
            }
        }
        return false;
    }
    public static boolean alterarProduto(int codigo, String novoNome){
        for (Produto p : produtos ) {
            if (p.getCodigo() == codigo){
                p.setNome(novoNome);
                return true;
            }
        }
        return false;
    }

    public static void imprimirProdutos(){
        System.out.println("\n>> LISTA DE PRODUTOS <<");
        for (Produto p : produtos ) {
            System.out.println(">> NOME: "+p.getNome()+" <<");
            System.out.println(">> CÓDIGO: "+p.getCodigo()+" <<\n");
            System.out.println(">> VENDAS: \n");
            for (int i = 1; i<=5 ;i++ ) {
                for (int j = 1; j <= 12; j++) {
                    System.out.println(">> ANO: "+i+", MÊS "+j+" "+p.getVendas().get(j)+"<<\n");
                }                           
            }
            
        }
    }
    
}
