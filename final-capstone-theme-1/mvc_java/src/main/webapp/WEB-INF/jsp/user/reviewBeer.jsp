<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<!-- Begin Page Content -->
<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row justify-content-center">
                        <!--<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>-->
                        <c:set var="rating" value="${requestScope.rating}"/>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Review Beer</h1>
                                </div>
                                <form:form class="user" method="POST" action="/user/reviewBeer" modelAttribute="rating">
                                    <input type="hidden" name="destination" value="${param.destination}"/>
                                    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user"
                                               name="ratingDescription" id="ratingDescription" aria-describedby="descriptionHelp" placeholder="ratingDescription">
                                        <form:errors path="ratingDescription" cssClass="error"/>
                                    </div>
                                    <div class="form-group">
                                        <input type="number" class="form-control form-control-user"
                                               name="rating" id="rating" placeholder="Rating (1-5)">
                                        <form:errors path="rating" cssClass="error"/>
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-user btn-block">
                                        Submit Review
                                    </button>
                                </form:form>
                                <hr>
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