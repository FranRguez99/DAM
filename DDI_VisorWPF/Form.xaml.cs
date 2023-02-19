using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace DDI_VisorWPF
{
    /// <summary>
    /// Interaction logic for Form.xaml
    /// </summary>
    public partial class Form : Window
    {
        bool edit;
        int index;
        CuentaQuery cuentaQuery = new CuentaQuery();
        List<Cuenta> CuentaList;
        public Form(bool edit)
        {
            InitializeComponent();
            this.edit = edit;
        }

        public Form(bool edit, int index)
        {
            InitializeComponent();
            this.edit = edit;
            this.index = index;
            CuentaList = cuentaQuery.getCuentas();
            readCuenta(CuentaList[index]);

        }

        private void readCuenta(Cuenta cuenta)
        {
            tfId.Text = cuenta.id.ToString();
            tfTitular.Text = cuenta.nombre;
            tfNacionalidad.Text = cuenta.nacionalidad;
            dpFecha.SelectedDate = cuenta.fechaApertura;
            tfSaldo.Text = cuenta.saldo.ToString();
        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            Button button = (Button)sender;
            Window window = Window.GetWindow(button);
            window.Close();
        }

        private void btnAccept_Click(object sender, RoutedEventArgs e)
        {
            if (dpFecha.SelectedDate.HasValue)
            {
                Cuenta cuenta = new Cuenta(int.Parse(tfId.Text), tfTitular.Text, tfNacionalidad.Text,
                dpFecha.SelectedDate.Value, double.Parse(tfSaldo.Text));
                
                if (edit) // UPDATE
                {
                    if (cuentaQuery.updateCuenta(cuenta))
                    {
                        Button button = (Button)sender;
                        Window window = Window.GetWindow(button);
                        window.Close();
                    }
                } else // INSERT
                {
                    if (cuentaQuery.addCuenta(cuenta))
                    {
                        Button button = (Button)sender;
                        Window window = Window.GetWindow(button);
                        window.Close();
                    }
                }

            }
            
        }
    }
}
