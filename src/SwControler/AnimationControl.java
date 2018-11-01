/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwControler;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 * @author Adriano Valt <adrianovalt@gmail.com>
 */
public class AnimationControl {

private Path gerarPercurso(final double pathOpacity, double x, double y)
   {
      final Path percurso = new Path();
      //definir coordenadas em MoveTo(X,Y) onde se encontra o nó de origem
      percurso.getElements().add(new MoveTo(x+36,y+36));
      //definir coordenadas em LineTo(X,Y) onde se encontra o nó de destino
      percurso.getElements().add(new LineTo(450,350));
      percurso.setOpacity(pathOpacity);
      return percurso;
   }

   private PathTransition gerarPercursoTransicao(final Shape shape, final Path path)
   {
      final PathTransition percursoTransicao = new PathTransition();
      percursoTransicao.setDuration(Duration.seconds(8.0));
      percursoTransicao.setDelay(Duration.seconds(2.0));
      percursoTransicao.setPath(path);
      percursoTransicao.setNode(shape);
      percursoTransicao.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
      percursoTransicao.setCycleCount(Timeline.INDEFINITE);
      //percursoTransicao.setAutoReverse(true);
      return percursoTransicao;
   }
   
   protected void applyAnimation(final Pane pane, double x, double y)
   {
      final Circle circle = new Circle(x+36, y+36, 10);
      circle.setFill(Color.GREEN);
      final Path path = gerarPercurso(0, x, y);
      pane.getChildren().add(path);
      pane.getChildren().add(circle);
      final PathTransition transition = gerarPercursoTransicao(circle, path);
      transition.play(); 
   }
}
