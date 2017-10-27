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
                $('#tt2').tree({
                    checkbox: true,
                    url: '/BS56/sys/getDeptTree.json',
                    onClick: function(node){
                        $(this).tree('toggle', node.target);
                        //alert('you dbclick '+node.text);
                    },
                    onContextMenu: function(e, node){
                        e.preventDefault();
                        $('#tt2').tree('select', node.target);
                        $('#mm').menu('show', {
                            left: e.pageX,
                            top: e.pageY
                        });
                    }
                });
            });
            function reload(){
                var node = $('#tt2').tree('getSelected');
                if (node) {
                    $('#tt2').tree('reload', node.target);
                }
                else {
                    $('#tt2').tree('reload');
                }
            }
           
            function getChildren(){
                var node = $('#tt2').tree('getSelected');
                if (node) {
                    var children = $('#tt2').tree('getChildren', node.target);
                }
                else {
                    var children = $('#tt2').tree('getChildren');
                }
                var s = '';
                for (var i = 0; i < children.length; i++) {
                    s += children[i].text + ',';
                }
                alert(s);
            }
           
            function getChecked(){
                var nodes = $('#tt2').tree('getChecked');
                //alert(nodes);
                var s = '';
                for (var i = 0; i < nodes.length; i++) {
                    if (s != '')
                        s += ',';
                    s += nodes[i].text;
                }
                alert(s);
            }
           
            function getSelected(){
                var node = $('#tt2').tree('getSelected');
                alert(node.text);
            }
           
            function collapse(){
                var node = $('#tt2').tree('getSelected');
                $('#tt2').tree('collapse', node.target);
            }
           
            function expand(){
                var node = $('#tt2').tree('getSelected');
                $('#tt2').tree('expand', node.target);
            }
           
            function collapseAll(){
                var node = $('#tt2').tree('getSelected');
                if (node) {
                    $('#tt2').tree('collapseAll', node.target);
                }
                else {
                    $('#tt2').tree('collapseAll');
                }
            }
           
            function expandAll(){
                var node = $('#tt2').tree('getSelected');
                if (node) {
                    $('#tt2').tree('expandAll', node.target);
                }
                else {
                    $('#tt2').tree('expandAll');
                }
            }
           
            function append(){
                var node = $('#tt2').tree('getSelected');
                $('#tt2').tree('append', {
                    parent: (node ? node.target : null),
                    data: [{
                        text: 'new1',
                        checked: true
                    }, {
                        text: 'new2',
                        state: 'closed',
                        children: [{
                            text: 'subnew1'
                        }, {
                            text: 'subnew2'
                        }]
                    }]
                });
            }
           
            function remove(){
                var node = $('#tt2').tree('getSelected');
                $('#tt2').tree('remove', node.target);
            }
           
            function update(){
                var node = $('#tt2').tree('getSelected');
                if (node) {
                    node.text = '<span style="font-weight:bold">new text<\/span>';
                    node.iconCls = 'icon-save';
                    $('#tt2').tree('update', node);
                }
            }
           
            function isLeaf(){
                var node = $('#tt2').tree('getSelected');
                var b = $('#tt2').tree('isLeaf', node.target);
                alert(b)
            }
           
            function GetNode(type){
                var node = $('#tt2').tree('getChecked');
                var chilenodes = '';
                var parantsnodes = '';
                var prevNode = '';
                for (var i = 0; i < node.length; i++) {
               
                    if ($('#tt2').tree('isLeaf', node[i].target)) {
                        chilenodes += node[i].text + ',';
                       
                        var pnode = $('#tt2').tree('getParent', node[i].target);
                        if (prevNode != pnode.text) {
                            parantsnodes += pnode.text + ',';
                            prevNode = pnode.text;
                        }
                    }
                }
                chilenodes = chilenodes.substring(0, chilenodes.length - 1);
                parantsnodes = parantsnodes.substring(0, parantsnodes.length - 1);
               
                if (type == 'child') {
                    return chilenodes;
                }
                else {
                    return parantsnodes
                };
                };
            function getNodes(){
                alert(GetNode('fnode') + "," + GetNode('child'));
            }
           
            function doNode(){
      var c="";
      var p="";
      $(".tree-checkbox1").parent().children('.tree-title').each(function(){
        c+=$(this).parent().attr('node-id')+",";
      });
       $(".tree-checkbox2").parent().children('.tree-title').each(function(){
     p+=$(this).parent().attr('node-id')+",";
      });
      var str=(c+p);
      str=str.substring(0,str.length-1);
      alert(str);
            }
   
        </script>
    </head>
    <body>
    <table>
    <tr>
    <td>
     <input id="source" class="easyui-combobox" name="source" style="width: 80px;" panelHeight="auto"
				data-options="valueField: 'value',textField: 'label',data: [{label: '直接投诉',value: '10',selected:true},{label: '96368',value: '20'},]"/>
    </td>
    </tr>
    </table>
        <h1>Tree</h1>
        <p>
            Create from HTML markup
        </p>
        <ul id="tt1" class="easyui-tree" animate="true" dnd="true">
            <li>
                <span>Folder</span>
                <ul>
                    <li state="closed">
                        <span>Sub Folder 1</span>
                        <ul>
                            <li>
                                <span><a href="#">File
                                        11</a></span>
                            </li>
                            <li>
                                <span>File 12</span>
                            </li>
                            <li>
                                <span>File 13</span>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span>File 2</span>
                    </li>
                    <li>
                        <span>File 3</span>
                    </li>
                    <li>
                        File 4
                    </li>
                    <li>
                        File 5
                    </li>
                </ul>
            </li>
            <li>
                <span>File21</span>
            </li>
        </ul>
        <hr>
        <p>
            Create from JSON data
        </p>
        <div style="margin:10px;">
            <a href="#" onclick="reload()">reload</a>
            <a href="#" onclick="getChildren()">getChildren</a>
            <a href="#" onclick="getChecked()">getChecked</a>
            <a href="#" onclick="getSelected()">getSelected</a>
            <a href="#" onclick="collapse()">collapse</a>
            <a href="#" onclick="expand()">expand</a>
            <a href="#" onclick="collapseAll()">collapseAll</a>
            <a href="#" onclick="expandAll()">expandAll</a>
            <a href="#" onclick="append()">append</a>
            <a href="#" onclick="remove()">remove</a>
            <a href="#" onclick="update()">update</a>
            <a href="#" onclick="isLeaf()">isLeaf</a>
            <a href="#" onclick="getNodes()">getNodes</a>
            <a href="#" onclick="doNode()">获取所有checkbox选中节点id</a>
        </div>
        <ul id="tt2">
        </ul>
        <div id="mm" class="easyui-menu" style="width:120px;">
            <div onclick="append()" iconcls="icon-add">
                Append
            </div>
            <div onclick="remove()" iconcls="icon-remove">
                Remove
            </div>
            <div onclick="expand()">
                Expand
            </div>
            <div onclick="collapse()">
                Collapse
            </div>
        </div>
    </body>
</html> 