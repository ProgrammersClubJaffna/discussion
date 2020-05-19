package com.miranda.automata;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MouseMover
{
  private Frame mainFrame;
  public static final int INTERVAL = 120000;
  public static final int MAX_Y = 400;
  public static final int MAX_X = 400;
  private Point position;

  public Point getPosition()
  {
    return this.position;
  }

  public void setPosition(Point position) {
    this.position = position;
  }

  public static void main(String[] args) throws Exception {
    MouseMover mouseMover = new MouseMover();
    mouseMover.execute(mouseMover);
  }

  private void execute(MouseMover mouseMover) {
    this.mainFrame = new Frame("Obus");
    this.mainFrame.setSize(200, 100);
    this.mainFrame.setLayout(new GridLayout(3, 1));
    this.mainFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent windowEvent) {
        System.exit(0);
      }
    });
    this.mainFrame.setVisible(true);
    try
    {
      mouseMover(mouseMover);
    } catch (AWTException e) {
      e.printStackTrace();
    }
  }

  private void mouseMover(MouseMover mouseMover)
    throws AWTException
  {
    Robot robot = new Robot();
    Random random = new Random();
    while (true)
      try {
        if (MouseInfo.getPointerInfo() != null) {
          Point currentPosition = MouseInfo.getPointerInfo()
            .getLocation();

          if (currentPosition.equals(mouseMover.getPosition())) {
            robot.mouseMove(random.nextInt(400), 
              random.nextInt(400));
          }

          mouseMover.setPosition(currentPosition);
          try {
            Thread.sleep(120000L); } catch (InterruptedException e) {
          }
          e.printStackTrace(); continue;
        }

      }
      catch (HeadlessException e)
      {
        e.printStackTrace();
      }
  }
}
