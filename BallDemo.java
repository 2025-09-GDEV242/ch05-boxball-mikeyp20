import java.awt.Color;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Patterson 
 * @version 10/27/25
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Box box;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     * 
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        box=new Box (100,100,500,400, myCanvas);
        box.draw();
        
    }

    /**
     * boxBounce - simulate 5-50 balls bouncing within a box
     * 
     * @param numOfBalls number of balls to simulate bouncing, clamped between 5-50. 
     */
    public void boxBounce(int b)
    {
        if(b < 5) b =5;
        if (b > 50) b = 50;
        
        myCanvas.setVisible(true);
        myCanvas.erase();
        
        int boxLeft = 80;
        int boxTop = 60;
        int boxWidth = 420;
        int boxHeight = 300;
        int borderThickness = 4;
        
        Box theBox = new Box(boxLeft,boxTop,boxWidth,boxHeight,myCanvas);
        theBox.draw();
        
        int right = theBox.getRightWall();
        int top =theBox.getTopWall();
        int left = theBox.getLeftWall();
        int bottom = theBox.getBottomWall();
        
        java.util.ArrayList<BoxBall>balls = new java.util.ArrayList<>();
        java.util.Random rng = new java.util.Random();
        
        for(int i =0; i<b; i = i+1) {
            int diameter = 15 + rng.nextInt(16);
            int startX = left + rng.nextInt(Math.max(1,(right - left)-diameter));
            int startY = top + rng.nextInt(Math.max(1,(bottom -top)-diameter));
            java.awt.Color a = new java.awt.Color(rng.nextInt(201),rng.nextInt(201),rng.nextInt(201));
            BoxBall demo = new BoxBall(startX,startY,diameter,a,theBox,myCanvas);
            balls.add(demo);
        }
        for (int frame = 0; frame <5000; frame++) {
            myCanvas.wait(50);
            for(BoxBall demo: balls) {
                demo.move();
            }
        }
        myCanvas.wait(10);
    }
    
    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
