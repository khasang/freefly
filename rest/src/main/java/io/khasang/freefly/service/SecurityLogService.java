package io.khasang.freefly.service;

import io.khasang.freefly.entity.SecurityLog;

public interface SecurityLogService {
    /**
     * method for getting securitylog by specific id
     *
     * @param id - securitylog's id
     * @return securitylog's by id
     */
    SecurityLog getSecurityLogById(long id);

    /**
     * method for delete securitylog by specific id
     *
     * @param id for deleting securitylog
     */
    void deleteSecurityLogById(long id);

    /**
     * method for add securitylog
     *
     * @param securityLog new log for creation
     * @return created securitylog
     */
    SecurityLog addSecurityLog(SecurityLog securityLog);
}
