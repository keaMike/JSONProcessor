public class Country {

    private String country;
    private String year;
    private String totalPopulation;

    public Country() {
    }

    public Country(String country, String year, String totalPopulation) {
        this.country = country;
        this.year = year;
        this.totalPopulation = totalPopulation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(String totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    @Override
    public String toString() {
        return "Country: " + country + "\n" +
                "Year: " + year + "\n" +
                "Total Population: " + totalPopulation;
    }
}
