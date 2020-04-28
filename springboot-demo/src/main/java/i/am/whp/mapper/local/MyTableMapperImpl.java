package i.am.whp.mapper.local;

import i.am.whp.domain.GetDataParam;
import i.am.whp.handler.SelectMapKeyAndValueHandler;
import i.am.whp.model.MyTable;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 自定义dao的实现类
 */
@Repository
public class MyTableMapperImpl extends SqlSessionDaoSupport implements MyTableMapper {

    @Autowired(required = false)
    @Qualifier("localSqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public MyTable getById(Integer id) {
        return this.getSqlSession().selectOne(MyTableMapper.class.getName() + ".getById", 1);
    }

    @Override
    public List<MyTable> getList(GetDataParam param) {
        return null;
    }

    @Override
    public int getCount(GetDataParam param) {
        return 0;
    }

    @Override
    public Map<Integer, Integer> countByStatus() {

        //namespace : XxxMapper.xml 中配置的地址（XxxMapper.xml的qualified name）
        //.selectXxxxNum : XxxMapper.xml 中配置的方法名称
        //this.getSqlSession().select(namespace+".selectXxxxNum", handler);

        SelectMapKeyAndValueHandler handler = new SelectMapKeyAndValueHandler();
        this.getSqlSession().select(MyTableMapper.class.getName() + ".countByStatus", handler);
        return handler.getMappedResults();
    }

    @Override
    public int update(Integer id) {
        return 0;
    }

    @Override
    public int insert(MyTable table) {
        return 0;
    }

    @Override
    public List<MyTable> whereTest(MyTable param) {
        return null;
    }
}
