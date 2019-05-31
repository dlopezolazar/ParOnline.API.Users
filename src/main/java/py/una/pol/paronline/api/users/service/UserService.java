package py.una.pol.paronline.api.users.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import py.una.pol.paronline.commons.domain.entity.Entity;
import py.una.pol.paronline.commons.domain.entity.users.User;

/**
 *
 * @author Sourabh Sharma
 */
public interface UserService {

    /**
     *
     * @param user
     * @throws Exception
     */
    public void add(User user) throws Exception;

    /**
     *
     * @param user
     * @throws Exception
     */
    public void update(User user) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(Integer id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Entity findById(Integer id) throws Exception;

    /**
     *
     * @param nombre
     * @param apellido
     * @return
     * @throws Exception
     */
    public Collection<User> findByNombreApellido(String nombre, String apellido) throws Exception;

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<User> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
    
    /**
     *
     * @param nombre
     * @param password
     * @return
     * @throws Exception
     */
    public Entity authenticate(String nombre, String password) throws Exception;
}
