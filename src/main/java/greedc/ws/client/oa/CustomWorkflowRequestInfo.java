/**
 * CustomWorkflowRequestInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jun 14, 2005 (09:15:57 EDT) WSDL2Java emitter.
 */

package greedc.ws.client.oa;

public class CustomWorkflowRequestInfo  implements java.io.Serializable {
    private java.lang.String applicantCode;
    private java.lang.Boolean canEdit;
    private java.lang.Boolean canView;
    private java.lang.String createTime;
    private java.lang.String creatorId;
    private java.lang.String creatorName;
    private java.lang.String currentNodeId;
    private java.lang.String currentNodeName;
    private java.lang.String forwardButtonName;
    private java.lang.String lastOperateTime;
    private java.lang.String lastOperatorName;
    private java.lang.String messageType;
    private java.lang.Boolean mustInputRemark;
    private java.lang.Boolean needAffirmance;
    private java.lang.String receiveTime;
    private java.lang.String rejectButtonName;
    private java.lang.String remark;
    private java.lang.String requestId;
    private java.lang.String requestLevel;
    private java.lang.String requestName;
    private java.lang.String status;
    private java.lang.String subbackButtonName;
    private java.lang.String submitButtonName;
    private java.lang.String subnobackButtonName;
    private greedc.ws.client.oa.WorkflowBaseInfo workflowBaseInfo;
    private greedc.ws.client.oa.ArrayOfWorkflowDetailTableInfo workflowDetailTableInfos;
    private greedc.ws.client.oa.ArrayOfString workflowHtmlShow;
    private greedc.ws.client.oa.ArrayOfString workflowHtmlTemplete;
    private greedc.ws.client.oa.WorkflowMainTableInfo workflowMainTableInfo;
    private greedc.ws.client.oa.ArrayOfArrayOfString workflowPhrases;
    private greedc.ws.client.oa.ArrayOfWorkflowRequestLog workflowRequestLogs;

    public CustomWorkflowRequestInfo() {
    }

    public CustomWorkflowRequestInfo(
           java.lang.String applicantCode,
           java.lang.Boolean canEdit,
           java.lang.Boolean canView,
           java.lang.String createTime,
           java.lang.String creatorId,
           java.lang.String creatorName,
           java.lang.String currentNodeId,
           java.lang.String currentNodeName,
           java.lang.String forwardButtonName,
           java.lang.String lastOperateTime,
           java.lang.String lastOperatorName,
           java.lang.String messageType,
           java.lang.Boolean mustInputRemark,
           java.lang.Boolean needAffirmance,
           java.lang.String receiveTime,
           java.lang.String rejectButtonName,
           java.lang.String remark,
           java.lang.String requestId,
           java.lang.String requestLevel,
           java.lang.String requestName,
           java.lang.String status,
           java.lang.String subbackButtonName,
           java.lang.String submitButtonName,
           java.lang.String subnobackButtonName,
           greedc.ws.client.oa.WorkflowBaseInfo workflowBaseInfo,
           greedc.ws.client.oa.ArrayOfWorkflowDetailTableInfo workflowDetailTableInfos,
           greedc.ws.client.oa.ArrayOfString workflowHtmlShow,
           greedc.ws.client.oa.ArrayOfString workflowHtmlTemplete,
           greedc.ws.client.oa.WorkflowMainTableInfo workflowMainTableInfo,
           greedc.ws.client.oa.ArrayOfArrayOfString workflowPhrases,
           greedc.ws.client.oa.ArrayOfWorkflowRequestLog workflowRequestLogs) {
           this.applicantCode = applicantCode;
           this.canEdit = canEdit;
           this.canView = canView;
           this.createTime = createTime;
           this.creatorId = creatorId;
           this.creatorName = creatorName;
           this.currentNodeId = currentNodeId;
           this.currentNodeName = currentNodeName;
           this.forwardButtonName = forwardButtonName;
           this.lastOperateTime = lastOperateTime;
           this.lastOperatorName = lastOperatorName;
           this.messageType = messageType;
           this.mustInputRemark = mustInputRemark;
           this.needAffirmance = needAffirmance;
           this.receiveTime = receiveTime;
           this.rejectButtonName = rejectButtonName;
           this.remark = remark;
           this.requestId = requestId;
           this.requestLevel = requestLevel;
           this.requestName = requestName;
           this.status = status;
           this.subbackButtonName = subbackButtonName;
           this.submitButtonName = submitButtonName;
           this.subnobackButtonName = subnobackButtonName;
           this.workflowBaseInfo = workflowBaseInfo;
           this.workflowDetailTableInfos = workflowDetailTableInfos;
           this.workflowHtmlShow = workflowHtmlShow;
           this.workflowHtmlTemplete = workflowHtmlTemplete;
           this.workflowMainTableInfo = workflowMainTableInfo;
           this.workflowPhrases = workflowPhrases;
           this.workflowRequestLogs = workflowRequestLogs;
    }


    /**
     * Gets the applicantCode value for this CustomWorkflowRequestInfo.
     * 
     * @return applicantCode
     */
    public java.lang.String getApplicantCode() {
        return applicantCode;
    }


    /**
     * Sets the applicantCode value for this CustomWorkflowRequestInfo.
     * 
     * @param applicantCode
     */
    public void setApplicantCode(java.lang.String applicantCode) {
        this.applicantCode = applicantCode;
    }


    /**
     * Gets the canEdit value for this CustomWorkflowRequestInfo.
     * 
     * @return canEdit
     */
    public java.lang.Boolean getCanEdit() {
        return canEdit;
    }


    /**
     * Sets the canEdit value for this CustomWorkflowRequestInfo.
     * 
     * @param canEdit
     */
    public void setCanEdit(java.lang.Boolean canEdit) {
        this.canEdit = canEdit;
    }


    /**
     * Gets the canView value for this CustomWorkflowRequestInfo.
     * 
     * @return canView
     */
    public java.lang.Boolean getCanView() {
        return canView;
    }


    /**
     * Sets the canView value for this CustomWorkflowRequestInfo.
     * 
     * @param canView
     */
    public void setCanView(java.lang.Boolean canView) {
        this.canView = canView;
    }


    /**
     * Gets the createTime value for this CustomWorkflowRequestInfo.
     * 
     * @return createTime
     */
    public java.lang.String getCreateTime() {
        return createTime;
    }


    /**
     * Sets the createTime value for this CustomWorkflowRequestInfo.
     * 
     * @param createTime
     */
    public void setCreateTime(java.lang.String createTime) {
        this.createTime = createTime;
    }


    /**
     * Gets the creatorId value for this CustomWorkflowRequestInfo.
     * 
     * @return creatorId
     */
    public java.lang.String getCreatorId() {
        return creatorId;
    }


    /**
     * Sets the creatorId value for this CustomWorkflowRequestInfo.
     * 
     * @param creatorId
     */
    public void setCreatorId(java.lang.String creatorId) {
        this.creatorId = creatorId;
    }


    /**
     * Gets the creatorName value for this CustomWorkflowRequestInfo.
     * 
     * @return creatorName
     */
    public java.lang.String getCreatorName() {
        return creatorName;
    }


    /**
     * Sets the creatorName value for this CustomWorkflowRequestInfo.
     * 
     * @param creatorName
     */
    public void setCreatorName(java.lang.String creatorName) {
        this.creatorName = creatorName;
    }


    /**
     * Gets the currentNodeId value for this CustomWorkflowRequestInfo.
     * 
     * @return currentNodeId
     */
    public java.lang.String getCurrentNodeId() {
        return currentNodeId;
    }


    /**
     * Sets the currentNodeId value for this CustomWorkflowRequestInfo.
     * 
     * @param currentNodeId
     */
    public void setCurrentNodeId(java.lang.String currentNodeId) {
        this.currentNodeId = currentNodeId;
    }


    /**
     * Gets the currentNodeName value for this CustomWorkflowRequestInfo.
     * 
     * @return currentNodeName
     */
    public java.lang.String getCurrentNodeName() {
        return currentNodeName;
    }


    /**
     * Sets the currentNodeName value for this CustomWorkflowRequestInfo.
     * 
     * @param currentNodeName
     */
    public void setCurrentNodeName(java.lang.String currentNodeName) {
        this.currentNodeName = currentNodeName;
    }


    /**
     * Gets the forwardButtonName value for this CustomWorkflowRequestInfo.
     * 
     * @return forwardButtonName
     */
    public java.lang.String getForwardButtonName() {
        return forwardButtonName;
    }


