<%-- 
    Document   : itemToolbarPageSize
    Created on : Jun 14, 2017, 1:41:18 PM
    Author     : u27brvz04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="btn-group pull-right toolbar-page-size">
    <button type="button" class="btn btn-default disabled">
        Кількість рядків на сторінці <span id="page-size">15</span>
    </button>
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <span class="caret"></span>
        <span class="sr-only">Toggle Dropdown</span>
    </button> 
    <ul id="toolbar-page-sizes-list" class="dropdown-menu dropdown-menu-right">
        <li><a>5</a></li>
        <li><a>10</a></li>
        <li><a>15</a></li>
        <li><a>25</a></li>
        <li><a>50</a></li>
    </ul>
</div>
