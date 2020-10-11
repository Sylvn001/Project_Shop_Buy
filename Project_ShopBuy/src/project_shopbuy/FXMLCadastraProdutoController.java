/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_shopbuy;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import static project_shopbuy.DBConnection.createConnection;

/**
 * FXML Controller class
 *
 * @author junio
 */
public class FXMLCadastraProdutoController implements Initializable {

    @FXML
    private TextField nomProd;
    @FXML
    private TextField categProd;
    @FXML
    private TextField precoProd;
    @FXML
    private TextArea descProd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
    }    

    @FXML
    private void CadastrarProduto(ActionEvent event) {
        Produto p = new Produto(nomProd.getText(), Double.parseDouble(precoProd.getText()) , categProd.getText(), descProd.getText());
        PreparedStatement psmt = null;
        Connection con = createConnection();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        if(con != null){
            try{
                System.out.println("Esta conectado");
                String sql = "INSERT INTO tb_prod ( pro_nome, pro_categ, pro_desc, pro_prec) values (?,?,?,? )";
                psmt = con.prepareCall(sql);
                psmt.setString(1, p.getProduto());
                psmt.setString(2, p.getCategoria());
                psmt.setString(3, p.getDescricao());
                psmt.setDouble(4, p.getPreco());
                psmt.execute();
                alert.setTitle("Erro de cadastro!");
                alert.setContentText("Cadastrado com sucesso!!");
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

    @FXML
    private void cancelarProd(ActionEvent event) {
        sair();
    }
    public void sair(){
        descProd.getScene().getWindow().getScene().getWindow().hide();
    }

}
