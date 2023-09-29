import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {

    public static void main(String[] args) {
        if (args.length == 0) {
            printHelp();
            return;
        }

        String command = args[0];

        switch (command) {
            case "help":
                printHelp();
                break;
            case "mem":
                if (args.length < 4) {
                    System.out.println("Недостаточно аргументов для команды 'mem'.");
                    break;
                }
                String imagePath = args[1];
                String text = args[2];
                String position = args[3];
                addTextToImage(imagePath, text, position);
                break;
            default:
                System.out.println("Неизвестная команда. Используйте 'mem' или 'help'.");
        }
    }

    private static void addTextToImage(String inputImagePath, String text, String position) {
        try {
            File inputFile = new File(inputImagePath);
            BufferedImage image = ImageIO.read(inputFile);

            Graphics2D g2d = image.createGraphics();
            g2d.setPaint(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 20));

            // Определение позиции текста
            int x = 0;
            int y = 0;
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            switch (position.toLowerCase()) {
                case "center":
                    x = (imageWidth - g2d.getFontMetrics().stringWidth(text)) / 2;
                    y = (imageHeight - g2d.getFontMetrics().getHeight()) / 2;
                    break;
                case "top":
                    x = (imageWidth - g2d.getFontMetrics().stringWidth(text)) / 2;
                    y = g2d.getFontMetrics().getHeight();
                    break;
                case "bottom":
                    x = (imageWidth - g2d.getFontMetrics().stringWidth(text)) / 2;
                    y = imageHeight - g2d.getFontMetrics().getHeight();
                    break;
                default:
                    System.out.println("Неподдерживаемая позиция текста. Используйте 'center', 'top' или 'bottom'.");
                    return;
            }

            g2d.drawString(text, x, y);
            g2d.dispose();

            String outputImagePath = generateOutputPath(inputImagePath);
            File outputFile = new File(outputImagePath);
            ImageIO.write(image, "png", outputFile);

            System.out.println("Текст успешно добавлен к изображению и сохранен в " + outputImagePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при обработке изображения.");
        }
    }

    private static String generateOutputPath(String inputImagePath) {
        int dotIndex = inputImagePath.lastIndexOf('.');
        String name = inputImagePath.substring(0, dotIndex);
        String extension = inputImagePath.substring(dotIndex);
        return name + "_with_text" + extension;
    }

    private static void printHelp() {
        System.out.println("Использование программы:");
        System.out.println("java -jar application.jar help");
        System.out.println("java -jar application.jar mem <путь_к_изображению> '<текст>' <позиция: center, top, bottom>");
    }
}
