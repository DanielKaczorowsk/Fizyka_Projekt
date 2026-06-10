import Model.PaddleActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Object.Paddle;

public class GameWindow extends JPanel implements ActionListener {
    private final Board board;

    public GameWindow() {
        board = new Board();
        board.getBall().setX(350);
        board.getBall().setY(300.0);
        board.getBall().setRadius(10);
        board.getBall().setSpeedY(5);
        board.getBall().setRadius(10);
        board.getBall().setTotalSpeed(4.5);
        board.getBall().setSpeedY(4);
        board.getBall().setSpeedX(2);

        board.getPaddle().setX(350);
        board.getPaddle().setY(550);
        board.getPaddle().setWidth(100);
        board.getPaddle().setHeight(20);

        Timer timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Czyszczenie ekranu
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);

        // Rysowanie paletki
        g.setColor(Color.WHITE);
        g.fillRect((int)board.getPaddle().getX(), (int)board.getPaddle().getY(),
                (int)board.getPaddle().getWidth(), (int)board.getPaddle().getHeight());

        // Rysowanie piłki
        g.setColor(Color.RED);
        int r = (int)board.getBall().getRadius();
        g.fillOval((int)(board.getBall().getX() - r), (int)(board.getBall().getY() - r), r * 2, r * 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.update(); // Aktualizacja logiki fizyki
        repaint();      // Wywołanie ponownego rysowania ekranu
    }

    public Board getBoard() {
        return this.board;
    }
    void main() {
        JFrame frame = new JFrame("Arkanoid / Pong");
        GameWindow game = new GameWindow();
        Paddle activePaddle = game.getBoard().getPaddle();
        PaddleActions paddleActions = new PaddleActions(activePaddle);
        frame.addMouseMotionListener(paddleActions);
        frame.add(game);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}