package com.publiccms.logic.dao.visit;

import java.util.Calendar;
// Generated 2021-1-14 22:43:59 by com.publiccms.common.generator.SourceGenerator
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Repository;

import com.publiccms.common.base.BaseDao;
import com.publiccms.common.handler.PageHandler;
import com.publiccms.common.handler.QueryHandler;
import com.publiccms.common.tools.CommonUtils;
import com.publiccms.entities.visit.VisitHistory;
import com.publiccms.entities.visit.VisitDay;
import com.publiccms.entities.visit.VisitItem;
import com.publiccms.entities.visit.VisitSession;
import com.publiccms.entities.visit.VisitUrl;

/**
 *
 * VisitHistoryDao
 * 
 */
@Repository
public class VisitHistoryDao extends BaseDao<VisitHistory> {

    /**
     * @param siteId
     * @param sessionId
     * @param startCreateDate
     * @param endCreateDate
     * @param orderType
     * @param pageIndex
     * @param pageSize
     * @return results page
     */
    public PageHandler getPage(Short siteId, String sessionId, Date startCreateDate, Date endCreateDate, String orderType,
            Integer pageIndex, Integer pageSize) {
        QueryHandler queryHandler = getQueryHandler("from VisitHistory bean");
        if (null != siteId) {
            queryHandler.condition("bean.siteId = :siteId").setParameter("siteId", siteId);
        }
        if (CommonUtils.notEmpty(sessionId)) {
            queryHandler.condition("bean.sessionId = :sessionId").setParameter("sessionId", sessionId);
        }
        if (null != startCreateDate) {
            queryHandler.condition("bean.createDate > :startCreateDate").setParameter("startCreateDate", startCreateDate);
        }
        if (null != endCreateDate) {
            queryHandler.condition("bean.createDate <= :endCreateDate").setParameter("endCreateDate", endCreateDate);
        }
        if (!ORDERTYPE_ASC.equalsIgnoreCase(orderType)) {
            orderType = ORDERTYPE_DESC;
        }
        queryHandler.order("bean.createDate").append(orderType);
        return getPage(queryHandler, pageIndex, pageSize);
    }

    /**
     * @param siteId
     * @param visitDate
     * @param itemType
     * @param itemId
     * @return results page
     */
    @SuppressWarnings("unchecked")
    public List<VisitItem> getItemList(Short siteId, Date visitDate, String itemType, String itemId) {
        QueryHandler queryHandler = getQueryHandler(
                "select new VisitItem(bean.siteId,bean.visitDate,bean.itemType,bean.itemId,count(*),count(distinct bean.sessionId),count(distinct bean.ip)) from VisitHistory bean");
        if (null != siteId) {
            queryHandler.condition("bean.siteId = :siteId").setParameter("siteId", siteId);
        }
        queryHandler.condition("bean.visitDate = :visitDate").setParameter("visitDate",
                DateUtils.truncate(visitDate, Calendar.DAY_OF_MONTH));
        if (CommonUtils.notEmpty(itemType)) {
            queryHandler.condition("bean.itemType = :itemType").setParameter("itemType", itemType);
        } else {
            queryHandler.condition("bean.itemId is not null");
        }
        if (CommonUtils.notEmpty(itemId)) {
            queryHandler.condition("bean.itemId = :itemId").setParameter("itemId", itemId);
        } else {
            queryHandler.condition("bean.itemId is not null");
        }
        queryHandler.group("bean.siteId");
        queryHandler.group("bean.visitDate");
        queryHandler.group("bean.itemType");
        queryHandler.group("bean.itemId");
        queryHandler.order("count(*) desc");
        return (List<VisitItem>) getList(queryHandler);
    }

    /**
     * @param siteId
     * @param visitDate
     * @return results page
     */
    @SuppressWarnings("unchecked")
    public List<VisitUrl> getUrlList(Short siteId, Date visitDate) {
        QueryHandler queryHandler = getQueryHandler(
                "select new VisitUrl(bean.siteId,bean.visitDate,bean.url,count(*),count(distinct bean.sessionId),count(distinct bean.ip)) from VisitHistory bean");
        if (null != siteId) {
            queryHandler.condition("bean.siteId = :siteId").setParameter("siteId", siteId);
        }
        queryHandler.condition("bean.visitDate = :visitDate").setParameter("visitDate",
                DateUtils.truncate(visitDate, Calendar.DAY_OF_MONTH));
        queryHandler.group("bean.siteId");
        queryHandler.group("bean.visitDate");
        queryHandler.group("bean.url");
        queryHandler.order("count(*) desc");
        return (List<VisitUrl>) getList(queryHandler);
    }

