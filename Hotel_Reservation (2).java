/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpelab1;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author asus
 */
public class CPELab1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int floorSize;
        int roomSize;
        
        System.out.println("Number of hotel floors: ");
        floorSize=sc.nextInt();
        
        System.out.println("Number of hotel rooms: ");
        roomSize=sc.nextInt();

        boolean[][] hotel = new boolean[floorSize][roomSize];

        initRooms(hotel);

        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome to the Hotel Reservation System");
        System.out.println("----------------------------------------------------------------");

        int option;

        do {
            System.out.println("1) Current Hotel Plan");
            System.out.println("2) Book a Room");
            System.out.println("3) Find First free Room ");
            System.out.println("4) Free a room");
            System.out.println("5) Find three consecutive rooms");
            System.out.println("6) Total Occupied Rooms");            
            System.out.println("0) Exit");
            System.out.println("--------------------------------");
            System.out.println("Please choose an option");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("-------------------------------------------");
                    System.out.println("**Reminder: \nX stands for Occupied room ");
                    System.out.println("O stands for free room");
                    System.out.println("-------------------------------------------");
                    System.out.println("Hotel's Current Floor Plan :\n");
                    printRooms(hotel);
                    break;
                case 2:
                    System.out.print("Enter a the floor number (1-" + hotel.length + "): ");
                    int floor = sc.nextInt() - 1;
                    
                    System.out.print("\nEnter a room number to book (1-" + hotel[0].length + "): ");
                    int room = sc.nextInt() - 1;
                    
                    bookAroom(hotel, floor, room);
                    break;
                case 3:               
                    System.out.print("\nEnter a starting room number to search for the first free room (1-" + hotel[0].length + "): ");
                    int start=sc.nextInt();
                    System.out.print("\nEnter a ending room number to search for the first free room (1-" + hotel[0].length + "): ");
                    int end=sc.nextInt();
                    fristFree(hotel,start,end);
                    break;
                case 4:
                    System.out.println("Enter the floor number : ");
                    floor = sc.nextInt() - 1;                  
                    System.out.println("Enter the room number you want to check out: ");
                    room = sc.nextInt() - 1;
                    freeAroom(hotel, floor, room);
                    break;
                case 5:
                    findBlock(hotel);
                    break;
                case 6:
                    int occupied = countOccupied(hotel);
                    System.out.println("There are " + occupied + " occupied rooms in Hotel\n");
                    break;
                
                default:
                    System.out.println("Enter a vaild option!!");
                    break;

            }

        } while (option != 0);
        System.out.println("You have exited from the system! ");
        

    }
    public static void findBlock(boolean[][] hotel) {
        for (int i = 0; i < hotel.length; i++) {
            for (int j = 0; j < hotel[i].length - 2; j++) {
                if (hotel[i][j] == false && hotel[i][j + 1] == false && hotel[i][j + 2] == false) {
                    System.out.println("Room No. [" + (j + 1) + "] , [" + (j + 2) + "] ,[" + (j + 3)+ "] are free rooms in floor [" + (i + 1) + "]");
                    return;
                }
            }
        }
        System.out.println("There are no free block of three rooms available at the moment.");
    }
        public static int countOccupied(boolean[][] occupied) {

        int counter = 0;
        for (int i = 0; i < occupied.length; i++) {
            for (int j = 0; j < occupied[i].length; j++) {
                if (occupied[i][j] == true) {
                    counter++;
                }
            }

        }

        return counter;
    }
      public static void fristFree(boolean[][] hotel,int start,int end) {
          if(start >=0 && end<=hotel[0].length)
          {
              for (int i = 0; i < hotel.length; i++)
              {
                  for (int j = start-1; j <= end-1; j++)
                  {
                      if (hotel[i][j] == false)
                      {
                          System.out.println("----------------------------------------------------------------------");
                          System.out.println("The first free room found on floor [" + (i + 1) + "] room [" + (j + 1) + "]");
                          System.out.println("----------------------------------------------------------------------");
                          return;
                      }
                  }
                  
              }
              
          }
          else
              System.out.println("Invalid room numbers...");
         
    }

        
    public static void initRooms(boolean [][]hotel)
    {
        Random r=new Random();
        for(int i=0;i<hotel.length;++i)//first iterate over # of floors
        {
            for(int j=0;j<hotel.length;++j)//then iterate over the rooms
            {
                hotel[i][j]=r.nextBoolean();
            }
        }
            
    }
    public static void printRooms(boolean [][]hotel)
    {
         for (int i = 0; i < hotel.length; i++) {
            System.out.print("\nFloor " + (i + 1) + " :");
            for (int j = 0; j < hotel[i].length; j++) {
                if (hotel[i][j] == false) {
                    System.out.print("[O]  ");
                } else {
                    System.out.print("[X]  ");
                }
            }
            System.out.println();
            System.out.println();
        }
            
    }
     public static void bookAroom(boolean[][] hotel, int floor, int room) {
        //You can check first if the floor and room numbers are valid
        if (hotel[floor][room] == false) {
            hotel[floor][room] = true;
            System.out.println("The room [" + (room + 1) +"]  on floor  [" + (floor + 1) + "]  has been booked.");
            System.out.println("----------------------------------------------------------------\n");
            System.out.println("Hotel's Floor Plan after booking :\n");
            printRooms(hotel);
        } else {
            System.out.println("This room is already occupied.");
            System.out.println("----------------------------------------------------------------\n");
        }

    }
         public static void freeAroom(boolean[][] hotel, int floor, int room) {
         if(floor>=0 && floor<hotel.length && room>=0 && room<hotel[0].length)//You can check first if the floor and room numbers are valid
         {
             if (hotel[floor][room] == true) {
                    hotel[floor][room] = false;
                    System.out.println("The room [" + (room + 1) +"]  on floor  [" + (floor + 1) + "]  has been booked.");
                    System.out.println("----------------------------------------------------------------\n");
                    System.out.println("Hotel's Floor Plan after booking :\n");
                    printRooms(hotel);
             }    
             
             else {
                    System.out.println("This room is already occupied.");
                    System.out.println("----------------------------------------------------------------\n");
            }
         }
    }
    
}
