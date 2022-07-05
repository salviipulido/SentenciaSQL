/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.persistencia;

import java.util.List;
import modelo.Equipo;

/**
 *
 * @author Rafa
 */
public interface EquipoDAO {

    Equipo read(String pk);

    void create(Equipo equipo);

    void update(Equipo equipo);

    void delete(Equipo equipo);
    
    //void createAMP(Equipo equipo);
    
    int num_equipos(String grupo);
    int posRepetidas(String grupo, String pos_grupo);

    List<Equipo> list();

}
