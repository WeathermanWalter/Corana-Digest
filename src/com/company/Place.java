package com.company;


public class Place {
    static String Province_State;
    static String Country_Region;
    static String Last_Update;
    static double Lat;
    static double Long_;
    static int Confirmed;
    static int Deaths;
    static int Recovered;
    static double Active;
    static int FIPS;
    static double Incident_Rate;
    static int People_Tested;
    static int People_Hospitalized;
    static double Mortality_Rate;
    static int UID;
    static String ISO3;
    static double Testing_Rate;
    static double Hospitalization_Rate;

    //TODO store historic values using ArrayLists

    static void setProvince_State(String i) {
        Province_State = i;
    }

    static void setCountry_Region(String i) {
        Country_Region = i;
    }

    static void setLast_Update(String i) {
        Last_Update = i;
    }

    static void setLat(double i) {
        Lat = i;
    }

    static void setLong_(double i) {
        Long_ = i;
    }

    static void setConfirmed(int i) {
        Confirmed = i;
    }

    static void setDeaths(int i) {
        Deaths = i;
    }

    static void setRecovered(int i) {
        Recovered = i;
    }

    static void setActive(double i) {
        Active = i;
    }

    static void setFIPS(int i) {
        FIPS = i;
    }

    static void setIncident_Rate(double i) {
        Incident_Rate = i;
    }

    static void setPeople_Tested(int i) {
        People_Tested = i;
    }

    static void setPeople_Hospitalized(int i) {
        People_Hospitalized = i;
    }

    static void setMortality_Rate(double i) {
        Mortality_Rate = i;
    }

    static void setUID(int i) {
        UID = i;
    }

    static void setISO3(String i) {
        ISO3 = i;
    }

    static void setTesting_Rate(double i) {
        Testing_Rate = i;
    }

    static void setHospitalization_Rate(double i) {
        Hospitalization_Rate = i;
    }

}