    /**
     * Sets the forwardButtonName value for this CustomWorkflowRequestInfo.
     * 
     * @param forwardButtonName
     */
    public void setForwardButtonName(java.lang.String forwardButtonName) {
        this.forwardButtonName = forwardButtonName;
    }


    /**
     * Gets the lastOperateTime value for this CustomWorkflowRequestInfo.
     * 
     * @return lastOperateTime
     */
    public java.lang.String getLastOperateTime() {
        return lastOperateTime;
    }


    /**
     * Sets the lastOperateTime value for this CustomWorkflowRequestInfo.
     * 
     * @param lastOperateTime
     */
    public void setLastOperateTime(java.lang.String lastOperateTime) {
        this.lastOperateTime = lastOperateTime;
    }


    /**
     * Gets the lastOperatorName value for this CustomWorkflowRequestInfo.
     * 
     * @return lastOperatorName
     */
    public java.lang.String getLastOperatorName() {
        return lastOperatorName;
    }


    /**
     * Sets the lastOperatorName value for this CustomWorkflowRequestInfo.
     * 
     * @param lastOperatorName
     */
    public void setLastOperatorName(java.lang.String lastOperatorName) {
        this.lastOperatorName = lastOperatorName;
    }


    /**
     * Gets the messageType value for this CustomWorkflowRequestInfo.
     * 
     * @return messageType
     */
    public java.lang.String getMessageType() {
        return messageType;
    }


    /**
     * Sets the messageType value for this CustomWorkflowRequestInfo.
     * 
     * @param messageType
     */
    public void setMessageType(java.lang.String messageType) {
        this.messageType = messageType;
    }


    /**
     * Gets the mustInputRemark value for this CustomWorkflowRequestInfo.
     * 
     * @return mustInputRemark
     */
    public java.lang.Boolean getMustInputRemark() {
        return mustInputRemark;
    }


    /**
     * Sets the mustInputRemark value for this CustomWorkflowRequestInfo.
     * 
     * @param mustInputRemark
     */
    public void setMustInputRemark(java.lang.Boolean mustInputRemark) {
        this.mustInputRemark = mustInputRemark;
    }


    /**
     * Gets the needAffirmance value for this CustomWorkflowRequestInfo.
     * 
     * @return needAffirmance
     */
    public java.lang.Boolean getNeedAffirmance() {
        return needAffirmance;
    }


    /**
     * Sets the needAffirmance value for this CustomWorkflowRequestInfo.
     * 
     * @param needAffirmance
     */
    public void setNeedAffirmance(java.lang.Boolean needAffirmance) {
        this.needAffirmance = needAffirmance;
    }


    /**
     * Gets the receiveTime value for this CustomWorkflowRequestInfo.
     * 
     * @return receiveTime
     */
    public java.lang.String getReceiveTime() {
        return receiveTime;
    }


    /**
     * Sets the receiveTime value for this CustomWorkflowRequestInfo.
     * 
     * @param receiveTime
     */
    public void setReceiveTime(java.lang.String receiveTime) {
        this.receiveTime = receiveTime;
    }


    /**
     * Gets the rejectButtonName value for this CustomWorkflowRequestInfo.
     * 
     * @return rejectButtonName
     */
    public java.lang.String getRejectButtonName() {
        return rejectButtonName;
    }


    /**
     * Sets the rejectButtonName value for this CustomWorkflowRequestInfo.
     * 
     * @param rejectButtonName
     */
    public void setRejectButtonName(java.lang.String rejectButtonName) {
        this.rejectButtonName = rejectButtonName;
    }


    /**
     * Gets the remark value for this CustomWorkflowRequestInfo.
     * 
     * @return remark
     */
    public java.lang.String getRemark() {
        return remark;
    }


    /**
     * Sets the remark value for this CustomWorkflowRequestInfo.
     * 
     * @param remark
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }


    /**
     * Gets the requestId value for this CustomWorkflowRequestInfo.
     * 
     * @return requestId
     */
    public java.lang.String getRequestId() {
        return requestId;
    }


    /**
     * Sets the requestId value for this CustomWorkflowRequestInfo.
     * 
     * @param requestId
     */
    public void setRequestId(java.lang.String requestId) {
        this.requestId = requestId;
    }


    /**
     * Gets the requestLevel value for this CustomWorkflowRequestInfo.
     * 
     * @return requestLevel
     */
    public java.lang.String getRequestLevel() {
        return requestLevel;
    }


