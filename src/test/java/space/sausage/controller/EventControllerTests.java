package space.sausage.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import space.sausage.Application;
import space.sausage.data.Event;
import space.sausage.service.EventNotFoundException;
import space.sausage.service.EventService;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings({"CanBeFinal", "unused"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class EventControllerTests {
    private final MediaType jsonContentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private EventService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getsAllByYearAndMonth() throws Exception {
        List<Event> events = Arrays.asList(new Event(), new Event());
        when(service.findAllByYearAndMonth(2017, 3)).thenReturn(events);

        mockMvc.perform(get("/events/2017/months/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonContentType))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getsByYearAndRace() throws Exception {
        when(service.findByYearAndRace(2017, 1)).thenReturn(new Event());

        mockMvc.perform(get("/events/2017/races/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(jsonContentType));
    }

    @Test
    public void getByYearAndRaceReturnsNotFound() throws Exception {
        when(service.findByYearAndRace(anyInt(), anyInt())).thenThrow(new EventNotFoundException());

        mockMvc.perform(get("/events/2017/races/1"))
                .andExpect(status().isNotFound());
    }
}
