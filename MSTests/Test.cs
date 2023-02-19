using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace MSTests
{
    
    [TestClass]
    public class Test
    {
        [TestMethod]
        public void Cuentas50k_TEST()
        {
            // Arrange
            var cuentaList = new List<Cuenta>
    {
            new Cuenta { saldo = 100000 },
            new Cuenta { saldo = 20000 },
            new Cuenta { saldo = 50000 },
            new Cuenta { saldo = 60000 },
            new Cuenta { saldo = 40000 }
    };
            var instance = new ClasePrueba { CuentaList = cuentaList };

            // Act
            int result = instance.cuentas50k();

            // Assert
            Assert.AreEqual(3, result);
        }
    }
}
