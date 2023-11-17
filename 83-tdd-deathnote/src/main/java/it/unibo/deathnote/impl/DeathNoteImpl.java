package it.unibo.deathnote.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {

    public static final String DEFAULT_CAUSE = "heart hattack";
    public static final String DEAFULT_DETAILS = "";
    private final Map<String, Death> deathNote = new LinkedHashMap<>();
    private String lastNameWritten;
    private long timeOfDeath;

    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > DeathNote.RULES.size()) {
            throw new IllegalArgumentException(
                    "The given rule number is smaller than 1 or larger than the number of rules");
        }
        return DeathNote.RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(String name) {
        if (name == null) {
            throw new NullPointerException("Passed null instead of a string as the name of the person.");
        }
        this.lastNameWritten = name;
        this.deathNote.put(name, new Death(DEFAULT_CAUSE, DEAFULT_DETAILS));
        this.timeOfDeath = System.currentTimeMillis();
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if (cause == null || deathNote.isEmpty()) {
            throw new IllegalStateException(
                    "Passed null or the death note is empty, need a valid string or to write or a new name before adding a cause.");
        }
        if (System.currentTimeMillis() > this.timeOfDeath + 40) {
            return false;
        }
        this.deathNote.get(this.lastNameWritten).setCause(cause);
        return true;
    }

    @Override
    public boolean writeDetails(String details) {
        if (details == null || deathNote.isEmpty()) {
            throw new IllegalStateException(
                    "Passed null or the death note is empty, need a valid string or to write or a new name before adding deatils.");
        }
        if (System.currentTimeMillis() > this.timeOfDeath + 6040l) {
            return false;
        }
        this.deathNote.get(this.lastNameWritten).setDetails(details);
        return true;
    }

    @Override
    public String getDeathCause(String name) {
        if (this.deathNote.get(name) == null) {
            throw new IllegalArgumentException("Name not in the death note need to add it first.");
        }
        return this.deathNote.get(name).getCause();
    }

    @Override
    public String getDeathDetails(String name) {
        if (this.deathNote.get(name) == null) {
            throw new IllegalArgumentException("Name not in the death note need to add it first.");
        }
        return this.deathNote.get(name).getDetails();
    }

    @Override
    public boolean isNameWritten(String name) {
        return this.deathNote.get(name) != null;
    }
}
