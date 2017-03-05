var app = angular.module("goodsApp",[]);
app.controller("goodsController",function($scope,$http){
	$scope.initView = function($id,$uid){
		console.log($id);
		console.log($uid);
	$http({
		method:'post',
		url:apiRoot,
		data:{
			action:'Trade.goodInfo',
			id:$id,
			uid:$uid,
		}
	}).then(function successCallback(response){
		console.log(JSON.stringify(response));         
		$scope.goodsData = response.data.data//标题下的数据
	},function errorfunction(e){ 
		console.log(JSON.stringify(e));
	})	 
}  
	
	$scope.shopping=function(goodid)//confirm_order.html
	{
		plus.storage.setItem('goodId',goodid+'');
		plus.nativeUI.showWaiting('加载中...');
		plus.webview.create('confirm_order.html', 'confirm_order.html').show('pop-in');
		plus.nativeUI.closeWaiting();
	}
	$scope.info = function(id)
	{
		plus.nativeUI.showWaiting('加载中......');
		plus.storage.setItem('goodsId',id+'');
		plus.webview.create('goods_detail.html','goods_detail.html').show('pop-in');
		plus.nativeUI.closeWaiting();
	}
})


document.addEventListener("plusready",function(){
	appElement=document.querySelector('[ng-controller=goodsController]');
	$scope= angular.element(appElement).scope();
	id = plus.storage.getItem('goodsId');
	uid = plus.storage.getItem('uid');
	$scope.initView(id,uid);
	$scope.$apply();
})