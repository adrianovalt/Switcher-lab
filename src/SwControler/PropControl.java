/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwControler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * @author Adriano Valt <adrianovalt@gmail.com>
 */
public class PropControl {
    @FXML
    private ImageView computerImage;
    
    @FXML
    private VBox rightVBox;
    
    @FXML
    protected void backAction(ActionEvent event) {
        Main.alternaTela("main");
    }
    
    //@Override
    public void initialize(URL url, ResourceBundle rb) {
        
        computerImage.setImage(new Image("images/download.jpeg"));
        rightVBox.getChildren().add(computerImage);
        
    }
}
