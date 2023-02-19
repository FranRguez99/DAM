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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace DDI_VisorWPF
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private List<Cuenta> CuentaList;
        private CuentaQuery cuentaQuery;
        private Cuenta selected;
        public MainWindow()
        {
            InitializeComponent();
            CuentaList= new List<Cuenta>();
            cuentaQuery= new CuentaQuery();

            // Ocultamos los botones iniciales
            btnBack.Visibility = Visibility.Collapsed;
            btnInsert.Visibility = Visibility.Collapsed;
            btnForward.Visibility = Visibility.Visible;

            loadCuentas();
            selected = CuentaList[0];
            readCuenta(selected);
            numCuentas();
            cuentas50k();
        }

        private void loadCuentas()
        {
            // Cargamos la lista
            CuentaList.Clear();
            CuentaList = cuentaQuery.getCuentas();
        }

        private void btnInsert_Click(object sender, RoutedEventArgs e)
        {
            Form form = new Form(false);
            form.ShowDialog();
            loadCuentas();
            selected = CuentaList[CuentaList.Count - 1];
            readCuenta(selected);
            numCuentas();
            cuentas50k();
        }

        private void readCuenta(Cuenta cuenta)
        {
            selected = cuenta;
            tfId.Text = cuenta.id.ToString();
            tfTitular.Text = cuenta.nombre;
            tfNacionalidad.Text = cuenta.nacionalidad;
            tfFechaApertura.Text = cuenta.fechaApertura.ToString("dd/MM/yyyy");
            tfSaldo.Text = cuenta.saldo.ToString();
        }

        private void numCuentas()
        {
            tfCuentas.Text = CuentaList.Count().ToString();
        }

        private int cuentas50k()
        {
            int count = 0;
            for (int i = 0; i < CuentaList.Count; i++)
            {
                if (CuentaList[i].saldo >= 50000)
                {
                    count++;
                }
            }
            tfMas50k.Text = count.ToString();
            return count;
        }

        private void btnForward_Click(object sender, RoutedEventArgs e)
        {
            int index = 0;
            for (int i = 0; i < CuentaList.Count; i++)
            {
                if (tfId.Text == CuentaList[i].id.ToString())
                {
                    index = i + 1;
                }
            }
            selected = CuentaList[index];
            readCuenta(selected);
            btnBack.Visibility= Visibility.Visible;
            if (index == CuentaList.Count - 1)
            {
                btnForward.Visibility = Visibility.Collapsed;
                btnInsert.Visibility = Visibility.Visible;
            }
        }

        private void btnBack_Click(object sender, RoutedEventArgs e)
        {
            int index = 0;
            for (int i = 0; i < CuentaList.Count; i++)
            {
                if (tfId.Text == CuentaList[i].id.ToString())
                {
                    index = i - 1;
                }
            }
            selected = CuentaList[index];
            readCuenta(selected);
            if (index == 0)
            {
                btnBack.Visibility = Visibility.Collapsed;
            }
            btnForward.Visibility = Visibility.Visible;
            btnInsert.Visibility = Visibility.Collapsed;
        }

        private void btnUpdate_Click(object sender, RoutedEventArgs e)
        {
            int index = 0;
            for (int i = 0; i < CuentaList.Count; i++)
            {
                if (tfId.Text == CuentaList[i].id.ToString())
                {
                    index = i;
                }
            }
            Form form = new Form(true, index);
            form.ShowDialog();
            loadCuentas();
            selected = CuentaList[index]; 
            readCuenta(selected);
            numCuentas();
            cuentas50k();
        }

        private void btnDelete_Click(object sender, RoutedEventArgs e)
        {
            cuentaQuery.deleteCuenta(selected);
            loadCuentas();
            selected = CuentaList[0];
            readCuenta(selected);
            numCuentas();
            cuentas50k();
        }
    }
}
