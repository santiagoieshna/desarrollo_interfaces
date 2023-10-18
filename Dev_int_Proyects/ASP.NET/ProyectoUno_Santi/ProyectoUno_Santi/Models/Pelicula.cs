using System;

public class Pelicula
{
    private DateTime publicacion1;
    private string v1;
    private int v2;
    private DateTime dateTime;
    private string v3;

    private string Titulo {  get; set; }
    private DateTime Publicacion { get => publicacion1; set => publicacion1 = value; }
	private string	Pais {  get; set; }
	private int Duracion { get; set; }

	public Pelicula()
	{

	}
	public Pelicula(string Titulo, DateTime publicacion, string pais, int duracion)
	{
		this.Titulo = Titulo;
		this.Publicacion = publicacion;
		this.Duracion = duracion;
		this.Pais = pais;
		
	}

    public Pelicula(string v1, int v2, DateTime dateTime, string v3)
    {
        this.v1 = v1;
        this.v2 = v2;
        this.dateTime = dateTime;
        this.v3 = v3;
    }
}
