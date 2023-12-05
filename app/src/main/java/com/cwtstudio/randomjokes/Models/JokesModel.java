package com.cwtstudio.randomjokes.Models;

public class JokesModel {
    private String type,setup,punchline,id;

    public JokesModel(String type, String setup, String punchline, String id) {
        this.type = type;
        this.setup = setup;
        this.punchline = punchline;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getSetup() {
        return setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public String getId() {
        return id;
    }
}
