

//incluir conectar con el supervisor lol
import java.time;
import java.util.ArrayList; 

class Area{
	private int id; 
	private String nombre; 
	private String funcion; 
	private Supervisor encargadoArea; 
	private ArrayList<Repartidor> repartidores; 
	
	Area(){
		repartidores = new ArrayList<Repartidor>(); 
		
	}
	
	//metodos
	
	void asignarEmpleado(Supervisor supervisor){
		this.supervisor = supervisor; 
	}
	
	void generarReporteActividad(LocalDate fecha){
		
	}
	
	void agregarRepartidor(Repartidor repartidor){
		this.repartidores.add(repartidor);
	}
	
	//setters y getters
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public Supervisor getEncargadoArea() {
		return encargadoArea;
	}

	public void setEncargadoArea(Supervisor encargadoArea) {
		this.encargadoArea = encargadoArea;
	}

	public ArrayList<Repartidor> getRepartidores() {
		return repartidores;
	}

	public void setRepartidores(ArrayList<Repartidor> repartidores) {
		this.repartidores = repartidores;
	}

	
}