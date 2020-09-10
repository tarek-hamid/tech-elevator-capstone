<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h1 class="text-lg-center orange dashboard-title-font">Our Breweries</h1>
<br>
<div class="row-2">
    <c:forEach items="${breweries}" var="brewery">
    <div class="col-sm-4 col-sm-4 col-sm-4" >
            <!-- Collapsable Card Example -->
            <div class="card shadow mb-4">
                <!-- Card Header - Accordion -->
                <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="collapseCardExample">
                    <h6 class="m-0 font-weight-bold orange text-center"><b>${brewery.name}</b></h6>
                </a>
                <!-- Card Content - Collapse -->
                <div class="collapse show" id="collapseCardExample">
                    <div class="card-body">
                        <ul>
                            <li>
                                <b>Hours:</b> ${brewery.openFrom} - ${brewery.openTo}
                            </li>
                            <li>
                                <c:choose>
                                    <c:when test="${brewery.history != null}">
                                        <b>History: </b>${brewery.history}
                                    </c:when>
                                    <c:otherwise>
                                        <b>History: </b>None available yet.
                                    </c:otherwise>
                                </c:choose>
                            </li>
                            <li>
                                <c:choose>
                                    <c:when test="${brewery.website != null}">
                                        <b>Website: </b>${brewery.website}
                                    </c:when>
                                    <c:otherwise>
                                        <b>Website: </b>None available yet.
                                    </c:otherwise>
                                </c:choose>
                            </li>
                            <li>
                                <a class="black" href="dashboard/breweryDetails?id=${brewery.breweryId}">
                                    <b>View more about this brewery</b>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
    </div>
    </c:forEach>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />