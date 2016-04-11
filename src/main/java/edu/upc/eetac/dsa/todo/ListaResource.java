package edu.upc.eetac.dsa.todo;

import edu.upc.eetac.dsa.todo.dao.ListaDAO;
import edu.upc.eetac.dsa.todo.dao.ListaDAOImpl;
import edu.upc.eetac.dsa.todo.entity.AuthToken;
import edu.upc.eetac.dsa.todo.entity.Lista;
import edu.upc.eetac.dsa.todo.entity.ListaCollection;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by Sergi1 on 02/03/2016.
 */
@Path("lista")
public class ListaResource {
    @Context
    private SecurityContext securityContext;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(TodoMediaType.TODO_LISTA)
    public Response createSting(@FormParam("title") String title, @Context UriInfo uriInfo) throws URISyntaxException {
        if(title==null )
            throw new BadRequestException("all parameters are mandatory");
        ListaDAO ListaDAO = new ListaDAOImpl();
        Lista lista = null;
        AuthToken authenticationToken = null;
        try {
            lista = ListaDAO.createLista(securityContext.getUserPrincipal().getName(), title);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + lista.getId());
        return Response.created(uri).type(TodoMediaType.TODO_LISTA).entity(lista).build();
    }
    @GET
    @Produces(TodoMediaType.TODO_LISTA_COLLECTION)
    public ListaCollection getStings(){
        ListaCollection listaCollection = null;
        ListaDAO listaDAO = new ListaDAOImpl();
        try {
            listaCollection = listaDAO.getListas();
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }

        return listaCollection;
    }

    @Path("/{id}")
    @GET
    @Produces(TodoMediaType.TODO_LISTA_COLLECTION)
    public Lista getSting(@PathParam("id") String id){
        Lista lista = null;
        ListaDAO stingDAO = new ListaDAOImpl();
        try {
            lista = stingDAO.getListaById(id);
            if(lista == null)
                throw new NotFoundException("Lista with id = "+id+" doesn't exist");
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        return lista;
    }
    @Path("/{id}")
    @PUT
    @Consumes(TodoMediaType.TODO_LISTA)
    @Produces(TodoMediaType.TODO_LISTA)
    public Lista updateLista(@PathParam("id") String id, Lista lista) {
        if(lista == null)
            throw new BadRequestException("entity is null");
        if(!id.equals(lista.getId()))
            throw new BadRequestException("path parameter id and entity parameter id doesn't match");

        String userid = securityContext.getUserPrincipal().getName();
        if(!userid.equals(lista.getUserid()))
            throw new ForbiddenException("operation not allowed");

        ListaDAO listaDAO = new ListaDAOImpl();
        try {
            String newtitle = lista.getTitle();
            lista = listaDAO.updateLista(id, newtitle);
            if(lista == null)
                throw new NotFoundException("Lista with id = "+id+" doesn't exist");
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        return lista;
    }
    @Path("/{id}")
    @DELETE
    public void deleteLista(@PathParam("id") String id) {
        String userid = securityContext.getUserPrincipal().getName();
        ListaDAO stingDAO = new ListaDAOImpl();
        try {
            String ownerid = stingDAO.getListaById(id).getUserid();
            if(!userid.equals(ownerid))
                throw new ForbiddenException("operation not allowed");
            if(!stingDAO.deleteLista(id))
                throw new NotFoundException("Lista with id = "+id+" doesn't exist");
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
    }

}
