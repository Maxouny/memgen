1)Для работы приложения необходимо сначала скомпилировать программу
  -Необходимо находится в дериктории самой программы src/main/java(там где находится ImageProcessor) (напрмер D:\workDir\memgen\src\main\java )
  -Прописать в консоль команду javac -encoding UTF-8 ImageProcessor.java
2)После того как появится ImgageProcessor.class прописываем команду java ImageProcessor help для отображения команд программы
*Для добавления тескста к картинке, сама картинка должне находится в дериктории вместе с программой
3)Прописываем команду java ImageProcessor mem ./picture.png 'hello world' top , где ./picture.png - путь до изображения, 'hello world' - текст который хотим добавить, top - местоположение текста на картинке 
