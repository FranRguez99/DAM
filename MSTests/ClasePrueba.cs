using System.Collections.Generic;

namespace MSTests
{
    internal class ClasePrueba
    {
        public List<Cuenta> CuentaList { get; set; }


        public int cuentas50k()
        {
            int count = 0;
            for (int i = 0; i < CuentaList.Count; i++)
            {
                if (CuentaList[i].saldo >= 50000)
                {
                    count++;
                }
            }
            return count;
        }
    }
}