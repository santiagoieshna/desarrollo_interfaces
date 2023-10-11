using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PracticandoCisar.model
{
    internal class Zumo : Bebida
    {
        private string Fruta {get;set;}

        public Zumo(string fruta, short litros, string nombre = "Zumito"):base(nombre, litros)
        {
           this.Fruta = fruta;
        }
    }
}
