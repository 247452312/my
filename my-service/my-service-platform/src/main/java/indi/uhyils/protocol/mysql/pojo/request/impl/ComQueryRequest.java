package indi.uhyils.protocol.mysql.pojo.request.impl;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.pojo.entity.SelectSql;
import indi.uhyils.pojo.entity.Sql;
import indi.uhyils.protocol.mysql.decoder.impl.Proto;
import indi.uhyils.protocol.mysql.enums.MysqlErrCodeEnum;
import indi.uhyils.protocol.mysql.enums.MysqlServerStatusEnum;
import indi.uhyils.protocol.mysql.enums.SqlTypeEnum;
import indi.uhyils.protocol.mysql.handler.MysqlHandler;
import indi.uhyils.protocol.mysql.pojo.request.AbstractMysqlRequest;
import indi.uhyils.protocol.mysql.pojo.response.MysqlResponse;
import indi.uhyils.protocol.mysql.pojo.response.impl.ErrResponse;
import indi.uhyils.protocol.mysql.pojo.response.impl.OkResponse;
import indi.uhyils.util.Asserts;
import java.util.List;
import org.apache.commons.lang3.StringUtils;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时42分
 */
public class ComQueryRequest extends AbstractMysqlRequest {

    private String sql;

    public ComQueryRequest(MysqlHandler mysqlHandler, String sql) {
        super(mysqlHandler);
        this.sql = sql;
    }

    public ComQueryRequest(MysqlHandler mysqlHandler) {
        super(mysqlHandler);
    }

