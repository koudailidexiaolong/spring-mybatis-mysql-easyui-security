	/**
	 * 首页时间的处理js
	 * @return
	 */
	function formatShowTimer(){
			var date = new Date();
			var year = date.getFullYear();
			var month = date.getMonth()+1;
			var day = date.getDate();
			var hours = date.getHours();
			var minutes = date.getMinutes();
			var seconds = date.getSeconds()
			var week = date.getDay();
			var time = year+"年"+month+"月"+day+"日     "+hours+"时"+minutes+"分"+seconds+"秒    "+weekDay(week);
			return time;
		}
	function timer(){
			var time = formatShowTimer();
			var show_time = window.document.getElementById("show_time");
			show_time.innerHTML = time;
			
		}
	/*获取日期*/
	function weekDay(day){
			switch(day){
		   		case 0:
			   		return "星期日";
		     		break;
		   		case 1:
			   		return "星期一";
		     		break;
		   		case 2:
			   		return "星期二";
		     		break;
		   		case 3:
			   		return "星期三";
		     		break;
		   		case 4:
			   		return "星期四";
		     		break;
		   		case 5:
			   		return "星期五";
		     		break;
		   		case 6:
			   		return "星期六";
		     		break;
		   	    default:
			   	    return "";
		   }
					
		}