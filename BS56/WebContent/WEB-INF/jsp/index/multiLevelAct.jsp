<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>测试多级联动</title>
<script type="text/javascript" src="<spring:url value="/js/jquery-1.12.3.min.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/fileupload/jquery.ui.widget.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/fileupload/jquery.iframe-transport.js"/>"></script>
<script type="text/javascript">
    $(function() {
    	//alert("111");
        initProvinces();
    });
    /**
     * 获取省列表
     */
     function initProvinces() {
     	var html = "";
         $('#province').empty();
         $("<option >" +"---请选择---"+ "</option>").appendTo("#province");
         $.ajax({
             type : "POST",
             url :  "/BS56/getProvinces.json",
             success : function(data) {
                 $.each(data.ones, function(i, one) {
                 	html = "";
                 	html = "<option value='" + one.tId + "'>" + one.tName + "</option>";
                    $(html).appendTo("#province");
                 });
                 
                 $('#province').change(function() {
              		var oneId= $("#province").val(); 
              		if(oneId!=null&&oneId!="")
              			{
              			initCities(oneId);
              			}
              		else{
              			alert("请选择类别");
              		}
          
                 });
             },
             error: function(e) { 
             	//$("<option >" +"---市---"+ "</option>").appendTo("#city");
              	} 

        });
     }
    /**
     * 获取市列表
     */
    function initCities(provinceID) {
        $('#city').empty();
        $("<option >" +"---请选择---"+ "</option>").appendTo("#city");
        $.ajax({
            type : "POST",
            url : "/BS56/getCities.json?tId=" + provinceID,
            success : function(data) {
                $.each(data.twos, function(i, two) {
                	html = "";
                 	html = "<option value='" + two.tId + "'>" + two.tName + "</option>";
                    $(html).appendTo("#city");
                });
                
                $('#city').change(function() {
                	var twoId= $("#city").val();
                	if(twoId!=null&&twoId!="")
          			{
                		initCounties(twoId);
          			}
          		else{
          			alert("请选择类别2");
          		}
                });
            },
            error: function(e) { 
            	//$("<option >" +"---市---"+ "</option>").appendTo("#city");
             	} 
        });
    }
    /**
     * 获取区县列表
     */
    function initCounties(cityID) {
        $('#county').empty();
        $("<option >" +"---请选择---"+ "</option>").appendTo("#county");
        $.ajax({
            type : "POST",
            url : "/BS56/getCounties.json?tId=" + cityID,
            success : function(data) {
                $.each(data.threes, function(i, three) {
                	html = "";
                 	html = "<option value='" + three.tId + "'>" + three.tName + "</option>";
                    $(html).appendTo("#county");
                });
            },
            error: function(e) { 
            	//$("<option >" +"---区---"+ "</option>").appendTo("#county");
             	} 
 });
}

</script>
<body>
 选择类别：
 <select id='province'><option>---请选择---</option></select>
 <select id='city'><option>---请选择---</option></select>
 <select id='county'><option>---请选择---</option></select>
</body>
</html>
