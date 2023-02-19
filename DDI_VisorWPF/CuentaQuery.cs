using MySql.Data.MySqlClient;
using Org.BouncyCastle.Utilities.Collections;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DDI_VisorWPF
{
    internal class CuentaQuery
    {
        // Variables
        private DatabaseConnection DBConnection;
        private List<Cuenta> CuentaList;

        public CuentaQuery()
        {
            DBConnection = new DatabaseConnection();
            CuentaList= new List<Cuenta>();
        }

        /// <summary>
        /// Carga todas las cuentas de la base de datos en una lista
        /// </summary>
        /// <returns>Lista con las cuentas de la bbdd</returns>
        public List<Cuenta> getCuentas() {
            // Carga de datos
            string Query = "SELECT * FROM cuentas";
            MySqlDataReader dataReader = null;
            try
            {
                // Conexión con la BBDD
                MySqlCommand mySqlCommand = new MySqlCommand(Query);
                mySqlCommand.Connection = DBConnection.getConnection();
                dataReader = mySqlCommand.ExecuteReader();

                // Guardado de datos en la lista de cuentas
                Cuenta cuenta = null;
                while (dataReader.Read())
                {
                    cuenta = new Cuenta(dataReader.GetInt16("id"),
                        dataReader.GetString("nombre"), dataReader.GetString("nacionalidad"),
                        dataReader.GetDateTime("fechaApertura"), dataReader.GetDouble("saldo"));
                    // Añadimos la cuenta a la lista
                    CuentaList.Add(cuenta);
                }

                dataReader.Close();
            } catch (Exception ex)
            {
                Console.WriteLine("Error: " + ex.Message);
                throw; // Lanza la excepción
            }
            return CuentaList;
        }

        /// <summary>
        /// Añade una cuenta a la base de datos
        /// </summary>
        /// <param name="cuenta">Cuenta que se va a añadir</param>
        /// <returns>true si la inserción se ha realizado correctamente</returns>
        internal bool addCuenta(Cuenta cuenta)
        {
            // Consulta de inserción
            string Insert = "INSERT INTO cuentas (nombre, nacionalidad, fechaApertura, saldo) " +
                "values (@nombre, @nacionalidad, @fechaApertura, @saldo)";

            MySqlCommand mySqlCommand= new MySqlCommand(Insert, DBConnection.getConnection());

            // Definimos los parámetros
            mySqlCommand.Parameters.Add(new MySqlParameter("@nombre", cuenta.nombre));
            mySqlCommand.Parameters.Add(new MySqlParameter("@nacionalidad", cuenta.nacionalidad));
            mySqlCommand.Parameters.Add(new MySqlParameter("@fechaApertura", cuenta.fechaApertura));
            mySqlCommand.Parameters.Add(new MySqlParameter("@saldo", cuenta.saldo));

            return mySqlCommand.ExecuteNonQuery() > 0;
        }

        /// <summary>
        /// Actualiza una cuenta a la base de datos
        /// </summary>
        /// <param name="cuenta">Cuenta que se va a actualizar</param>
        /// <returns>true si la actualización se ha realizado correctamente</returns>
        internal bool updateCuenta(Cuenta cuenta)
        {
            // Consulta de la actualización
            string Update = "UPDATE cuentas SET " +
                "nombre=@nombre, " +
                "nacionalidad=@nacionalidad, " +
                "fechaApertura=@fechaApertura, " +
                "saldo=@saldo " +
            "WHERE id=@id";

            MySqlCommand mySqlCommand = new MySqlCommand(Update, DBConnection.getConnection());

            // Definimos los parámetros
            mySqlCommand.Parameters.Add(new MySqlParameter("@nombre", cuenta.nombre));
            mySqlCommand.Parameters.Add(new MySqlParameter("@nacionalidad", cuenta.nacionalidad));
            mySqlCommand.Parameters.Add(new MySqlParameter("@fechaApertura", cuenta.fechaApertura));
            mySqlCommand.Parameters.Add(new MySqlParameter("@saldo", cuenta.saldo));
            mySqlCommand.Parameters.Add(new MySqlParameter("@id", cuenta.id));


            return mySqlCommand.ExecuteNonQuery() > 0;
        }

        /// <summary>
        /// Elimina una cuenta de la base de datos
        /// </summary>
        /// <param name="cuenta">Cuenta que se va a eliminar</param>
        /// <returns>true si la eliminación se ha realizado correctamente</returns>
        internal bool deleteCuenta(Cuenta cuenta)
        {
            string Delete = "DELETE FROM cuentas WHERE id=@id";

            MySqlCommand mySqlCommand = new MySqlCommand(Delete, DBConnection.getConnection());

            mySqlCommand.Parameters.Add(new MySqlParameter("@id", cuenta.id));

            return mySqlCommand.ExecuteNonQuery() > 0;
        }
    }
}
