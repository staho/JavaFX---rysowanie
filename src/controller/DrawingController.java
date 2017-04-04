package controller;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import model.DrawerTask;
import model.MonteCarloData;
import model.MonteCarloDrawerTask;

/**
 * Created by staho on 02.04.2017.
 */
public class DrawingController {

    @FXML
    private Canvas canvas;

    @FXML
    private Label xBeginLabel;
    @FXML
    private Label yBeginLabel;
    @FXML
    private Label xEndLabel;
    @FXML
    private Label yEndLabel;
    @FXML
    private Label noOfPointsLabel;
    @FXML
    private Label sumLabel;


    private DrawerTask task;
    private MonteCarloDrawerTask mcDrawerTask;

    @FXML
    private void initialize(){
        showMonteCarloDataDetails();
    }

    @FXML
    private void handleRunBtnAction(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        task = new DrawerTask(gc);
        progressBar.progressProperty().bind(task.progressProperty());

        new Thread(task).start();

    }

    @FXML
    private void handleCalculateBtnAction(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        mcDrawerTask = new MonteCarloDrawerTask(gc, data);
        progressBar.progressProperty().bind(mcDrawerTask.progressProperty());

        new Thread(mcDrawerTask).start();

        mcDrawerTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                showMonteCarloDataDetails();
            }
        });

    }


    @FXML
    private void handleStopBtnAction(){
        if(task != null)
            task.cancel();
        else if(mcDrawerTask != null)
            mcDrawerTask.cancel();
    }

    @FXML
    private ProgressBar progressBar;

    @FXML
    private void handleEditDataAction(){
        boolean okClicked = main.showMonteCarloDataDialog();
        if(okClicked){
            showMonteCarloDataDetails();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Brak danych");
            alert.setHeaderText("Błąd danych");

            alert.showAndWait();
        }
    }


    private void showMonteCarloDataDetails(){
        if(data != null) {
            xBeginLabel.setText(Double.toString(data.getxBegin()));
            yBeginLabel.setText(Double.toString(data.getxEnd()));
            xEndLabel.setText(Double.toString(data.getyBegin()));
            yEndLabel.setText(Double.toString(data.getyEnd()));
            noOfPointsLabel.setText(Long.toString(data.getNoOfPoints()));
            sumLabel.setText(Double.toString(data.getSum()));
        }
        else {
            xBeginLabel.setText("null");
            yBeginLabel.setText("null");
            xEndLabel.setText("null");
            yEndLabel.setText("null");
            noOfPointsLabel.setText("null");
            sumLabel.setText("null");
        }
    }

    private Main main;
    private MonteCarloData data;

    public void setMonteCarloData(MonteCarloData data){
        this.data = data;
    }

    public void setMain(Main main){
        this.main = main;
    }
}
