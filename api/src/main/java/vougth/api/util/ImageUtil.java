package vougth.api.util;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ImageUtil {
    public ImageUtil() {
    }

    public static byte[] compressImage(byte[] bytes, float imageQuality) throws IOException {

        if (imageQuality <= 0) {
            return bytes;
        } else {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("jpg");

            if (!imageWriters.hasNext()) {
                throw new IllegalStateException("Writers Not Found!!");
            }
            ImageWriter imageWriter = imageWriters.next();
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            imageWriter.setOutput(imageOutputStream);

            ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

            imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            imageWriteParam.setCompressionQuality(imageQuality);

            InputStream inputStream = new ByteArrayInputStream(bytes);
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            bufferedImage = removeAlphaChannel(bufferedImage);

            imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);

            inputStream.close();
            outputStream.close();
            imageOutputStream.close();
            imageWriter.dispose();

            bytes = outputStream.toByteArray();

            return compressImage(bytes, imageQuality);
        }

    }

    private static BufferedImage removeAlphaChannel(BufferedImage img) {
        if (!img.getColorModel().hasAlpha()) {
            return img;
        }

        BufferedImage target = createImage(img.getWidth(), img.getHeight(), false);
        Graphics2D g = target.createGraphics();
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
        g.drawImage(img, 0, 0, null);
        g.dispose();

        return target;
    }

    private static BufferedImage createImage(int width, int height, boolean hasAlpha) {
        return new BufferedImage(width, height,
                hasAlpha ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
    }
}