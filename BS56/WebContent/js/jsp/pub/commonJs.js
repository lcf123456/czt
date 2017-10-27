//全局服务名
var baseURL="/BS56";

//功能点列表
//var opList = "";



//删除左右两端的空格
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

//返回yyyy-mm-dd
function getDateYMD() {
    var date = new Date();
    var seperator1 = "-";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
} 

//返回yyyy-mm-dd hh-mi-ss
function getDateYMDHMS() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
} 

//返回yyyy-01-01
function getDateYY0101()
{
	var date = new Date();
	var seperator1 = "-";
	var dateStr = date.getFullYear() + seperator1 +"01-01";
	return dateStr;
	}

//返回yyyy-mm-01
function getDateYM01() {
    var date = new Date();
    var seperator1 = "-";
    var month = date.getMonth() + 1;
    var strDate = "01";
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    var dateStr = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return dateStr;
} 

//返回yyyy-mm
function getDateYM() {
    var date = new Date();
    var seperator1 = "-";
    var month = date.getMonth() + 1;
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    var dateStr = date.getFullYear() + seperator1 + month;
    return dateStr;
} 

//初始化obj控件，返回yyyy-mm
function initDataboxYM(obj){
	//alert("00_"+getDateYM());
	//obj.datebox("setValue",getDateYM());
	obj.datebox({  
	        onShowPanel : function() {// 显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层  
	            span.trigger('click'); // 触发click事件弹出月份层  
	            if (!tds)  
	                setTimeout(function() {// 延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔  
	                    tds = p.find('div.calendar-menu-month-inner td');  
	                    tds.click(function(e) {  
	                        e.stopPropagation(); // 禁止冒泡执行easyui给月份绑定的事件  
	                        var year = /\d{4}/.exec(span.html())[0]// 得到年份  
	                        , month = parseInt($(this).attr('abbr'), 10) + 1; // 月份  
	                        obj.datebox('hidePanel')// 隐藏日期对象  
	                        .datebox('setValue', year + '-' + month); // 设置日期的值  
	                    });  
	                }, 0);  
	        },  
	        parser : function(s) {// 配置parser，返回选择的日期  
	            if (!s)  
	                return new Date();  
	            var arr = s.split('-');  
	            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);  
	        },  
	        formatter : function(d) {  
	        	var month = d.getMonth();
	        	if(month<10){
	        		month = "0"+month;
	        	}
	            if (d.getMonth() == 0) {  
	                return d.getFullYear()-1 + '-' + 12;  
	            } else {  
	                return d.getFullYear() + '-' + month;  
	            }  
	        }// 配置formatter，只返回年月  
	    });  
	    var p = obj.datebox('panel'), // 日期选择对象  
	    tds = false, // 日期选择对象中月份  
	    span = p.find('span.calendar-text'); // 显示月份层的触发控件  
}	 

Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1, // month  
        "d+": this.getDate(), // day  
        "h+": this.getHours(), // hour  
        "m+": this.getMinutes(), // minute  
        "s+": this.getSeconds(), // second  
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S": this.getMilliseconds()  
        // millisecond  
    }  
    if (/(y+)/.test(format))  
        format = format.replace(RegExp.$1, (this.getFullYear() + "")  
            .substr(4 - RegExp.$1.length));  
    for (var k in o)  
        if (new RegExp("(" + k + ")").test(format))  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
    return format;  
} 

//格式化日期，对于datagrid返回日期格式如2017-08-22 00:00:00的，格式化为2017-08-22 ,订单出库中有调用
function formatDatebox(value) {  
    if (value == null || value == '') {  
        return '';  
    }  
    var dt;  
    if (value instanceof Date) {  
        dt = value;  
    } else {  
        dt = new Date(value);  
    }  
    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)  
} 