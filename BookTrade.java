import java.util.Scanner;


import java.util.Collections;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class BookTrade 

    {

	public static void main(String[] args) throws IOException{
		
	
	//Create an array for the randomly created titles for books
	
	String[] bookTitles = new String[100];
	//generate titles for first 80 titles
	for (int i = 0; i < 80; i++){
		bookTitles[i] = "book" + Integer.toString(i);
	}
	//Generate single duplicate numbers to replicate multiple copies of books
	final int[] dupes = new Random().ints(1, 80).distinct().limit(20).toArray();

	//System.out.println("Duplicate book numbers: "+ Arrays.toString(dupes));
	//Add these to the existing array
	for (int i = 1; i < dupes.length; i++){
		bookTitles[i+80] = "book" + Integer.toString(dupes[i]);

	}

	//Create an inventory array
	Book[] bookInventory = new Book[bookTitles.length];
	//For each title also create a random price.
	for( int i = 0; i < bookTitles.length; i++){
		Book book = new Book(bookTitles[i]);
		bookInventory[i] = book;
	}
	
	SellerAgent[] sellers = new SellerAgent[10];

	
	for (int i = 0; i < sellers.length; i++){
		sellers[i] = new SellerAgent();
		sellers[i].setID("seller_" + Integer.toString(i));
	}
	
	//Beginning
	
	while (true) {
		  
	
	System.out.println ("\nEnter the type of Agent you want to proceed with ?  (e.g. 1 for Buyer Agent )?");
	System.out.println ("\n1.Buyer Agent \t 2.Seller Agent \t 3.Exit\n");
	Scanner keyboard = new Scanner(System.in);
	BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
	int agentType = keyboard.nextInt();
	if (agentType == 1) {
	BuyerAgent buyer = new BuyerAgent();
	buyer.setID("buyer1");

	//Ask for book to find
	System.out.println ("\nEnter the book(title) you want to find (e.g. book1)?\n");
    String bookTarget = br.readLine();
	Collections.shuffle(Arrays.asList(bookInventory));

	//distribute books
	int count = 0;
	while (count < bookInventory.length){
		for (int i = 0; i < sellers.length; i++){
			sellers[i].receiveBook(bookInventory[count]);
			count++;
		}
	}

	buyer.setTarget(bookTarget);
	System.out.println ("Issuing bid for  "+"Book "+
		bookTarget+ ".");
	
	//issue tenders to all seller agents
	for (int i = 0; i < sellers.length; i++){
		buyer.issueTender(sellers[i]);
	}

	//issue tenders to all seller agents
	for (int i = 0; i < sellers.length; i++){
		sellers[i].getID();
		buyer.queryAgent(sellers[i]);
		buyer.reportResult();
	}

	//Transfer book and report result
	buyer.transferBook();
	buyer.reportTransfer();
	
	
	}else if (agentType == 2) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Choose the seller agent(1-10) :");
		int sid = sc.nextInt();
		System.out.print("Enter the book name : ");
		String bk_name = br.readLine();
		System.out.print("Enter the book price : ");
		float bk_price = sc.nextFloat();
		Book book1 = new Book(bk_name,bk_price);
		sellers[sid].receiveBook(book1);
		System.out.print("Added book entered to the"+ sellers[sid].getID());
	}else if (agentType == 3) {
		System.exit(0);
	}else {
			System.out.println ("Invalid Agent type! \n");
		  
		}
	}

	}
	 

}