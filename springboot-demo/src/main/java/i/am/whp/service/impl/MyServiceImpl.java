package i.am.whp.service.impl;

import i.am.whp.annotation.Cache;
import i.am.whp.annotation.LogCost;
import i.am.whp.domain.GetDataParam;
import i.am.whp.mapper.local.MyTableMapper;
import i.am.whp.model.MyTable;
import i.am.whp.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

//expected single matching bean but found 2: myServiceImpl,myServiceImpl222
@Service
@Slf4j
public class MyServiceImpl implements MyService<HashMap<String, String>> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    MyTableMapper myTableMapper;
    @Autowired
    MyService self;

    @Override
    public HashMap<String, String> hi() {
        HashMap<String, String> model = new HashMap<>();
        model.put("code", "200");
        model.put("msg", "hello");
        return model;
    }

    @Override
    @LogCost
    @Retryable(value = Exception.class, maxAttempts = 4, backoff = @Backoff(delay = 2000L, multiplier = 1))
    @Cache(keyName = "whp-test:get_data", expireTime = 20)
    public List<MyTable> getData(GetDataParam param) {
        new Thread(() -> System.out.println("开启线程1：" + MDC.get("guid"))).start();
        System.out.println("service:" + MDC.get("guid"));
//        手动继承
//        Map<String, String> m = MDC.getCopyOfContextMap();
//        MDC.setContextMap(m);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2" + System.identityHashCode(MDC.getCopyOfContextMap()));
            System.out.println("开启线程2：" + MDC.get("guid"));
        }).start();
        // 重试完才返回
        //int i = 1/ 0;
        return myTableMapper.getList(param);
    }

    @Override
    public int getCount(GetDataParam param) {
        return myTableMapper.getCount(param);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAndInsert(MyTable param) {
        myTableMapper.updateStatus(param.getId(), 1);
        myTableMapper.insert(param);
        return 0;
    }

    @Override
    public List<MyTable> whereTest(MyTable param) {
        return myTableMapper.whereTest(param);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean testRollbackWrapper() {
        myTableMapper.updateStatus(1, 10);
        boolean b = self.testRollback();
//        if (!b){
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        }
        // ....
        return b;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean testRollback() {
        myTableMapper.updateStatus(1, 11);
        try {
            self.voidReturnException();
            log.info("setRollbackOnly");
        } catch (Exception e) {
            //不加 会引起Transaction rolled back because it has been marked as rollback-only的报错
            // 事务还是需要回滚
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("testRollback with error", e);
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void voidReturnException() {
        int i = 1 / 0;//ArithmeticException extends RuntimeException
//        throw new UnexpectedRollbackException("11111111");// extends NestedRuntimeException extends RuntimeException
//        throw new FileNotFoundException("不能被try-catch");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean hasReturnException() {
        // 当前事务状态
//            int i = 1 / 0;//ArithmeticException extends RuntimeException
//        throw new UnexpectedRollbackException("11111111");// extends NestedRuntimeException extends RuntimeException
        self.voidReturnException();
        return false;
//        try {
//            throw new FileNotFoundException("不能被try-catch");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            return false;
//        }

        // tip：只有Propagation为REQUIRE的时候：形成事务嵌套
        // 会引起Transaction rolled back because it has been marked as rollback-only的报错
    }


    //------------------事务被try-catch四种情况-------------
    //----------------------------------------------------
    //  有无@Tra ｜rollback｜ 异常类型 ｜ 事务传播类别 ｜ 结果  ｜
    // ---------------------------------------------------
    //   无     ｜   -    ｜ 运行时异常｜    -     ｜ 不回滚  ｜
    //   有     ｜   空   ｜ 运行时异常｜ 默/require｜  回滚  ｜Transaction rolled back because it has been marked as rollback-only
    //   有     ｜   空   ｜ 运行时异常｜  非require ｜ 不回滚 ｜
    //   有     ｜   空   ｜ 检查异常 ｜ 默/require ｜ 不回滚 ｜
    //   有     ｜ 检查异常｜ 检查异常 ｜ 默/require ｜  回滚  ｜同上，但是注解一定是加被调方（抛错方）的
    // ---------------------------------------------------
    // ps: 1 与有无返回值无关，必须使用代理类调用，方法需是public
    //     2 加上Transactional之后 默认的rollbackFor为RuntimeException 其余默认请看注解
    //     3 异常是调用方捕获，如果是被调用方已经捕获，不会影响事务

}
