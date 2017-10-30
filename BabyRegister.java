import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import models.Baby;
import models.Gift;

public class BabyRegister {

    static ArrayList<Baby> babies = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy H:m");
        SimpleDateFormat giftFormat = new SimpleDateFormat("dd/MM/yyyy");
       
        //pre-filled several objects
        Baby baby2 = new Baby("12/02/2015 23:12", "Henry", 'M');
        Baby baby3 = new Baby("19/03/2017 19:21", "Sultan", 'M');
        babies.add(baby2);
        babies.add(baby3);
        Date givenTime = new Date();
		
        try {
            givenTime = giftFormat.parse("02/01/2017");
        } catch (ParseException e) {
            System.out.println(e);
        }
		
        Gift gift2 = new Gift("Carriage", "John", givenTime);
        baby2.addGift(gift2);
		
        try {
            givenTime = giftFormat.parse("21/10/2017");
        } catch (ParseException e) {
            System.out.println(e);
        }
		
        Gift gift3 = new Gift("Iphone 7", "Nazerke", givenTime);
        baby3.addGift(gift3);

        String userCommand;
        do {
            System.out.println("--------------------  Menu  ------------------- ");
            System.out.println("Exit the program:  'exit'");
            System.out.println("Add a new baby: 'add'");
            System.out.println("List babies by their Age: 'age'");
            System.out.println("List babies by their Name: 'name'");
            System.out.println("Add a gift: 'gift'");
            System.out.println("List gifts: 'listGift'");

            userCommand = scanner.next();
            switch (userCommand) {

                case "add":

                    Date birthday = new Date();
                    String day;
                    String time;

                    boolean validDate = false;
                    while (!validDate) {
                        try {
							
                            System.out.println("Enter the day of the birth  of the baby(dd/MM/yyyy)");
                            day = scanner.next();
                            System.out.println("Enter the birth time of the baby(H:m)");
                            time = scanner.next();

                            birthday = myFormat.parse(day + " " + time);
                            validDate = true;
							
                        } catch (ParseException e) {
                            
							System.out.println("Invalid date, type it again");
                            validDate = false;
                        }
                    }
                    System.out.println("Enter the name of the new baby");
                    String name = scanner.next();
                    char gender;
        
	             	do {
                        System.out.println("Enter the gender of the new baby ('F' or 'M')");
                        gender = scanner.next().charAt(0);
                    } while (gender != 'F' && gender != 'M');

                    Baby baby1 = new Baby(birthday, name, gender);
                    babies.add(baby1);
                    System.out.println("The # of babies:  " + babies.size() + ".");
					
                    break;

                case "age":

                    //sort babies by their age
                    for (int i = 0; i < babies.size(); i++) {
						
                        for (int j = 0; j < babies.size() - i - 1; j++) {
							
                            if (babies.get(j).howOld() > (babies.get(j + 1).howOld())) {
								
                                Baby temp = babies.get(j);
                                babies.set(j, babies.get(j + 1));
                                babies.set(j + 1, temp);
                            }
                        }
                    }
                    for (Baby b : babies) {
                        
						System.out.println(myFormat.format(b.getBirthday()) + " " + b.getName() + " " + b.getGender() + " " + b.howOld() + " days old");
                    }
                    break;

                case "name":

                    //Sort babies by their name
                    for (int i = 0; i < babies.size(); i++) {
						
                        for (int j = 0; j < babies.size() - i - 1; j++) {
							
                            if (babies.get(j).compareName(babies.get(j + 1)) > 0) {
								
                                Baby temp = babies.get(j);
                                babies.set(j, babies.get(j + 1));
                                babies.set(j + 1, temp);
                            }
                        }
                    }
                    for (Baby b : babies) {
                        
						System.out.println(b.getName() + " " + b.getGender() + " " + b.howOld() + " days old" + " " + myFormat.format(b.getBirthday()));
                    }
                    break;

                case "gift":
				
                    //add a gift to the list gifts
                    String giftName="";
					System.out.println("Enter a gift name: ");
				   do{
                    
                     giftName= scanner.nextLine();
				   }while(giftName.equals(""));
                    System.out.println("Enter  the name of a donator: ");
                    String donator = scanner.next();

                    boolean validTime = false;
                    while (!validTime) {
                        try {
                            System.out.println("Enter the given time of a donation: (dd/MM/yyyy)");
                            givenTime = giftFormat.parse(scanner.next());
                            validTime = true;
                        } catch (ParseException e) {
                            System.out.println("Invalid type of a date, type it again");
                            validTime = false;
                        }
                    }
					
                    System.out.println("type name of a baby you would like to give this " + giftName);
                    String babyName;
                    babyName = scanner.next();

                    boolean confirm;
                    confirm = false;
                    for (int i = 0; i < babies.size(); i++) {
						
                        if (babies.get(i).getName().equals(babyName)) {
							
                            System.out.println("Thank you for the gift! This gift will be delivered to " + babyName + ".");
                            Gift gift;
                            gift = new Gift(giftName, donator, givenTime);
                            babies.get(i).addGift(gift);

                            confirm = true;
                        }
                    }
                    if (!confirm) {
                        System.out.println("Name of a baby is not found. Try again by typing 'gift' shown on the menu. ");
                    }
                    break;

                case "listGift":
				
				
                    // List gifts by their name
                    for (Baby b : babies) {
                        System.out.print("baby " + b.getName() + "'s gifts: ");
                        for (Gift g : b.sortGifts()) {
                            System.out.print(g.getGiftName() + ", ");
                        }
                        System.out.println();

                    }
                    break;
            }

        } while (!userCommand.equals("exit"));

    }
}
