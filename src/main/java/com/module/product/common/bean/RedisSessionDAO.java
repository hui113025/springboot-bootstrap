package com.module.product.common.bean;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisSessionDAO extends AbstractSessionDAO {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * shiro-redis的session对象前缀
     */
    @Resource(name = "redisTemplate")
    private ValueOperations<Serializable, Session> valueOperations;
    /**
     * The Redis key prefix for the sessions
     */
    private String keyPrefix = "shiro_redis_session:";

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
    }

    /**
     * save session
     *
     * @param session
     * @throws UnknownSessionException
     */
    private void saveSession(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            return;
        }

        Serializable key = session.getId();
        Long timeOut = session.getTimeout() / 1000;
        valueOperations.set(keyPrefix + key, session);
        valueOperations.getOperations().expire(keyPrefix + key, timeOut, TimeUnit.SECONDS);
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            return;
        }
        valueOperations.getOperations().delete(session.getId());

    }

    //用来统计当前活动的session
    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet();

        Set<Serializable> keys = valueOperations.getOperations().keys(this.keyPrefix + "*");
        if (keys != null && keys.size() > 0) {
            for (Serializable key : keys) {
                Session s = valueOperations.get(key);
                sessions.add(s);
            }
        }

        return sessions;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            logger.error("session id is null");
            return null;
        }

        Session s = valueOperations.get(keyPrefix + sessionId);
        return s;
    }

}