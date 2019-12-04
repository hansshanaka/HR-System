/* 
 * user-Department-form-validator
 */


$(document).ready(function () {


    // =================================================================
    var faIcon = {
        valid: 'fa fa-check-circle fa-lg text-success',
        invalid: 'fa fa-times-circle fa-lg',
        validating: 'fa fa-refresh'
    };

    // =================================================================
    $('#userDepartment').bootstrapValidator({
        message: 'This value is not valid',
        excluded: ':disabled',
        disable: false,
        feedbackIcons: faIcon,
        fields: {
            
            department_name: {
                //container: 'tooltip',
                validators: {
                        notEmpty: {
                        message: 'This field is required and cannot be empty.'
                    },
                    stringLength: {
                        min: 1,
                        max: 60,
                        message: 'Characters amount is out of range'
                    }
                }
            }

        }
    });

});