import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Theatre{
    String theatreName;
    int balance;
    int availableSeats;
    int totalSeats;
    Movie onShow;
    Boolean hasBooked = false;

    Theatre(String theatreName, int totalSeats, Movie onShow) {
        this.theatreName = theatreName;
        this.balance = 0;
        this.availableSeats = totalSeats;
        this.totalSeats = totalSeats;
        this.onShow = onShow;
    }

    Boolean bookTicket(int ticketCount, int price){
        if(availableSeats >= ticketCount){
            availableSeats -= ticketCount;
            balance += price*ticketCount;
            hasBooked = true;
            return true;
        }
        return false;
    }

    Boolean cancelTicket(int ticketCount, int price){
        if(hasBooked != true){
            return false;
        }
        if(ticketCount > totalSeats-availableSeats){
            System.out.println("Only "+ (totalSeats-availableSeats) +" tickets have been booked!");
            Scanner input = new Scanner(System.in);
            System.out.println("1) Cancel remaining seats\n2) Exit");
            int choice = input.nextInt();
            if(choice == 1){
                balance -= refund((totalSeats-availableSeats),price);
                availableSeats = totalSeats;
                hasBooked = false;
            }
            else {
                hasBooked = true;
                return false;
            }

        }
        return true;
    }

    int refund(int ticketCount, int price){
        int refundAmount = ticketCount*price * 50/100;
        return refundAmount;
    }
}

class Movie{
    String title;
    int price;
    String language;
    String certificate;
    float duration;
    int ratingCount;
    float rating;

    Movie(String title, String certificate, int price, float duration, String language){
        this.title = title;
        this.certificate = certificate;
        this.price = price;
        this.duration = duration;
        this.language = language;
    }

    void getRating(float rating){
        ratingCount++;
        rating = (this.rating + rating) / 2;
    }
}
class Snacks
{
    String snacks[]={"PEPSI","COKE","7-UP","BURGER-VEG","BURGER-CHICKEN","COLD COFFEE","POPCORN","CORN","ICE-CREAM","FRENCH-FRIES","CHICKEN POPCORN","NACHOS","VEG-PUFF",
            "CHICKEN-PUFF","COFFEE","TEA"};
    int price[]={100,100,100,120,180,80,80,60,100,110,130,200,40,50,30,20};
    String bill[]={"","","","","","","","","","","","","","","",""};
    int quantity,rate,total=0;
    String[] Bill(List <Integer> order)
    {
        int count=0;
        Scanner sc=new Scanner(System.in);
        for(int i:order)
        {

            System.out.println("Enter quantity required for "+snacks[i-1]);
            quantity=sc.nextInt();
            rate=price[i-1]*quantity;
            total=total+rate;
            bill[count]=snacks[i-1]+"    x "+quantity+"   :"+rate;
            count++;

        }

        return bill;
    }
    int amount()
    {
        return total;
    }
}

class Main {
    static Movie[] movieArray = new Movie[4];
    static int index1 = -1;

    static Theatre[] theatreArray = new Theatre[4];
    static int index2 = -1;
    static String[] snackArray = new String[10];
    public static void newMovie(String title, String certificate, int price, float duration, String language){
        Movie movie = new Movie(title, certificate, price, duration, language);
        movieArray[++index1] = movie;
    }

    public static void newTheatre(String theatreName, int totalSeats, Movie onShow){
        Theatre movie = new Theatre( theatreName,  totalSeats,  onShow);
        theatreArray[++index2] = movie;
    }


