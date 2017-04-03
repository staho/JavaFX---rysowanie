package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import model.DrawerTask;

/**
 * Created by staho on 02.04.2017.
 */
public class DrawingController {

    @FXML
    private Canvas canvas;

    private DrawerTask task;

    @FXML
    private void handleRunBtnAction(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        task = new DrawerTask(gc);
        progressBar.progressProperty().bind(task.progressProperty());

        new Thread(task).start();

    }

    @FXML
    private void handleStopBtnAction(){
        if(task != null)
            task.cancel();
    }

    @FXML
    private ProgressBar progressBar;




    private Main main;

    public void setMain(Main main){
        this.main = main;
    }
}