    public static void main(String[] args) {
        String sql = "SELECT\n"
                     + "        dlt.organization_id,\n"
                     + "        dlt.test_no,\n"
                     + "        dlt.test_name,\n"
                     + "        dlt.english_name,\n"
                     + "        dltr.test_range AS ref_range,\n"
                     + "        dlt.default_result AS test_result,\n"
                     + "        '' AS ref_status,\n"
                     + "        dltr.test_unit AS test_item_unit,\n"
                     + "        '' AS id,\n"
                     + "        dltn.sample_no AS sample_no,\n"
                     + "        dltn.patient_id AS patient_id,\n"
                     + "        dlt.relation_dict\n"
                     + "                ,\n"
                     + "        dlt.print_order,\n"
                     + "        dlt.important,\n"
                     + "        '' AS critical_status,\n"
                     + "        dltr.request_dept_code,\n"
                     + "        dltr.device_id,\n"
                     + "        dltr.test_dept_code\n"
                     + "        FROM\n"
                     + "        (\n"
                     + "            SELECT\n"
                     + "            DISTINCT dltn.organization_id,\n"
                     + "            dltn.test_no,\n"
                     + "            dltn.device_id,\n"
                     + "            blsp.sample_no,\n"
                     + "            blsp.sample_type_code,\n"
                     + "            blsp.patient_sex,\n"
                     + "            blsp.patient_age_unit,\n"
                     + "            blsp.patient_age,\n"
                     + "                blsp.test_dept_code,\n"
                     + "            blsp.patient_id,\n"
                     + "            blsp.request_dept_code,\n"
                     + "            blsp.test_campus_id\n"
                     + "        FROM\n"
                     + "            biz_lis_sample_add bls\n"
                     + "        INNER JOIN\n"
                     + "            biz_lis_sample blsp \n"
                     + "                ON bls.organization_id=blsp.organization_id \n"
                     + "                AND bls.sample_no=blsp.sample_no \n"
                     + "                and blsp.active = 1\n"
                     + "        INNER JOIN\n"
                     + "            dict_lis_testcode_no dltn \n"
                     + "                on bls.organization_id = dltn.organization_id \n"
                     + "                AND bls.request_item_id = dltn.request_item_id \n"
                     + "                and dltn.active = 1\n"
                     + "                and blsp.test_campus_id = dltn.execute_campus_id\n"
                     + "        WHERE\n"
                     + "            bls.active = 1\n"
                     + "            and bls.organization_id = 1\n"
                     + "            and bls.sample_no = '222'\n"
                     + "            and blsp.test_status in (60, 65) \n"
                     + "         UNION\n"
                     + "            SELECT\n"
                     + "                DISTINCT dlgt.organization_id,\n"
                     + "                dlgt.test_no,\n"
                     + "                dtg.device_id,\n"
                     + "                blsp.sample_no,\n"
                     + "                blsp.sample_type_code,\n"
                     + "                blsp.patient_sex,\n"
                     + "                blsp.patient_age_unit,\n"
                     + "                blsp.patient_age,\n"
                     + "                blsp.test_dept_code,\n"
                     + "                blsp.patient_id,\n"
                     + "                blsp.request_dept_code,\n"
                     + "                blsp.test_campus_id\n"
                     + "            FROM\n"
                     + "                dict_test_group dtg\n"
                     + "            INNER JOIN\n"
                     + "                dict_lis_group_testno dlgt \n"
                     + "                    ON dtg.organization_id=dlgt.organization_id \n"
                     + "                    AND dtg.group_id=dlgt.group_id \n"
                     + "                    AND dlgt.active = 1\n"
                     + "                    and dtg.execute_campus_id = dlgt.execute_campus_id\n"
                     + "            INNER JOIN\n"
                     + "                biz_lis_sample blsp \n"
                     + "                    ON dtg.organization_id = blsp.organization_id \n"
                     + "                    AND dtg.group_id = blsp.group_id \n"
                     + "                    AND blsp.active = 1\n"
                     + "                    and dtg.execute_campus_id = blsp.test_campus_id\n"
                     + "            WHERE\n"
                     + "                dtg.active = 1\n"
                     + "                and dtg.organization_id = 1\n"
                     + "                and blsp.sample_no = '222'\n"
                     + "                and blsp.test_status in (60, 65)\n"
                     + "                and dtg.group_id = 'CBD'\n"
                     + "                and dtg.device_id =1240 \n"
                     + "        ) dltn\n"
                     + "        INNER JOIN\n"
                     + "            dict_lis_testno dlt \n"
                     + "                on dltn.organization_id = dlt.organization_id \n"
                     + "                and dltn.test_no = dlt.test_no \n"
                     + "                AND dlt.active = 1\n"
                     + "                and dltn.test_campus_id = dlt.execute_campus_id\n"
                     + "        LEFT OUTER JOIN\n"
                     + "            dict_lis_testno_range dltr \n"
                     + "                ON dlt.organization_id=dltr.organization_id \n"
                     + "                and dltn.sample_type_code=dltr.sample_type_code \n"
                     + "                and dlt.test_no=dltr.test_no \n"
                     + "                and dltn.patient_sex=dltr.range_sex\n"
                     + "                and dltn.patient_age_unit=dltr.age_unit \n"
                     + "                AND dltn.patient_age>=dltr.age_low \n"
                     + "                AND dltn.patient_age<=dltr.age_high\n"
                     + "                AND (\n"
                     + "                    dltr.device_id IS NULL \n"
                     + "                    OR LENGTH(TRIM(dltr.device_id))<=0\n"
                     + "                )\n"
                     + "                AND dltr.active=1 \n"
                     + "                and dltr.set_type='1' \n"
                     + "                and dlt.execute_campus_id = dltr.execute_campus_id\n"
                     + "        WHERE\n"
                     + "            (\n"
                     + "                dltn.device_id = 1240  \n"
                     + "                OR  dltn.device_id IS NULL \n"
                     + "                OR LENGTH(TRIM(dltn.device_id))<=0\n"
                     + "            )\n"
                     + "            and dlt.test_no NOT IN (\n"
                     + "                    SELECT\n"
                     + "                    test_no \n"
                     + "                FROM\n"
                     + "                    biz_lis_result\n"
                     + "                WHERE\n"
                     + "                    organization_id = 1\n"
                     + "                    and sample_no = '222'\n"
                     + "                    and active = 1 \n"
                     + "            )\n";
        Sql s = new Sql(sql, null);
        s.parse();
        SelectSql transformation = (SelectSql) s.transformation();
        List<SQLSelectQueryBlock> sqlSelectQueryBlocks = transformation.blockQuerys();


    }

    @Override
    protected void load() {
        Proto proto = new Proto(mysqlBytes, 4);
        this.sql = proto.get_null_str();
    }

    @Override
    public MysqlResponse invoke() {
        if (StringUtils.isBlank(sql)) {
            return new ErrResponse(getMysqlHandler(), MysqlErrCodeEnum.EE_FAILED_PROCESSING_DIRECTIVE, MysqlServerStatusEnum.SERVER_STATUS_NO_BACKSLASH_ESCAPES, "sql语句不能为空");
        }
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Sql sqlEntity = new Sql(sql, null);
        sqlEntity.parse();
        if (sqlStatement instanceof SQLSelectStatement) {
            SelectSql transformation = (SelectSql) sqlEntity.transformation();
            List<SQLSelectQueryBlock> sqlSelectQueryBlocks = transformation.blockQuerys();
        } else if (sqlStatement instanceof SQLUpdateStatement) {
        } else if (sqlStatement instanceof SQLInsertStatement) {
        } else if (sqlStatement instanceof SQLDeleteStatement) {
        } else {
            return new OkResponse(getMysqlHandler(), SqlTypeEnum.NULL);
        }

        Asserts.assertTrue(false, "不识别的sql语句:{}", sql);
        return null;
    }
}
