/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.EquipoController;
import java.util.List;
import modelo.persistencia.EquipoDAO;
import modelo.persistencia.JDBC.EquipoDAOJDBC;

/**
 *
 * @author eps
 */
public class EquipoModelImpl implements EquipoModel {

    private EquipoController c;

    @Override
    public EquipoController getController() {
        return c;
    }

    @Override
    public void setController(EquipoController controller) {
        this.c = controller;
    }

    @Override
    public void nuevoEquipo(Equipo contacto) {
        EquipoDAO dao = new EquipoDAOJDBC();
        dao.create(contacto);
        c.fireDataModelChanged();
    }

    @Override
    public Equipo obtenerEquipo(String nombre) {
        EquipoDAO dao = new EquipoDAOJDBC();
        return dao.read(nombre);
    }

    @Override
    public void eliminarEquipo(Equipo contacto) {
        EquipoDAO dao = new EquipoDAOJDBC();
        dao.delete(contacto);
        c.fireDataModelChanged();
    }

    @Override
    public void actualizarEquipo(Equipo contacto) {
        EquipoDAO dao = new EquipoDAOJDBC();
        dao.update(contacto);
        c.fireDataModelChanged();
    }

    @Override
    public List<Equipo> obtenerEquipos() {
        EquipoDAO dao = new EquipoDAOJDBC();
        return dao.list();

    }

    //metodo que nos devolverá el número de equipos que hay en un grupo
    @Override
    public int num_equipos(String grupo) {
        EquipoDAO dao = new EquipoDAOJDBC();
        return dao.num_equipos(grupo);
    }

    //metodo que nos devolverá el número de posiciones igual a la posicion pasada por parametro dentro del grupo especificado
    @Override
    public int posRepetidas(String grupo, String pos_grupo) {
        EquipoDAO dao = new EquipoDAOJDBC();
        return dao.posRepetidas(grupo, pos_grupo);
    }

}
