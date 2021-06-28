package sk.fri.uniza.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;
import sk.fri.uniza.db.IotNodeDAO;
import sk.fri.uniza.model.IotNode;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Api("/IoTNode")
@Path("/IoTNode")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

// Určené na konfigurovanie swagger dokumentačného nástroja
@SwaggerDefinition(
        info = @Info(
                description = "IoT Node data",
                version = "V1.0.0",
                title = "IoT Node service",
                contact = @Contact(
                        name = "Tomas Pocklan",
                        email = "pocklan@fri.uniza.sk"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP}

)


public class IoTNodeResource {

    private IotNodeDAO iotNodeDAO;

    public IoTNodeResource(IotNodeDAO iotNodeDAO) {
        this.iotNodeDAO = iotNodeDAO;
    }

    @POST /*JAX-RS*/
    @UnitOfWork //Otvorí novú hibernate session // Dropwizard
    @ApiOperation(value = "Create IoT node.")
    public IotNode createIotNode(IotNode iotNode) {
        return iotNodeDAO.create(iotNode);
    }
    @PUT /*JAX-RS*/
    @UnitOfWork //Otvorí novú hibernate session // Dropwizard
    @ApiOperation(value = "Update IoT node.")
    public IotNode updateIotNode(IotNode iotNode) {
        return iotNodeDAO.update(iotNode);
    }

    @GET //HTTP metóda
    @Path("{id}") // Jedna vetva hlavnej adresy /household
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Find IoT node.")
    public IotNode findIotNode(Long id) {
        return iotNodeDAO.findById(id);
    }

    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "All IoT nodes.")
    public List<IotNode> allIotNodes() {
        return iotNodeDAO.allIotNodes();
    }

}
