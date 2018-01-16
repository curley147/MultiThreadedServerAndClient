public class Person {
	
	private String name;
	private String password;
	private String address;
	private String ppsNum;
	private int age;
	private double weight;
	private double height;
	
	public Person() {
	
	}
	public Person(String name, String password, String address, String ppsNum, int age, double weight, double height) {
		super();
		this.name = name;
		this.password = password;
		this.address = address;
		this.ppsNum = ppsNum;
		this.age = age;
		this.weight = weight;
		this.height = height;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPpsNum() {
		return ppsNum;
	}
	public void setPpsNum(String ppsNum) {
		this.ppsNum = ppsNum;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}

}

