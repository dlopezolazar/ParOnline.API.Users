/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.users.rest;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import py.una.pol.paronline.api.users.entity.User;
import py.una.pol.paronline.api.users.repository.JdbcUserRepository;
import py.una.pol.paronline.api.users.service.UserServiceImpl;

/**
 *
 * @author dlopez
 */
@Path("/v1/users")
public class UserRestService {
    private final UserServiceImpl userService = new UserServiceImpl(new JdbcUserRepository());

    @GET
    @Path("/")
    @Produces("application/json")
    public ArrayList<User> getUsers() {
        ArrayList<User> users = (ArrayList) userService.getAll();
        return users;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public User getUser(@PathParam("id") Integer id) {
        User entity = null;
        try {
            entity = (User) userService.findById(id);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public User addUser(User entity) {
        try {
            userService.add(entity);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public void updateUser(User entity) {
        try {
            userService.update(entity);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/{id}")
    public void removeUser(@PathParam("id") Integer id) {
        try {
            userService.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
