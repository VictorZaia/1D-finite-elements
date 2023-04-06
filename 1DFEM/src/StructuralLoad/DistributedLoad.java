package StructuralLoad;

public class DistributedLoad {
	
	private String ID;
	private String ID_bar;
	private double positionInitial;
	private double positionFinal;
	private double qInitial;
	private double qFinal;
	
	public DistributedLoad(){
		this.ID = "q0";
		this.ID_bar = "B0";
		this.positionInitial = 0;
		this.positionFinal = 0;
		this.qInitial = 0;
		this.qFinal = 0;
	}
	
	public DistributedLoad(String ID, String ID_bar, double positionInitial, double positionFinal, double qInitial, double qFinal) {
		this.ID = "q0";
		this.ID_bar = "B0";
		this.positionInitial = 0;
		this.positionFinal = 0;
		this.qInitial = 0;
		this.qFinal = 0;
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
	
	public double get_InitialPosition() {
		return this.positionInitial;
	}
	
	public void set_InitialPosition(double positionInitial) {
		this.positionInitial = positionInitial;
	}
	
	public double get_FinalPosition() {
		return this.positionFinal;
	}
	
	public void set_FinalPosition(double positionFinal) {
		this.positionFinal = positionFinal;
	}
	
	public double get_QInitial() {
		return this.qInitial;
	}
	
	public void set_QInitial(double qInitial) {
		this.qInitial = qInitial;
	}
	
	public double get_QFinal() {
		return this.qFinal;
	}
	
	public void set_QFinal(double qFinal) {
		this.qFinal = qFinal;
	}
	
	public String toString() {
		String str;
		str = this.get_ID() + "\t" + this.get_ID_Bar() + "\t" + this.get_InitialPosition() + "\t" + this.get_FinalPosition() + "\t" + this.get_QInitial() + "\t" + this.get_QFinal();
		return str;
	}
	
}
