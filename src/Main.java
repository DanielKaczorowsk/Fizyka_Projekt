import Model.PaddleActions;
import Object.Paddle;
import javax.swing.*;

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
