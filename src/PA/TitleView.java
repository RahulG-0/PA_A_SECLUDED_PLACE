package PA;

import javax.swing.*;

import javafx.scene.effect.ColorAdjust;

// import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
public class TitleView extends JFrame{

    public JLabel loadingScreenImage;
    public JFrame loadingScreen;

    public JLabel title;

    public JButton newGame= new JButton("New Game");
    public JButton continueGame= new JButton("Continue Game");
    public JButton options= new JButton("Options");
    public JButton quit = new JButton("Exit Game");

    public JPanel menu;

    public TitleView(Model aGame)
    {
        super();
        LayoutView();
    }

    public void LayoutView(){


        loadingScreenImage = new JLabel();
        loadingScreenImage.setBounds(0, 0, 1920, 1080);

        loadingScreenImage.setIcon(new ImageIcon("C:\\Users\\rocki\\Documents\\javaSchool\\src\\res\\Safeimagekit-resized-img.png"));
       

        title =  new JLabel();
        title.setText("A Secluded Place");
        title.setBounds(450,250,1200,100);
        // title.setEditable(false);
        title.setForeground(new Color(139, 0, 0));
        // title.setBackground(new Color(0,0,0,70));
        title.setBorder(null);

        File font_file = new File("C:\\Users\\rocki\\Documents\\javaSchool\\src\\res\\HelpMe.ttf");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
            Font sizedFont = font.deriveFont(100f);
            title.setFont(sizedFont);
        } catch (FontFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        menu = new JPanel();

        // menu.setLayout(new GridLayout(4,1));
        menu.setOpaque(true);
        // newGame.setOpaque(false);

        
        
        
        newGame.setBackground(Color.darkGray);
        newGame.setMargin(new Insets(10,10,10,10));
        newGame.setBounds(800,500,100,100);

        menu.add(newGame);
        menu.add(continueGame);
        menu.add(options);
        menu.add(quit);

        menu.setBounds(800,500,300,550);


        this.setSize(1920,1080);
        this.setLayout(null);
        this.add(title);
        this.add(menu);
        this.add(loadingScreenImage);
        this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }   

}