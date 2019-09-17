package com.example.mapper.mybatisMap.page;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.github.orderbyhelper.OrderByHelper;
import com.github.pagehelper.Dialect;
import com.github.pagehelper.Page;
import com.github.pagehelper.SqlUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)})
public class PageHelper implements Interceptor {
    private SqlUtil sqlUtil;
    private Properties properties;
    private Boolean autoDialect;

    public PageHelper() {
    }

    public static Page startPage(int pageNum, int pageSize) {
        return startPage(pageNum, pageSize, true);
    }

    public static Page startPage(int pageNum, int pageSize, boolean count) {
        return startPage(pageNum, pageSize, count, (Boolean)null);
    }

    public static Page startPage(int pageNum, int pageSize, String orderBy) {
        orderBy(orderBy);
        return startPage(pageNum, pageSize);
    }

    public static Page startPage(int pageNum, int pageSize, boolean count, Boolean reasonable) {
        return startPage(pageNum, pageSize, count, reasonable, (Boolean)null);
    }

    public static Page startPage(int pageNum, int pageSize, boolean count, Boolean reasonable, Boolean pageSizeZero) {
        Page page = new Page(pageNum, pageSize, count);
        page.setReasonable(reasonable);
        page.setPageSizeZero(pageSizeZero);
        SqlUtil.setLocalPage(page);
        return page;
    }

    public static Page startPage(Object params) {
        Page page = SqlUtil.getPageFromObject(params);
        SqlUtil.setLocalPage(page);
        return page;
    }

    public static void orderBy(String orderBy) {
        OrderByHelper.orderBy(orderBy);
    }

    public Object intercept(Invocation invocation) throws Throwable {
        if (this.autoDialect.booleanValue()) {
            this.initSqlUtil(invocation);
        }

        return this.sqlUtil.processPage(invocation);
    }

    public synchronized void initSqlUtil(Invocation invocation) {
        if (this.sqlUtil == null) {
            String url = null;

            try {
                MappedStatement ms = (MappedStatement)invocation.getArgs()[0];
                MetaObject msObject = SystemMetaObject.forObject(ms);
                DataSource dataSource = (DataSource)msObject.getValue("configuration.environment.dataSource");
                url = dataSource.getConnection().getMetaData().getURL();
            } catch (SQLException var6) {
                throw new RuntimeException("分页插件初始化异常:" + var6.getMessage());
            }

            if (url == null || url.length() == 0) {
                throw new RuntimeException("无法自动获取jdbcUrl，请在分页插件中配置dialect参数!");
            }

            String dialect = Dialect.fromJdbcUrl(url);
            if (dialect == null) {
                throw new RuntimeException("无法自动获取数据库类型，请通过dialect参数指定!");
            }

            this.sqlUtil = new SqlUtil(dialect);
            this.sqlUtil.setProperties(this.properties);
            this.properties = null;
            this.autoDialect = false;
        }

    }

    public Object plugin(Object target) {
        return target instanceof Executor ? Plugin.wrap(target, this) : target;
    }

    public void setProperties(Properties p) {
        try {
            Class.forName("org.apache.ibatis.scripting.xmltags.SqlNode");
        } catch (ClassNotFoundException var3) {
            throw new RuntimeException("您使用的MyBatis版本太低，MyBatis分页插件PageHelper支持MyBatis3.2.0及以上版本!");
        }

        String dialect = p.getProperty("dialect");
        if (dialect != null && dialect.length() != 0) {
            this.autoDialect = false;
            this.sqlUtil = new SqlUtil(dialect);
            this.sqlUtil.setProperties(p);
        } else {
            this.autoDialect = true;
            this.properties = p;
        }

    }
}
