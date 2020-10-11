/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_shopbuy;

import java.util.List;

/**
 *
 * @author junio
 */
public class ListaProduto {
    private List<Produto> produtos;
    
    public void addProduto(Produto p){
        produtos.add(p);
    }
    
    public List<Produto> listaProdutos(){
        return this.produtos;
    }
}
