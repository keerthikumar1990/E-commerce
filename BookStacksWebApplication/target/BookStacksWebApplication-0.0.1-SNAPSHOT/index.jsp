<jsp:include page="header.jsp"></jsp:include>
<style>
.item img{
    width: 675px;
    height: 400px;
}
</style>
		

    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
        <li data-target="#myCarousel" data-slide-to="3"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <div class="item active">
         
		  <img class="first-slide" src="images/library.jpg" alt="First slide">

        </div>
        <div class="item">

          <img class="second-slide" src="images/1.jpg" alt="Second slide">

        </div>
        <div class="item">

          <img class="third-slide" src="images/2.jpg" alt="Third slide">
 
        </div>
        <div class="item">

          <img class="fourth-slide" src="images/3.jpg" alt="Fourth slide">
 
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
	<div class="clearfix"></div>
	<div class="welcomepage">
		<H1>Welcome to Book stacks!</H1>
	</div>


<!-- banner -->
<jsp:include page="footer.jsp"></jsp:include>