package org.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/employees")

public class EmployeeWebService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAllEmployees() {
        try (Connection connection = OracleDBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employees")) {
            List<Employee> employees = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String position = resultSet.getString("position");

                Employee employee = new Employee(id, name, age, position);
                employees.add(employee);
            }

            return employees;
        } catch (SQLException e) {
            e.printStackTrace();  // Imprimir la traza de error
            // Manejo de excepciones, por ejemplo, lanzar una excepción personalizada o devolver un código de error
            // Aquí, simplemente se devuelve una lista vacía
            return new ArrayList<>();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeById(@PathParam("id") int id) {
        try (Connection connection = OracleDBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String position = resultSet.getString("position");

                return new Employee(id, name, age, position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createEmployee(Employee employee) {
        try (Connection connection = OracleDBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO employees (name, age, position) VALUES (?, ?, ?)");
        ) {
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getAge());
            statement.setString(3, employee.getPosition());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateEmployee(@PathParam("id") int id, Employee employee) {
        try (Connection connection = OracleDBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE employees SET name = ?, age = ?, position = ? WHERE id = ?");
        ) {
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getAge());
            statement.setString(3, employee.getPosition());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @DELETE
    @Path("/{id}")
    public void deleteEmployee(@PathParam("id") int id) {
        try (Connection connection = OracleDBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
