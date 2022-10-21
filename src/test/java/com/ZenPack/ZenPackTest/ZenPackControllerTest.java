package com.ZenPack.ZenPackTest;

import com.ZenPack.Dto.MenuDto;
import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.controller.ZenPackController;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ZenPackRepository;
import com.ZenPack.service.Impl.ZenPackServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ZenPackController.class)
public class ZenPackControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZenPackRepository repository;

    @MockBean
    private ZenPackServiceImpl service;

    @InjectMocks
    private ZenPackController controller;

    @Autowired
    private ObjectMapper objectMapper;

//    @BeforeEach
    @Test
    void create() throws Exception {
        ZenPackDto zenPackDto = new ZenPackDto();
//        List<MenuDto> menus = objectMapper.c
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ZenPack zenPack=mapper.map(zenPackDto,ZenPack.class);
        zenPack.setZenPackId(1L);
        zenPack.setName("");
        zenPack.setCreatedDate(new Date());
        zenPack.setCreatedBy("");
        zenPack.setUpdatedTime(new Date());
        zenPack.setUpdatedBy("");
//        zenPack.setMenus("");
        ResponseEntity<ZenPack> ZenPack = null;
        when(service.saveZenPack(any(ZenPack.class))).thenReturn(ZenPack);

        this.mockMvc.perform(post("/api/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(zenPack)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.featureId", CoreMatchers.is(zenPack.getZenPackId())))
                .andExpect(jsonPath("$.text", CoreMatchers.is(zenPack.getName())))
                .andExpect(jsonPath("$.icon", CoreMatchers.is(zenPack.getCreatedDate())))
                .andExpect(jsonPath("$.isSettingMenu", CoreMatchers.is(zenPack.getCreatedBy())))
                .andExpect(jsonPath("$.featureUrl", CoreMatchers.is(zenPack.getUpdatedTime())))
                .andExpect(jsonPath("$.featureUrl", CoreMatchers.is(zenPack.getUpdatedTime())));
    }

}
