package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static java.lang.Thread.sleep;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private DeathNote deathNote;
    private static final String MARCO = "Marco";
    private static final String PAOLO = "Paolo";

    @BeforeEach
    public void setUp() {
        this.deathNote = new DeathNoteImpl();
    }

    @Test
    public void TestGetRule() {
        try {
            deathNote.getRule(0);
            deathNote.getRule(-1);
            Assertions.fail("Rule smaller than 1 or larger than number of rules didn't thrown an exception.");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    public void TestEmptyRule() {
        for (String rule : DeathNote.RULES) {
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
    }

    @Test
    public void TestHumanDeath() {
        assertFalse(deathNote.isNameWritten(MARCO));
        deathNote.writeName(MARCO);
        assertTrue(deathNote.isNameWritten(MARCO));
        assertFalse(deathNote.isNameWritten(PAOLO));
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test
    public void TestDeathCause() throws InterruptedException {
        try {
            deathNote.writeDeathCause("infarto");
            Assertions.fail("Writing death cause before writing name didn't thrown an exception");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        deathNote.writeName(PAOLO);
        assertEquals(DeathNoteImpl.DEFAULT_CAUSE, deathNote.getDeathCause(PAOLO));
        deathNote.writeName(MARCO);
        assertTrue(deathNote.writeDeathCause("karting accident"));
        assertEquals("karting accident", deathNote.getDeathCause(MARCO));
        sleep(100);
        assertFalse(deathNote.writeDeathCause("sparato"));
        assertEquals("karting accident", deathNote.getDeathCause(MARCO));
    }

    @Test
    public void TestTimeElapsed() throws InterruptedException {
        try {
            deathNote.writeDetails("infarto");
            Assertions.fail("Writing death cause before writing name didn't thrown an exception");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        deathNote.writeName(MARCO);
        assertTrue(deathNote.getDeathDetails(MARCO).isBlank());
        assertTrue(deathNote.writeDetails("ran for too long"));
        assertEquals("ran for too long", deathNote.getDeathDetails(MARCO));
        deathNote.writeName(PAOLO);
        sleep(6100l);
        assertFalse(deathNote.writeDetails("infarto"));
        assertTrue(deathNote.getDeathDetails(PAOLO).isBlank());
    }
}
