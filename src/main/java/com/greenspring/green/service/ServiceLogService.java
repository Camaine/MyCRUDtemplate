package com.greenspring.green.service;

import com.greenspring.green.model.ServiceLog;
import com.greenspring.green.model.TwitterToken;
import com.greenspring.green.model.TwtUser;
import com.greenspring.green.repo.ServiceLogRepository;
import com.greenspring.green.repo.TwtUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

@Service
public class ServiceLogService {

    private final ServiceLogRepository serviceLogRepository;

    public ServiceLogService(ServiceLogRepository serviceLogRepository) {
        this.serviceLogRepository = serviceLogRepository;
    }

    @Transactional
    public void saveServiceLog(String apiCode, String uid, String reqInfo, int result){
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setApiCode(apiCode);
        serviceLog.setUid(uid);
        serviceLog.setReqInfo(reqInfo);
        serviceLog.setResult(result);
        serviceLogRepository.save(serviceLog);
    }

}
