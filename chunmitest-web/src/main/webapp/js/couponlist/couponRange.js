function chooseProduct(couponId) {
	$("#searchGoodsInfo").find("input[name='couponId']").val(couponId);
	doAjax(1, 10);
	$('#chooseGoods').modal('show');
}

function doAjax(pageNo, pageSize) {
	$("#pageNo").val(pageNo);
	$("#pageSize").val(pageSize);
	$("#chooseAllPro").attr("checked", false);

	$.ajax({
		url : "queryProductListWithoutCoupon.htm",
		data : $("#searchGoodsInfo").serialize(),
		async : true,
		success : function(data) {
			if(data.list != null && data.list != undefined){
				var list = data.list;
				var productListHtml = "";
				for (var i = 0; i < list.length; i++) {
					productListHtml = productListHtml + "<tr>" + "<td class='tc'><input type='checkbox' class='productId' name='productId'";

					var pro = document.getElementsByName("goodsIdP");
					for (var j = 0; j < pro.length; j++) {
						if (pro[j].value == list[i].goodsInfoId) {
							productListHtml = productListHtml + ' checked="checked" ';
						}
					}
					productListHtml = productListHtml + " value='" + list[i].goodsInfoId + "'/></td>";
					productListHtml += '<td><img src="' + list[i].goodsInfoImgId + '" class="goodsImg"  width="50" height="50"/></td>';
					productListHtml += "<td  class='goodsTag' >";
					if (list[i].specVo.length > 0) {
						for (var k = 0; k < list[i].specVo.length; k++) {
							productListHtml = productListHtml + list[i].specVo[k].spec.specName;
							productListHtml = productListHtml
									+ ":"
									+ list[i].specVo[k].goodsSpecDetail.specDetailName
									+ "<br/>";
						}
					}
					productListHtml = productListHtml + "</td>"
							+ "<td class='goodsNo'>"
							+ list[i].goodsInfoItemNo + "</td>"
							+ "<td  class='goodsName' title='"
							+ list[i].goodsInfoName + "' >"
							+ list[i].goodsInfoName + "</td>";
				}
				$("#productAddList tbody").html(productListHtml);
				$("input[type=button]").button();
				/* 添加页脚 */
				$("#productAddList .meneame").html("");
				var foot = "";
				var i = 1;
				for (var l = data.startNo; l <= data.endNo; l++) {
					if ((i - 1 + data.startNo) == data.pageNo) {
						foot = foot + "<span class='current'> " + (i - 1 + data.startNo) + "</span>";
					} else {
						foot = foot + "<a onclick='doAjax(" + (i - 1 + data.startNo) + ","
								+ (data.pageSize)
								+ ")' href='javascript:void(0)'>"
								+ (i - 1 + data.startNo) + "</a>";
					}
					i++;
				}
				foot = foot + "";
				/* 添加tfoot分页信息 */
				$("#productAddList_table_foot .meneame").html(foot);
			}
			
		}
	});
}

/* 添加商品时全选 */
function chooseAllPro(obj) {
	if (obj.checked) {
		$("input[name='productId']").each(function() {
			this.checked = true;
		});
	} else {
		$("input[name='productId']").each(function() {
			this.checked = false;
		});
	}
}

/*******************************************************************************
 * 保存
 */
function saveCouponRange() {
	var arr = new Array();
	$("input[name='productId']").each(function() {
		if (this.checked) {
			arr.push($(this).val())
		}
	});
	console.info(arr);
	if (arr.length <= 0) {
		showTipAlert("至少选择一个货品");
	}

	var couponId = $("#searchGoodsInfo").find("input[name='couponId']").val();
	var token = $("#chooseGoods").find("input[name='token']").val();
	$.get('addCouponRange.htm?CSRFToken=' + token, {
		"productIds" : arr,
		"couponId" : couponId
	}, function(data){
		location.reload();
	});
	
}

/*******************************************************************************
 * 
 */
function deleteCouponRange(couponRangeId, couponId) {
	var token = $("#chooseGoods").find("input[name='token']").val();
	showDeleteOneConfirmAlert("delCouponRange.htm?couponRangeId="
			+ couponRangeId + "&couponId=" + couponId + '&CSRFToken='
			+ token);
}
