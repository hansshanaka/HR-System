/* 
 * user-status-form-validator
 */


$(document).ready(function () {


    // =================================================================
    var faIcon = {
        valid: 'fa fa-check-circle fa-lg text-success',
        invalid: 'fa fa-times-circle fa-lg',
        validating: 'fa fa-refresh'
    };

    // =================================================================
    $('#emp_status').bootstrapValidator({
        message: 'This value is not valid',
        excluded: ':disabled',
        disable: false,
        feedbackIcons: faIcon,
        fields: {
            
            emp_status: {
                //container: 'tooltip',
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: 'Characters amount is out of range'
                    }
                }
            }

        }
    });

});