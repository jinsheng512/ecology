var projectOrder = ['格力海岸', '格力广场', '格力香樟', '平沙九号花园', '平沙九号'];

var toMoney = function(num) {
	num = isNaN(num) ? 0 : num * 1;
	var lessZero = num < 0;
	var float = (num - parseInt(num)).toFixed(2).substring(1, 4);	// 取小数位
	var integer = Math.abs(parseInt(num)) + '';	// 取整数位
	var money = '', split = '';
	while (integer.length > 3) {
		split = money.length > 0 ? ',' : '';
		money = (integer.substring(integer.length - 3, integer.length)) + split + money;
		integer = integer.substring(0, integer.length - 3);
	}
	split = money.length > 0 ? ',' : '';
	money = integer + split + money + float;
	return (lessZero ? '-' : '') + money;
};

var formatNumber = function(num, n) {
	if (num == undefined || isNaN(num) || num == 0) {
		return '&nbsp;';
	}
	return (num * 1).toFixed(n);
};