import java.util.Random;

public class Book
{
	
	private String name;
	private float price;


	public Book(String bookName) 
	{
		this.name = bookName;
		Random r = new Random();
		int lowPrice = 10;
		int highPrice = 100;
		this.price = r.nextInt(highPrice - lowPrice) + lowPrice;
	}
	public Book(String bookName, float price) 
	{
		this.name = bookName;
		this.price = price;
	}
	public String getName()
	{
		return name;
	}

	public float getPrice()
	{
		return price;
	}
}