    /**
     * Sets the requestLevel value for this CustomWorkflowRequestInfo.
     * 
     * @param requestLevel
     */
    public void setRequestLevel(java.lang.String requestLevel) {
        this.requestLevel = requestLevel;
    }


    /**
     * Gets the requestName value for this CustomWorkflowRequestInfo.
     * 
     * @return requestName
     */
    public java.lang.String getRequestName() {
        return requestName;
    }


    /**
     * Sets the requestName value for this CustomWorkflowRequestInfo.
     * 
     * @param requestName
     */
    public void setRequestName(java.lang.String requestName) {
        this.requestName = requestName;
    }


    /**
     * Gets the status value for this CustomWorkflowRequestInfo.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this CustomWorkflowRequestInfo.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the subbackButtonName value for this CustomWorkflowRequestInfo.
     * 
     * @return subbackButtonName
     */
    public java.lang.String getSubbackButtonName() {
        return subbackButtonName;
    }


    /**
     * Sets the subbackButtonName value for this CustomWorkflowRequestInfo.
     * 
     * @param subbackButtonName
     */
    public void setSubbackButtonName(java.lang.String subbackButtonName) {
        this.subbackButtonName = subbackButtonName;
    }


    /**
     * Gets the submitButtonName value for this CustomWorkflowRequestInfo.
     * 
     * @return submitButtonName
     */
    public java.lang.String getSubmitButtonName() {
        return submitButtonName;
    }


    /**
     * Sets the submitButtonName value for this CustomWorkflowRequestInfo.
     * 
     * @param submitButtonName
     */
    public void setSubmitButtonName(java.lang.String submitButtonName) {
        this.submitButtonName = submitButtonName;
    }


    /**
     * Gets the subnobackButtonName value for this CustomWorkflowRequestInfo.
     * 
     * @return subnobackButtonName
     */
    public java.lang.String getSubnobackButtonName() {
        return subnobackButtonName;
    }


    /**
     * Sets the subnobackButtonName value for this CustomWorkflowRequestInfo.
     * 
     * @param subnobackButtonName
     */
    public void setSubnobackButtonName(java.lang.String subnobackButtonName) {
        this.subnobackButtonName = subnobackButtonName;
    }


    /**
     * Gets the workflowBaseInfo value for this CustomWorkflowRequestInfo.
     * 
     * @return workflowBaseInfo
     */
    public greedc.ws.client.oa.WorkflowBaseInfo getWorkflowBaseInfo() {
        return workflowBaseInfo;
    }


    /**
     * Sets the workflowBaseInfo value for this CustomWorkflowRequestInfo.
     * 
     * @param workflowBaseInfo
     */
    public void setWorkflowBaseInfo(greedc.ws.client.oa.WorkflowBaseInfo workflowBaseInfo) {
        this.workflowBaseInfo = workflowBaseInfo;
    }


    /**
     * Gets the workflowDetailTableInfos value for this CustomWorkflowRequestInfo.
     * 
     * @return workflowDetailTableInfos
     */
    public greedc.ws.client.oa.ArrayOfWorkflowDetailTableInfo getWorkflowDetailTableInfos() {
        return workflowDetailTableInfos;
    }


    /**
     * Sets the workflowDetailTableInfos value for this CustomWorkflowRequestInfo.
     * 
     * @param workflowDetailTableInfos
     */
    public void setWorkflowDetailTableInfos(greedc.ws.client.oa.ArrayOfWorkflowDetailTableInfo workflowDetailTableInfos) {
        this.workflowDetailTableInfos = workflowDetailTableInfos;
    }


    /**
     * Gets the workflowHtmlShow value for this CustomWorkflowRequestInfo.
     * 
     * @return workflowHtmlShow
     */
    public greedc.ws.client.oa.ArrayOfString getWorkflowHtmlShow() {
        return workflowHtmlShow;
    }


    /**
     * Sets the workflowHtmlShow value for this CustomWorkflowRequestInfo.
     * 
     * @param workflowHtmlShow
     */
    public void setWorkflowHtmlShow(greedc.ws.client.oa.ArrayOfString workflowHtmlShow) {
        this.workflowHtmlShow = workflowHtmlShow;
    }


    /**
     * Gets the workflowHtmlTemplete value for this CustomWorkflowRequestInfo.
     * 
     * @return workflowHtmlTemplete
     */
    public greedc.ws.client.oa.ArrayOfString getWorkflowHtmlTemplete() {
        return workflowHtmlTemplete;
    }


