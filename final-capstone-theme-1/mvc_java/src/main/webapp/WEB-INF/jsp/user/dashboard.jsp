<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h1 class="text-lg-center">Our Breweries</h1>
<div class="row">
    <div class="col-sm-6 col-sm-6 col-sm-6" >
        <c:forEach items="${breweries}" var="brewery">
            <!-- Collapsable Card Example -->
            <div class="card shadow mb-4">
                <!-- Card Header - Accordion -->
                <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="collapseCardExample">
                    <h6 class="m-0 font-weight-bold text-primary">${brewery.name}</h6>
                </a>
                <!-- Card Content - Collapse -->
                <div class="collapse show" id="collapseCardExample">
                    <div class="card-body">
                        <ul>
                            <li>
                                History: ${brewery.history}
                            </li>
                            <li>
                                <a href="dashboard/breweryDetails?id=${brewery.breweryId}">
                                    View more about this brewery
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />