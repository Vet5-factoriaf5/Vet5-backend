package org.factoriaf5.happypaws.register.services;

import org.factoriaf5.happypaws.register.dtos.RegisterDTORequest;
import org.factoriaf5.happypaws.register.dtos.RegisterDTOResponse;

public interface RegisterService {
    RegisterDTOResponse registerUser(RegisterDTORequest dto);
}