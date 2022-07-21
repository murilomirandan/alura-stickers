import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    public void cria(InputStream inputStream, String fileName) throws IOException {
        // read image
        BufferedImage orgImage = ImageIO.read(inputStream);

        // create new image
        int width = orgImage.getWidth();
        int height = orgImage.getHeight();
        int newHeight = (int) Math.round(height * 1.2); // plus 20% of height

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(orgImage, 0, 0, null);

        // setup style (font and color)
        Font font = new Font("Impact", Font.PLAIN, 96);

        // write in the new image
        graphics.setColor(Color.YELLOW.darker());
        graphics.setFont(font);
        // String text = "Uma porta a menos";
        String text = "Hello, World!";

        TextLayout textLayout = new TextLayout(text, font, graphics.getFontRenderContext());
        // double textHeight = textLayout.getBounds().getHeight();
        double textWidth = textLayout.getBounds().getWidth();

        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(text, width / 2 - (int) textWidth / 2, height + ((newHeight - height) / 2));

        // save a new image to file
        File file = new File("saida");
        file.mkdir();

        fileName = "saida/" + fileName;
        ImageIO.write(newImage, "png", new File(fileName));
    }

    public static void main(String[] args) throws Exception {
        var geradora = new GeradoraDeFigurinhas();
        String fileImage = "entrada/miss-sunshine.jpg";
        InputStream inputStream = new FileInputStream(new File(fileImage));

        // String urlImage = "https://m.media-amazon.com/images/M/MV5BYjJiZjMzYzktNjU0NS00OTkxLWEwYzItYzdhYWJjN2QzMTRlL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@.jpg";
        // InputStream inputStream = new URL(urlImage).openStream();
        geradora.cria(inputStream, "little-miss-sunshine.png");
    }
}
