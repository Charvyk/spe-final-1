package esd.academia.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import esd.academia.controller.FacultyController;
import esd.academia.model.Faculty;
import esd.academia.service.FacultyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.hamcrest.CoreMatchers;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = FacultyController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Autowired
    private ObjectMapper objectMapper;

    private Faculty faculty;

    @BeforeEach
    public void init() {
        faculty = Faculty.builder()
                .faculty_id(2)
                .firstname("X")
                .lastname("Y")
                .email("XY@mail.com")
                .title("Phd in nothing").build();
    }

    @Test
    public void FacultyController_addFaculty_test() throws Exception{
        given(facultyService.saveFaculty(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/faculty/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faculty)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}