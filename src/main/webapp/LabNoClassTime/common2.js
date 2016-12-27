
var content_str = '';

var N = ["","一", "二", "三", "四", "五", "六", "日"];

var ue;

var reObj = function (id) {
    return "string" == typeof id ? document.getElementById(id) : id;
}
var Bind = function(object, fun) {
    return function() {
        return fun.apply(object, arguments);
    }
}
function AutoComplete(obj,autoObj,arr){
    this.obj=reObj(obj);        //输入框
    this.autoObj=reObj(autoObj);//DIV的根节点
    this.value_arr=arr;        //不要包含重复值
    this.index=-1;          //当前选中的DIV的索引
    this.search_value="";   //保存当前搜索的字符
}
AutoComplete.prototype={
    //初始化DIV的位置
    init: function(){
        this.autoObj.style.left = this.obj.offsetLeft + "px";
        this.autoObj.style.top  = this.obj.offsetTop + this.obj.offsetHeight + "px";
        this.autoObj.style.width= this.obj.offsetWidth - 2 + "px";//减去边框的长度2px  
    },
    //删除自动完成需要的所有DIV
    deleteDIV: function(){
        while(this.autoObj.hasChildNodes()){
            this.autoObj.removeChild(this.autoObj.firstChild);
        }
        this.autoObj.className="auto_hidden";
    },
    //设置值
    setValue: function(_this){
        return function(){
            _this.obj.value=this.seq;
            _this.autoObj.className="auto_hidden";
        }      
    },
    //模拟鼠标移动至DIV时，DIV高亮
    autoOnmouseover: function(_this,_div_index){
        return function(){
            _this.index=_div_index;
            var length = _this.autoObj.children.length;
            for(var j=0;j<length;j++){
                if(j!=_this.index ){      
                    _this.autoObj.childNodes[j].className='auto_onmouseout';
                }else{
                    _this.autoObj.childNodes[j].className='auto_onmouseover';
                }
            }
        }
    },
  //更改classname
    changeClassname: function(length){
        for(var i=0;i<length;i++){
            if(i!=this.index ){      
                this.autoObj.childNodes[i].className='auto_onmouseout';
            }else{
                this.autoObj.childNodes[i].className='auto_onmouseover';
                this.obj.value=this.autoObj.childNodes[i].seq;
            }
        }
    }
    ,
    //响应键盘
    pressKey: function(event){
        var length = this.autoObj.children.length;
        //光标键"↓"
        if(event.keyCode==40){
            ++this.index;
            if(this.index>length){
                this.index=0;
            }else if(this.index==length){
                this.obj.value=this.search_value;
            }
            this.changeClassname(length);
        }
        //光标键"↑"
        else if(event.keyCode==38){
            this.index--;
            if(this.index<-1){
                this.index=length - 1;
            }else if(this.index==-1){
                this.obj.value=this.search_value;
            }
            this.changeClassname(length);
        }
        //回车键
        else if(event.keyCode==13){
            this.autoObj.className="auto_hidden";
            this.index=-1;
        }else{
            this.index=-1;
        }
    },
    //程序入口
    start: function(event){
        if(event.keyCode!=13&&event.keyCode!=38&&event.keyCode!=40){
            this.init();
            this.deleteDIV();
            this.search_value=this.obj.value;
            var valueArr=this.value_arr;
            valueArr.sort();
            if(this.obj.value.replace(/(^\s*)|(\s*$)/g,'')==""){ return; }//值为空，退出
            try{ var reg = new RegExp("(" + this.obj.value + ")","i");}
            catch (e){ return; }
            var div_index=0;//记录创建的DIV的索引
            for(var i=0;i<valueArr.length;i++){
                if(reg.test(valueArr[i])){
                    var div = document.createElement("div");
                    div.className="auto_onmouseout";
                    div.seq=valueArr[i];
                    div.onclick=this.setValue(this);
                    div.onmouseover=this.autoOnmouseover(this,div_index);
                    div.innerHTML=valueArr[i].replace(reg,"<strong>$1</strong>");//搜索到的字符粗体显示
                    this.autoObj.appendChild(div);
                    this.autoObj.className="auto_show";
                    div_index++;
                }
            }
        }
        this.pressKey(event);
        window.onresize=Bind(this,function(){this.init();});
    }
}
/**本程序**/
$(function(){
    $(".unSelected").live('hover', function(event){
        if(event.type=='mouseenter') {
            $(this).html('<i class="iconfont icon-queren2"></i> <strong>无课列表</strong>'); 
        } else {
            $(this).html('');
        }
    });
});

function chooseDate(w, d, t, room, obj)
{
    // 判断用户是否已经登录
    $(obj).attr('id', 'c-'+w+'-'+d+'-'+t);
    $.post(root+'getLesson', {
        "room" : room
    }, 
    function(res) {
        content_str = '<option value="0">请选择班级</option>';
        $.each(res, function(i, v){
            content_str += "<option value='"+v+"'>"+v+"</option>";
        }); 
        layer.open({
            type: 1,
            area: ['490px', '220px'],
            shadeClose: true,
            title: '<i class="iconfont icon-date"></i> 正在预约：<strong><code>'+room+'</code>实验室，第<code>'+w+'</code>周星期<code>'+N[d]+'</code>，第<code>'+(2*t-1)+'~'+2*t+'</code>节课</strong>',
            content: '<div class="bz-form bz-form-aligned"><fieldset><div class="bz-control-group"><label for="name">选择班级</label><select name="teachclass" id="teachclass" onchange="javascript:getLesson();">'+content_str+'</select></div><div class="bz-control-group"><label for="name">选择课程</label><select name="lessonname" id="lessonname" onchange="javascript:getProject();"><option value="0">请选择课程</option></select></div><div class="bz-control-group"><label for="name">实验项目</label><select name="projectname" id="projectname"><option value="">请选择实验项目</option></select></div><div class="bz-controls"><button class="bz-button bz-button-primary" id="doSign-btn" onclick="doSign('+w+','+d+','+t+',\''+room+'\');"><i class="iconfont icon-queren2"></i> 确认预约</button></div></fieldset></div>'
        });
    }, 'json'); 
}

function chooseAllDate(w, d, t, room, obj)
{
    // 判断用户是否已经登录
    
    $(obj).attr('id', 'c-'+w+'-'+d+'-'+t);
    $.post(root+'getAllUser', {
        "room" : room
    }, 
    function(res) {
        content_str1 = '<option value="0">请选择教师</option>';
        $.each(res, function(i, v){
            content_str1 += "<option value='"+v.uid+"'>"+v.username+" ("+(v.uid)+")</option>";
        });
        layer.open({
            type: 1,
            area: ['490px', '280px'],
            shadeClose: true,
            title: '<i class="iconfont icon-date"></i> 正在补排：<strong><code>'+room+'</code>实验室，第<code>'+w+'</code>周星期<code>'+N[d]+'</code>，第<code>'+(2*t-1)+'~'+2*t+'</code>节课</strong>',
            content: '<div class="bz-form bz-form-aligned"><fieldset><div class="bz-control-group"><label for="name">选择教师</label><select name="teacher_no" id="teacher_no" onchange="getClass(\''+$.trim(room)+'\')">'+content_str1+'</select></div><div class="bz-control-group"><label for="name">选择班级</label><select name="teachclass" id="teachclass" onchange="getRenewLesson()"><option value="0">请选择班级</option></select></div><div class="bz-control-group"><label for="name">选择课程</label><select name="lessonname" id="lessonname" onchange="getProject()"><option value="0">请选择课程</option></select></div><div class="bz-control-group"><label for="name">实验项目</label><select name="projectname" id="projectname"><option value="">请选择实验项目</option></select></div><div class="bz-controls"><button class="bz-button bz-button-primary" id="doSign-btn" onclick="doRenewSign('+w+','+d+','+t+',\''+room+'\')"><i class="iconfont icon-queren2"></i> 确认预约</button></div></fieldset></div>'
        });
    }, 'json'); 
}

function cancelDate(w, d, t, room, obj)
{
    if (uid == 0) {
        layer.msg('请先登录哦~');
        return false;
    }
    $(obj).attr('id', 'c-'+w+'-'+d+'-'+t);
    layer.open({
        type: 1,
        area: ['300px', '120px'],
        shadeClose: true, //点击遮罩关闭
        title: '<i class="iconfont icon-date"></i> 即将取消该预约，是否确定？',
        content: '\<\div style="padding:20px;"><a href="javascript:doCancel('+w+','+d+','+t+',\''+room+'\');" class="bz-button bz-button-primary left" id="cancel-btn"><i class="iconfont icon-queren2"></i> <strong>确定执行</strong></a><a href="javascript:hiddenShade();" class="bz-button bz-button-sm right"><strong>取消</strong></a>\<\/div>'
    });
}

