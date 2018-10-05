/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwControler;

import static SwControler.MainControl.mainPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * @author Adriano Valt <adrianovalt@gmail.com>
 */
public class Nodo {

    private String buttonPress;
    private int position;

    protected static ImageView criarItem(double x, double y, String caminho) throws FileNotFoundException {
        ImageView selectedImage = new ImageView();
        Image imageX = new Image(new FileInputStream(caminho));
        selectedImage.setImage(imageX);
        selectedImage.setLayoutX(x - 36);
        selectedImage.setLayoutY(y - 36);
        selectedImage.setFitHeight(72);
        selectedImage.setFitWidth(72);
        selectedImage.addEventFilter(MouseEvent.MOUSE_CLICKED, Event::consume);
        return selectedImage;
    }

    protected void manipulaItem(Node node) {
        final Posicao novaPosicao = new Posicao();

        //O comando setOnMouseEntered executa uma ação ao passar o mouse por
        //sobre o componente Node, no caso abaixo, altero o cursor do mouse
        //para uma mão.
        node.setOnMouseEntered(me -> {
            if (!me.isPrimaryButtonDown()) {
                if (!buttonPress.equals("delete")) {
                    node.getScene().setCursor(Cursor.HAND);
                } else {
                    node.getScene().setCursor(Cursor.CROSSHAIR);
                }
            }
            //O comando abaixo faz com que o componente node aumente sua
            //escala ao passar o mouse por sobre ele
            Timeline timeE = new Timeline();
            KeyValue kv1 = new KeyValue(node.scaleXProperty(), 1.1);
            KeyValue kv2 = new KeyValue(node.scaleYProperty(), 1.1);
            KeyFrame kf = new KeyFrame(Duration.millis(200), kv1, kv2);
            timeE.getKeyFrames().add(kf);
            timeE.play();
            setButtonPress(MainControl.getButtonPress());
            setPosition(mainPane.getChildren().indexOf(node));
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
            if (buttonPress.equals("line")) {
                if (MainControl.lastUnconnectedNode.get() == null) {
                    MainControl.lastUnconnectedNode.setValue(mainPane.getChildren().get(position));
                } else {
                    conectarItem(MainControl.lastUnconnectedNode.get(), mainPane.getChildren().get(position));
                    MainControl.lastUnconnectedNode.set(null);
                }
            }
            System.out.println("foi pressionado " + buttonPress);
            if (buttonPress.equals("delete")) {
                System.out.println("esse é o id do obj = " + mainPane.getChildren().get(0).getId());
                mainPane.getChildren().remove(position);
                return;
            }
            if (buttonPress.equals("play")) {
                Main.alternaTela("prop");
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
    }

    private void conectarItem(Node n1, Node n2) {
        if (n1.getParent() != n2.getParent()) {
            throw new IllegalArgumentException("Os nodos estão em containers diferentes");
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

    private class Posicao {

        public double x;
        public double y;
    }

    public void setButtonPress(String buttonPress) {
        this.buttonPress = buttonPress;
    }

    public void setPosition(int pos) {
        this.position = pos;
    }
}
