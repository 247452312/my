package indi.uhyils.redis.druid.filter;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLLimit;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;

import java.io.Serializable;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月21日 10时00分
 */
public class MainTest implements Serializable {

    public static void main(String[] args) {
        String sql = "select count(0) from (SELECT\n" +
                "        organization_id,\n" +
                "        test_campus_id,\n" +
                "        sample_no,\n" +
                "        test_status,\n" +
                "        patient_name,\n" +
                "        patient_sex,\n" +
                "        if (patient_type=2,inpatient_id,outpatient_id) as inpatient_id,\n" +
                "        request_item_mixname,\n" +
                "        send_organization_id,\n" +
                "        micro_status,\n" +
                "        CASE patient_type\n" +
                "        WHEN '1' THEN '门诊'\n" +
                "        WHEN '2' THEN '住院'\n" +
                "        WHEN '3' THEN '体检'\n" +
                "        ELSE null END AS patient_type,\n" +
                "        ( SELECT dict_type_name FROM dict_type_code WHERE\n" +
                "        dict_type_no = report_type AND dict_type_code =null\n" +
                "        AND organization_id = 1\n" +
                "        AND active = 1\n" +
                "        ) AS report_type,\n" +
                "        emergency_status,\n" +
                "        critical_status,\n" +
                "        barcode,\n" +
                "        patient_id,\n" +
                "        diagnostics_name,\n" +
                "        sample_type_name,\n" +
                "        check_doct_name,\n" +
                "        check_date,\n" +
                "        view_doct_name,\n" +
                "        view_report_date,\n" +
                "        test_dept_name,\n" +
                "        patient_age,\n" +
                "        patient_age_unit,\n" +
                "        multi_status,\n" +
                "        multi_resistance_code_mix,\n" +
                "        multi_resistance_name_mix,\n" +
                "        reported_doct_name,\n" +
                "        reported_date,\n" +
                "        report_mark,\n" +
                "        request_doctor_name,\n" +
                "        request_doct_date,\n" +
                "        test_doct_name,\n" +
                "        test_date,\n" +
                "        card_id\n" +
                "        FROM biz_lis_sample WHERE\n" +
                "        active = 1\n" +
                "            AND (outpatient_id ='1670663'\n" +
                "            OR card_id='1670663'\n" +
                "            OR inpatient_id='1670663'\n" +
                "            OR visit_id='1670663')\n" +
                "            AND test_status >=70\n" +
                "            AND check_date >= '2021-05-17 00:00:00'\n" +
                "            AND organization_id = 1\n" +
                "        order by check_date) tmp_count limit 0,1000000";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLSelectStatement sqlStatement = (SQLSelectStatement) parser.parseStatement();
        SQLSelect selectQuery = sqlStatement.getSelect();
        MySqlSelectQueryBlock sqlSelectQuery = (MySqlSelectQueryBlock) selectQuery.getQuery();
        SQLLimit limit = sqlSelectQuery.getLimit();
        SQLExpr rowCount = limit.getRowCount();
        String s = rowCount.toString();
        limit.setRowCount(5000);
        System.out.println(sqlSelectQuery);
    }
}