function editNotice(id)
{
    $.post(root+'getNoticeDetail', {
        'id': id
    }, function(res) {
        if (res.id == 0) {
            var t_str = '正在新增公告';
        }else{
            var t_str = '正在编辑公告 - <strong>'+res.title+'</strong>';
        }
        layer.open({
            type: 1,
            area: ['900px', '600px'],
            shadeClose: true, //点击遮罩关闭
            title: t_str,
            content: '<form method="post" class="bz-form bz-form-stacked" style="width:880px;margin:10px"><fieldset><input type="hidden" name="id" class="bz-input-1" value="'+res.id+'"><input type="text" name="title" class="bz-input-1" value="'+res.title+'"><textarea name="contents" style="display:none"></textarea><script id="container'+id+'" name="content" type="text/plain" style="height:380px;">'+res.content+'</script><script>showEditor("'+id+'");</script></fieldset><button type="submit" class="bz-button bz-button-primary" onclick="synContent();">确定提交</button> <button type="button" onclick="hiddenShade();" class="bz-button bz-button-sm">取消</button></form>'
        });
    }, 'json');
}

function getMyRes()
{
    var myClass = $('#myClass option:selected').val();
    if (myClass == 0) {
        layer.msg('请选择任教班级！');
        return false;
    };
    $('.bz-table').remove();
    $('#outxls-btn').remove();
    $('#print-btn').remove();
    $('#query-btn').attr('disabled', 'disabled'); 
    $.post(root+'getMyClassRes', {
        'class_name': myClass
    }, function(res) {
        $('.bz-panel').after('<table id="row-res" class="bz-table"><thead><tr><th>周次</th><th>时间</th><th>课程</th><th>实验项目</th><th>实验班级</th><th>实验室</th></tr></thead>');
        $.each(res, function(i, v){
            $('.bz-table').append('<tr><td>第 <code>'+v.week+'</code> 周</td><td>'+v.time+'</td><td>'+v.classInfo.lesson_name+'</td><td>'+v.projectname+'</td><td>'+v.classInfo.class_name+'</td><td>'+v.roomName+'</td></tr>');
        }); 
        $('tr:last').after('</tbody></table>');
        $('#query-btn').removeAttr('disabled');
        xlsOUT('row-res', uid+'-'+$('#myClass option:selected').text()+'的课程安排.xls');
    }, 'json');
}

function getQueryRes()
{
    var q_term = $('#q_term option:selected').val();
    var q_week = $('#q_week option:selected').val();
    var tid = $('#tid option:selected').val();
    var rid = $('#room option:selected').val();
    var t_str = '';

    if (q_term == 0) {
        layer.msg('请选择学期！');
        return false;
    }

    t_str = $('#q_term option:selected').text();

    if (q_week > 0) {
        t_str += ' - ' + $('#q_week option:selected').text();
    }
    if (tid > 0) {
        t_str += ' - ' + $('#tid option:selected').text();
    }
    if (rid > 0) {
        t_str += ' - ' + $('#room option:selected').text();
    }

    $('.bz-table').remove();
    $('#outxls-btn').remove();
    $('#print-btn').remove();
    $('#query-btn').attr('disabled', 'disabled');
    $('#query-btn').html('<i class="iconfont icon-search"></i> 正在查询...');
    $.post(root+'getQueryRes', {
        'q_term': q_term,
        'q_week': q_week,
        'tid': tid,
        'rid': rid
    }, function(res) {
        if (q_week > 0) {
            $('.bz-panel').after('<table id="row-res" class="bz-table"><thead><tr><td colspan="6"><b>'+t_str+'的课表</b></td></tr><tr><th>时间</th><th>教师</th><th>课程</th><th>实验项目</th><th>实验班级</th><th>实验室</th></tr></thead>');
            $.each(res, function(i, v){
                $('.bz-table').append('<tr><td>'+v.time+'</td><td>'+v.teacher+'</td><td>'+v.classInfo.lesson_name+'</td><td>'+v.projectname+'</td><td>'+v.classInfo.class_name+'</td><td>'+v.roomName+'</td></tr>');
            }); 
        }else{
            $('.bz-panel').after('<table id="row-res" class="bz-table"><thead><tr><td colspan="7"><b>'+t_str+'的课表</b></td></tr><tr><th>周次</th><th>时间</th><th>教师</th><th>课程</th><th>实验项目</th><th>实验班级</th><th>实验室</th></tr></thead>');
            $.each(res, function(i, v){
                $('.bz-table').append('<tr><td>第 '+v.week+' 周</td><td>'+v.time+'</td><td>'+v.teacher+'</td><td>'+v.classInfo.lesson_name+'</td><td>'+v.projectname+'</td><td>'+v.classInfo.class_name+'</td><td>'+v.roomName+'</td></tr>');
            }); 
        }
        
        $('tr:last').after('</tbody></table>');
        $('#query-btn').removeAttr('disabled');
        $('#query-btn').html('<i class="iconfont icon-search"></i> 查询');
        xlsOUT('row-res', t_str+' 的课程安排结果.xls');
    }, 'json');
}

