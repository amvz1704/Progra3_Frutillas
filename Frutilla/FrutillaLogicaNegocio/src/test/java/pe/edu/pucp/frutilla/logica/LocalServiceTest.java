package pe.edu.pucp.frutilla.logica;


import pe.edu.pucp.frutilla.crud.dao.local.LocalDAO;
import pe.edu.pucp.frutilla.logica.local.LocalService;
import pe.edu.pucp.frutilla.models.local.Local;


class LocalServiceTest {

    private LocalDAO localDAO;               // mock del DAO

    private LocalService localService;       // clase bajo prueba

    private Local ejemplo;

    void init() {
        ejemplo = new Local("Ciencias Sociales", "Dentro de Campus PUCP", "Av. Universitario 123", "995777011");
        
    }

 
}
