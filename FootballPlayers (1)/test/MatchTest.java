import MODEL.Match;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatchTest {

    @Test
    public void testConstructorAndGetters() {
        Match m = new Match(1, "2026-06-17", "Barcelona", "Friendly");
        assertEquals(1, m.getMatchID());
        assertEquals("2026-06-17", m.getDate());
        assertEquals("Barcelona", m.getOpponentTeam());
        assertEquals("Friendly", m.getMatchType());
    }

    @Test
    public void testSetters() {
        Match m = new Match();
        m.setDate("2026-07-01");
        m.setOpponentTeam("Real Madrid");
        m.setMatchType("League");

        assertEquals("2026-07-01", m.getDate());
        assertEquals("Real Madrid", m.getOpponentTeam());
        assertEquals("League", m.getMatchType());
    }
}