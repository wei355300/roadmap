package com.mantas.tapd.iteration;

import com.jayway.jsonpath.TypeRef;
import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.TapdClient;
import com.mantas.tapd.TapdURL;
import com.mantas.tapd.exception.TapdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@Validated
public class IterationService {

    private TapdClient tapdClient;

    public IterationService(TapdClient tapdClient) {
        this.tapdClient = tapdClient;
    }

    public List<Iteration> list(Date startDate) {
        return null;
    }

    /**
     * 关闭迭代(将迭代设置为关闭状态)
     *
     * @param projectId
     * @param iterationId
     * @param optUser
     * @return 操作结果, true: 关闭成功, false: 操作失败
     */
    public boolean close(@Valid @NotNull(message = "参数不能为空") Integer projectId, @NotNull String iterationId, @NotNull String optUser) {
        List<ParamPair> pairs = tapdClient.buildParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
        tapdClient.appendParams(pairs, TapdURL.ITERATION.PARAM.ID, iterationId);
        tapdClient.appendParams(pairs, TapdURL.ITERATION.PARAM.CURRENT_USER, optUser);
        tapdClient.appendParams(pairs, TapdURL.ITERATION.PARAM.STATUS, TapdURL.ITERATION.VALUE.STATUS_CLOSE);

        return tapdClient.post(TapdURL.ITERATION.URL.UPDATE, pairs);
    }

    /**
     * 获取未关闭状态的迭代
     *
     * @param projectId
     * @return
     */
    public List<Iteration> getIterations(Integer projectId) throws TapdException {
        List<ParamPair> pairs = tapdClient.buildParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
        tapdClient.appendParams(pairs, TapdURL.PARAM.STATUS, TapdURL.ITERATION.VALUE.STATUS_OPEN);
        try {
            return tapdClient.get(TapdURL.ITERATION.URL.LIST, pairs, new TypeRef<List<Iteration>>() {}, "$.data[*].Iteration");
        } catch (IOException e) {
            throw new TapdException("获取迭代失败, project id: " + projectId, e);
        }
    }
}
