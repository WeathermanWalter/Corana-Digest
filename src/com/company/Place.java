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

    static void setLat(String i) {
        Lat = checkDouble(i);
    }

    static void setLong_(String i) {
        Long_ = checkDouble(i);
    }

    static void setConfirmed(String i) {
        Confirmed = checkInt(i);
    }

    static void setDeaths(String i) {
        Deaths = checkInt(i);
    }

    static void setRecovered(String i) {
        Recovered = checkInt(i);
    }

    static void setActive(String i) {
        Active = checkDouble(i);
    }

    static void setFIPS(String i) {
        FIPS = checkInt(i);
    }

    static void setIncident_Rate(String i) {
        Incident_Rate = checkDouble(i);
    }

    static void setPeople_Tested(String i) {
        People_Tested = checkInt(i);
    }

    static void setPeople_Hospitalized(String i) {
        People_Hospitalized = checkInt(i);
    }

    static void setMortality_Rate(String i) {
        Mortality_Rate = checkDouble(i);
    }

    static void setUID(String i) {
        UID = checkInt(i);
    }

    static void setISO3(String i) {
        ISO3 = i;
    }

    static void setTesting_Rate(String i) {
        Testing_Rate = checkDouble(i);
    }

    static void setHospitalization_Rate(String i) {
        Hospitalization_Rate = checkDouble(i);
    }

    private static double checkDouble(String num) {
        if (num.equals("")) return 0;
        else return Double.parseDouble(num);
    }

    private static int checkInt(String num) {
        if (num.equals("")) return 0;
        else return Integer.parseInt(num);
    }

    //--------------------------------------------------------------------------

    static String getProvince_State() {
        return Province_State;
    }

    static String getCountry_Region() {
        return Country_Region;
    }

    static String getLast_Update() {
        return Last_Update;
    }

    static double getLat() {
        return Lat;
    }

    static double getLong_() {
        return Long_;
    }

    static int getConfirmed() {
        return Confirmed;
    }

    static int getDeaths() {
        return Deaths;
    }

    static int getRecovered() {
        return Recovered;
    }

    static double getActive() {
        return Active;
    }

    static int getFIPS() {
        return FIPS;
    }

    static double getIncident_Rate() {
        return Incident_Rate;
    }

    static int getPeople_Tested() {
        return People_Tested;
    }

    static int getPeople_Hospitalized() {
        return People_Hospitalized;
    }

    static double getMortality_Rate() {
        return Mortality_Rate;
    }

    static int getUID() {
        return UID;
    }

    static String getISO3() {
        return ISO3;
    }

    static double getTesting_Rate() {
        return Testing_Rate;
    }

    static double getHospitalization_Rate() {
        return Hospitalization_Rate;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("*********************************");
        sb.append("\nState = " + getProvince_State());
        sb.append("\nCountry = " + getCountry_Region());
        sb.append("\nLast Update = " + getLast_Update());
        sb.append("\nLatitude = " + Double.toString(getLat()));
        sb.append("\nLongitude = " + Double.toString(getLong_()));
        sb.append("\nConfirmed cases = " + Integer.toString(getConfirmed()));
        sb.append("\nDeaths = " +Integer.toString(getDeaths()));
        sb.append("\nRecovered = " +Integer.toString(getRecovered()));
        sb.append("\nActive cases = " + Double.toString(getActive()));
        sb.append("\nFIPS = " + Integer.toString(getFIPS()));
        sb.append("\nIncident Rate = " + Double.toString(getIncident_Rate()));
        sb.append("\nPeople Tested = " + Integer.toString(getPeople_Tested()));
        sb.append("\nPeople Hospitalized = " + Integer.toString(getPeople_Hospitalized()));
        sb.append("\nMortality Rate = " + Double.toString(getMortality_Rate()));
        sb.append("\nUID = " + Integer.toString(getUID()));
        sb.append("\nISO3 = " + getISO3());
        sb.append("\nTesting Rate = " + Double.toString(getTesting_Rate()));
        sb.append("\nHospitalization rate = " + Double.toString(getHospitalization_Rate()));
        return sb.toString();
    }

}
