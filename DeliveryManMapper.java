package kz.batyrbek.palaumen.palaumen.mapper;

import kz.batyrbek.palaumen.palaumen.dto.DeliveryManDto;
import kz.batyrbek.palaumen.palaumen.model.DeliveryMan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryManMapper {
    DeliveryManDto toDto(DeliveryMan deliveryMan);
    DeliveryMan toEntity(DeliveryManDto deliveryManDto);
}
