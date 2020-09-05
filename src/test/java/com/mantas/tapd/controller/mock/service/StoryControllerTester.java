package com.mantas.tapd.controller.mock.service;

import com.mantas.tapd.connector.UrlBuiler;
import com.mantas.tapd.controller.StoryController;
import com.mantas.tapd.dto.Story;
import com.mantas.tapd.service.StoryService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest


@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@WebMvcTest(StoryController.class)
public class StoryControllerTester {

    String iterationId = "1122259671001000629";
    String releaseId = "1122259671001000149";

    @MockBean
    private StoryService storyService;

//    @MockBean
//    private UrlBuiler urlBuiler;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @PrepareForTest(UrlBuiler.class)
    public void testListStoriesByIterationId() throws Exception {

//        PowerMockito.mockStatic(UrlBuiler.class);

        PowerMockito.mock(UrlBuiler.class);

//        PowerMockito.verifyStatic(UrlBuiler.class);

        when(UrlBuiler.buildViewStoryUrl(Mockito.anyString())).thenReturn("");

        when(storyService.listByIteration(iterationId)).thenReturn(buildStoryListData());

        //ok test
        ResultActions resultActions = mockMvc.perform(get("/api/tapd/story/list/iteration").param("iterationId", iterationId));
        resultActions.andExpect(status().isOk()).andDo(print());

        //miss param test
        ResultActions resultActions2 = mockMvc.perform(get("/api/tapd/story/list/iteration"));
        resultActions2.andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void testListStoriesByReleaseId() throws Exception {

        when(storyService.listByRelease(releaseId)).thenReturn(buildStoryListData());

        //ok test
        ResultActions resultActions = mockMvc.perform(get("/api/tapd/story/list/release").param("releaseId", releaseId));
        resultActions.andExpect(status().isOk()).andDo(print());

        //miss param test
        ResultActions resultActions2 = mockMvc.perform(get("/api/tapd/story/list/release"));
        resultActions2.andExpect(status().isBadRequest()).andDo(print());
    }

    private List<Story> buildStoryListData() {
        List<Story> stories = new ArrayList<>();
        Story story = new Story();
        story.setId("1");
        story.setName("a");

        stories.add(story);
        return stories;
    }
}