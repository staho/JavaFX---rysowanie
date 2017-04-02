package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

/**
 * Created by staho on 02.04.2017.
 */
public class DrawingController {

    @FXML
    private Canvas canvas;





    private Main main;

    public void setMain(Main main){
        this.main = main;
    }
}
