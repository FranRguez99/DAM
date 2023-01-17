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

namespace DDI_ViewExpenseReport
{
    
    public partial class ExpenseReportPage : Page
    {
        public ExpenseReportPage()
        {
            InitializeComponent();
        }

        // Constructor personalizado para pasar los datos de gasto
        public ExpenseReportPage(object data) : this()
        {
            // Enlace a los datos del informe de gastos.
            this.DataContext = data;
        }
    }
}
