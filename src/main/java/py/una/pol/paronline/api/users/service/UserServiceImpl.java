package py.una.pol.paronline.api.users.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import py.una.pol.paronline.api.users.entity.User;
import py.una.pol.paronline.api.users.repository.UserRepository;
import py.una.pol.paronline.commons.domain.entity.Entity;
import py.una.pol.paronline.commons.domain.service.BaseService;

/**
 *
 * @author dlopez
 */
//@Service("userService")
public class UserServiceImpl extends BaseService<User, Integer>
        implements UserService {

    private UserRepository<User, Integer> userRepository;

    /**
     *
     * @param userRepository
     */
    //@Autowired
    public UserServiceImpl(UserRepository<User, Integer> userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) throws Exception {
        if (userRepository.containsNombreApellido(user.getNombre(), user.getApellido())) {
            throw new Exception(String.format("Ya existe un usuario con el nombre %s y el apellido %s", user.getNombre(), user.getApellido()));
        }
        
        if (userRepository.containsLoginName(user.getLoginName())) {
            throw new Exception(String.format("Ya existe un usuario con el login name %s", user.getLoginName()));
        }

        if (user.getNombre() == null || "".equals(user.getNombre())) {
            throw new Exception("El nombre del usuario no puede ser nulo o cadena vacia.");
        }
        
        if (user.getApellido() == null || "".equals(user.getApellido())) {
            throw new Exception("El apellido del usuario no puede ser nulo o cadena vacia.");
        }
        
        super.add(user);
    }


    /**
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(Integer id) throws Exception {
        userRepository.remove(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Entity findById(Integer id) throws Exception {
        return userRepository.get(id);
    }

    @Override
    public void update(User user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<User> findByNombreApellido(String nombre, String apellido) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<User> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
