package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lop dung chung cho toan bo File I/O trong du an.
 * Cac module khac (MatchList, ClubManager, TrainingManager, ...) chi can goi
 * writeLines()/readLines() thay vi tu viet lai BufferedReader/BufferedWriter.
 *
 * Moi module van tu lo phan "hieu du lieu" cua rieng minh
 * (VD Match.toFileLine() / parseMatchLine()), FileIOhelper chi lo phan
 * co che doc/ghi file thuan tuy - dung dinh huong Decomposition.
 */
public class FileIOhelper {

    // thu muc co dinh de luu toan bo file du lieu cua du an, nam canh noi chay chuong trinh
    private static final String DATA_FOLDER = "data";

    private FileIOhelper() {
        // utility class, khong can khoi tao
    }

    // ghep ten file voi thu muc data/, tu tao thu muc neu chua co
    public static String resolvePath(String fileName) {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return DATA_FOLDER + File.separator + fileName;
    }

    // ghi danh sach dong text ra file trong thu muc data/
    // nem IOException ra ngoai de tung module tu quyet dinh thong bao loi the nao cho phu hop
    public static void writeLines(String fileName, List<String> lines) throws IOException {
        String path = resolvePath(fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    // doc toan bo dong text tu file trong thu muc data/, tra ve danh sach dong tho (chua parse)
    public static List<String> readLines(String fileName) throws IOException {
        String path = resolvePath(fileName);
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
    public static boolean fileExists(String fileName) {
    File file = new File(resolvePath(fileName));
    return file.exists();
}
public static boolean deleteFile(String fileName) {
    File file = new File(resolvePath(fileName));
    return file.exists() && file.delete();
}
}
