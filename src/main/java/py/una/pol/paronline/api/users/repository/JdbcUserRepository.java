package py.una.pol.paronline.api.users.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import py.una.pol.paronline.api.users.entity.User;
import py.una.pol.paronline.commons.domain.entity.Entity;
import py.una.pol.paronline.commons.utils.DataBaseUtil;

/**
 *
 * @author Sourabh Sharma
 */
public class JdbcUserRepository implements UserRepository<User, Integer> {

    private Connection connection;
    private PreparedStatement pstmt;

    public JdbcUserRepository() {
    }
    
    public JdbcUserRepository(Connection connection, PreparedStatement pstmt) {
        this.connection = connection;
        this.pstmt = pstmt;
    }
    

    /**
     * Check if given user name already exist.
     *
     * @param nombre
     * @param apellido
     * @return true if already exist, else false
     */
    @Override
    public boolean containsNombreApellido(String nombre, String apellido) {
        try {
            return this.findByNombreApellido(nombre, apellido).size() > 0;
        } catch (Exception ex) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.FATAL, null, ex);
        }
        return false;
    }

    /**
     *
     * @param entity
     */
    @Override
    public void add(User entity) {
        
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("INSERT INTO cliente (nombre, apellido, email, login_name, passwd, tipo_cliente) values (?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getApellido());
            pstmt.setString(3, entity.getEmail());
            pstmt.setString(4, entity.getLoginName());
            pstmt.setString(5, entity.getPasswd());
            pstmt.setInt(6, entity.getTipoCliente());

            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }
    }

    /**
     *
     * @param id
     */
    @Override
    public void remove(Integer id) {
        
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("DELETE FROM cliente WHERE id_cliente = ?");

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }
    }

    /**
     *
     * @param entity
     */
    @Override
    public void update(User entity) {

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("UPDATE cliente SET nombre = ?, apellido = ?, email = ?, login_name = ?, passwd = ?, tipo_cliente = ? WHERE id_cliente = ?");

            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getApellido());
            pstmt.setString(3, entity.getEmail());
            pstmt.setString(4, entity.getLoginName());
            pstmt.setString(5, entity.getPasswd());
            pstmt.setInt(6, entity.getTipoCliente());
            pstmt.setInt(7, entity.getId());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean contains(Integer id) {
        User user = (User) get(id);
        
        return user != null;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Entity get(Integer id) {
        Entity retValue = null;
        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?");

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new User(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("login_name"), rs.getString("passwd"), rs.getInt("tipo_cliente"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }

        return retValue;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<User> getAll() {
        Collection<User> retValue = new ArrayList();
        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM cliente");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new User(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("login_name"), rs.getString("passwd"), rs.getInt("tipo_cliente")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }

        return retValue;
    }

    /**
     *
     * @param nombre
     * @param apellido
     * @return
     * @throws Exception
     */
    @Override
    public Collection<User> findByNombreApellido(String nombre, String apellido) throws Exception {
        Collection<User> retValue = new ArrayList();
        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM cliente WHERE nombre = ? and apellido = ?");

            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue.add(new User(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("login_name"), rs.getString("passwd"), rs.getInt("tipo_cliente")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }

        return retValue;
    }

    @Override
    public boolean containsLoginName(String loginName) {
        try {
            return this.findByLoginName(loginName).size() > 0;
        } catch (Exception ex) {
            Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.FATAL, null, ex);
        }
        return false;
    }

    @Override
    public Collection<User> findByLoginName(String loginName) throws Exception {
        Collection<User> retValue = new ArrayList();
        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM cliente WHERE login_name = ?");

            pstmt.setString(1, loginName);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue.add(new User(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("login_name"), rs.getString("passwd"), rs.getInt("tipo_cliente")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcUserRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }

        return retValue;
    }

}
