/*基本消息*/
function message(title,message){
	$.messager.alert(title,message);
}
/*错误消息*/
function error(title,message){
	$.messager.alert(title,message,'error');
}
/*错误消息*/
function info(title,message){
	$.messager.alert(title,message,'info');
}
/*问题消息*/
function question(title,message){
	$.messager.alert(title,message,'question');
}
/*警告消息*/
function warning(title,message){
	$.messager.alert(title,message,'warning');
}
