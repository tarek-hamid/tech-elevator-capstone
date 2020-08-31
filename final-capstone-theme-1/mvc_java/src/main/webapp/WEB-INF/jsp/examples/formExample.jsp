<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<!-- Begin Page Content -->
<div class="container-fluid">
  <div class="form-container">
    <c:url var="formExampleUrl" value="/formExample"/>
    <form:form action="${formExampleUrl}" method="POST" modelAttribute="formExample">
      <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
      <fieldset>
        <legend>Form Example</legend>

        <div class="formgrid">
          <form:input id="name" name="name" path="name" required="true"/>
          <label for="name">name</label>

          <form:input type="date" id="startDate" name="startDate" path="startDate" required="true"/>
          <label for="startDate">Start Date</label>

          <form:select id="experience" name="experience" path="experience" required="true">
            <option value="1">1 year or less</option>
            <option value="2">2 years</option>
            <option value="3">3 - 4 years</option>
            <option value="5">5 years or more</option>
          </form:select>
          <label for="experience">experience</label>

          <input type="checkbox" id="knowHtml" name="knowHtml" path="knowHtml"/>
          <label for="knowHtml">HTML</label>

          <input type="checkbox" id="knowCss" name="knowCss" path="knowCss"/>
          <label for="knowCss">CSS</label>

          <input type="checkbox" id="knowJavascript" name="knowJavascript" path="knowJavascript"/>
          <label for="knowJavascript">JavaScript</label>

          <form:textarea id="skills" name="skills" path="skills"
                         rows="5" cols="20" required="true"/>
          <label for="skills">other skills</label>

          <button type="submit">SUBMIT</button>
        </div>
      </fieldset>
    </form:form>
  </div>
  <!-- /.form-container -->
</div>
<!-- /.container-fluid -->
<!-- End of Page Content -->

<!-- Page level plugins -->


<c:import url="/WEB-INF/jsp/common/footer.jsp" />