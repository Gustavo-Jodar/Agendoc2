<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendoc</title> 

        <link rel="stylesheet" href="../styles/main.css"> 
        <link rel="stylesheet" href="../styles/header.css">
        <link rel="stylesheet" href="../styles/page-profissional-login.css"> 
        <link rel="stylesheet" href="../styles/forms.css">
        <link rel="stylesheet" href="../styles/erro.css">

    </head>
    <body id= "page-prof-register" >
            <fmt:bundle basename="messages">
        <div id= "container">
            <header class="page-header">
                <div class="top-bar-container">
                    <a href="../user/userType.jsp">
                    <img src="../images/back.svg" alt="Voltar">
                    </a>
                </div>

                <div class="header-content">
                    <strong><fmt:message key="make_available_appointments"/></strong> 
                    <p><fmt:message key="acess_to_specialities_sub"/></p>
                </div>
            </header>

            <main>
                <fmt:bundle basename="messages">
                    <!-- <form method="post" action="fileuploadservlet" enctype="multipart/form-data">
                        <input type="file" name="file" />
                        <input type="submit" value="Upload" />
                    </form> -->
                    <form action="saveProfissional" method="POST" id="register-prof" enctype="multipart/form-data">
                        <fieldset>
                            <legend><fmt:message key="your_data"/></legend>
                            <div class="file-block">
                                <label for="file">Curriculo em PDF</label>
                                <input type="file" name="file" />
                            </div>
                            <div class="input-block">
                                <label for="nome"><fmt:message key="name"/></label>
                                <input name="nome" id="nome" required>
                            </div>
                            <div class="input-block">
                                <label for="email">E-mail</label>
                                <input name="email" id="email" required>
                            </div>
                            <div class="input-block">
                                <label for="senha"><fmt:message key="password"/></label>
                                <input name="senha" id="senha" type="password" required>
                            </div>
                            <!-- <div class="input-block">
                                <label for="avatar">Curriculo em PDF</label>
                                <input name="avatar" id="avatar" type="url">
                            </div> -->
                            <div class="input-block">
                                <label for="cpf"><fmt:message key="id_cpf"/></label>
                                <input name="cpf" id="cpf" type="number" required>
                            </div>
                            <div class="input-block">
                                <label for="nascimento"><fmt:message key="birth"/></label>
                                <input type="date" name="nascimento">
                            </div>

                            <div class="textarea-block">
                                <label for="bio"><fmt:message key="bio"/></label>
                                <textarea name="bio" id="bio"></textarea>
                            </div>
                        </fieldset>

                        <fieldset>
                            <legend><fmt:message key="area_of_knowlegde"/></legend>
                            <div class="select-block">
                                <label for="area">Área</label>
                                <select name="area" id="area required" required>
                                    <option value=""><fmt:message key="select_option"/></option>
                                    <option value="1"><fmt:message key="Medicina"/></option>
                                    <option value="2"><fmt:message key="Advocacia"/></option>
                                    <option value="3"><fmt:message key="Psicologia"/></option>
                                    <option value="4"><fmt:message key="Educacao"/></option>
                                    <option value="5"><fmt:message key="Nutricao"/></option>
                                    <option value="4"><fmt:message key="Terapia"/></option>
                                </select>
                            </div>

                            <div class="input-block">
                                <label for="especialidade"><fmt:message key="speciality"/></label>
                                <input type="especialidade" id="especialidade" name="especialidade" required>
                            </div>
                        </fieldset>

                    </form>            
                    <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <div>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <div class="alerta" > ${erro} </div>
                        </c:forEach>
                </div>
            </div>
        </c:if>  
                </fmt:bundle>
                <footer>
                    <button type="submit" form="register-prof"><fmt:message key="save_register"/></button>
                </footer>
            </main>
        </div>
        </fmt:bundle>
    </body>
</html>

