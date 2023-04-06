package StructuralLoad;

public class PointLoad {
	
	private String ID;
	private String ID_bar;
	private double position;
	private double Fx;
	private double Fy;
	private double Mz;
	
	public PointLoad(){
		this.ID = "P0";
		this.ID_bar = "B0";
		this.Fx = 0;
		this.Fy = 0;
		this.Mz = 0;
	}
	
	public PointLoad(String ID, String ID_bar, double Fx, double Fy, double Mz) {
		this.ID = ID;
		this.ID_bar = ID_bar;
		this.Fx = Fx;
		this.Fy = Fy;
		this.Mz = Mz;
	}
	
	public String get_ID() {
		return this.ID;
	}
	
	public void set_ID(String ID) {
		this.ID = ID;
	}
	
	public String get_ID_Bar() {
		return this.ID_bar;
	}
	
	public void set_ID_Bar(String ID_bar) {
		this.ID_bar = ID_bar;
	}
	
	public double get_Position() {
		return this.position;
	}
	
	public void set_Position(double position) {
		this.position = position;
	}
	
	public double get_Fx() {
		return this.Fx;
	}
	
	public void set_Fx(double Fx) {
		this.Fx = Fx;
	}
	
	public double get_Fy() {
		return this.Fy;
	}
	
	public void set_Fy(double Fy) {
		this.Fy = Fy;
	}
	
	public double get_Mz() {
		return this.Mz;
	}
	
	public void set_Mz(double Mz) {
		this.Mz = Mz;
	}
	
	public String toString() {
		String str;
		str = this.get_ID() + "\t" + this.get_ID_Bar() + "\t" + this.get_Position() + "\t" + this.get_Fx() + "\t" + this.get_Fy() + "\t" + this.get_Mz();
		return str;
	}
}
