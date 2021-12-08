package indi.uhyils.pojo.entity;

import com.alibaba.fastjson.JSON;
import indi.uhyils.BaseTest;
import indi.uhyils.pojo.DO.AlgorithmDO;
import indi.uhyils.pojo.DTO.request.CellAlgorithmRequest;
import indi.uhyils.pojo.DTO.response.CellAlgorithmResponse;
import indi.uhyils.service.AlgorithmService;
import indi.uhyils.util.Asserts;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月08日 09时38分
 */
public class AlgorithmTest extends BaseTest {

    private static final String s = "public class Algorithm {\n" +
                                    "    public int cell(int a, int b) {\n" +
                                    "        return a + b;\n" +
                                    "    }\n" +
                                    "}";

    @Autowired
    private AlgorithmService service;

    @Test
    void cell() {
        AlgorithmDO data = new AlgorithmDO();
        data.setId(123123L);
        data.setName("测试算法");
        data.setNeedFile(false);
        List<String> body = new ArrayList<>(1);
        body.add(s);
        String body1 = JSON.toJSONString(body);
        data.setBody(body1);
        Algorithm algorithm = new Algorithm(data);
        Object cell = algorithm.cell(1, 1);
        Asserts.assertTrue(cell instanceof Integer && (Integer) cell == 2);
    }

    @Test
    public void cellB() {
        CellAlgorithmRequest request = new CellAlgorithmRequest();
        request.setAlgorithmId(1L);
        request.setRequestBody(new Object[]{1, 2});
        CellAlgorithmResponse cellAlgorithmResponse = service.cellAlgorithm(request);
        Object cell = cellAlgorithmResponse.getResult();
        Asserts.assertTrue(cell instanceof Integer && (Integer) cell == 3);
    }
}
