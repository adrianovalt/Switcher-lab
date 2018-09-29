/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwControler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author Adriano Valt <adrianovalt@gmail.com>
 */
public class Nodo {

    private MainControl getS;

    private void conecartNodo(Node n1, Node n2) {
        if (n1.getParent() != n2.getParent()) {
            throw new IllegalArgumentException("Nodes are in different containers");
        }
        Pane parent = (Pane) n1.getParent();
        Line line = new Line();
        line.startXProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = n1.getBoundsInParent();
            return b.getMinX() + b.getWidth() / 2;
        }, n1.boundsInParentProperty()));
        line.startYProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = n1.getBoundsInParent();
            return b.getMinY() + b.getHeight() / 2;
        }, n1.boundsInParentProperty()));
        line.endXProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = n2.getBoundsInParent();
            return b.getMinX() + b.getWidth() / 2;
        }, n2.boundsInParentProperty()));
        line.endYProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = n2.getBoundsInParent();
            return b.getMinY() + b.getHeight() / 2;
        }, n2.boundsInParentProperty()));
        parent.getChildren().add(line);
    }

    protected static ImageView createDragImage(double x, double y, String caminho) throws FileNotFoundException {
        ImageView selectedImage = new ImageView();
        Image imageX = new Image(new FileInputStream(caminho));
        selectedImage.setImage(imageX);
        selectedImage.setLayoutX(x - 36);
        selectedImage.setLayoutY(y - 36);
        selectedImage.setFitHeight(72);
        selectedImage.setFitWidth(72);

        ObjectProperty<Point2D> mouseLoc = new SimpleObjectProperty<>();
        selectedImage.setOnMousePressed(e -> mouseLoc.set(new Point2D(e.getX(), e.getY())));
        selectedImage.setOnMouseDragged(e -> {
            double deltaX = e.getX() - mouseLoc.get().getX();
            double deltaY = e.getY() - mouseLoc.get().getY();
            mouseLoc.set(new Point2D(e.getX(), e.getY()));
        });
        selectedImage.addEventFilter(MouseEvent.MOUSE_CLICKED, Event::consume);
        return selectedImage;
    }

    protected void manipulaNodo(Node node, String buttonPress) {
        final Posicao novaPosicao = new Posicao();
        //O comando setOnMouseEntered executa uma ação ao passar o mouse por
        //sobre o componente Node, no caso abaixo, altero o cursor do mouse
        //para uma mão.
        node.setOnMouseEntered(me -> {
            if (!me.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.HAND);
            }
            //O comando abaixo faz com que o componente node aumente sua
            //escala ao passar o mouse por sobre ele
            Timeline timeE = new Timeline();
            KeyValue kv1 = new KeyValue(node.scaleXProperty(), 1.1);
            KeyValue kv2 = new KeyValue(node.scaleYProperty(), 1.1);
            KeyFrame kf = new KeyFrame(Duration.millis(200), kv1, kv2);
            timeE.getKeyFrames().add(kf);
            timeE.play();
        });
        //O comando setOnMouseExited executa uma ação ao passar o mouse por
        //sobre o componente Node, no caso abaixo, altero o cursor do mouse
        //para o padrão novamente.
        node.setOnMouseExited(me -> {
            if (!me.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.DEFAULT);
            }
            //O comando abaixo faz com que o componente node volte a sua
            //escala padrão ao passar o mouse por sobre ele
            Timeline timeE = new Timeline();
            KeyValue kv1 = new KeyValue(node.scaleXProperty(), 1.0);
            KeyValue kv2 = new KeyValue(node.scaleYProperty(), 1.0);
            KeyFrame kf = new KeyFrame(Duration.millis(200), kv1, kv2);
            timeE.getKeyFrames().add(kf);
            timeE.play();
        });
        node.setOnMousePressed(me -> {
            node.getScene().setCursor(Cursor.MOVE);
            if (!me.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.DEFAULT);
            }
            novaPosicao.x = me.getX();
            novaPosicao.y = me.getY();

        });
        node.setOnMouseReleased(me -> {
            if (!me.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.DEFAULT);
            }
        });
        node.setOnMouseDragged(me -> {
            node.setLayoutX(node.getLayoutX() + me.getX() - novaPosicao.x);
            node.setLayoutY(node.getLayoutY() + me.getY() - novaPosicao.y);
        });

        node.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                System.out.println("Entrei ate aqui");
                if (buttonPress.equals("play")) {
                    System.out.println("entrei aqui");
                    Main.alternaTela("prop");
                }
            }
        });
    }

    private class Posicao {

        public double x;
        public double y;
    }

}