    /**
     * Sets the workflowHtmlTemplete value for this CustomWorkflowRequestInfo.
     * 
     * @param workflowHtmlTemplete
     */
    public void setWorkflowHtmlTemplete(greedc.ws.client.oa.ArrayOfString workflowHtmlTemplete) {
        this.workflowHtmlTemplete = workflowHtmlTemplete;
    }


    /**
     * Gets the workflowMainTableInfo value for this CustomWorkflowRequestInfo.
     * 
     * @return workflowMainTableInfo
     */
    public greedc.ws.client.oa.WorkflowMainTableInfo getWorkflowMainTableInfo() {
        return workflowMainTableInfo;
    }


    /**
     * Sets the workflowMainTableInfo value for this CustomWorkflowRequestInfo.
     * 
     * @param workflowMainTableInfo
     */
    public void setWorkflowMainTableInfo(greedc.ws.client.oa.WorkflowMainTableInfo workflowMainTableInfo) {
        this.workflowMainTableInfo = workflowMainTableInfo;
    }


    /**
     * Gets the workflowPhrases value for this CustomWorkflowRequestInfo.
     * 
     * @return workflowPhrases
     */
    public greedc.ws.client.oa.ArrayOfArrayOfString getWorkflowPhrases() {
        return workflowPhrases;
    }


    /**
     * Sets the workflowPhrases value for this CustomWorkflowRequestInfo.
     * 
     * @param workflowPhrases
     */
    public void setWorkflowPhrases(greedc.ws.client.oa.ArrayOfArrayOfString workflowPhrases) {
        this.workflowPhrases = workflowPhrases;
    }


    /**
     * Gets the workflowRequestLogs value for this CustomWorkflowRequestInfo.
     * 
     * @return workflowRequestLogs
     */
    public greedc.ws.client.oa.ArrayOfWorkflowRequestLog getWorkflowRequestLogs() {
        return workflowRequestLogs;
    }


