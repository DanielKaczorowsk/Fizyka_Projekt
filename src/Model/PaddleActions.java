package Model;
import Object.Paddle;
import javax.swing.*;
import java.awt.event.*;

public class PaddleActions implements MouseMotionListener {
    private Paddle paddle;

    // Konstruktor przyjmuje referencję do paletki, na której gra operuje
    public PaddleActions(Paddle paddle) {
        this.paddle = paddle;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Centrujemy paletkę na kursorze myszy (odejmując połowę jej szerokości)
        double nowaPozycjaX = e.getX() - (paddle.getWidth() / 2.0);

        // Przypisujemy nową pozycję X
        paddle.setX(nowaPozycjaX);

        // Opcjonalnie: blokujemy pozycję Y, żeby paletka była zawsze na dole ekranu
        // paddle.setY(520);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Ta metoda jest wymagana przez interfejs (ruch z wciśniętym przyciskiem myszy)
        // Możemy tu wywołać to samo, żeby gra działała też podczas przeciągania
        mouseMoved(e);
    }
}
