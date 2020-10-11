/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_shopbuy;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static project_shopbuy.DBConnection.createConnection;

/**
 *
 * @author junio
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TableColumn<Produto, Integer> cod;
    @FXML
    private TableColumn<Produto, String> prod;
    @FXML
    private TableColumn<Produto, Double> preco;
    @FXML
    private TableColumn<Produto, String> categoria;
    @FXML
    private Button btnCadastra;
    @FXML
    private Button btnSair;
    @FXML
    private TableView<Produto> tabela;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    ObservableList<Produto> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cod.setCellValueFactory(new PropertyValueFactory<>("cod"));
        prod.setCellValueFactory(new PropertyValueFactory<>("produto"));
        preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tableUpdate();
    }    

    public void tableUpdate(){
        Connection con = createConnection();

        if(con != null)
        {
            try{
                Statement stmt = con.createStatement(); 
                String sql = "select * from tb_prod";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                   oblist.add(new Produto(
                        Integer.parseInt(rs.getString("pro_id")),rs.getString("pro_nome")
                        ,rs.getString("pro_categ"),rs.getString("pro_desc"), rs.getDouble("pro_prec") ));
                }
            }catch(Exception e)
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Erro Não foi possivel executar a ação");
                alert.setContentText("Algo deu errado... \nmais informacoes: " + e);
                alert.showAndWait();  
            }
        }
        
        else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Erro de Conexão!");
            alert.setContentText("Não foi possivel conectar-se ao banco!!");
            alert.showAndWait();
        }
        tabela.setItems(oblist);

    }
    @FXML
    private void cadProd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastraProduto.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        oblist.removeAll(oblist);
        tableUpdate();
    }

    @FXML
    private void exit(ActionEvent event) {
        btnSair.getScene().getWindow().hide();
    }

    @FXML
    private void eventClickTable(MouseEvent event) {
         if (event.getClickCount() == 2 && tabela.getSelectionModel() != null) {
            Produto c = tabela.getSelectionModel().getSelectedItem();
            String mens = "Descricao:  " + c.getDescricao()+ "\n";     
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Descricao do Produto: " + c.getProduto());
            alert.setHeaderText(c.getProduto());
            alert.setContentText(mens);
            alert.showAndWait();
        }
    }
    private void delete(int cod){
        Connection con = createConnection();
        
        if(con != null)
        {
            try{
                Statement stmt = con.createStatement(); 
                String sql = "delete from tb_prod where pro_id = " + cod;
                stmt.execute(sql);
            }catch(Exception e)
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Erro Não foi possivel executar a ação");
                alert.setContentText("Algo deu errado... \nmais informacoes: " + e);
                alert.showAndWait();  
            }
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Erro de conexão");
            alert.setContentText("Algo deu errado na conexão. ");
            alert.showAndWait();  
        }  
    }

    @FXML
    private void deleteProd(ActionEvent event) {
        if(tabela.getSelectionModel().getSelectedIndex() >= 0 ){
            delete(tabela.getSelectionModel().getSelectedItem().getCod());
        }
        oblist.removeAll(oblist);
        tableUpdate();
    }

    @FXML
    private void EditProd(ActionEvent event) throws IOException {
        
        if(tabela.getSelectionModel().getSelectedIndex() >= 0 ){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLEditarProduto.fxml"));
            Parent root = loader.load();

            FXMLEditarProdutoController controller = loader.getController();
            controller.setProd(tabela.getSelectionModel().getSelectedItem());

            Stage stage=new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Selecione um Item!!");
            alert.setContentText("Você precisa selecionar algo para editar");
            alert.showAndWait(); 
        }
        
        oblist.removeAll(oblist);
        tableUpdate();
    }
    
}
