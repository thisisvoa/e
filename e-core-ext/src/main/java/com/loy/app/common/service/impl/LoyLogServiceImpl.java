package com.loy.app.common.service.impl;

import org.apache.catalina.connector.ResponseFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.loy.app.common.domain.entity.ExceptionEntity;
import com.loy.app.common.domain.entity.OperatorLogEntity;
import com.loy.app.common.domain.entity.PerformanceEntity;
import com.loy.app.common.repository.ExceptionRepository;
import com.loy.app.common.repository.OperatorLogRepository;
import com.loy.app.common.repository.PerformanceRepository;
import com.loy.e.core.util.JsonUtil;
import com.loy.e.security.service.LoyLogService;

/**
 * 
 * @author Loy Fu qq群 540553957
 * @since 1.7
 * @version 1.0.0
 * 
 */

@Service(value = "loyLogService")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class LoyLogServiceImpl implements LoyLogService {
    protected final Log logger = LogFactory.getLog(LoyLogServiceImpl.class);
    @Autowired
    PerformanceRepository performanceRepository;
    @Autowired
    ExceptionRepository exceptionRepository;
    @Autowired
    OperatorLogRepository operatorLogRepository;

    /**
     * 记录操作日志
     */
    @Override
    public void log(String systemCode, String userId, String name, String opName, Object... args) {

        OperatorLogEntity operatorLogEntity = new OperatorLogEntity();
        operatorLogEntity.setUserId(userId);
        operatorLogEntity.setOperator(name);
        operatorLogEntity.setOpName(opName);
        operatorLogEntity.setSystemCode(systemCode);
        if (args != null) {
            Object[] argss = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];

                if (arg instanceof MultipartFile ||
                        arg instanceof org.apache.catalina.connector.Response ||
                        arg instanceof ResponseFacade) {
                } else {
                    argss[i] = arg;
                }
            }
            String data = JsonUtil.json(argss);
            operatorLogEntity.setData(data);
        }

        logger.debug("操作日志：" + opName + " " + operatorLogEntity.getData());
        operatorLogRepository.save(operatorLogEntity);

    }

    /**
     * 主要记录一个业务方法用的时间
     */
    @Override
    public void record(String systemCode, String url, String method, long useTime, String opName) {
        PerformanceEntity performanceEntity = new PerformanceEntity();
        performanceEntity.setUrl(url);
        performanceEntity.setUseTime(useTime);
        performanceEntity.setMethod(method);
        performanceEntity.setOpName(opName);
        performanceEntity.setSystemCode(systemCode);
        performanceRepository.save(performanceEntity);

    }

    /**
     * 主要记录异常信息
     */
    @Override
    public void exception(String systemCode, String exceptionName, String stackTraceMsg) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setExceptionName(exceptionName);
        exceptionEntity.setStackTraceMsg(stackTraceMsg);
        exceptionEntity.setSystemCode(systemCode);
        exceptionRepository.save(exceptionEntity);
    }

}