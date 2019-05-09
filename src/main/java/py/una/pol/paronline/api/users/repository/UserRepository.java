package py.una.pol.paronline.api.users.repository;

import java.util.Collection;
import py.una.pol.paronline.commons.domain.repository.Repository;

/**
 *
 * @author dlopez
 * @param <User> Object type Repository
 * @param <Integer> Pk data type
 */
public interface UserRepository<User, Integer> extends Repository<User, Integer> {

    /**
     *
     * @param nombre
     * @param apellido
     * @return
     */
    boolean containsNombreApellido(String nombre, String apellido);

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
     * @param loginName
     * @return
     */
    boolean containsLoginName(String loginName);

    /**
     *
     * @param loginName
     * @return
     * @throws Exception
     */
    public Collection<User> findByLoginName(String loginName) throws Exception;
}
