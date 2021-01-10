package fr.m3105.projetmode.controller;

import fr.m3105.projetmode.model.Model;
import javafx.scene.paint.Color;

public class FaceSegmentController extends ViewController {
    @Override
    public void draw() {
        if (stage.isCamera()) {
            Model bidule = new Model(this.model);
            bidule.rotateOnYAxis(Math.PI);
            dessine(bidule);
        } else {
            dessine(this.model);
        }
    }

    public void dessine(Model model) {
        int tabLenght = model.getFaces()[0].length;
        int[][] tempFace = sortFace(model.getFaces());
        if (lightsOn && !stage.isCamera()) applyLights(new double[]{0, 0, 1.25});
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
        for (int k = 0; k < tabLenght; k++) {
            if (model.isColor() && !model.isRgbSurPoints() && model.getFaces()[0].length < 50000)
                graphicsContext.setFill(Color.color((double) model.getRgbAlpha()[0][k] / 255, (double) model.getRgbAlpha()[1][k] / 255, (double) model.getRgbAlpha()[2][k] / 255));
            double[] pointsX = new double[]{model.getPoint(tempFace[0][k])[0], model.getPoint(tempFace[1][k])[0], model.getPoint(tempFace[2][k])[0]};
            double[] pointsY = new double[]{model.getPoint(tempFace[0][k])[1], model.getPoint(tempFace[1][k])[1], model.getPoint(tempFace[2][k])[1]};
            graphicsContext.fillPolygon(pointsX, pointsY, 3);
                for(int i = 0;i< tempFace.length;i++){
                    if(i<tempFace.length-1){
                        graphicsContext.strokeLine(model.getPoint(tempFace[i][k])[0], model.getPoint(tempFace[i][k])[1], model.getPoint(tempFace[i+1][k])[0], model.getPoint(tempFace[i+1][k])[1]);
                    }else{
                        graphicsContext.strokeLine(model.getPoint(tempFace[i][k])[0], model.getPoint(tempFace[i][k])[1], model.getPoint(tempFace[0][k])[0], model.getPoint(tempFace[0][k])[1]);
                    }
                }
        }
    }
}
