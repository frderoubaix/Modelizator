package fr.m3105.projetmode.controller;

import fr.m3105.projetmode.model.Model;
import javafx.scene.paint.Color;

public class PointController extends ViewController{
    @Override
    public void draw() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
        int tabLenght = model.points[0].length;
        double[][] tempPoint = model.points;
        for(int i=0;i<tabLenght;i++){
            graphicsContext.strokeLine(tempPoint[0][i],tempPoint[1][i],tempPoint[0][i],tempPoint[1][i]);
        }
    }
}
