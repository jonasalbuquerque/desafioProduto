import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Aplicacao {
    
    //lista de produtos
    private static List<Produto> produtos;
    //constante para atribuir o código de produto
    private static int c;
       
    public static void main(String [] args) {
        //inicializa as variáveis
        produtos = new ArrayList<>();
        c=1;
        int escolha;
        boolean ok = true;
        
        System.out.println("---------REGISTRO DE VENDAS---------");
        
        //laço que executa a variável "ok" for verdadeira
        //isso serve pra criar o menu, que sempre vai ser executado até que eu mude a variável pra false
        while(ok){
            System.out.println("\n>> ESCOLHA UMA DAS OPÇÕES <<");
            System.out.println(">> 1 << Cadastrar um produto\n>> 2 << Alterar um produto\n>> 3 << Alterar uma venda de um produto\n>> 4 << Imprimir total de vendas para os anos\n>> 5 << Imprimir total de vendas para cada mês\n>> 6 << Imprimir produto mais vendido em um determinado ano\n>> 7 << Imprimir todos os produtos e suas vendas\n>> 8 << Sair\n");
            System.out.print(">> ");
            
            //recebe a escolha digitada no teclado em forma de inteiro
            Scanner sc = new Scanner(System.in);
            escolha = sc.nextInt();
            
            //para cada valor de "escolha", realiza um caminho diferente
            switch (escolha) {
                case 1 :
                    //Cadastrar um produto
                    System.out.println("\n>> Digite o nome do produto a ser cadastrado: ");
                    System.out.print(">> ");
                    Scanner sc1 = new Scanner(System.in);
                    String n1;
                    n1 = sc1.nextLine();
                    if(cadastraProduto(n1)) {
                        //aqui são geradas as vendas
                        //quantidade aleatória entre 0 e 100 foram atribuídas para cada mês dos 5 anos
                        Random random = new Random();
                        int qnt;
                        for (int i = 0; i<5 ;i++ ) {
                            for (int j = 0; j < 12; j++) {
                                qnt = random.nextInt(100);
                                cadastraVenda(c-1, j, i, qnt);                             
                            }                           
                        }
                        System.out.println("\n>> Produto cadastrado com sucesso!\n\n>> As vendas do produto foram geradas automaticamente.\n");
                    } else {
                        System.out.println(">> Produto NÃO cadastrado!\n");
                    }             
                    break;
                case 2 :
                    //Alterar um produto
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
                        System.out.println("\n>> Produto alterado com sucesso!\n");
                    } else {
                        System.out.println(">> Produto NÃO alterado!\n");
                    } 
                    break;
                case 3 :
                    //Alterar uma venda de um produto
                    System.out.println("\n>> Digite o codigo do produto a ser alterado: ");
                    System.out.print(">> ");
                    int cp;
                    cp = sc.nextInt();
                    System.out.println("\n>> Digite o ano, o mes e a quantidade da venda a ser alterada: ");
                    System.out.print(">> ");
                    int ano,mes,qnt;
                    ano = sc.nextInt();
                    mes = sc.nextInt();
                    qnt = sc.nextInt();
                    if(alterarVenda(cp,mes,ano,qnt)) {
                        System.out.println("\n>> Venda alterada com sucesso!\n");
                    } else {
                        System.out.println(">> Produto NÃO alterado!\n");
                    }                    
                    break;
                case 4 :
                    //Imprimir total de vendas para os anos
                    totalAnos();                   
                    break;
                case 5 :
                    //Imprimir total de vendas para cada mês
                    totalMeses();                   
                    break;
                case 6 :
                    //Imprimir produto mais vendido em um determinado ano
                    System.out.println(">> Digite o ano em questão: ");
                    System.out.print(">> ");
                    int y;
                    y = sc.nextInt();
                    maisVendido(y);                   
                    break;
                case 7 :
                    //Imprimir todos os produtos e suas vendas
                    imprimirProdutos();                   
                    break;
                case 8 :
                    //Sair
                    //atribuo false à variável "ok" e o laço principal não é mais executado, então sai do programa
                    ok = false;                   
                    break;
                default :
                    //se digitar qualquer outro comando aparece essa mensagem
                    System.out.println("\n>> Digite um comando válido!");
                    break;

            }
        }
    }
    //cadastra um produto e retorna um booleano dizendo se deu certo ou não
    public static boolean cadastraProduto(String nome){
        //cria um objeto Produto
        Produto p = new Produto();
        //altera o nome do objeto p
        p.setNome(nome);
        //altera o código do objeto p
        p.setCodigo(c);
        
        System.out.println("\n>> NOME: "+nome);
        System.out.println(">> CÓDIGO: "+c);
        
        c++;
        //adiciona o objeto p à lista de produtos
        //essa ação retorna verdadeiro se der certo e false se não conseguir adicionar
        return produtos.add(p);
    }
    public static boolean cadastraVenda(int codigo, int mes, int ano, int quantidade){
        //procura o produto p com o código recebido
        for (Produto p : produtos ) {
            if (p.getCodigo() == codigo){
                //cria uma nova matriz de vendas
                int[][] vendas;
                //copia a matriz do produto para a nova matriz
                vendas = p.getVendas();
                //adiciona a quantidade à posição que tem a linha ano e coluna mes
                vendas[ano][mes] = quantidade;
                p.setVendas(vendas);
                return true;
            }
        }
        return false;
    }
    public static boolean alterarProduto(int codigo, String novoNome){
        for (Produto p : produtos ) {
            if (p.getCodigo() == codigo){
                p.setNome(novoNome);
                System.out.println("\n>> NOME: "+novoNome);
                System.out.println(">> CÓDIGO: "+codigo);
                return true;
            }
        }
        return false;
    }
    
    public static boolean alterarVenda(int codigo, int mes, int ano, int quantidade){
        for (Produto p : produtos ) {
            if (p.getCodigo() == codigo){
                int[][] vendas;
                vendas = p.getVendas();
                vendas[ano-1][mes-1] = quantidade;
                p.setVendas(vendas);
                return true;
            }
        }
        return false;
    }
    //imprime todos os produtos e suas respectivas vendas de cada mês
    public static void imprimirProdutos(){
        System.out.println("\n>> LISTA DE PRODUTOS <<");
        for (Produto p : produtos) {
            System.out.println("\n>> NOME: "+p.getNome()+" <<");
            System.out.println(">> CÓDIGO: "+p.getCodigo()+" <<");
            System.out.println(">> VENDAS: \n");
            int[][] vendas = p.getVendas();
            for (int i = 0; i<5 ;i++ ) {
                for (int j = 0; j < 12; j++) {
                    System.out.println(">> ANO: "+(i+1)+", MÊS "+(j+1)+" QUANTIDADE: "+vendas[i][j]+" <<");
                }                           
            }
        }
    }
    //imprime as vendas de cada ano para cada produto e o total das vendas de cada ano
    //criei um vetor de tamanho 5 que significa cada ano
    //pra cada produto eu somei as vendas de cada ano e coloquei no vetor
    //o vetor t1 foi opcional para conseguir dizer o total geral das vendas de cada produto
    public static void totalAnos(){
        int total;
        int[] t = new int[5];
        for (Produto p : produtos) {
            int[] t1 = new int[5];
            System.out.println("Produto: "+p.getNome());
            int[][] vendas = p.getVendas();
            for (int i = 0; i<5 ;i++ ) {
                total=0;
                for (int j = 0; j < 12; j++) {
                    total+=vendas[i][j];
                }
                t[i]+=total;
                t1[i]+=total;
                System.out.println("Ano "+(i+1)+", total: "+total+" vendas");
            }
            System.out.println("       Total: "+(t1[0]+t1[1]+t1[2]+t1[3]+t1[4])+" vendas");
            System.out.println();
        }
        System.out.println("TOTAL GERAL: ");
        System.out.println("Ano 1: "+t[0]+" vendas");
        System.out.println("Ano 2: "+t[1]+" vendas");
        System.out.println("Ano 3: "+t[2]+" vendas");
        System.out.println("Ano 4: "+t[3]+" vendas");
        System.out.println("Ano 5: "+t[4]+" vendas");
    }
    //imprime o total das vendas de cada mês
    //aqui eu criei uma matriz com tudo 0 e fui copiando e somando a o conteúdo de cada posição da matriz de cada produto
    public static void totalMeses(){
        int[][] t=new int[5][12];
        for (Produto p : produtos) {
            System.out.println("Produto: "+p.getNome());
            int[][] vendas = p.getVendas();
            for (int i = 0; i<5 ;i++ ) {
                for (int j = 0; j < 12; j++) {
                    t[i][j]+=vendas[i][j];
                }
            }
        }
        System.out.println(">> Total de vendas de cada mês <<");
        for (int i = 0; i<5 ;i++ ) {
            for (int j = 0; j < 12; j++) {
                System.out.println(">> ANO: "+(i+1)+", MÊS "+(j+1)+" TOTAL: "+t[i][j]+" vendas <<");
            }                           
        }
    }
    //imprime o produto mais vendido em determinado ano;
    public static void maisVendido(int ano){
        int total, codigo=0, maior=0;
        String nome="";
        for (Produto p : produtos) {
            int[][] vendas = p.getVendas();
            total=0;
            for (int j = 0; j < 12; j++) {
                total+=vendas[ano-1][j];
            }
            if (total>maior){
                maior=total;
                codigo = p.getCodigo();
                nome = p.getNome();
            }
            System.out.println();
        }
        System.out.println(">> RESULTADO:");
        System.out.println("\n>> Nome: "+nome+" <<");
        System.out.println(">> Código: "+codigo+" <<");
        System.out.println(">> Total de vendas no "+ano+"º ano: "+maior+" <<");
    }
}
