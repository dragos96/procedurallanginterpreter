import java.util.ArrayList;
import java.util.List;

public class Variabila {

	private int stadiuCurent = 0;
	
	private String nume;
//	private Object valoare;
	private List<Object> valori = new ArrayList<>();
	
	
	
	
	public int getStadiu() {
		return stadiuCurent;
	}


	public void setStadiu(int stadiu) {
		this.stadiuCurent = stadiu;
	}


	public Variabila(String nume) {
		super();
		this.nume = nume;
	}
	
	
	public Variabila() {
		super();
	}


	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public List<Object> getValoari() {
		return valori;
	}
	
	public void addValoare(Object valoare){
		valori.add(valoare);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nume == null) ? 0 : nume.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variabila other = (Variabila) obj;
		if (nume == null) {
			if (other.nume != null)
				return false;
		} else if (!nume.equals(other.nume))
			return false;
		return true;
	}
	
	
	
}
