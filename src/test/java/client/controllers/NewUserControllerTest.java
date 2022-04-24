package client.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NewUserControllerTest {
    NewUserController control;

    @BeforeEach
    void init(){
        control = new NewUserController();
    }

    @Test
    void testValidUsernameTooShort() {
        assertFalse(control.isValidUsername("aaa"));    // 3
    }

    @Test
    void testValidUsernameTooLong() {
        assertFalse(control.isValidUsername("aaaaaaaaaaaaaaaaaaaaa"));  // 21
    }

    @Test
    void testValidUsernameShortestPossible() {
        assertTrue(control.isValidUsername("aaaa"));    // 4
    }

    @Test
    void testValidUsernameLongestPossible() {
        assertTrue(control.isValidUsername("aaaaaaaaaaaaaaaaaaaa"));    // 20
    }

    @Test
    void testValidUsernameNumbers() {
        assertTrue(control.isValidUsername("1234567890"));
    }

    @Test
    void testValidUsernameCharacters() {
        assertTrue(control.isValidUsername("abcdefghijklmnopqrst"));
        assertTrue(control.isValidUsername("iuwgyz"));  // Too long otherwise
    }

    @Test
    void testValidUsernameEmptySpaces() {
        assertFalse(control.isValidUsername("    "));   // Trim all empty spaces from beginning and end
    }

    @Test
    void testValidUsernameEmptySpacesWithTooFewCharacters() {
        assertFalse(control.isValidUsername(" abc "));
    }

    @Test
    void testValidUsernameEmptySpacesWithEnoughCharacters() {
        assertTrue(control.isValidUsername(" abcdefghijklmnopqrst "));
    }

    @Test
    void testValidUsernameIllegalCharacters() {
        assertFalse(control.isValidUsername("!@%*_"));
    }
}
