using CRUD.BD;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DDI_SmartCRUD
{
    internal class PersonaConsultas
    {
        private ConexionMySql conexionMySql;
        private List<Persona> mPersonas;

        public PersonaConsultas()
        {
            conexionMySql= new ConexionMySql();
            mPersonas = new List<Persona>();
        }

        public List<Persona> getPersonas(string filtro)
        {
            string QUERY = "SELECT * FROM tb_smart_crud ";
            MySqlDataReader mReader = null;
            try
            {
                if (filtro != "")
                {
                    QUERY += " WHERE " +
                        "autoid LIKE '%" + filtro + "%' OR " +
                        "nombre LIKE '%" + filtro + "%' OR " +
                        "apellidos LIKE '%" + filtro + "%' OR " +
                        "genero LIKE '%" + filtro + "%';";
                }

                MySqlCommand mComando = new MySqlCommand(QUERY);
                mComando.Connection = conexionMySql.getConexion();
                mReader = mComando.ExecuteReader();

                Persona mPersona = null;
                while(mReader.Read())
                {
                    mPersona = new Persona();
                    mPersona.autoid = mReader.GetInt16("autoid");
                    mPersona.nombre = mReader.GetString("nombre");
                    mPersona.apellidos = mReader.GetString("apellidos");
                    mPersona.genero = mReader.GetString("genero");
                    // Añadimos la persona a la lista
                    mPersonas.Add(mPersona);
                }

                mReader.Close();

            } catch (Exception ex)
            {
                Console.WriteLine("Error: " + ex.Message);
                throw; // rethrow the exception
            }
            return mPersonas;
        }

        internal bool agregarPersona(Persona mPersona)
        {
            string INSERT = "INSERT INTO tb_smart_crud (nombre, apellidos, genero) " +
                "values (@nombre,@apellidos,@genero)";

            MySqlCommand mCommand= new MySqlCommand(INSERT, conexionMySql.getConexion());

            mCommand.Parameters.Add(new MySqlParameter("@nombre", mPersona.nombre));
            mCommand.Parameters.Add(new MySqlParameter("@apellidos", mPersona.apellidos));
            mCommand.Parameters.Add(new MySqlParameter("@genero", mPersona.genero));

            return mCommand.ExecuteNonQuery() > 0;
        }

        internal bool eliminarPersona(Persona mPersona)
        {
            string DELETE = "DELETE FROM tb_smart_crud WHERE autoid=@autoid";

            MySqlCommand mCommand = new MySqlCommand(DELETE, conexionMySql.getConexion());

            mCommand.Parameters.Add(new MySqlParameter("@autoid", mPersona.autoid));

            return mCommand.ExecuteNonQuery() > 0;
        }

        internal bool modificarPersona(Persona mPersona)
        {
            string UPDATE = "UPDATE tb_smart_crud SET " +
                "nombre=@nombre, " +
                "apellidos=@apellidos, " +
                "genero=@genero " +
                "WHERE autoid=@autoid";

            MySqlCommand mCommand = new MySqlCommand(UPDATE, conexionMySql.getConexion());

            mCommand.Parameters.Add(new MySqlParameter("@nombre", mPersona.nombre));
            mCommand.Parameters.Add(new MySqlParameter("@apellidos", mPersona.apellidos));
            mCommand.Parameters.Add(new MySqlParameter("@genero", mPersona.genero));
            mCommand.Parameters.Add(new MySqlParameter("@autoid", mPersona.autoid));

            return mCommand.ExecuteNonQuery() > 0;
        }
    }
}
