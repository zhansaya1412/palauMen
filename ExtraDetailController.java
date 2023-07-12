package kz.batyrbek.palaumen.palaumen.api;

import kz.batyrbek.palaumen.palaumen.dto.ExtraDetailDto;
import kz.batyrbek.palaumen.palaumen.service.ExtraDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/extraDetail")
public class ExtraDetailController {

    @Autowired
    ExtraDetailService extraDetailService;

    @GetMapping
    public List<ExtraDetailDto> getExtraDetails(){
        return extraDetailService.getExtraDetails();
    }

    @GetMapping(value="{id}")
    public ExtraDetailDto getDetail(@PathVariable(name = "id") Long id){
        return extraDetailService.getDetail(id);
    }

}