function getQueryLessonCount()
{
    var belong = $('#belong option:selected').val();
    var type = $('#type option:selected').val();
    var tid = $('#tid option:selected').val();

    var t_str = '';

    if (belong == 0) {
        layer.msg('请选择系别！');
        return false;
    }

    t_str = $('#belong option:selected').text();

    if (type != 0) {
        t_str += ' - ' + $('#type option:selected').text();
    }
    if (tid > 0) {
        t_str += ' - ' + $('#tid option:selected').text();
    }

    $('.bz-table').remove();
    $('#outxls-btn').remove();
    $('#print-btn').remove();
    $('#query-btn').attr('disabled', 'disabled');
    $('#query-btn').html('<i class="iconfont icon-search"></i> 正在查询...');
    $.post(root+'admin/lessonCount', {
        'belong': belong,
        'type': type,
        'tid': tid
    }, function(res) {
        $('.bz-panel').after('<table id="row-res" class="bz-table"><thead><tr><th>课程名称</th><th>班级名称</th><th>实验类别</th><th>系别</th><th>选课人数</th><th>实验计划学时</th><th>实验人时数</th></tr></thead>');
        $.each(res, function(i, v){
            $('.bz-table').append('<tr><td>'+v.lesson_name+'</td><td>'+v.class_name+'</td><td>'+v.type+'</td><td>'+v.belong+'</td><td>'+v.count+'</td><td>'+v.lesson_time+'</td><td>'+v.allCount+'</td></tr>');
        });
        $('tr:last').after('</tbody></table>');
        $('#query-btn').removeAttr('disabled');
        $('#query-btn').html('<i class="iconfont icon-search"></i> 查询');
        xlsOUT('row-res', t_str+' 的机时统计.xls');
    }, 'json');
}

function getClass(room)
{
    var teacher_no = $('#teacher_no option:selected').val();
    $.post(root+'getTeacherClass', {
        'teacher_no': teacher_no,
        'room' : room
    }, function(res) {
        $('#teachclass').html('<option value="0">请选择班级</option>');
        $.each(res, function(i, v){
            $('#teachclass').append("<option value='"+v+"'>"+v+"</option>");
        }); 
    }, 'json');
}

function getLesson()
{
    var teachclass = $('#teachclass option:selected').val();
    $.post(root+'getClassLesson', {
        'class_name': teachclass
    }, function(res) {
        $('#lessonname').html('<option value="0">请选择课程</option>');
        $.each(res, function(i, v){
            $('#lessonname').append("<option value='"+v['lesson_no']+"'>"+v['lesson_name']+"</option>");
        }); 
    }, 'json');
}

function getRenewLesson()
{
    var teachclass = $('#teachclass option:selected').val();
    var teacher_no = $('#teacher_no option:selected').val();
    $.post(root+'getRenewClassLesson', {
        'class_name': teachclass,
        'teacher_no' : teacher_no
    }, function(res) {
        $('#lessonname').html('<option value="0">请选择课程</option>');
        $.each(res, function(i, v){
            $('#lessonname').append("<option value='"+v['lesson_no']+"'>"+v['lesson_name']+"</option>");
        }); 
    }, 'json');
}

