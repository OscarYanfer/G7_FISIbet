package com.microservicio.fisibet.aplication.controller;


import com.microservicio.fisibet.aplication.request.CreateEventRequest;
import com.microservicio.fisibet.infraestructure.model.EventModel;
import com.microservicio.fisibet.infraestructure.port.spring.BetSpringPort;
import com.microservicio.fisibet.infraestructure.port.spring.EventSpringPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductoGraphQLController {

    @Autowired
    private EventSpringPort eventSpringPort;

    @Autowired
    private BetSpringPort betSpringPort;

    @QueryMapping
    public List<EventModel> listarEventos(){
        return eventSpringPort.findAll();
    }

    @QueryMapping
    public EventModel listarEventoPorId(@Argument Long id){
        return eventSpringPort.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Producto %s no encontrado",id))
        );
    }

    @MutationMapping
    public EventModel guardarEvento(@Argument CreateEventRequest createEventRequest){
        EventModel eventModel = new EventModel();
        eventModel.setName(createEventRequest.getName());
        eventModel.setDescription(createEventRequest.getDescription());
        eventModel.setStatus(1);
        eventModel.setRegistered(LocalDateTime.now());

        return eventSpringPort.save(eventModel);
    }

    /*@MutationMapping
    public Producto actualizarProducto(@Argument String id,@Argument ProductoRequest productoRequest){
        Categoria categoria = categoriaRepository.findById(productoRequest.categoriaId()).orElse(null);
        Producto productoBBDD = new Producto();
        productoBBDD.setId(id);
        productoBBDD.setNombre(productoRequest.nombre());
        productoBBDD.setPrecio(productoRequest.precio());
        productoBBDD.setCantidad(productoRequest.cantidad());
        productoBBDD.setCategoria(categoria);

        return productoRepository.save(productoBBDD);
    }

    @MutationMapping
    public void eliminarProducto(@Argument String id){
        productoRepository.deleteById(id);
    }*/
}
