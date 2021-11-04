package com.ss.scrumptious_drivers.mapper;

import com.ss.scrumptious.common_entities.entity.Address;
import com.ss.scrumptious_drivers.dto.CreateAddressDto;
import com.ss.scrumptious_drivers.dto.UpdateAddressDto;

public class AddressDtoMapper {
    
    public static Address map(CreateAddressDto dto) {
        Address address = Address.builder()
            .line1(dto.getLine1())
            .city(dto.getCity())
            .state(dto.getState())
            .zip(dto.getZip())
            .build();

        if (dto.getLine2() != null) {
            address.setLine2(dto.getLine2());
        }

        return address;
    }

    public static Address map(Address address, UpdateAddressDto dto) {
        
        if (dto.getLine1() != null) {
            address.setLine1(dto.getLine1());
        }
        if (dto.getLine2() != null) {
            address.setLine2(dto.getLine2());
        }
        if (dto.getCity() != null) {
            address.setCity(dto.getCity());
        }
        if (dto.getState() != null) {
            address.setState(dto.getState());
        }
        if (dto.getZip() != null) {
            address.setZip(dto.getZip());
        }

        return address;
    }
}