function getProject()
{
    var lesson = $('#lessonname option:selected').val();
    $.post(root+'getProject', {
        'lesson_no': lesson
    }, function(res) {
        $('#projectname').html('<option value="">请选择实验项目</option>');
        $.each(res, function(i, v){
            $('#projectname').append("<option value='"+v+"'>"+v+"</option>");
        }); 
    }, 'json');
}

function doSign(w, d, t, room)
{
    var class_name = $('#teachclass option:selected').val();
    var lesson_no = $('#lessonname option:selected').val();
    var projectname = $('#projectname option:selected').val();

    if (class_name == 0) {layer.msg('请选择班级!');return false;}
    if (lesson_no == 0) {layer.msg('请选择课程!');return false;}

    $('#doSign-btn').attr('disabled', 'disabled');
    $('#doSign-btn').html('<i class="iconfont icon-queren2"></i> 正在预约中...');
    setTimeout(function () { 
        $.post(root+'doSign', {
            'week' : w,
            'x' : d,
            'y' : t,
            'room' : room,
            'projectname' : projectname,
            'class_name' : class_name,
            'lesson_no' : lesson_no
        }, function(res) {
            if (res.status > 0) {
                $('#c-'+w+'-'+d+'-'+t).attr('class', 'selected');

                $('#c-'+w+'-'+d+'-'+t).html(res.info);

                $('#c-'+w+'-'+d+'-'+t).attr('onclick', 'cancelDate('+w+', '+d+', '+t+', "'+room+'", this);');

                $('#doSign-btn').removeAttr('disabled');

                $('#doSign-btn').html('<i class="iconfont icon-queren2"></i> 确认预约');
                
                $('.layui-layer-shade').click();

                layer.msg('预约成功！');
            } else {
                $('#doSign-btn').removeAttr('disabled');

                $('#doSign-btn').html('<i class="iconfont icon-queren2"></i> 确认预约');

                layer.msg(res.message);
            }
        }, 'json');
    }, 800);
}

// 实验补排预约
function doRenewSign(w, d, t, room)
{
    var teacher_no = $('#teacher_no option:selected').val();
    var class_name = $('#teachclass option:selected').val();
    var lesson_no = $('#lessonname option:selected').val();
    var projectname = $('#projectname option:selected').val();

    if (teacher_no == 0) {layer.msg('请选择教师!');return false;}
    if (class_name == 0) {layer.msg('请选择班级!');return false;}
    if (lesson_no == 0) {layer.msg('请选择课程!');return false;}

    $('#doSign-btn').attr('disabled', 'disabled');
    $('#doSign-btn').html('<i class="iconfont icon-queren2"></i> 正在补排中...');
    setTimeout(function () { 
        $.post(root+'doRenewSign', {
            'teacher_no' : teacher_no,
            'week' : w,
            'x' : d,
            'y' : t,
            'room' : room,
            'projectname' : projectname,
            'class_name' : class_name,
            'lesson_no' : lesson_no
        }, function(res) {
            if (res.status > 0) {
                $('#c-'+w+'-'+d+'-'+t).attr('class', 'selected');

                $('#c-'+w+'-'+d+'-'+t).html(res.info);

                $('#c-'+w+'-'+d+'-'+t).attr('onclick', 'cancelDate('+w+', '+d+', '+t+', "'+room+'", this);');

                $('#doSign-btn').removeAttr('disabled');

                $('#doSign-btn').html('<i class="iconfont icon-queren2"></i> 确认预约');
                
                $('.layui-layer-shade').click();

                layer.msg('预约成功！');
            } else {
                $('#doSign-btn').removeAttr('disabled');

                $('#doSign-btn').html('<i class="iconfont icon-queren2"></i> 确认预约');

                layer.msg(res.message);
            }
        }, 'json');
    }, 800);
}

