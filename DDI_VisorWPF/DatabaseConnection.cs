using MySql.Data.MySqlClient;
using System;
using System.Windows;

namespace DDI_VisorWPF
{
    public class DatabaseConnection : Connection
    {
        private MySqlConnection SqlConnection;
        private string ConnectionString;


        public DatabaseConnection()
        {
            ConnectionString = "Database=" + database +
                "; DataSource=" + server +
                "; User Id= " + user +
                "; Password=" + password;
            SqlConnection = new MySqlConnection(ConnectionString);
        }

        public MySqlConnection getConnection()
        {
            try
            {
                if (SqlConnection.State != System.Data.ConnectionState.Open)
                {
                    SqlConnection.Open();
                }

            }
            catch (Exception e)
            {
                MessageBox.Show(e.ToString());
            }

            return SqlConnection;
        }

        public void closeConexion()
        {
            SqlConnection.Close();
        }
    }
}
