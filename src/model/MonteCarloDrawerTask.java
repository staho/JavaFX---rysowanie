package model;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by staho on 03.04.2017.
 */
public class MonteCarloDrawerTask extends Task {

    @Override
    protected Object call() throws Exception{
        final int width = (int)gc.getCanvas().getWidth();
        final int height = (int)gc.getCanvas().getHeight();
        /*
        gc.clearRect(gc.getCanvas().getLayoutX(),
                gc.getCanvas().getLayoutY(),
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());

        gc.setFill(javafx.scene.paint.Color.BLUE);
        gc.fillRect(gc.getCanvas().getLayoutX(),
                gc.getCanvas().getLayoutY(),
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());
        */
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        double dx = data.getxEnd() - data.getxBegin();
        double dy = data.getyEnd() - data.getyBegin();

        //double xB = data.getxBegin();
        //double yB = data.getyBegin();

        double x;
        double y;

        long hits = 0;

        Random random = new Random();

        for(long i = 1; i <= data.getNoOfPoints(); i++){
            x =  -8 + (8-(-8)) * random.nextDouble();
            y =  -8 + (8-(-8)) * random.nextDouble();
            if(Equation.calc(x,y)){
                bi.setRGB((int)(35*x + width/2),(int)((-35)*y+height/2), Color.YELLOW.getRGB());
                hits++;
            }
            else {
                bi.setRGB((int)(35*x + width/2),(int)((-35)*y+height/2), Color.BLUE.getRGB());
            }

            if(i % 1000 == 0) {
                gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0 );
                //TimeUnit.MICROSECONDS.sleep(5);
            }
            updateProgress(i, data.getNoOfPoints());
            if(isCancelled()) break;
        }
        data.setSum(hits/(double)data.getNoOfPoints() * dx * dy);


        return null;
    }

    public MonteCarloDrawerTask(GraphicsContext gc, MonteCarloData data){
        this.gc = gc;
        this.data = data;
    }

    private GraphicsContext gc;
    private MonteCarloData data;
}
