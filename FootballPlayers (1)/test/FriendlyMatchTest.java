import MODEL.FriendlyMatch;
import org.junit.Test;
import static org.junit.Assert.*;

public class FriendlyMatchTest {

    @Test
    public void testDefaultConstructorAndFriendlyReason() {
        FriendlyMatch fm = new FriendlyMatch();
        assertEquals(0, fm.getMatchID());
        assertEquals("", fm.getDate());
        assertEquals("", fm.getOpponentTeam());
        assertEquals("", fm.getMatchType());
        assertEquals("", fm.getFriendlyReason());
    }

    @Test
    public void testSetFriendlyReason() {
        FriendlyMatch fm = new FriendlyMatch();
        fm.setDate("2026-06-18");
        fm.setOpponentTeam("Juventus");
        fm.setMatchType("Friendly");
        fm.setFriendlyReason("Preseason training");

        assertEquals("2026-06-18", fm.getDate());
        assertEquals("Juventus", fm.getOpponentTeam());
        assertEquals("Friendly", fm.getMatchType());
        assertEquals("Preseason training", fm.getFriendlyReason());
    }
}