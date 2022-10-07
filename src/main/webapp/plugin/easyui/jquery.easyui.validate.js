/* 
 * author:julong 
 * date :20180606
 * length[1,100] 区间长度
 * minLength[6] 最小长度
 * maxLength[110] 最大长度
 * email 邮箱地址
 * Chinese 汉字
 * IDCARD 身份证号码
 * loginAccount 登录账号
 * phone 手机号码
 * telephone 座机号码
 * QQ QQ 登录账号
 * CN_EN_NO 中文数字字母   
 * EN_NO 数字和字母
 * EN 英文
 * password 密码 
 * postCode 邮政编码
 * equals 对比两次输入的密码
 * domain 域名
 * InternetURL 网址
 * age 年龄
 * integer 整数
 * double 小数 
 * int_double 小数和整数
 * money 钱
 * space 空格
 * IP IP地址
 * htmlTag html标签 
 * OPTION select框
 * */
$.extend($.fn.validatebox.defaults.rules, {
	length: {//区间长度
        validator: function(value, param){
        	var reg = /^[^\x00-\xff]$/;//包括汉字在内，可以用来计算字符串的长度(一个双字节字符长度计2，ASCII字符计1)
        	var length = 0;
        	for(var i=0;i<value.length;i++){
        		if(reg.test(value[i])){
        			length = length +2;
        		}else{
        			length = length +1;
        		}
        	}
        	if(param[0]<= length && length <= param[1]){
        		return true ;
        	}
        	return false;
        },
        message: '输入字符不能少于{0}且不能超过{1}'
    },
    minLength: {//最小长度
        validator: function(value, param){
        	var reg = /^[^\x00-\xff]$/;//包括汉字在内，可以用来计算字符串的长度(一个双字节字符长度计2，ASCII字符计1)
        	var length = 0;
        	for(var i=0;i<value.length;i++){
        		if(reg.test(value[i])){
        			length = length +2;
        		}else{
        			length = length +1;
        		}
        	}
            return length >= param[0];
        },
        message: '输入字符不能少于{0}'
    },
    maxLength: {//最大长度
        validator: function(value, param){
        	var reg = /^[^\x00-\xff]$/;//包括汉字在内，可以用来计算字符串的长度(一个双字节字符长度计2，ASCII字符计1)
        	var length = 0;
        	for(var i=0;i<value.length;i++){
        		if(reg.test(value[i])){
        			length = length +2;
        		}else{
        			length = length +1;
        		}
        	}
            return length <= param[0];
        },
        message: '输入字符不能超过{0}'
    },
	email: {//邮箱
	    validator: function(value, param){
	    	var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	        return reg.test(value);
	    },
	    message: '请输入正确的邮箱地址！'
	},
	Chinese : {//汉字
	    validator: function(value, param){
	    	var reg = /^[\u4e00-\u9fa5]{0,}$/;
	        return reg.test(value);
	    },
	    message: '请输入正确的邮箱地址！'
	},
	IDCARD: {//身份证号码
	    validator: function(value, param){
	    	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	    	if(!reg.test(value)){
		    	return false;
		    } 
		    var birthday = value.substr(6,4)+"-"+Number(value.substr(10,2))+"-"+Number(value.substr(12,2));   
		    var date = new Date(birthday.replace(/-/g,"/")) ;  
		    if(birthday != (date.getFullYear()+"-"+ (date.getMonth()+1) + "-" + date.getDate())){
		    	return false;   
		    }
		    var sum = 0;
		    for(var i = 17;i>=0;i --){
		    	sum += (Math.pow(2,i) % 11) * parseInt(value.charAt(17 - i),11) ;  
		    }
		    if((sum%11) != 1){
		    	return false;
		    }
	    	return true;
	    },
	    message: '请输入正确的身份证号码！'
	},
	loginAccount: {//登录账号
	    validator: function(value, param){
	    	var reg = /^[a-zA-Z0-9][a-zA-Z0-9_]{3,15}$/;
	        return reg.test(value);
	    },
	    message: '请输入正确的登录账户（允许5-16字节，允许字母数字下划线）！'
	},
	phone: {//手机号码
	    validator: function(value, param){
	    	var reg = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
	        return reg.test(value);
	    },
	    message: '请输入正确的手机号码！'
	},
	telephone: {//座机号码
	    validator: function(value, param){
	    	var reg = /^(\d{3,4}-)|(\d{3.4}-)?\d{7,8}$/;
	        return reg.test(value);
	    },
	    message: '请输入正确的座机号码！'
	},
	QQ: {//QQ登录账号
	    validator: function(value, param){
	    	var reg = /^[1-9][0-9]{4,}$/;
	        return reg.test(value);
	    },
	    message: '请输入正确的QQ号码！'
	},
	CN_EN_NO: {//中文数字字母   
	    validator: function(value, param){
	    	var reg = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/;
	        return reg.test(value);
	    },
	    message: '允许输入汉字、字母、数字、下划线！'
	},
	EN_NO: {//数字和字母
	    validator: function(value, param){
	    	var reg = /^[A-Za-z0-9]+$/;
	        return reg.test(value);
	    },
	    message: '允许输入数字、字母！'
	},
	EN: {//英文
	    validator: function(value, param){
	    	var reg = /^[A-Za-z]+$/;
	        return reg.test(value);
	    },
	    message: '允许输入英文字母！'
	},
	password: {//密码
	    validator: function(value, param){
	    	var reg = /^[a-zA-Z0-9]\w{5,17}$/;
	        return reg.test(value);
	    },
	    message: '请输入正确的密码！（长度在6~18之间，只能包含字母、数字和下划线）'
	},
	postCode: {//邮政编码
	    validator: function(value, param){
	    	var reg = /^[1-9]\d{5}(?!\d)$/;
	        return reg.test(value);
	    },
	    message: '请输入正确的邮政编码！'
	},    
	equals: {//对比两次输入的密码
        validator: function(value,param){
            return value == $(param[0]).val();
        },
        message: '两次输入的密码不一致！'
    },
    domain: {//域名
    	validator: function(value, param){
	    	var reg = new RegExp("^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?$");
	        return reg.test(value);
	    },
	    message: '请输入正确的域名！'
    },
    InternetURL: {//网址
    	validator: function(value, param){
	    	var reg = /^((https|http|ftp|rtsp|mms)?:\/\/)[^\s]+$/;
	        return reg.test(value);
	    },
	    message: '请输入正确的网址！'
    },
    age: {//年龄
    	validator: function(value, param){
	    	var reg = /(^[1-9]{1,2}$)|(^[1][0-3][0-9]$)/;
	        return reg.test(value);
	    },
	    message: '请输入正确的年龄（长度在1-139之间）！'
    },
    integer: {//整数
    	validator: function(value, param){
	    	var reg = /^[1-9]\d*$/;
	        return reg.test(value);
	    },
	    message: '允许输入字符为正整数！'
    },
    double: {//小数
    	validator: function(value, param){
	    	var reg = /^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$/;
	        return reg.test(value);
	    },
	    message: '允许输入字符为小数！'
    },
    int_double: {//小数和整数
    	validator: function(value, param){
    		var reg = /(^[1-9]\d*$)|(^[0-9]+(\.[0-9]{2})?$)/;
    		return reg.test(value);
    	},
    	message: '允许输入字符为小数或整数(小数保留两位)！'
    },
    money: {//钱
    	validator: function(value, param){
    		var reg = /^(\-|\+)?\d+(\.\d+)?$/;
    		return reg.test(value);
    	},
    	message: '允许输入正数、负数、小数！'
    },
    space: {//空格
    	validator: function(value, param){
    		var reg = /^\s+$/;
    		return reg.test(value);
    	},
    	message: '不允许输入字符中包含空格！'
    },
    IP: {//IP地址
    	validator: function(value, param){
    		var reg = /^[1-9]{1,3}\.[0-9]{1,3}\.([0-9]{1,3})\.[0-9]{1,3}$/;
    		return reg.test(value);
    	},
    	message: '请输入正确的IP地址！'
    },
    htmlTag: {//html标签
    	validator: function(value, param){
    		var reg = /^[<?>?]$/;
    		if(reg.test(value)){
    			return false;
    		}
    		return true;
    	},
    	message: '您的输入含有非法字符！'
    },
    OPTION: {//select选中
    	validator: function(value, param){
    		// $(param[0]).find("option:contains('"+value+"')").val();
    		if("" == value || "请选择" == value ||"-1" == value ){
    			return false;
    		}else{
    			return true;
    		}
    	},
    	message: '请选择下拉框的值！'
    },
});