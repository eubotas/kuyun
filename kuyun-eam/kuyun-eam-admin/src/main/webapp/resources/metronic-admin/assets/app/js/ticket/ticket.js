var addGalleryUploader;
var editGalleryUploader;

$(document).ready(function() {
    selectedItemColor("ticketList", category);

    //codes works on all bootstrap modal windows in application
    $('.modal').on('hidden.bs.modal', function(e)
    {
        jQuery("#add_Form").validate().resetForm();
        jQuery("#edit_Form").validate().resetForm();
    }) ;
    generateAddEditForm('template-ticket-addEditForm', 'add_,edit_', null, null, 'addTicketFormContainer,editTicketFormContainer');
    FormWidgets.init('add');
    FormWidgets.init('edit');

    $('#add_equipmentId, #edit_equipmentId').select2({minimumResultsForSearch: -1});

    addGalleryUploader = new qq.FineUploader($.extend(uploadImageOpt, {element : document.getElementById("add_fine-uploader-gallery")}));
    editGalleryUploader = new qq.FineUploader($.extend(uploadImageOpt, {element : document.getElementById("edit_fine-uploader-gallery")}));


    $('#createButton').click(function(){
        resetFileUpload('addTicketFormContainer');
        $("#addTicketFormContainer").modal("show");
        ajaxGet(basePath+'/manage/ticket/create', function (responseData) {
            if (responseData) {
                var data = responseData.data;
                addOptionToHtmlSelect(null, "add_equipmentId", data.equipments);
                buildTicketType('add', data.ticketTypes);
                buildTicketPriority('add', data.ticketPriorityList);
            }
        });
    });

    $('#deleteButton').click(function(){
        deleteAction();
    });
});

function buildTicketType(templateId, ticketTypes, ticketTypeId) {
    $("#"+templateId+"_ticketTypeDiv").empty();
    var html = '<div class="m-radio-inline">';
    $.each(ticketTypes,
        function(i, ticketType) {
            html += '<label class="m-radio">';

            var checked = "";



            if ('edit' === templateId && ticketType.VALUEFIELD === ticketTypeId.toString()){
                checked  = "checked";
            }else if ('add' === templateId && i == 0){
                checked  = "checked";
            }

            html += '<input type="radio" id="'+templateId+'_ticketTypeId_'+ ticketType.VALUEFIELD +'" name="ticketTypeId" value='+ ticketType.VALUEFIELD + ' ' + checked+'>';
            html += ticketType.DESCFIELD;
            html += '<span></span></label>';
        }
    );

    html += '</div>';
    $("#"+templateId+"_ticketTypeDiv").append(html);

}

function buildTicketPriority(templateId, ticketPriorityList, priority) {
    $("#"+templateId+"_ticketPriorityDiv").empty();
    var html = '<div class="m-radio-inline">';
    $.each(ticketPriorityList,
        function(i, ticketPriority) {
            html += '<label class="m-radio">';

            var checked = "";

            if ('edit' === templateId && ticketPriority.DESCFIELD === priority){
                checked  = "checked";
            }else if ('add' === templateId && i == 0){
                checked  = "checked";
            }

            html += '<input type="radio" id="'+templateId+'_priority_'+ ticketPriority.DESCFIELD +'" name="priority" value='+ ticketPriority.DESCFIELD +' '+checked+'>';

            html += ticketPriority.DESCFIELD;
            html += '<span></span></label>';
        }
    );

    html += '</div>';
    $("#"+templateId+"_ticketPriorityDiv").append(html);
}

function toAction(type){
    window.location = basePath+'/manage/ticket/index?category='+ type;
}

