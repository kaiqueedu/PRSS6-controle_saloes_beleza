package br.edu.ifsp.util;

import br.edu.ifsp.controller.vo.ClienteResponse;
import br.edu.ifsp.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel="spring")
public interface ClienteMapper {

    ClienteResponse clienteToClienteResponse(Cliente cliente);
    Cliente clienteReponseToCliente(ClienteResponse clienteResponse);
    
}
