package com.framework.utils;

public class RegexValidationUtils {

    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean isValidMobileNumber(String mobile) {
        return mobile.matches("^[6-9]\\d{9}$");
    }

    public static boolean isValidDate(String date) {
        return date.matches("^\\d{2}/\\d{2}/\\d{4}$");
    }

    public static boolean isValidURL(String url) {
        return url.matches("https?://[\\w.-]+(\\.[a-zA-Z]{2,})+");
    }

    public static boolean isAlphaNumericWithUnderscoreDash(String input) {
        return input.matches("^[a-zA-Z0-9_-]+$");
    }

    public static boolean isOnlyDigits(String input) {
        return input.matches("^\\d+$");
    }

    public static boolean isOnlyLetters(String input) {
        return input.matches("^[a-zA-Z]+$");
    }

    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
    }
    
    // Validates email addresses (e.g., example@test.com)
    public static boolean isValidEmail1(String email) {
        return email.matches("^[\\w-\\.+]+@[\\w-]+\\.[a-z]{2,6}$");
    }

    // Validates Indian mobile numbers (10 digits, starting with 6-9)
    public static boolean isValidMobileNumber1(String mobile) {
        return mobile.matches("^[6-9]\\d{9}$");
    }

    // Validates simple URLs (with http/https)
    public static boolean isValidURL1(String url) {
        return url.matches("^(http|https)://[\\w.-]+\\.[a-z]{2,6}(/.*)?$");
    }

    // Validates usernames (alphanumeric and underscore, 3-16 characters)
    public static boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9_]{3,16}$");
    }

    // Validates passwords (minimum 8 characters, at least 1 letter and 1 number)
    public static boolean isValidPassword1(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    // Validates numeric input (only digits)
    public static boolean isNumeric(String value) {
        return value.matches("^\\d+$");
    }

    // Validates alphabetic input (only letters)
    public static boolean isAlphabetic(String value) {
        return value.matches("^[a-zA-Z]+$");
    }

    // Validates alphanumeric input (letters and digits only)
    public static boolean isAlphanumeric(String value) {
        return value.matches("^[a-zA-Z0-9]+$");
    }

    // Validates date in yyyy-mm-dd format
    public static boolean isValidDate1(String date) {
        return date.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    // Validates time in hh:mm:ss format
    public static boolean isValidTime(String time) {
        return time.matches("^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$");
    }

    // Add more patterns as needed
    
    // Validates email addresses (e.g., example@test.com)
    // ^         : start of the string
    // [\\w-\\.+]+ : one or more word characters, hyphen, dot or plus
    // @         : must contain "@"
    // [\\w-]+   : domain name (e.g., "gmail")
    // \\.       : literal dot
    // [a-z]{2,6} : domain extension (e.g., com, org)
    // $         : end of the string
    public static boolean isValidEmail2(String email) {
        return email.matches("^[\\w-\\.+]+@[\\w-]+\\.[a-z]{2,6}$");
    }

    // Validates Indian mobile numbers (10 digits, starting with 6-9)
    // ^[6-9]    : starts with digits 6 to 9
    // \\d{9}$   : followed by exactly 9 digits
    public static boolean isValidMobileNumber2(String mobile) {
        return mobile.matches("^[6-9]\\d{9}$");
    }

    // Validates simple URLs (with http/https)
    // ^(http|https) : starts with http or https
    // ://          : mandatory separator
    // [\\w.-]+     : domain part with word characters, dot or hyphen
    // \\.          : literal dot
    // [a-z]{2,6}   : TLD (e.g., com, in)
    // (/.*)?$      : optional path
    public static boolean isValidURL2(String url) {
        return url.matches("^(http|https)://[\\w.-]+\\.[a-z]{2,6}(/.*)?$");
    }

    // Validates usernames (alphanumeric and underscore, 3-16 characters)
    // ^[a-zA-Z0-9_]{3,16}$ : must be 3-16 characters long, alphanumeric or _
    public static boolean isValidUsername2(String username) {
        return username.matches("^[a-zA-Z0-9_]{3,16}$");
    }

    // Validates passwords (min 8 characters, at least 1 letter and 1 digit)
    // ^(?=.*[A-Za-z]) : at least one letter
    // (?=.*\\d)       : at least one digit
    // [A-Za-z\\d]{8,} : min 8 characters of letters/digits
    public static boolean isValidPassword2(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    // Validates numeric input (only digits)
    // ^\\d+$ : one or more digits only
    public static boolean isNumeric2(String value) {
        return value.matches("^\\d+$");
    }

    // Validates alphabetic input (only letters)
    // ^[a-zA-Z]+$ : one or more alphabet letters
    public static boolean isAlphabetic2(String value) {
        return value.matches("^[a-zA-Z]+$");
    }

    // Validates alphanumeric input (letters and digits only)
    // ^[a-zA-Z0-9]+$ : one or more letters or digits
    public static boolean isAlphanumeric2(String value) {
        return value.matches("^[a-zA-Z0-9]+$");
    }

    // Validates date in yyyy-mm-dd format
    // ^\\d{4} : 4 digits year
    // -\\d{2} : dash and 2 digits month
    // -\\d{2}$: dash and 2 digits day
    public static boolean isValidDate2(String date) {
        return date.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    // Validates time in hh:mm:ss format (24-hour)
    // ^([01]\\d|2[0-3]) : hours (00-23)
    // :([0-5]\\d)       : minutes (00-59)
    // :([0-5]\\d)$      : seconds (00-59)
    public static boolean isValidTime2(String time) {
        return time.matches("^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$");
    }

    
    
    
    public static void main(String[] args) {
        System.out.println("Email: " + isValidEmail("user@example.com"));
        System.out.println("Mobile: " + isValidMobileNumber("9876543210"));
        System.out.println("Date: " + isValidDate("29/04/2025"));
        System.out.println("URL: " + isValidURL("https://example.com"));
        System.out.println("AlphaNum: " + isAlphaNumericWithUnderscoreDash("user_123-abc"));
        System.out.println("Digits: " + isOnlyDigits("123456"));
        System.out.println("Letters: " + isOnlyLetters("JohnDoe"));
        System.out.println("Password: " + isValidPassword("Abc@1234"));
    }
} 

