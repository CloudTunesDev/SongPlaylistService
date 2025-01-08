package com.cloudtunes.songplaylistserv.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRoleTests {

    @Test
    public void testToString() {
        assertEquals("ADMIN", UserRole.ADMIN.toString());
        assertEquals("GUEST", UserRole.GUEST.toString());
        assertEquals("MEMBER", UserRole.MEMBER.toString());
    }

    @Test
    public void testEnumEquality() {
        assertSame(UserRole.ADMIN, UserRole.ADMIN);
        assertSame(UserRole.GUEST, UserRole.GUEST);
        assertSame(UserRole.MEMBER, UserRole.MEMBER);
    }

    @Test
    public void testEnumInequality() {
        assertNotSame(UserRole.ADMIN, UserRole.GUEST);
        assertNotSame(UserRole.ADMIN, UserRole.MEMBER);
        assertNotSame(UserRole.GUEST, UserRole.MEMBER);
    }

    @Test
    public void testEnumNames() {
        assertEquals("ADMIN", UserRole.ADMIN.name());
        assertEquals("GUEST", UserRole.GUEST.name());
        assertEquals("MEMBER", UserRole.MEMBER.name());
    }

    @Test
    public void testValues() {
        UserRole[] values = UserRole.values();

        assertEquals(3, values.length);
        assertSame(UserRole.ADMIN, values[0]);
        assertSame(UserRole.GUEST, values[1]);
        assertSame(UserRole.MEMBER, values[2]);
    }

}


