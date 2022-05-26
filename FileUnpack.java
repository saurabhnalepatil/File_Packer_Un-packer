import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUnPackBackend {
    // FileOutputStream outstream = null;

    public FileUnPackBackend(String src) throws Exception {
        unpck(src);
    }

    public void unpck(String filePath) throws Exception {
        try {
            FileInputStream instream = new FileInputStream(filePath);

            byte header[] = new byte[100];
            int length = 0;

            byte Magic[] = new byte[12];
            instream.read(Magic, 0, Magic.length);

            String Magicstr = new String(Magic);

            if (!Magicstr.equals("Marvellous11")) {
                throw new InvalidFileException("Invalid Packed File format");
            }

            while ((length = instream.read(header, 0, 100)) > 0) {
                String str = new String(header);

                String ext = str.substring(str.lastIndexOf("\\"));
                ext = ext.substring(1);

                String[] words = ext.split("\\s");

                String fileName = words[0];
                System.out.println(fileName);
                int size = Integer.parseInt(words[1]);
                System.out.println(size);
                byte[] arr = new byte[size];
                instream.read(arr, 0, size);
                FileOutputStream fout = new FileOutputStream((fileName));
                fout.write(arr, 0, size);
            }
        } catch (InvalidFileException obj) {
            throw new InvalidFileException("Invalid Packed File format");
        } catch (Exception e) {

        }
    }
}