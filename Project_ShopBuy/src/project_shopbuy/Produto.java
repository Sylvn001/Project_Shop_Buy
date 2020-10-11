/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_shopbuy;

/**
 *
 * @author junio
 */
public class Produto {
    private int cod; 
    private String produto;
    private double preco;
    private String categoria;    
    private String descricao;
    
    public Produto( String produto, double preco, String categoria, String descricao) {
        this.produto = produto;
        this.preco = preco;
        this.categoria = categoria;
        this.descricao = descricao;
       
    }
    public Produto(int cod, String produto, String categoria, String descricao, double preco) {
        this.produto = produto;
        this.preco = preco;
        this.categoria = categoria;
        this.descricao = descricao;
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }
    
    

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
