package Models;

public class Reimbursement {
	
	
	private int id;
	private int Author;
	private int Resolver;
	private String Description;
	private Reimbursement_Type type ;
	private Status status;
	private double Amount;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int id, int author, int resolver, String description, Reimbursement_Type type,
			Status status, int amount) {
		super();
		this.id = id;
		Author = author;
		Resolver = resolver;
		Description = description;
		this.type = type;
		this.status = status;
		Amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAuthor() {
		return Author;
	}
	public void setAuthor(int author) {
		Author = author;
	}
	public int getResolver() {
		return Resolver;
	}
	public void setResolver(int resolver) {
		Resolver = resolver;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Reimbursement_Type getType() {
		return type;
	}
	public void setType(Reimbursement_Type type) {
		this.type = type;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double d) {
		Amount = d;
	}

	}
	
	


