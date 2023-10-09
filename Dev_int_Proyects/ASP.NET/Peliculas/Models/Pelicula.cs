using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Peliculas.Models
{
    public class Pelicula
    {
        private string Titulo {  get; set; }
        private DateTime Publicaciones { get; set; }
        private string Pais {  get; set; }
        private int Duracion {  get; set; }
        public Pelicula(string titulo, int duracion, DateTime publicaciones, string pais)
        {
            Titulo = titulo;
            Publicaciones = publicaciones;
            Pais = pais;
            Duracion = duracion;
        }
    }
}