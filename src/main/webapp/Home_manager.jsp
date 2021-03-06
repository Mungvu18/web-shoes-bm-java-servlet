<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <!--begin of menu-->
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="/home_manager">Shoes</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                    aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                <ul class="navbar-nav m-auto">
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="#">Manager Account</a>--%>
<%--                    </li>--%>
                    <li class="nav-item">
                        <a class="nav-link" href="/managerProduct">Manager Product</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#">Hello Manager </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="home_login">Logout</a>
                    </li>
                </ul>

                <form action="/search" method="post" class="form-inline my-2 my-lg-0">
                    <div class="input-group input-group-sm">
                        <input name="txt" type="text" class="form-control" aria-label="Small"
                               aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-secondary btn-number">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                    <a class="btn btn-success btn-sm ml-3" href="show">
                        <i class="fa fa-shopping-cart"></i> Cart
                        <span class="badge badge-light">3</span>
                    </a>
                </form>
            </div>
        </div>
    </nav>
    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">Siêu thị giày chất lượng cao</h1>
            <p class="lead text-muted mb-0">Uy tín tạo nên thương hiệu với hơn 10 năm cung cấp các sản phầm giày nhập từ
                Trung Quốc</p>
        </div>
    </section>
    <!--end of menu-->
            <div class="container">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="/home_manager">Home</a></li>
                                <li class="breadcrumb-item"><a href="#">Category</a></li>
                                <li class="breadcrumb-item active" aria-current="#">Sub-category</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="card bg-light mb-3">
                            <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                            <ul class="list-group category_block">
                                <c:forEach items="${categories}" var="o">
                                    <li class="list-group-item text-white"><a href="category?id=${o.getId()}">${o.getName()}</a></li>
                                </c:forEach>

                            </ul>
                        </div>
                        <div class="card bg-light mb-3">
                            <div class="card-header bg-success text-white text-uppercase">New Product</div>
                            <c:forEach items="${productList}" var="o">
                            <div class="card-body">
                                <img class="img-fluid" src="${o.getImage()}" />
                                <h5 class="card-title">${o.getName()}</h5>
                                <p class="bloc_left_price">Giá: ${o.getPrice()} VNĐ</p>
                                <p class="card-text">Sản phẩm mới nhất</p>
                            </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="col-sm-9">
                        <div class="row">
                        <c:forEach items="${products}" var="o">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img class="card-img-top" style="text-align: center; height: 250px;max-height: 100%;max-width: 100%" src="${o.getImage()}" alt="Not source">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?id=${o.getId()}" title="View Product">${o.getName()}</a></h4>
<%--                                        <p class="card-text show_txt">${o.getDescription()}</p>--%>
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-danger btn-block">${o.getPrice()} VNĐ</p>
                                            </div>
                                            <div class="col">
                                                <a href="#" class="btn btn-success btn-block">Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>

