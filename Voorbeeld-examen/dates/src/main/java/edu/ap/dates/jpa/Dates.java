package edu.ap.dates.jpa;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Dates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDate checkDate;

    @Column
    private String yesOrNo;

    @Column
    private long days;



    public Dates() {
    }

    public Dates(LocalDate checkDate, String yesOrNo, long days) {
        this.checkDate = checkDate;
        this.yesOrNo = yesOrNo;
        this.days = days;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public String getYesOrNo() {
        return yesOrNo;
    }

    public void setYesOrNo(String yesOrNo) {
        this.yesOrNo = yesOrNo;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }
}
