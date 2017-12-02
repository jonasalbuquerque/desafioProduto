
public class Produto {
    private int codigo;
    private String nome;
    private int[][] vendas;
    
    Produto(){
        //inicializa a matriz de vendas
        //as linhas são os anos, as colunas são os meses
        vendas = new int[5][12];
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int[][] getVendas() {
        return vendas;
    }

    public void setVendas(int[][] vendas) {
        this.vendas = vendas;
    }

    
    
    

    
    
        
}
