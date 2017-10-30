package models;

import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;


import java.text.ParseException;

public class Baby {

    private String name;
    private char gender;
    private Date birthday;
    private ArrayList<Gift> gifts = new ArrayList<>();
	
    SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy H:m");

//constructors
    public Baby() {

    }

    public Baby(String birthday, String name, char gender) {
        try {
            this.birthday = myFormat.parse(birthday);
        } catch (ParseException e) {
            System.out.println("Invalid date.");
        }
        this.name = name;
        this.gender = gender;
    }

    public Baby(Date birthday, String name, char gender) {
        this.birthday = birthday;
        this.name = name;
        this.gender = gender;
    }

//Getters and Setters
    public Date getBirthday() {
        return this.birthday;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setGifts(ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }

    public ArrayList<Gift> getGifts() {
        return this.gifts;
    }
	
// If a given kid is older than the current one
    public boolean isOlder(Baby baby) {
        return birthday.after(baby.birthday);
    }
	
// The actual age of the kid in days
    public int howOld() {
        Date currentDate = new Date();
        return (int) ((long) (currentDate.getTime() - this.birthday.getTime()) / (long) (1000 * 60 * 60 * 24));
    }

    public String toString() {
        return name + " " + " " + gender + " " + birthday;
    }
	
//compare the name
    public int compareName(Baby baby) {
        return this.getName().compareTo(baby.getName());
    }
	
//add a gift to the list gifts
    public void addGift(Gift gift) {
      gifts.add(gift);

    }

    public int howManyGifts() {
        return this.gifts.size();
    }

    public ArrayList<Gift> sortGifts() {
		
        for (int i = 0; i < gifts.size(); i++) {
			
            for (int j = 0; j < gifts.size() - i - 1; j++) {
				
                if (gifts.get(j).compareGiftName(gifts.get(j + 1)) > 0) {
					
                    Gift temp = gifts.get(j);
                    gifts.set(j, gifts.get(j + 1));
                    gifts.set(j + 1, temp);
                }
            }
        }
        return gifts;
    }

}
