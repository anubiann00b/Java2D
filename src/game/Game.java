package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Game extends JFrame implements MouseListener, KeyListener, Runnable {
    
    public static final int SCREEN_WIDTH = 640;
    public static final int SCREEN_HEIGHT = 480;
    public static final BufferedImage offscreen = new BufferedImage(SCREEN_WIDTH ,SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    
    boolean keyPressed;
    int x;
    int y;
    
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public Game() {
        x = 0;
        y = 0;
        setVisible(true);
        setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Game");
        addKeyListener(this);
        addMouseListener(this);
    }
    
    public void start() {
        new Thread(this).start();
    }
    
    @Override
    public void run() {
        while(true) {
            //update();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                System.out.println("o.0 " + e);
            }
        }
    }
    
    public void update() {
        if (keyPressed) {
            x++;
            y++;
        }
    }
    
    @Override
    public void paint(Graphics g) {
        update();
        Graphics s = offscreen.getGraphics();
        
        s.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        
        s.setColor(Color.red);
        s.fillRect(x/10, y/10, 32, 32);
        
        g.drawImage(offscreen, 0, 0, this);
        
        repaint();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            keyPressed = true;
    }

    @Override public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            keyPressed = false;
    }
    
    @Override public void keyTyped(KeyEvent e) { }
    
    @Override public void mouseClicked(MouseEvent e) { }
    @Override public void mousePressed(MouseEvent e) { }
    @Override public void mouseReleased(MouseEvent e) { }
    @Override public void mouseEntered(MouseEvent e) { }
    @Override public void mouseExited(MouseEvent e) { }
}
