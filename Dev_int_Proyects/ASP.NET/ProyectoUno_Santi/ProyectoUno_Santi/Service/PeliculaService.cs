using ProyectoUno_Santi.Models.ObjectMother;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;


namespace ProyectoUno_Santi.Service
{
    public class PeliculaService
    {
        private List<Pelicula> Peliculas { get; set; }

        public PeliculaService() {
            this.Peliculas = PeliculaOM.ObtenerPeliculas();
        }

    }
}