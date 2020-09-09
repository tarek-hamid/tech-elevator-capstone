<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Begin Page Content -->
<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">
        <c:set var="beer" value="${requestScope.beer}"/>
        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row justify-content-center">
                        <!--<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>-->
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="display-3 orange beer-title-font">${beer.name}</h1>
                                </div>
                                <div class="text-md-center black">
                                    <b>Average Rating:</b> 4.5
                                </div>
                                <br>
                                <ul class="list-group">
                                    <li class="list-group-item">
                                       <b>Description:</b>  ${beer.description}
                                    </li>
                                    <li class="list-group-item">
                                        <b>Alcohol by Volume:</b> ${beer.abv}
                                    </li>
                                    <li class="list-group-item">
                                        <b>Beer type:</b> ${beer.beerType}
                                    </li>
                                </ul>
                                <br>
                                <a href="/user/reviewBeer?id=${beer.beerId}" class="btn btn-light btn-icon-split">
                                        <span class="icon text-gray-600">
                                            <i class="fas fa-arrow-right"></i>
                                        </span>
                                    <span class="text">Review Beer</span>
                                </a>
                                <a href="/user/deleteBeer?id=${beer.beerId}" class="btn btn-light btn-icon-split">
                                        <span class="icon text-gray-600">
                                            <i class="fas fa-arrow-right"></i>
                                        </span>
                                    <span class="text">Delete Beer</span>
                                </a>
                            </div>
                        </div>
                            <div class="container-fluid">
                                <div class="row-2">
                                    <c:forEach var="ratings" items="${requestScope.ratings}">
                                    <div class="col-md-4 col-md-4 col-md-4 col-md-4">
                                        <div class="card shadow mb-4">
                                                <div class="card-header py-3">
                                                    <h6 class="m-0 font-weight-bold text-primary text-center">
                                                        <c:if test ="${ratings.rating < 1.5}">
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star unchecked"></span>
                                                                <span class="fa fa-star unchecked"></span>
                                                                <span class="fa fa-star unchecked"></span>
                                                                <span class="fa fa-star unchecked"></span>
                                                        </c:if>
                                                        <c:if test ="${ratings.rating >= 1.5 && ratings.rating < 2.5}">
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star unchecked"></span>
                                                                <span class="fa fa-star unchecked"></span>
                                                                <span class="fa fa-star unchecked"></span>
                                                        </c:if>
                                                        <c:if test ="${ratings.rating >= 2.5 && ratings.rating < 3.5}">
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star unchecked"></span>
                                                                <span class="fa fa-star unchecked"></span>
                                                        </c:if>
                                                        <c:if test ="${ratings.rating >= 3.5 && ratings.rating < 4.5}">
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star unchecked"></span>
                                                        </c:if>
                                                        <c:if test ="${ratings.rating >= 4.5}">
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star checked"></span>
                                                                <span class="fa fa-star checked"></span>
                                                        </c:if>
                                                    </h6>
                                                </div>
                                                <div class="card-body text-center">
                                                        ${ratings.ratingDescription}
                                                </div>
                                        </div>
                                    </div>
                                    </c:forEach>
                                </div>
                            </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>
<!-- End of Page Content -->

<c:import url="/WEB-INF/jsp/common/footer.jsp" />