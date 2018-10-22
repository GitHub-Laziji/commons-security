package org.laziji.commons.security.captcha;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ImageCaptchaGenerator {


    public static String toBase64(Captcha captcha) throws IOException {
        return toBase64(captcha.getValue(), 100, 40);
    }

    public static String toBase64(Captcha captcha, int w, int h) throws IOException {
        return toBase64(captcha.getValue(), w, h);
    }

    public static String toBase64(String code) throws IOException {
        return toBase64(code, 100, 40);
    }

    public static String toBase64(String code, int w, int h) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        writeStream(os, code, w, h);
        return Base64.encodeBase64String(os.toByteArray());
    }

    public static void writeStream(OutputStream os, String code, int w, int h) throws IOException {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, w, h);
        Color c = randomColor(200, 250);
        g2.setColor(c);
        g2.fillRect(0, 2, w, h - 4);
        g2.setColor(randomColor(160, 200));
        for (int i = 0; i < 20; i++) {
            int x = RandomUtils.nextInt(0, w - 1);
            int y = RandomUtils.nextInt(0, h - 1);
            int xl = RandomUtils.nextInt(1, 7);
            int yl = RandomUtils.nextInt(1, 13) + 1;
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);
        }
        float yawpRate = 0.05f;
        int area = (int) (yawpRate * w * h);
        for (int i = 0; i < area; i++) {
            int x = RandomUtils.nextInt(0, w);
            int y = RandomUtils.nextInt(0, h);
            int rgb = randomIntColor();
            image.setRGB(x, y, rgb);
        }
        shear(g2, w, h, c);
        g2.setColor(randomColor(100, 160));
        int fontSize = h - 4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        g2.setFont(font);
        char[] chars = code.toCharArray();
        int length = code.length();
        int d = w/length;
        for (int i = 0; i < length; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * new Random().nextDouble() * (RandomUtils.nextBoolean() ? 1 : -1), d * i + (fontSize >> 1), h >> 1);
            g2.setTransform(affine);
            g2.drawChars(chars, i, 1, ((w - 10) / length) * i + 5, h / 2 + fontSize / 2 - 10);
        }
        g2.dispose();
        ImageIO.write(image, "jpg", os);
    }

    private static Color randomColor(int c1, int c2) {
        return new Color(RandomUtils.nextInt(c1, c2), RandomUtils.nextInt(c1, c2), RandomUtils.nextInt(c1, c2));
    }

    private static int randomIntColor() {
        int color = 0;
        for (int i = 0; i < 3; i++) {
            int r = RandomUtils.nextInt(0, 256);
            color <<= 8;
            color |= r;
        }
        return color;
    }

    private static void shear(Graphics g, int w, int h, Color color) {
        int period = RandomUtils.nextInt(10, 50);
        int frames = 20;
        int phase = 7;
        g.setColor(color);
        for (int i = 0; i < w; i++) {
            int d = (int) (period / 2 * Math.sin((double) i / period + 6.2831853071795862D * phase / frames));
            g.copyArea(i, 0, 1, h, 0, d);
            g.drawLine(i, d, i, 0);
            g.drawLine(i, d + h, i, h);
        }
    }
}