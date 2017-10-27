<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>
<html>
    <head>
        <meta name="generator" content="HTML Tidy, see www.w3.org">
        <meta http-equiv="Content-Type" content= "text/html; charset=UTF-8">
        <title>jQuery EasyUI</title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
        <script type="text/javascript">
            $(function(){
                
            });
 
           //一号工程的入库单数据XMl串解析
            function runXmlParse(){
            	$.ajax({ 
        		    url: baseURL+'/test/toparseXml.json', 
        		    type: 'POST', 
        		    success: function(data){
        		    	var url;
        				$(data.rows).each(function(i,val) {
        				}); 
        			}
        		   }); 
            }
           

           
 
   
        </script>
    </head>
    <body>
    <table>
    <tr>
    <td>
    <input class="easyui-textbox"  name="xmlparse" id="xmlparse" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){runXmlParse();},prompt:'一号工程xml处理测试'" style="width:260px;height:24px;">
    </td>
    </tr>
    </table>

    </body>
</html> 