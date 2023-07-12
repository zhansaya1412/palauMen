package kz.batyrbek.palaumen.palaumen.mapper;

import kz.batyrbek.palaumen.palaumen.dto.CartItemDto;
import kz.batyrbek.palaumen.palaumen.model.CartItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemDto toDto(CartItem cartItem);
    CartItem toEntity(CartItemDto cartItemDto);

    List<CartItemDto> toDtoList(List<CartItem> cartItems);

//    List<CartItem> toEntityList(CartItem CartItem);
}
