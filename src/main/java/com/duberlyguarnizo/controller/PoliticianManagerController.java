package com.duberlyguarnizo.controller;

import com.duberlyguarnizo.model.PoliticiansManager;
import com.duberlyguarnizo.repository.PoliticianManagerRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Produces("application/json")
@Consumes("application/json")
@Transactional
@Path("/politicians")
public class PoliticianManagerController {
    @Inject
    PoliticianManagerRepository politicianManagerRepository;

    @GET
    @Path("/{id}")
    public PoliticiansManager getPoliticianById(@PathParam("id") Long id) {

        return politicianManagerRepository.findPoliticianById(id);
    }

    @GET
    @Path("/all")
    public List<PoliticiansManager> getAllPoliticians() {
        return StreamSupport.stream(politicianManagerRepository.findAllPoliticians().spliterator(), false)
                .collect(Collectors.toList());
    }

    @POST
    public void createPolitician(PoliticiansManager politician) {
        politicianManagerRepository.createPolitician(politician);
    }

    @PUT
    @Path("/{id}")
    public PoliticiansManager updatePolitician(@PathParam("id") Long id, PoliticiansManager politician) {
        if (politician.getId() != null && !politician.getId().equals(id)) {
            throw new BadRequestException("The politician ID cannot be modified");
        }
        PoliticiansManager politicianToUpdate = politicianManagerRepository.findPoliticianById(id);
        politicianToUpdate.setName(politician.getName());
        politicianToUpdate.setLastName(politician.getLastName());
        politicianToUpdate.setEmail(politician.getEmail());
        politicianToUpdate.setCountry(politician.getCountry());
        politicianToUpdate.setAge(politician.getAge());
        return politicianManagerRepository.updatePolitician(politicianToUpdate);
    }

    @DELETE
    @Path("/{id}")
    public void deletePolitician(@PathParam("id") Long id) {
        //TODO: check if the politician exists
        politicianManagerRepository.deletePolitician(id);
    }
}
