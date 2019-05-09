package py.una.pol.paronline.api.users.entity;

import lombok.Data;
import py.una.pol.paronline.commons.domain.entity.BaseEntity;

/**
 *
 * @author dlopez
 */
@Data
public class User extends BaseEntity<Integer> {

    private String apellido;
    private String email;
    private String loginName;
    private String passwd;
    private int tipoCliente;

    /**
     *
     * @param nombre
     * @param id
     * @param apellido
     * @param email
     * @param loginName
     * @param passwd
     * @param tipoCliente
     */
    public User(Integer id, String nombre, String apellido, String email, String loginName, String passwd, int tipoCliente) {
        super(id, nombre);
        this.apellido = apellido;
        this.email = email;
        this.loginName = loginName;
        this.passwd = passwd;
        this.tipoCliente = tipoCliente;
    }

}
