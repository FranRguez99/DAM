using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DDI_VisorWPF
{
    internal class Cuenta
    {
        public int id { get; set; }
        public string nombre { get; set; }
        public string nacionalidad { get; set; }
        public DateTime fechaApertura { get; set; }
        public double saldo { get; set; }

        public Cuenta(int Id, string Nombre, string Nacionalidad, DateTime FechaApertura, double Saldo)
        {
            id = Id;
            nombre = Nombre;
            nacionalidad = Nacionalidad;
            fechaApertura = FechaApertura;
            saldo = Saldo;
        }

    }
}
