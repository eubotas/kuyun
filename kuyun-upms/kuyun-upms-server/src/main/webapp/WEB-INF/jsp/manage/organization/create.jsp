<!--begin::Modal-->
<div class="modal fade" id="create_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <form id="myForm" class="m-form m-form--fit m-form--label-align-right">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">
                        新建部门
                    </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">
												&times;
											</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="m-scrollable" data-scrollbar-shown="true" data-scrollable="true" data-height="200">
                        <div class="form-group">
                            <label for="name" class="form-control-label">
                                名称:*
                            </label>
                            <input type="text" class="form-control" id="name" name="name">
                        </div>

                        <div class="form-group">
                            <label for="description" class="form-control-label">
                                描述:*
                            </label>
                            <textarea class="form-control" id="description" name="description" rows="6"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        取消
                    </button>
                    <button type="submit" class="btn btn-primary" id="m_blockui_4_1">
                        提交
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
<!--end::Modal-->

<pageResources>
    <script>
        //== Class definition

        var FormWidgets = function () {
            var myForm = function () {
                $("#myForm").validate({
                    // define validation rules
                    rules: {
                        name: {
                            required: true,
                            minlength: 2,
                            maxlength: 20
                        },
                        description: {
                            required: true,
                            minlength: 2,
                            maxlength: 200
                        },
                    },
                    submitHandler: function (form) {
                        //form[0].submit(); // submit the form


                    }
                });
            }

            return {
                // public functions
                init: function () {
                    myForm();
                }
            };

        }();

        jQuery(document).ready(function () {
            FormWidgets.init();
        });

    </script>
</pageResources>