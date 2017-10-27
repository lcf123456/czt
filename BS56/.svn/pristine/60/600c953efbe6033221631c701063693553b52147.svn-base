$(document).ready(function() {
	registerEvent();
});

/**
 * Register events
 * @param elemSelector jquery object,jquery selector or dom
 */
function registerEvent(elemSelector) {
	var elem = !elemSelector ? $(document) : $(elemSelector);
	//Ajax link
	elem.find("a.ajax-link").click(function() {
		return ajaxLink($(this));
	});
	//Pagination jump btn
	elem.find(".pagination-btn").click(function(){
		return paginationBtn($(this));
	});
}

function ajaxLink(link) {
	//var link = $(this);
	var target = link.data("target");
	if (!target) {
		target = link.parents(".ajax-link-ctn:eq(0)");
	}
	else {
		target = $(target);
	}
	if (target.size() == 1) {
		var callback = link.data("callback");
		target.load(link.attr("href"), function() {
			registerEvent(target);
			if (callback) {
				eval(callback + "(link,target)");
			}
		});
		return false;
	}
	return true;	
}

function paginationBtn(btn) {
	var input = btn.prev("input");
	var currentPageNo = toInt(input.val(), 0);
	if (currentPageNo <= 0) {
		return showError(input, "翻页数输入错误");
	}
	var totalPage = toInt(input.data("total-page"), 0);
	if (currentPageNo > totalPage) {
		return showError(input, "翻页数不能大于"+totalPage);
	}
	var href = btn.attr("href");
	if (href.indexOf("?") == -1) {
		href += "?";
	}
	href += ("&pageNo=" + currentPageNo);
	btn.attr("href", href);
	return ajaxLink(btn);
}

/**
 * Showing error
 * @param obj
 * @param defaultError
 * @returns {Boolean}
 */
function showError(obj, defaultError) {
	obj.select();
	var error = obj.data("error");
	if (error) {
		alert(error);
	}
	else {
		var name = obj.attr("name");
		if (name) {
			defaultError = "字段" + name + "有误," + defaultError;
		}
		alert(defaultError);
	}
	return false;
}

/**
 * Parse string to integer
 * @param str
 * @param def
 * @returns
 */
function toInt(str, def) {
	var val = parseInt(str, 10);
	return isNaN(val) ? def : val;
}

