
<%@ include file="../layout/header.jsp"%>

<div class="container">

    <button class="btn btn-secondary" onclick="history.back()">BACK</button>
    <c:if test="${board.user.id == principal.user.id}">
        <a href="/board/${board.id}/updatePost" class="btn btn-warning">EDIT</a>
        <button id="btn-delete" class="btn btn-danger">DELETE</button>
    </c:if>
    <br/><br/>
    <div>
        Post No : <span id="id"><i>${board.id} </i></span>
        Author : <span><i>${board.user.username} </i></span>
    </div>
    <br/>
    <div class="form-group">
        <label for="title">Title</label>
        <h3>${board.title}</h3>
    </div>
    <hr />
    <div class="form-group">
        <div>${board.content}</div>
    </div>
    <hr />
    <button id="btn-save" class="btn btn-primary">POST</button>
</div>
<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp"%>

