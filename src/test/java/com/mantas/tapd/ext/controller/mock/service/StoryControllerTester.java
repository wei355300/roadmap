package com.mantas.tapd.ext.controller.mock.service;

import com.mantas.tapd.ext.controller.StoryController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


//@SpringBootTest


//@RunWith(PowerMockRunner.class)
//@PowerMockRunnerDelegate(SpringRunner.class)
@WebMvcTest(StoryController.class)
@Deprecated
public class StoryControllerTester {

//    String iterationId = "1122259671001000629";
//    String releaseId = "1122259671001000149";
//
//    @MockBean
//    private StoryService storyService;
//
////    @MockBean
////    private UrlBuiler urlBuiler;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
////    @PrepareForTest(UrlBuilder.class)
//    public void testListStoriesByIterationId() throws Exception {
//
////        PowerMockito.mockStatic(UrlBuiler.class);
//
////        PowerMockito.mock(UrlBuilder.class);
//
////        PowerMockito.verifyStatic(UrlBuiler.class);
//
//        when(UrlBuilder.buildViewStoryUrl(Mockito.anyString(), Mockito.anyString())).thenReturn("");
//
//        when(storyService.listByIteration(iterationId)).thenReturn(buildStoryListData());
//
//        //ok test
//        ResultActions resultActions = mockMvc.perform(get("/api/tapd/story/list/iteration").param("iterationId", iterationId));
//        resultActions.andExpect(status().isOk()).andDo(print());
//
//        //miss param test
//        ResultActions resultActions2 = mockMvc.perform(get("/api/tapd/story/list/iteration"));
//        resultActions2.andExpect(status().isBadRequest()).andDo(print());
//    }
//
//    @Test
//    public void testListStoriesByReleaseId() throws Exception {
//
//        when(storyService.listByRelease(releaseId)).thenReturn(buildStoryListData());
//
//        //ok test
//        ResultActions resultActions = mockMvc.perform(get("/api/tapd/story/list/release").param("releaseId", releaseId));
//        resultActions.andExpect(status().isOk()).andDo(print());
//
//        //miss param test
//        ResultActions resultActions2 = mockMvc.perform(get("/api/tapd/story/list/release"));
//        resultActions2.andExpect(status().isBadRequest()).andDo(print());
//    }
//
//    private List<Story> buildStoryListData() {
//        List<Story> stories = new ArrayList<>();
//        Story story = new Story();
//        story.setId("1");
//        story.setName("a");
//
//        stories.add(story);
//        return stories;
//    }
}