

import java.util.ArrayList;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MesasService {
    private List<Mesa> listaMesas;

    public MesasService() {
        listaMesas = new ArrayList<>();
    }

    public List<Mesa> obtenerListaMesas() {
        try {
            URL url = new URL("http://192.168.1.47:8088/mavenproject1-1.0/api/cliente");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Aquí deberías procesar la respuesta obtenida y convertirla en objetos de la clase Mesa
                // y agregarlos a la listaMesas
            } else {
                // Manejar el caso en el que la respuesta no sea HTTP_OK
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaMesas;
    }

    public void agregarMesa(Mesa mesa) {
        try {
            URL url = new URL("http://192.168.1.47:8088/mavenproject1-1.0/api/cliente/insert");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);

            // Aquí deberías enviar los datos de la mesa al servidor para agregarla a la base de datos

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        listaMesas.add(mesa);
    }

    public void editarMesa(Mesa mesa) {
        try {
            URL url = new URL("http://192.168.1.47:8088/mavenproject1-1.0/api/cliente");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);

            // Aquí deberías enviar los datos de la mesa al servidor para actualizarla en la base de datos

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarMesa(int idMesa) {
        try {
            URL url = new URL("http://192.168.1.47:8088/mavenproject1-1.0/api/cliente/" + idMesa);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Aquí deberías procesar la respuesta obtenida para verificar que la eliminación fue exitosa
            } else {
                // Manejar el caso en el que la respuesta no sea HTTP_OK
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Aquí deberías eliminar la mesa de la listaMesas si la eliminación fue exitosa en el servidor
    }
}
