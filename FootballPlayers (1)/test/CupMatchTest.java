import MODEL.CupMatch;
import org.junit.Test;
import static org.junit.Assert.*;

public class CupMatchTest {

    @Test
    public void testDefaultConstructorAndCupName() {
        CupMatch cm = new CupMatch();
        assertEquals(0, cm.getMatchID());
        assertEquals("", cm.getDate());
        assertEquals("", cm.getOpponentTeam());
        assertEquals("", cm.getMatchType());
        assertEquals("", cm.getCupName());
    }

    @Test
    public void testSetCupName() {
        CupMatch cm = new CupMatch();
        cm.setDate("2026-06-19");
        cm.setOpponentTeam("Bayern Munich");
        cm.setMatchType("Cup");
        cm.setCupName("FA Cup");

        assertEquals("2026-06-19", cm.getDate());
        assertEquals("Bayern Munich", cm.getOpponentTeam());
        assertEquals("Cup", cm.getMatchType());
        assertEquals("FA Cup", cm.getCupName());
    }
}