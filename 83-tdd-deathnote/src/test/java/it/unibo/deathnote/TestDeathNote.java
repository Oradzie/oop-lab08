package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.channels.spi.SelectorProvider;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private DeathNote deathNote;

    @BeforeEach
    public void setUp() {
        this.deathNote = new DeathNoteImpl("Mario", "Sparato", "Buco in testa");
    }

    @Test
    public void TestGetRule() {
        try {
            deathNote.getRule(0);
            deathNote.getRule(-1);
            Assertions.fail("Rule smaller than 1 or larger than number of rules didn't thrown an exception.");
        } catch (IllegalArgumentException e) {
            assertEquals("The given rule number is smaller than 1 or larger than the number of rules", e.getMessage());
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
        final String name = "Luca";
        final String wrongName = "Marco";
        assertFalse(deathNote.isNameWritten(name));
        deathNote.writeName(name);
        assertTrue(deathNote.isNameWritten(name));
        assertFalse(deathNote.isNameWritten(wrongName));
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test
    public void TestDeathCause() {
        try {
            final String name = "Luca";
            final String cause = "infarto";
            deathNote.writeDeathCause(cause);
            deathNote.writeName(name);
            Assertions.fail("Writing death cause before writing name didn't thrown an exception");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        } finally {
            deathNote.writeName("Paolo");
            assertEquals("Heart attack", deathNote.getDeathCause("Paolo"));
            deathNote.writeName("Giovanni");
            assertTrue(deathNote.writeDeathCause("karting accident"));
            assertEquals("karting accident", deathNote.getDeathCause("Giovanni"));
            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {

            }
        }

    }
}