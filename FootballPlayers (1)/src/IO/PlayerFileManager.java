package IO;

import MODEL.Player;
import MODEL.RegularPlayer;
import MODEL.StarPlayer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PlayerFileManager {

    // Ghi mảng player ra file, trả về không cần gì vì chỉ đọc dữ liệu có sẵn
    public void saveToFile(String fileName, Player[] arr, int count) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < count; i++) {
                bw.write(arr[i].toFileLine());
                bw.newLine();
            }
            System.out.println("Saved " + count + " player(s) to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // Đọc file, nạp trực tiếp vào mảng arr được truyền vào, trả về số lượng player đọc được (count mới)
    public int loadFromFile(String fileName, Player[] arr) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                try {
                    Player p = parsePlayerLine(line);
                    if (!isDuplicateId(arr, count, p.getId())) {
                        arr[count] = p;
                        count++;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid line skipped: " + e.getMessage());
                }
            }
            System.out.println("Loaded " + count + " player(s) from " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return count;
    }

    private boolean isDuplicateId(Player[] arr, int count, String id) {
        for (int i = 0; i < count; i++) {
            if (arr[i].getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }

    private Player parsePlayerLine(String line) {
        String[] f = line.split("\\|", -1);
        if (f.length < 9) throw new IllegalArgumentException("not enough fields");
        Player p;
        if (f[0].equalsIgnoreCase("Regular player")) p = new RegularPlayer();
        else if (f[0].equalsIgnoreCase("Star player")) p = new StarPlayer();
        else throw new IllegalArgumentException("unknown type: " + f[0]);
        p.setType(f[0]); p.setId(f[1]); p.setName(f[2]); p.setAge(Integer.parseInt(f[3]));
        p.setNational(f[4]); p.setPosition(f[5]); p.setNumber(Integer.parseInt(f[6]));
        p.setSalary(Double.parseDouble(f[7])); p.setStatus(f[8]);
        if (f.length > 9 && !f[9].isEmpty()) p.setAbsentDays(Integer.parseInt(f[9]));
        if (f.length > 10 && !f[10].isEmpty()) p.setGoalsScored(Integer.parseInt(f[10]));
        return p;
    }
}
