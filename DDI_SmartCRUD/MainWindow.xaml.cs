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

namespace DDI_SmartCRUD
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private List<Persona> mPersonas;
        private Persona mPersona;
        private PersonaConsultas mPersonaConsultas;
        private Persona seleccion;

        public MainWindow()
        {
            InitializeComponent();
            mPersonas= new List<Persona>();
            mPersonaConsultas= new PersonaConsultas();
            mPersona = new Persona();

            cargarPersonas();
        }

        private void cargarPersonas(string filtro = "")
        {
            dgPersonas.Items.Clear();
            dgPersonas.Items.Refresh();
            mPersonas.Clear();
            mPersonas = mPersonaConsultas.getPersonas(filtro);

            for (int i = 0; i < mPersonas.Count(); i++)
            {
                dgPersonas.Items.Add(new Persona { autoid = mPersonas[i].autoid,
                    nombre = mPersonas[i].nombre,
                    apellidos = mPersonas[i].apellidos,
                    genero = mPersonas[i].genero});
            }
        }

        private void cajaBusqueda_TextChanged(object sender, TextChangedEventArgs e)
        {
            cargarPersonas(cajaBusqueda.Text.Trim());
        }

        private void botonInsertar_Click(object sender, RoutedEventArgs e)
        {
            if (!datosCorrectos())
            {
                return;
            }

            cargarDatosPersonas();

            if (mPersonaConsultas.agregarPersona(mPersona))
            {
                MessageBox.Show("Persona agregada");
                cargarPersonas();
                limpiarCampos();
            }
        }

        private void limpiarCampos()
        {
            txtNombre.Text = "";
            txtApellidos.Text = "";
            cbGenero.Text = "";
            txtId.Text = "";
        }

        private void cargarDatosPersonas()
        {
            mPersona.autoid = getId();
            mPersona.nombre = txtNombre.Text.Trim();
            mPersona.apellidos = txtApellidos.Text.Trim();
            mPersona.genero = cbGenero.Text.Trim();
        }

        private int getId()
        {
            if (!txtId.Text.Trim().Equals(""))
            {
                if (int.TryParse(txtId.Text.Trim(), out int id))
                {
                    return id;
                }
                else return -1;
            }
            else return -1;
        }

        private bool datosCorrectos()
        {
            if (txtNombre.Text.Trim().Equals(""))
            {
                MessageBox.Show("Ingrese el nombre");
                return false;
            }

            if (txtApellidos.Text.Trim().Equals(""))
            {
                MessageBox.Show("Ingrese el apellido");
                return false;
            }

            if (cbGenero.Text.Trim().Equals(""))
            {
                MessageBox.Show("Ingrese el género");
                return false;
            }

            return true;
        }

        private void dgPersonas_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            seleccion = (Persona) dgPersonas.SelectedItem;
            if (seleccion != null)
            {
                txtId.Text = seleccion.autoid.ToString();
                txtNombre.Text = seleccion.nombre;
                txtApellidos.Text = seleccion.apellidos;
                cbGenero.Text = seleccion.genero;
            }
        }

        private void botonActualizar_Click(object sender, RoutedEventArgs e)
        {
            if (!datosCorrectos())
            {
                return;
            }

            cargarDatosPersonas();

            if (mPersonaConsultas.modificarPersona(mPersona))
            {
                MessageBox.Show("Persona modificada");
                cargarPersonas();
                limpiarCampos();
            }
        }

        private void botonBorrar_Click(object sender, RoutedEventArgs e)
        {
            if (getId() == -1)
            {
                return;
            }
            cargarDatosPersonas();

            if (MessageBox.Show("¿Desea eliminar la persona?", "Eliminar persona",
                MessageBoxButton.YesNo) == MessageBoxResult.Yes)
            {
                cargarDatosPersonas();
                if (mPersonaConsultas.eliminarPersona(mPersona))
                {
                    MessageBox.Show("Persona eliminada");
                    cargarPersonas();
                    limpiarCampos();
                }
            }
        }
    }
}
