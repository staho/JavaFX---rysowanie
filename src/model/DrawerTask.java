package model;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.*;

import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * Created by staho on 03.04.2017.
 */
public class DrawerTask extends Task{
    @Override
    protected Object call() throws Exception{
        final int width = (int)gc.getCanvas().getWidth();
        final int height = (int)gc.getCanvas().getHeight();

        gc.setFill(javafx.scene.paint.Color.BLUE);
        gc.fillRect(gc.getCanvas().getLayoutX(),
                gc.getCanvas().getLayoutY(),
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


        for(double i = -8.; i <= 8.; i+=0.001){
            for(double j = -8.; j <= 8.; j+=0.001){
                if(Equation.calc(i,j)){
                    bi.setRGB((int)(50*i + width/2),(int)((-50)*j+height/2),Color.YELLOW.getRGB());
                }
            }
            gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0 );
            TimeUnit.MICROSECONDS.sleep(15);
            updateProgress(i+8, 16);
            if(isCancelled()) break;
        }
        return null;
    }

    private GraphicsContext gc;

    public DrawerTask(GraphicsContext gc){
        this.gc = gc;
    }
}
