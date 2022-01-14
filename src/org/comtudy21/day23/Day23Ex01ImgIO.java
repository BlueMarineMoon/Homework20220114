package org.comtudy21.day23;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MyContentPanTest01 extends JPanel {
   JLabel label;

   public MyContentPanTest01() {
      label = new JLabel("우리는 하나다!");
      Font font = new Font("궁서체", Font.ITALIC, 48);
      label.setFont(font);
      this.add(label);
   }
}

class Airplan01 extends JPanel {
   Image img;
   String imagePath = "C:\\Users\\minib\\eclipse-workspace\\day23ThreadEx02\\src\\org\\comtudy21\\day23\\airplan01.png";
   BufferedImage bfImg;
   public int x = 300, y = 300;
   public JLabel imgLbl = null;

   public Airplan01() {
      // img = Toolkit.getDefaultToolkit().getImage(imagePath);
      // 이미지나 그래픽 관련 메소드는 라이프사이클에 의해서 자동 실행 된다.
      // paint(), repaint(), paintComponent()
//      try {
//         bfImg = ImageIO.read(this.getClass().getResource("airplan01.png"));
//      } catch (IOException e) {
//         e.printStackTrace();
//      }
      // ImageIcon imgIcon = new ImageIcon("src/org/comtudy21/day23/airplan01.png");
      ImageIcon imgIcon = new ImageIcon("images/airplan01.png");

      // ImageIcon imgIcon = new ImageIcon(getClass().getResource("airplan01.png"));
      // 이미지 아이콘은 이미지가 아니다.
      img = imgIcon.getImage();
      // 이미지 크기도 조정
      Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
      imgIcon = new ImageIcon(newImg);
      // JLabel()에 ImageIcon 객체 사용 가능.
      imgLbl = new JLabel(imgIcon);
      this.add(imgLbl);
   }

   // 일체유심조 - 하면된다!
   @Override
   protected void paintComponent(Graphics g) {
      // g.drawImage(img, x, y, 100, 100, this);
   }
}

class Airplan02 extends JPanel {
   String imagePath = "images/airplan02.png";
   Image img = null;
   ImageIcon imgIco = null;
   int x = 0, y = 0, w = 100, h = 100;

   public Airplan02() {
      imgIco = new ImageIcon(imagePath);
      img = imgIco.getImage();
      Image newImg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
      imgIco = new ImageIcon(newImg);
      add(new JLabel(imgIco));
   }
}

class MyCenterPan extends JPanel {
   int x1 = 350, y1 = 400, w1 = 100, h1 = 100;
   int x2 = 100, y2 = 0, w2 = 100, h2 = 100;
   public Airplan01 airplan01 = new Airplan01();
   public Airplan02 airplan02 = new Airplan02();

   public MyCenterPan() {
      setLayout(null);
      add(airplan01);
      add(airplan02);

      airplan02.setBounds(x2, y2, w2, h2);
      airplan01.setBounds(x1, y1, w1, h1);
   }

   public void airplan01MoveLeft() {
      x1 -= 10;
      airplan01.setBounds(x1, y1, w1, h1);
   }

   public void airplan01MoveRight() {
      x1 += 10;
      airplan01.setBounds(x1, y1, w1, h1);
   }
}

class MyContentPan extends JPanel {
   MyCenterPan centerPan = new MyCenterPan();
   JButton leftBtn = new JButton("Left");
   JButton rightBtn = new JButton("Right");

   public MyContentPan() {
      setBackground(Color.PINK);
      layoutComponent();
      actionEvetn();
   }

   private void actionEvetn() {
      leftBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // 버튼을 누르면 비행기가 화면의 좌측으로 이동한다.
            centerPan.airplan01.x = centerPan.airplan01.x - 50;
            centerPan.airplan01.imgLbl.setBounds(centerPan.airplan01.x, centerPan.airplan01.y, 100, 100);
            repaint(); // 해당 콤포넌트의 화면을 다시 그린다.
         }
      });
      rightBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // 버튼을 누르면 비행기가 화면의 우측으로 이동한다.
            centerPan.airplan01.x = centerPan.airplan01.x + 50;
            centerPan.airplan01.imgLbl.setBounds(centerPan.airplan01.x, centerPan.airplan01.y, 100, 100);
            repaint(); // 해당 콤포넌트의 화면을 다시 그린다.
         }
      });

      this.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_LEFT) {
               centerPan.airplan01.x = centerPan.airplan01.x - 30;
               centerPan.airplan01.imgLbl.setBounds(centerPan.airplan01.x, centerPan.airplan01.y, 100, 100);
               repaint();
            } else if (keyCode == KeyEvent.VK_RIGHT) {
               centerPan.airplan01.x = centerPan.airplan01.x + 30;
               centerPan.airplan01.imgLbl.setBounds(centerPan.airplan01.x, centerPan.airplan01.y, 100, 100);
               repaint();
            }

         }
      });
      // Focus를 읽을 수 있도록 설정 한다.
      this.setFocusable(true);
      this.requestFocus();
   }

   private void layoutComponent() {
      setLayout(new BorderLayout());
      this.add(BorderLayout.NORTH, new JLabel("미사일로 비행기 맞추기 게임 - 방향키로 비행기 조정"));
      this.add(centerPan);
      JPanel bottomPan = new JPanel(new FlowLayout());
      bottomPan.add(leftBtn);
      bottomPan.add(rightBtn);
      this.add(BorderLayout.SOUTH, bottomPan);
   }
}

public class Day23Ex01ImgIO extends JFrame {
   Container contentPan;

   public Day23Ex01ImgIO() {
      setTitle("이미지 불러오기 실습");
      setSize(800, 600);

      // contentPan = getContentPane();
      MyContentPan myContentPan = new MyContentPan();
      setContentPane(myContentPan);
   }

   public static void main(String[] args) {
      new Day23Ex01ImgIO().setVisible(true);
   }

}