function doCancel(w, d, t, room)
{
    $('#cancel-btn').attr('disabled', 'disabled');
    $('#cancel-btn').html('<i class="iconfont icon-queren2"></i> 正在取消中...');
    setTimeout(function () { 
        $.post(root+'doCancel', {
            'week' : w,
            'x' : d,
            'y' : t,
            'room' : room
        }, function(res) {
            $('#cancel-btn').removeAttr('disabled');
            $('#cancel-btn').html('<i class="iconfont icon-queren2"></i> 确认执行');
            $('.layui-layer-shade').click();
            if (res.status > 0) {
                window.location.reload();
                $('#c-'+w+'-'+d+'-'+t).attr('class', 'unSelected');

                $('#c-'+w+'-'+d+'-'+t).attr('onclick', 'chooseDate('+w+', '+d+', '+t+', "'+room+', this");');

                $('#c-'+w+'-'+d+'-'+t).html('');

                layer.msg('取消预约成功~');
            } else {
                layer.msg('取消预约失败，你没有权限执行此操作！');
            }
        }, 'json');
    }, 500);
}

function checkroom() {
    var room = document.getElementById("room").value;
    if (room == 0)
    {
        alert("必须选择实验房间！");
        return false
    }
}

function getroom() {
    var tid = $('#tid option:selected').val();
    $.post(root+'getRoom', {
        'tid': tid
    }, function(res) {
        $('#room').html('<option value="0">请选择实验室</option>');
        $.each(res, function(i, v){
            $('#room').append("<option value='"+v['rid']+"'>"+v['room']+"</option>");
        }); 
    }, 'json');
}

function goSearch()
{
    var tid = $('#tid option:selected').val();
    var rid = $('#room option:selected').val();

    if (tid == 0) {
        layer.msg('实验模块未选择！');
        return false;
    }

    if (rid == 0) {
        layer.msg('实验室未选择！');
        return false;
    }

    window.location.href = root+"m/"+tid+"/"+rid+"/";
}

function goRenewSearch()
{
    var tid = $('#tid option:selected').val();
    var rid = $('#room option:selected').val();

    if (tid == 0) {
        layer.msg('实验模块未选择！');
        return false;
    }

    if (rid == 0) {
        layer.msg('实验室未选择！');
        return false;
    }

    window.location.href = root+"admin/renew/"+tid+"/"+rid+"/";
}

function editInformation(class_id)
{
    layer.open({
        type: 1,
        area: ['380px', '450px'],
        shadeClose: true,
        title: '编辑班级信息',
        content: '<div class="bz-form bz-form-aligned"><form action="'+root+'admin/data/cclab_information" method="post"><fieldset><div class="bz-control-group"><label for="name">class_id</label><input type="text" name="class_id" value="'+$('#inf-'+class_id+' .class_id').text()+'" readonly></div><div class="bz-control-group"><label for="name">班级名</label><input type="text" name="class_name" value="'+$('#inf-'+class_id+' .class_name').text()+'"></div><div class="bz-control-group"><label for="name">课程号</label><input type="text" name="lesson_no" value="'+$('#inf-'+class_id+' .lesson_no').text()+'"></div><div class="bz-control-group"><label for="name">课程名</label><input type="text" name="lesson_name" value="'+$('#inf-'+class_id+' .lesson_name').text()+'"></div><div class="bz-control-group"><label for="name">课时数</label><input type="text" name="lesson_time" value="'+$('#inf-'+class_id+' .lesson_time').text()+'"></div><div class="bz-control-group"><label for="name">模块名</label><input type="text" name="device_name" value="'+$('#inf-'+class_id+' .device_name').text()+'"></div><div class="bz-control-group"><label for="name">教师号</label><input type="text" name="teacher_no" value="'+$('#inf-'+class_id+' .teacher_no').text()+'"></div><div class="bz-control-group"><label for="name">教师名</label><input type="text" name="teacher_name"  value="'+$('#inf-'+class_id+' .teacher_name').text()+'"></div><div class="bz-control-group"><label for="name">学期</label><input type="text" name="term" value="'+$('#inf-'+class_id+' .term').text()+'"></div><div class="bz-controls"><button type="submit" class="bz-button bz-button-primary"><i class="iconfont icon-queren2"></i> 确认编辑</button></div></fieldset></form></div>'
    });
}

