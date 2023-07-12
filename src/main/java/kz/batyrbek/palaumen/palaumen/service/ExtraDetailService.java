package kz.batyrbek.palaumen.palaumen.service;

import kz.batyrbek.palaumen.palaumen.dto.ExtraDetailDto;
import kz.batyrbek.palaumen.palaumen.mapper.ExtraDetailMapper;
import kz.batyrbek.palaumen.palaumen.model.ExtraDetail;
import kz.batyrbek.palaumen.palaumen.repository.ExtraDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtraDetailService {
    private final ExtraDetailRepository extraDetailRepository;
    private final ExtraDetailMapper extraDetailMapper;

    public List<ExtraDetailDto> getExtraDetails(){
        List<ExtraDetail> details = extraDetailRepository.findAll();
        List<ExtraDetailDto> detailDtos = new ArrayList<>();
        details.forEach(detail -> detailDtos.add(extraDetailMapper.toDto(detail)));
        return detailDtos;
    }

    public ExtraDetailDto getDetail(Long id){
        return extraDetailMapper.toDto(extraDetailRepository.findById(id).orElseThrow());
    }

}
