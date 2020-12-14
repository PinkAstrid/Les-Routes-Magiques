package sample;

public class Coordonees implements ElementVisitor {
	float lattitude;
	float longitude;
	float elevation;

	public Coordonees(){

	}

	public Coordonees(float lattitude, float longitude, float elevation){
		this.lattitude = lattitude;
		this.longitude = longitude;
		this.elevation = elevation;
	}

	@Override
	public void accept(Visitor v) {

	}
}
