package rs.raf.rafnews.repository.impl;

import rs.raf.rafnews.database.DatabaseUtil;
import rs.raf.rafnews.dto.user.RequestUserDto;
import rs.raf.rafnews.dto.user.ResponseUserDto;
import rs.raf.rafnews.entity.Role;
import rs.raf.rafnews.entity.User;
import rs.raf.rafnews.exception.UserNotFoundException;
import rs.raf.rafnews.repository.RoleRepository;
import rs.raf.rafnews.repository.UserRepository;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Inject
    private RoleRepository roleRepository;

    @Override
    public ResponseUserDto findById(int id) {
        String query = "SELECT id, firstname, lastname, username, status, role_id FROM User WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                boolean status = resultSet.getBoolean("status");
                int roleId = resultSet.getInt("role_id");
                return new ResponseUserDto(id, firstname, lastname, username, roleId, status);
            } else {
                throw new UserNotFoundException("User with id: " + id + " not found.");
            }
        } catch (SQLException | UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public List<ResponseUserDto> findAll() {
        List<ResponseUserDto> userList = new ArrayList<>();
        String query = "SELECT id, username, firstname, lastname, status, role_id FROM User";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                boolean status = resultSet.getBoolean("status");
                int roleId = resultSet.getInt("role_id");
                ResponseUserDto user = new ResponseUserDto(id, firstname, lastname, username, roleId, status);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public int insert(RequestUserDto object) {
        String query = "INSERT INTO User (username, password, firstname, lastname, status, role_id) values (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, object.getUsername());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getFirstname());
            statement.setString(4, object.getLastname());
            statement.setBoolean(5, true);
            statement.setInt(6, object.getRole());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Insert successful");
            } else {
                System.out.println("Insert failed");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM User WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Delete successful");
            } else {
                System.out.println("Delete failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public void update(RequestUserDto object) {
        String query = "UPDATE User SET username = ?, firstname = ?, lastname = ?, role_id = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, object.getUsername());
            statement.setString(2, object.getFirstname());
            statement.setString(3, object.getLastname());
            statement.setInt(4, object.getRole());
            statement.setInt(5, object.getId());
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Update successful");
            } else {
                throw new UserNotFoundException("User with id: " + object.getId() + " not found.");
            }
        } catch (SQLException | UserNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public void activateUserById(int id) {
        String query = "UPDATE User SET status = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, true);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Update successful");
            } else {
                throw new UserNotFoundException("User with id: " + id + " not found.");
            }
        } catch (SQLException | UserNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public void deactivateUserById(int id) {
        String query = "UPDATE User SET status = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, false);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Update successful");
            } else {
                throw new UserNotFoundException("User with id: " + id + " not found.");
            }
        } catch (SQLException | UserNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM User WHERE username = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                boolean status = resultSet.getBoolean("status");
                Role role = roleRepository.findRoleById(resultSet.getInt("role_id"));
                return new User(id, username, password, firstname, lastname, status , role);
            } else {
                throw new UserNotFoundException("User with username: " + username + " not found.");
            }
        } catch (SQLException | UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }
}
