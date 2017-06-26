<jsp:include page="header.jsp"></jsp:include>

	<div role="main" class="container theme-showcase" ng-app="angularTable">
      <div class="" style="margin-top:90px;">
        <div class="col-lg-8">
			<div class="page-header">
				
			</div>
			<div class="bs-component" ng-controller="listdata">
				
				<form class="form-inline">
					<div class="form-group">
						<label >Search</label>
						<input type="text" ng-model="search" class="form-control" placeholder="Search">
					</div>
				</form>
				<form id="cartform">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th ng-click="sort('post_id')">Book Name
								<span class="glyphicon sort-icon" ng-show="sortKey=='Book Name'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('user_id')">Year
								<span class="glyphicon sort-icon" ng-show="sortKey=='Year'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('category_id')">Publisher
								<span class="glyphicon sort-icon" ng-show="sortKey=='Publisher'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
							<th ng-click="sort('book_name')">Condition
								<span class="glyphicon sort-icon" ng-show="sortKey=='Condition'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr dir-paginate="post in posts|orderBy:sortKey:reverse|filter:search|itemsPerPage:5">
							<td>{{post.book_name}}</td>
							<td>{{post.year}}</td>
							<td>{{post.publisher_name}}</td>
							<td>{{post.condition}}</td>
							<td><input type="number" class="form-control" id="qty_{{post.post_id}}"  style="width: 70px" placeholder="Qty" required/></td>
							<td><button class="btn btn-primary" id="addtocart_{{post.post_id}}">Add to cart</button></td>
						</tr>
					</tbody>
				</table> 
				</form>
				<dir-pagination-controls
					max-size="5"
					direction-links="true"
					boundary-links="true" >
				</dir-pagination-controls>
			</div>
			  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
          <p>Some text in the modal.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
					</div>
		
      </div>
    </div>
   
<script src="lib/dirPagination.js"></script>
<script src="app/app.js"></script>
<script type="text/javascript" src="js/jquery.validate-1.14.0.min.js" /></script>
<script type="text/javascript" src="js/jquery-validate.bootstrap-tooltip.js" /></script>
<script>
$(document).ready(function(){
	$('#cartform').validate();
	$('body').on('click','[id^=addtocart]',function(){
		postid = $(this).attr("id").split("_")[1];
		 
		if($('#qty_'+postid).val() == ""){
			$('#qty_'+postid).valid();
			return false;
	    }else{
	    	qty = $('#qty_'+postid).val();
	    	$.ajax({
				url : 'CartController.do',
				type: 'post',
				data : {
					cmd : "addItem",
					post_id : postid,
					quantity : qty
				},
				success : function(responseText) {console.log(responseText);
					if(responseText == 'success'){
						data = { "item_name": "Product", "amount": 5.00, "currency_code": "USD" };
						paypal.minicart.cart.add(data);
						paypal.minicart.view.show();	
						
					}else{
						
					}
				}
			});
	    }
	});
});
</script>
<jsp:include page="footer.jsp"></jsp:include>