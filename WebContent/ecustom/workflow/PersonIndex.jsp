<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>个人季度绩效考核表</title>
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/metro-blue/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ecustom/js/easyui/demo/demo.css">
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/ecustom/js/easyui/locale/easyui-lang-zh_CN.js"></script>
  	<script type="text/javascript" src="/ecustom/js/jetbrick.print.js"></script>
    <script type="text/javascript" src="PersonIndex.js?time=201810111421"></script>
    <style type="text/css">
        html {
            overflow-x:hidden;
        }
    </style>
</head>


<body style="padding: 10px;">
<input type="hidden" id="year" name="year" value="<%=request.getParameter("year") %>">
<input type="hidden" id="season" name="season" value="<%=request.getParameter("season") %>">
<input type="hidden" id="userId" name="userId" value="<%=request.getParameter("userId") %>">

<div style="width:1024px; padding: 10px; margin-left: auto; margin-right: auto;">
    <div style="text-align: right;">
        <a id="btnReject" href="javascript:void(0)" onclick="reject()" class="easyui-linkbutton" style="display: none;">退回</a>
        <a id="btnEdit" href="javascript:void(0)" onclick="edit()" class="easyui-linkbutton" style="display: none;">编辑</a>
        <a id="btnCancelEdit" href="javascript:void(0)" onclick="cancelEdit()" class="easyui-linkbutton" style="display: none;">取消编辑</a>
        <a id="btnSave" href="javascript:void(0)" onclick="save()" class="easyui-linkbutton" style="display: none;">保存</a>
        <a id="submit" href="javascript:void(0)" onclick="submit()" class="easyui-linkbutton" style="display: none;">提交</a>
        <a id="saveManagerMark" href="javascript:void(0)" onclick="saveManagerMark()" class="easyui-linkbutton" style="display: none;">保存评分</a>
      <a id="btnPrint" href="javascript:void(0)" onclick="window.print();" class="easyui-linkbutton">打印</a>
    </div>
    <div align="center" style="font-size: 18px">个人季度绩效考核表</div>

    <div style="margin-top: 10px;">
        <table width="100%">
            <tr>
                <td>
                    <span>被考核人：</span><span id="beikhrName"></span>
                    <input id="beikhr" type="hidden" name="beikhr" value="">
                </td>
                <td>
                    <span>部门负责人：</span><span id="bumfzrName"></span>
                    <input id="bumfzr" type="hidden" name="bumfzr" value="">
                </td>
                <td>
                    <span>考核季度：</span> <span id="yearName"></span> <span id="seasonName"></span>
                </td>
                <td>
                    <span>优良评定：</span><span id="youlpdName"></span>
                </td>
            </tr>
        </table>
    </div>

    <div style="margin-top: 10px;">
        <table id="dgIM" class="easyui-datagrid" title="工作业绩指标（A类权重总和≥50%，B类权重总和≤30%，C类权重总和≤20%）" style="width:100%;"></table>
    </div>

    <div style="margin-top: 10px;">
        <table id="dgBA" class="easyui-datagrid" title="行为能力指标" style="width:100%;"></table>
    </div>

    <div style="height: 10px;"></div>
    <form id="form">
        <div class="easyui-panel" title="评述" collapsible="true" style="width:100%; padding: 10px;">
            <table style="width:100%;border:0px;">
                <tr style="border-bottom:1px solid #e6e6e6;">
                    <td>个人自我评定及<br/>培训发展要求</td>
                    <td>
                        <input id="fazyq" name="fazyq" class="easyui-textbox" multiline="true" readonly style="width:750px;height:100px">
                    </td>
                </tr>
                <tr style="border-bottom:1px solid #e6e6e6;">
                    <td>员工考核成绩</td>
                    <td>
                        <input id="chengj" name="chengj" class="easyui-textbox" readonly style="width:100px;height:30px">
                        <span style="margin-left: 10px; color: #999999;">工作业绩指标审定分 * 0.7 + 行为能力审定分，部门负责人评分结果</span>
                    </td>
                </tr>
                <tr style="border-bottom:1px solid #e6e6e6;">
                    <td>工作业绩评述</td>
                    <td>
                        <input id="gongzyj" name="gongzyj" class="easyui-textbox" multiline="true" readonly style="width:750px;height:50px">
                    </td>
                </tr>
                <tr style="border-bottom:1px solid #e6e6e6;">
                    <td>行为评述</td>
                    <td>
                        <input id="xingwps" name="xingwps" class="easyui-textbox" multiline="true" readonly style="width:750px;height:50px">
                    </td>
                </tr>
                <tr style="border-bottom:1px solid #e6e6e6;">
                    <td>总体评述</td>
                    <td>
                        <input id="zongtps" name="zongtps" class="easyui-textbox" multiline="true" readonly style="width:750px;height:50px">
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
</div>
</body>
</html>