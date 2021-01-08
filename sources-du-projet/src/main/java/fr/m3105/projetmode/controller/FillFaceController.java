package fr.m3105.projetmode.controller;

import fr.m3105.projetmode.model.Model;
import javafx.scene.paint.Color;

public class FillFaceController extends ViewController {
    @Override
    public void draw() {
        Model model = ((Model) this.getValue());
        int tabLenght = model.getFaces()[0].length;
        int[][] tempFace = sortFace(model.getFaces());
        if(lightsOn) applyLights(new double[] {1,1,1});
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
        for (int k = 0; k < tabLenght; k++) {
            if (model.isColor()){
                int pos = 0;
                for (int i = 0;i<tabLenght;i++){
                    if(tempFace[0][k] == model.getFaces()[0][i] && tempFace[1][k] == model.getFaces()[1][i] && tempFace[2][k] == model.getFaces()[2][i]){
                        pos = i;
                        break;
                    }
                }
                //System.out.println(pos);
                graphicsContext.setFill(Color.color((double)model.getRgbAlpha()[0][pos]/255,(double)model.getRgbAlpha()[1][pos]/255,(double)model.getRgbAlpha()[2][pos]/255));
            }
            double[] x = new double[]{model.getPoint(tempFace[0][k])[0], model.getPoint(tempFace[1][k])[0], model.getPoint(tempFace[2][k])[0]};
            double[] y = new double[]{model.getPoint(tempFace[0][k])[1], model.getPoint(tempFace[1][k])[1], model.getPoint(tempFace[2][k])[1]};
            graphicsContext.fillPolygon(x, y, 3);
        }
        /*for (int i = 0; i < model.getFaces().length; i++) {
            for (int j = 0; j < model.getFaces()[i].length; j++)
                System.out.print(model.getFaces()[i][j] + " ");
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < sortFace(model.getFaces()).length; i++) {
            for (int j = 0; j < sortFace(model.getFaces())[i].length; j++)
                System.out.print(sortFace(model.getFaces())[i][j] + " ");
            System.out.println();
        }*/
    }
}
/*

 */
