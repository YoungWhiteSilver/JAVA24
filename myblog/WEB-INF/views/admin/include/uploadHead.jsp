<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
               <!-- 上传头像模态框 -->
   <div class="modal fade" id="addHeadModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">上传头像</h4>
        </div>
        <div class="modal-body">
              <div class="form-group">
              <form action="/admin/uploadHead" method="post" id="headForm" enctype="multipart/form-data">
                  <label class="col-sm-2 control-label">选择头像:</label>
                  <div class="col-sm-10">
                 <input type="hidden" id="adminId" name="adminId" value="${sessionScope.admin.id}"/>
                 <input type="file" name="file"/>
                  </div>
               </form>
                </div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary pull-left" id="btn">上传</button>
          <button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>