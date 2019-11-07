package com.llorca.lab5;

public class Android {
    int logo;
    String name, level, version, released, description;

    public Android(int logo, String name, String level, String version, String released, String description) {
        this.logo = logo;
        this.name = name;
        this.level = level;
        this.version = version;
        this.released = released;
        this.description = description;
    }

    public int getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getVersion() {
        return version;
    }

    public String getReleased() {
        return released;
    }

    public String getDescription() {
        return description;
    }
}