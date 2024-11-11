import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyProjectController {

    @FXML
    private Canvas canv;
  
    private GraphicsContext gc;

    private final int RECT_WIDTH = 20;
    private final int MONTHS = 12; 
    private final int MAX_HEIGHT = 250; 

    public void initialize() {
        gc = canv.getGraphicsContext2D();
    }

    @FXML
    void btnpressed(ActionEvent event){
        gc.setFill(Color.RED);
        gc.fillRect(50, 50, 200, 200);

        Random r = new Random(); 

        for (int i = 0; i < MONTHS; i++){
            int height = r.nextInt(MAX_HEIGHT);
            gc.fillRect(RECT_WIDTH * i * 2, canv.getHeight() - 50, RECT_WIDTH, height);
        }   gc.strokeText("WOW", 100, 100);
    }

}
