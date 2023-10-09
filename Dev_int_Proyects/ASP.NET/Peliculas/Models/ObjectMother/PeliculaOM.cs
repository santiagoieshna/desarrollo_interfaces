using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Peliculas.Models.ObjectMother
{
    public class PeliculaOM
    {

        public static List<Pelicula> ObtenerPeliculas()
        {
            return new List<Pelicula>()
            {
                new Pelicula("Escape Plan",230,new DateTime(2013, 12, 5),"Usa"),
                new Pelicula("Captain America: Civil War",147,new DateTime(2016,02,29),"USA"),
                new Pelicula("Harry Potter",260,new DateTime(2008, 02, 5),"España"),
                new Pelicula("Otra peli ejemplo",120,new DateTime(2000, 06, 1),"EEUU")
            };
        }
    }
}