package SERVICES;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper doc/ghi file text, chi phuc vu rieng cho phan Match (MatchList).
 * Khong dung chung cho cac module khac (Player, Training...) de tranh
 * anh huong toi phan viec cua thanh vien khac trong nhom.
 */
class MatchFileIOHelper {

    // thu muc co dinh de luu file du lieu cua Match, nam canh noi chay chuong trinh
    private static final String DATA_FOLDER = "data";

    private MatchFileIOHelper() {
        // utility class, khong can khoi tao
    }

    // ghep ten file voi thu muc data/, tu tao thu muc neu chua co
    static String resolvePath(String fileName) {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return DATA_FOLDER + File.separator + fileName;
    }

    // ghi danh sach dong text ra file trong thu muc data/
    static void writeLines(String fileName, List<String> lines) throws IOException {
        String path = resolvePath(fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    // doc toan bo dong text tu file trong thu muc data/, tra ve danh sach dong tho (chua parse)
    static List<String> readLines(String fileName) throws IOException {
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
}
