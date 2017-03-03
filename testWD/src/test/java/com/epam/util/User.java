package com.epam.util;


public enum User {

    USER1("John", "Smith", "Minsk", "456783", "johnsmith@gmail.com", "BY", "+375");

    private final String name;
    private final String surname;
    private final String city;
    private final String phone;
    private final String email;
    private final String countryCode;
    private final String countryPhonePrefix;

    User(String name, String surname, String city, String phone, String email, String countryCode, String countryPhonePrefix) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.countryCode = countryCode;
        this.countryPhonePrefix = countryPhonePrefix;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryPhonePrefix() {
        return countryPhonePrefix;
    }


}

