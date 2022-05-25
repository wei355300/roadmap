package com.mantas.tapd.service.test;

import com.mantas.tapd.dto.*;
import com.mantas.tapd.service.WorkerBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class WorkerBoardServiceTester {

    @Autowired
    private WorkerBoardService workerBoardService;

    @Test
    public void testGetProjects() throws IOException {

        List<ProjectComp> projects = workerBoardService.getProjects();
//        System.out.println(tapdConf.toString());
//
//        List<Project> projects = projectService.getProjects();

        assertThat(projects).isNotNull();

        projects.forEach(p -> System.out.println(p.toString()));
    }

    @Test
    public void testGetTraces() throws IOException {
//        List<ProjectComp> projects = workerBoardService.getProjects();
        List<ProjectComp> projects = new ArrayList<>();

        projects.add(getB2BProjectComp());

        Collection<Worker> traces = workerBoardService.getTraces(projects);

        assertThat(traces).isNotNull();

        traces.forEach(t -> System.out.println(t.toString()));
    }

    private ProjectComp getB2BProjectComp() {
        ProjectComp p = new ProjectComp();
        p.setId(64372788);
        p.setName("B2B订货商城");
        Role r = new Role();
        r.setId("1164372788001000036");
        r.setName("研发");
        List rList = new ArrayList<>();
        rList.add(r);
        p.setRoles(rList);
        Iteration i = new Iteration();
        i.setId("1164372788001000997");
        i.setName("第21.44~45周");
        List iList = new ArrayList();
        iList.add(i);
        p.setIterations(iList);
        return p;
    }

    private ProjectComp getPetLifeProjectComp() {
        ProjectComp p = new ProjectComp();
        p.setId(55694943);
        p.setName("魔鬼鱼宠物活体");
        Role r = new Role();
        r.setId("1000000000000000089");
        r.setName("普通成员");
        List rList = new ArrayList<>();
        rList.add(r);
        p.setRoles(rList);
        Iteration i = new Iteration();
        i.setId("1155694943001001009");
        i.setName("");
        List iList = new ArrayList();
        iList.add(i);
        p.setIterations(iList);
        return p;
    }
}
