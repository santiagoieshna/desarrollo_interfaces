package modelo;

public enum Tabs {
	LIBRO(0), LIBRERIA(1), VENDER(2);
	private Integer indice;
	
	Tabs(int i) {
		this.indice= i;
	}

	public Integer getIndice(){
		return indice;
	}
}
