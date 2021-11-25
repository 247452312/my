package indi.uhyils.protocol.mysql.plan.other;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLExprImpl;
import com.alibaba.druid.sql.dialect.mysql.ast.expr.MySqlExpr;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import com.alibaba.fastjson.JSON;
import java.util.List;
import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年11月25日 09时24分
 */
public class MySqlListExpr extends SQLExprImpl implements MySqlExpr {

    private List<SQLExpr> values;

    public MySqlListExpr() {
    }

    public MySqlListExpr(List<SQLExpr> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MySqlListExpr)) {
            return false;
        }
        MySqlListExpr other = (MySqlListExpr) o;
        List<SQLExpr> values = other.getValues();
        return Objects.equals(this.values, values);
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {

    }

    public List<SQLExpr> getValues() {
        return values;
    }

    public void setValues(List<SQLExpr> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(values);
    }
}
