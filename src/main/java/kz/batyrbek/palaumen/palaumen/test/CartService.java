//package kz.batyrbek.palaumen.palaumen.test;
//
//import kz.batyrbek.palaumen.palaumen.dto.CartDto;
//import kz.batyrbek.palaumen.palaumen.mapper.CartMapper;
//import kz.batyrbek.palaumen.palaumen.repository.CartRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//
//@Service
//@RequiredArgsConstructor
//public class CartService {
//    private final CartRepository cartRepository;
//    private final CartMapper cartMapper;
//    public CartDto getCart(Long id){
//        return cartMapper.toDto(cartRepository.findById(id).orElseThrow());
//    }
//}
