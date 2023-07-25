import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class MesasBean {

    @ManagedProperty("#{mesasService}")
    private MesasService mesasService; // Inyecta el servicio de mesas

    private List<Mesa> listaMesas;

    private Mesa mesaSeleccionada;

    public void init() {
        listaMesas = mesasService.obtenerListaMesas();
    }

    public List<Mesa> getListaMesas() {
        return listaMesas;
    }

    public void setListaMesas(List<Mesa> listaMesas) {
        this.listaMesas = listaMesas;
    }

    public Mesa getMesaSeleccionada() {
        return mesaSeleccionada;
    }

    public void setMesaSeleccionada(Mesa mesaSeleccionada) {
        this.mesaSeleccionada = mesaSeleccionada;
    }

    public void agregarMesa() {
        // Lógica para agregar una mesa
        mesasService.agregarMesa(mesaSeleccionada);
        mesaSeleccionada = new Mesa(); // Reinicializar la mesa seleccionada
        listaMesas = mesasService.obtenerListaMesas(); // Actualizar la lista de mesas
    }

    public void editarMesa() {
        // Lógica para editar una mesa
        mesasService.editarMesa(mesaSeleccionada);
        mesaSeleccionada = new Mesa(); // Reinicializar la mesa seleccionada
        listaMesas = mesasService.obtenerListaMesas(); // Actualizar la lista de mesas
    }

    public void eliminarMesa(Mesa mesa) {
        // Lógica para eliminar una mesa
        mesasService.eliminarMesa(mesa.getId());
        listaMesas = mesasService.obtenerListaMesas(); // Actualizar la lista de mesas
    }

    // Otros métodos y lógica para interactuar con el backend

    // Getters y setters para la propiedad mesasService

    public void setMesasService(MesasService mesasService) {
        this.mesasService = mesasService;
    }

    public MesasService getMesasService() {
        return mesasService;
    }
}