    public static void main(String[] args){
        int choice;
        int book;


        newMovie("Sardar","U/A",200,2.46f,"Tamil");
        newMovie("Kantara","U/A",190,2.30f,"Kannada");
        newMovie("Black Adam","U/A",190,2.05f,"English");
        newMovie("Prince","U",300,2.23f,"Telugu");

        newTheatre("PVR Theatres",190,movieArray[0]);
        newTheatre("Swami Cinemas",300,movieArray[1]);
        newTheatre("Subramani Cinemas",500,movieArray[2]);
        newTheatre("Sajee Multiplex" ,200,movieArray[3]);

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Ticket booking service!");
        do{
            System.out.println("The movies available :\n");
            for(int i=0 ; i<4 ; i++) {
                System.out.println(i+1+") "+movieArray[i].title);
            }
            System.out.println("5) Exit");
            System.out.println("Enter your choice of movie according to serial number:");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(theatreArray[0].theatreName);
                    System.out.println("Available Seats: " + theatreArray[0].availableSeats);
                    System.out.println("Ticket Price:" + movieArray[0].price);
                    System.out.println("Do you want to book ticket?\n" +
                            "1. Book\n2.Cancel\n3.Exit\n");
                    book = input.nextInt();
                    if(book == 1) {
                        System.out.println("Enter number of tickets:");
                        int ticketCount = input.nextInt();
                        if(theatreArray[0].bookTicket(ticketCount,movieArray[0].price)){
                            System.out.println("Tickets Booked");
                            Snacks sc=new Snacks();
                            int index3=1;
                            List<Integer> snacks_index=new ArrayList<Integer>();
                            System.out.println("Do you Want Snacks?? 1)Yes 2)No");
                            int ch=input.nextInt();
                            if(ch==1) {
                                for (String i : sc.snacks) {
                                    System.out.println(index3 + ") " + i);
                                    index3++;
                                }
                                while(ch!=2)
                                {
                                    System.out.println("Enter snacks you want");
                                    int snack=input.nextInt();
                                    snacks_index.add(snack);
                                    System.out.println("Do you Want more Snacks?? 1)Yes 2)No");
                                    ch= input.nextInt();
                                }
                                String bill[]=sc.Bill(snacks_index);
                                for(int j=0;j<bill.length;j++) {
                                    if(bill[j] != "") {
                                        System.out.println(bill[j]);
                                    }
                                }
                                System.out.println("Total amount for snacks = "+sc.amount());
                                theatreArray[0].balance += sc.amount();
                            }
                        }
                        else{
                            System.out.println("Tickets Full");
                        }
                    }

                    else if(book == 2){
                        System.out.println("Enter number of tickets to cancel:");
                        int ticketCount = input.nextInt();
                        if(theatreArray[0].cancelTicket(ticketCount,movieArray[0].price)){
                            System.out.println("Tickets Cancelled");
                        }
                        else{
                            System.out.println("Couldn't cancel");
                        }
                    }

                    break;

                case 2:
                    System.out.println(theatreArray[1].theatreName);
                    System.out.println("Available Seats: " + theatreArray[1].availableSeats);
                    System.out.println("Ticket Price:" + movieArray[1].price);
                    System.out.println("Do you want to book ticket?\n" +
                            "1. Book\n2.Cancel\n3.Exit\n");
                    book = input.nextInt();
                    if(book == 1) {
                        System.out.println("Enter number of tickets:");
                        int ticketCount = input.nextInt();
                        if(theatreArray[1].bookTicket(ticketCount,movieArray[1].price)){
                            System.out.println("Tickets Booked");
                            Snacks sc1=new Snacks();
                            int index4=1;
                            List<Integer> snack_index=new ArrayList<Integer>();
                            System.out.println("Do you Want Snacks?? 1)Yes 2)No");
                            int ch1=input.nextInt();
                            if(ch1==1) {
                                for (String i : sc1.snacks) {
                                    System.out.println(index4 + ") " + i);
                                    index4++;
                                }
                                while(ch1!=2)
                                {
                                    System.out.println("Enter snacks you want");
                                    int snack=input.nextInt();
                                    snack_index.add(snack);
                                    System.out.println("Do you Want more Snacks?? 1)Yes 2)No");
                                    ch1= input.nextInt();
                                }
                                String bill[]=sc1.Bill(snack_index);
                                for(int j=0;j<bill.length;j++) {
                                    if (bill[j] != "") {
                                        System.out.println(bill[j]);
                                    }
                                }
                                System.out.println("Total amount for snacks = "+sc1.amount());
                                theatreArray[1].balance += sc1.amount();
                            }
                        }
                        else{
                            System.out.println("Tickets Full");
                        }
                    }
                    else if(book == 2){
                        System.out.println("Enter number of tickets to cancel:");
                        int ticketCount = input.nextInt();
                        if(theatreArray[1].cancelTicket(ticketCount,movieArray[1].price)){
                            System.out.println("Tickets Cancelled");
                        }
                        else{
                            System.out.println("Couldn't cancel");
                        }
                    }

                    break;

                case 3:
                    System.out.println(theatreArray[2].theatreName);
                    System.out.println("Available Seats: " + theatreArray[2].availableSeats);
                    System.out.println("Ticket Price:" + movieArray[2].price);
                    System.out.println("Do you want to book ticket?\n" +
                            "1. Book\n2.Cancel\n3.Exit\n");
                    book = input.nextInt();
                    if(book == 1) {
                        System.out.println("Enter number of tickets:");
                        int ticketCount = input.nextInt();
                        if(theatreArray[2].bookTicket(ticketCount,movieArray[2].price)){
                            System.out.println("Tickets Booked");
                            Snacks sc2=new Snacks();
                            int index5=1;
                            List<Integer> snack_index2=new ArrayList<Integer>();
                            System.out.println("Do you Want Snacks?? 1)Yes 2)No");
                            int ch2=input.nextInt();
                            if(ch2==1) {
                                for (String i : sc2.snacks) {
                                    System.out.println(index5 + ") " + i);
                                    index5++;
                                }
                                while(ch2!=2)
                                {
                                    System.out.println("Enter snacks you want");
                                    int snack=input.nextInt();
                                    snack_index2.add(snack);
                                    System.out.println("Do you Want more Snacks?? 1)Yes 2)No");
                                    ch2= input.nextInt();
                                }
                                String bill[]=sc2.Bill(snack_index2);
                                for(int j=0;j<bill.length;j++) {
                                    if (bill[j] != "") {
                                        System.out.println(bill[j]);
                                    }
                                }
                                System.out.println("Total amount for snacks = "+sc2.amount());
                                theatreArray[2].balance += sc2.amount();
                            }
                        }
                        else{
                            System.out.println("Tickets Full");
                        }
                    }
                    else if(book == 2){
                        System.out.println("Enter number of tickets to cancel:");
                        int ticketCount = input.nextInt();
                        if(theatreArray[2].cancelTicket(ticketCount,movieArray[2].price)){
                            System.out.println("Tickets Cancelled");
                        }
                        else{
                            System.out.println("Couldn't cancel");
                        }
                    }

                    break;

                case 4:
                    System.out.println(theatreArray[3].theatreName);
                    System.out.println("Available Seats: " + theatreArray[3].availableSeats);
                    System.out.println("Ticket Price:" + movieArray[3].price);
                    System.out.println("Do you want to book ticket?\n" +
                            "1. Book\n2.Cancel\n3.Exit\n");
                    book = input.nextInt();
                    if(book == 1) {
                        System.out.println("Enter number of tickets:");
                        int ticketCount = input.nextInt();
                        if(theatreArray[3].bookTicket(ticketCount,movieArray[3].price)){
                            System.out.println("Tickets Booked");
                            Snacks sc3=new Snacks();
                            int index6=1;
                            List<Integer> snack_index3=new ArrayList<Integer>();
                            System.out.println("Do you Want Snacks?? 1)Yes 2)No");
                            int ch3=input.nextInt();
                            if(ch3==1) {
                                for (String i : sc3.snacks) {
                                    System.out.println(index6 + ") " + i);
                                    index6++;
                                }
                                while(ch3!=2)
                                {
                                    System.out.println("Enter snacks you want");
                                    int snack=input.nextInt();
                                    snack_index3.add(snack);
                                    System.out.println("Do you Want more Snacks?? 1)Yes 2)No");
                                    ch3= input.nextInt();
                                }
                                String bill[]=sc3.Bill(snack_index3);
                                for(int j=0;j<bill.length;j++) {
                                    if (bill[j] != "") {
                                        System.out.println(bill[j]);
                                    }
                                }
                                System.out.println("Total amount for snacks = " +sc3.amount());
                                theatreArray[3].balance += sc3.amount();
                            }
                        }
                        else{
                            System.out.println("Tickets Full");
                        }
                    }
                    else if(book == 2){
                        System.out.println("Enter number of tickets to cancel:");
                        int ticketCount = input.nextInt();
                        if(theatreArray[3].cancelTicket(ticketCount,movieArray[3].price)){
                            System.out.println("Tickets Cancelled");
                        }
                        else{
                            System.out.println("Couldn't cancel");
                        }
                    }

                    break;
            }
            System.out.println("Collection:");
            for(int i=0; i<4; i++){
                System.out.println(theatreArray[i].theatreName+ " - " + theatreArray[i].balance);
            }
        }while(choice!=5);
    }
}
