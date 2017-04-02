package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by staho on 02.04.2017.
 */
public class Main extends Application{
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Rysowanie funkcji");
        primaryStage.getIcons().add(new Image("https://cdn0.iconfinder.com/data/icons/personal-and-business-finance/64/1-27-512.png"));

        initRootLayout();
        showDrawingOverview();

    }

    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));

            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showDrawingOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/Drawing.fxml"));
            SplitPane splitPane = loader.load();

            rootLayout.setCenter(splitPane);

            DrawingController controller = loader.getController();
            controller.setMain(this);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