    /**
     * Sets the workflowRequestLogs value for this CustomWorkflowRequestInfo.
     * 
     * @param workflowRequestLogs
     */
    public void setWorkflowRequestLogs(greedc.ws.client.oa.ArrayOfWorkflowRequestLog workflowRequestLogs) {
        this.workflowRequestLogs = workflowRequestLogs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CustomWorkflowRequestInfo)) return false;
        CustomWorkflowRequestInfo other = (CustomWorkflowRequestInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicantCode==null && other.getApplicantCode()==null) || 
             (this.applicantCode!=null &&
              this.applicantCode.equals(other.getApplicantCode()))) &&
            ((this.canEdit==null && other.getCanEdit()==null) || 
             (this.canEdit!=null &&
              this.canEdit.equals(other.getCanEdit()))) &&
            ((this.canView==null && other.getCanView()==null) || 
             (this.canView!=null &&
              this.canView.equals(other.getCanView()))) &&
            ((this.createTime==null && other.getCreateTime()==null) || 
             (this.createTime!=null &&
              this.createTime.equals(other.getCreateTime()))) &&
            ((this.creatorId==null && other.getCreatorId()==null) || 
             (this.creatorId!=null &&
              this.creatorId.equals(other.getCreatorId()))) &&
            ((this.creatorName==null && other.getCreatorName()==null) || 
             (this.creatorName!=null &&
              this.creatorName.equals(other.getCreatorName()))) &&
            ((this.currentNodeId==null && other.getCurrentNodeId()==null) || 
             (this.currentNodeId!=null &&
              this.currentNodeId.equals(other.getCurrentNodeId()))) &&
            ((this.currentNodeName==null && other.getCurrentNodeName()==null) || 
             (this.currentNodeName!=null &&
              this.currentNodeName.equals(other.getCurrentNodeName()))) &&
            ((this.forwardButtonName==null && other.getForwardButtonName()==null) || 
             (this.forwardButtonName!=null &&
              this.forwardButtonName.equals(other.getForwardButtonName()))) &&
            ((this.lastOperateTime==null && other.getLastOperateTime()==null) || 
             (this.lastOperateTime!=null &&
              this.lastOperateTime.equals(other.getLastOperateTime()))) &&
            ((this.lastOperatorName==null && other.getLastOperatorName()==null) || 
             (this.lastOperatorName!=null &&
              this.lastOperatorName.equals(other.getLastOperatorName()))) &&
            ((this.messageType==null && other.getMessageType()==null) || 
             (this.messageType!=null &&
              this.messageType.equals(other.getMessageType()))) &&
            ((this.mustInputRemark==null && other.getMustInputRemark()==null) || 
             (this.mustInputRemark!=null &&
              this.mustInputRemark.equals(other.getMustInputRemark()))) &&
            ((this.needAffirmance==null && other.getNeedAffirmance()==null) || 
             (this.needAffirmance!=null &&
              this.needAffirmance.equals(other.getNeedAffirmance()))) &&
            ((this.receiveTime==null && other.getReceiveTime()==null) || 
             (this.receiveTime!=null &&
              this.receiveTime.equals(other.getReceiveTime()))) &&
            ((this.rejectButtonName==null && other.getRejectButtonName()==null) || 
             (this.rejectButtonName!=null &&
              this.rejectButtonName.equals(other.getRejectButtonName()))) &&
            ((this.remark==null && other.getRemark()==null) || 
             (this.remark!=null &&
              this.remark.equals(other.getRemark()))) &&
            ((this.requestId==null && other.getRequestId()==null) || 
             (this.requestId!=null &&
              this.requestId.equals(other.getRequestId()))) &&
            ((this.requestLevel==null && other.getRequestLevel()==null) || 
             (this.requestLevel!=null &&
              this.requestLevel.equals(other.getRequestLevel()))) &&
            ((this.requestName==null && other.getRequestName()==null) || 
             (this.requestName!=null &&
              this.requestName.equals(other.getRequestName()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.subbackButtonName==null && other.getSubbackButtonName()==null) || 
             (this.subbackButtonName!=null &&
              this.subbackButtonName.equals(other.getSubbackButtonName()))) &&
            ((this.submitButtonName==null && other.getSubmitButtonName()==null) || 
             (this.submitButtonName!=null &&
              this.submitButtonName.equals(other.getSubmitButtonName()))) &&
            ((this.subnobackButtonName==null && other.getSubnobackButtonName()==null) || 
             (this.subnobackButtonName!=null &&
              this.subnobackButtonName.equals(other.getSubnobackButtonName()))) &&
            ((this.workflowBaseInfo==null && other.getWorkflowBaseInfo()==null) || 
             (this.workflowBaseInfo!=null &&
              this.workflowBaseInfo.equals(other.getWorkflowBaseInfo()))) &&
            ((this.workflowDetailTableInfos==null && other.getWorkflowDetailTableInfos()==null) || 
             (this.workflowDetailTableInfos!=null &&
              this.workflowDetailTableInfos.equals(other.getWorkflowDetailTableInfos()))) &&
            ((this.workflowHtmlShow==null && other.getWorkflowHtmlShow()==null) || 
             (this.workflowHtmlShow!=null &&
              this.workflowHtmlShow.equals(other.getWorkflowHtmlShow()))) &&
            ((this.workflowHtmlTemplete==null && other.getWorkflowHtmlTemplete()==null) || 
             (this.workflowHtmlTemplete!=null &&
              this.workflowHtmlTemplete.equals(other.getWorkflowHtmlTemplete()))) &&
            ((this.workflowMainTableInfo==null && other.getWorkflowMainTableInfo()==null) || 
             (this.workflowMainTableInfo!=null &&
              this.workflowMainTableInfo.equals(other.getWorkflowMainTableInfo()))) &&
            ((this.workflowPhrases==null && other.getWorkflowPhrases()==null) || 
             (this.workflowPhrases!=null &&
              this.workflowPhrases.equals(other.getWorkflowPhrases()))) &&
            ((this.workflowRequestLogs==null && other.getWorkflowRequestLogs()==null) || 
             (this.workflowRequestLogs!=null &&
              this.workflowRequestLogs.equals(other.getWorkflowRequestLogs())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getApplicantCode() != null) {
            _hashCode += getApplicantCode().hashCode();
        }
        if (getCanEdit() != null) {
            _hashCode += getCanEdit().hashCode();
        }
        if (getCanView() != null) {
            _hashCode += getCanView().hashCode();
        }
        if (getCreateTime() != null) {
            _hashCode += getCreateTime().hashCode();
        }
        if (getCreatorId() != null) {
            _hashCode += getCreatorId().hashCode();
        }
        if (getCreatorName() != null) {
            _hashCode += getCreatorName().hashCode();
        }
        if (getCurrentNodeId() != null) {
            _hashCode += getCurrentNodeId().hashCode();
        }
        if (getCurrentNodeName() != null) {
            _hashCode += getCurrentNodeName().hashCode();
        }
        if (getForwardButtonName() != null) {
            _hashCode += getForwardButtonName().hashCode();
        }
        if (getLastOperateTime() != null) {
            _hashCode += getLastOperateTime().hashCode();
        }
        if (getLastOperatorName() != null) {
            _hashCode += getLastOperatorName().hashCode();
        }
        if (getMessageType() != null) {
            _hashCode += getMessageType().hashCode();
        }
        if (getMustInputRemark() != null) {
            _hashCode += getMustInputRemark().hashCode();
        }
        if (getNeedAffirmance() != null) {
            _hashCode += getNeedAffirmance().hashCode();
        }
        if (getReceiveTime() != null) {
            _hashCode += getReceiveTime().hashCode();
        }
        if (getRejectButtonName() != null) {
            _hashCode += getRejectButtonName().hashCode();
        }
        if (getRemark() != null) {
            _hashCode += getRemark().hashCode();
        }
        if (getRequestId() != null) {
            _hashCode += getRequestId().hashCode();
        }
        if (getRequestLevel() != null) {
            _hashCode += getRequestLevel().hashCode();
        }
        if (getRequestName() != null) {
            _hashCode += getRequestName().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getSubbackButtonName() != null) {
            _hashCode += getSubbackButtonName().hashCode();
        }
        if (getSubmitButtonName() != null) {
            _hashCode += getSubmitButtonName().hashCode();
        }
        if (getSubnobackButtonName() != null) {
            _hashCode += getSubnobackButtonName().hashCode();
        }
        if (getWorkflowBaseInfo() != null) {
            _hashCode += getWorkflowBaseInfo().hashCode();
        }
        if (getWorkflowDetailTableInfos() != null) {
            _hashCode += getWorkflowDetailTableInfos().hashCode();
        }
        if (getWorkflowHtmlShow() != null) {
            _hashCode += getWorkflowHtmlShow().hashCode();
        }
        if (getWorkflowHtmlTemplete() != null) {
            _hashCode += getWorkflowHtmlTemplete().hashCode();
        }
        if (getWorkflowMainTableInfo() != null) {
            _hashCode += getWorkflowMainTableInfo().hashCode();
        }
        if (getWorkflowPhrases() != null) {
            _hashCode += getWorkflowPhrases().hashCode();
        }
        if (getWorkflowRequestLogs() != null) {
            _hashCode += getWorkflowRequestLogs().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CustomWorkflowRequestInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "CustomWorkflowRequestInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicantCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "applicantCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("canEdit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "canEdit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("canView");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "canView"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "createTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creatorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "creatorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creatorName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "creatorName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentNodeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "currentNodeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentNodeName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "currentNodeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forwardButtonName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "forwardButtonName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastOperateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "lastOperateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastOperatorName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "lastOperatorName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "messageType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mustInputRemark");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "mustInputRemark"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("needAffirmance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "needAffirmance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiveTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "receiveTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rejectButtonName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "rejectButtonName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remark");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "remark"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "requestId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "requestLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "requestName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subbackButtonName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "subbackButtonName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("submitButtonName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "submitButtonName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subnobackButtonName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "subnobackButtonName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowBaseInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "workflowBaseInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "WorkflowBaseInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowDetailTableInfos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "workflowDetailTableInfos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "ArrayOfWorkflowDetailTableInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowHtmlShow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "workflowHtmlShow"));
        elemField.setXmlType(new javax.xml.namespace.QName("webservices.services.weaver.com.cn", "ArrayOfString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowHtmlTemplete");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "workflowHtmlTemplete"));
        elemField.setXmlType(new javax.xml.namespace.QName("webservices.services.weaver.com.cn", "ArrayOfString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowMainTableInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "workflowMainTableInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "WorkflowMainTableInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowPhrases");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "workflowPhrases"));
        elemField.setXmlType(new javax.xml.namespace.QName("webservices.services.weaver.com.cn", "ArrayOfArrayOfString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowRequestLogs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://oa.server.webservice.ecustom", "workflowRequestLogs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.workflow.weaver", "ArrayOfWorkflowRequestLog"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
