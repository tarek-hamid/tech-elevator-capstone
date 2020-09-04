<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

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
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">${brewery.name}</h1>
                                </div>
                                <ul>
                                    <li>
                                        Active: ${brewery.active}
                                    </li>
                                    <li>
                                        History: ${brewery.history}
                                    </li>
                                    <li>
                                        Open from: ${brewery.openFrom}
                                    </li>
                                    <li>
                                        Open to: ${brewery.openTo}
                                    </li>
                                    <li>
                                        Phone number: ${brewery.phoneNumber}
                                    </li>
                                    <li>
                                        Website: ${brewery.website}
                                    </li>
                                    <li>
                                        Email: ${brewery.email}
                                    </li>
                                    <li>
                                        Address: ${brewery.address}
                                    </li>
                                    <li>
                                        Beer List:
                                        <c:forEach items="${beers}" var="beer">
                                            <a href="beerDetails?id=${beer.beerId}">
                                                ${beer.name}
                                            </a>
                                        </c:forEach>
                                    </li>
                                    <a href="/addBeer?id=${brewery.breweryId}"   class="btn btn-light btn-icon-split">
                                        <span class="icon text-gray-600">
                                            <i class="fas fa-arrow-right"></i>
                                        </span>
                                        <span class="text">Add Beer</span>
                                    </a>
                                </ul>
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