
<%@ include file="../layout/header.jsp"%>

<!-- some CSS styling changes and overrides -->
<style>
    .kv-avatar .krajee-default.file-preview-frame,.kv-avatar .krajee-default.file-preview-frame:hover {
        margin: 0;
        padding: 0;
        border: none;
        box-shadow: none;
        text-align: center;
    }
    .kv-avatar {
        display: inline-block;
    }
    .kv-avatar .file-input {
        display: table-cell;
        width: 213px;
    }
    .kv-reqd {
        color: red;
        font-family: monospace;
        font-weight: normal;
    }
</style>

<!-- markup -->
<!-- note: your server code `/site/avatar_upload/2` will receive `$_FILES['avatar-2']` on form submission -->
<!-- the avatar markup -->
<div id="kv-avatar-errors-2" class="center-block" style="width:800px;display:none"></div>
<form action="/post" method="post" enctype="multipart/form-data">
    <div class="form-group row">
        <label for="inputTitle" class="col-sm-2 col-form-label"><strong>제목</strong></label>
        <div class="col-sm-10">
            <input type="text" name="title" class="form-control" id="inputTitle">
        </div>
    </div>
    <div class="form-group row">
        <label for="inputAuthor" class="col-sm-2 col-form-label"><strong>작성자</strong></label>
        <div class="col-sm-10">
            <input type="text" name="author" class="form-control" id="inputAuthor">
        </div>
    </div>
    <div class="form-group row">
        <label for="inputContent" class="col-sm-2 col-form-label"><strong>내용</strong></label>
        <div class="col-sm-10">
            <textarea type="text" name="content" class="form-control" id="inputContent"></textarea>
        </div>
    </div>
    <div class="form-group row">
        <label for="inputFile" class="col-sm-2 col-form-label"><strong>첨부 파일</strong></label>
        <div class="col-sm-10">
            <div class="custom-file" id="inputFile">
                <input name="file" type="file" class="custom-file-input" id="customFile">
                <label class="custom-file-label" for="customFile">파일을 선택해 주세요.</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-auto mr-auto"></div>
        <div class="col-auto">
            <input class="btn btn-primary" type="submit" role="button" value="글쓰기">
        </div>
    </div>
</form>

<!-- the fileinput plugin initialization -->
<script>
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>

<%@ include file="../layout/footer.jsp"%>

