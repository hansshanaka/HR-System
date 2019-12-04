/* 
 * 
 */


$(document).ready(function () {


    // =================================================================
    var faIcon = {
        valid: 'fa fa-check-circle fa-lg text-success',
        invalid: 'fa fa-times-circle fa-lg',
        validating: 'fa fa-refresh'
    };

    // =================================================================
    $('#leaveForm').bootstrapValidator({
        message: 'This value is not valid',
        excluded: ':disabled',
        disable: false,
        feedbackIcons: faIcon,
        fields: {
            
            user_code: {
                validators: {
                        notEmpty: {
                        message: 'Please Select User Code.'
                    }
                }
            },
            ltype_id: {
                validators: {
                        notEmpty: {
                        message: 'Please Select Leave Type.'
                    }
                }
            },
            days: {
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    }
                }
            }

        }
    });

});
