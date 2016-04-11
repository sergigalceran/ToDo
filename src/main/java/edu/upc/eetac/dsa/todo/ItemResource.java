package edu.upc.eetac.dsa.todo;

import edu.upc.eetac.dsa.todo.dao.ItemDAOImpl;
import edu.upc.eetac.dsa.todo.dao.ItemsDAO;
import edu.upc.eetac.dsa.todo.dao.ListaDAO;
import edu.upc.eetac.dsa.todo.dao.ListaDAOImpl;
import edu.upc.eetac.dsa.todo.entity.AuthToken;
import edu.upc.eetac.dsa.todo.entity.Items;
import edu.upc.eetac.dsa.todo.entity.Lista;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by Sergi1 on 10/03/2016.
 */
@Path("lista/{id}")
public class ItemResource {

    @Context
    private SecurityContext securityContext;
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(TodoMediaType.TODO_ITEM)
    public Response createItem(@PathParam("id") String listaid, @FormParam("nombre") String nombre,@Context UriInfo uriInfo) throws URISyntaxException  {
        if(nombre==null )
            throw new BadRequestException("all parameters are mandatory");
        ItemsDAO ItemsDAO = new ItemDAOImpl();
        Items item = null;
        AuthToken authenticationToken = null;
        try {
            item = ItemsDAO.createItem(securityContext.getUserPrincipal().getName(), listaid, nombre);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + item.getId());
        return Response.created(uri).type(TodoMediaType.TODO_ITEM).entity(item).build();
    }

}
