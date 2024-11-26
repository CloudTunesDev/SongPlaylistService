package com.cloudtunes.songplaylistserv.user;

public enum UserRole {
    ADMIN("ADMIN"),

    //Will be used in the future for the Queueing system
    GUEST("GUEST"),

    MEMBER("MEMBER");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}