function editProject(id)
{
    layer.open({
        type: 1,
        area: ['380px', '280px'],
        shadeClose: true,
        title: '编辑课程项目信息',
        content: '<div class="bz-form bz-form-aligned"><form action="'+root+'admin/data/cclab_project" method="post"><fieldset><div class="bz-control-group"><label for="name">id</label><input type="text" name="id" value="'+$('#pro-'+id+' .id').text()+'" readonly></div><div class="bz-control-group"><label for="name">课程号</label><input type="text" name="lesson_no" value="'+$('#pro-'+id+' .lesson_no').text()+'"></div><div class="bz-control-group"><label for="name">课程名称</label><input type="text" name="lesson_name" value="'+$('#pro-'+id+' .lesson_name').text()+'"></div><div class="bz-control-group"><label for="name">项目名称</label><input type="text" name="projectname" value="'+$('#pro-'+id+' .projectname').text()+'"></div><div class="bz-controls"><button type="submit" class="bz-button bz-button-primary"><i class="iconfont icon-queren2"></i> 确认编辑</button></div></fieldset></form></div>'
    });
}

function editUser(uid)
{
    layer.open({
        type: 1,
        area: ['380px', '320px'],
        shadeClose: true,
        title: '编辑教师信息',
        content: '<div class="bz-form bz-form-aligned"><form action="'+root+'admin/data/cclab_user" method="post"><fieldset><div class="bz-control-group"><label for="name">教师工号</label><input type="text" name="uid" value="'+$('#user-'+uid+' .uid').text()+'" readonly></div><div class="bz-control-group"><label for="name">教师姓名</label><input type="text" name="username" value="'+$('#user-'+uid+' .username').text()+'"></div><div class="bz-control-group"><label for="name">邮箱</label><input type="text" name="email" value="'+$('#user-'+uid+' .email').text()+'"></div><div class="bz-control-group"><label for="name">用户组</label><input type="text" name="groupid" value="'+$('#user-'+uid+' .groupid').text()+'"></div><div class="bz-control-group"><label for="name">密码</label><input type="password" name="password" placeholder="不修改请留空"></div><div class="bz-controls"><button type="submit" class="bz-button bz-button-primary"><i class="iconfont icon-queren2"></i> 确认编辑</button></div></fieldset></form></div>'
    });
}

function editDevice(id)
{
    layer.open({
        type: 1,
        area: ['400px', '410px'],
        shadeClose: false,
        title: '编辑设备信息',
        content: '<div class="bz-form bz-form-aligned"><form action="'+root+'admin/data/cclab_device" method="post"><fieldset><input type="hidden" name="id" value="'+$('#device-'+id+' .id').val()+'"><div class="bz-control-group"><label>设备编号</label><input type="text" name="sid" value="'+$('#device-'+id+' .sid').text()+'"></div><div class="bz-control-group"><label>设备名称</label><input type="text" name="name" value="'+$('#device-'+id+' .name').text()+'"></div><div class="bz-control-group"><label>设备型号</label><input type="text" name="model" value="'+$('#device-'+id+' .model').text()+'"></div><div class="bz-control-group"><label>数量</label><input type="text" name="num" value="'+$('#device-'+id+' .num').text()+'"></div><div class="bz-control-group"><label>状态</label><input type="text" name="status" value="'+$('#device-'+id+' .status').text()+'"></div><div class="bz-control-group"><label>负责人</label><input type="text" name="person" value="'+$('#device-'+id+' .person').text()+'"></div><div class="bz-control-group"><label>位置</label><input type="text" name="location" value="'+$('#device-'+id+' .location').text()+'"></div><div class="bz-control-group"><label>时间</label><input type="text" name="time" value="'+$('#device-'+id+' .time').text()+'"></div><div class="bz-controls"><button type="submit" class="bz-button bz-button-primary"><i class="iconfont icon-queren2"></i> 确认编辑</button></div></fieldset></form></div>'
    });
}

function toggleFunc()
{
    $('#choose-tip-info').toggle('500');
}

function hiddenShade()
{
    $('.layui-layer-shade').click();
}

function myPrint(){
    window.print();
}

var xlsOUT = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><meta http-equiv="Content-Type" charset=utf-8"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table border="1">{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet:'Worksheet', table: table.innerHTML}
    $('#row-res').after('<a href="javascript:myPrint();" class="bz-button bz-button-sm right" id="print-btn">打印该表格</a><a href="'+uri + base64(format(template, ctx))+'" download="'+name+'" class="bz-button bz-button-sm right" id="outxls-btn">导出为xls文件<em>（推荐使用chrome浏览器下载）</em></a><div class="clear"></div>');
  }
})();
