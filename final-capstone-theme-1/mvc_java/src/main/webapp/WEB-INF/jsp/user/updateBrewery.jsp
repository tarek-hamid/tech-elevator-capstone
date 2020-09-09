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
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Update Brewery</h1>
                                </div>
                                <form:form class="user" method="POST" action="/brewer/updateBrewery" modelAttribute="brewery">
                                    <input type="hidden" name="destination" value="${param.destination}"/>
                                    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
                                    <input type="hidden" name="breweryId" value="${breweryId}"/>
<%--                                    <div class="form-group">--%>
<%--                                        <input type="text" class="form-control form-control-user"--%>
<%--                                               name="breweryId" id="breweryId" placeholder="Brewery ID">--%>
<%--                                        <form:errors path="breweryId" />--%>
<%--                                    </div>--%>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user"
                                               name="name" id="name" aria-describedby="nameHelp" placeholder="Brewery Name">
                                        <form:errors path="name" cssClass="error"/>
                                    </div>
                                    <div class="form-group">
                                        <input type="time" class="form-control form-control-user"
                                               name="openFrom" id="openFrom" placeholder="Open From">
                                        <form:errors path="openFrom" cssClass="error"/>
                                    </div>
                                    <div class="form-group">
                                        <input type="time" class="form-control form-control-user"
                                               name="openTo" id="openTo" placeholder="Open To">
                                        <form:errors path="openTo" cssClass="error"/>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user"
                                               name="phoneNumber" id="phoneNumber" placeholder="Phone Number" value="(000)000-0000">
                                        <form:errors path="phoneNumber" cssClass="error"/>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user"
                                               name="website" id="website" placeholder="Website">
                                        <form:errors path="website" cssClass="error"/>
                                    </div>
                                    <div class="form-group">
                                        <input type="email" class="form-control form-control-user"
                                               name="email" id="email" placeholder="Email">
                                        <form:errors path="email" cssClass="error"/>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user"
                                               name="address" id="address" placeholder="Address">
                                        <form:errors path="address" cssClass="error"/>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user"
                                               name="history" id="history" placeholder="History">
                                        <form:errors path="history" cssClass="error"/>
                                    </div>
                                    <div class="form-group">
                                        <div class="custom-control custom-checkbox small">
                                            <input type="checkbox" class="custom-control-input" value="true" id="active">
                                            <label class="custom-control-label" for="active">Is Active</label>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-user btn-block">
                                        Update
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