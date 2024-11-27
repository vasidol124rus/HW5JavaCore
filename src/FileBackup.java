import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//Написать функцию, создающую резервную копию всех файлов в директории(без поддиректорий) во вновь созданную папку ./backup
public class FileBackup {

    public static void backup() {
        File sourceDir = new File(".");
        File backupDir = new File("./backup");

        if (!backupDir.exists()) {
            backupDir.mkdir();
        }

        if (sourceDir.isDirectory()) {
            File[] files = sourceDir.listFiles();

            for (File file : files) {
                if (file.isFile()) {
                    try {
                        FileInputStream fis = new FileInputStream(file);
                        FileOutputStream fos = new FileOutputStream(new File(backupDir.getPath() + "/" + file.getName()));

                        byte[] buffer = new byte[1024];
                        int length;

                        while ((length = fis.read(buffer)) > 0) {
                            fos.write(buffer, 0, length);
                        }

                        fis.close();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("Backup completed successfully.");
        } else {
            System.out.println("Source directory does not exist.");
        }
    }


    public static void main(String[] args) throws IOException {
        backup();


//            Предположить, что числа в исходном массиве из 9 элементов имеют диапазон
//                    [0, 3], и представляют собой, например, состояния ячеек поля для игры в крестики-
//                    нолики, где 0 – это пустое поле, 1 – это поле с крестиком, 2 – это поле с ноликом,
//            3 – резервное значение. Такое предположение позволит хранить в одном числе
//            типа int всё поле 3х3. Записать в файл 9 значений так, чтобы они заняли три
//            байта.

        int[] ar2 = {0, 1, 2, 3, 0, 1, 2, 3, 0};

        FileOutputStream fos = new FileOutputStream("game.out");
        for (int b = 0; b < 3; b++) { // write to 3 bytes
            byte wr = 0;
            for (int v = 0; v < 3; v++) { // write by 3 values in each
                wr += (byte) (ar2[3 * b + v] << (v * 2));
            }
            fos.write(wr);
        }
        fos.flush();
        fos.close();
    }


}