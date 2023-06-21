package edu.ap.infractions;

public class Infraction {

    private int id;
    private int year;

    private int month;

    private String date;

    private String street;

    private String driving_direction;

    private int speed_limit;

    private int passersby;

    private int infractions_speed;

    private int infractions_red_light;

    public Infraction(){}
    public Infraction(int id, int year, int month, String date, String street, String driving_direction, int speed_limit, int passersby, int infractions_speed, int infractions_red_light) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.date = date;
        this.street = street;
        this.driving_direction = driving_direction;
        this.speed_limit = speed_limit;
        this.passersby = passersby;
        this.infractions_speed = infractions_speed;
        this.infractions_red_light = infractions_red_light;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDriving_direction() {
        return driving_direction;
    }

    public void setDriving_direction(String driving_direction) {
        this.driving_direction = driving_direction;
    }

    public int getSpeed_limit() {
        return speed_limit;
    }

    public void setSpeed_limit(int speed_limit) {
        this.speed_limit = speed_limit;
    }

    public int getPassersby() {
        return passersby;
    }

    public void setPassersby(int passersby) {
        this.passersby = passersby;
    }

    public int getInfractions_speed() {
        return infractions_speed;
    }

    public void setInfractions_speed(int infractions_speed) {
        this.infractions_speed = infractions_speed;
    }

    public int getInfractions_red_light() {
        return infractions_red_light;
    }

    public void setInfractions_red_light(int infractions_red_light) {
        this.infractions_red_light = infractions_red_light;
    }

        public int compareTo(Infraction ov){
        try{
            if (Integer.parseInt(this.infractions_speed) == Integer.parseInt(ov.infractions_speed))
            return 0;
            else if(Integer.parseInt(this.infractions_speed) > Integer.parseInt(ov.infractions_speed))
            return 1;
            else 
            return -1;
        }
        catch(Exception ex){
            return -1;
        }
    }
}