var $table = $('#table');
$(function() {
    $table.bootstrapTable({
        url: basePath+'/manage/ticket/list?category=${category}',
        striped: true,
        search: true,
        searchAlign: 'left',
        toolbarAlign: 'right',
        minimumCountColumns: 2,
        clickToSelect: true,
        detailView: true,
        detailFormatter: 'detailFormatter',
        pagination: true,
        paginationLoop: false,
        sidePagination: 'server',
        silentSort: false,
        smartDisplay: false,
        escape: true,
        searchOnEnterKey: true,
        maintainSelected: true,
        idField: 'ticketId',
        columns: [
            {field: 'ck', checkbox: true},
            {field: 'ticketNumber', title: '工单编号',  align: 'center', searchable: true},
//                    {field: 'ticketType.name', title: '工单类型',  align: 'center', searchable: true},
//                    {field: 'priority', title: '优先级',  align: 'center', searchable: true},
//                    {field: 'serviceman', title: '维修人',  align: 'center', searchable: true},
//                    {field: 'servicePhone', title: '维修人电话',  align: 'center', searchable: true},
//                    {field: 'customerContacts', title: '提报人',  align: 'center', searchable: true},
//                    {field: 'customerPhone', title: '提报人电话',  align: 'center', searchable: true},
            {field: 'status', title: '当前状态',  align: 'center', searchable: true, formatter: 'formatStatus'},
            {field: 'description', title: '工单描述' , width: 350, searchable: true},
            {field: 'action', width: 120, title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
        ]
    });
    setSearchPlaceholder('工单编号');
});

function actionFormatter(value, row, index) {
    return [
        '<a id="detail" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="详细">	<i class="la flaticon-file-1"></i>	</a>',
        '<shiro:hasPermission name="eam:ticket:update"><a id="update" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-accent m-btn--icon m-btn--icon-only m-btn--pill" title="编辑">	<i class="la la-edit"></i>	</a></shiro:hasPermission>',
        '<shiro:hasPermission name="eam:ticket:delete"><a id="delete" href="javascript:void(0)" class="m-portlet__nav-link btn m-btn m-btn--hover-danger m-btn--icon m-btn--icon-only m-btn--pill" title="删除">	<i class="la la-trash"></i>	</a></shiro:hasPermission>'
    ].join('');
}

var FormWidgets = function () {
    var createForm = function (formid) {
        $("#"+formid+"_Form").validate({
            // define validation rules
            rules: {
                description: {
                    required: true,
                    maxlength: 200
                },
                equipmentId: {
                    required: true
                }
            },
            submitHandler: function (form) {
                if(formid == 'add')
                    submitForm();
                else{
                    submitForm($('#edit_id').val());
                }

            }
        });
    }

    return {
        // public functions
        init: function (formid) {
            createForm(formid);
        }
    };
}();

function submitForm(id) {
    var targetUrl=basePath+'/manage/ticket/create';
    var formId='add_Form';
    if(id){
        targetUrl=basePath+'/manage/ticket/update/'+id;
        formId='edit_Form';
        setImagePathEdit('edit_imagePath', getUploadFileName(editGalleryUploader));
    }else{
        $('#add_impagePath').val(getUploadFileName(addGalleryUploader));
    }
    ajaxPost(targetUrl, formId, function(result) {
        if (result.code != 1) {
            sendErrorInfo(result);
        } else {
            if(formId=='add_Form') {
                toastr.success("新建工单成功");
                $('#addTicketFormContainer').modal('toggle');
            }else{
                toastr.success("编辑工单成功");
                $('#editTicketFormContainer').modal('toggle');
            }
            $table.bootstrapTable('refresh');
        }
    });
}


function updateAction(row) {
    $("#editTicketFormContainer").modal("show");
    resetFileUpload('editTicketFormContainer');

    ajaxGet(basePath+'/manage/ticket/update/' + row["ticketId"], function (responseData) {
        if (responseData) {
            var data = responseData.data;
            // 赋值
            $("#edit_id").val(data.ticket.ticketId);
            $("#edit_description").val(data.ticket.description);
            addOptionToHtmlSelect(data.ticket.equipmentId, "edit_equipmentId", data.equipments );
            buildTicketType('edit', data.ticketTypes, data.ticket.ticketTypeId);
            buildTicketPriority('edit', data.ticketPriorityList, data.ticket.priority);
            $("#edit_imagePath").val(data.ticket.imagePath);
            showImageFiles(data.ticket.imagePath, 'edit_imagePath');
        }
    });
}

window.actionEvents = {
    'click #update': function (e, value, row, index) {
        updateAction(row);

    },
    'click #delete': function (e, value, row, index) {
        var rows = new Array();
        rows.push(row);
        deleteActionImpl(rows);
    },
    'click #detail': function (e, value, row, index) {
        var ticketId = row["ticketId"];
        window.location = basePath+'/manage/ticket/detail/' +ticketId;
    }
};

function deleteAction(){
    var rows = $table.bootstrapTable('getSelections');
    deleteActionImpl(rows);
}

function deleteActionImpl(rows) {
    if (rows.length == 0) {
        swWarn("请至少选择一条记录");
    }else {
        deleteRows(rows,'ticketId',basePath+'/manage/ticket/delete/', "请确认要删除选中的工单吗？", "删除工单成功");
    }//end else
}