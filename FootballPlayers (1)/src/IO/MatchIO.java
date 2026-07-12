package IO;

import MODEL.CupMatch;
import MODEL.FriendlyMatch;
import MODEL.LeagueMatch;
import MODEL.Match;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper riêng cho phần MatchList, dùng để lưu/đọc dữ liệu trận đấu.
 */
public class MatchIO {

    private static final String DATA_FOLDER = "data";

    private MatchIO() {
        // utility class
    }

    public static String resolvePath(String fileName) {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return DATA_FOLDER + File.separator + fileName;
    }

    public static void saveMatches(List<Match> matches, String fileName) throws IOException {
        String path = resolvePath(fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Match match : matches) {
                bw.write(match.toFileLine());
                bw.newLine();
            }
        }
    }

    public static List<Match> loadMatches(String fileName) throws IOException {
        String path = resolvePath(fileName);
        List<Match> matches = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                matches.add(parseMatchLine(line));
            }
        }
        return matches;
    }

    private static Match parseMatchLine(String line) {
        String[] p = line.split("\\|", -1);
        if (p.length < 5) {
            throw new IllegalArgumentException("not enough fields");
        }

        String type = p[0];
        int id = Integer.parseInt(p[1]);
        LocalDate date = LocalDate.parse(p[2]);
        String opponentTeam = p[3];
        String stadium = p[4];

        Match match;
        switch (type) {
            case "Friendly":
                FriendlyMatch fm = new FriendlyMatch();
                if (p.length > 5) {
                    fm.setFriendlyReason(p[5]);
                }
                match = fm;
                break;
            case "League":
                LeagueMatch lm = new LeagueMatch();
                if (p.length > 5) {
                    lm.setLeagueName(p[5]);
                }
                match = lm;
                break;
            case "Cup":
                CupMatch cm = new CupMatch();
                if (p.length > 5) {
                    cm.setCupName(p[5]);
                }
                match = cm;
                break;
            default:
                throw new IllegalArgumentException("unknown match type: " + type);
        }

        match.setMatchType(type);
        match.setMatchID(id);
        match.setDate(date);
        match.setOpponentTeam(opponentTeam);
        match.setStadium(stadium);
        return match;
    }
}