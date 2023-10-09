using System;

public class Pelicula
{
    private DateTime publicacion1;

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
		this.Publicacion1 = publicacion;
	}
  
}
