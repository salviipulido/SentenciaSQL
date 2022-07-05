/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.persistencia.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.EquipoImpl;
import modelo.Equipo;
import modelo.persistencia.EquipoDAO;

/**
 *
 * @author Rafa
 */
public class EquipoDAOJDBC implements EquipoDAO {

    public void create(Equipo equipo) {
        String sql = "insert into equipos(nombre,pais,pos_grupo,grupo) values (?,?,?,?)";
        try {
            PreparedStatement stm = Persistencia.createConnection().prepareStatement(sql);
            stm.setString(1, equipo.getNombre());
            stm.setString(2, equipo.getPais());
            stm.setString(3, equipo.getPos_grupo());
            stm.setString(4, equipo.getGrupo());
            stm.execute();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Persistencia.closeConnection();
        }

    }

    public Equipo read(String pk) {
        Equipo c = null;
        try {
            Statement stmt = Persistencia.createConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM equipos where nombre=" + pk);
            String nombre, pais, pos_grupo, grupo;
            if (res.next()) {
                nombre = res.getString("nombre");
                pais = res.getString("pais");
                pos_grupo = res.getString("pos_grupo");
                grupo = res.getString("grupo");
                c = new EquipoImpl(nombre, pais, pos_grupo, grupo);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Persistencia.closeConnection();
        }
        return c;
    }

    public void update(Equipo contacto) {
        String sql = "UPDATE equipos set pais=?, grupo=?,pos_grupo=? WHERE nombre=? ";
        try {
            PreparedStatement stmt = Persistencia.createConnection().prepareStatement(sql);
            stmt.setString(4, contacto.getNombre());
            stmt.setString(1, contacto.getPais());
            stmt.setString(2, contacto.getGrupo());
            stmt.setString(3, contacto.getPos_grupo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Persistencia.closeConnection();
        }
    }
    
    public void delete(Equipo contacto) {
        String sql = "DELETE FROM equipos WHERE nombre = ?";
        try {
            PreparedStatement stmt = Persistencia.createConnection().prepareStatement(sql);
            stmt.setString(1, contacto.getNombre());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Persistencia.closeConnection();
        }
    }

    public List<Equipo> list() {
        List<Equipo> equipos = new ArrayList<Equipo>();

        try {
            Statement stmt = Persistencia.createConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM equipos ");
            String nombre, pais, pos_grupo, grupo;
            while (res.next()) {
                nombre = res.getString("nombre");
                pais = res.getString("pais");
                pos_grupo = res.getString("pos_grupo");
                grupo = res.getString("grupo");
                equipos.add(new EquipoImpl(nombre, pais, pos_grupo, grupo));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Persistencia.closeConnection();
        }
        return equipos;
    }
    
    @Override
    //vemos cuantos equipos hay en el grupo G, si hay mas de 2, el controlador
    //no ejercerá el insert
    public int num_equipos(String grupo){
        String sql = "SELECT COUNT(*) as num_equipos from equipos where grupo = ?";
        int num = 0;
        try {
            PreparedStatement stm = Persistencia.createConnection().prepareStatement(sql);
            stm.setString(1, grupo);
            ResultSet res = stm.executeQuery();
            res.next();
            num = res.getInt("num_equipos");
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            Persistencia.closeConnection();
        }
        return num;
    }
    
    //vemos cuantos equipos hay en la posicion P en el grupo G, en el momento que haya mas de 1, el controlador
    //no ejercerá el insert
    public int posRepetidas(String grupo, String pos_grupo){
         String sql2 = "SELECT COUNT(*) AS pos FROM equipos WHERE grupo = ? AND pos_grupo = ? ";
            int pos = -1;
            try {
                PreparedStatement stm = Persistencia.createConnection().prepareStatement(sql2);
                stm.setString(1, grupo);
                stm.setString(2, pos_grupo);
                ResultSet res = stm.executeQuery();
                res.next();
                pos = res.getInt("pos");
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                Persistencia.closeConnection();
            }
            return pos;
    }
}
