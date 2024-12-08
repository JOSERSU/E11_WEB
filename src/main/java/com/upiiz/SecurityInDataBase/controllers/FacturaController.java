package com.upiiz.SecurityInDataBase.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/facturas")
@PreAuthorize("denyAll()")
public class FacturaController {

    @GetMapping
    @PreAuthorize("hasAnyAuthority('READ')")
    public String lisarfacturas(){
        return "Lista de facturas";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('CREATE')")
    public String crearfacturas(){
        return "Factura creada";
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('UPDATE')")
    public String ActualizarFactura(){
        return "Actualizar factura";
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('DELETE')")
    public String EliminarFactura(){
        return "Eliminar una factura";
    }

}
