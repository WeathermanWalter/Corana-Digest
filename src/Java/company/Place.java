package Java.company;


public class Place {
    String file;
    String Province_State;
    String Country_Region;
    String Last_Update;
    double Lat;
    double Long_;
    int Confirmed;
    int Deaths;
    int Recovered;
    double Active;
    int FIPS;
    double Incident_Rate;
    int People_Tested;
    int People_Hospitalized;
    double Mortality_Rate;
    int UID;
    String ISO3;
    double Testing_Rate;
    double Hospitalization_Rate;

    //TODO store historic values using ArrayLists

    void setFile(String i) {
        file = i;
    }

    void setProvince_State(String i) {
        Province_State = i;
    }

    void setCountry_Region(String i) {
        Country_Region = i;
    }

    void setLast_Update(String i) {
        Last_Update = i;
    }

    void setLat(String i) {
        Lat = checkDouble(i);
    }

    void setLong_(String i) {
        Long_ = checkDouble(i);
    }

    void setConfirmed(String i) {
        Confirmed = checkInt(i);
    }

    void setDeaths(String i) {
        Deaths = checkInt(i);
    }

    void setRecovered(String i) {
        Recovered = checkInt(i);
    }

    void setActive(String i) {
        Active = checkDouble(i);
    }

    void setFIPS(String i) {
        FIPS = checkInt(i);
    }

    void setIncident_Rate(String i) {
        Incident_Rate = checkDouble(i);
    }

    void setPeople_Tested(String i) {
        People_Tested = checkInt(i);
    }

    void setPeople_Hospitalized(String i) {
        People_Hospitalized = checkInt(i);
    }

    void setMortality_Rate(String i) {
        Mortality_Rate = checkDouble(i);
    }

    void setUID(String i) {
        UID = (int)checkDouble(i);
    }

    void setISO3(String i) {
        ISO3 = i;
    }

    void setTesting_Rate(String i) {
        Testing_Rate = checkDouble(i);
    }

    void setHospitalization_Rate(String i) {
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

    String getFile() {
        return file;
    }

    String getProvince_State() {
        return Province_State;
    }

    String getCountry_Region() {
        return Country_Region;
    }

    String getLast_Update() {
        return Last_Update;
    }

    double getLat() {
        return Lat;
    }

    double getLong_() {
        return Long_;
    }

    int getConfirmed() {
        return Confirmed;
    }

    int getDeaths() {
        return Deaths;
    }

    int getRecovered() {
        return Recovered;
    }

    double getActive() {
        return Active;
    }

    int getFIPS() {
        return FIPS;
    }

    double getIncident_Rate() {
        return Incident_Rate;
    }

    int getPeople_Tested() {
        return People_Tested;
    }

    int getPeople_Hospitalized() {
        return People_Hospitalized;
    }

    double getMortality_Rate() {
        return Mortality_Rate;
    }

    int getUID() {
        return UID;
    }

    String getISO3() {
        return ISO3;
    }

    double getTesting_Rate() {
        return Testing_Rate;
    }

    double getHospitalization_Rate() {
        return Hospitalization_Rate;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n*********************************");
        sb.append("\nFrom File " + getFile() + ".cvs");
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
