using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace prueba1.model
{
    internal class Persona
    {
        public string? Nombre { get; set; }
        public string Nif {  get; set; }
        public int Edad {  get; set; }

        public Persona()
        {
        }

        public Persona(string? nombre, string nif, int edad)
        {
            Nombre = nombre;
            Nif = nif;
            Edad = edad;
        }
    }
}
