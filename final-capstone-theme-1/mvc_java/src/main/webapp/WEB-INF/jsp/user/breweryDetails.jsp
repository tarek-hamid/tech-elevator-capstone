<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Begin Page Content -->
<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">
        <c:set var="brewery" value="${requestScope.brewery}"/>
        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row justify-content-center">
                        <!--<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>-->
                        <br>
                        <br>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="display-3 orange beer-title-font">${brewery.name}</h1>
                                </div>
                                    <c:choose>
                                        <c:when test="${brewery.active == true} ">
                                            <div class="text-md-center black">
                                                <b>This Brewery Is Currently Active</b>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="text-md-center black">
                                                <b>This Brewery Is Currently Inactive</b>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                <br>
                                <ul class="list-group">
                                    <li class="list-group-item">
                                        <b>History: </b>${brewery.history}
                                    </li>
                                    <li class="list-group-item">
                                        <b>Open from: </b>${brewery.openFrom}
                                    </li>
                                    <li class="list-group-item">
                                        <b>Open to: </b>${brewery.openTo}
                                    </li>
                                    <li class="list-group-item">
                                        <b>Phone number:</b> ${brewery.phoneNumber}
                                    </li>
                                    <li class="list-group-item">
                                        <b>Website: </b>${brewery.website}
                                    </li>
                                    <li class="list-group-item">
                                        <b>Email: </b>${brewery.email}
                                    </li>
                                    <li class="list-group-item">
                                        <b>Address: </b>${brewery.address}
                                    </li>
                                </ul>
                            </div>
                            <c:if test="${LOGGED_USER.getRole().equals(\"Brewer\")}">
                                <div class="text-center">
                                    <a href="/brewer/addBeer?breweryId=${brewery.breweryId}"   class="btn btn-light btn-icon-split">
                                        <span class="icon text-gray-600">
                                            <i class="fas fa-arrow-right"></i>
                                        </span>
                                        <span class="text">Add Beer</span>
                                    </a>
                                    <a class="btn btn-light btn-icon-split" href="/brewer/updateBrewery?breweryId=${brewery.breweryId}">
                                            <span class="icon text-gray-600">
                                                <i class="fas fa-fw fa-table"></i>
                                            </span>
                                        <span class="text">Update Brewery</span>
                                    </a>
                                </div>
                            </c:if>
                            <br>
                            <br>
                            <h3 class="text-center orange">
                                Beers We Offer...
                            </h3>
                            <br>
                        </div>
                            <div class="container-fluid">
                                <div class="row-2">
                                    <c:forEach items="${beers}" var="beer">
                                    <div class="col-md-4 col-md-4 col-md-4 col-md-4">
                                        <div class="card shadow mb-4">
                                            <div class="card-header py-3">
                                                <h6 class="m-0 font-weight-bold text-primary text-center">
                                                   <a href="beerDetails?id=${beer.beerId}">
                                                           ${beer.name}
                                                   </a>
                                                </h6>
                                            </div>
                                            <div class="card-body text-center">
                                                ${beer.description}
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

</div>
<!-- End of Page Content -->

<c:import url="/WEB-INF/jsp/common/footer.jsp" />