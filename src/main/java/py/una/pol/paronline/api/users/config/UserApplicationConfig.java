/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.users.config;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import py.una.pol.paronline.api.users.rest.UserRestService;

/**
 *
 * @author dlopez
 */
public class UserApplicationConfig extends Application{
    
    private Set<Object> singletons = new HashSet<Object>();

    public UserApplicationConfig() {
        singletons.add(new UserRestService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
