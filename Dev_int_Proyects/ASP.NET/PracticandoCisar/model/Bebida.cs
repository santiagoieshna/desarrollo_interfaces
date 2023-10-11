using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PracticandoCisar.model
{
    internal class Bebida
    {
        private string Nombre;
        private short Litros { get; set; }

        public Bebida(string nombre, short litros)
        {
            Nombre = nombre;
            Litros = litros;
        }

        public string MyNombre{
                get { return Nombre; }
                set { Nombre = value; }
                }
        //public int MyNombre { get { return Nombre; } set { return Nombre; } }
    }
}
