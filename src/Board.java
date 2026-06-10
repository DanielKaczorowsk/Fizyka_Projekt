import Object.Ball;
import Object.Paddle;
public class Board
{
    private final Ball ball;
    private final Paddle paddle;

    public Board()
    {
        this.ball = new Ball();
        this.paddle = new Paddle();
    }
    public Ball getBall()
    {
        return this.ball;
    }
    public Paddle getPaddle()
    {
        return this.paddle;
    }
    /*
    * Metoda kalkulująca punkt uderzenia relative do środka paletki (-1 do 1)
    * @param ball
    * @param paddle
    * */
    private double hitPointCalculate(Ball ball, Paddle paddle)
    {
        double calculate = (ball.getX() - (paddle.getX() + paddle.getWidth() / 2.0)) / (paddle.getWidth() / 2.0);
        return Math.max(-1.0, Math.min(1.0, calculate));
    }
    /*
    * Pomocnicza metoda do wykrywania kolizji (AABB z uwzględnieniem promienia)
    * @param ball
    * @param paddle
    * @return true przy kolizji bądź false*/
    private boolean checkCollision(Ball ball, Paddle paddle) {
        return ball.getX() + ball.getRadius() >= paddle.getX() &&
                ball.getX() - ball.getRadius() <= paddle.getX() + paddle.getWidth() &&
                ball.getY() + ball.getRadius() >= paddle.getY() &&
                ball.getY() - ball.getRadius() <= paddle.getY() + paddle.getHeight();
    }
    /*
     * Metoda Gry
     */
    public void update() {
        // Ruch piłki
        ball.setX(ball.getX() + ball.getSpeedX());
        ball.setY(ball.getY() + ball.getSpeedY());

        // Kolizja ze ścianami bocznymi (odbicie)
        if (ball.getX() - ball.getRadius() < 0) {
            ball.setX(ball.getRadius()); // WYPCHNIĘCIE: ustawiamy piłkę idealnie przy ścianie
            ball.setSpeedX(-ball.getSpeedX());
        }
        // Kolizja z prawą ścianą (zakładając szerokość 800)
        else if (ball.getX() + ball.getRadius() > 800) {
            ball.setX(800 - ball.getRadius()); // WYPCHNIĘCIE
            ball.setSpeedX(-ball.getSpeedX());
        }

    // Kolizja z sufitem
        if (ball.getY() - ball.getRadius() < 0) {
            ball.setY(ball.getRadius()); // WYPCHNIĘCIE
            ball.setSpeedY(-ball.getSpeedY());
        }

        // Kolizja z paletką
        if (checkCollision(ball, paddle)) {
            double hitPoint = hitPointCalculate(this.ball, this.paddle);
            double maxAngle = Math.PI / 3;
            double angle = hitPoint * maxAngle;
            this.ball.setSpeedX(this.ball.getTotalSpeed() * Math.sin(angle));
            this.ball.setSpeedY(-this.ball.getTotalSpeed() * Math.cos(angle));
            this.ball.setY(this.paddle.getY() - this.ball.getRadius());
        }
    }
}
