package models;


import java.util.Date;

public class Gift {

    private String donatorName;
    private String giftName;
    private Date givenTime;

    public Gift() {
    }

    public Gift(String giftName, String donatorName, Date givenTime) {
        this.giftName = giftName;
        this.donatorName = donatorName;
        this.givenTime = givenTime;
    }

//Getters and Setters
    public String getDonatorName() {
        return donatorName;
    }

    public String getGiftName() {
        return giftName;
    }

    public Date getGivenTime() {
        return givenTime;
    }

    public void setDonatorName(String donatorName) {
        this.donatorName = donatorName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public void setGivenTime(Date givenTime) {
        this.givenTime = givenTime;
    }

    public int compareGiftName(Gift gift) {
        return this.getGiftName().compareTo(gift.getGiftName());
    }

}
