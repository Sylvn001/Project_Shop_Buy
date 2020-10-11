/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_shopbuy;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.xml.bind.DatatypeConverter;
import static project_shopbuy.DBConnection.createConnection;

/**
 * FXML Controller class
 *
 * @author junio
 */
public class FXMLEditarProdutoController implements Initializable {

    @FXML
    private TextField nomProd;
    @FXML
    private TextField categProd;
    @FXML
    private TextField precoProd;
    @FXML
    private TextArea descProd;

    private Produto prod = null;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setProd(Produto p){
        this.prod = p;
        this.nomProd.setText(p.getProduto());
        this.categProd.setText(p.getCategoria());
        this.descProd.setText(p.getDescricao());
        this.precoProd.setText(Double.toString(p.getPreco())); 
        
    }

    
    @FXML
    private void EditarProduto(ActionEvent event) {
        Produto p = new Produto(nomProd.getText(), Double.parseDouble(precoProd.getText()) , categProd.getText(), descProd.getText());
        PreparedStatement psmt = null;
        Connection con = createConnection();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        if(con != null){
            try{
                System.out.println("Esta conectado");
                String sql = "UPDATE tb_prod SET pro_nome = ?, pro_categ = ?, pro_desc = ?, pro_prec = ?  where pro_id = " + this.prod.getCod();
                psmt = con.prepareCall(sql);
                psmt.setString(1, p.getProduto());
                psmt.setString(2, p.getCategoria());
                psmt.setString(3, p.getDescricao());
                psmt.setDouble(4, p.getPreco());
                psmt.execute();
                alert.setTitle("Cadastrado com sucesso!");
                alert.setContentText("O produto foi Editado com sucesso!!");
                alert.showAndWait();
                sair();
                
            }catch(SQLException e){
                alert.setTitle("Erro de cadastro!");
                alert.setContentText("Não foi possivel concluir a operação\n " + e);
                alert.showAndWait();
            }
        }
         
        else{
              alert.setTitle("Erro Não foi possivel concetar ao banco");
                alert.setContentText("houve algum problema na conexao");
                alert.showAndWait(); 
        }
    }
    private void sair(){
        nomProd.getScene().getWindow().getScene().getWindow().hide();
    }

    @FXML
    private void Cancelar(ActionEvent event) {
        sair();
    }
    
}
