/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwControler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * @author Adriano Valt <adrianovalt@gmail.com>
 */
public class PropControl {

    @FXML
    protected void backAction(ActionEvent event) {
        Main.alternaTela("main");
    }
}
