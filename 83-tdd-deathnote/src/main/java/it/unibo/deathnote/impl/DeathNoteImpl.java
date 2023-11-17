package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {

    public static final String DEFAULT_CAUSE = "heart hattack";
    private final Map<String, Pair> deathNote = new HashMap<>();
    private String lastNameWritten;
    private long timeWrittenName;
    private long timeWrittenCause;

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
            throw new NullPointerException("Error");
        }
        this.lastNameWritten = name;
        this.deathNote.put(name, new Pair(DEFAULT_CAUSE, ""));
        this.timeWrittenName = System.currentTimeMillis();
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if (cause == null || deathNote.isEmpty()) {
            throw new IllegalStateException("Error");
        }
        if (System.currentTimeMillis() - this.timeWrittenName > 40) {
            return false;
        }
        this.timeWrittenCause = System.currentTimeMillis();
        return this.deathNote.get(this.lastNameWritten).setCause(cause);
    }

    @Override
    public boolean writeDetails(String details) {
        if (details == null || deathNote.isEmpty()) {
            throw new IllegalStateException("Error");
        }
        if (System.currentTimeMillis() - this.timeWrittenCause > 6040) {
            return false;
        }
        return this.deathNote.get(this.lastNameWritten).setDetails(details);
    }

    @Override
    public String getDeathCause(String name) {
        if (this.deathNote.get(name) == null) {
            throw new IllegalArgumentException();
        }
        
        return this.deathNote.get(name).getCause();
    }

    @Override
    public String getDeathDetails(String name) {
        if (this.deathNote.get(name) == null) {
            throw new IllegalArgumentException();
        }
        
        return this.deathNote.get(name).getDetails();
    }

    @Override
    public boolean isNameWritten(String name) {
        return this.deathNote.get(name) != null;
    }
}
