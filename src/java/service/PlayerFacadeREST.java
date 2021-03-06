package service;

import entity.Player;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Michal Szpytko
 */
@Stateless
@Path("players")
public class PlayerFacadeREST extends AbstractFacade<Player> {
    @PersistenceContext(unitName = "CRUDRESTPlayerPU")
    private EntityManager em;

    public PlayerFacadeREST() {
        super(Player.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Player entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Player entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Player find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Player> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Player> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("player/{lastname}")
    @Produces({"application/xml", "application/json"})
    public Player findByLastname(@PathParam("lastname") String lastname) {
        TypedQuery<Player> query = em.createNamedQuery("Player.findByLastname", Player.class);
        query.setParameter("lastname", lastname);
        Player result = query.getSingleResult();
        return result;
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @java.lang.Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