    /**
     * @param siteId
     * @param visitDate
     * @param visitHour
     * @return results page
     */
    @SuppressWarnings("unchecked")
    public List<VisitDay> getHourList(Short siteId, Date visitDate, byte visitHour) {
        QueryHandler queryHandler = getQueryHandler(
                "select new VisitDay(bean.siteId,bean.visitDate,bean.visitHour,count(*),count(distinct bean.sessionId),count(distinct bean.ip)) from VisitHistory bean");
        if (null != siteId) {
            queryHandler.condition("bean.siteId = :siteId").setParameter("siteId", siteId);
        }
        queryHandler.condition("bean.visitDate = :visitDate").setParameter("visitDate",
                DateUtils.truncate(visitDate, Calendar.DAY_OF_MONTH));
        queryHandler.condition("bean.visitHour = :visitHour").setParameter("visitHour", visitHour);
        queryHandler.group("bean.siteId");
        queryHandler.group("bean.visitDate");
        queryHandler.group("bean.visitHour");
        return (List<VisitDay>) getList(queryHandler);
    }

    /**
     * @param siteId
     * @param startCreateDate
     * @param endCreateDate
     * @return results page
     */
    @SuppressWarnings("unchecked")
    public List<VisitSession> getSessionList(Short siteId, Date startCreateDate, Date endCreateDate) {
        QueryHandler queryHandler = getQueryHandler(
                "select new VisitSession(bean.siteId,bean.sessionId,bean.visitDate,max(bean.createDate), min(bean.createDate), bean.ip, count(*)) from VisitHistory bean");
        if (null != siteId) {
            queryHandler.condition("bean.siteId = :siteId").setParameter("siteId", siteId);
        }
        if (null != startCreateDate) {
            queryHandler.condition("bean.createDate > :startCreateDate").setParameter("startCreateDate", startCreateDate);
        }
        if (null != endCreateDate) {
            queryHandler.condition("bean.createDate <= :endCreateDate").setParameter("endCreateDate", endCreateDate);
        }
        queryHandler.group("bean.siteId");
        queryHandler.group("bean.sessionId");
        queryHandler.group("bean.visitDate");
        queryHandler.group("bean.ip");
        return (List<VisitSession>) getList(queryHandler);
    }

    /**
     * @param begintime
     * @return number of data deleted
     */
    public int delete(Date begintime) {
        if (null != begintime) {
            QueryHandler queryHandler = getQueryHandler("delete from VisitHistory bean");
            if (null != begintime) {
                queryHandler.condition("bean.createDate <= :createDate").setParameter("createDate", begintime);
            }
            return delete(queryHandler);
        }
        return 0;
    }

    @Override
    protected VisitHistory init(VisitHistory entity) {
        if (null == entity.getCreateDate()) {
            entity.setCreateDate(CommonUtils.getDate());
        }
        if (CommonUtils.notEmpty(entity.getSessionId()) && entity.getSessionId().length() > 50) {
            entity.setSessionId(entity.getSessionId().substring(0, 50));
        }
        if (CommonUtils.notEmpty(entity.getTitle()) && entity.getTitle().length() > 255) {
            entity.setTitle(entity.getTitle().substring(0, 255));
        }
        if (CommonUtils.notEmpty(entity.getUserAgent()) && entity.getUserAgent().length() > 500) {
            entity.setUserAgent(entity.getUserAgent().substring(0, 500));
        }
        if (CommonUtils.notEmpty(entity.getItemType()) && entity.getItemType().length() > 50) {
            entity.setItemType(entity.getItemType().substring(0, 50));
        }
        if (CommonUtils.notEmpty(entity.getItemId()) && entity.getItemId().length() > 50) {
            entity.setItemId(entity.getItemId().substring(0, 50));
        }
        return entity;
    }

}