import MODEL.LeagueMatch;
import org.junit.Test;
import static org.junit.Assert.*;

public class LeagueMatchTest {

    @Test
    public void testDefaultConstructorAndLeagueName() {
        LeagueMatch lm = new LeagueMatch();
        assertEquals(0, lm.getMatchID());
        assertEquals("", lm.getDate());
        assertEquals("", lm.getOpponentTeam());
        assertEquals("", lm.getMatchType());
        assertEquals("", lm.getLeagueName());
    }

    @Test
    public void testSetLeagueName() {
        LeagueMatch lm = new LeagueMatch();
        lm.setDate("2026-06-18");
        lm.setOpponentTeam("PSG");
        lm.setMatchType("League");
        lm.setLeagueName("Champions League");

        assertEquals("2026-06-18", lm.getDate());
        assertEquals("PSG", lm.getOpponentTeam());
        assertEquals("League", lm.getMatchType());
        assertEquals("Champions League", lm.getLeagueName());
    }
}